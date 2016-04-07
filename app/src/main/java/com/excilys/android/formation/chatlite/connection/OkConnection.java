package com.excilys.android.formation.chatlite.connection;

import android.net.Proxy;
import android.util.Log;

import com.excilys.android.formation.chatlite.mappers.JsonMapper;
import com.excilys.android.formation.chatlite.mappers.MessageMapper;
import com.excilys.android.formation.chatlite.mappers.UserMapper;
import com.excilys.android.formation.chatlite.model.Message;
import com.excilys.android.formation.chatlite.model.User;
import com.excilys.android.formation.chatlite.tools.InputStreamToString;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.List;

import okhttp3.Challenge;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;

public enum OkConnection {
    INSTANCE;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private static final String TAG = OkConnection.class.getSimpleName();
    private static final String ACCESS_URL = "https://training.loicortola.com/chat-rest/2.0";

    private String user;
    private String pass;
    OkHttpClient client = null;

    private OkConnection() {
        this.user = null;
        this.pass = null;
        client = new OkHttpClient();
    }

    public void reset() {
        this.user = null;
        this.pass = null;
    }

    public boolean isValidUser(User u) {
        this.user = u.getUsername();
        this.pass = u.getPassword();

        // We authenticate
//        Authenticator.setDefault(new BasicAuthenticator(this.user, this.pass));


        String url = ACCESS_URL + "/connect";
        String res = getResult(url);
        boolean success = JsonMapper.mapStatus(res);
        return success;
    }

    public String getMessages(String limit, String offset) {
        String url = ACCESS_URL + "/messages?limit=" + limit + "&offset=" + offset;
        String res = getResult(url);
        return res;
    }

    public boolean sendMessage(Message message) {
        String url = ACCESS_URL + "/messages";
        String json = MessageMapper.toJSONObject(message).toString();
        String res = sendRequest(url, json);
        boolean success = JsonMapper.mapStatus(res);
        return success;
    }

    public boolean register(User u) {
        String url = ACCESS_URL + "/register";
        String json = UserMapper.toJSONObject(u).toString();
        String res = sendRequest(url, json);
        boolean success = JsonMapper.mapStatus(res);
        return success;
    }

    private String getResult(String url) {
        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass.toCharArray());
            }
        });
        String res = "";
        Response response = null;
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            response = client.newCall(request).execute();
            res = response.body().string();
            Log.d(TAG, "res = " + res);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }

        return res;
    }

    private String sendRequest(String url, String jsonObject) {
        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass.toCharArray());
            }
        });
        String res = "";
        Response response = null;
        RequestBody body = RequestBody.create(JSON, jsonObject);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try {
            response = client.newCall(request).execute();
            res = response.body().string();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }

        return res;
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
