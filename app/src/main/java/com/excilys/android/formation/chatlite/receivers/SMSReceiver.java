package com.excilys.android.formation.chatlite.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.widget.Toast;

/**
 * Created by excilys on 05/04/16.
 */
public class SMSReceiver extends BroadcastReceiver {
    private final String ACTION_RECEIVE_SMS = "android.provider.Telephony.Sms.Intents.SMS_RECEIVED_ACTION";

    @Override
    public void onReceive(Context context, Intent intent) {
        String s = android.provider.Telephony.Sms.Intents.SMS_RECEIVED_ACTION;
        Toast.makeText(context, intent.getAction(), Toast.LENGTH_SHORT).show();
        if (intent.getAction().equals(ACTION_RECEIVE_SMS)) {
            Toast.makeText(context, "SMS received! (1)", Toast.LENGTH_SHORT).show();
        }
        if (intent.getAction().equals(s)) {
            Toast.makeText(context, "SMS received! (2)", Toast.LENGTH_SHORT).show();
        }
        if (intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
            Toast.makeText(context, "SMS received! (3)", Toast.LENGTH_SHORT).show();
        }
    }
}
