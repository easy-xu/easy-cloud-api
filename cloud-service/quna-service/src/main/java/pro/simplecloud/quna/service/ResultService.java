package pro.simplecloud.quna.service;

import pro.simplecloud.quna.dto.ResultDto;

import java.util.List;

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

    /**
     * 查询问卷结果
     * @param answerId 回答Id
     * @return List<ResultDto>
     */
    List<ResultDto> listByAnswerId(Long answerId);
}
