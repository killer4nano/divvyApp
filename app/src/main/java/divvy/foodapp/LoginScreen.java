package divvy.foodapp;

import android.content.Intent;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import divvy.foodapp.background.ServerCommunication;

public class LoginScreen extends AppCompatActivity {

    private EditText username;
    private EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        username = (EditText) findViewById(R.id.txtUsername);
        password = (EditText) findViewById(R.id.txtPassword);

    }


    public void login(View v) {
        String usernameS;
        String passwordS;

        usernameS = username.getText().toString();
        passwordS = password.getText().toString();


        try {
            if (ServerCommunication.login(usernameS, passwordS)) {
                startActivity(new Intent(LoginScreen.this, HomeScreen.class));
            } else {
                showMessage("Wrong password or username!");
            }
        }catch (Exception e) {
            Log.e("TEST",e.getMessage());
            showMessage("Some stupid error! try again!");
        }

    }

    public void register(View v) {
        startActivity(new Intent(LoginScreen.this, Registration.class));
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
