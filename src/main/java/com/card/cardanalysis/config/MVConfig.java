package com.card.cardanalysis.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


@Configuration
public class MVConfig extends WebMvcConfigurerAdapter {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }

    /**
     * 设置 mvc解析 json
     *
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter messageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig jsonConfig = new FastJsonConfig();
        jsonConfig.setCharset(Charset.forName("UTF-8"));
        jsonConfig.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect);
        messageConverter.setFastJsonConfig(jsonConfig);
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        messageConverter.setSupportedMediaTypes(mediaTypes);
        converters.add(messageConverter);
        super.extendMessageConverters(converters);
    }
}
