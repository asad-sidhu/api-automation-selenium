package Pages;

import Config.BaseTest;
import Config.ReadPropertyFile;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;


public class BasePage extends BaseTest {


    public static String getToken() {
        ReadPropertyFile config = ConfigFactory.create(ReadPropertyFile.class);

        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();

        String apiRequestScript = "var xhr = new XMLHttpRequest();\n" +
                "xhr.open('GET', '"+config.weather_web_url()+"/home/get_session_data', false);\n" +
                "xhr.send();\n" +
                "return xhr.responseText;";

        String apiResponse = (String) jsExecutor.executeScript(apiRequestScript);

        JsonObject jsonObject = JsonParser.parseString(apiResponse).getAsJsonObject();
        String token = jsonObject.get("token").getAsString();
    //    System.out.println("Authentication Token: \n" + token);

        return token;
    }

}
