package com.barisirgin.catchthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView time;
    TextView score;
    int intScore;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;

    ImageView[] imageArray;

    Handler handler;
    Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time = findViewById(R.id.timeText);
        score = findViewById(R.id.scoreText);
        intScore = 0;

        imageView = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);

        imageArray = new ImageView[]{imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};

        hideImages();

        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time.setText("Time : " + millisUntilFinished/1000);
                if(millisUntilFinished ==0){

                }
            }

            @Override
            //Zaman sayaci 0 olduktan sonra neler yapilacagi kodlanir.
            public void onFinish() {
                time.setText("Time off");
                handler.removeCallbacks(runnable);
                for(ImageView image :imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                //Zaman bittikten sonra kullanıcıya verilecek bildirimin kodlari
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart ?");
                alert.setMessage("Oyuna yeniden baslamak istermisin ?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //Activityi restart etme yollarindan biri

                        Intent intent = getIntent();
                        //Aktiviteyi yormamak icin bitiriyoruz.
                        finish();
                        startActivity(intent);

                    }
                });
                alert.show();
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Game Over",Toast.LENGTH_LONG).show();
                    }
                });
                alert.show();
            }
        }.start();


    }
    public void increaseScore(View view){
        intScore++;
        score.setText("SCORE : " + intScore);
    }
    //Resimlerin hepsini gizler.
    public void hideImages(){

        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {
                //Resimlerin hepsini gizler.
                for(ImageView image :imageArray){
                    image.setVisibility(View.INVISIBLE);

                }
                Random random = new Random();
                int i = random.nextInt(9);
                //Gizlenen resimlerin iciden rastgele 1-9 araliginda birini görünebilir hale getirir.
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this,200);

            }
        };
        handler.post(runnable);
    }
}