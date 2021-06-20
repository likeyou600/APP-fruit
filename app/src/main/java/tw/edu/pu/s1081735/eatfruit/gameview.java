package tw.edu.pu.s1081735.eatfruit;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class gameview extends AppCompatActivity {
    private ImageView boss,light,arrow,left,right;
    private TextView test,win,life,black,rule,homebacktext,againtext2;
    private Button tap,gamestart;
    private ImageButton pausebutton,homeback,againgame;
    boolean gameplaying = true,stage1=false;

    public boolean strongprotect=false;
    public int userlife=5,wintime=0;
    public int speed=2500,strong=0;
    public float imageYPosition;
    MediaPlayer mediaPlayerstart,mediaPlayerattack,mediaPlayerclick,music;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameview);
        boss = findViewById(R.id.boss);
        win = findViewById(R.id.win);
        life = findViewById(R.id.life);
        tap = findViewById(R.id.tap);
        tap.setSoundEffectsEnabled(false);
        light= findViewById(R.id.light);
        test= findViewById(R.id.test);
        pausebutton = findViewById(R.id.pausebutton);
        pausebutton.setSoundEffectsEnabled(false);
        gamestart=findViewById(R.id.gamestart);
        gamestart.setSoundEffectsEnabled(false);
        black=findViewById(R.id.black);
        rule=findViewById(R.id.rule);
        arrow=findViewById(R.id.arrow);
        left=findViewById(R.id.left);
        right=findViewById(R.id.right);
        homebacktext=findViewById(R.id.homebacktext);
        againtext2=findViewById(R.id.againtext2);
        homeback=findViewById(R.id.homeback);
        homeback.setSoundEffectsEnabled(false);
        againgame=findViewById(R.id.againgame);
        againgame.setSoundEffectsEnabled(false);
        mediaPlayerstart=MediaPlayer.create(getApplicationContext(), R.raw.gogo);
        mediaPlayerattack=MediaPlayer.create(getApplicationContext(), R.raw.attack);
        mediaPlayerclick=MediaPlayer.create(getApplicationContext(), R.raw.click);
        music=MediaPlayer.create(getApplicationContext(), R.raw.music);
        ObjectAnimator translateYAnimation = ObjectAnimator.ofFloat(boss, "translationY", 0, 1100);
        translateYAnimation.setRepeatCount(ValueAnimator.INFINITE);


        AnimatorSet set = new AnimatorSet();
        set.setDuration(speed);
        gamestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                black.setVisibility(View.INVISIBLE);
                rule.setVisibility(View.INVISIBLE);
                gamestart.setVisibility(View.INVISIBLE);
                arrow.setVisibility(View.INVISIBLE);
                mediaPlayerstart.start();
                music.start();
                set.play(translateYAnimation);
                set.start();
            }
        });



        translateYAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                imageYPosition = (Float) animation.getAnimatedValue();
                String position = String.format("Y:%d", (int) imageYPosition);
                life.setText("生命:" + userlife);
                win.setText("拯救次數:" + wintime);
                test.setText(position);
//                Log.d("XXXposition", String.valueOf(imageYPosition));
//                Log.d("XXXwin", String.valueOf(wintime));
                if (userlife == 0) {
                    set.cancel();
                    music.stop();

                    Intent intent = new Intent(gameview.this, settle.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("key", String.valueOf(wintime));
                    intent.putExtras(bundle);

                    startActivity(intent);
                    finish();
                }
                tap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        new CountDownTimer(100, 100) {

                            @Override
                            public void onTick(long millisUntilFinished) {
                                light.setVisibility(View.VISIBLE);
                                left.setVisibility(View.VISIBLE);
                                right.setVisibility(View.VISIBLE);
                                mediaPlayerattack.start();
                                strong +=1;
                                if (imageYPosition <= 840 && imageYPosition >= 650&&strong==1) {
                                    wintime++;
                                    strongprotect = true;
                                }

                                if(strong>=2){
                                    userlife--;
                                }
                            }

                            @Override
                            public void onFinish() {
                                if(strongprotect!=true) {
                                    light.setVisibility(View.INVISIBLE);
                                    left.setVisibility(View.INVISIBLE);
                                    right.setVisibility(View.INVISIBLE);
                                }
                            }
                        }.start();

                    }
                });

            }
        });
        translateYAnimation.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                if(stage1!=true) {
                    if (speed > 1500) {
                        speed -= 50;
                    }
                    else {
                        stage1 = true;
                    }
                }else{

                    speed=new Random().nextInt(1800) + 1000;

                }


                set.cancel();
                set.setDuration(speed);
                set.start();

                if (strongprotect != true) {
                    userlife--;
                }
                strongprotect = false;
                strong=0;
                light.setVisibility(View.INVISIBLE);
                left.setVisibility(View.INVISIBLE);
                right.setVisibility(View.INVISIBLE);
            }
        });
        pausebutton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                if(gameplaying){
                    set.pause();
                    tap.setEnabled(false);
                    gameplaying=false;
                    pausebutton.setImageResource(R.drawable.st);
                    homeback.setVisibility(View.VISIBLE);
                    homebacktext.setVisibility(View.VISIBLE);
                    againgame.setVisibility(View.VISIBLE);
                    againtext2.setVisibility(View.VISIBLE);
                    black.setVisibility(View.VISIBLE);
                    mediaPlayerclick.start();
                    music.pause();
                }
                else {
                    tap.setEnabled(true);
                    set.resume();
                    gameplaying=true;
                    pausebutton.setImageResource(R.drawable.pau);
                    homeback.setVisibility(View.INVISIBLE);
                    homebacktext.setVisibility(View.INVISIBLE);
                    againgame.setVisibility(View.INVISIBLE);
                    againtext2.setVisibility(View.INVISIBLE);
                    black.setVisibility(View.INVISIBLE);
                    mediaPlayerclick.start();
                    music.start();

                }
            }
        });

        homeback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayerclick.start();
                music.stop();
                Intent intent = new Intent(gameview.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        againgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayerclick.start();
                music.stop();
                Intent intent = new Intent(gameview.this,gameview.class);
                startActivity(intent);
                finish();
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

