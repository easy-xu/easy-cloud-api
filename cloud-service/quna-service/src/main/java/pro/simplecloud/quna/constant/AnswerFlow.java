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
     * 0初始化 1回答中 2回答完毕 3生成结果
     */
    INIT(0L),
    ANSWER(1L),
    SUBMIT(2L),
    FINISH(3L);

    public final Long value;

    AnswerFlow(Long value) {
        this.value = value;
    }
}
