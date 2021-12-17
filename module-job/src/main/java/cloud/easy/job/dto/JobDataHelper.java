package cloud.easy.job.dto;


/**
 * Title: JobDataHelper
 * Description: JobData ThreadLocal变量
 *
 * @author Xu Honglin
 * @version 1.0
 */
public class JobDataHelper {

    /**
     * 维护多线程间局部变量
     */
    private static final ThreadLocal<JobData> HOLDER = new ThreadLocal<>();

    private JobDataHelper() {
    }

    public static JobData get() {
        return HOLDER.get();
    }


    public static void set(JobData jobData) {
        HOLDER.set(jobData);
    }

    public static void remove() {
        HOLDER.remove();
    }
}
