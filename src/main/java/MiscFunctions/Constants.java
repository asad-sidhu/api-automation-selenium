package MiscFunctions;

import Config.ReadPropertyFile;
import org.aeonbits.owner.ConfigFactory;

public class Constants {
    public static ReadPropertyFile config = ConfigFactory.create(ReadPropertyFile.class);

    public static String WEATHER_HOME_URL = config.weather_web_url();
    public static String WEATHER_API_URL = config.weather_api_url();



    public static String ReportFilePath = "./extent-test-output";
    public static String ReportScreenshotPath = "/ScreenShots/ScreenShot";
    public static String ImageComparisonScreenshotPath = ReportFilePath+"/ScreenShots/ImageComparison/";


}
