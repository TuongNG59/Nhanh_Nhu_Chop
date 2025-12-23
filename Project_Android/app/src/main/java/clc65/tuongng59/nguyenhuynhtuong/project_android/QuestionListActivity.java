package clc65.tuongng59.nguyenhuynhtuong.project_android;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import clc65.tuongng59.nguyenhuynhtuong.project_android.database.DBHelper;

public class QuestionListActivity extends AppCompatActivity {

    ListView lvQuestion;
    Button btnAdd;
    ArrayList<Question> questionList;
    ArrayAdapter<Question> adapter;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);

        lvQuestion = findViewById(R.id.lvQuestion);
        btnAdd = findViewById(R.id.btnAdd);

        dbHelper = new DBHelper(this);

        questionList = dbHelper.getAllQuestions();

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

        //Bắt sự kiện long click
        lvQuestion.setOnItemLongClickListener((parent, view, position, id) -> {

            Question q = questionList.get(position);

            String[] options = {"Sửa", "Xóa"};

            AlertDialog.Builder builder = new AlertDialog.Builder(QuestionListActivity.this);
            builder.setTitle("Chọn thao tác");
            builder.setItems(options, (dialog, which) -> {

                if (which == 1) { // XÓA
                    deleteQuestion(q.id);
                }

                if (which == 0) { // SỬA
                    Intent intent = new Intent(
                            QuestionListActivity.this,
                            AddQuestionActivity.class
                    );
                    intent.putExtra("QUESTION_ID", q.id);
                    startActivity(intent);
                }
            });

            builder.show();
            return true;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        questionList.clear();
        questionList.addAll(dbHelper.getAllQuestions());
        adapter.notifyDataSetChanged();
    }


    void deleteQuestion(int id) {
        DBHelper dbHelper = new DBHelper(this);
        dbHelper.deleteQuestion(id);

        questionList.clear();
        questionList.addAll(dbHelper.getAllQuestions());
        adapter.notifyDataSetChanged();
    }

}