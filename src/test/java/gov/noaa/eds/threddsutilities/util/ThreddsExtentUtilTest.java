package gov.noaa.eds.threddsutilities.util;

import org.junit.Test;

import thredds.server.metadata.bean.Extent;
import thredds.server.metadata.util.ThreddsExtentUtil;
import ucar.nc2.dataset.NetcdfDataset;

import static org.junit.Assert.*;

public class ThreddsExtentUtilTest {

	public void getExtentTest() {
    	String urlStr = "http://oos.soest.hawaii.edu/thredds/dodsC/pacioos/ncom/global/NCOM_Global_Ocean_Model_best.ncd";
		//String urlStr = "http://localhost:8080/thredds/dodsC/test/ncom_glb_sfc_2011050100.nc";
    	try {
    	    NetcdfDataset ncd = NetcdfDataset.openDataset(urlStr); 
		    Extent ext = ThreddsExtentUtil.getExtent(ncd);
		    System.out.println(ext._minLat + "," + ext._maxLat);
		    assertTrue(ext._minLat==-78.5);
    	} catch (Exception e) {
    		System.err.println(e);
    	}
	}
    
}
