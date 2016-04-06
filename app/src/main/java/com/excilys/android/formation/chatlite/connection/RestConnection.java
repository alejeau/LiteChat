package com.excilys.android.formation.chatlite.connection;

import android.util.Log;

import com.excilys.android.formation.chatlite.tools.InputStreamToString;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;

public enum RestConnection {
    INSTANCE;

//    private final String TAG = RestConnection.class.getSimpleName();
    private static final String ACCESS_URL = "https://training.loicortola.com/chat-rest/2.0/";

    private String user;
    private String pass;

    private RestConnection() {
        this.user = null;
        this.pass = null;
    }

//    public void init(String user, String pass) {
//        if ((user == null) && (pass == null)) {
//            this.user = user;
//            this.pass = pass;
//            Authenticator.setDefault(new BasicAuthenticator(this.user, this.pass));
//        }
//    }
//
//    public void reinit(String user, String pass) {
//        this.user = user;
//        this.pass = pass;
//        Authenticator.setDefault(new BasicAuthenticator(this.user, this.pass));
//    }

    public void reset() {
        this.user = null;
        this.pass = null;
    }

    public String isValidUser(String user, String pass) {
        this.user = user;
        this.pass = pass;
        // We authenticate
        Authenticator.setDefault(new BasicAuthenticator(this.user, this.pass));

        String textUrl = ACCESS_URL + "/connect";
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

    public String getMessages(String limit, String offset) {
        String textUrl = ACCESS_URL + "/messages?limit=" + limit + "&offset=" + offset;
//        Log.i(TAG, "URL = " + textUrl);
//        String textUrl = ACCESS_URL + "/messages?limit=100&offset=0";
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

    public void sendMessage(String message) {
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

    static class BasicAuthenticator extends Authenticator {
        String baName;
        String baPassword;

        protected BasicAuthenticator(String baName1, String baPassword1) {
            baName = baName1;
            baPassword = baPassword1;
        }

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(baName, baPassword.toCharArray());
        }
    }
}
