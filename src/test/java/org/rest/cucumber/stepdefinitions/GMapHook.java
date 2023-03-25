package org.rest.cucumber.stepdefinitions;

import io.cucumber.java.Before;
import io.restassured.response.Response;

import java.io.IOException;

public class GMapHook {

    @Before("@DeletePlace")
    public void deletePrecondition() throws IOException {
        if(GMapValidationDef.place_id==null) {
            GMapValidationDef gMapValidationDef = new GMapValidationDef();
            gMapValidationDef.addPlacePayloadWith("Paris", "French", "testing address");
            gMapValidationDef.userCallsTheWithPostRequestByUsingGivenPayload("ADDPLACEAPI", "POST");
            gMapValidationDef.beforeDeleteExecute();
        }
    }
}
