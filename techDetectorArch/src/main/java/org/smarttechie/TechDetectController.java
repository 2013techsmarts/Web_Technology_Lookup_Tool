package org.smarttechie;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.smarttechie.common.GeneralTechLookupBean;
import org.smarttechie.dnslookup.DnsLookupBean;
import org.smarttechie.headerslookup.ResponseHeadersLookupBean;
import org.smarttechie.linklookup.LinkLookupBean;
import org.smarttechie.metalookup.MetaLookupBean;
import org.smarttechie.scriptlookup.ScriptLibrariesLookupBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TechDetectController {

	private static final Log logger = LogFactory.getLog(TechDetectController.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private DnsLookupBean dnsLookupBean;


	@Autowired
	private ScriptLibrariesLookupBean scriptLibrariesLookupBean;

	@Autowired
	private MetaLookupBean metaLookupBean;

	@Autowired
	private LinkLookupBean linkLookupBean;

	@Autowired
	private ResponseHeadersLookupBean responseHeadersLookupBean;

	@Autowired
	private GeneralTechLookupBean generalTechLookupBean;


	public RestTemplate getRestTemplate() {
		return restTemplate;
	}


	public DnsLookupBean getDnsLookupBean() {
		return dnsLookupBean;
	}

	public ScriptLibrariesLookupBean getScriptLibrariesLookupBean() {
		return scriptLibrariesLookupBean;
	}

	public MetaLookupBean getMetaLookupBean() {
		return metaLookupBean;
	}

	public LinkLookupBean getLinkLookupBean() {
		return linkLookupBean;
	}

	public ResponseHeadersLookupBean getResponseHeadersLookupBean() {
		return responseHeadersLookupBean;
	}

	public GeneralTechLookupBean getGeneralTechLookupBean() {
		return generalTechLookupBean;
	}
	@RequestMapping("/techDetect")
	public ModelAndView webTechnologiesDetector(HttpServletRequest request, Model model, Device device) {


		Map<String, String> attributes = new HashMap<String, String>();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index.jsp");

		String url = request.getParameter("url");
		
		try {
			Connection connection = Jsoup.connect(url);
			connection.followRedirects(true);
			Response response = connection.execute();
			Document document = response.parse();
			String docStr = document.toString();
			Map<String, String> generalInfo = getGeneralTechLookupBean().getGeneralTechInfo(docStr);

			//get the script elements
			Elements scriptElements = document.getElementsByTag("script");

			Map<String, String> scriptLibraries = getScriptLibrariesLookupBean().getScriptLibraries(scriptElements);

			//get the meta elements
			Elements metaElements = document.getElementsByTag("meta");
			Map<String, String> metaInfos = getMetaLookupBean().getMetaInfo(metaElements);
			//get the link elements
			Elements linkElements = document.getElementsByTag("link");
			Map<String, String> linkInfos = getLinkLookupBean().getLinkInfo(linkElements);

			Map<String, String> headersMap = response.headers();
			Map<String, String> responseHeadersInfo = getResponseHeadersLookupBean().getHeadersInfo(headersMap);


			//dns lookup
			/*String dnsProvider = getDnsLookupBean().getDNS(url);
			attributes.put("DNS Provider", dnsProvider);*/
			attributes.putAll(responseHeadersInfo);
			attributes.putAll(scriptLibraries);
			attributes.putAll(linkInfos);
			attributes.putAll(metaInfos);
			attributes.putAll(generalInfo);
			modelAndView.addObject("attributes", attributes);
			return modelAndView;
		} catch(IOException ioException) {
			logger.error("Unable to fetch the data from " + url, ioException);
			modelAndView.addObject("errorMsg", "Sorry, We are unble to indentify the web technlogies used by " + url);
			return modelAndView;
		} catch (Exception e) {
			logger.error("Unable to fetch the data from " + url, e);
			modelAndView.addObject("errorMsg", "Sorry, We are unble to indentify the web technlogies used by " + url);
			return modelAndView;
		}
	}
}
