package tw.edu.pu.s1081735.eatdinner;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class gameview extends AppCompatActivity {
    private ImageView boss,light;
    private TextView test,win,life;
    private Button tap;
    private ImageButton pausebutton;
    boolean gameplaying = true;

    public boolean strong=false,strongprotect=false;
    public int userlife=5,wintime=0;
    public int speed=2000;
    public float imageYPosition;
    public long time;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameview);
        boss = findViewById(R.id.boss);
        win = findViewById(R.id.win);
        life = findViewById(R.id.life);
        tap = findViewById(R.id.tap);
        light= findViewById(R.id.light);
        test= findViewById(R.id.test);
        pausebutton = findViewById(R.id.pausebutton);

        ObjectAnimator translateYAnimation = ObjectAnimator.ofFloat(boss, "translationY", 0, 1100);
        translateYAnimation.setRepeatCount(ValueAnimator.INFINITE);


        AnimatorSet set = new AnimatorSet();
        set.setDuration(speed);
        set.play(translateYAnimation);
        set.start();


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
                                strong = true;
                                if (imageYPosition <= 840 && imageYPosition >= 720) {
                                    wintime++;
                                    strongprotect = true;
                                }
                                light.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onFinish() {
                                light.setVisibility(View.INVISIBLE);
                                strong = false;

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
                if (speed > 1000) {
                    speed -= 50;
                }

                set.cancel();
                set.setDuration(speed);
                set.start();
                if (strongprotect != true) {
                    userlife--;
                }
                strongprotect = false;
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

                }
                else {
                    tap.setEnabled(true);
                    set.resume();
                    gameplaying=true;
                    pausebutton.setImageResource(R.drawable.pau);

                }
            }
        });
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

