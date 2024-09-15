package ApiAutomationPractice;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeworkWithJsonPath {
    @Test
    public void validationAirportInformation() {
        RestAssured.baseURI = "https://airportgap.dev-tester.com";
        RestAssured.basePath = "/api/airports";

        Response response = RestAssured.given().contentType("application/json")
                .when().get().then().log().body().statusCode(200).extract().response();
        JsonPath deserializationResponse = response.jsonPath();
        Assert.assertTrue(deserializationResponse.getList("data.id").contains("HGU"));
        Assert.assertTrue(deserializationResponse.getList("data.attributes.name").contains("Mount Hagen Kagamuga Airport"));
        Assert.assertTrue(deserializationResponse.getList("data.attributes.city").contains("Mount Hagen"));
        Assert.assertTrue(deserializationResponse.getList("data.attributes.country").contains("Papua New Guinea"));
    }
}
