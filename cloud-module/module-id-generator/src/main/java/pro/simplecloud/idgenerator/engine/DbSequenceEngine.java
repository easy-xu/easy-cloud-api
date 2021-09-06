package pro.simplecloud.idgenerator.engine;

import pro.simplecloud.idgenerator.entity.MaxNoCache;

import javax.sql.DataSource;
import java.sql.*;
import java.util.HashMap;

/**
 * Title: DbSequenceEngine
 * Description: 使用数据库生成自增序列
 *
 * @author Xu Honglin
 * @version 1.0
 */
public class DbSequenceEngine {

    private static final String DEFAULT_OPERATOR = "Engine";
    private static final int DEFAULT_NO_STEP = 100;
    private static final String DEFAULT_NO_LIMIT = "SN";
    /**
     * 本地缓存
     */
    private HashMap<String, MaxNoCache> maxNoLocalCache = new HashMap<>();
    /**
     * 数据源
     */
    private DataSource dataSource;

    public DbSequenceEngine(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 缓存KEY
     */
    private static String getCachedKey(String noType, String noLimit) {
        return "MaxNo_" + noType + "@" + noLimit;
    }


    /**
     * 获取指定序列的最大值, 默认步长100
     *
     * @param cNoType 序列类型
     * @return nextId
     */
    public long nextId(String cNoType) {
        return this.nextId(cNoType, DEFAULT_NO_LIMIT, DEFAULT_NO_STEP);
    }


    /**
     * 获取指定序列的最大值, 默认步长100
     *
     * @param cNoType  序列类型
     * @param cNoLimit 序列小类型，号码限制条件， 默认SN
     * @return nextId
     */
    public long nextId(String cNoType, String cNoLimit) {
        return this.nextId(cNoType, cNoLimit, DEFAULT_NO_STEP);
    }

    /**
     * 获取指定序列的最大值
     *
     * @param cNoType  序列类型
     * @param cNoLimit 序列小类型，号码限制条件， 默认SN
     * @param cNoStep  序列步长
     * @return nextId
     */
    public synchronized long nextId(String cNoType, String cNoLimit, int cNoStep) {
        long iCurNo = -1;
        if (cNoType == null || "".equals(cNoType)) {
            return iCurNo;
        }
        //不传，默认是SN
        if (cNoLimit == null || "".equals(cNoLimit)) {
            cNoLimit = DEFAULT_NO_LIMIT;
        }
        //步长不传默认为100
        if (cNoStep <= 0) {
            cNoStep = DEFAULT_NO_STEP;
        }

        //从本地缓存取下一个值
        String cachedKey = getCachedKey(cNoType, cNoLimit);
        MaxNoCache localCache = maxNoLocalCache.get(cachedKey);
        if (localCache != null) {
            long nextNo = localCache.getNextNo();
            if (nextNo >= localCache.getMaxNo()) {
                //本地号段用尽,申请新的号段空间
                applyMaxNo(localCache);
            } else {
                //号段内自增
                localCache.setCurNo(nextNo);
                localCache.setNextNo(nextNo + 1);
            }
        } else {
            //重启后第一次
            localCache = new MaxNoCache();
            localCache.setNoType(cNoType);
            localCache.setNoLimit(cNoLimit);
            localCache.setNoStep(cNoStep);
            localCache.setCachedKey(cachedKey);
            applyMaxNo(localCache);
            maxNoLocalCache.put(cachedKey, localCache);
        }
        return localCache.getCurNo();
    }

    /**
     * 从关系数据库中申请某一个号段的号码,
     * 行级锁申请
     * 步长更新为数据库配置
     */
    private synchronized void applyMaxNo(MaxNoCache maxNoCache) {
        Connection connection = null;
        PreparedStatement statement = null;
        Date dateTime = new Date(System.currentTimeMillis());
        String noType = maxNoCache.getNoType();
        String noLimit = maxNoCache.getNoLimit();
        try {
            connection = dataSource.getConnection();
            //开启事务
            connection.setAutoCommit(false);
            //查询并锁定
            statement = connection.prepareStatement("select max_no, no_step from sys_max_no where no_type = ? and no_limit = ? for update");
            statement.setString(1, noType);
            statement.setString(2, noLimit);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                //存在则自增
                long maxNo = resultSet.getLong(1);
                //步长更新为数据库
                int noStep = resultSet.getInt(2);
                maxNoCache.setNoStep(noStep);
                maxNo = maxNo + noStep;
                statement = connection.prepareStatement("update sys_max_no set max_no = ?, update_by = ?, update_time = ? where no_type = ? and no_limit = ?");
                statement.setLong(1, maxNo);
                statement.setString(2, DEFAULT_OPERATOR);
                statement.setDate(3, dateTime);
                statement.setString(4, noType);
                statement.setString(5, noLimit);
                if (statement.executeUpdate() != 1) {
                    throw new SQLException("update no record");
                } else {
                    //更新localCache
                    long curNo = maxNo - noStep;
                    maxNoCache.setMaxNo(maxNo);
                    maxNoCache.setNoStep(noStep);
                    maxNoCache.setCurNo(curNo);
                    maxNoCache.setNextNo(curNo + 1);
                }
            } else {
                //不存在则新增
                int noStep = maxNoCache.getNoStep();
                statement = connection.prepareStatement("insert into sys_max_no (`no_type`, `no_limit`, `max_no`, `no_step`, `remark`, `create_by`, `create_time`, `update_by`, `update_time`) values (?,?,?,?,?,?,?,?,?)");
                statement.setString(1, noType);
                statement.setString(2, noLimit);
                statement.setLong(3, noStep);
                statement.setInt(4, noStep);
                statement.setString(5, "");
                statement.setString(6, DEFAULT_OPERATOR);
                statement.setDate(7, dateTime);
                statement.setString(8, DEFAULT_OPERATOR);
                statement.setDate(9, dateTime);
                if (statement.executeUpdate() != 1) {
                    throw new SQLException("insert no record");
                } else {
                    //更新localCache
                    maxNoCache.setMaxNo(noStep);
                    maxNoCache.setCurNo(0);
                    maxNoCache.setNextNo(1);
                }
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            //关闭连接
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
