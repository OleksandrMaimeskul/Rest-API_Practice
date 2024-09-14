package ApiAutomationPractice;

import PoJo.BookPojo;
import PoJo.StarWarsPeoplePojo;
import PoJo.StarWarsPlanetPojo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

public class StarWars {
    @Test
    public void validatePlanetsInfoWithPojo() {
        RestAssured.baseURI = "https://swapi.dev";
        RestAssured.basePath = "api/planets/1";

        Response response = RestAssured.given().accept("application/json").when().get()
                .then().log().body().statusCode(200).extract().response();

        StarWarsPlanetPojo deserializationResponse = response.as(StarWarsPlanetPojo.class);
        Assert.assertEquals(deserializationResponse.getName(),"Tatooine");
        Assert.assertEquals(deserializationResponse.getClimate(),"arid");
        Assert.assertEquals(deserializationResponse.getTerrain(),"desert");
    }
    @Test
    public void validateCharacterInformation(){
        RestAssured.baseURI = "https://swapi.dev";
        RestAssured.basePath = "api/people/1";

        Response response = RestAssured.given().accept("application/json").when().get()
                .then().log().body().statusCode(200).extract().response();
        StarWarsPeoplePojo deserializationResponse = response.as(StarWarsPeoplePojo.class);
        Assert.assertEquals(deserializationResponse.getName(),"Luke Skywalker");
        Assert.assertEquals(deserializationResponse.getHeight(),"172");
        Assert.assertEquals(deserializationResponse.getMass(),"77");
        Assert.assertEquals(deserializationResponse.getGender(),"male");
        List<String> actualListOFilms = Arrays.asList("https://swapi.dev/api/films/1/","https://swapi.dev/api/films/2/",
                "https://swapi.dev/api/films/3/","https://swapi.dev/api/films/6/");
        Assert.assertEquals(deserializationResponse.getFilms(),actualListOFilms);
        List<String> actualListOVechicles = Arrays.asList("https://swapi.dev/api/vehicles/14/","https://swapi.dev/api/vehicles/30/");
        Assert.assertEquals(deserializationResponse.getVehicles(),actualListOVechicles);
    }
}
