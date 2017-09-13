package divvy.foodapp;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import divvy.foodapp.background.ServerCommunication;

public class Registration extends AppCompatActivity {

    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        username = (EditText) findViewById(R.id.txtUsername);
        password = (EditText) findViewById(R.id.txtPassword);

    }

    public void register(View v) {
        String usernameS = username.getText().toString();
        String passwordS = password.getText().toString();

        try {
            if (!ServerCommunication.checkUser(usernameS)) {
                ServerCommunication.register(usernameS,passwordS);
                showMessage("Congrats! You are now registered");
            }else {
                showMessage("Sorry, that username already exsists!");
            }
        }catch(Exception e) {
            Log.e("TEST",Log.getStackTraceString(e));
        }
    }

    public void back(View v) {
        onBackPressed();
    }


    public void showMessage(String message) {
        Snackbar snack = Snackbar.make(getWindow().getDecorView().getRootView(), message, Snackbar.LENGTH_SHORT);
        View view = snack.getView();
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
        params.gravity = Gravity.CENTER;
        view.setLayoutParams(params);
        snack.show();
    }
}
