package org.rest.cucumber.resources;

import org.rest.cucumber.pojo.AddPlacePojo;
import org.rest.cucumber.pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class MapTestDataBuild {

    public static AddPlacePojo addPlacePayload(String name, String language, String address) {
        AddPlacePojo addPlace = new AddPlacePojo();
        addPlace.setLanguage(language);
        addPlace.setAccuracy(50);
        addPlace.setAddress(address);
        addPlace.setName(name);
        addPlace.setPhone_number("99999999999");
        addPlace.setWebsite("http://google.com");
        List<String> types = new ArrayList<>();
        types.add("shoe park");
        types.add("shop");
        addPlace.setTypes(types);
        Location location = new Location("-38.383494", "33.427362");
        addPlace.setLocation(location);
        return addPlace;
    }
    public static String deletePayLoad(String placeId) {
        return "{\n" +
                "    \"place_id\":\""+placeId+"\"\n" +
                "}\n";
    }

}
