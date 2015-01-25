package org.smarttechie.dnslookup;

import org.springframework.stereotype.Component;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.MXRecord;
import org.xbill.DNS.Record;
import org.xbill.DNS.TextParseException;
import org.xbill.DNS.Type;

@Component
public class DnsLookupBean {

	public String getDNS(String url) {
		MXRecord mx = null;
		String dnsName = null;
		Record[] records;
		try {
			records = new Lookup("walmart.com", Type.NS).run();
			for (int i = 0; i < records.length;) {
				mx = (MXRecord) records[i];
				dnsName = mx.getTarget().toString();
				break;
			}
		} catch (TextParseException e) {
			e.printStackTrace();
		}
		return dnsName;
	}
}
