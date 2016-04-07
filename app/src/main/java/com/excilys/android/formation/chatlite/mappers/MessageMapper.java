package com.excilys.android.formation.chatlite.mappers;

import com.excilys.android.formation.chatlite.model.Message;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

/**
 * Created by excilys on 06/04/16.
 */
public class MessageMapper {
    public static JSONObject toJSONObject(Message m) {
        JSONObject jso = new JSONObject();
        try {
            jso.put("uuid", m.getUuid().toString());
            jso.put("login", m.getAuthor());
            jso.put("message", m.getContent());
            jso.put("attachments", m.getAttachment());
        } catch (JSONException j) {
            jso = null;
        }
        return jso;
    }
}
