package ApiAutomationPractice;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class HomeWork {

    @Test
    public void catsAmountValidation() {
        RestAssured.baseURI = "https://catfact.ninja";
        RestAssured.basePath = "/facts";
        Response response = RestAssured.given().queryParam("limit",332).accept("application/json")
                .when().get().then().log().body().extract().response();

        Map<String, Object> responseMap = response.as(new TypeRef<Map<String, Object>>() {});
        List<Map<String, Object>> deserializationResponse = (List<Map<String, Object>>) responseMap.get("data");

        int moreThan50 = 0;
        int lessThan200 = 0;
        int between50And200 = 0;
        int notContainCat = 0;

        for (Map<String, Object> requiredAmount : deserializationResponse) {
            String fact = (String) requiredAmount.get("fact");
            int length = (Integer) requiredAmount.get("length");

            if (length > 50) {
                moreThan50++;
            }
            if (length < 200) {
                lessThan200++;
            }
            if (length > 50 && length < 200) {
                between50And200++;
            }
            if (!fact.toLowerCase().contains("cat")) {
                notContainCat++;
            }
        }

        System.out.println("Facts with more than 50 characters: " + moreThan50);
        Assert.assertEquals(moreThan50, 299);

        System.out.println("Facts with less than 200 characters: " + lessThan200);
        Assert.assertEquals(lessThan200, 293);

        System.out.println("Facts with length between 50 and 200 characters: " + between50And200);
        Assert.assertEquals(between50And200, 260);

        System.out.println("Facts that do not contain 'cat': " + notContainCat);
        Assert.assertEquals(notContainCat, 25);
    }
}