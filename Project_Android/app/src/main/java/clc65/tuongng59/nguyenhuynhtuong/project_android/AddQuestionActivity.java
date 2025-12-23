package clc65.tuongng59.nguyenhuynhtuong.project_android;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import clc65.tuongng59.nguyenhuynhtuong.project_android.database.DBHelper;

public class AddQuestionActivity extends AppCompatActivity {

    EditText edtQuestion, edtA, edtB, edtC, edtD;
    RadioGroup rgCorrect;
    Button btnSave;

    int questionId = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        edtQuestion = findViewById(R.id.edtQuestion);
        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        edtC = findViewById(R.id.edtC);
        edtD = findViewById(R.id.edtD);
        rgCorrect = findViewById(R.id.rgCorrect);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> saveQuestion());

        questionId = getIntent().getIntExtra("QUESTION_ID", -1);

        if (questionId != -1) {
            DBHelper dbHelper = new DBHelper(this);
            Question q = dbHelper.getQuestionById(questionId);

            if (q != null) {
                edtQuestion.setText(q.question);
            }
        }
    }


    void saveQuestion() {
        String question = edtQuestion.getText().toString().trim();
        String a = edtA.getText().toString().trim();
        String b = edtB.getText().toString().trim();
        String c = edtC.getText().toString().trim();
        String d = edtD.getText().toString().trim();

        if (question.isEmpty() || a.isEmpty() || b.isEmpty()
                || c.isEmpty() || d.isEmpty() || rgCorrect.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton rb = findViewById(rgCorrect.getCheckedRadioButtonId());
        String correct = rb.getText().toString();

        DBHelper dbHelper = new DBHelper(this);
        if (questionId == -1) {
            dbHelper.insertQuestion(question, a, b, c, d, correct);
        } else {
            dbHelper.updateQuestion(questionId, question, a, b, c, d, correct);
        }
        Toast.makeText(this, "Đã lưu câu hỏi", Toast.LENGTH_SHORT).show();
        finish();
    }
}