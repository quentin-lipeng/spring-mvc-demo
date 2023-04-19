package org.quentin.web.config;

import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.apache.shiro.spring.web.config.ShiroRequestMappingConfig;
import org.quentin.web.validator.UserAccValidator;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.Ordered;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.CacheControl;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
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
import java.time.Duration;
import java.util.List;

/**
 * 如果想进一步配置spring mvc
 * 可以继承DelegatingWebMvcConfiguration来代替实现WebMvcConfigurer
 * 并去掉@EnableWebMvc 注解
 * EnableWebMvc导入了DelegatingWebMvcConfiguration
 *
 * @author:quentin
 * @create: 2022-09-30 17:40
 * @Description: web config
 */
@Configuration
@EnableWebMvc
@ComponentScan({"org.quentin.web.controller"})
@Import({ShiroRequestMappingConfig.class})
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public FreeMarkerViewResolver freemarkerViewResolver() {
        // 因为在freeMarkerConfigurer()已经配置前缀 所以前缀为<"">
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver("", ".ftl");
        resolver.setCache(true);
        // 设置解析中文主要配置
        resolver.setContentType("text/html; charset=utf-8");
        // order默认为LOWEST_PRECEDENCE
        resolver.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return resolver;
    }

    @Bean
    public UserAccValidator userAccValidator() {
        return new UserAccValidator();
    }

    /**
     * - 此方法为@Value注解工作 加此bean的原因是因为shiro使用了动态代理之类的东西
     * 导致静态的注入Value失效
     * 目前的作用解决了 配置LifecycleBeanPostProcessor后 就算其bean配置为static注入
     * 依然解决不了@Value无法获取到值 但加此bean后解决
     *
     * @author quentin
     * @date 2022/11/8
     */
    /*@Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer placeholderConfigurer = new PropertySourcesPlaceholderConfigurer();

//        此方法配置的property 可以使用@Value获取
//        Properties properties = new Properties();
//        properties.setProperty("user.lastname", "mike");
//        placeholderConfigurer.setProperties(properties);
//        bring in some property values from a Properties file
//        等同于@PropertySource("classpath:jdbc.properties")
        Resource resource = new ClassPathResource("jdbc.properties");
        placeholderConfigurer.setLocation(resource);
        return placeholderConfigurer;
    }*/
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
        // 对应了war包下路径
        configurer.setTemplateLoaderPath("/WEB-INF/freemarker");
        return configurer;
    }

    /*
    也可以使用FreeMarkerViewResolver
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(freemarkerViewResolver());
        registry.enableContentNegotiation(new MappingJackson2JsonView());
        // 其中只配置了suffix(.ftl) 也会检查FreeMarkerConfigurer的bean，也就是相当于配置了prefix
        // 但其中的order无法设置
//        registry.freeMarker();
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer pathMatchConfigurer) {

    }

    /**
     * 默认会通过Header中Accept进行匹配
     * 如果使用URL-based content type resolution,需要使用次方法配置, 例如后缀匹配
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        // 配置接收前端请求的数据类型
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

    /**
     * 此配置除了静态文件的映射配置 还有加密文件的解析配置，例如js进行md5加解密
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // registry.addResourceHandler("/resources/**").addResourceLocations("/public", "classpath:/static/")
        // 上面代码的意思是让resources开始的资源在public或者classpath下的static寻找
        // 可以作为一种映射 把 resources的资源映射到public
        // 让url从static开始的资源从static下目录寻找
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/")
                .setCacheControl(CacheControl.maxAge(Duration.ofDays(31)));
        // 因为浏览器默认会请求favicon.ico所以直接映射 后面会对页面进行favicon设置
        registry.addResourceHandler("/favicon.ico")
                .addResourceLocations("classpath:/static/favicon.ico")
                .setCacheControl(CacheControl.maxAge(Duration.ofDays(31)));
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
    public Validator getValidator() {
        return new UserAccValidator();
    }

    @Override
    public MessageCodesResolver getMessageCodesResolver() {
//        DefaultMessageCodesResolver codesResolver = new DefaultMessageCodesResolver();
        return null;
    }
}