package tw.edu.pu.s1081735.eatdinner;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SqlDataBaseHelper extends SQLiteOpenHelper {

    private static final int DataBaseVersion = 1;
    private static final String DataBaseName = "eatdinner";
    private static String DataBaseTable = "gamer";

    public SqlDataBaseHelper(Context context) {
        super(context, DataBaseName, null, DataBaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL = "CREATE TABLE IF NOT EXISTS " + DataBaseTable + "( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT DEFAULT 1, " +
                "_score VARCHAR(10) DEFAULT 0," +
                "_f1 tinyint(1) DEFAULT 0,"+
                "_f2 tinyint(1) DEFAULT 0,"+
                "_f3 tinyint(1) DEFAULT 0,"+
                "_f4 tinyint(1) DEFAULT 0,"+
                "_f5 tinyint(1) DEFAULT 0,"+
                "_f6 tinyint(1) DEFAULT 0,"+
                "_f7 tinyint(1) DEFAULT 0,"+
                "_f8 tinyint(1) DEFAULT 0,"+
                "_f9 tinyint(1) DEFAULT 0,"+
                "_f10 tinyint(1) DEFAULT 0,"+
                "_f11 tinyint(1) DEFAULT 0,"+
                "_f12 tinyint(1) DEFAULT 0,"+
                "_f13 tinyint(1) DEFAULT 0,"+
                "_f14 tinyint(1) DEFAULT 0,"+
                "_f15 tinyint(1) DEFAULT 0,"+
                "_f16 tinyint(1) DEFAULT 0,"+
                "_f17 tinyint(1) DEFAULT 0,"+
                "_f18 tinyint(1) DEFAULT 0,"+
                "_f19 tinyint(1) DEFAULT 0,"+
                "_f20 tinyint(1) DEFAULT 0"+
                ");";
        db.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        final String SQL = "DROP TABLE " + DataBaseTable;
        db.execSQL(SQL);
    }
}
