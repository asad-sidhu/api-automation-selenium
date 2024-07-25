package MiscFunctions;

import Config.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static Config.BaseTest.getDriver;

public class Functions extends BaseTest {
    public static WebDriver ldriver;


    public Functions(WebDriver rdriver) {
        ldriver = rdriver;
        PageFactory.initElements(rdriver, this);
    }

    public static void waitUntilVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitUntilInvisible(By locator) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(35));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static void waitElementClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void click(By locator) {
        waitElementClickable(locator);
        scrollUntilVisible(locator);
        getDriver().findElement(locator).click();
    }

    public static void type(By locator, String text) {
        waitUntilVisible(locator);
        scrollUntilVisible(locator);
        getDriver().findElement(locator).sendKeys(text);
    }

    public static void scrollUntilVisible(By locator) {
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("arguments[0].scrollIntoView()", getDriver().findElement(locator));
    }

    public static void hoverByCoordinates(WebElement locator, int x, int y) {
        Actions action = new Actions(getDriver());
        action.moveToElement(locator, x, y).perform();
    }

    public static void hover(By locator) {
        WebElement hover = getDriver().findElement(locator);
        Actions action = new Actions(getDriver());
        action.moveToElement(hover).perform();
    }

    public static String getText(By locator) {
        return getDriver().findElement(locator).getText();
    }


    public static void sortListByMapKey(List<Map<String, Object>> list, String key) {
        list.sort((map1, map2) -> {
            String value1 = String.valueOf(map1.get(key));
            String value2 = String.valueOf(map2.get(key));
            return value1.compareTo(value2);
        });
    }

}
