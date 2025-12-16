package clc65.tuongng59.nguyenhuynhtuong.project_android;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import clc65.tuongng59.nguyenhuynhtuong.project_android.database.DBHelper;

public class MainActivity extends AppCompatActivity {

    Button btnPlay, btnManage, btnScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = findViewById(R.id.btnPlay);
        btnManage = findViewById(R.id.btnManage);
        btnScore = findViewById(R.id.btnScore);

        //  Chuyển qua màn hình chơi
        btnPlay.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PlayActivity.class);
            startActivity(intent);
        });


       //   Chuyển qua màn hình quản lý câu hỏi
        btnManage.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, QuestionListActivity.class);
            startActivity(intent);
        });

        //  Chuyển qua màn hình điểm
        btnScore.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ScoreActivity.class);
            startActivity(intent);
        });


        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.close();
    }
}