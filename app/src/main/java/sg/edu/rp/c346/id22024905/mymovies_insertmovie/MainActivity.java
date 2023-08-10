package sg.edu.rp.c346.id22024905.mymovies_insertmovie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etMovieTitle, etGenre, etYear;
    Spinner spinnerRating;
    Button btnInsert, btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMovieTitle = findViewById(R.id.etMovieTitle);
        etGenre = findViewById(R.id.etGenre);
        etYear = findViewById(R.id.etYear);
        spinnerRating = findViewById(R.id.spinnerRating);
        btnInsert = findViewById(R.id.insert);
        btnShow = findViewById(R.id.showList);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);

                String rating = spinnerRating.getSelectedItem().toString();
                String movieTitle = etMovieTitle.getText().toString();
                String genre = etGenre.getText().toString();
                int year = 0;
                String yearString = etYear.getText().toString();
                if(yearString.length() > 0){
                    year = Integer.parseInt(yearString);
                }


                if (movieTitle.isEmpty() || genre.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Song please insert movie details", Toast.LENGTH_SHORT).show();
                } else {

                    db.insertMovie(movieTitle, genre, year, rating);
                    Toast.makeText(MainActivity.this, "Movie has been added successfully", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}