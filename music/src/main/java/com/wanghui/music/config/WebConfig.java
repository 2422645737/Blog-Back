package com.wanghui.music.config;

import com.wanghui.music.Constance;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    // 静态资源展示
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // PS：注意文件路径最后的斜杠（文件分隔符），如果缺少了，就不能够正确的映射到相应的目录
        registry.addResourceHandler( "/" +  Constance.prefix + "/**").addResourceLocations(Constance.basePath);
    }
}
