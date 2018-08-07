package com.example.arrive_at_click;

import android.content.Intent;
import android.os.Build;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;

public class LoginPage extends AppCompatActivity {

    private PopupWindow mPopupWindow;
    private RelativeLayout mRelativeLayout;
    private EditText pass;
    public static int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        pass = (EditText)findViewById(R.id.etPass);

        // Get the widgets reference from XML layout
        mRelativeLayout = (RelativeLayout)findViewById(R.id.rl);

        //set bttnLogin listener
        ImageButton bttnLogin = (ImageButton)findViewById(R.id.bttnLogin);
        bttnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickLogin(v);
            }
        });
    }

    public void OnClickLogin(View v)
    {
        count++;

        if (pass.getText().toString().equals("AdminOnly"))
        {
            Intent i = new Intent(this,AdminPage.class);
            startActivity(i);
        }
        else if(count < 3)
        {
            // Initialize a new instance of LayoutInflater service
            LayoutInflater inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);

            // Inflate the custom layout/view
            View customView = inflater.inflate(R.layout.popup_message,null);

            // Initialize a new instance of popup window
            mPopupWindow = new PopupWindow(customView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

            // Set an elevation value for popup window
            // Call requires API level 27
            if(Build.VERSION.SDK_INT>=27){
                mPopupWindow.setElevation(10);
            }

            // Get a reference for the custom view close button
            ImageButton closeButton = (ImageButton)customView.findViewById(R.id.ib_close);

            // Set a click listener for the popup window close button
            closeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Dismiss the popup window
                    mPopupWindow.dismiss();
                    pass.setText("");
                }
            });

            mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER,0,0);

        }
        else
            this.finish();

    }
}
