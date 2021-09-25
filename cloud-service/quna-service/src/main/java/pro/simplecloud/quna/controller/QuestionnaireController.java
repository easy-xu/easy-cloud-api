package pro.simplecloud.quna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.simplecloud.controller.BaseController;
import pro.simplecloud.quna.entity.QunaConfigQuestionnaire;
import pro.simplecloud.quna.service.IQunaConfigQuestionnaireService;

/**
 * Title: QuestionnaireController
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@RestController
@RequestMapping("/api/questionnaire")
public class QuestionnaireController extends BaseController<QunaConfigQuestionnaire, IQunaConfigQuestionnaireService> {

    @Autowired
    public QuestionnaireController(IQunaConfigQuestionnaireService service) {
        super(service);
    }
}
