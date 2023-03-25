package org.rest.cucumber.resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class GMapUtils {
    public static RequestSpecification requestSpecification;
    public RequestSpecification gMapRequestSpecificationBuild() throws IOException {
        if(requestSpecification==null) {
            PrintStream log = new PrintStream(new FileOutputStream("gmaplogs.txt"), true);
            requestSpecification = new RequestSpecBuilder()
                    .setBaseUri(this.gMapProperties("baseURI"))
                    .addQueryParam("key", "qaclick123")
                    .addHeader("Content-Type", "application/json")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .build();
            return requestSpecification;
        }
        return requestSpecification;
    }
    public String gMapProperties(String key) throws IOException {
        Properties props = new Properties();
        FileInputStream inputStream = new FileInputStream("D:\\Rest Assured framework\\RestassuredCucumberFramework\\src\\test\\resources\\GMap.properties");
        props.load(inputStream);
        return props.getProperty(key);
    }

    public String getGmapResponseJson(Response response, String key) {
        JsonPath jsonPath = new JsonPath(response.asString());
        return jsonPath.get(key);
    }

}
