package clc65.tuongng59.nguyenhuynhtuong.project_android;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import clc65.tuongng59.nguyenhuynhtuong.project_android.database.DBHelper;

public class PlayActivity extends AppCompatActivity {

    TextView tvScore, tvTimer, tvQuestion;
    Button btnA, btnB, btnC, btnD;

    //Bộ đếm thời gian
    CountDownTimer countDownTimer;
    int timeLeft = 60;


    //Tính điểm
    int score = 0;
    String correctAnswer = "A";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        tvScore = findViewById(R.id.tvScore);
        tvTimer = findViewById(R.id.tvTimer);
        tvQuestion = findViewById(R.id.tvQuestion);

        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);
        btnC = findViewById(R.id.btnC);
        btnD = findViewById(R.id.btnD);


        //Dữ liệu giả để test
        tvQuestion.setText("Con gì kêu meo meo?");
        btnA.setText("Con mèo");
        btnB.setText("Con chó");
        btnC.setText("Con trâu");
        btnD.setText("Con vịt");

        startTimer();


        btnA.setOnClickListener(v -> checkAnswer("A"));
        btnB.setOnClickListener(v -> checkAnswer("B"));
        btnC.setOnClickListener(v -> checkAnswer("C"));
        btnD.setOnClickListener(v -> checkAnswer("D"));


        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.close();
    }

    void startTimer() {
        countDownTimer = new CountDownTimer(60000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = (int) (millisUntilFinished / 1000);
                tvTimer.setText("Time: " + timeLeft + "s");
            }

            @Override
            public void onFinish() {
                tvTimer.setText("Time: 0s");
                endGame();
            }
        }.start();
    }


    void endGame() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        Intent intent = new Intent(PlayActivity.this, ResultActivity.class);
        intent.putExtra("SCORE", score);
        startActivity(intent);
        finish();
    }


    void checkAnswer(String selectedAnswer) {
        if (selectedAnswer.equals(correctAnswer)) {
            score++;
            tvScore.setText("Score: " + score);
            loadNextQuestion();
        } else {
            endGame();
        }
    }


    void loadNextQuestion() {
        tvQuestion.setText("Con gì kêu gâu gâu?");
        btnA.setText("Con mèo");
        btnB.setText("Con chó");
        btnC.setText("Con trâu");
        btnD.setText("Con vịt");

        correctAnswer = "B";
    }

}