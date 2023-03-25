package org.rest.cucumber.resources;

public enum APIResource {
    ADDPLACEAPI("/maps/api/place/add/json"),
    GETPLACEAPI("/maps/api/place/get/json"),
    DELETEPLACEAPI("/maps/api/place/delete/json");
    String api;
    APIResource(String api) {
        this.api = api;
    }

    public String getApiResource() {
        return api;
    }
}
