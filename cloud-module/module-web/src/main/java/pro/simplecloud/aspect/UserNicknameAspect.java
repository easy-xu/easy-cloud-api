package pro.simplecloud.aspect;


import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import pro.simplecloud.base.entity.BaseEntity;
import pro.simplecloud.base.mapper.WebMapperCust;
import pro.simplecloud.utils.SpringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Title: LogAspect
 * Description: 日志环绕切面
 *
 * @author Xu Honglin
 * @version 1.0
 * @date 2021/7/21 13:33 首次创建
 * @date 2021/7/21 13:33 最后修改
 */
@Order(1)
@Aspect
@Component
public class UserNicknameAspect {

    private static Map<String, String> nicknames = new HashMap<>();

    @AfterReturning(value = "execution(* pro.simplecloud.*.service.*.get*(..))", returning = "res")
    public Object controllerAround(Object res) {
        if (res instanceof BaseEntity) {
            queryNickname((BaseEntity) res);
        }
        if (res instanceof List) {
            for (Object item : (List) res) {
                if (item instanceof BaseEntity) {
                    queryNickname((BaseEntity) item);
                }
            }
        }
        return res;
    }

    private void queryNickname(BaseEntity entity) {
        String createBy = entity.getCreateBy();
        String updateBy = entity.getUpdateBy();
        entity.setCreateByNickname(getNickname(createBy));
        entity.setUpdateByNickname(getNickname(updateBy));
    }

    private String getNickname(String userNo) {
        if (nicknames.containsKey(userNo)) {
            return nicknames.get(userNo);
        } else {
            WebMapperCust webMapperCust = SpringUtils.getBean(WebMapperCust.class);
            String nickname = webMapperCust.getNickname(userNo);
            nicknames.put(userNo, nickname);
            return nickname;
        }
    }
}
