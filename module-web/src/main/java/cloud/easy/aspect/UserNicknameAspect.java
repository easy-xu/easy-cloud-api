package cloud.easy.aspect;


import cloud.easy.base.entity.BaseEntity;
import cloud.easy.base.mapper.BaseMapperCust;
import cloud.easy.utils.SpringUtils;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Title: UserNicknameAspect
 * Description: 查询用户昵称
 *
 * @author Xu Honglin
 * @version 1.0
 * @date 2021/7/21 13:33 首次创建
 * @date 2021/7/21 13:33 最后修改
 */
@Order(2)
@Aspect
@Component
public class UserNicknameAspect {

    private static Map<String, String> nicknames = new HashMap<>();

    @AfterReturning(value = "execution( * cloud.easy.*.mapper.*Mapper.selectById(..))", returning = "res")
    public Object afterReturning(Object res) {
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
            BaseMapperCust baseMapperCust = SpringUtils.getBean(BaseMapperCust.class);
            String nickname = baseMapperCust.getNickname(userNo);
            nicknames.put(userNo, nickname);
            return nickname;
        }
    }
}
