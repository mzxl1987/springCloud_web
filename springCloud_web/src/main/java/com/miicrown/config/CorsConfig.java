package com.miicrown.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

	private CorsConfiguration buildConfig() {  
        CorsConfiguration corsConfiguration = new CorsConfiguration();  
        corsConfiguration.addAllowedOrigin("*"); // 1允许任何域名使用
        corsConfiguration.addAllowedHeader("*"); // 2允许任何头
        corsConfiguration.addAllowedMethod("*"); // 3允许任何方法（post、get等） 
        return corsConfiguration;  
    }  
  
    @Bean  
    public CorsFilter corsFilter() {  
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();  
        source.registerCorsConfiguration("/**", buildConfig()); // 4  
        return new CorsFilter(source);  
    } 
    
//    final static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CorsFilter.class);  
//    
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {  
//        HttpServletResponse response = (HttpServletResponse) res;  
//        response.setHeader("Access-Control-Allow-Origin", "*");  
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");  
//        response.setHeader("Access-Control-Max-Age", "3600");  
//        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");  
//        System.out.println("*********************************过滤器被使用**************************");  
//        chain.doFilter(req, res);  
//    }  
//    public void init(FilterConfig filterConfig) {}  
//    public void destroy() {}  
	
}
