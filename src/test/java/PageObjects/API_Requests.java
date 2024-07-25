package PageObjects;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.util.Map;

import static Pages.BasePage.getToken;

public class API_Requests {
    static WebDriver ldriver;
    SoftAssert softAssert = new SoftAssert();

    public API_Requests(WebDriver rdriver) {
        ldriver = rdriver;
        PageFactory.initElements(rdriver, this);
    }

    private RequestSpecification getRequestSpecification() {
        RequestSpecification request = RestAssured.given();
//        request.header("Authorization", "Bearer " + getToken());
        request.header("Content-Type", "application/json");
//        request.header("Cookie", "my_csrf_cookie_name=fDioBsrRIAxvT0sVbHHY8uyWoQug3oEv; _ga=GA1.1.219464796.1704450966; csrftoken=5WPcCsnQmjLvtPJ75iM2v9Nrr9fyQygJ; _ga_NJMV12DR2B=GS1.1.1708889510.2.1.1708890313.0.0.0; _ga_P6LKN1MW7C=GS1.1.1708936873.6.1.1708937061.0.0.0; _ga_LQKSZYXJ8T=GS1.1.1708964734.7.0.1708964734.0.0.0; sessionid=v3nkmzy9mb2zw8jqu5lilpznyjahckv4");
        return request;
    }

    public JsonPath get_request(String api_url, Map<String ,String> params) throws InterruptedException {
        RequestSpecification request = getRequestSpecification();
        request.params(params);
        Response response = request.get(api_url);
        return processResponse(response);
    }

    public JsonPath post_request(String api_url, String requestBody) throws InterruptedException {
        RequestSpecification request = getRequestSpecification();
        request.body(requestBody);
        Response response = request.post(api_url);
        return processResponse(response);
    }

    public JsonPath put_request(String api_url, String requestBody) throws InterruptedException {
        RequestSpecification request = getRequestSpecification();
        request.body(requestBody);
        Response response = request.put(api_url);
        return processResponse(response);
    }

    public JsonPath delete_request(String api_url) throws InterruptedException {
        RequestSpecification request = getRequestSpecification();
        Response response = request.delete(api_url);
        return processResponse(response);
    }

    private JsonPath processResponse(Response response) throws InterruptedException {
        int code = response.getStatusCode();
        Thread.sleep(2000);
        softAssert.assertEquals(code, 200, "Did not return 200 status code");

        String response_data = response.getBody().asString();

        return response.jsonPath();
    }

}
