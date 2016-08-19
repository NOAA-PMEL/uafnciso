package gov.noaa.eds.threddsutilities.util;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import org.junit.Test;

import static org.junit.Assert.*;

public class XMLUtilTest {
   
	@Test
    public void htmlReadTest() {
    	String url = "http://ilikai.soest.hawaii.edu/uhslc/fast/";    	
		HttpClient client = new HttpClient();

		// Create a method instance.
		GetMethod method = new GetMethod(url);
		// Provide custom retry handler is necessary
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(2, false));

		try {
			// Execute the method.
			int statusCode = client.executeMethod(method);

			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
			}

			// Read the response body.
			String responseBody = method.getResponseBodyAsString();
			//System.out.println(responseBody);
			String expr = "<td><a href=\""
	              + "([^\"]+)";      // first piece of data goes up to quote

			Pattern patt = Pattern.compile(expr,
			Pattern.DOTALL | Pattern.UNIX_LINES);
			String dodsUrl = "dods://ilikai.soest.hawaii.edu/cgi-bin/nph-dods/fast/";
			Matcher m = patt.matcher(responseBody);
			List al = new ArrayList<String>();
			while (m.find()) {
					  String href = m.group(1);
					  String ncUrl = null;
					  if (href.indexOf(".nc")>0) {
						  ncUrl = dodsUrl + href;
						  al.add(ncUrl);
						  System.out.println(ncUrl);
					  }
					 
			}
			assertTrue(al.size()>0);
		} catch (HttpException he) {
			he.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			// Release the connection.
			method.releaseConnection();
		} 	
    }
 


}
