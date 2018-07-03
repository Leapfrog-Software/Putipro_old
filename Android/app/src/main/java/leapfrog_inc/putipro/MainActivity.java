package leapfrog_inc.putipro;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import leapfrog_inc.putipro.Fragment.Splash.SplashFragment;
import leapfrog_inc.putipro.Function.SaveData;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SaveData.getInstance().initialize(this);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.rootContainer, new SplashFragment());
        transaction.commitAllowingStateLoss();
    }
}
