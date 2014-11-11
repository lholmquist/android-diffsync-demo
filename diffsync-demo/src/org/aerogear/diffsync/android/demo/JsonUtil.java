package org.aerogear.diffsync.android.demo;

import org.aerogear.diffsync.android.demo.Info.Hobby;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class JsonUtil {
    
    private JsonUtil() {
    }
    
    public static String toJson(final Info info) {
        try {
            final JSONObject content = new JSONObject();
            content.put("name", info.getName());
            content.put("profession", info.getProfession());
            final JSONArray hobbies = new JSONArray();
            for (Hobby hobby : info.getHobbies()) {
                hobbies.put(new JSONObject().put("id", hobby.id())
                        .put("description", hobby.description()));
            }
            content.put("hobbies", hobbies);
            return content.toString();
        } catch (JSONException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
    
    public static Info fromJson(final String json) {
        try {
            final JSONObject jsonObject = new JSONObject(json);
            final JSONArray hobbies = jsonObject.getJSONArray("hobbies");
            return new Info(jsonObject.get("name").toString(), 
                    jsonObject.get("profession").toString(),
                    new Hobby(id(hobbies.getJSONObject(0)), description(hobbies.getJSONObject(0))),
                    new Hobby(id(hobbies.getJSONObject(1)), description(hobbies.getJSONObject(1))),
                    new Hobby(id(hobbies.getJSONObject(2)), description(hobbies.getJSONObject(2))),
                    new Hobby(id(hobbies.getJSONObject(3)), description(hobbies.getJSONObject(3))));
        } catch (JSONException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static String id(final JSONObject json) throws JSONException {
        return json.get("id").toString();
    }

    private static String description(final JSONObject json) throws JSONException {
        return json.get("description").toString();
    }
}
