package com.example.escbasicappr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ImageButton addContact;
    private ImageButton contact;
    private TextView phoneNum;
    private TextView[] dials = new TextView[10];
    private TextView star;
    private TextView sharp;
    private ImageButton message;
    private ImageButton call;
    private ImageButton backspace;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setUpUI();
    }
    private void setUpUI(){
        addContact = findViewById(R.id.main_ibtn_add);
        contact = findViewById(R.id.main_ibtn_contact);
        phoneNum = findViewById(R.id.main_tv_phone);

        for (int i = 0; i < dials.length; i++) {
            dials[i] = findViewById(getResourceID("main_tv_"+i, "id", this));
        }

        star = findViewById(R.id.main_tv_star);
        sharp = findViewById(R.id.main_tv_sharp);
        message = findViewById(R.id.main_ibtn_message);
        call = findViewById(R.id.main_ibtn_call);
        backspace = findViewById(R.id.main_ibtn_backspace);

        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 연락처 추가
                Toast.makeText(MainActivity.this, "text", Toast.LENGTH_SHORT).show();
            }
        });

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 연락처
            }
        });

        setOnClickDial(star, "*");
        setOnClickDial(sharp, "#");

        for(int i = 0; i<10; i++){
            setOnClickDial(dials[i], String.valueOf(i));
        }

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 메시지
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 전화
            }
        });

        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if( phoneNum.length() > 0) {
                    String formatPhoneNum = PhoneNumberUtils.formatNumber(phoneNum.getText().subSequence(0, phoneNum.length() - 1).toString(), Locale.getDefault().getCountry());
                    phoneNum.setText(formatPhoneNum);*/
                phoneNum.setText(changeToDial(phoneNum.getText().subSequence(0, phoneNum.getText().length() - 1).toString()));
                }

        });

    }

    private void setOnClickDial(View view, final String input){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String formatPhoneNum = PhoneNumberUtils.formatNumber(phoneNum.getText()+input, Locale.getDefault().getCountry());
                phoneNum.setText(changeToDial(phoneNum.getText() + input));
            }
        });
    }

    private int getResourceID(final String resName, final String resType, final Context ctx){
        final int ResourceID =
                ctx.getResources().getIdentifier(resName, resType, ctx.getApplicationInfo().packageName);
        if(ResourceID == 0) {
            throw new IllegalArgumentException("No resource string found with name " + resName);
        } else{
            return ResourceID;
        }
    }
    private String changeToDial(String phoneNum){
        StringBuffer origin = new StringBuffer(phoneNum);
        if(!(phoneNum.contains("*") || phoneNum.contains("#"))) {
            if (phoneNum.length() >= 4) {
                if (!(phoneNum.charAt(3) == '-')) {
                    origin.insert(3, "-");
                }
            }
            if (phoneNum.length() >= 9) {
                if (!(phoneNum.charAt(8) == '-')) {
                    origin.insert(8, "-");
                }
            }
            if (phoneNum.length() >= 14) {
                origin.deleteCharAt(3);
                origin.deleteCharAt(7);
            }
        }
        else{
            if(phoneNum.charAt(3) == '-'){
                origin.deleteCharAt(3);
            }
            if(phoneNum.charAt(8) == '-'){
                origin.deleteCharAt(7);
            }
        }

        return origin.toString();

    }
}