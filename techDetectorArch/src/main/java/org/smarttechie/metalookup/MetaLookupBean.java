package org.smarttechie.metalookup;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

/**
 * This class has methods to extract the web technologies from the webpage meta tags
 * @author SmartTechie
 *
 */
@Component
public class MetaLookupBean {
	
	private static final Log logger = LogFactory.getLog(MetaLookupBean.class);


	/**
	 * This method will find the meta tags used by the website for SEO purpose
	 * 
	 * @param metaElements
	 *            - list of meta tags
	 * @return
	 */
	public Map<String, String> getMetaInfo(Elements metaElements) {
		
		logger.info("Extracting the web technologies/processes information from the meta tags");
		
		Map<String, String> attributes = new HashMap<String, String>();
		if (metaElements != null && !metaElements.isEmpty()) {
			String metaVal = null;
			String metaPropertyVal = null;
			String metaHttpEqui = null;
			for (Element element : metaElements) {
				metaVal = element.attr("name");
				metaPropertyVal = element.attr("property");
				metaHttpEqui = element.attr("http-equiv");
				if (StringUtils.isNotBlank(metaVal)) {

					if (metaVal.contains("keywords")) {
						attributes.put("Meta-Keywords", "Yes");

					} else if (metaVal.contains("description")) {
						attributes.put("Meta-Description", "Yes");
					} else if (metaVal.contains("viewport")) {
						attributes.put("Meta-viewport", "Yes");
					} else if (metaVal.contains("robots")) {
						attributes.put("Meta-robots", "Yes");
					} else if (metaVal.contains("google-site-verification")) {
						attributes.put("Google Site Verification", "DONE");
					} else if (metaVal.contains("revisit-after")) {
						attributes.put("Meta Revisit After", "YES");
					} else if (metaVal.contains("apple-itunes-app")) {
						attributes.put("Smart App Banner", "YES");
					} else if (metaVal.contains("MobileOptimized")) {
						attributes.put("MobileOptimized", "YES");
					} else if (metaVal.contains("MobileOptimized")) {
						attributes.put("HandheldFriendly", "YES");
					} else if (metaVal.contains("generator")) {
						if (element.attr("content").contains("WordPress.com")) {
							attributes.put("WordPress Hosting", "YES");
						}
					} else if (metaVal.contains("p:domain_verify")) {
						attributes.put("Pinterest Domain Verify", "DONE");
					} else if (metaVal.contains("msvalidate.01")) {
						attributes.put("Bing Webmaster Verification", "DONE");
					}

				}

				if (StringUtils.isNotBlank(metaHttpEqui)) {
					if (metaHttpEqui.contains("X-UA-Compatible")) {
						attributes.put("X-UA-Compatible", "Yes");

					}
				}

				if (element.hasAttr("charset")) {
					attributes.put("charset", element.attr("charset"));
				}

				if (StringUtils.isNotBlank(metaPropertyVal)) {
					if (metaPropertyVal.contains("og:title")
							|| metaPropertyVal.contains("og:url")
							|| metaPropertyVal.contains("og:image")
							|| metaPropertyVal.contains("og:description")
							|| metaPropertyVal.contains("og:site_name")) {
						attributes.put("Open Graph Protocol", "Yes");
					}

					if ( metaPropertyVal.contains("fb:admins")) {
						attributes.put("Facebook Page Administration", "Yes");
					}

					// to detect the twitter tags

					if (metaPropertyVal.contains("twitter:card")
							|| metaPropertyVal.contains("twitter:site")
							|| metaPropertyVal.contains("twitter:title")
							|| metaPropertyVal.contains("twitter:description")
							|| metaPropertyVal.contains("twitter:creator")
							|| metaPropertyVal.contains("twitter:image")) {
						attributes.put("Facebook", "Yes");
					}
				}
			}
		}

		return attributes;

	}
}
