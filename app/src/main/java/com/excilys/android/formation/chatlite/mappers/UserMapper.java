package com.excilys.android.formation.chatlite.mappers;

import com.excilys.android.formation.chatlite.model.Message;
import com.excilys.android.formation.chatlite.model.User;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by excilys on 07/04/16.
 */
public class UserMapper {
    public static JSONObject toJSONObject(User u) {
        JSONObject jso = new JSONObject();
        try {
            jso.put("login", u.getUsername());
            jso.put("password", u.getPassword());
        } catch (JSONException j) {
            jso = null;
        }
        return jso;
    }
}
