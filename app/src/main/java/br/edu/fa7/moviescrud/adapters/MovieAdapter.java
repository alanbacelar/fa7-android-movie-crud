package br.edu.fa7.moviescrud.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import br.edu.fa7.moviescrud.R;
import br.edu.fa7.moviescrud.activities.MainActivity;
import br.edu.fa7.moviescrud.fragments.FormFragment;
import br.edu.fa7.moviescrud.models.Movie;

/**
 * Created by bruno on 19/08/15.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> implements Serializable {

    private Context mContext;
    private List<Movie> list;
    private LayoutInflater layoutInflater;
    private static final int UPDATE_MOVIE = 2;

    public MovieAdapter(Context context, List<Movie> list) {
        this.mContext = context;
        this.list = list;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public List<Movie> getList() {
        return list;
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = layoutInflater.inflate(R.layout.cardview_item, viewGroup, false);
        return new MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder MovieViewHolder, int i) {

        Movie p = list.get(i);

        MovieViewHolder.mMovieTitle.setText(p.getTitle());
        MovieViewHolder.mMovieDirector.setText(p.getDirector());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mMovieTitle;
        private TextView mMovieDirector;

        public MovieViewHolder(View itemView) {
            super(itemView);

            mMovieTitle = (TextView) itemView.findViewById(R.id.cardview_title);
            mMovieDirector = (TextView) itemView.findViewById(R.id.cardview_director);

            itemView.getContext().getApplicationContext();

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Movie selectedMovie = list.get(getAdapterPosition());

            Bundle b = new Bundle();
            b.putInt("type", UPDATE_MOVIE);
            b.putLong("id", selectedMovie.getId());

            FragmentTransaction fragmentTransaction = ((AppCompatActivity) mContext).getSupportFragmentManager().beginTransaction();

            FormFragment fragmentForm = ((MainActivity) mContext).getFormFragment();
            fragmentForm.setArguments(b);

            fragmentTransaction.replace(R.id.main_content, fragmentForm);
            fragmentTransaction.commit();
        }
    }

}
