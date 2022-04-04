package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pojo.PojoPostAndPutRequest;
import pojo.PojoPostResponse;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class TestExampleDelete1 extends Base {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestExamplePut1.class);
    //  PojoPostAndPutRequest pojoPostAndPutRequest;
    private static String pojoPostResponseId = "";
    Response response;
    PojoPostResponse pojoPostResponse;

    @BeforeMethod
    public void preparePostResponseForDeleteMethod() {
        PodamFactory podamFactory = new PodamFactoryImpl();
        PojoPostAndPutRequest pojoPostAndPutRequestPodam = podamFactory.manufacturePojo(PojoPostAndPutRequest.class);

        //    pojoPostAndPutRequest = new PojoPostAndPutRequest();
        //   pojoPostAndPutRequest.setName("morpheus1");
        //   pojoPostAndPutRequest.setJob("leader1");

        pojoPostResponse = given().log().uri()
                .when()
                .contentType(ContentType.JSON)
                .body(pojoPostAndPutRequestPodam) // pojoPostAndPutRequest   pojoPostAndPutRequestPodam
                .post()
                .then().log().body()
                .statusCode(201)
                .extract().as(PojoPostResponse.class);

        pojoPostResponseId = pojoPostResponse.getId();

        LOGGER.info("Prepared step for preparePostResponseForDeleteMethod() was done");
    }

    @Test(priority = 4, description = "Test Delete Method from https://reqres.in/api/users/{ID} endpoint")
    @Story("Delete API test")
    @Description("Delete API test for /reqres.in/api/users/{ID}")
    public void test_DeleteMethod() {
        //   String pojoPostResponseId = TestExamplePost1.id;
        LOGGER.info("test_DeleteMethod() was started");


        response = given().log().uri()
                .when()
                .contentType(ContentType.JSON)
                .delete(divider + pojoPostResponseId)
                .then().log().body()
                .statusCode(204)
                .extract().response();

        String responseBody = response.getBody().asString();
        assertThat(responseBody).isNotNull().isEmpty();

        LOGGER.info("test_DeleteMethod() was finished");
    }
}

