package com.example.shardingspheretest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * 启动程序后访问 http://127.0.0.1:8080/swagger-ui.html
 *
 * @author: yinsc
 * @Date: 2020/1/14
 * @Description:
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        List<Parameter> pars = new ArrayList<>();

        ParameterBuilder ticketPar = new ParameterBuilder();
        ticketPar.name("token").description("token") // TOKEN
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).defaultValue("").build(); //header中的ticket参数非必填，传空也可以
        pars.add(ticketPar.build());

//        ParameterBuilder ticketParFlag = new ParameterBuilder();
//        ticketParFlag.name("flag").description("flag[0:web端,1:APP端,2:winform,3:服务(Server)]") // FLAG
//                .modelRef(new ModelRef("string")).parameterType("header")
//                .required(false).defaultValue("0").build(); //header中的ticket参数非必填，传空也可以
//        pars.add(ticketParFlag.build());
//
//        ParameterBuilder companyFlag = new ParameterBuilder();
//        companyFlag.name("companyflag").description("companyflag[0:集团,1:太平洋,2:菁英]") // FLAG
//                .modelRef(new ModelRef("string")).parameterType("header")
//                .required(false).defaultValue("0").build(); //header中的ticket参数非必填，传空也可以
//        pars.add(companyFlag.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.shardingspheretest.controller"))
                .paths(PathSelectors.any())
                .build().globalOperationParameters(pars);
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("DEMO RESTFUL APIS")
                .description("DEMO 接口文档")
                .contact("yinsc")
                .version("1.0")
                .build();
    }

}
