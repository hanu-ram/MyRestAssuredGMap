package org.rest.cucumber.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.rest.cucumber.resources.APIResource;
import org.rest.cucumber.resources.GMapUtils;
import org.rest.cucumber.resources.MapTestDataBuild;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class GMapValidationDef extends GMapUtils {
    RequestSpecification requestSpecification = gMapRequestSpecificationBuild();
    RequestSpecification gMapRequestSpecification;
    Response gMapresponseAPI;
    static String place_id;

    public GMapValidationDef() throws IOException {
    }

    @Given("Add Place Payload with {string} {string} {string}")
    public void addPlacePayloadWith(String name, String language, String address) {

        gMapRequestSpecification = given().log().all().spec(requestSpecification)
                .body(MapTestDataBuild.addPlacePayload(name, language, address));
    }

    @When("user calls the {string} with {string} http request")
    public void userCallsTheWithPostRequestByUsingGivenPayload(String apiResource, String apiMethod) {
        APIResource ApiResource = APIResource.valueOf(apiResource);
        String apiSource = ApiResource.getApiResource();
        if (apiMethod.equalsIgnoreCase("POST")) {
            gMapresponseAPI = gMapRequestSpecification.when().post(apiSource);
        } else if (apiMethod.equalsIgnoreCase("GET")) {
            gMapresponseAPI = gMapRequestSpecification.when().get(apiSource);
        } else if (apiMethod.equalsIgnoreCase("DELETE")) {
            gMapresponseAPI = gMapRequestSpecification.when().delete(apiSource);
        } else {
            gMapresponseAPI = gMapRequestSpecification.when().put(apiSource);
        }
    }

    @Then("API call should got success with status code {int}")
    public void apiCallShouldGotSuccessWithStatusCode(int statusCode) {
        Assert.assertEquals(gMapresponseAPI.getStatusCode(), statusCode);
    }

    @And("In response the {string} contains {string}")
    public void inResponseTheContains(String parameter, String expected) {
        String addReponseString = gMapresponseAPI.asString();
        System.out.println("addReponseString = " + addReponseString);
        Assert.assertEquals(getGmapResponseJson(gMapresponseAPI, parameter), expected);
    }

    @And("verify place_id created matched to {string} using {string}")
    public void verifyPlace_idCreatedMatchedToUsing(String name, String apiResource) throws IOException{
        place_id= getGmapResponseJson(gMapresponseAPI, "place_id");
        gMapRequestSpecification = given().log().all()
                .spec(requestSpecification)
                .queryParam("place_id", place_id);
        this.userCallsTheWithPostRequestByUsingGivenPayload(apiResource, "get");
        String actualName = getGmapResponseJson(gMapresponseAPI, "name");
        Assert.assertEquals(actualName, name);
    }

    @Given("delete payload is given")
    public void deletePayloadIsGiven() {
        gMapRequestSpecification = given().log().all().spec(requestSpecification)
                .body(MapTestDataBuild.deletePayLoad(place_id));
    }
    public void beforeDeleteExecute() {
        place_id= getGmapResponseJson(gMapresponseAPI, "place_id");
    }
}