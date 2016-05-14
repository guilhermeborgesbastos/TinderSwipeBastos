package guilhermeborgesbastos.com.br.tinderswipegbastos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import guilhermeborgesbastos.com.br.tinderswipegbastos.fragment.SwipeCardFragment;

/**
 * Created by Guilherme Borges Bastos on 17/02/2016.
 * guilhermeborgesbastos@gmail.com
 */

public class MainActivity extends AppCompatActivity {

    private Fragment fragment;
    private SwipeCardFragment swipeCardFragment;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        swipeCardFragment = new SwipeCardFragment(this);

        FragmentManager fm = getSupportFragmentManager();
        fragment = fm.findFragmentByTag("MapaFragment");

        if (fragment == null) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.content, swipeCardFragment, "SwipeCardFragment");
            ft.commit();
        }

        setContentView(R.layout.main_activity);

    }

}
