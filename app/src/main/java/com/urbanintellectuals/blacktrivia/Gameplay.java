package com.urbanintellectuals.blacktrivia;

import android.content.Intent;
import android.graphics.Color;
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/*TODO: Implement the baby database into the system
* TODO: Randomize question selection
* TODO: Add a timer
* TODO: Implement answering until wrong system
* TODO: Release v1.0.0
* TODO: Create username and local wireless gameplay (Multiple Devices)
* TODO: Release v1.1.0
* TODO: Upgrade from local to WFC
* TODO: Take database to the cloud with the username and statistics systems
* TODO: Apply all graphical changes
* TODO: Release v2.0.0*/

public class Gameplay extends AppCompatActivity {

    TextView p1name, p2name, p1score, p2score, round, question;
    Button answer1, answer2, answer3, answer4, winner, nextQ;
    static Player p1 = new Player();
    static Player p2 = new Player();
    boolean answered, correct;
    int questionNum = 2;
    int roundNum = questionNum / 2;
    int a;
    Timer delay = new Timer();
    TimerTask tt;
    String[][] questions = new String[5][6];
    ArrayList q1 = new ArrayList();
    ArrayList q2 = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gameplay);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        p1name = (TextView) findViewById(R.id.p1Name);
        p2name = (TextView) findViewById(R.id.p2Name);
        p1score = (TextView) findViewById(R.id.p1Score);
        p2score = (TextView) findViewById(R.id.p2Score);
        round = (TextView) findViewById(R.id.roundNum);
        question = (TextView) findViewById(R.id.question);

        answer1 = (Button) findViewById(R.id.answer1);
        answer2 = (Button) findViewById(R.id.answer2);
        answer3 = (Button) findViewById(R.id.answer3);
        answer4 = (Button) findViewById(R.id.answer4);
        winner = (Button) findViewById(R.id.winnerText);
        nextQ = (Button) findViewById(R.id.nextQ);

        answer1.setOnClickListener(ans);
        answer2.setOnClickListener(ans);
        answer3.setOnClickListener(ans);
        answer4.setOnClickListener(ans);
        // winner.setOnClickListener(done);
        nextQ.setOnClickListener(next);

        p1name.setText(p1.getName());
        p2name.setText(p2.getName());
        p1score.setText("" + p1.getScore());
        p2score.setText("" + p2.getScore());
        round.setText("Round " + roundNum);
        winner.setVisibility(View.INVISIBLE);

        q1.add("What is the color of the sky?");
        q1.add("Light Blue");
        q1.add("Red");
        q1.add("Orange");
        q1.add("Navy Blue");
        q1.add("Light Blue");
        q1.toArray(questions[0]);

        q2.add("What city is known as The Windy City?");
        q2.add("Los Angeles");
        q2.add("Chicago");
        q2.add("New York");
        q2.add("Detroit");
        q2.add("Chicago");
        q2.toArray(questions[1]);

        question.setText(questions[0][0]);
        answer1.setText(questions[0][1]);
        answer2.setText(questions[0][2]);
        answer3.setText(questions[0][3]);
        answer4.setText(questions[0][4]);

        a = (int) (Math.random()*2);

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

    public void gameOver(View v){
        Intent i = new Intent(this, Gameend.class);
        startActivity(i);
        finish();
    }
    private View.OnClickListener done = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(Gameplay.this, Gameend.class);
            startActivity(i);
        }
    };
    private View.OnClickListener next = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            if (answered) {
                answer1.setBackgroundColor(Color.LTGRAY);
                answer2.setBackgroundColor(Color.LTGRAY);
                answer3.setBackgroundColor(Color.LTGRAY);
                answer4.setBackgroundColor(Color.LTGRAY);
                nextQ.setVisibility(View.GONE);
                answered = false;
            }
        }
    };
    private View.OnClickListener ans = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!answered){
                answered = true;
                TextView tv = null;
                switch (v.getId()) {
                    case R.id.answer1:
                        tv = (TextView) findViewById(R.id.answer1);
                        if (tv.getText().toString().equals(questions[0][5])) correct = true;
                        else correct = false;
                        break;
                    case R.id.answer2:
                        tv = (TextView) findViewById(R.id.answer2);
                        if (tv.getText().toString().equals(questions[0][5])) correct = true;
                        else correct = false;
                        break;
                    case R.id.answer3:
                        tv = (TextView) findViewById(R.id.answer3);
                        if (tv.getText().toString().equals(questions[0][5])) correct = true;
                        else correct = false;
                        break;
                    case R.id.answer4:
                        tv = (TextView) findViewById(R.id.answer4);
                        if (tv.getText().toString().equals(questions[0][5])) correct = true;
                        else correct = false;
                        break;
                    default:
                        break;
                }
                if (correct) {
                    tv.setBackgroundColor(Color.GREEN);
                    nextQ.setText("Correct! Next Question");
                } else {
                    tv.setBackgroundColor(Color.RED);
                    if (answer1.getText().toString().equals(questions[0][5])) answer1.setBackgroundColor(Color.GREEN);
                    if (answer2.getText().toString().equals(questions[0][5])) answer2.setBackgroundColor(Color.GREEN);
                    if (answer3.getText().toString().equals(questions[0][5])) answer3.setBackgroundColor(Color.GREEN);
                    if (answer4.getText().toString().equals(questions[0][5])) answer4.setBackgroundColor(Color.GREEN);
                    nextQ.setText("Wrong! Next Question");
                }
                nextQ.setVisibility(View.VISIBLE);
                int turn = questionNum % 2;
                if (turn == 0 && correct) {
                    p1.addScore();
                    p1.setWinner();
                }
                if (turn == 1 && correct) {
                    p2.addScore();
                    p2.setWinner();
                }
                p1score.setText("" + p1.getScore());
                p2score.setText("" + p2.getScore());
                if (p1.getIsWinner()) {
                    winner.setVisibility(View.VISIBLE);
                }
                if (p2.getIsWinner()) {
                    winner.setVisibility(View.VISIBLE);
                }
                questionNum++;
                roundNum = questionNum / 2;
                round.setText("Round " + roundNum);
            }
        }
    };
}
