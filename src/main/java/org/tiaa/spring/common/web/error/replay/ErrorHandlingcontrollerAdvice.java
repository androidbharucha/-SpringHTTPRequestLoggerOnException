package org.tiaa.spring.common.web.error.replay;

import java.io.BufferedReader;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorHandlingcontrollerAdvice 
  extends ResponseEntityExceptionHandler {

	//https://www.baeldung.com/spring-reading-httpservletrequest-multiple-times
	//https://www.oodlestechnologies.com/blogs/How-to-create-duplicate-object-of-httpServletRequest-object/
	//https://www.baeldung.com/spring-http-logging
	
	
	IExceptionHandlerService adobeXyzServicehandler;
	
	
    @ExceptionHandler(value 
      = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleConflict(
      RuntimeException ex, WebRequest request) {
    	
    	try {
    		RequestWrapper wrappedRequest = HTTPRequestTheadLocalHandler.get();
    		BufferedReader reader = wrappedRequest.getReader();
    		String body = reader.lines().collect(Collectors.joining(System.lineSeparator()));
			System.out.println("request body printed in controller advice "+body);
			adobeXyzServicehandler.handleException(body);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return handleExceptionInternal(ex, "err", 
          new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}