package com.example.videocurator.videocurator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

public class UserDetail extends AppCompatActivity {

    EditText editgender,editemail;
    ImageButton userimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        editemail = findViewById(R.id.useremail);
        editgender = findViewById(R.id.usergender);
        userimage = findViewById(R.id.userimage);

    }
}
