package ApiAutomationPractice;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class BooksApi {
    @Test
    public void getToken() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.basePath = "/auth";
        Response response = RestAssured.given().header("Content-type", "application/json")
                .header("Accept", "application/json")
                .body("{ \"username\" : \"admin\", \n" +
                        "\"password\" : \"password123\"\n" +
                        "}")
                .when().post()
                .then().log().body().extract().response();
        Map<String, Object> deserializeResponse = response.as(new TypeRef<Map<String, Object>>() {
        });
        Assert.assertNotNull(deserializeResponse.get("token"));
    }

    @Test
    public void validateBookDetails() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.basePath = "/booking/1395";
//       ;pathParams("id",1395)
        Response response = RestAssured.given().header("Accept", "application/json")
                .when().get()
                .then().log().body().extract().response();
        Map<String, Object> deserializeResponse = response.as(new TypeRef<Map<String, Object>>() {
        });
        System.out.println(deserializeResponse);
        Assert.assertEquals(deserializeResponse.get("firstname"), "Josh");
        Assert.assertEquals(deserializeResponse.get("depositpaid"), true);
        Map<String, Object> bookingDatesJson = (Map<String, Object>) deserializeResponse.get("bookingdates");
        Assert.assertEquals(bookingDatesJson.get("checkin"), "2018-01-01");

    }

    @Test
    public void validateUpdateApi() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.basePath = "/booking/1321";
        Response response = RestAssured.given().contentType("application/json").accept("application/json")
                .body("{\n" +
                        "    \"firstname\": \"Oleksandr\",\n" +
                        "    \"lastname\": \"Allen\",\n" +
                        "    \"totalprice\": 111,\n" +
                        "    \"depositpaid\": true,\n" +
                        "    \"bookingdates\": {\n" +
                        "        \"checkin\": \"2018-01-01\",\n" +
                        "        \"checkout\": \"2019-01-01\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\": \"super bla bla\"\n" +
                        "}")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .when().put().then().log().body().statusCode(200)
                .extract().response();
        Map<String, Object> deserializeResponse = response.as(new TypeRef<Map<String, Object>>() {
        });
        Assert.assertEquals(deserializeResponse.get("additionalneeds"), "super bla bla");
    }
}
