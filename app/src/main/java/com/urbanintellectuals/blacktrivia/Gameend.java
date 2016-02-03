package com.urbanintellectuals.blacktrivia;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Gameend extends AppCompatActivity {

    TextView winner, score;
    Button again;
    Player p1 = Gameplay.p1;
    Player p2 = Gameplay.p2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameend);

        winner = (TextView) findViewById(R.id.winnerIs);
        score = (TextView) findViewById(R.id.scoreIs);

        again = (Button) findViewById(R.id.restart);

        again.setOnClickListener(res);

        if (p1.getIsWinner()) winner.setText(p1.getName() + " wins!");
        if (p2.getIsWinner()) winner.setText(p2.getName() + " wins!");
        score.setText("Final Score: " + p1.getScore() + " - " + p2.getScore());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gameplay, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private View.OnClickListener res = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent i = new Intent(Gameend.this, Gameplay.class);
            startActivity(i);
            finish();
        }
    };

}
