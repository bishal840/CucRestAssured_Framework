package StepDefinations;

import POJO.AddPlaceBody;
import POJO.Location;
import Resources.APIResources;
import Resources.TestBuildData;
import Resources.Utilities;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class stepDef1 extends Utilities {
    TestBuildData buildData = new TestBuildData();
    ResponseSpecification res;
    RequestSpecification reqSpec;
    Response response;
    String place_id;
    @Given("Add Place Payload with {string} {string} {string} {string}")
    public void addPlacePayloadWith(String name, String address, String latitude, String longitude) throws IOException {
        res = new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();

        reqSpec = given().log().all().spec(requestSpec()).body(buildData.addPlacePayload(name,address,latitude,longitude));

    }

    @When("User calls {string} with {string} http request")
    public void userCallsWithHttpRequest(String resource, String method) {
        APIResources apiResources = APIResources.valueOf(resource);

        if(method.equalsIgnoreCase("POST"))
            response= reqSpec.when().post(apiResources.getResource()).then().log().all().extract().response();
        else if(method.equalsIgnoreCase("DELETE"))
            response= reqSpec.when().delete(apiResources.getResource()).then().log().all().extract().response();
        else if(method.equalsIgnoreCase("GET"))
            response= reqSpec.when().get(apiResources.getResource()).then().log().all().extract().response();
    }

    @Then("API call got success with status code {int}")
    public void apiCallGotSuccessWithStatusCode(int expectedStatusCode) {
        Assert.assertEquals(response.getStatusCode(),expectedStatusCode);
    }

    @And("{string} in response body is {string}")
    public void inResponseBodyIs(String key, String value) {
        Assert.assertEquals(getJsonPathValue(response,key),value);
    }


    @Given("Get Place Payload with place_id {string}")
    public void getPlacePayloadWithPlace_id(String place_id) throws IOException {
        if(place_id.equals(""))
            place_id= getJsonPathValue(response,"place_id");
        this.place_id=place_id;
        reqSpec = given().log().all().spec(requestSpec()).queryParam("place_id",place_id);

    }

    @And("{string} in response body is generated")
    public void inResponseBodyIsGenerated(String key) {
        Assert.assertFalse(getJsonPathValue(response,key).isEmpty());
    }

    @Given("Delete Place Payload with place_id {string}")
    public void deletePlacePayloadWithPlace_id(String place_id) throws IOException {
        if(place_id.equals(""))
            place_id= this.place_id;
        System.out.println("++++++++++++++++++++++++++");
        System.out.println("Place Id:"+place_id);
        System.out.println("++++++++++++++++++++++++++");
        reqSpec = given().log().all().spec(requestSpec()).body(buildData.deletePlacePayload(place_id));
    }
}
