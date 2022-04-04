package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.nio.file.*;

import static io.restassured.RestAssured.requestSpecification;

public abstract class Base {

    public static final String divider = "/";

    @BeforeSuite
    private static void deleteAllureResults() {

        Path path = FileSystems.getDefault().getPath("//target/allure-results");
        try {
            Files.deleteIfExists(path);
        } catch (NoSuchFileException x) {
            System.err.format("%s: no such" + " file or directory%n", path);
        } catch (DirectoryNotEmptyException x) {
            System.err.format("%s not empty%n", path);
        } catch (IOException ix) {
            ix.printStackTrace();
        }
    }

    @BeforeTest
    public void setUp() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://reqres.in/api/")
                .setBasePath("users")
                .addFilter(new AllureRestAssured())
                .setContentType(ContentType.JSON)
                .build().log().uri();
    }
}
