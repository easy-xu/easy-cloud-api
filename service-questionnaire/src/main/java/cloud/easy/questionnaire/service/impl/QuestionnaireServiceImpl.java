package cloud.easy.questionnaire.service.impl;

import cloud.easy.constant.Messages;
import cloud.easy.exception.RequestException;
import cloud.easy.exception.SystemErrorException;
import cloud.easy.file.entity.FileContent;
import cloud.easy.file.entity.FileMaster;
import cloud.easy.file.service.IFileContentService;
import cloud.easy.file.service.IFileMasterService;
import cloud.easy.questionnaire.dto.QuestionDto;
import cloud.easy.questionnaire.dto.QuestionnaireDto;
import cloud.easy.questionnaire.service.*;
import cloud.easy.quna.entity.*;
import cloud.easy.quna.service.*;
import cloud.easy.utils.Base64Utils;
import cloud.easy.utils.BeanUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Title: QuestionnaireServiceImpl
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class QuestionnaireServiceImpl implements QuestionnaireService {

    @Resource
    private IQunaConfigQuestionnaireService questionnaireService;

    @Resource
    private IQunaConfigQuestionService questionService;

    @Resource
    private IQunaConfigOptionService optionService;

    @Resource
    private IQunaConfigResultService resultService;

    @Resource
    private IQunaConfigResultScoreService resultScoreService;

    @Resource
    private IQunaConfigResultDescriptionService resultDescriptionService;

    @Resource
    private IFileMasterService fileMasterService;

    @Resource
    private IFileContentService fileContentService;


    @Override
    public List<QuestionDto> getQuestions(Long questionnaireId) {
        //??????????????????
        QunaConfigQuestionnaire questionnaire = questionnaireService.getById(questionnaireId);
        if (questionnaire == null) {
            throw new RequestException(Messages.NOT_FOUND);
        }
        //????????????
        QuestionnaireDto questionnaireDto = new QuestionnaireDto();
        BeanUtils.copy(questionnaire, questionnaireDto);
        //????????????
        QunaConfigQuestion question = new QunaConfigQuestion();
        question.setQuestionnaireId(questionnaireId);
        List<QunaConfigQuestion> questions = questionService.list(Wrappers.query(question).orderByAsc("order_num"));
        if (questions.isEmpty()) {
            throw new SystemErrorException(Messages.DB_DATA_ERROR);
        }
        //????????????
        return questions.stream().map(item -> {
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copy(item, questionDto);
            return questionDto;
        }).collect(Collectors.toList());
    }

    @Override
    public QunaConfigQuestionnaire importExcel(Long fileId) {
        //????????????
        FileMaster fileMaster = fileMasterService.getById(fileId);
        if (fileMaster == null) {
            throw new RequestException(Messages.NOT_FOUND);
        }
        FileContent fileContent = fileContentService.getById(fileMaster.getContentId());
        if (fileContent == null) {
            throw new RequestException(Messages.NOT_FOUND);
        }
        String fileName = fileMaster.getOriginalName();
        String content = fileContent.getContent();
        byte[] bytes = Base64Utils.getByte(content);
        QunaConfigQuestionnaire questionnaire = null;
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
            Workbook workbook = null;
            if (fileName.endsWith("xls")) {
                workbook = new HSSFWorkbook(inputStream);
            } else if (fileName.endsWith("xlsx")) {
                workbook = new XSSFWorkbook(inputStream);
            } else {
                throw new RequestException("??????????????????");
            }
            //???????????????
            Sheet questionnaireSheet = workbook.getSheet("??????");
            Row questionnaireRow = questionnaireSheet.getRow(1);
            String title = questionnaireRow.getCell(0).getStringCellValue();
            String desc = questionnaireRow.getCell(1).getStringCellValue();
            log.info("title: {}", title);
            log.info("desc: {}", desc);
            questionnaire = new QunaConfigQuestionnaire();
            questionnaire.setTitle(title);
            questionnaire.setShortDesc(desc);
            questionnaire.setParticipantNum(0L);
            questionnaire.setLikeNum(0L);
            questionnaireService.save(questionnaire);

            //???????????????
            Sheet resultSheet = workbook.getSheet("??????");
            //????????????????????????
            int index = 1;
            QunaConfigResult result = null;
            Row resultRow = resultSheet.getRow(index);
            int orderNum = 1;
            Map<String, Long> resultIds = new HashMap<>();
            while (resultRow != null) {
                String resultTitle = resultRow.getCell(0).getStringCellValue();
                String filedName = resultRow.getCell(1).getStringCellValue();
                String filedText = resultRow.getCell(2).getStringCellValue();
                if (StringUtils.hasLength(resultTitle)) {
                    result = new QunaConfigResult();
                    result.setQuestionnaireId(questionnaire.getId());
                    result.setTitle(resultTitle);
                    resultService.save(result);
                    orderNum = 1;
                    resultIds.put(resultTitle, result.getId());
                }
                if (result != null) {
                    QunaConfigResultDescription resultDescription = new QunaConfigResultDescription();
                    resultDescription.setQuestionnaireId(questionnaire.getId());
                    resultDescription.setResultId(result.getId());
                    resultDescription.setOrderNum(orderNum);
                    resultDescription.setName(filedName);
                    resultDescription.setValue(filedText);
                    resultDescriptionService.save(resultDescription);
                    orderNum++;
                }
                log.info("{}|{}|{}", resultTitle, filedName, filedText);
                resultRow = resultSheet.getRow(index++);
            }
            //???????????????
            Sheet questionsSheet = workbook.getSheet("??????");
            Row questionSubTitleRow = questionsSheet.getRow(1);
            //????????????????????????
            index = 2;
            QunaConfigQuestion question = null;
            Row questionsRow = questionsSheet.getRow(index);
            char optionValue = 'A';
            while (questionsRow != null) {
                double sort = questionsRow.getCell(0).getNumericCellValue();
                String type = questionsRow.getCell(1).getStringCellValue();
                String text = questionsRow.getCell(2).getStringCellValue();
                if ("??????".equals(type)) {
                    question = new QunaConfigQuestion();
                    question.setQuestionnaireId(questionnaire.getId());
                    question.setOrderNum((int) sort);
                    question.setTitle(text);
                    questionService.save(question);
                    optionValue = 'A';
                }
                if ("??????".equals(type) && question != null) {
                    QunaConfigOption option = new QunaConfigOption();
                    option.setQuestionId(question.getId());
                    option.setQuestionnaireId(questionnaire.getId());
                    option.setValue(String.valueOf(optionValue));
                    option.setText(text);
                    optionService.save(option);
                    optionValue = (char) (optionValue + 1);

                    //????????????
                    int resultSize = resultIds.size();
                    int offset = 3;
                    for (int i = 0; i < resultSize; i++) {
                        int cellNum = i + offset;
                        Cell scoreCell = questionsRow.getCell(cellNum);
                        if (scoreCell != null) {
                            double score = scoreCell.getNumericCellValue();
                            QunaConfigResultScore resultScore = new QunaConfigResultScore();
                            resultScore.setQuestionnaireId(questionnaire.getId());
                            resultScore.setQuestionId(question.getId());
                            resultScore.setOptionId(option.getId());
                            resultScore.setScore((long) score);
                            //????????????id
                            String resultTitle = questionSubTitleRow.getCell(cellNum).getStringCellValue();
                            Long resultId = resultIds.get(resultTitle);
                            resultScore.setResultId(resultId);
                            resultScoreService.save(resultScore);
                        }
                    }
                }
                log.info("{}|{}|{}", sort, type, text);
                questionsRow = questionsSheet.getRow(++index);
            }
            //????????????
            question = new QunaConfigQuestion();
            question.setQuestionnaireId(questionnaire.getId());
            long count = questionService.count(Wrappers.query(question));
            questionnaire.setQuestionNum((long) count);
            questionnaireService.saveOrUpdate(questionnaire);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemErrorException("??????????????????", e);
        }
        return questionnaire;
    }

    @Override
    public void deleteConfig(Long id) {
        questionnaireService.removeById(id);
        HashMap<String, Object> hashMap = new HashMap<>(1);
        hashMap.put("questionnaire_id", id);
        questionService.removeByMap(hashMap);
        optionService.removeByMap(hashMap);
        resultService.removeByMap(hashMap);
        resultScoreService.removeByMap(hashMap);
        resultDescriptionService.removeByMap(hashMap);
    }

}
