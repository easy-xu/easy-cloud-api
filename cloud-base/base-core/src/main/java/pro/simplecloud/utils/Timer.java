package pro.simplecloud.utils;

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

    public int end() {
        long end = System.currentTimeMillis();
        return (int) (end - start);
    }
}
