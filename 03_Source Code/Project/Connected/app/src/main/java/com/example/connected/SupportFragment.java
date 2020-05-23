package com.example.connected;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SupportFragment extends Fragment {

    Button send;
    EditText email, message;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_support, container, false);

        send = rootView.findViewById(R.id.send_btn);
        email = rootView.findViewById(R.id.email);
        message = rootView.findViewById(R.id.feedback);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject = "Feedback";
                String sMessage = message.getText().toString();
                String sEmail = email.getText().toString();

                Intent send = new Intent(Intent.ACTION_SEND);
                send.putExtra(Intent.EXTRA_EMAIL, sEmail);
                send.putExtra(Intent.EXTRA_SUBJECT, subject);
                send.putExtra(Intent.EXTRA_TEXT, sMessage);
                send.setType("message/rfc822");
                send.setPackage("com.google.android.gm");
                startActivity(send);
            }
        });
        return rootView;
    }
}
