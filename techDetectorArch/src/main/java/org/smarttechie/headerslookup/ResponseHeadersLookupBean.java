package org.smarttechie.headerslookup;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class ResponseHeadersLookupBean {

	/**
	 * This method will find the meta tags used by the website for SEO purpose
	 * 
	 * @param scriptElements
	 *            - list of script tags
	 * @return
	 */
	public Map<String, String> getHeadersInfo(Map<String, String> headersMap) {
		Map<String, String> attributes = new HashMap<String, String>();
		if (StringUtils.isNotBlank(headersMap.get("X-ATG-Version"))) {
			attributes.put("Powered By", "ATG");
		}

		if (StringUtils.isNotBlank(headersMap.get("X-Powered-By"))) {
			attributes.put("Powered By Server", headersMap.get("X-Powered-By"));
		}

		if (StringUtils.isNotBlank(headersMap.get("Server"))) {
			attributes.put("Web Server", headersMap.get("Server"));
		}
		
		if (StringUtils.isNotBlank(headersMap.get("X-Cache"))) {
			if (headersMap.get("X-Cache").contains("cloudfront")) {
				attributes.put("CDN", "CLOUDFRONT");
			}
		}
		
		if (StringUtils.isNotBlank(headersMap.get("Via"))) {
			if (headersMap.get("Via").contains("varnish")) {
				attributes.put("VARNISH", "YES");
			}
		}
		
		if (StringUtils.isNotBlank(headersMap.get("x-amz-id-1")) || StringUtils.isNotBlank(headersMap.get("x-amz-id-2"))) {
			attributes.put("HOSTED ON AMAZON WEB STORE", "YES");
		}

		if (StringUtils.isNotBlank(headersMap.get("X-UA-Compatible"))) {
			attributes.put("X-UA-Compatible", "YES");
		}

		if (StringUtils.isNotBlank(headersMap.get("x-xss-protection"))) {
			attributes.put("X-XSS-Protection", "YES");
		}
		
		if (StringUtils.isNotBlank(headersMap.get("X-AspNet-Version"))) {
			attributes.put("Powered By .Net", "YES");
		}
		return attributes;

	}
	
}
