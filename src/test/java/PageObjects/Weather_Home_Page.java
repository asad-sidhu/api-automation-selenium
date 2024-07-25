package PageObjects;

import Config.ReadPropertyFile;
import MiscFunctions.Constants;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.*;
import java.util.function.BiConsumer;

import static Config.BaseTest.getDriver;
import static MiscFunctions.Methods.*;

public class Weather_Home_Page {
    static WebDriver driver;
    private static API_Requests api_requests = new API_Requests(driver);
    private static ReadPropertyFile config = ConfigFactory.create(ReadPropertyFile.class);
    private static By searchField = By.xpath("//input[@placeholder= 'Search city']");
    private static By searchBtn = By.xpath("//button[text()= 'Search']");
    private static By searchDropdownItems = By.xpath("//ul[@class= 'search-dropdown-menu']//li");
    private static By currentWeatherContainer = By.xpath("//div[@class= 'current-container mobile-padding']");
    private static By currentWeatherLocationName = By.xpath("//div[@class= 'current-container mobile-padding']//h2");
    private static By currentWeatherDateTime = By.xpath("(//div[@class= 'current-container mobile-padding']//span)[1]");
    private static By currentWeatherTemperature = By.xpath("(//div[@class= 'current-container mobile-padding']//span)[2]");
    private static By currentWeatherDescription = By.xpath("(//div[@class= 'current-container mobile-padding']//div)[4]");
    private static By currentWeatherWind = By.xpath("//div[@class= 'current-container mobile-padding']//li[*[contains(@class, 'wind-line')]]");
    private static By currentWeatherPressure = By.xpath("//div[@class= 'current-container mobile-padding']//li[*[contains(@class, 'icon-pressure')]]");
    private static By currentWeatherHumidity = By.xpath("//div[@class= 'current-container mobile-padding']//li[span[contains(text(), 'Humidity')]]");
    private static By currentWeatherUV = By.xpath("//div[@class= 'current-container mobile-padding']//li[span[contains(text(), 'UV')]]");
    private static By currentWeatherDew = By.xpath("//div[@class= 'current-container mobile-padding']//li[span[contains(text(), 'Dew point')]]");
    private static By currentWeatherVisibility = By.xpath("//div[@class= 'current-container mobile-padding']//li[span[contains(text(), 'Visibility')]]");

    public Weather_Home_Page(WebDriver driver) {
        this.driver = driver;
    }

    public static void navigate()  {
        getDriver().get(Constants.WEATHER_HOME_URL);
    }

    public static void search(String query) throws InterruptedException {
        type(searchField,query);
        waitForPageToLoad();
        pressEnter();
        Thread.sleep(2000);
        click(searchDropdownItems);
        waitForPageToLoad();
        Thread.sleep(2000);
    }

    public static Map<String, String> getCurrentWeatherDetails() {
        Map<String, String> currentWeatherDetails = new HashMap<>();

        // Helper method to safely add data to the map
        BiConsumer<String, String> safePut = (key, value) -> {
            if (value != null && !value.isEmpty()) {
                currentWeatherDetails.put(key, value);
            }
        };

        try {
            String location = getText(currentWeatherLocationName);
            safePut.accept("Location", location);

            String dateTime = getText(currentWeatherDateTime);
            safePut.accept("DateTime", dateTime);

            String temperature = getText(currentWeatherTemperature);
            if (temperature != null && temperature.contains("°C")) {
                temperature = temperature.replace("°C", "");
            }
            safePut.accept("Temperature", temperature);

            String description = getText(currentWeatherDescription);
            safePut.accept("Description", description);

            String wind = getText(currentWeatherWind);
            if (wind != null) {
                String[] windParts = wind.split("m");
                if (windParts.length > 0) {
                    safePut.accept("Wind", windParts[0].trim());
                    if (windParts.length > 1) {
                        String windDirection = windParts[1].trim().split("/s")[1].trim();
                        safePut.accept("Wind Direction", windDirection);
                    }
                }
            }

            String pressure = getText(currentWeatherPressure);
            if (pressure != null && pressure.contains("hPa")) {
                pressure = pressure.replace("hPa", "");
            }
            safePut.accept("Pressure", pressure);

            String humidity = getText(currentWeatherHumidity);
            if (humidity != null && humidity.contains(":")) {
                humidity = humidity.split(":")[1].trim().replace("%", "");
            }
            safePut.accept("Humidity", humidity);

            String uv = getText(currentWeatherUV);
            if (uv != null && uv.contains(":")) {
                uv = uv.split(":")[1].trim();
            }
            safePut.accept("UV", uv);

            String dew = getText(currentWeatherDew);
            if (dew != null && dew.contains(":")) {
                dew = dew.split(":")[1].trim().replace("°C", "");
            }
            safePut.accept("Dew", dew);

            String visibility = getText(currentWeatherVisibility);
            if (visibility != null && visibility.contains(":")) {
                visibility = visibility.split(":")[1].trim().replace("km", "");
            }
            safePut.accept("Visibility", visibility);

        } catch (Exception e) {
            e.printStackTrace(); // Log the exception or handle it as needed
        }

        return currentWeatherDetails;
    }
    public static Map<String,Object> getCurrentWeatherApiData() throws InterruptedException {
        Map<String,Object> currentWeatherDetails = new HashMap<>();
        Map<String,String> params = new HashMap<>();
        params.put("lat","-33.8679");
        params.put("lon","151.2073");
        params.put("appid","439d4b804bc8187953eb36d2a8c26a02");
        params.put("units","metric");
        Map<String,Map<String ,Object>> apiResponse = api_requests.get_request(Constants.WEATHER_API_URL,params).getMap("$");
        currentWeatherDetails = apiResponse.get("current");
        return currentWeatherDetails;
    }

    public static String getWindDirection(double degrees) {
        // Normalize degrees to be within 0-360 range
        degrees = degrees % 360;
        if (degrees < 0) {
            degrees += 360;
        }

        // Determine the compass direction
        if (degrees >= 348.75 || degrees < 11.25) {
            return "N";
        } else if (degrees >= 11.25 && degrees < 33.75) {
            return "NNE";
        } else if (degrees >= 33.75 && degrees < 56.25) {
            return "NE";
        } else if (degrees >= 56.25 && degrees < 78.75) {
            return "ENE";
        } else if (degrees >= 78.75 && degrees < 101.25) {
            return "E";
        } else if (degrees >= 101.25 && degrees < 123.75) {
            return "ESE";
        } else if (degrees >= 123.75 && degrees < 146.25) {
            return "SE";
        } else if (degrees >= 146.25 && degrees < 168.75) {
            return "SSE";
        } else if (degrees >= 168.75 && degrees < 191.25) {
            return "S";
        } else if (degrees >= 191.25 && degrees < 213.75) {
            return "SSW";
        } else if (degrees >= 213.75 && degrees < 236.25) {
            return "SW";
        } else if (degrees >= 236.25 && degrees < 258.75) {
            return "WSW";
        } else if (degrees >= 258.75 && degrees < 281.25) {
            return "W";
        } else if (degrees >= 281.25 && degrees < 303.75) {
            return "WNW";
        } else if (degrees >= 303.75 && degrees < 326.25) {
            return "NW";
        } else if (degrees >= 326.25 && degrees < 348.75) {
            return "NNW";
        } else {
            return "Unknown"; // Shouldn't reach here
        }
    }




}
