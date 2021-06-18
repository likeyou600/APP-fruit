package tw.edu.pu.s1081735.eatdinner;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class setting extends AppCompatActivity {
        private ImageButton back2,resetdb;
    public SqlDataBaseHelper DH= null;

    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        back2 = findViewById(R.id.back2);
        resetdb=findViewById(R.id.resetdb);


        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(setting.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        resetdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DH = new SqlDataBaseHelper(setting.this);
                SQLiteDatabase db = DH.getWritableDatabase();
                db.execSQL("DROP TABLE IF EXISTS gamer");

                db.execSQL("CREATE TABLE IF NOT EXISTS gamer( " +
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
                        ");");
                DH.close();

            }
        });

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
}
