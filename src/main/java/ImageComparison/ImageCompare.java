package ImageComparison;

import Config.BaseTest;
import MiscFunctions.Constants;
//import Misc.DB_Config_DB;
import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;

//import static MiscFunctions.DateUtil.dateYYYYMM;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.Methods.*;
import static com.github.romankh3.image.comparison.ImageComparisonUtil.readImageFromResources;
import static com.github.romankh3.image.comparison.model.ImageComparisonState.MISMATCH;


public class ImageCompare extends BaseTest {

    @BeforeTest(groups = {"smoke", "regression"})
    public void extent() throws InterruptedException, IOException {
//        spark = new ExtentSparkReporter("target/Reports/Administration_Organization_Engagement_CRUD" + DateUtil.date + ".html");
//        spark.config().setReportName("Organization Engagement Management Test Report");
//        DB_Config_DB.test();
    }

    @BeforeClass(groups = {"smoke", "regression"})
    public void Login() throws InterruptedException, IOException {
        getDriver().get("https://reporting-qa.ancera.com/");
        getDriver().findElement(By.id("email")).sendKeys("junaid.sidhu@tenx.ai");
    }
    public String get_Screenshot(String filename) throws IOException {
        BaseTest drive = new BaseTest();
        TakesScreenshot ts = (TakesScreenshot) drive.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = Constants.ReportFilePath + "/ScreenShots/ImageComparison/" + filename + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return "." + Constants.ReportScreenshotPath + "/ImageComparison/" + filename + ".png";
    }
    public String get_Snapshot(WebElement element, String filename) throws IOException {

        // Get the location and size of the element
        int x = element.getLocation().getX();
        int y = element.getLocation().getY();
        int width = element.getSize().getWidth();
        int height = element.getSize().getHeight();

        // Take a screenshot of the entire page
        File screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

        // Crop the screenshot to include only the element
        BufferedImage elementScreenshot = ImageIO.read(screenshot)
                .getSubimage(x, y, width, height);

        // Save the cropped screenshot to a file
        ImageIO.write(elementScreenshot, "png", screenshot);

        // Specify the location to save the screenshot
        File screenshotLocation = new File(Constants.ReportFilePath + "/ScreenShots/ImageComparison/"+ filename);

        // Copy the screenshot to the specified location
        FileUtils.copyFile(screenshot, screenshotLocation);
        return "." + Constants.ReportScreenshotPath + "/ImageComparison/" + filename;
    }

    @Test(enabled = true, priority = 1, groups = {"smoke", "regression"})
    public void CreateOrganization() throws InterruptedException, IOException {
        //given
        BufferedImage expectedResultImage = readImageFromResources(Constants.ReportFilePath+"/ScreenShots/ImageComparison/result.png");

        File file = new File(Constants.ReportFilePath+"/ScreenShots/ImageComparison/result2.png");

        WebElement element = getDriver().findElement(By.id("loginform"));

        get_Snapshot(element,"actual.png");

//        get_Screenshot("expected");
        getDriver().get("https://reporting-qa.ancera.com/");
        getDriver().findElement(By.id("email")).sendKeys("junaid.alam@tenx.ai");

//        waitElementInvisible(BasePage.loader);
//        waitElementVisible(By.id("header"));
//        Thread.sleep(5000);
//        get_Snapshot(element,"actual.png");
        //when
        ImageComparison imageComparison = new ImageComparison(Constants.ReportFilePath+"/ScreenShots/ImageComparison/expected.png", Constants.ReportFilePath+"/ScreenShots/ImageComparison/actual.png");
        ImageComparisonResult imageComparisonResult = imageComparison.compareImages().writeResultTo(file);

        //then
        Assert.assertNotNull(imageComparison.getActual());
        Assert.assertNotNull(imageComparison.getExpected());
        Assert.assertEquals(MISMATCH, imageComparisonResult.getImageComparisonState());
        assertImagesEqual(expectedResultImage, imageComparisonResult.getResult());
    }

    private void assertImagesEqual(BufferedImage expected, BufferedImage actual) {
        if (expected.getWidth() != actual.getWidth() || expected.getHeight() != actual.getHeight()) {
            System.out.println(expected.getWidth());
            System.out.println(expected.getHeight());
            System.out.println(actual.getWidth());
            System.out.println(actual.getHeight());
//            fail("Images have different dimensions");
        }

        int width = expected.getWidth();
        int height = expected.getHeight();

//        for (int y = 0; y < height; y++) {
//            for (int x = 0; x < width; x++) {
//                if (expected.getRGB(x, y) != actual.getRGB(x, y)) {
////                    fail("Images are different, found a different pixel at: x = " + x + ", y = " + y);
//                }
//            }
//        }
    }




    @AfterTest(groups = {"smoke", "regression"})
    public static void endreport() {
        extent.flush();
    }

}

