package com.bajaj.fixeddeposit.filter;

import java.text.Normalizer;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;

public class XSSUtils {
	private static final Logger LOG = Logger.getLogger(XSSUtils.class);

private XSSUtils()
{

}

public static String stripXSS(String value) {
	String cleanValue = null;
	if (value != null) {
		cleanValue = Normalizer.normalize(value, Normalizer.Form.NFD);

		// Avoid null characters
		cleanValue = cleanValue.replaceAll("\0", "");
		
		// Avoid anything between script tags
		Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
		cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");
 
		// Avoid anything in a src='...' type of expression
		scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

		scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");
		
		// Remove any lonesome </script> tag
		scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
		cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

		// Remove any lonesome <script ...> tag
		scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

		// Avoid eval(...) expressions
		scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");
		
		// Avoid expression(...) expressions
		scriptPattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");
		
		// Avoid javascript:... expressions
		scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
		cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");
		
		// Avoid vbscript:... expressions
		scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
		cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");
		
		// Avoid onload= expressions
		scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");
	}
	LOG.info("cleanValue"+cleanValue);
	return cleanValue; 
  } 
}