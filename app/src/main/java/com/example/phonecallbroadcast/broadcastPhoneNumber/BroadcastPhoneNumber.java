package com.example.phonecallbroadcast.broadcastPhoneNumber;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.telephony.PhoneStateListener;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class BroadcastPhoneNumber extends BroadcastReceiver {

    public String phoneNumber = "";
    public String message = "Hardcoded Message";
    String phoneNumberRegistered;
    String messageToPhoneNumber;

    /*public BroadcastPhoneNumber(String phoneNumberToRegister, String message){
        phoneNumberRegistered = phoneNumberToRegister;
        messageToPhoneNumber = message;
    }*/

    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
            phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            Toast.makeText(context.getApplicationContext(), "Llamada iniciando", Toast.LENGTH_SHORT).show();
        }
        else{

            TelephonyManager telephony = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
            telephony.listen(new PhoneStateListener(){
                @Override
                public void onCallStateChanged(int state, String incomingNumber) {
                    super.onCallStateChanged(state, incomingNumber);
                    System.out.println("incomingNumber : "+ incomingNumber);
                    Toast.makeText(context.getApplicationContext(), "incomingNumber : "+incomingNumber, Toast.LENGTH_SHORT).show();
                    phoneNumber = incomingNumber;
                    if(state == TelephonyManager.CALL_STATE_RINGING){
                        sendMessage();
                    }
                    
                }
            },PhoneStateListener.LISTEN_CALL_STATE);

            /*TelephonyManager telMang=(TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
            switch (telMang.getCallState()){
                case TelephonyManager.CALL_STATE_RINGING:{
                    //phoneNumber=intent.getStringExtra("incoming_number");

                    Toast.makeText(context.getApplicationContext(), "llamada entrante: "+ phoneNumber, Toast.LENGTH_SHORT).show();
                    break;
                }

                case TelephonyManager.CALL_STATE_OFFHOOK:{

                    break;
                }

                case TelephonyManager.CALL_STATE_IDLE:{

                    break;
                }

                default:
                    break;
            }*/
        }

    }

    private void sendMessage(){
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber,
                null,
                message,
                null, null);
    }

}
