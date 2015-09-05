package br.edu.fa7.moviescrud.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.fa7.moviescrud.R;
import br.edu.fa7.moviescrud.activities.MainActivity;
import br.edu.fa7.moviescrud.daos.MovieDao;
import br.edu.fa7.moviescrud.models.Movie;

/**
 * Created by bruno on 31/08/15.
 */
public class FormFragment extends Fragment implements View.OnClickListener {

    private AppCompatActivity mMainActiivty;
    private Toolbar mMainToolbar;
    private TextView mTitle;
    private TextView mDirector;
    private TextView mGenre;
    private MovieDao mMovieDao;
    private Movie mMovie;
    private Bundle mBundle;
    private Button mBtnRemove;
    private int mType;

    private final int INSERT_TYPE = 1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.form_fragment, container, false);

        mMainActiivty = (AppCompatActivity) getActivity();

        mMainToolbar = (Toolbar) mMainActiivty.findViewById(R.id.main_toolbar);
        mMainToolbar.setLogo(null);
        mMainToolbar.setSubtitle("");
        mMainActiivty.setSupportActionBar(mMainToolbar);
        mMainActiivty.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mMovieDao = new MovieDao(mMainActiivty);

        mTitle = (TextView) v.findViewById(R.id.form_title);
        mDirector = (TextView) v.findViewById(R.id.form_director);
        mGenre = (TextView) v.findViewById(R.id.form_genre);

        mBtnRemove = (Button) v.findViewById(R.id.btn_remove);
        mBtnRemove.setOnClickListener(this);

        (v.findViewById(R.id.btn_save)).setOnClickListener(this);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        mBundle = getArguments();
        mType = mBundle.getInt("type");

        mMovie = new Movie();

        if (mType == INSERT_TYPE) {
            mMainToolbar.setTitle("New Movie");
            mBtnRemove.setVisibility(View.GONE);
        } else {
            mMainToolbar.setTitle("Update Movie");

            Long id = mBundle.getLong("id");

            if (id != null) {
                mMovie = mMovieDao.find(id);
            }
        }

        populateForm();
    }

    private void populateForm() {
        if (mMovie != null) {
            if (mMovie.getTitle() != null) {
                mTitle.setText(mMovie.getTitle());
            } else {
                mTitle.setText("");
            }

            if (mMovie.getDirector() != null) {
                mDirector.setText(mMovie.getDirector());
            } else {
                mDirector.setText("");
            }

            if (mMovie.getGenre() != null) {
                mGenre.setText(mMovie.getGenre());
            } else {
                mGenre.setText("");
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        save();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                back();
                break;

            case R.id.btn_remove:
                remove();
                break;
        }
    }

    private void save() {
        String title = mTitle.getText().toString();
        String director = mDirector.getText().toString();
        String genre = mGenre.getText().toString();

        Movie movie = new Movie(title, director, genre);

        if (mType == INSERT_TYPE) {
            mMovieDao.insert(movie);
        } else {
            movie.setId(mMovie.getId());
            mMovieDao.update(movie);
        }

    }


    private void remove() {
        mMovieDao.delete(mMovie);
        back();
    }

    private void back() {
        ((MainActivity) mMainActiivty).getMainFragment().dataChanged();

        FragmentTransaction fragmentTransaction = mMainActiivty.getSupportFragmentManager().beginTransaction();

        MainFragment mainFragment = ((MainActivity) mMainActiivty).getMainFragment();

        fragmentTransaction.replace(R.id.main_content, mainFragment);
        fragmentTransaction.commit();
    }
}
