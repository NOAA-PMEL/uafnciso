package gov.noaa.eds.threddsutilities.util;

import java.util.List;

import org.jdom.Element;
import org.junit.Test;

import thredds.server.metadata.bean.Extent;
import thredds.server.metadata.util.ElementNameComparator;
import thredds.server.metadata.util.NCMLModifier;
import thredds.server.metadata.util.ThreddsExtentUtil;
import thredds.server.metadata.util.XMLUtil;
import static org.junit.Assert.*;

public class NCMLModifierTest {

    public void ncmlIntegrationTest() throws Exception {
    	String urlStr = "http://sdf.ndbc.noaa.gov:8080/thredds/dodsC/hfradar_usegc_1km";    	
    	Extent ext = ThreddsExtentUtil.getExtent(urlStr);  
    	
    	String fileName = "C:/Workspace/isoConverter/thredds/dodsC/hfradar_usegc_1km_NCML.xml";
    	XMLUtil xmlUtil = new XMLUtil(fileName);
    	List<Element> childElems = xmlUtil.elemFinder("//ncml:attribute", "ncml", "http://www.unidata.ucar.edu/namespaces/netcdf/ncml-2.2");
    	
    	NCMLModifier ncmlMod = new NCMLModifier();
    	
    	List<Element> list = xmlUtil.elemFinder("//ncml:netcdf", "ncml", "http://www.unidata.ucar.edu/namespaces/netcdf/ncml-2.2");
    	Element rootElem = list.get(0);
    	ncmlMod.addCFMetadata(ext, rootElem);

    	//Test extent
    	xmlUtil.sortElements(rootElem, new ElementNameComparator());
    	xmlUtil.write(fileName.substring(0,fileName.length()-4) + "_enh.xml");

    	
    	assertTrue(childElems.size()>0);
    }
}
