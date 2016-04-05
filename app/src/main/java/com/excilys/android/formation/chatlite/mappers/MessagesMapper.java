package com.excilys.android.formation.chatlite.mappers;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by excilys on 05/04/16.
 */
public class MessagesMapper {
    public static ArrayList<HashMap<String, String>> map(String message) {
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();

        for (String str : message.split(";")) {
            String[] elements = str.split(":");
            if (elements.length == 2) {
                HashMap<String,String> tmp = new HashMap<>();
                tmp.put("name", elements[0]);
                tmp.put("message", elements[1]);
                list.add(0, tmp);
            }
        }

        return list;
    }
}
