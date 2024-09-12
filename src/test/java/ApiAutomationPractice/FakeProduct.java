package ApiAutomationPractice;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class FakeProduct {

    @Test
    public void validateSingleProductInformation() {
        RestAssured.baseURI = "https://fakestoreapi.com/";
        RestAssured.basePath = "/products/1";
        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .when()
                .get()
                .then().log().body().statusCode(200).extract().response();
        Map<String,Object> deserializeResponse = response.as(new TypeRef<Map<String, Object>>() {});
        System.out.println(deserializeResponse);
        Assert.assertEquals(deserializeResponse.get("id"),1);
        Assert.assertEquals((deserializeResponse.get("title")),"Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops");
        Assert.assertEquals((deserializeResponse.get("price")),109.95);
        Assert.assertEquals((deserializeResponse.get("description")),"Your perfect pack for everyday use and walks in the forest. " +
                "Stash your laptop (up to 15 inches) in the padded sleeve, your everyday");
        Assert.assertEquals((deserializeResponse.get("category")),"men's clothing");
        Assert.assertEquals((deserializeResponse.get("image")),"https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg");
        Map<String,Object> rating = (Map<String, Object>) deserializeResponse.get("rating");
        System.out.println(rating);
        Assert.assertEquals(rating.get("rate"),3.9);
        Assert.assertEquals(rating.get("count"),120);
        }

}

