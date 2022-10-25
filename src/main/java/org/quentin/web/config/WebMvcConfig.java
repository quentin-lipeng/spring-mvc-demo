package org.quentin.web.config;

import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.quentin.web.validator.UserAccValidator;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.format.FormatterRegistry;
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
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;

/**
 * @author:quentin
 * @create: 2022-09-30 17:40
 * @Description: web config
 */
// 如果想进一步配置spring mvc 可以继承DelegatingWebMvcConfiguration来代替实现WebMvcConfigurer
// 并去掉@EnableWebMvc 注解
@EnableWebMvc
@ComponentScan("org.quentin.web.controller")
@Configuration
// 其中shiro的配置基本都是shiro提供好的 但由于某些程序报错不得不自己实现
// 现在采用getRootConfigClasses()进行配置注册 所以不需要在import下面的配置类
//@Import({
//        MybatisConfig.class,
//        ShiroBeanConfiguration.class,
//        ShiroWebFilterConfiguration.class,
//        ShiroConfig.class,
//        ShiroAnnotationProcessorConfiguration.class,
//        ShiroWebConfiguration.class,
//        ShiroRequestMappingConfig.class,
//        SpringBeanConfig.class,
//        FunctionalConfig.class
//})
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public FreeMarkerViewResolver freemarkerViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setCache(true);
        // 因为在freeMarkerConfigurer()已经配置前缀
        resolver.setPrefix("");
        resolver.setSuffix(".ftl");
        // 设置解析中文主要配置
        resolver.setContentType("text/html; charset=utf-8");
        resolver.setOrder(0);
        return resolver;
    }

    @Bean
    public UserAccValidator userAccValidator() {
        return new UserAccValidator();
    }

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
//        Properties properties = new Properties();
//        properties.setProperty("locale", "utf-8");
//        properties.setProperty("default_encoding", "utf-8");
//        configurer.setFreemarkerSettings(properties);
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

        registry.viewResolver(freemarkerViewResolver());
        registry.enableContentNegotiation(new MappingJackson2JsonView());
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
        registry.addResourceHandler("/favicon.ico")
                .addResourceLocations("classpath:/static/favicon.ico");
    }

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 如果RequestMapping有映射对应的路径那么下面的配置将失效
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
//    @Bean("globalValidator")
    public Validator getValidator() {
//        return new UserAccValidator();
        return null;
    }

    @Override
    public MessageCodesResolver getMessageCodesResolver() {
//        DefaultMessageCodesResolver codesResolver = new DefaultMessageCodesResolver();
        return null;
    }
}