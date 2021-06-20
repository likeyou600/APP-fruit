package tw.edu.pu.s1081735.eatfruit;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;


public class settle extends AppCompatActivity {
    private TextView fin;
    private ImageButton backhome,again;
    public SqlDataBaseHelper DH= null;
    public ImageView nowget,newlogo;
    String score;
    MediaPlayer mediaPlayerclick,mediaPlayerstart;


    public int oldscore,id,intscore;

    String a[] = {"荔枝","枇杷","巨峰葡萄","酪梨","鳳梨釋迦","棗子","芒果","甜桃","蓮霧","檸檬","紅龍果","椪柑","番石榴","楊桃","柳橙","葡萄柚","木瓜","鳳梨","青香蕉","椰子"};

    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settle);
        fin=findViewById(R.id.fin);
        backhome=findViewById(R.id.back);
        backhome.setSoundEffectsEnabled(false);
        again=findViewById(R.id.again);
        again.setSoundEffectsEnabled(false);
        nowget=findViewById(R.id.nowget);
        newlogo=findViewById(R.id.newlogo);
        mediaPlayerclick = MediaPlayer.create(getApplicationContext(), R.raw.click);
        mediaPlayerstart= MediaPlayer.create(getApplicationContext(), R.raw.gogo);
        Bundle bundle = getIntent().getExtras();
        score = bundle.getString("key");

        intscore = Integer.parseInt(score);

        Context context = this;

        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), BitmapFactory.decodeResource(context.getResources(), R.drawable.bg_round_button));
        circularBitmapDrawable.setCornerRadius(15);
        nowget.setImageDrawable(circularBitmapDrawable);


        backhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayerclick.start();
                Intent intent = new Intent(settle.this,MainActivity.class);
                startActivity(intent);
                finish();
                DH.close();
            }
        });
        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayerstart.start();
                Intent intent = new Intent(settle.this,gameview.class);
                startActivity(intent);
                finish();
                DH.close();
            }
        });

        DH = new SqlDataBaseHelper(settle.this);
        add(Integer.parseInt(score));
        View decorView = getWindow().getDecorView();
