package com.excilys.android.formation.chatlite.connection;

import android.util.Log;

import com.excilys.android.formation.chatlite.mappers.MessageMapper;
import com.excilys.android.formation.chatlite.model.Message;
import com.excilys.android.formation.chatlite.tools.InputStreamToString;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.UnknownHostException;

public enum RestConnection {
    INSTANCE;

    private static final String TAG = RestConnection.class.getSimpleName();
    private static final String ACCESS_URL = "https://training.loicortola.com/chat-rest/2.0";

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
        InputStream in = null;
        try {
            url = new URL(textUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream());
            res = InputStreamToString.convert(in);
        } catch (Exception e) {
        } finally {
            closeInputStream(in);
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

    public void sendMessage(Message message) {
        String textUrl = ACCESS_URL + "/messages";
        URL url = null;
        HttpURLConnection urlConnection = null;
        InputStream in = null;
        try {
            url = new URL(textUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
            writer.write(MessageMapper.toJSONObject(message).toString());
            writer.flush();
            writer.close();
            urlConnection.connect();
            in = urlConnection.getErrorStream();
            String res = InputStreamToString.convert(in);
            Log.d(TAG, "res = " + res);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
        } finally {
            closeInputStream(in);
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }

    public boolean checkOnlineAvailability(){
        boolean available = false;
        try {
            available = InetAddress.getByName(ACCESS_URL).isReachable(30);
        } catch (UnknownHostException e) {
            Log.e(TAG, e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
        return available;
    }

    private static void closeInputStream(InputStream in) {
        try {
            if (in != null) {
                in.close();
            }
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        } finally {
            in = null;
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
