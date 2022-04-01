package tests;

import io.restassured.http.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pojo.PojoPostAndPutRequest;
import pojo.PojoPostResponse;
import pojo.PojoPutResponse;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static tests.PostBodyConstructor.requestPutUserBody;

public class TestExamplePut1 extends Base {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestExamplePut1.class);

    PojoPutResponse pojoPutResponse;
    PojoPostResponse pojoPostResponse;
    PojoPostAndPutRequest pojoPostAndPutRequest;
    private static String pojoPostResponseId= "";
    private static String pojoPostResponseName = "";
    private static String pojoPostResponseJob= "zion resident";

    @BeforeMethod
    public void preparePostResponseForPutMethod() {
        pojoPostAndPutRequest = new PojoPostAndPutRequest();
        pojoPostAndPutRequest.setName("morpheus");
        pojoPostAndPutRequest.setJob("leader");

        pojoPostResponse = given().log().uri()
                .when()
                .contentType(ContentType.JSON)
                .body(pojoPostAndPutRequest) // pojoPostAndPutRequest   jsonString
                .post()
                .then().log().body()
                .statusCode(201)
                .extract().as(PojoPostResponse.class);

        pojoPostResponseId = pojoPostResponse.getId();
        pojoPostResponseName = pojoPostResponse.getName();

        LOGGER.info("Prepared step for preparePostResponseForPutMethod() was done");
    }

    @Test(priority = 3, description = "Test Put Method from https://reqres.in/api/users/{ID} endpoint")
    public void test_PutMethod() {
        pojoPostAndPutRequest = new PojoPostAndPutRequest(pojoPostResponseName, pojoPostResponseJob);
      //   String pojoPostResponseId = TestExamplePost1.id;
        LOGGER.info("test_PutMethod() was started");


        pojoPutResponse = given().log().uri()
                .when()
                .contentType(ContentType.JSON)
                .body(pojoPostAndPutRequest) // pojoPostResponseId or  requestPutUserBody
                .put(divider + pojoPostResponseId)
                .then().log().body()
                .statusCode(200)
                .extract().as(PojoPutResponse.class);

        assertThat(pojoPutResponse.toString()).doesNotContain("\"id\"");
        assertThat(pojoPutResponse.getUpdatedAt()).as("Checking the response time").isNotNull()
                .matches("[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}.[0-9]{3}Z");
        assertThat(pojoPutResponse.getName()).isEqualTo("morpheus");
        assertThat(pojoPutResponse.getJob()).isEqualTo("zion resident");


        LOGGER.info("Printing OUTPUT variable values: name = {}, job = {}", pojoPutResponse.getName(), pojoPutResponse.getJob());
        LOGGER.info("test_PutMethod() was finished");
    }
}


