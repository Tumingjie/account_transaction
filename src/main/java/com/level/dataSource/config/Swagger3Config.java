package com.level.dataSource.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.*;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
*@author TMJ
*@version 1.0
*@date 2022/5/10 11:37
*@description Swagger3.0配置
**/
@Configuration
@EnableOpenApi
public class Swagger3Config {
    /** 系统基础配置 */
    @Autowired
    private RuoYiConfig ruoyiConfig;

    /** 是否开启swagger */
    @Value("${swagger.enabled:false}")
    private boolean enabled;

    /** 预约与排队API包含的路径 */
    @Value("${swagger.queue-api-paths:/queue.*}")
    private String queueApiPaths;

    @Bean
    public Docket queueDocket(){
        String[] paths = queueApiPaths.split(",");
        return getDocket("预约与排队","预约与排队服务", "预约与排队服务", paths);
    }

    /**
     * 设置分组信息
     * @param groupName 组名
     * @param apiName   apiName
     * @param apiDesc   apiDesc
     * @param paths     正则路径匹配
     * @return docket
     */
    private Docket getDocket(String groupName,String apiName, String apiDesc, String[] paths){
        List<RequestParameter> requestParameterList = new ArrayList<>();
        RequestParameter requestParameter = new RequestParameterBuilder()
            .name("Authorization")
            .description("模拟用户Token")
            .in(ParameterType.HEADER)
            .required(false)
            .build();
        requestParameterList.add(requestParameter);

        ApiSelectorBuilder apiSelectorBuilder = new Docket(DocumentationType.OAS_30)
            .apiInfo(apiInfo(apiName, apiDesc))
            .groupName(groupName)
            .securitySchemes(Arrays.asList(new BasicAuth("test")))
            .select()
            .apis(RequestHandlerSelectors.any());

        if (paths == null) {//如果配置路径为空，则扫描所有com.ruoyi.*包
            apiSelectorBuilder.paths(PathSelectors.any());
            apiSelectorBuilder.apis(RequestHandlerSelectors.basePackage("com.level.*"));// 扫描指定包中的swagger注解
        } else {
            StringBuilder pathRegex = new StringBuilder();
            for (String path : paths) {
                pathRegex.append("(").append(path).append(")|");
            }
            apiSelectorBuilder.paths(PathSelectors.regex(pathRegex.substring(0, pathRegex.length() - 1)));
        }
        return apiSelectorBuilder.build()
            .globalRequestParameters(requestParameterList)
            .ignoredParameterTypes(HttpServletResponse.class, HttpServletRequest.class)
            .enable(enabled);// 是否启用Swagger
    }

    /**
     * swagger 标题信息
     * @param title 标题
     * @param description 描述
     * @return ApiInfo
     */
    private ApiInfo apiInfo(String title, String description) {
        // 用ApiInfoBuilder进行定制
        return new ApiInfoBuilder()
            // 设置标题
            .title(title)
            // 描述
            .description(description)
            // 作者信息
            .contact(new Contact(ruoyiConfig.getName(), null, null))
            // 版本
            .version("版本号:" + ruoyiConfig.getVersion())
            .build();
    }
}
