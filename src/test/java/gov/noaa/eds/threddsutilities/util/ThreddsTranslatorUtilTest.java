package gov.noaa.eds.threddsutilities.util;

import static org.junit.Assert.assertTrue;
import thredds.server.metadata.exception.ThreddsUtilitiesException;
import thredds.server.metadata.util.ThreddsTranslatorUtil;

import org.junit.Test;

public class ThreddsTranslatorUtilTest {

    private static final String xsltMetadataAssessmentUrl = "http://www.ngdc.noaa.gov/metadata/published/xsl/UnidataDDCount-HTML.xsl";
    
    @Test
    public void translateTest() throws ThreddsUtilitiesException {
	
    	ThreddsTranslatorUtil.transform(xsltMetadataAssessmentUrl, "C:\\Workspace\\isoConverter\\psd\\thredds\\dodsC\\Datasets\\20thC_Rean\\Dailies\\monolevel\\air.sig995.1908_NCML.xml", "C:\\Workspace\\isoConverter\\psd\\thredds\\dodsC\\Datasets\\20thC_Rean\\Dailies\\monolevel\\air_sig995_1908_REPORT.html");
    	assertTrue(true);    	
    }
}
