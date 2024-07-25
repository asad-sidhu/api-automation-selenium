package MiscFunctions;

import Enums.ConfigProperties;
import Utilities.PropertyUtils;


public class FrameworkConstants {

    private FrameworkConstants() {
    }

    private static final int EXPLICITWAIT = 10;
    private static final String MAINPATH = System.getProperty("user.dir") + "/src/main/java";
    private static final String TESTPATH = System.getProperty("user.dir") + "/src/test/resources";
    private static final String RESOURCESPATH = System.getProperty("user.dir") + "/src/test/resources";

    public static String CONFIGPROPERTIESFILE = MAINPATH + "/Config/config.properties";


    public static String OrganizationBulkSiteUploadInvalidFile = RESOURCESPATH + "/BulkSiteUpload/BulkSiteTemplateInvalid.xlsx";
    public static String OrganizationBulkSiteUploadValidFile = RESOURCESPATH + "/BulkSiteUpload/BulkSiteTemplate.xlsx";
    public static String OrganizationBulkSiteUploadRegionFile = RESOURCESPATH + "/BulkSiteUpload/SiteRegionTemplate.xlsx";
    public static String OrganizationBulkSiteUploadSubRegionFile = RESOURCESPATH + "/BulkSiteUpload/SiteSubRegionTemplate.xlsx";

    public static String OrganizationBulkSiteUploadComplexFile = RESOURCESPATH + "/BulkSiteUpload/SiteComplexTemplate.xlsx";
    public static String OrganizationBulkSiteUploadFarmFile = RESOURCESPATH + "/BulkSiteUpload/SiteFarmTemplate.xlsx";
    public static String OrganizationBulkSiteUploadHatcheryFile = RESOURCESPATH + "/BulkSiteUpload/SiteHatcheryTemplate.xlsx";
    public static String OrganizationBulkSiteUploadHouseFile = RESOURCESPATH + "/BulkSiteUpload/SiteHouseTemplate.xlsx";
    public static String AnceraLogo = RESOURCESPATH + "/Ancera_Logo/ancera_logo.jpg";


    public static String DataTemplateIdentificationColumnCheckFile = RESOURCESPATH + "/Excel/IndentificationFieldCheck.xlsx";
    public static String NormalIngestionTemplateUpload = RESOURCESPATH + "/Excel/MetaData RunMode1.xlsx";
    public static String CSMDataTemplateUpload = RESOURCESPATH + "/Excel/SampleMetadata_Mobile.xlsx";
    public static String CSMSiteIDsFile = RESOURCESPATH + "/Excel/CSMOrgSiteIDs.xlsx";
    public static String DataSecurityTemplateUpload = RESOURCESPATH + "/Excel/MetaData DataSecurity.xlsx";


    private static final String CONFIGFILEPATH = MAINPATH + "/Config/config.properties";
    private static final String JSONCONFIGFILEPATH = RESOURCESPATH + "/config/config.json";
    //private static final String EXCELPATH = RESOURCESPATH+"/excel/testdata.xlsx";
    private static final String EXCELPATH = MAINPATH + "/Utilities/testdata.xlsx";
    private static final String RUNMANGERSHEET = "RUNMANAGER";
    private static final String ITERATIONDATASHEET = "DATA";
    private static final String EXTENTREPORTFOLDERPATH = System.getProperty("user.dir") + "/extent-test-output/";
    private static String extentReportFilePath = "";


    public static String RUNMODE_COL = "Runmode";
    public static String DATA_SHEET = "TestData";

    public static String SUITE_SHEET = "Suite";
    public static String SUITENAME_COL = "SuiteName";

    public static String TESTCASE_SHEET = "TestCases";
    public static String TESTCASE_COL = "TestCases";

    public static String RUNMODE_YES = "Y";
    public static String RUNMODE_NO = "N";

    public static String SUITE_XL_PATH = System.getProperty("user.dir") + "/src/test/resources/testdata/Suite.xlsx";
    public static String SUITE1_XL_PATH = System.getProperty("user.dir") + "/src/test/resources/testdata/master.xlsx";

    public static String getExtentReportFilePath() {
        if (extentReportFilePath.isEmpty()) {
            extentReportFilePath = createReportPath();
        }
        return extentReportFilePath;
    }

    private static String createReportPath() {
        if (PropertyUtils.get(ConfigProperties.OVERRIDEREPORTS).equalsIgnoreCase("no")) {
            return EXTENTREPORTFOLDERPATH + System.currentTimeMillis() + "/index.html";
        } else {
            return EXTENTREPORTFOLDERPATH + "/index.html";
        }
    }


    public static String getConfigFilePath() {
        return CONFIGPROPERTIESFILE;
    }


    public static String getExcelpath() {
        return EXCELPATH;
    }

    public static String getJsonconfigfilepath() {
        return JSONCONFIGFILEPATH;
    }

    public static int getExplicitwait() {
        return EXPLICITWAIT;
    }

    public static String getRunmangerDatasheet() {
        return RUNMANGERSHEET;
    }

    public static String getIterationDatasheet() {
        return ITERATIONDATASHEET;
    }


}

