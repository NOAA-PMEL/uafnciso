package gov.noaa.eds.service;

import org.junit.Test;
import static org.junit.Assert.*;


/**
 * ThreddsDatasetTreeServiceTest
 * Date: Feb 22, 2010
 * Time: 12:27:53 PM
 *
 * @author dneufeld
 */

public class ThreddsDatasetTreeServiceTest  {

    private static final String _hdayCatalog = "catalog=http://thredds1.pfeg.noaa.gov/thredds/Hfradar/aggreghfradarCS" +
            "/u25h/catalog.xml&amp;dataset=satellite/CS/u25h/hday";
    private static final String _oceanwatchCatalog = "http://oceanwatch.pfeg.noaa.gov/thredds/catalog.xml";
    private static final String _ourThreddsServer = "http://oncilla.ngdc.noaa.gov/thredds/catalog.xml";
    private static final String _oceansitesThreddsServer = "http://dods.ndbc.noaa.gov/thredds/dodsC/data/oceansites/catalog.xml";
    private static final String _esrlThreddsServer = "http://www.esrl.noaa.gov/psd/thredds/dodsC/Datasets/catalog.xml";
    private static final String _uafThreddsServer = "http://ferret.pmel.noaa.gov/geoide/geoIDECleanCatalog.xml";
    private static final String _ioosHawaiiThreddsServer = "http://oos.soest.hawaii.edu/thredds/catalog.xml"; //big
    private static final String _ngdcThreddsServer = "http://www.ngdc.noaa.gov/thredds/catalog.xml"; //big
    
    //IOOS 
    private static final String _ioosMacoora1ThreddsServer = "http://aqua.smast.umassd.edu:8080/thredds/natl_catalog.xml";
    private static final String _ioosMacoora2ThreddsServer = "http://nopp.dms.uconn.edu:8080/thredds/catalog.xml";
    private static final String _ioosMacoora3ThreddsServer = "http://tashtego.marine.rutgers.edu:8080/thredds/catalog.xml";  //big
    private static final String _ioosMacoora4ThreddsServer = "http://colossus.dl.stevens-tech.edu:8080/thredds/catalog.xml";
    private static final String _caraThreddsServer = "http://dm1.caricoos.org/thredds/catalog.xml"; 
    private static final String _gcosRomsThreddsServer = "http://csanady.tamu.edu:8080/thredds/catalog.xml";

    //OpenDAP GCOS
    private static final String _gcosOpenDAPServer = "http://ilikai.soest.hawaii.edu/uhslc/fast/";
    

    @Test
    public void testThreddsTreeService(){    	
    	int numSample = 10;
    	int depth = 6;
        DatasetTreeService treeService = new DatasetTreeService();
        treeService.setServer(_oceansitesThreddsServer, depth, numSample, true, true, "UnidataDD2MI.xsl");
        treeService.generateTree();
    }    

}
