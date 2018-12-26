package online.shatla_app;


import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash extends AppCompatActivity {
    ImageView backgraound ;
    Animation fromleft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);



        setContentView(R.layout.activity_splash);


        backgraound=(ImageView)findViewById(R.id.backgruond);
        fromleft= AnimationUtils.loadAnimation(this,R.anim.fromleft);
        backgraound.setAnimation(fromleft);



        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(Splash.this,NavigationMainActivity.class);
                startActivity(i);
                finish();
            }
        }, 3000);

    }
}

