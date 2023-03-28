package Resources;

import POJO.AddPlaceBody;
import POJO.Location;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TestBuildData {
    public AddPlaceBody addPlacePayload(String name, String address, String latitude, String longitude)
    {
        AddPlaceBody apb = new AddPlaceBody();

        Location loc = new Location();
        loc.setLat(latitude);
        loc.setLng(longitude);
        apb.setLocation(loc);

        apb.setAccuracy(53);
        apb.setName(name);
        apb.setPhone_number("(+91) 983 893 3937");
        apb.setAddress(address);
        apb.setLanguage("French-IN");
        apb.setWebsite("http://google.com");

        List<String> typeStr = new ArrayList();
        typeStr.add("shoe park");
        typeStr.add("SHOqP");
        apb.setTypes(typeStr);

        return apb;
    }

    public String deletePlacePayload(String place_id)
    {
        return "{\n" +
                "    \"place_id\":\""+place_id+"\"\n" +
                "}";
    }
}
