package cloud.easy.utils;

/**
 * Title: Timer
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
public class Timer {

    private long start;

    public void start() {
        start = System.currentTimeMillis();
    }

    public long end() {
        long end = System.currentTimeMillis();
        return end - start;
    }
}
