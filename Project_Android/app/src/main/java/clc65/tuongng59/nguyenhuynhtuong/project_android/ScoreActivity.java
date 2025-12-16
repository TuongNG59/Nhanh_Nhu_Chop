package clc65.tuongng59.nguyenhuynhtuong.project_android;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import clc65.tuongng59.nguyenhuynhtuong.project_android.database.DBHelper;

public class ScoreActivity extends AppCompatActivity {

    ListView lvScore;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        lvScore = findViewById(R.id.lvScore);
        btnBack = findViewById(R.id.btnBack);

        DBHelper dbHelper = new DBHelper(this);
        ArrayList<String> scoreList = dbHelper.getAllScores();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                scoreList
        );

        lvScore.setAdapter(adapter);


        btnBack.setOnClickListener(v -> {
            finish();
        });
    }
}