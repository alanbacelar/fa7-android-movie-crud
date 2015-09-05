package br.edu.fa7.moviescrud.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by bruno on 30/08/15.
 */
public abstract class GenericDAO<T> implements IDatabaseOperations<T> {

    protected SQLiteDatabase db;

    public GenericDAO(Context context) {
        this.db = new DBSQLiteHelper(context).getWritableDatabase();
    }

    protected abstract ContentValues createContentValue(T obj);

    public void close(){
        db.close();
    }

}
