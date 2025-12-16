package clc65.tuongng59.nguyenhuynhtuong.project_android.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORE);
        onCreate(db);
    }
}
