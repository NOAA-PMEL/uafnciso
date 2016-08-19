package gov.noaa.eds.service;

import gov.noaa.eds.threddsutilities.service.iface.ICatalogCrawler;
import gov.noaa.eds.threddsutilities.service.impl.CatalogCrawlerImpl;

import org.apache.log4j.Logger;
import org.junit.Test;

import thredds.server.metadata.bean.MetadataContainer;
import static org.junit.Assert.*;

import java.util.Vector;

/**
 * ThreddsDatasetTreeServiceTest
 * Date: Feb 22, 2010
 * Time: 12:27:53 PM
 *
 * @author dneufeld
 */

public class WafServiceTest  {

	private static org.slf4j.Logger _log = org.slf4j.LoggerFactory
    .getLogger(WafServiceTest.class);
    //TDSs
    private static final String _hdayCatalog = "catalog=http://thredds1.pfeg.noaa.gov/thredds/Hfradar/aggreghfradarCS" +
            "/u25h/catalog.xml&amp;dataset=satellite/CS/u25h/hday";
    private static final String _oceanwatchCatalog = "http://oceanwatch.pfeg.noaa.gov/thredds/catalog.xml";
    private static final String _ourThreddsServer = "http://oncilla.ngdc.noaa.gov/thredds/catalog.xml";
    private static final String _oceansitesThreddsServer = "http://dods.ndbc.noaa.gov/thredds/dodsC/data/oceansites/catalog.xml";
    private static final String _esrlThreddsServer = "http://www.esrl.noaa.gov/psd/thredds/dodsC/Datasets/catalog.xml";
    private static final String _uafThreddsServer = "http://ferret.pmel.noaa.gov/geoide/carbontracker.xml";
    private static final String _ioosHawaiiThreddsServer = "http://oos.soest.hawaii.edu/thredds/catalog.xml"; //big
    private static final String _ngdcThreddsServer = "http://www.ngdc.noaa.gov/thredds/catalog.xml"; //big
    
    //Rich Signell
    private static final String _suratestbedThreddsServer = "http://testbedapps.sura.org/thredds/clean.xml";
    
    // Kyle Wilcox test SOS
    private static final String _suratestbedSOSThreddsServer = "http://testbedapps.sura.org/thredds/catalog/estuarine_hypoxia/observations/cbp/catalog.xml";
    
    //IOOS for Jeff DlB
    private static final String _ioosMacoora1ThreddsServer = "http://aqua.smast.umassd.edu:8080/thredds/natl_catalog.xml";
    private static final String _ioosMacoora2ThreddsServer = "http://nopp.dms.uconn.edu:8080/thredds/catalog.xml";
    private static final String _ioosMacoora3ThreddsServer = "http://tashtego.marine.rutgers.edu:8080/thredds/catalog.xml";  //big
    private static final String _ioosMacoora4ThreddsServer = "http://colossus.dl.stevens-tech.edu:8080/thredds/catalog.xml";
    private static final String _caraThreddsServer = "http://dm1.caricoos.org/thredds/catalog.xml"; 
    private static final String _gcosRomsThreddsServer = "http://csanady.tamu.edu:8080/thredds/catalog.xml";

    //OpenDAP GCOS
    private static final String _gcosOpenDAPServer = "http://ilikai.soest.hawaii.edu/uhslc/fast/";
    
    public void fakeTest(){
    	assertTrue(true);    	
    }
    
    @Test
    public void testWafServices(){    	

        Vector<MetadataContainer> mdcs = new Vector<MetadataContainer>();
        Vector<String> ncmlFiles = new Vector<String>();
        ICatalogCrawler crawler = new CatalogCrawlerImpl();
        try {
            crawler.crawlThredds(_suratestbedSOSThreddsServer, 3, 10, mdcs);
            //crawler.crawlThredds(_ngdcThreddsServer, depth, numSample, mdcs);
            WafService.generateNcml(mdcs, ncmlFiles, "C:/temp/sos/ncml/");            
            WafService.generateNcmlRubric(ncmlFiles, "C:/temp/sos/scores/");
            WafService.generateIso(ncmlFiles, "C:/temp/sos/iso/");
            assertTrue(ncmlFiles.size()>0);
        } catch (Exception e) {
        	_log.error("Error in testWafServices: ", e);
        	System.out.println("Error in testWafServices: " + e.getMessage());
        	assertTrue(false); 
        }
    }    

}
