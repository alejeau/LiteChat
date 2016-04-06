package com.excilys.android.formation.chatlite.mappers;

import java.util.ArrayList;
import java.util.HashMap;


public class MessagesMapper {

    /**
     * Converts a String of message into a ArrayList of HashMap<String, String>
     *     with the username as key and the message as value.
     * The String is divided by couples username:message, separated by a ';'.
     *
     * @param message the String of messages,
     * @return an ArrayList of HashMap<"username", "message">
     */
    public static ArrayList<HashMap<String, String>> map(String message) {
        ArrayList<HashMap<String, String>> list = new ArrayList<>();

        for (String str : message.split(";")) {
            String[] elements = str.split(":");
            if (elements.length == 2) {
                HashMap<String, String> tmp = new HashMap<>();
                tmp.put("name", elements[0]);
                tmp.put("message", elements[1]);
                list.add(0, tmp);
            }
        }

        return list;
    }
}
