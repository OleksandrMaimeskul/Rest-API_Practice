package ApiAutomationPractice;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GameOfThrones {
    @Test
    public void validationOfProductGameOfThrones(){
        RestAssured.baseURI="https://thronesapi.com/";
        RestAssured.basePath="/api/v2/Characters/10";
        Response response = RestAssured.given()
                .header("Accept","application/json")
                .when()
                .get()
                .then().log().body().statusCode(200).extract().response();
        Map<String,Object> deserializeResponse = response.as(new TypeRef<Map<String, Object>>() {});
        Assert.assertEquals(deserializeResponse.get("id"),10);
        Assert.assertEquals(deserializeResponse.get("firstName"),"Cateyln");
        Assert.assertEquals(deserializeResponse.get("lastName"),"Stark");
        Assert.assertEquals(deserializeResponse.get("fullName"),"Catelyn Stark");
        Assert.assertEquals(deserializeResponse.get("title"),"Lady of Winterfell");

    }

    @Test
    public void validateContinents(){
        RestAssured.baseURI="https://thronesapi.com/";
        RestAssured.basePath="/api/v2/Continents";
        Response response = RestAssured.given().accept("application/json")
                .when().get().then().log().body().extract().response();
        List<Map<String,Object>> deserializedResponse = response.as(new TypeRef<List<Map<String, Object>>>() {});
        List<String> expectedContinentns = Arrays.asList("Westeros","Essos","Sothoryos","Ulthos");
        for (int i = 0; i < expectedContinentns.size() ; i++) {
            System.out.println(deserializedResponse.get(i).get("name"));
            Assert.assertEquals(deserializedResponse.get(i).get("name"),expectedContinentns.get(i));
        }
    }
}
