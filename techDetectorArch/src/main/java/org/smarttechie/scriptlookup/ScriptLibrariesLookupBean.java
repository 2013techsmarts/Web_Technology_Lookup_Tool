package org.smarttechie.scriptlookup;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

/**
 * This class will extract the information from the java script libraries and tells us which JS libraries the site is using.
 * @author SmartTechie
 *
 */
@Component
public class ScriptLibrariesLookupBean {

	private static final Log logger = LogFactory.getLog(ScriptLibrariesLookupBean.class);

	/**
	 * This method will find the appropriate script libraries used by the website
	 * @param scriptElements - list of script tags
	 * @return
	 */
	public Map<String, String> getScriptLibraries(Elements scriptElements) {
		Map<String, String> attributes = new HashMap<String, String>();

		logger.info("Extrcating the JS libraries infornmation");
		for (Element element : scriptElements) {
			if (element.toString().contains("jquery")) {
				attributes.put("jquery", "YES");
			}

			if (element.toString().contains("jquery-ui")) {
				attributes.put("jquery-ui", "YES");
			}

			if (element.toString().contains("mybuys")) {
				attributes.put("MyBuys", "YES");
			}

			if (element.toString().contains("google-analytics")) {
				attributes.put("google-analytics", "YES");
			}

			if (element.toString().contains("monetate")) {
				attributes.put("monetate", "YES");
			}

			if (element.toString().toLowerCase().contains("hoverintent")) {
				attributes.put("hoverintent", "YES");
			}

			if (element.toString().toLowerCase().contains("optimizely")) {
				attributes.put("optimizely", "YES");
			}

			if (element.toString().toLowerCase().contains("backbone")) {
				attributes.put("Backbone JS", "YES");
			}

			if (element.toString().toLowerCase().contains("less.js") || element.toString().toLowerCase().contains("less.min.js")) {
				attributes.put("less", "YES");
			}

			if (element.toString().toLowerCase().contains("angular")) {
				attributes.put("Angular JS", "YES");
			}

			if (element.toString().toLowerCase().contains("leaflet")) {
				attributes.put("leaflet", "leaflet");
			}

			if (element.toString().toLowerCase().contains("hammer")) {
				attributes.put("Hammer JS", "YES");
			}

			if (element.toString().toLowerCase().contains("jquery.jfontsizer")) {
				attributes.put("jFontSizer", "YES");
			}

			if (element.toString().toLowerCase().contains("jquery.jNice")) {
				attributes.put("jNice", "YES");
			}

			if (element.toString().toLowerCase().contains("addthis")) {
				attributes.put("AddThis Plugin", "YES");
			}

			if (element.toString().toLowerCase().contains("jquery.jcarousel")) {
				attributes.put("jCarousel", "YES");
			}

			if (element.toString().toLowerCase().contains("jquery.easing")) {
				attributes.put("jQuery EasIng", "YES");
			}

			if (element.toString().toLowerCase().contains("jquery.easing")) {
				attributes.put("jQuery EasIng", "YES");
			}

			if (element.toString().toLowerCase().contains("jquery.timers")) {
				attributes.put("jQuery.timers", "YES");
			}

			if (element.toString().toLowerCase().contains("modernizer.js")) {
				attributes.put("modernizer.js", "YES");
			}
		}

		return attributes;

	}
}
