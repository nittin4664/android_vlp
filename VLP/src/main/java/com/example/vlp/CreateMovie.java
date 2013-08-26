package com.example.vlp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.security.PrivateKey;

import static android.provider.BaseColumns._ID;
import static com.example.vlp.Constant.NAME;
import static com.example.vlp.Constant.NUMBER;
import static com.example.vlp.Constant.TABLE_NAME;


public class CreateMovie extends Activity {

    private MovieData movie;
    private static String[] FROM = {_ID, NAME, NUMBER};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView  (R.layout.create_movie);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.create_movie, menu);
        return true;
    }

    public void saveMovie(View view) {
        movie = new MovieData(this.getApplicationContext());
        try {
            addMovie(view);
            Cursor cursor = getMovie();
            showMovie(cursor);
        } finally {
            movie.close();
        }

    }

    private void showMovie(Cursor cursor) {
        StringBuilder builder = new StringBuilder("saved Movies: \n");
        while (cursor.moveToNext()){
            long id = cursor.getLong(0);
            String name = cursor.getString(1);
            String number = cursor.getString(2);
            builder.append(id).append(":");
            builder.append(name).append(":");
            builder.append(number).append("\n");
        }
        TextView textView = new TextView(this);
        textView.setText(builder);

    }

    private void addMovie(View view) {
        view = (View) view.getParent();
        EditText name = (EditText)view.findViewById(R.id.movieName);
        EditText number = (EditText)view.findViewById(R.id.movieNumber);
        SQLiteDatabase db = movie.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, name.getText().toString());
        values.put(NUMBER, number.getText().toString());
        db.insertOrThrow(TABLE_NAME,null,values);
    }


    public Cursor getMovie() {
        SQLiteDatabase db = movie.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,FROM,null,null,null,null,null);
        startManagingCursor(cursor);
        return cursor;
    }
}
