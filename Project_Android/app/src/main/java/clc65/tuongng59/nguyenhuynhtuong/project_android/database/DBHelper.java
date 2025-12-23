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


    public ArrayList<String> getAllQuestionText() {
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT question FROM QUESTION",
                null
        );

        if (cursor.moveToFirst()) {
            do {
                String question = cursor.getString(0);
                list.add(question);
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

}
