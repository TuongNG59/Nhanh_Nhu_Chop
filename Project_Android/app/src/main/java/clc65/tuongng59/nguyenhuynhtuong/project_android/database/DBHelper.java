package clc65.tuongng59.nguyenhuynhtuong.project_android.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.Nullable;

import clc65.tuongng59.nguyenhuynhtuong.project_android.Question;

public class DBHelper extends SQLiteOpenHelper {

    // Tên DB & version
    private static final String DB_NAME = "score.db";
    private static final int DB_VERSION = 1;

    // Tên bảng
    public static final String TABLE_SCORE = "SCORE";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateTable =
                "CREATE TABLE " + TABLE_SCORE + " (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "score INTEGER, " +
                        "play_time TEXT" +
                        ")";
        db.execSQL(sqlCreateTable);


        String sqlQuestion =
                "CREATE TABLE IF NOT EXISTS QUESTION (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "question TEXT, " +
                        "optionA TEXT, " +
                        "optionB TEXT, " +
                        "optionC TEXT, " +
                        "optionD TEXT, " +
                        "correct TEXT" +
                        ")";
        db.execSQL(sqlQuestion);

        db.execSQL(
                "INSERT INTO QUESTION (question, optionA, optionB, optionC, optionD, correct) VALUES " +
                        "('Android là gì?', 'Hệ điều hành', 'Ngôn ngữ', 'Trình duyệt', 'Phần mềm diệt virus', 'A')," +
                        "('Android Studio dùng để làm gì?', 'Thiết kế web', 'Lập trình Android', 'Soạn thảo văn bản', 'Chơi game', 'B')," +
                        "('Activity trong Android là gì?', 'Cơ sở dữ liệu', 'Một màn hình giao diện', 'File XML', 'Service', 'B')," +
                        "('File nào dùng để thiết kế giao diện?', 'Java', 'XML', 'SQL', 'JSON', 'B')," +
                        "('SQLite là gì?', 'Server', 'API', 'CSDL cục bộ', 'Framework', 'C')," +
                        "('Ngôn ngữ phổ biến để lập trình Android là?', 'C++', 'Java', 'Python', 'PHP', 'B')," +
                        "('AndroidManifest.xml dùng để làm gì?', 'Thiết kế giao diện', 'Khai báo thành phần ứng dụng', 'Lưu dữ liệu', 'Viết logic', 'B')," +
                        "('View dùng để hiển thị văn bản là?', 'Button', 'ImageView', 'TextView', 'ListView', 'C')," +
                        "('ListView dùng để làm gì?', 'Hiển thị ảnh', 'Hiển thị danh sách', 'Nhập dữ liệu', 'Phát nhạc', 'B')," +
                        "('Adapter trong Android dùng để?', 'Kết nối dữ liệu với giao diện', 'Kết nối Internet', 'Quản lý bộ nhớ', 'Tạo database', 'A')," +
                        "('Intent dùng để làm gì?', 'Lưu dữ liệu', 'Chuyển màn hình', 'Thiết kế UI', 'Chạy timer', 'B')," +
                        "('CountDownTimer dùng để?', 'Đếm click', 'Đếm thời gian ngược', 'Đếm câu hỏi', 'Đếm điểm', 'B')," +
                        "('Button là gì?', 'Văn bản', 'Hình ảnh', 'Nút bấm', 'Danh sách', 'C')," +
                        "('XML trong Android chủ yếu dùng để?', 'Viết logic', 'Thiết kế giao diện', 'Lưu điểm', 'Kết nối server', 'B')," +
                        "('SQLiteOpenHelper dùng để?', 'Tạo giao diện', 'Quản lý CSDL SQLite', 'Kết nối mạng', 'Chạy service', 'B')," +
                        "('RadioButton dùng khi nào?', 'Chọn nhiều đáp án', 'Chọn một đáp án', 'Hiển thị ảnh', 'Nhập văn bản', 'B')," +
                        "('Toast dùng để?', 'Hiển thị thông báo nhanh', 'Lưu dữ liệu', 'Chuyển màn hình', 'Chạy game', 'A')," +
                        "('finish() trong Activity dùng để?', 'Tạo Activity', 'Đóng Activity hiện tại', 'Lưu dữ liệu', 'Chạy nền', 'B')," +
                        "('RecyclerView khác ListView ở điểm nào?', 'Không cuộn', 'Tối ưu hiệu năng hơn', 'Không cần adapter', 'Không layout', 'B')," +
                        "('APK là gì?', 'File cài đặt Android', 'File giao diện', 'File database', 'File ảnh', 'A')"
        );
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORE);
        onCreate(db);
    }


    public void insertScore(int score, String playTime) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("score", score);
        values.put("play_time", playTime);

        db.insert(TABLE_SCORE, null, values);
        db.close();
    }


    public ArrayList<String> getAllScores() {
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT score, play_time FROM " + TABLE_SCORE + " ORDER BY id DESC",
                null
        );


        SimpleDateFormat sdf = new SimpleDateFormat(
                "HH:mm - dd/MM/yyyy",
                Locale.getDefault()
        );


        if (cursor.moveToFirst()) {
            do {
                int score = cursor.getInt(0);
                long timeMillis = Long.parseLong(cursor.getString(1));

                String formattedTime = sdf.format(new Date(timeMillis));

                list.add("Score: " + score + " | " + formattedTime);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return list;
    }


    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT id, question FROM QUESTION",
                null
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String question = cursor.getString(1);
                list.add(new Question(id, question));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return list;
    }


    public void insertQuestion(String question, String a, String b, String c, String d, String correct) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("question", question);
        values.put("optionA", a);
        values.put("optionB", b);
        values.put("optionC", c);
        values.put("optionD", d);
        values.put("correct", correct);

        db.insert("QUESTION", null, values);
        db.close();
    }

    //Xóa
    public void deleteQuestion(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("QUESTION", "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    //Sửa
    public Question getQuestionById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT id, question, optionA, optionB, optionC, optionD, correct " +
                        "FROM QUESTION WHERE id = ?",
                new String[]{String.valueOf(id)}
        );

        Question q = null;

        if (cursor.moveToFirst()) {
            q = new Question(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6)
            );
        }

        cursor.close();
        db.close();
        return q;
    }


    public void updateQuestion(int id, String question,
                               String a, String b, String c, String d,
                               String correct) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("question", question);
        values.put("optionA", a);
        values.put("optionB", b);
        values.put("optionC", c);
        values.put("optionD", d);
        values.put("correct", correct);

        db.update(
                "QUESTION",
                values,
                "id = ?",
                new String[]{String.valueOf(id)}
        );

        db.close();
    }

    public Question getRandomQuestion() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT id, question, optionA, optionB, optionC, optionD, correct " +
                        "FROM QUESTION ORDER BY RANDOM() LIMIT 1",
                null
        );


        Question q = null;

        if (cursor.moveToFirst()) {
            q = new Question(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6)
            );
        }

        cursor.close();
        db.close();
        return q;
    }

}
