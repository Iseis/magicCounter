package com.firerockwebs.lifelinker;

import android.content.Context;
import android.content.Intent;
import android.hardware.input.InputManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "lifelinker.MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_options, menu);
        getWindow().addFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.to_bluetooth:
                Intent intent = new Intent(this, BluToothActivity.class);
                startActivity(intent);
                return true;
            default:
                return false;
        }
    }

    public void plusClicked(View view) {
        String playerLife = (String) view.getTag();
        Log.i(TAG, playerLife);
    }

    public void minusClicked(View view) {
        String playerLife = (String) view.getTag();
        Log.i(TAG, playerLife);
    }

    public void changeName(View view) {

    }
}
