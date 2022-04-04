package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.security.KeyStore;
import java.util.*;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class TestExampleGet1 extends Base {

  //  private static Map<String, Integer> getParam = new HashMap<>(){{put("page", 2);}};
   // private static  List<String> keyList = Collections.singletonList("page");
    private static final String PAGE = "page";
    private static  List<Integer> valueList = Arrays.asList(1,2);


    @Test(priority = 1, description = "Test Get Method from https://reqres.in/api/users?page=2 endpoint")
    public void test_GetMethod() {

        given().log().uri()
                .when()
                .header("Content-Type", "application/x-www-form-urlencoded")
              //  .param(getParam.keySet().toString().replace("[","").replace("]", ""), getParam.values())
                .param(PAGE, valueList.get(1))
                .get()
                .then().log().body()
                .statusCode(200)
                .body("data[0].email", equalTo("michael.lawson@reqres.in"));
    }

    @Test(description = "Test Get Method from https://reqres.in/api/users?page=1 endpoint")
    public void test_GetMethod2() {

        Response response = given().log().uri()
                .when()
                .contentType(ContentType.JSON)
                .param(PAGE, valueList.get(0))
                .get()
                .then().log().body()
                .statusCode(200)
                .extract().response();

        // assertThat(pojoPostResponse.jsonPath().getString("data.email[0]"),equalTo("michael.lawson@reqres.in"));
        String bodyFilteredFirstName = response.jsonPath().getString("data.first_name");
        Assert.assertEquals(bodyFilteredFirstName.substring(1, bodyFilteredFirstName.length() - 1), "George, Janet, Emma, Eve, Charles, Tracey");
    }
}
