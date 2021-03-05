package org.tiaa.spring.common.web.error.replay;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
@WebFilter(filterName = "requestCacheFilter", urlPatterns = "/*")
public class RequestCacheFilter implements Filter {
  
  @Override
  public void init (FilterConfig filterConfig) throws ServletException {
  }
  
  @Override
  public void doFilter (ServletRequest request, ServletResponse response,
                        FilterChain chain)
            throws IOException, ServletException {
      System.out.println("-- In MyFilter --");
      HttpServletRequest req = (HttpServletRequest) request;
      
      try {
			
			RequestWrapper wrappedRequest = new RequestWrapper(req);
	    	//request = wrappedRequest;
	    	BufferedReader reader = wrappedRequest.getReader();
			String body = reader.lines().collect(Collectors.joining(System.lineSeparator()));
			System.out.println("request body printed in interceptor "+body);
			HTTPRequestTheadLocalHandler.set(wrappedRequest);
			chain.doFilter(wrappedRequest, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
   
  }
  
  @Override
  public void destroy () {
  }
}