// Hide both the navigation bar and the status bar.
// SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
// a general rule, you should design your app to hide the status bar whenever you
// hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);    }

    private void add(int newscore) {
        SQLiteDatabase db = DH.getWritableDatabase();
        ContentValues values = new ContentValues();


        Cursor c = db.rawQuery("SELECT * FROM gamer",null);
        c.moveToFirst();
        if(c.getCount()>0){
            id=c.getInt(0);
        oldscore=c.getInt(1);
            Log.d("XXXX","id:"+id);
            Log.d("XXXX","score:"+oldscore);
            if(newscore>oldscore){
                Log.d("XXXX","yes");
                values.put("_score",newscore);
                db.update("gamer",values,"_id=1",null);
            }
        }
        else{
            values.put("_score",newscore);
            db.insert("gamer",null,values);
        }



        if(intscore>=0&&intscore<=5){
            nowget.setImageResource(R.drawable.f1);


            if(c.getCount()>0){
                if(c.getInt(2)!=1){
                    newlogo.setVisibility(View.VISIBLE);
                }
            }else{
                newlogo.setVisibility(View.VISIBLE);
            }

            values.put("_f1",1);
            db.update("gamer",values,"_id=1",null);
            fin.setText("拯救次數:"+score+"\n獲得水果:"+a[19]);
        }
        if(intscore>=6&&intscore<=10){
            nowget.setImageResource(R.drawable.f2);

            if(c.getCount()>0){
                if(c.getInt(3)!=1){
                    newlogo.setVisibility(View.VISIBLE);
                }
            }else{
                newlogo.setVisibility(View.VISIBLE);
            }

            values.put("_f2",1);
            db.update("gamer",values,"_id=1",null);
            fin.setText("拯救次數:"+score+"\n獲得水果:"+a[18]);
        }
        if(intscore>=11&&intscore<=15){
            nowget.setImageResource(R.drawable.f3);
            if(c.getCount()>0){
                if(c.getInt(4)!=1){
                    newlogo.setVisibility(View.VISIBLE);
                }
            }else{
                newlogo.setVisibility(View.VISIBLE);
            }
            values.put("_f3",1);
            db.update("gamer",values,"_id=1",null);
            fin.setText("拯救次數:"+score+"\n獲得水果:"+a[17]);
        }
        if(intscore>=16&&intscore<=20){
            nowget.setImageResource(R.drawable.f4);
            if(c.getCount()>0){
                if(c.getInt(5)!=1){
                    newlogo.setVisibility(View.VISIBLE);
                }
            }else{
                newlogo.setVisibility(View.VISIBLE);
            }
            values.put("_f4",1);
            db.update("gamer",values,"_id=1",null);
            fin.setText("拯救次數:"+score+"\n獲得水果:"+a[16]);
        }
        if(intscore>=21&&intscore<=25){
            nowget.setImageResource(R.drawable.f5);
            if(c.getCount()>0){
                if(c.getInt(6)!=1){
                    newlogo.setVisibility(View.VISIBLE);
                }
            }else{
                newlogo.setVisibility(View.VISIBLE);
            }
            values.put("_f5",1);
            db.update("gamer",values,"_id=1",null);
            fin.setText("拯救次數:"+score+"\n獲得水果:"+a[15]);
        }
        if(intscore>=26&&intscore<=30){
            nowget.setImageResource(R.drawable.f6);
            values.put("_f6",1);
            db.update("gamer",values,"_id=1",null);
            fin.setText("拯救次數:"+score+"\n獲得水果:"+a[14]);
        }
        if(intscore>=31&&intscore<=35){
            nowget.setImageResource(R.drawable.f7);
            values.put("_f7",1);
            db.update("gamer",values,"_id=1",null);
            fin.setText("拯救次數:"+score+"\n獲得水果:"+a[13]);
        }
        if(intscore>=36&&intscore<=40){
            nowget.setImageResource(R.drawable.f8);
            values.put("_f8",1);
            db.update("gamer",values,"_id=1",null);
            fin.setText("拯救次數:"+score+"\n獲得水果:"+a[12]);
        }
        if(intscore>=41&&intscore<=45){
            nowget.setImageResource(R.drawable.f9);
            values.put("_f9",1);
            db.update("gamer",values,"_id=1",null);
            fin.setText("拯救次數:"+score+"\n獲得水果:"+a[11]);
        }
        if(intscore>=46&&intscore<=50){
            nowget.setImageResource(R.drawable.f10);
            values.put("_f10",1);
            db.update("gamer",values,"_id=1",null);
            fin.setText("拯救次數:"+score+"\n獲得水果:"+a[10]);
        }
        if(intscore>=51&&intscore<=55){
            nowget.setImageResource(R.drawable.f11);
            values.put("_f11",1);
            db.update("gamer",values,"_id=1",null);
            fin.setText("拯救次數:"+score+"\n獲得水果:"+a[9]);
        }
        if(intscore>=56&&intscore<=60){
            nowget.setImageResource(R.drawable.f12);
            values.put("_f12",1);
            db.update("gamer",values,"_id=1",null);
            fin.setText("拯救次數:"+score+"\n獲得水果:"+a[8]);
        }
        if(intscore>=61&&intscore<=65){
            nowget.setImageResource(R.drawable.f13);
            values.put("_f13",1);
            db.update("gamer",values,"_id=1",null);
            fin.setText("拯救次數:"+score+"\n獲得水果:"+a[7]);
        }
        if(intscore>=66&&intscore<=70){
            nowget.setImageResource(R.drawable.f14);
            values.put("_f14",1);
            db.update("gamer",values,"_id=1",null);
            fin.setText("拯救次數:"+score+"\n獲得水果:"+a[6]);
        }
        if(intscore>=71&&intscore<=75){
            nowget.setImageResource(R.drawable.f15);
            values.put("_f15",1);
            db.update("gamer",values,"_id=1",null);
            fin.setText("拯救次數:"+score+"\n獲得水果:"+a[5]);
        }
        if(intscore>=76&&intscore<=80){
            nowget.setImageResource(R.drawable.f16);
            values.put("_f16",1);
            db.update("gamer",values,"_id=1",null);
            fin.setText("拯救次數:"+score+"\n獲得水果:"+a[4]);
        }
        if(intscore>=81&&intscore<=85){
            nowget.setImageResource(R.drawable.f17);
            values.put("_f17",1);
            db.update("gamer",values,"_id=1",null);
            fin.setText("拯救次數:"+score+"\n獲得水果:"+a[3]);
        }
        if(intscore>=86&&intscore<=90){
            nowget.setImageResource(R.drawable.f18);
            values.put("_f18",1);
            db.update("gamer",values,"_id=1",null);
            fin.setText("拯救次數:"+score+"\n獲得水果:"+a[2]);
        }
        if(intscore>=91&intscore<=95){
            nowget.setImageResource(R.drawable.f19);
            values.put("_f19",1);
            db.update("gamer",values,"_id=1",null);
            fin.setText("拯救次數:"+score+"\n獲得水果:"+a[1]);
        }
        if(intscore>=96){
            nowget.setImageResource(R.drawable.f20);
            values.put("_f20",1);
            db.update("gamer",values,"_id=1",null);
            fin.setText("拯救次數:"+score+"\n獲得水果:"+a[0]);
        }


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
