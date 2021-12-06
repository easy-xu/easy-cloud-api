package cloud.easy.generator.service;

import cloud.easy.generator.config.db.ColumnInfo;
import cloud.easy.generator.config.db.TableInfo;
import cloud.easy.generator.mapper.TableInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * TableInfoService
 *
 * @author xu honglin
 * @date 2021/12/4 14:28
 */
@Service
public class TableInfoService {

    @Resource
    private TableInfoMapper mapper;

    public TableInfo queryTableInfo(String tableName) {
        TableInfo tableInfo = mapper.getTableInfo(tableName);
        List<ColumnInfo> columns = mapper.getColumnInfo(tableName);
        tableInfo.setColumns(columns);
        return tableInfo;
    }
}
