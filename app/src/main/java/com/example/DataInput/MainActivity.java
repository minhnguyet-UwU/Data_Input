package com.example.DataInput;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.lab02.R;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    DatePickerDialog datePickerDialog;
    Button date, bRegister;
    EditText firstName, lastName, birthday, address, email;
    CheckBox accept;
    RadioButton male, female;

    // one boolean variable to check whether all the text fields
    // are filled by the user, properly or not.
    boolean isAllFieldsChecked = false;

    private String firstName1, lastName1, birthday1, address1, email1, major1, date1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDatePicker();
        date = findViewById(R.id.dateSelect);

        // register buttons with their proper IDs.
        bRegister = findViewById(R.id.registerButton);

        // register all the EditText fields with their IDs.
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        birthday = findViewById(R.id.birthday);
        address = findViewById(R.id.address);
        email = findViewById(R.id.email);

        accept = findViewById(R.id.accept);

        male = findViewById(R.id.male);
        female = findViewById(R.id.female);

//        male.setChecked(true);
        male.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doOnMajorChanged(buttonView,isChecked);
            }
        });
        female.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doOnMajorChanged(buttonView,isChecked);
            }
        });

        if(accept.isChecked()){
            bRegister.setEnabled(true);
            bRegister.setBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimary));
        }

        accept.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    bRegister.setEnabled(true);
                    bRegister.setBackgroundColor(ContextCompat.getColor(compoundButton.getContext(), R.color.colorPrimary));
                }else {
                    bRegister.setEnabled(false);
                    bRegister.setBackgroundColor(ContextCompat.getColor(compoundButton.getContext(), R.color.colorPrimaryDisable));
                }
            }
        });

        // handle the PROCEED button
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // store the returned value of the dedicated function which checks
                // whether the entered data is valid or if any fields are left blank.
                isAllFieldsChecked = CheckAllFields();

                // the boolean variable turns to be true then
                // only the user must be proceed to the activity2
                if (isAllFieldsChecked) {
                    Bundle bundle = new Bundle();
                    bundle.putString("firstName", firstName1);
                    bundle.putString("lastName", lastName1);
                    bundle.putString("birthday", birthday1);
                    bundle.putString("address", address1);
                    bundle.putString("email", email1);
                    bundle.putString("major", major1);
                    bundle.putString("date", date1);
                    Intent i = new Intent(MainActivity.this, SecondActivity.class);
                    i.putExtras(bundle);
                    startActivity(i);
                }
            }
        });

    }

    private void doOnMajorChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            RadioButton radio = (RadioButton) buttonView;
            major1 = radio.getText().toString();
        }
    }


    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1 +1;
                String dateTime = makeDateString(i, i1,i2);
                birthday.setText(dateTime);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        month = month + 1;

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    private String makeDateString(int i, int i1, int i2) {
        return i2 + "/" + i1 + "/" + i;
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

    // function which checks all the text fields
    // are filled or not by the user.
    // when user clicks on the Register button
    // this function is triggered.
    private boolean CheckAllFields() {
        if (firstName.length() == 0) {
            firstName.setError("This field is required");
            return false;
        }

        if (lastName.length() == 0) {
            lastName.setError("This field is required");
            return false;
        }

        if (birthday.length() == 0) {
            birthday.setError("This field is required");
            return false;
        }

        if (address.length() == 0) {
            address.setError("This field is required");
            return false;
        }

        if (email.length() == 0) {
            email.setError("This field is required");
            return false;
        }
        if (date.length() == 0) {
            date.setError("This field is required");
            return false;
        }
        if (major1.length() == 0) {
            date.setError("This field is required");
            return false;
        }
        firstName1 = firstName.getText().toString();
        lastName1 = lastName.getText().toString();
        birthday1 = birthday.getText().toString();
        address1 = address.getText().toString();
        email1 = email.getText().toString();
        date1 = date.getText().toString();

        // after all validation return true.
        return true;
    }


}