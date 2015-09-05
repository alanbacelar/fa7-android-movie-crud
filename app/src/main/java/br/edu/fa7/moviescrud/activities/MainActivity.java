package br.edu.fa7.moviescrud.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import br.edu.fa7.moviescrud.R;
import br.edu.fa7.moviescrud.fragments.FormFragment;
import br.edu.fa7.moviescrud.fragments.MainFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentManager mFragmentManager;
    private final MainFragment mMainFragment;
    private final FormFragment mFormFragment;



    public MainActivity() {
        mMainFragment = new MainFragment();
        mFormFragment = new FormFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_content, mMainFragment);
        fragmentTransaction.commit();
    }

    public MainFragment getMainFragment() { return mMainFragment; }

    public FormFragment getFormFragment() {
        return mFormFragment;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        switch (id){

            case android.R.id.home:
                fragmentTransaction.replace(R.id.main_content, mMainFragment);
                break;

        }

        fragmentTransaction.commit();
        return true;
    }
}
