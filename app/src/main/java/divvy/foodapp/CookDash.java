package divvy.foodapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CookDash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_dash);
    }



    public void createDisk(View v) {
        startActivity(new Intent(CookDash.this, CreateDish.class));
    }
}
