package com.excilys.android.formation.chatlite.mappers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by excilys on 06/04/16.
 */
public class JsonMapper {

    public static String mapLogIn(String response, String tag) {
        JSONObject reader = null;
        String res = null;
        try {
            reader = new JSONObject(response);
            res = reader.getString(tag);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * Converts a String of message into a ArrayList of HashMap<String, String>
     *     with the username as key and the message as value.
     * The String is divided by couples username:message, separated by a ';'.
     *
     * @param response the String of messages,
     * @return an ArrayList of HashMap<"username", "message">
     */
    public static ArrayList<HashMap<String, String>> mapMessages(String response) {
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        JSONArray ja = null;
        try {
            ja = new JSONArray(response);
            int len = ja.length();
            for (int i = 0; i < len; i++) {
                list.add(0, toHashMap(ja.getJSONObject(i)));
            }
        } catch (JSONException e) {
        }

        if (ja != null) {
        }





//        for (String str : message.split(";")) {
//            String[] elements = str.split(":");
//            if (elements.length == 2) {
//                HashMap<String, String> tmp = new HashMap<>();
//                tmp.put("name", elements[0]);
//                tmp.put("message", elements[1]);
//                list.add(0, tmp);
//            }
//        }

        return list;
    }

    private static HashMap<String, String> toHashMap(JSONObject jso) throws JSONException{
        HashMap<String, String> h = new HashMap<>(2);
        h.put("name", jso.getString("login"));
        h.put("message", jso.getString("message"));
        return h;
    }

}
