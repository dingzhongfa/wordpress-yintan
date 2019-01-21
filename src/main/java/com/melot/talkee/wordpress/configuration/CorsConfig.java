package com.melot.talkee.wordpress.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
public class CorsConfig {

    @Value("${spring.http.header.allowedOrigin:*}")
    private String allowedOrgin;
    @Value("${spring.http.header.allowedHeader:*}")
    private String allowedHeader;
    @Value("${spring.http.header.allowedMethod:*}")
    private String allowedMethod;
    @Value("${spring.http.header.allowCredential:true}")
    private boolean allowCredential;

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin(allowedOrgin); // 1允许任何域名使用
        corsConfiguration.addAllowedHeader(allowedHeader); // 2允许任何头
        corsConfiguration.addAllowedMethod(allowedMethod); // 3允许任何方法（post、get等）
        corsConfiguration.setAllowCredentials(allowCredential);
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig()); // 4
        return new CorsFilter(source);
    }

}
