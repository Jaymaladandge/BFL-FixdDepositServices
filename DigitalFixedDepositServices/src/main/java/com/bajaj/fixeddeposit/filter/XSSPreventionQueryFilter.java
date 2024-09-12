package com.bajaj.fixeddeposit.filter;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.log4j.Logger;

/**
 * Filters Http requests and removes malicious characters/strings
 * (i.e. XSS) from the Query String
 */
public class XSSPreventionQueryFilter implements Filter {
	private static final Logger LOG = Logger.getLogger(XSSPreventionQueryFilter.class);

	public class XSSRequestWrapper extends HttpServletRequestWrapper {

		
		
		
		private Map<String, String[]> sanitizedQueryString;
		public XSSRequestWrapper(HttpServletRequest request) {
			super(request);
		}
		
		//QueryString overrides
		
		@Override
		public String getParameter(String name) {
			LOG.info("Query String parameter"+name);
			String parameter = null;
			String[] vals = getParameterMap().get(name); 
			
			if (vals != null && vals.length > 0) {
				parameter = vals[0];
			}
			LOG.info("Query String parameter"+name);
			return parameter;
		}
 
		@Override
		public String[] getParameterValues(String name) {
			LOG.info("getParameterValues"+name);  
			return getParameterMap().get(name);
		}
		
		@Override 
		public Enumeration<String> getParameterNames() {
			return Collections.enumeration(getParameterMap().keySet());
		}

		@SuppressWarnings("unchecked")
		@Override
		public Map<String,String[]> getParameterMap() {
			if(sanitizedQueryString == null) {
				Map<String, String[]> res = new HashMap<String, String[]>();
				Map<String, String[]> originalQueryString = super.getParameterMap();
				if(originalQueryString!=null) {
					for (String key : (Set<String>) originalQueryString.keySet()) {
						String[] rawVals = originalQueryString.get(key);
						String[] snzVals = new String[rawVals.length];
						for (int i=0; i < rawVals.length; i++) {
							snzVals[i] = XSSUtils.stripXSS(rawVals[i]);
							LOG.info("Sanitized: " + rawVals[i] + " to " + snzVals[i]);
						}
						res.put(XSSUtils.stripXSS(key), snzVals);
					}
				} 
				sanitizedQueryString = res;
			}
			LOG.info("sanitizedQueryString"+sanitizedQueryString);
			return sanitizedQueryString;
		}  

		//TODO: Implement support for headers and cookies (override getHeaders and getCookies)
		
		/**
		 * Removes all the potentially malicious characters from a string
		 * @param value the raw string
		 * @return the sanitized string 
		 */
		
	}

	@Override
	public void destroy() {
		LOG.info("XSSPreventionFilter: destroy()");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, 
			FilterChain chain) throws IOException, ServletException {
		XSSRequestWrapper wrapper = new XSSRequestWrapper((HttpServletRequest)request);
		chain.doFilter(wrapper, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOG.info("XSSPreventionFilter: init()");
	} 
}

 