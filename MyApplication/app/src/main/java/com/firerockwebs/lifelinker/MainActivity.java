package com.firerockwebs.lifelinker;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private final String TAG = "lifelinker.MainActivity";
    ActionBar mActionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mActionBar = getSupportActionBar();
        //mActionBar.hide();


        //RelativeLayout layout = (RelativeLayout) findViewById(R.id.top_view);

        /*layout.setOnTouchListener(new View.OnTouchListener() {
            float x1, x2;
            float y1, y2;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getRawX();
                        y1 = event.getRawY();
                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = event.getRawX();
                        y2 = event.getRawY();
                        // if UP to Down sweep event on screen
                        if (y1 < y2)
                        {
                            mActionBar.show();
                        }
                        break;
                    default:
                        break;
                }
                return false;
            }
        });*/
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
            case R.id.new_game:
                LinearLayout layout = (LinearLayout) findViewById(R.id.player_container);
                int childCount = layout.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    LinearLayout view = (LinearLayout) layout.getChildAt(i);
                    int newChildCount = view.getChildCount();
                    for (int j = 0; j < newChildCount; j++) {
                        View childsView = view.getChildAt(j);
                        if (childsView.getTag() != null && childsView.getTag().equals("life")) {
                            TextView textView = (TextView) childsView;
                            textView.setText(getResources().getString(R.string.life_count));
                        }
                    }
                }
                return true;
            case R.id.three_players:
                setContentView(R.layout.three_player_layout);
                return true;
            case R.id.two_players:
                setContentView(R.layout.activity_main);
                return true;
            case R.id.two_headed_giant:
                int currentID = getWindow().getDecorView().getId();
                if (currentID != R.layout.activity_main)
                    setContentView(R.layout.activity_main);

                TextView life = (TextView) findViewById(R.id.player_one_life);
                life.setText(getResources().getString(R.string.giant_count));
                life = (TextView) findViewById(R.id.player_two_life);
                life.setText(getResources().getString(R.string.giant_count));
                return true;
            case R.id.four_players:
                setContentView(R.layout.four_player_layout);
                return true;
            default:
                return false;
        }
    }

    public void plusClicked(View view) {
        String playerLife = (String) view.getTag();
        int id = getResources().getIdentifier(playerLife, "id", this.getPackageName());
        TextView life = (TextView) findViewById(id);

        int lifeInt = Integer.parseInt(life.getText().toString());
        lifeInt += 1;
        life.setText(Integer.toString(lifeInt));
        //mActionBar.hide();
    }

    public void minusClicked(View view) {
        String playerLife = (String) view.getTag();
        int id = getResources().getIdentifier(playerLife, "id", this.getPackageName());
        TextView life = (TextView) findViewById(id);
        int lifeInt = Integer.parseInt(life.getText().toString());
        lifeInt -= 1;
        life.setText(Integer.toString(lifeInt));
        //mActionBar.hide();
    }

    public void changeName(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Change your name");
        final TextView textView = (TextView) view;
        // Set up the input
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String text = input.getText().toString();
                if (!text.equals("")) {
                    textView.setText(text);
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builder.show();
    }
}
