package ApiAutomationPractice;

import PoJo.BookPojo;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Book {
    @Test
    public void validateBookDetailsWithPojo() {

        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.basePath = "booking/16";

        Response response = RestAssured.given().accept("application/json").when().get()
                .then().log().body().statusCode(200).extract().response();

        BookPojo deserializationResponse = response.as(BookPojo.class);
        Assert.assertEquals(deserializationResponse.getFirstname(), "Jane");
        Assert.assertEquals(deserializationResponse.getLastname(), "Doe");
        Assert.assertTrue(deserializationResponse.isDepositpaid());
        Assert.assertEquals(deserializationResponse.getAdditionalneeds(), "Extra pillows please");
        Assert.assertEquals(deserializationResponse.getBookingdates().getCheckin(), "2018-01-01");
        Assert.assertEquals(deserializationResponse.getBookingdates().getCheckout(), "2019-01-01");

    }

    @Test
    public void validateUpdateBookWithPojo() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.basePath = "booking/16";

        Response response = RestAssured.given().contentType("application/json").accept("application/json").header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=").body("{\n" +
                        "    \"firstname\": \"Alex\",\n" +
                        "    \"lastname\": \"Doe\",\n" +
                        "    \"totalprice\": 111,\n" +
                        "    \"depositpaid\": true,\n" +
                        "    \"bookingdates\": {\n" +
                        "        \"checkin\": \"2018-01-01\",\n" +
                        "        \"checkout\": \"2019-01-01\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\": \"Extra pillows please\"\n" +
                        "}").put()
                .then().log().body().statusCode(200).extract().response();
        BookPojo deserializationResponse = response.as(BookPojo.class);
        Assert.assertEquals(deserializationResponse.getAdditionalneeds(), "Extra pillows please");
        Assert.assertEquals(deserializationResponse.getBookingdates().getCheckout(), "2019-01-01");

    }
}
