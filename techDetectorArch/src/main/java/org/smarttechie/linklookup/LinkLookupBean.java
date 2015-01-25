package org.smarttechie.linklookup;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

/**
 * This class has methods to extract the web technologies from the webpage link tags
 * @author SmartTechie
 *
 */
@Component
public class LinkLookupBean {
	
	private static final Log logger = LogFactory.getLog(LinkLookupBean.class);

	/**
	 * This method will find the link tags used by the website and detect the web technologies used by the webpage
	 * 
	 * @param linkElements
	 *            - list of link tags
	 * @return
	 */
	public Map<String, String> getLinkInfo(Elements linkElements) {
		
		logger.info("Extracting the link tags and the web technologies used by the webaite");
		Map<String, String> attributes = new HashMap<String, String>();
		if (linkElements != null && !linkElements.isEmpty()) {
			String linkVal = null;
			String linkHref = null;
			String linkType = null;
			for (Element element : linkElements) {
				linkVal = element.attr("rel");
				linkHref = element.attr("href");
				linkType = element.attr("type");
				if (StringUtils.isNotBlank(linkVal)) {
					if (linkVal.contains("icon")) {
						attributes.put("Favorite Icon", "YES");
					} 
					
					if (linkVal.contains("canonical")) {
						attributes.put("Canonical Content Tag", "YES");
					}
				}
				
				if (StringUtils.isNotBlank(linkHref)) { 
					if (linkHref.contains("jquery.jscrollpane")) {
						attributes.put("jScrollPane", "YES");
					} 
					
					if (linkHref.contains("jquery.nouislider")) {
						attributes.put("noUiSlider", "YES");
					} 
					
					if (linkHref.contains("jquery.bxslider")) {
						attributes.put("BxSlider", "YES");
					} 
					
					if (linkHref.contains("jquery.bxslider")) {
						attributes.put("BxSlider", "YES");
					} 
				}
				
				if (StringUtils.isNotBlank(linkType)) { 
					if (linkType.contains("application/opensearchdescription+xml")) {
						attributes.put("OpenSearch", "YES");
					} 
					
					
				}
			}
		}

		return attributes;

	}
}
