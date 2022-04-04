package tests;

import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;

public abstract class Base {

    public static final String divider = "/";

    @BeforeClass
    public void setUp() {
        baseURI = "https://reqres.in/api/";
        basePath = "users";
    }
}
