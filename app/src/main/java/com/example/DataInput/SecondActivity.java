package com.example.DataInput;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.lab02.R;

import java.util.List;

public class SecondActivity extends AppCompatActivity {
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String studentId = bundle.getString("firstName");
            String fullName = bundle.getString("lastName");
            String id = bundle.getString("birthday");
            String phone = bundle.getString("address");
            String email = bundle.getString("email");
            String date = bundle.getString("date");
            text = findViewById(R.id.text);
            text.setText("firstName: "+ studentId + "\n"
                        + " lastName: " + fullName + "/\n"
                        + " birthday: " + id + "\n"
                        + " address: " + phone + "\n"
                        + " email: " + email + "\n"
                        + " date: " + date);
        }
    }
}
