package com.excilys.android.formation.chatlite.connection;

import android.util.Log;

import com.excilys.android.formation.chatlite.mappers.MessageMapper;
import com.excilys.android.formation.chatlite.tools.InputStreamToString;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;

public enum RestConnection {
    INSTANCE;

        private final String TAG = RestConnection.class.getSimpleName();
    private static final String ACCESS_URL = "https://training.loicortola.com/chat-rest/2.0/";

    private String user;
    private String pass;

    private RestConnection() {
        this.user = null;
        this.pass = null;
    }

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
        String textUrl = ACCESS_URL + "/messages";
        URL url = null;
        HttpURLConnection urlConnection = null;

        try {
            Log.d(TAG, "dibug 0");
            url = new URL(textUrl);
            Log.d(TAG, "dibug 1");
            urlConnection = (HttpURLConnection) url.openConnection();
            Log.d(TAG, "dibug 2");
            urlConnection.setRequestMethod("POST");
            Log.d(TAG, "dibug 3");
            OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
            Log.d(TAG, "dibug 6");
            writer.write(MessageMapper.toJSONObject(this.user, message).toString());
            Log.d(TAG, "dibug 7");
            writer.flush();
            Log.d(TAG, "dibug 8");
            writer.close();
            Log.d(TAG, "dibug 9");
            InputStream in = urlConnection.getInputStream();
            Log.d(TAG, "dibug 10");
            String res = InputStreamToString.convert(in);
            Log.d(TAG, "res = " + res);
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
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
