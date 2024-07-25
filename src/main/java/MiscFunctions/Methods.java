package MiscFunctions;

import Config.BaseTest;
import MiscFunctions.Constants;
import Pages.BasePage;
import com.aventstack.extentreports.Status;
import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;


import static Config.BaseTest.getDriver;
import static MiscFunctions.Constants.ImageComparisonScreenshotPath;
import static MiscFunctions.ExtentVariables.test;

import static com.github.romankh3.image.comparison.model.ImageComparisonState.MATCH;

public class Methods {

    static BaseTest drive = new BaseTest();
    public WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(100, 1));
    static SoftAssert softAssert = new SoftAssert();
    static Robot robot;

    public static void pressEnter(){
        try {
            robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isDisplayed(By locator) throws InterruptedException {
        Thread.sleep(1000);
        BaseTest drive = new BaseTest();
        boolean isDisplayed = drive.getDriver().findElement(locator).isDisplayed();
        System.out.println(isDisplayed);
        ExtentVariables.test.log(Status.INFO, "Verifying " + locator + " is Displayed ");
        return isDisplayed;
    }

    public static void waitForPageToLoad() {
        ((JavascriptExecutor) getDriver()).executeScript("return document.readyState").equals("complete");
    }

    public static void click(By locator) {
            waitElementClickable(locator);
            scroll(getDriver().findElement(locator));
            BaseTest drive = new BaseTest();
            getDriver().findElements(locator).get(0).click();
            ExtentVariables.test.log(Status.INFO, "Clicking on " + locator);
    }

    public static void clickByJSE(By locator) {
        waitElementClickable(locator);
        scroll(getDriver().findElement(locator));
        BaseTest drive = new BaseTest();
        WebElement elementToClick = getDriver().findElement(locator);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
        jsExecutor.executeScript("arguments[0].click();", elementToClick);
        ExtentVariables.test.log(Status.INFO, "Clicking on " + locator);
    }





    public static void clickIE(By locator) {
        waitElementClickable(locator);
        scroll(getDriver().findElement(locator));
        getDriver().findElement(locator).click();
        ExtentVariables.test.log(Status.INFO, "Clicking on " + locator);
    }

    public static void type(By locator, String text) {
        waitElementClickable(locator);
        BaseTest drive = new BaseTest();
        getDriver().findElement(locator).sendKeys(text);
        ExtentVariables.test.log(Status.INFO, "Typing " + text + " in " + locator);
    }

    public static void typeIE(By locator, String text) {
        waitElementClickable(locator);
        BaseTest drive = new BaseTest();
        getDriver().findElement(locator).sendKeys(text);
        ExtentVariables.test.log(Status.INFO, "Typing " + text + " in " + locator);
    }

    public static void enterKey(By locator) {
        waitElementVisible(locator);
        BaseTest drive = new BaseTest();
        getDriver().findElement(locator).sendKeys(Keys.ENTER);
        ExtentVariables.test.log(Status.INFO, "Press enter key in " + locator);
    }

    public static String getText(By locator) {
        try {
            return getDriver().findElement(locator).getText();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public static String getAttribute(By locator) {
        BaseTest drive = new BaseTest();
        return getDriver().findElement(locator).getAttribute("value");
    }

    public static void clear(By locator) {
        waitElementVisible(locator);
        BaseTest drive = new BaseTest();
        getDriver().findElement(locator).clear();
    }

    public static int size(By locator) {
        BaseTest drive = new BaseTest();
        int elementSize = getDriver().findElements(locator).size();
        return elementSize;
    }

    public static void waitElementVisible(By locator) {
        Methods methods = new Methods();
        methods.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitElementInvisible(String locator) {
        Methods methods = new Methods();
        methods.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator)));
    }

    public static void waitElementClickable(By locator) {
        Methods methods = new Methods();
        methods.wait.until(ExpectedConditions.elementToBeClickable(locator));
    }


    public static void scrollBy(By locator) {
        BaseTest drive = new BaseTest();
        WebElement scroll = getDriver().findElement(locator);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", scroll);
    }


    public static void scroll(WebElement locator) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", locator);
    }

    public static void scrollToTopOfPage() {
        BaseTest drive = new BaseTest();
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0, 0)");
    }

    public static void hover(By locator) {
        BaseTest drive = new BaseTest();
        WebElement hover = getDriver().findElement(locator);
        Actions action = new Actions(getDriver());
        action.moveToElement(hover).perform();
    }

    public static void getScreenshot() throws IOException {
        BaseTest base = new BaseTest();
        test.addScreenCaptureFromPath(base.get_Screenshot());
    }

    public static String getJsonValue(WebDriver driver, String jsonString, String key) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String script = String.format("return JSON.parse(arguments[0]).%s;", key);
        return (String) jsExecutor.executeScript(script, jsonString);
    }

    public static void unLockFilter(String tablename) {
        if (size(By.cssSelector("#" + tablename + " #save-filters.d-none")) != 0) {
            click(By.cssSelector("#" + tablename + " #remove-filters"));
            click(By.cssSelector("#" + tablename + " #reset-all-filters"));
        }
    }

    public static void imageComparison(String expectedImage, String actualImage, String resultImage, String screenName) {
        String SnapshotFolderPath = ImageComparisonScreenshotPath+screenName+"/";
        File file = new File(SnapshotFolderPath+resultImage);
        ImageComparison imageComparison = new ImageComparison(SnapshotFolderPath+expectedImage, SnapshotFolderPath+actualImage);
        imageComparison.setRectangleLineWidth(5);
        ImageComparisonResult imageComparisonResult = imageComparison.compareImages().writeResultTo(file);

        softAssert.assertNotNull(imageComparison.getActual());
        softAssert.assertNotNull(imageComparison.getExpected());
        softAssert.assertEquals(imageComparisonResult.getImageComparisonState(),MATCH,"Discrepencies Found in "+actualImage);
    }
    public static void assertions(){
        softAssert.assertAll();
    }

    public static void get_Snapshot(WebElement element, String filename, String screenName) throws IOException {

        int x = element.getLocation().getX();
        int y = element.getLocation().getY();
        int width = element.getSize().getWidth();
        int height = element.getSize().getHeight();

        File screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

        BufferedImage elementScreenshot = ImageIO.read(screenshot)
                .getSubimage(x, y, width, height);

        ImageIO.write(elementScreenshot, "png", screenshot);

        File screenshotLocation = new File(ImageComparisonScreenshotPath+screenName+"/"+ filename);

        FileUtils.copyFile(screenshot, screenshotLocation);
//        return "." + Constants.ReportScreenshotPath + "/ImageComparison/"+screenName+"/" + filename;
    }



}
