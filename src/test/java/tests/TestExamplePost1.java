package tests;

import io.restassured.http.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import pojo.PojoPostAndPutRequest;
import pojo.PojoPostResponse;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;


public class TestExamplePost1 extends Base {

    private static final   Logger LOGGER = LoggerFactory.getLogger(TestExamplePost1.class);

    PojoPostResponse pojoPostResponse;
    PojoPostAndPutRequest pojoPostAndPutRequest; // = new PojoPostAndPutRequest("morpheus", "leader");
   // private String jsonString = new Gson().toJson(pojoPostAndPutRequest);
   // public static String id;


    @Test(priority = 2, description = "Test Post Method from https://reqres.in/api/users endpoint")
    public void test_PostMethod() {
        LOGGER.info("test_PostMethod() was started");

        pojoPostAndPutRequest = new PojoPostAndPutRequest();
        pojoPostAndPutRequest.setName("morpheus");
        pojoPostAndPutRequest.setJob("leader");

        LOGGER.info("Printing INPUT variable values: name = {}, job = {}", pojoPostAndPutRequest.getName(), pojoPostAndPutRequest.getJob());

         pojoPostResponse = given().log().uri()
                .when()
                .contentType(ContentType.JSON)
                 .body(pojoPostAndPutRequest) // pojoPostAndPutRequest   jsonString
                .post()
                .then().log().body()
                .statusCode(201)
                .extract().as(PojoPostResponse.class);

        assertThat(pojoPostResponse.getId()).as("Checking the response id").isNotNull().containsOnlyDigits();
        assertThat(pojoPostResponse.getCreatedAt()).as("Checking the response time").isNotNull()
                .matches("[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}.[0-9]{3}Z");
        assertThat(pojoPostResponse.getName()).isEqualTo("morpheus");
        assertThat(pojoPostResponse.getJob()).isEqualTo("leader");

       // id = pojoPostResponse.getId();

        LOGGER.info("Printing OUTPUT variable values: name = {}, job = {}", pojoPostResponse.getName(), pojoPostResponse.getJob());
        LOGGER.info("test_PostMethod() was finished");
    }
}
