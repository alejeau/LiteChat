package com.excilys.android.formation.chatlite.connection;

import com.excilys.android.formation.chatlite.tools.InputStreamToString;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestConnection {
    private static final String ACCESS_URL = "http://formation-android-esaip.herokuapp.com";

    public static String isValidUser(String user, String pass) {
        String textUrl = ACCESS_URL + "/connect/" + user + "/" + pass;
        URL url = null;
        HttpURLConnection urlConnection = null;
        String res = "";

        try {
            url = new URL(textUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            res = InputStreamToString.convert(in);
        } catch (Exception e) {
        } finally {
            urlConnection.disconnect();
        }

        return res;
    }

    public static String getMessages(String user, String pass) {
        String textUrl = ACCESS_URL + "/messages/" + user + "/" + pass;
        URL url = null;
        HttpURLConnection urlConnection = null;
        String res = "";

        try {
            url = new URL(textUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            res = InputStreamToString.convert(in);
        } catch (Exception e) {
        } finally {
            urlConnection.disconnect();
        }
        return res;
    }

    public static void sendMessage(String user, String pass, String message) {
        String textUrl = ACCESS_URL + "/message/" + user + "/" + pass + "/" + message;
        URL url = null;
        HttpURLConnection urlConnection = null;
        
        try {
            url = new URL(textUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.getInputStream();
        } catch (Exception e) {
        } finally {
            urlConnection.disconnect();
        }
    }
}
