package ApiAutomationPractice;

import PoJo.PetsPojo;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class PetsStore {
    @Test
    public void petsStoreValidationInfo() {

        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        RestAssured.basePath = "/pet";

        Response response = RestAssured.given().contentType("application/json").accept("application/json").body("{\n" +
                        "  \"id\":12388990,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 53,\n" +
                        "    \"name\": \"Dinosaur\"\n" +
                        "  },\n" +
                        "  \"name\": \"Rex\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"https://www.google.com/url?sa=i&url=https%3A%2F%2Ftoystory.disney.com%2Frex&psig=AOvVaw3t4qKamxBKJFphTanp2cSi&ust=1726419851311000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCNjCtqP1wogDFQAAAAAdAAAAABAE\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 45,\n" +
                        "      \"name\": \"Green dinosaur\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"available\"\n" +
                        "}").when().post()
                .then().log().body().extract().response();
        PetsPojo deserializationResponse = response.as(PetsPojo.class);
        Assert.assertEquals(deserializationResponse.getId(),12388990);
        Assert.assertEquals(deserializationResponse.getCategory().getId(), 53);
        Assert.assertEquals(deserializationResponse.getCategory().getName(), "Dinosaur");
        Assert.assertEquals(deserializationResponse.getName(), "Rex");
        Assert.assertEquals(deserializationResponse.getPhotoUrls().getFirst(), "https://www.google.com/url?sa=i&url=https%3A%2F%2Ftoystory.disney.com%2Frex&psig=AOvVaw3t4qKamxBKJFphTanp2cSi&ust=1726419851311000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCNjCtqP1wogDFQAAAAAdAAAAABAE");
        Assert.assertEquals(deserializationResponse.getTags().getFirst().getId(), 45);
        Assert.assertEquals(deserializationResponse.getTags().getFirst().getName(), "Green dinosaur");
        Assert.assertEquals(deserializationResponse.getStatus(), "available");
//    }
    }

        @Test
        public void petsStoreValidationInfoWithJasonPath() {

            RestAssured.baseURI = "https://petstore.swagger.io/v2";
            RestAssured.basePath = "/pet";

            Response response = RestAssured.given().contentType("application/json").accept("application/json").body("{\n" +
                            "  \"id\":12388990,\n" +
                            "  \"category\": {\n" +
                            "    \"id\": 53,\n" +
                            "    \"name\": \"Dinosaur\"\n" +
                            "  },\n" +
                            "  \"name\": \"Rex\",\n" +
                            "  \"photoUrls\": [\n" +
                            "    \"https://www.google.com/url?sa=i&url=https%3A%2F%2Ftoystory.disney.com%2Frex&psig=AOvVaw3t4qKamxBKJFphTanp2cSi&ust=1726419851311000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCNjCtqP1wogDFQAAAAAdAAAAABAE\"\n" +
                            "  ],\n" +
                            "  \"tags\": [\n" +
                            "    {\n" +
                            "      \"id\": 45,\n" +
                            "      \"name\": \"Green dinosaur\"\n" +
                            "    }\n" +
                            "  ],\n" +
                            "  \"status\": \"available\"\n" +
                            "}").when().post()
                    .then().log().body().extract().response();
            JsonPath deserializationResponse=response.jsonPath();
            Assert.assertEquals(deserializationResponse.getInt("id"),12388990);
            Assert.assertEquals(deserializationResponse.getList("photoUrls").getFirst(),"https://www.google.com/url?sa=i&url=https%3A%2F%2Ftoystory.disney.com%2Frex&psig=AOvVaw3t4qKamxBKJFphTanp2cSi&ust=1726419851311000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCNjCtqP1wogDFQAAAAAdAAAAABAE");
            Assert.assertEquals(deserializationResponse.getList("tags.name").getFirst(),"Green dinosaur");

        }
    }


