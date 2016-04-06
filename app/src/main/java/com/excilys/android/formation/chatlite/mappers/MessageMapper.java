package com.excilys.android.formation.chatlite.mappers;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

/**
 * Created by excilys on 06/04/16.
 */
public class MessageMapper {
    public static JSONObject toJSONObject(String user, String message) {
        JSONObject jso = new JSONObject();
        UUID uuid = UUID.randomUUID();
        try {
            jso.put("uuid", uuid);
            jso.put("login", user);
            jso.put("login", message);
        } catch (JSONException j) {
            jso = null;
        }
        return jso;
    }
}
