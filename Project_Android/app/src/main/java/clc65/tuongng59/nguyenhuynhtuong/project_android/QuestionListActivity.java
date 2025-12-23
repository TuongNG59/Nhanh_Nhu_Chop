package clc65.tuongng59.nguyenhuynhtuong.project_android;

import android.content.Intent;
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

public class QuestionListActivity extends AppCompatActivity {

    ListView lvQuestion;
    Button btnAdd;
    ArrayList<String> questionList;
    ArrayAdapter<String> adapter;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);

        lvQuestion = findViewById(R.id.lvQuestion);
        btnAdd = findViewById(R.id.btnAdd);

        dbHelper = new DBHelper(this);

        questionList = dbHelper.getAllQuestionText();

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                questionList
        );
        lvQuestion.setAdapter(adapter);

        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(QuestionListActivity.this, AddQuestionActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        questionList.clear();
        questionList.addAll(dbHelper.getAllQuestionText());
        adapter.notifyDataSetChanged();
    }
}