package com.example.youtubeplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = findViewById(R.id.input);
    }

    public void showVideo(View view) {
        String videoUrl = input.getText().toString();

        Intent intent = new Intent(this, VideoPlayerActivity.class);
        intent.putExtra("VIDEO_URL", videoUrl);
        startActivity(intent);
    }
}