//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yh.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
@PropertySource({"classpath:application.properties"})
public class SwaggerConfig2 {
    @Value("${swagger.basePackage:com.yanxintec}")
    private String swaggerBasePackage;
    @Value("${swagger.pathMapping:/}")
    private String swaggerPathMapping;
    @Value("${swagger.enable:false}")
    private boolean swaggerEnable;
    @Value("${swagger.apiInfo.title:文档标题}")
    private String swaggerApiInfoTitle;
    @Value("${swagger.apiInfo.description:文档描述}")
    private String swaggerApiInfoDescription;
    @Value("${swagger.apiInfo.version:文档版本}")
    private String swaggerApiInfoVersion;
    @Value("${swagger.apiInfo.termsOfServiceUrl:}")
    private String swaggerApiInfoTermsOfServiceUrl;
    @Value("${swagger.apiInfo.author:yh}")
    private String swaggerApiInfoAuthor;
    @Value("${swagger.apiInfo.url:www.yanxintec.com}")
    private String swaggerApiInfoUrl;
    @Value("${swagger.apiInfo.email:admin@yanxintec.com}")
    private String swaggerApiInfoEmail;
    @Value("${swagger.apiInfo.license:}")
    private String swaggerApiInfoLicense;
    @Value("${swagger.apiInfo.license.url:}")
    private String swaggerApiInfoLicenseUrl;

    public SwaggerConfig2() {
    }

    @Bean
    public Docket api() {
        System.out.println(this.toString());
        return (new Docket(DocumentationType.SWAGGER_2)).apiInfo(this.apiInfo()).genericModelSubstitutes(new Class[]{DeferredResult.class}).useDefaultResponseMessages(false).forCodeGeneration(false).enable(this.swaggerEnable).pathMapping(this.swaggerPathMapping).select().apis(RequestHandlerSelectors.basePackage(this.swaggerBasePackage)).paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(this.swaggerApiInfoTitle, this.swaggerApiInfoDescription,
                this.swaggerApiInfoVersion, this.swaggerApiInfoTermsOfServiceUrl,
                new Contact(this.swaggerApiInfoAuthor, this.swaggerApiInfoUrl, this.swaggerApiInfoEmail),
                this.swaggerApiInfoLicense, this.swaggerApiInfoLicenseUrl,new ArrayList<VendorExtension>() );//,new ArrayList<VendorExtension>()
    }
    @Override
    public String toString() {
        return "SwaggerConfig{swaggerBasePackage='" + this.swaggerBasePackage + '\'' + ", swaggerPathMapping='" + this.swaggerPathMapping + '\'' + ", swaggerEnable=" + this.swaggerEnable + ", swaggerApiInfoTitle='" + this.swaggerApiInfoTitle + '\'' + ", swaggerApiInfoDescription='" + this.swaggerApiInfoDescription + '\'' + ", swaggerApiInfoVersion='" + this.swaggerApiInfoVersion + '\'' + ", swaggerApiInfoTermsOfServiceUrl='" + this.swaggerApiInfoTermsOfServiceUrl + '\'' + ", swaggerApiInfoAuthor='" + this.swaggerApiInfoAuthor + '\'' + ", swaggerApiInfoUrl='" + this.swaggerApiInfoUrl + '\'' + ", swaggerApiInfoEmail='" + this.swaggerApiInfoEmail + '\'' + ", swaggerApiInfoLicense='" + this.swaggerApiInfoLicense + '\'' + ", swaggerApiInfoLicenseUrl='" + this.swaggerApiInfoLicenseUrl + '\'' + '}';
    }
}
