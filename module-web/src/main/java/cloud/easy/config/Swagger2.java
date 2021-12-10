package cloud.easy.config;

import cloud.easy.constant.ApiHeaderTag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

import static org.assertj.core.util.Lists.newArrayList;

/**
 * Title: Swagger2
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Configuration
public class Swagger2 {


    /**
     * 创建API应用
     * apiInfo() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .globalRequestParameters(parameters())
                .securityContexts(securityContexts())
                .securitySchemes(securityScheme())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cloud.easy"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://项目实际地址/swagger-ui/index.html
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Easy Cloud RESTFul APIs")
                .build();
    }

    /**
     * 配置全局参数: 请求头
     */
    private List<RequestParameter> parameters() {
        RequestParameterBuilder builder = new RequestParameterBuilder();
        return newArrayList(
                builder.name(ApiHeaderTag.REQUEST_ID).in(ParameterType.HEADER).required(true).build()
        );
    }

    /**
     * 配置全局参数: JWT Token
     */
    private List<SecurityScheme> securityScheme() {
        return newArrayList(
                new ApiKey("JWT", ApiHeaderTag.TOKEN, "header"));
    }

    /**
     * 配置全局参数作用接口范围
     */
    private List<SecurityContext> securityContexts() {
        return newArrayList(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .build()
        );
    }

    /**
     * 配置全局参数作用接口范围
     */
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return newArrayList(new SecurityReference("JWT", authorizationScopes));
    }
}