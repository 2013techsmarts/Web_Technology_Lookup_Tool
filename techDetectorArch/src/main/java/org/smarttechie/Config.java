package org.smarttechie;

import java.util.List;

import org.smarttechie.dnslookup.DnsLookupBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mobile.device.DeviceResolverHandlerInterceptor;
import org.springframework.mobile.device.DeviceWebArgumentResolver;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ServletWebArgumentResolverAdapter;

@Controller
@ComponentScan("org.smarttechie")
@EnableWebMvc
public class Config extends WebMvcConfigurerAdapter {

	@Bean
	public DeviceResolverHandlerInterceptor deviceResolverHandlerInterceptor() {
		return new DeviceResolverHandlerInterceptor();
	}
	
	  @Bean
	    public RestTemplate restTemplate() {
	        RestTemplate restTemplate = new RestTemplate();
	        return restTemplate;
	    }
	  
     @Bean	 
	 public DnsLookupBean getDnsLookupBean() {
		 DnsLookupBean dnsLookupBean = new DnsLookupBean();
		 return dnsLookupBean;
	 }

    
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(deviceResolverHandlerInterceptor());
	}

	@Override
	public void addArgumentResolvers(
			List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add( new ServletWebArgumentResolverAdapter(
				new DeviceWebArgumentResolver()));
	}
}
