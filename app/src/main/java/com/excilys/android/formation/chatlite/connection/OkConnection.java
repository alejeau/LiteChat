package com.excilys.android.formation.chatlite.connection;

import android.util.Log;

import com.excilys.android.formation.chatlite.mappers.JsonMapper;
import com.excilys.android.formation.chatlite.mappers.jackson.JacksonMapper;
import com.excilys.android.formation.chatlite.model.SimpleMessage;
import com.excilys.android.formation.chatlite.model.User;

import java.io.IOException;
import java.net.InetAddress;

import okhttp3.Authenticator;
import okhttp3.Credentials;
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
    }

    public void reset() {
        this.user = null;
        this.pass = null;
    }

    public void authenticate() {
        client = new OkHttpClient.Builder()
                .authenticator(new Authenticator() {
                    @Override
                    public Request authenticate(Route route, Response response) throws IOException {
                        String credential = Credentials.basic(user, pass);
                        return response.request().newBuilder()
                                .header("Authorization", credential)
                                .build();
                    }
                })
                .build();
    }

    public boolean isValidUser(User u) {
        this.user = u.getUsername();
        this.pass = u.getPassword();

        // We authenticate
        authenticate();

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

    public boolean sendMessage(SimpleMessage message) {
        String url = ACCESS_URL + "/messages";
        String json = JacksonMapper.simpleMessageToJSONString(message);
        String res = sendRequest(url, json);
        boolean success = JsonMapper.mapStatus(res);
        return success;
    }

    public boolean register(User u) {
        String url = ACCESS_URL + "/register";
        String json = JacksonMapper.userToJSONString(u);
        String res = sendRequest(url, json);
        boolean success = JsonMapper.mapStatus(res);
        return success;
    }

    /**
     * @param url
     * @return
     */
    private String getResult(String url) {
        String res = "";
        Response response = null;
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            response = client.newCall(request).execute();
            res = response.body().string();
            Log.d(TAG, "dibug: resRes = " + res);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }

        return res;
    }

    private String sendRequest(String url, String jsonString) {
        String res = "";
        Response response = null;
        RequestBody body = RequestBody.create(JSON, jsonString);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try {
            response = client.newCall(request).execute();
//            if (responseCount(response) >= 0) {
//                Log.d(TAG, "dibug: resReq = \"" + jsonString + "\"");
//                res = response.body().string();
//                Log.d(TAG, "dibug: resReq = " + res);
//                return res;
//            }
            Log.d(TAG, "dibug: resReq = \"" + jsonString + "\"");
            res = response.body().string();
            Log.d(TAG, "dibug: resReq = " + res);
            return res;
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }

        return res;
    }

    private static int responseCount(Response response) {
        int result = 1;
        while ((response = response.priorResponse()) != null) {
            result++;
        }
        return result;
    }

    public static boolean checkOnlineAvailability() {
        boolean available = false;
        try {
            available = InetAddress.getByName(ACCESS_URL).isReachable(30);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
        return available;
    }
}
