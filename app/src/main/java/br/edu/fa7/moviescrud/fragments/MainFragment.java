package br.edu.fa7.moviescrud.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.melnykov.fab.FloatingActionButton;

import java.util.List;

import br.edu.fa7.moviescrud.R;
import br.edu.fa7.moviescrud.activities.MainActivity;
import br.edu.fa7.moviescrud.adapters.MovieAdapter;
import br.edu.fa7.moviescrud.daos.MovieDao;
import br.edu.fa7.moviescrud.models.Movie;

/**
 * Created by alan on 9/5/15.
 */
public class MainFragment extends Fragment implements View.OnClickListener {
    private AppCompatActivity mMainActivity;
    private Toolbar mMainToolbar;
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFloatingActionButton;
    private MovieDao mMovieDao;
    private MovieAdapter mMovieAdapter;

    private final int INSERT_MOVIE = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.main_fragment, container, false);

        mMainActivity = (AppCompatActivity) getActivity();

        mMainToolbar = (Toolbar) mMainActivity.findViewById(R.id.main_toolbar);
        mMainToolbar.setTitle("Movies CRUD");
        mMainActivity.setSupportActionBar(mMainToolbar);
        mMainActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        mMovieDao = new MovieDao(mMainActivity);
        List<Movie> list = mMovieDao.findAll();

        LinearLayoutManager llm = new LinearLayoutManager(mMainActivity);
        mMovieAdapter = new MovieAdapter(mMainActivity, list);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.fragment_main_recyclerview);
        mRecyclerView.setLayoutManager(llm);
        mRecyclerView.setAdapter(mMovieAdapter);

        mFloatingActionButton = (FloatingActionButton) v.findViewById(R.id.fragment_main_floating_add_button);
        mFloatingActionButton.setOnClickListener(this);
        mFloatingActionButton.attachToRecyclerView(mRecyclerView);

        return v;
    }

    public void dataChanged() {
        mMovieAdapter.getList().clear();
        mMovieAdapter.getList().addAll(mMovieDao.findAll());
        mMovieAdapter.notifyDataSetChanged();
    }


    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = mMainActivity.getSupportFragmentManager().beginTransaction();

        switch (v.getId()) {
            case R.id.fragment_main_floating_add_button:

                Bundle b = new Bundle();
                b.putInt("type", INSERT_MOVIE);

                FormFragment fragmentForm = ((MainActivity) mMainActivity).getFormFragment();
                fragmentForm.setArguments(b);

                fragmentTransaction.replace(R.id.main_content, fragmentForm);
                break;
        }

        fragmentTransaction.commit();
    }

}
