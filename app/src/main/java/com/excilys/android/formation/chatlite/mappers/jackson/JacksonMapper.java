package com.excilys.android.formation.chatlite.mappers.jackson;

import android.util.Log;

import com.excilys.android.formation.chatlite.model.ComplexMessage;
import com.excilys.android.formation.chatlite.model.SimpleMessage;
import com.excilys.android.formation.chatlite.model.User;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;

public class JacksonMapper {
    private static final String TAG = JacksonMapper.class.getSimpleName();

    /**
     * Converts a SimpleMessage object to a JSON String.
     *
     * @param m the SimpleMessage to convert
     * @return a JSON String representing the SimpleMessage m
     */
    public static String simpleMessageToJSONString(SimpleMessage m) {
        ObjectMapper om = new ObjectMapper();
        StringWriter sw = new StringWriter();
        try {
            om.writeValue(sw, m);
        } catch (IOException e) {
            Log.e(TAG, "Error mapping SimpleMessage to Jackson string!");
            Log.e(TAG, e.getMessage());
            return null;
        }

        return sw.toString();
    }

    /**
     * Converts a SimpleMessage object to a JSON String.
     *
     * @param m the SimpleMessage to convert
     * @return a JSON String representing the SimpleMessage m
     */
    public static String complexMessageToJSONString(ComplexMessage m) {
        ObjectMapper om = new ObjectMapper();
        StringWriter sw = new StringWriter();
        try {
            om.writeValue(sw, m);
        } catch (IOException e) {
            Log.e(TAG, "Error mapping SimpleMessage to Jackson string!");
            Log.e(TAG, e.getMessage());
            return null;
        }

        return sw.toString();
    }

    /**
     * Converts a User object to a JSON String.
     *
     * @param u the User to convert
     * @return a JSON String representing the User u
     */
    public static String userToJSONString(User u) {
        ObjectMapper om = new ObjectMapper();
        StringWriter sw = new StringWriter();
        try {
            om.writeValue(sw, u);
        } catch (IOException e) {
            Log.e(TAG, "Error mapping SimpleMessage to Jackson string!");
            Log.e(TAG, e.getMessage());
            return null;
        }

        return sw.toString();
    }
}
