package cloud.easy.idgenerator.entity;

/**
 * Title: MaxNoCache
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 * @date 2019/5/21 10:54 首次创建
 * @date 2019/5/21 10:54 最后修改
 */
public class MaxNoCache {

    private String noType;
    private String noLimit;
    private int noStep;

    private long maxNo;
    private long curNo;
    private long nextNo;
    private String cachedKey;

    public String getNoLimit() {
        return noLimit;
    }

    public void setNoLimit(String noLimit) {
        this.noLimit = noLimit;
    }

    public String getNoType() {
        return noType;
    }

    public void setNoType(String noType) {
        this.noType = noType;
    }

    public int getNoStep() {
        return noStep;
    }

    public void setNoStep(int noStep) {
        this.noStep = noStep;
    }

    public long getMaxNo() {
        return maxNo;
    }

    public void setMaxNo(long maxNo) {
        this.maxNo = maxNo;
    }

    public long getCurNo() {
        return curNo;
    }

    public void setCurNo(long curNo) {
        this.curNo = curNo;
    }

    public long getNextNo() {
        return nextNo;
    }

    public void setNextNo(long nextNo) {
        this.nextNo = nextNo;
    }

    public String getCachedKey() {
        return cachedKey;
    }

    public void setCachedKey(String cachedKey) {
        this.cachedKey = cachedKey;
    }
}
