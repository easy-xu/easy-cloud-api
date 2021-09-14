package pro.simplecloud.quna.service;

/**
 * Title: ResultService
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
public interface ResultService {


    /**
     * 计算问卷结果
     *
     * @param answerId 回答Id
     */
    void calculateScore(Long answerId);
}
