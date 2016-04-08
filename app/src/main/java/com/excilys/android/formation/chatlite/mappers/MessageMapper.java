package com.excilys.android.formation.chatlite.mappers;

import com.excilys.android.formation.chatlite.model.SimpleMessage;

import org.json.JSONException;
import org.json.JSONObject;

public class MessageMapper {

    /**
     * Converts a SimpleMessage object to a JSON object.
     * @param m the message to convert
     * @return a JSON object representing the message m
     */
    public static JSONObject toJSONObject(SimpleMessage m) {
        JSONObject jso = new JSONObject();
        try {
            jso.put("uuid", m.getUuid().toString());
            jso.put("login", m.getLogin());
            jso.put("message", m.getMessage());
        } catch (JSONException j) {
            jso = null;
        }
        return jso;
    }
}
