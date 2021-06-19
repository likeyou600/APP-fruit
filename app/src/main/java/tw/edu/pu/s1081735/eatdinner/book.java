package tw.edu.pu.s1081735.eatdinner;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;


public class book extends AppCompatActivity {
    private ImageButton bookback;
    private TextView highscore;
    public SqlDataBaseHelper DH= null;
    public int score;
    public ArrayList<String> show=new ArrayList();
    MediaPlayer mediaPlayerclick,mediaPlayerget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book);
        bookback = findViewById(R.id.back);
        bookback.setSoundEffectsEnabled(false);
        highscore = findViewById(R.id.highscore);
        mediaPlayerclick = MediaPlayer.create(getApplicationContext(), R.raw.click);
        mediaPlayerget = MediaPlayer.create(getApplicationContext(), R.raw.get);
        ImageButton[]  ImageButton = new ImageButton[20];
        for (int j = 0; j <20; j++) {
            int c=j+1;
            String ib = "f"+c+"button";
            int ibb = getResources().getIdentifier(ib, "id", getPackageName());
            ImageButton[j] = ((ImageButton) findViewById(ibb));
            ImageButton[j].setEnabled(false);

        }





        DH = new SqlDataBaseHelper(book.this);
        SQLiteDatabase db = DH.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM gamer", null);
        c.moveToFirst();
        if (c.getCount() > 0) {
            score = c.getInt(1);
            highscore.setText("高分紀錄 " + score + " 分");
            for(int i=0;i<20;i++){
                if(c.getInt(i+2)==1){
                    int spic=i+1;
                    String pic = "f"+spic;
                    int picc = getResources().getIdentifier(pic, "drawable", getPackageName());
                    ImageButton[i].setImageResource(picc);
                    ImageButton[i].setEnabled(true);

                }
            }


        } else {
            highscore.setText("高分紀錄 0分");
        }



        bookback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayerclick.start();
                Intent intent = new Intent(book.this, MainActivity.class);
                startActivity(intent);
                finish();
                DH.close();
            }
        });
        DownloadAsyncTask dTask = new DownloadAsyncTask();
        dTask.execute("https://data.coa.gov.tw/Service/OpenData/TransService.aspx?UnitId=WVOiWSdDjWxx");




            for(int ib=0;ib<20;ib++) {
                int finalIb = ib;
                int finalIb1 = ib;
                ImageButton[ib].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(show.size()!=0) {
//                        Toast.makeText(book.this, show.get(finalIb), Toast.LENGTH_LONG).show();
                            int spic = finalIb1 + 1;
                            String pic = "f" + spic;
                            int picc = getResources().getIdentifier(pic, "drawable", getPackageName());

                            Intent intent = new Intent(book.this, detail.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("key", show.get(finalIb));
                            bundle.putInt("picc", picc);

                            intent.putExtras(bundle);
                            startActivity(intent);
                            mediaPlayerget.start();
                        }
                        else{
                            Toast.makeText(book.this,"請稍等，資料載入中",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }

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

    class DownloadAsyncTask extends AsyncTask<String, Void, String> {
        StringBuffer json = new StringBuffer();
        @Override
        protected String doInBackground(String... urls) {//表示不只一個urls 可傳入多個
            try {

                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream is = connection.getInputStream();
                int status = connection.getResponseCode();
                Log.d("JSON parser", String.valueOf(status));
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line = br.readLine();
                while (line != null) {
                    json.append(line);
                    line = br.readLine();
                }
                //
                connection.disconnect();
                return json.toString();
                //
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        //

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("JSON", s);
            JSONArray allData =null;
            try {
                allData = new JSONArray (s);
                String a[] = {"荔枝","枇杷","巨峰葡萄","酪梨","鳳梨釋迦","棗子","芒果","甜桃","蓮霧","檸檬","紅龍果","椪柑","番石榴","楊桃","柳橙","葡萄柚","木瓜","鳳梨","青香蕉","椰子"};
                for(int fruit=19;fruit>=0;fruit--) {
                    for (int i = 0; i < allData.length(); i++) {
                        JSONObject object = allData.getJSONObject(i);

                        if (object.getString("PRODUCTNAME").contains(a[fruit])) {
//                            Log.d("XXXX", object.getString("PRODUCTNAME") +",平均價格:"+object.getString("AVGPRICE") + "元,時間" + object.getString("YEAR") + "/" + object.getString("MONTH") + "/"+object.getString("PERIOD"));
                            String back="水果名稱:"+object.getString("PRODUCTNAME") +"\n"+"平均價格:"+object.getString("AVGPRICE") + "元"+"\n"+"時間:" + object.getString("YEAR") + "/" + object.getString("MONTH") + "/"+object.getString("PERIOD");
                            show.add(back);
                            break;
                        }
                    }
                }

            }
             catch (JSONException e) {
                e.printStackTrace();
            }
        }
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
