package clc65.tuongng59.nguyenhuynhtuong.project_android;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {

    TextView tvFinalScore;
    Button btnPlayAgain, btnBackMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvFinalScore = findViewById(R.id.tvFinalScore);
        btnPlayAgain = findViewById(R.id.btnPlayAgain);
        btnBackMenu = findViewById(R.id.btnBackMenu);


        // Tạm thời set score giả
        tvFinalScore.setText("Score: 0");

        btnPlayAgain.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, PlayActivity.class);
            startActivity(intent);
            finish();
        });

        btnBackMenu.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });


        // Nhận dữ liệu từ PlayActivity
        int finalScore = getIntent().getIntExtra("SCORE", 0);

        // Hiển thị điểm
        tvFinalScore.setText("Score: " + finalScore);

    }
}