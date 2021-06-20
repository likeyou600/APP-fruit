package tw.edu.pu.s1081735.eatfruit;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class setting extends AppCompatActivity {
        private ImageButton back2,resetdb;
    public SqlDataBaseHelper DH= null;
    MediaPlayer mediaPlayerclick,mediaPlayerdelete;


    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        back2 = findViewById(R.id.back2);
        back2.setSoundEffectsEnabled(false);
        resetdb=findViewById(R.id.resetdb);
        resetdb.setSoundEffectsEnabled(false);
        mediaPlayerclick = MediaPlayer.create(getApplicationContext(), R.raw.click);
        mediaPlayerdelete= MediaPlayer.create(getApplicationContext(),R.raw.delete);

        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayerclick.start();
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
                Toast.makeText(setting.this,"成功重置~",Toast.LENGTH_LONG).show();
                mediaPlayerdelete.start();
            }
        });
        View decorView = getWindow().getDecorView();
// Hide both the navigation bar and the status bar.
// SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
// a general rule, you should design your app to hide the status bar whenever you
// hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }
    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
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
