package pro.simplecloud.quna.constant;

/**
 * Title: AnswerFlow
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
public enum AnswerFlow {
    /**
     * 0初始化 1回答中 2回答完毕 3完成计算
     */
    INIT(0L),
    ANSWER(1L),
    SUBMIT(2L),
    CALCULATED(3L),
    FINISH(4L);

    public final Long value;

    AnswerFlow(Long value) {
        this.value = value;
    }
}
