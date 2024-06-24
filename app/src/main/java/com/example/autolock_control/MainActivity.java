package com.example.autolock_control;

import androidx.appcompat.app.AppCompatActivity;
import io.socket.client.IO;
import io.socket.client.Socket;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {
    private Socket mSocket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            mSocket = IO.socket("https://south-bright-suggestion.glitch.me");
            mSocket.connect();
        }catch (URISyntaxException e){
            e.printStackTrace();
        }

        Button lockButton = findViewById(R.id.lockButton);
        Button unlockButton = findViewById(R.id.unlockButton);

        lockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSignal("lock");
            }
        });
        unlockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSignal("unlock");
            }
        });
    }

    private void sendSignal(String lock) {
        mSocket.emit("control",lock);
    }
}