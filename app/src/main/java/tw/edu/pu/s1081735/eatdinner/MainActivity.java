package tw.edu.pu.s1081735.eatdinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.facebook.stetho.Stetho;


public class MainActivity extends AppCompatActivity {
    private ImageButton start,book,setting;
    public SqlDataBaseHelper DH= null;
    MediaPlayer mediaPlayerclick,mediaPlayerstart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        start= findViewById(R.id.start);
        book= findViewById(R.id.book);
        setting=findViewById(R.id.setting);
        start.setSoundEffectsEnabled(false);
        book.setSoundEffectsEnabled(false);
        setting.setSoundEffectsEnabled(false);
        Stetho.initializeWithDefaults(this);
        mediaPlayerstart = MediaPlayer.create(getApplicationContext(), R.raw.gogo);
        mediaPlayerclick = MediaPlayer.create(getApplicationContext(), R.raw.click);

        DH = new SqlDataBaseHelper(MainActivity.this);
        SQLiteDatabase db = DH.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM gamer", null);
        c.moveToFirst();
        Log.d("XXXXX","f"+c.getColumnCount());
        if(c.getColumnCount()<10){
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

        start.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ViewGroup.LayoutParams params = start.getLayoutParams();
                    params.width=10000;
                    params.height=8000;
                    start.setLayoutParams(params);
                }
               

                return false;
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayerstart.start();

                Intent intent = new Intent(MainActivity.this,gameview.class);
                startActivity(intent);

            }
        });
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayerclick.start();

                Intent intent = new Intent(MainActivity.this,book.class);
                startActivity(intent);
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayerclick.start();
                Intent intent = new Intent(MainActivity.this,setting.class);
                startActivity(intent);
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