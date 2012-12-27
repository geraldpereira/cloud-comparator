package fr.byob.cloud.comparator.rest;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.google.inject.Singleton;
import com.sun.jersey.core.util.Base64;

import fr.byob.cloud.comparator.commons.guice.InjectLogger;

@Singleton
public class AuthenticationFilter implements Filter {

	@InjectLogger
	private Logger log;

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException,
	ServletException {

		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		log.debug("AuthenticationFilter.doFilter() " + httpRequest.getHeader("test"));

		final Enumeration<String> headerNames = httpRequest.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			final String headerName =  headerNames.nextElement();
			log.debug("Header Name: " + headerName);
			final String headerValue = httpRequest.getHeader(headerName);
			log.debug("Header Value: " + headerValue);
		}

		final String authHeader = httpRequest.getHeader("authorization");
		log.debug("Authorization header: " + authHeader);
		if (authHeader != null){
			final String encodedValue = authHeader.split(" ")[1];
			log.debug("Base64-encoded Authorization Value: " + encodedValue);
			final String decodedValue = Base64.base64Decode(encodedValue);
			log.debug("Base64-decoded Authorization Value: " + decodedValue);
		}

		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {
	}

}
