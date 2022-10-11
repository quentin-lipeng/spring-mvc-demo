package org.quentin.web.config;

import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.apache.shiro.spring.config.ShiroAnnotationProcessorConfiguration;
import org.apache.shiro.spring.config.ShiroBeanConfiguration;
import org.apache.shiro.spring.web.config.ShiroRequestMappingConfig;
import org.apache.shiro.spring.web.config.ShiroWebConfiguration;
import org.apache.shiro.spring.web.config.ShiroWebFilterConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;

/**
 * @author:quentin
 * @create: 2022-09-30 17:40
 * @Description: web config
 */
@EnableWebMvc
//@ComponentScan("org.quentin.web")
@Configuration
// 其中shiro的配置基本都是shiro提供好的 但由于某些程序报错不得不自己实现
@Import({MybatisConfig.class,
//        ShiroBeanConfiguration.class,
//        ShiroWebFilterConfiguration.class,
        ShiroConfig.class,
        ShiroAnnotationProcessorConfiguration.class,
        ShiroWebConfiguration.class,
        ShiroRequestMappingConfig.class
})
public class WebMvcConfig implements WebMvcConfigurer {

    // 此方法为@Value注解工作 但加此bean的具体原因还没搞清楚
    // 目前的作用解决了 配置LifecycleBeanPostProcessor后 就算其bean配置为static注入
    // 依然解决不了@Value无法获取到值 但加此bean后解决
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer placeholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        Properties properties = new Properties();
        //此方法配置的property 可以使用@Value获取
//        properties.setProperty("user.lastname", "mike");
        // bring in some property values from a Properties file
        // 等同于@PropertySource("classpath:jdbc.properties")
        Resource resource = new ClassPathResource("jdbc.properties");
        placeholderConfigurer.setLocation(resource);
        placeholderConfigurer.setProperties(properties);
        return placeholderConfigurer;
    }

    @Bean
    public FreeMarkerViewResolver freemarkerViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setCache(true);
        // 因为在freeMarkerConfigurer()已经配置前缀
        resolver.setPrefix("");
        resolver.setSuffix(".ftl");
        resolver.setContentType("text/html; charset=utf-8");
        resolver.setOrder(0);
        return resolver;
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setViewClass(JstlView.class);
        bean.setPrefix("/WEB-INF/views/");
        bean.setSuffix(".jsp");
        bean.setOrder(1);
        return bean;
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setDefaultEncoding("utf-8");
        Properties properties = new Properties();
//        properties.setProperty("locale", "utf-8");
//        properties.setProperty("default_encoding", "utf-8");
        configurer.setFreemarkerSettings(properties);
        configurer.setTemplateLoaderPath("/WEB-INF/freemarker/");
        return configurer;
    }


    /*
    也可以使用FreeMarkerViewResolver
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        //此方法可以为freemarker进行默认配置
//        registry.freeMarker();
        registry.order(0);
        registry.viewResolver(freemarkerViewResolver());
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer pathMatchConfigurer) {

    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        // 配置接收前端请求的数据类型 但目前不需要进行复写
//        configurer.mediaType("json", MediaType.APPLICATION_JSON);
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer asyncSupportConfigurer) {

    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer defaultServletHandlerConfigurer) {

    }

    @Override
    public void addFormatters(FormatterRegistry formatterRegistry) {

    }

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 如果requestMapping有映射对应的路径那么下面的配置将失效
//        registry.addViewController("/index").setViewName("index");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> list) {

    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> list) {

    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
                .indentOutput(true)
                .dateFormat(new SimpleDateFormat("yyyy-MM-dd"))
                .modulesToInstall(new ParameterNamesModule());
        // 配置json转换
        converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> list) {

    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> list) {

    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> list) {

    }

    @Override
    public Validator getValidator() {
        return null;
    }

    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        return null;
    }
}