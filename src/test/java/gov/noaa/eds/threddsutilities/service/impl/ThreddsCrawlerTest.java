package gov.noaa.eds.threddsutilities.service.impl;

import thredds.server.metadata.exception.ThreddsUtilitiesException;
import gov.noaa.eds.threddsutilities.service.iface.ICatalogCrawler;
import gov.noaa.eds.threddsutilities.service.impl.CatalogCrawlerImpl;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.junit.Test;

import thredds.server.metadata.bean.MetadataContainer;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ThreddsCrawlerTest
 * Date: Feb 22, 2010
 * Time: 12:27:53 PM
 *
 * @author rbaker
 */

public class ThreddsCrawlerTest  {

    private static final String _hdayCatalog = "catalog=http://thredds1.pfeg.noaa.gov/thredds/Hfradar/aggreghfradarCS" +
            "/u25h/catalog.xml&amp;dataset=satellite/CS/u25h/hday";
    private static final String _oceanwatchCatalog = "http://oceanwatch.pfeg.noaa.gov/thredds/catalog.xml";
    private static final String _ourThreddsServer = "http://oncilla.ngdc.noaa.gov/thredds/catalog.xml";
    private static final String _oceansitesThreddsServer = "http://dods.ndbc.noaa.gov/thredds/dodsC/data/oceansites/catalog.xml";
    private static final String _esrlThreddsServer = "http://www.esrl.noaa.gov/psd/thredds/dodsC/Datasets/catalog.xml";
    private static final String _uafThreddsServer = "http://ferret.pmel.noaa.gov/geoide/geoIDECleanCatalog.xml";
    private static final String _ioosHawaiiThreddsServer = "http://oos.soest.hawaii.edu/thredds/catalog.xml";
    private static final String _ioosNDBCThreddsServer = "http://sdf.ndbc.noaa.gov:8080/thredds/catalog.xml";
    private static final String _ioosMacoora1ThreddsServer = "http://csanady.tamu.edu:8080/thredds/catalog.xml";
    private final String _ngdcThreddsServer = "http://www.ngdc.noaa.gov/thredds/catalog.xml";

    //OpenDAP GCOS
    private static final String _gcosOpenDAPServer = "http://ilikai.soest.hawaii.edu/uhslc/fast/";
    

    @Test
    public void testThreddsCrawl(){
        ICatalogCrawler crawler = new CatalogCrawlerImpl();
        File output = new File("output.out");
        FileWriter fw = null;
        try {
            fw = new FileWriter(output);
            Vector<MetadataContainer> mdcs = new Vector<MetadataContainer>();
            crawler.crawlThredds(_oceansitesThreddsServer, 3, 10, mdcs);
            System.out.println("urls.size()="+mdcs.size());
            assertTrue("MSCs is empty",!mdcs.isEmpty());
            for(MetadataContainer mdc:mdcs){
            	String str = mdc.getOpenDapUrl();
                System.err.println(str+"\n");
                fw.write(str+"\n");
            }
            mdcs = null;
        } catch (ThreddsUtilitiesException e) {
            e.printStackTrace();
            fail(e.getLocalizedMessage());
        } catch (IOException e) {
            e.printStackTrace();
            fail(e.getLocalizedMessage());
        } finally {
            try {
                if(fw != null){
                    fw.flush();
                    fw.close();
                }
            } catch (IOException e) {
            e.printStackTrace();
            fail(e.getLocalizedMessage());
            }
        }
        crawler = null;

    }
    
    //@Test
    //This is a one off for now...
    public void crawlOpenDAP(String url, int depth, Vector<String> urls) throws ThreddsUtilitiesException {
        
		HttpClient client = new HttpClient();

		// Create a method instance.
		GetMethod method = new GetMethod(url);
		// Provide custom retry handler is necessary
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(2, false));
		int depthCnt = 0;
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
			while (m.find()) {
					  String href = m.group(1);
					  String ncUrl = null;
					  if (href.indexOf(".nc")>0) {
						  ncUrl = dodsUrl + href;
						  urls.add(ncUrl);
						  depthCnt++;
						  System.out.println(ncUrl);
					  }
					  if (depthCnt>=depth) break;					
			}
		} catch (HttpException he) {
			throw new ThreddsUtilitiesException(he.getMessage());
		} catch (IOException ioe) {
			throw new ThreddsUtilitiesException(ioe.getMessage());
		} finally {
			// Release the connection.
			method.releaseConnection();
		}

	}
    
    // fix me
    /* public void findWMS(){
    	OGCLocatorService ogcLocSvc = new OGCLocatorService();
    	ogcLocSvc.setServer(_uafThreddsServer, 10000, 100);
    	ogcLocSvc.generateCatalog();
    }*/
}
