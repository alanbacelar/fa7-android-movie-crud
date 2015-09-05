package br.edu.fa7.moviescrud.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.edu.fa7.moviescrud.models.Movie;

/**
 * Created by bruno on 30/08/15.
 */
public class MovieDao extends GenericDAO<Movie> {

    private static final String TABLE_NAME = "movie";

    public MovieDao(Context context) {
        super(context);
    }

    @Override
    public void insert(Movie obj) {
        db.insert(TABLE_NAME, null, createContentValue(obj));
    }

    @Override
    public void update(Movie obj) {
        ContentValues values = createContentValue(obj);
        db.update(TABLE_NAME, values, "_id = ?", new String[]{obj.getId().toString()});
    }

    @Override
    public void delete(Movie obj) {
        db.delete(TABLE_NAME, "_id = ?", new String[]{obj.getId().toString()});
    }

    @Override
    public Movie find(Long id) {
        Log.d("ID", Long.toString(id));
        Cursor cursor = db.query(TABLE_NAME, null, "_id = ?", new String[]{Long.toString(id)}, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return curso2Movie(cursor);
        }

        return null;
    }

    @Override
    public List<Movie> findAll() {
        List<Movie> list = new ArrayList<>();

        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, "_id");
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToPosition(i);
                list.add(curso2Movie(cursor));

            }
        }

        return list;
    }

    protected Movie curso2Movie(Cursor cursor) {
        Long id = cursor.getLong(cursor.getColumnIndex("_id"));
        String title = cursor.getString(cursor.getColumnIndex("title"));
        String director = cursor.getString(cursor.getColumnIndex("director"));
        String genre = cursor.getString(cursor.getColumnIndex("genre"));

        return new Movie(id, title, director, genre);
    }

    @Override
    protected ContentValues createContentValue(Movie obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getId());
        values.put("title", obj.getTitle());
        values.put("director", obj.getDirector());
        values.put("genre", obj.getGenre().toString());
        return values;
    }

}
