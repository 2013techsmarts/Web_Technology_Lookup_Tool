package org.smarttechie.common;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 * This class has the methods to extrcat the information of couple of the web technologies from the website homepage.
 * @author SmartTechie
 *
 */
@Component
public class GeneralTechLookupBean {
	
	private static final Log logger = LogFactory.getLog(GeneralTechLookupBean.class);


	/**
	 * This method will find the meta tags used by the website for SEO purpose
	 * 
	 * @param scriptElements
	 *            - list of script tags
	 * @return
	 */
	public Map<String, String> getGeneralTechInfo(String docStr) {
		Map<String, String> attributes = new HashMap<String, String>();
		
		logger.info("Extrcating the web technologies info from the website homepage");
		if (docStr.contains("liveperson")) {
			attributes.put("liveperson", "YES");
		}

		if (docStr.contains("typography")) {
			attributes.put("typography", "YES");
		}

		if (docStr.toLowerCase().contains("mozu")) {
			attributes.put("Powered By", "MOZU");
		}

		if (docStr.toLowerCase().contains("tealium")) {
			attributes.put("tealium", "YES");
		}

		if (docStr.toLowerCase().contains("batcache")) {
			attributes.put("BatCache", "YES");
		}
		
		if (docStr.toLowerCase().contains("adroll")) {
			attributes.put("AdRoll", "YES");
		}
		
		if (docStr.toLowerCase().contains("intershop")) {
			attributes.put("INTERSHOP", "YES");
		}

		return attributes;

	}
	
}
