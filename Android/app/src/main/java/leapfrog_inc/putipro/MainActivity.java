package leapfrog_inc.putipro;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import leapfrog_inc.putipro.Fragment.Registration.RegistrationInterviewFragment;
import leapfrog_inc.putipro.Fragment.Registration.RegistrationProfileFragment;
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

    public int getSubContainerId() {
        return R.id.subContainer;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (int i = 0; i < fragments.size(); i++) {
            if (requestCode == RegistrationProfileFragment.requestCodePermission) {
                if (fragments.get(i) instanceof RegistrationProfileFragment) {
                    ((RegistrationProfileFragment) fragments.get(i)).didGrantPermission();
                }
            } else if (requestCode == RegistrationInterviewFragment.requestCodePermission) {
                if (fragments.get(i) instanceof RegistrationInterviewFragment) {
                    ((RegistrationInterviewFragment) fragments.get(i)).didGrantPermission();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode != RESULT_OK) {
            return ;
        }

        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (int i = 0; i < fragments.size(); i++) {
            if ((requestCode == RegistrationProfileFragment.requestCodeGallery)) {
                if (fragments.get(i) instanceof RegistrationProfileFragment) {
                    ((RegistrationProfileFragment)fragments.get(i)).didSelectImage(data);
                }
            } else if ((requestCode == RegistrationInterviewFragment.requestCodeGallery)) {
                if (fragments.get(i) instanceof RegistrationInterviewFragment) {
                    ((RegistrationInterviewFragment)fragments.get(i)).didSelectImage(data);
                }
            }
        }
    }
}
