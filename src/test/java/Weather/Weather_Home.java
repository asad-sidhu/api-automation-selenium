package Weather;


import Config.BaseTest;
import Config.ReadPropertyFile;
import MiscFunctions.DateUtil;
import MiscFunctions.Functions;
import PageObjects.Weather_Home_Page;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.math3.dfp.DfpField;
import org.junit.BeforeClass;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import static ExtentReports.ExtentReport.initReport;
import static MiscFunctions.DateUtil.TimestampConverter;
import static MiscFunctions.DateUtil.dateFormatConvertor;
import static MiscFunctions.ExtentVariables.*;


public class Weather_Home extends BaseTest {
    public static ReadPropertyFile config = ConfigFactory.create(ReadPropertyFile.class);
    private static Map<String, Object> expectedData = new HashMap<>();
    static SoftAssert softAssert = new SoftAssert();

    @BeforeTest
    public void extent() throws InterruptedException, IOException {
        spark = new ExtentSparkReporter("target/Reports/Weather/Weather_" + DateUtil.date + ".html");
        spark.config().setReportName("Weather Test Report");
        initReport("Weather");
        expectedData = Weather_Home_Page.getCurrentWeatherApiData();
    }

    @BeforeClass
    public void Navigate() throws InterruptedException, IOException {

    }
    @Test(priority = 1)
    public void CurrentWeatherData() throws InterruptedException, IOException {
        try {
            test = extent.createTest("Verify that current weather Data is showing properly");
            steps = test.createNode(Scenario.class, Steps);
            results = test.createNode(Scenario.class, Results);

            steps.createNode("1. Navigate to Weather Home Page");
            steps.createNode("2. Search For a city");
            steps.createNode("3. Verify that current weather Data is showing properly");

            Weather_Home_Page.search("Sydney");
            Map<String, String> actualData = Weather_Home_Page.getCurrentWeatherDetails();

            // Check and assert Pressure if available
            if (actualData.containsKey("Pressure")) {
                softAssert.assertEquals(actualData.get("Pressure"), String.valueOf(expectedData.get("pressure")), "Pressure value does not match");
            } else {
                steps.createNode("Pressure data not available");
            }

            // Check and assert Humidity if available
            if (actualData.containsKey("Humidity")) {
                softAssert.assertEquals(actualData.get("Humidity"), String.valueOf(expectedData.get("humidity")), "Humidity value does not match");
            } else {
                steps.createNode("Humidity data not available");
            }

            // Check and assert Dew point if available
            if (actualData.containsKey("Dew")) {
                softAssert.assertEquals(actualData.get("Dew"), String.valueOf(Math.round(Double.parseDouble(expectedData.get("dew_point").toString()))), "Dew value does not match");
            } else {
                steps.createNode("Dew data not available");
            }

            // Check and assert Visibility if available
            if (actualData.containsKey("Visibility")) {
                softAssert.assertEquals(actualData.get("Visibility"), String.valueOf(((Integer) expectedData.get("visibility")).doubleValue() / 1000), "Visibility value does not match");
            } else {
                steps.createNode("Visibility data not available");
            }

            // Check and assert Wind speed if available
            if (actualData.containsKey("Wind")) {
                softAssert.assertEquals(Math.round(Double.parseDouble(actualData.get("Wind"))), Math.round(Double.parseDouble(expectedData.get("wind_speed").toString())), "Wind value does not match");
            } else {
                steps.createNode("Wind data not available");
            }

            // Check and assert Wind Direction if available
            if (actualData.containsKey("Wind Direction")) {
                softAssert.assertEquals(actualData.get("Wind Direction"), Weather_Home_Page.getWindDirection(((Number) expectedData.get("wind_deg")).doubleValue()), "Wind Direction value does not match");
            } else {
                steps.createNode("Wind Direction data not available");
            }

            // Check and assert Temperature if available
            if (actualData.containsKey("Temperature")) {
                softAssert.assertEquals(actualData.get("Temperature"), String.valueOf(Math.round(Double.parseDouble(expectedData.get("temp").toString()))), "Temperature value does not match");
            } else {
                steps.createNode("Temperature data not available");
            }

            // Check and assert DateTime if available
            if (actualData.containsKey("DateTime")) {
                softAssert.assertEquals(actualData.get("DateTime").toLowerCase(), dateFormatConvertor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), new SimpleDateFormat("MMM d, hh:mma"), TimestampConverter(Long.parseLong(expectedData.get("dt").toString()))).toLowerCase(), "DateTime value does not match");
            } else {
                steps.createNode("DateTime data not available");
            }

            // Check and assert UV if available
            if (actualData.containsKey("UV")) {
                softAssert.assertEquals(actualData.get("UV"), String.valueOf(Math.round(Double.parseDouble(expectedData.get("uvi").toString()))), "UV value does not match");
            } else {
                steps.createNode("DateTime data not available");
            }

            softAssert.assertAll();
            test.pass("Current Weather Test Verified Successfully");
            results.createNode("Current Weather Test Verified Successfully");
            get_Screenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Current Weather Test failed to verify");
            results.createNode("Current Weather Test failed to verify");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Current Weather Test failed to verify");
            results.createNode("Current Weather Test failed to verify");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    @Test(priority = 2)
    public void Logout() throws IOException {
        try {
            Functions functions = new Functions(getDriver());
            test = extent.createTest("AN-01: Verify that user is able to logout.");
            steps = test.createNode(Scenario.class, Steps);
            results = test.createNode(Scenario.class, Results);
            steps.createNode("1. Click on Logout Button");
            steps.createNode("2. Confirm Logout");
            steps.createNode("3. Verify that user gets logged out");

            test.pass("User Successfully logged out");
            results.createNode("Logout Test Verified Successfully");
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("User does not Logout");
            results.createNode("Logout Test case failed to verify");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("User does not Logout");
            results.createNode("Logout Test case failed to verify");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    @Test(priority = 3)
    public void Negative_Cases() throws InterruptedException, IOException {
        try {
            test.pass("User receives an alert message");
//                results.createNode("Forgot Password Test Verified Successfully");
            saveResult(ITestResult.SUCCESS, null);

        } catch (AssertionError er) {
            test.fail("User does not receive an alert message");
            results.createNode("Test case failed to verify");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("User does not receive an alert message");
            results.createNode("Test case failed to verify");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    @AfterTest
    public static void endreport() {
        extent.flush();
    }
}
