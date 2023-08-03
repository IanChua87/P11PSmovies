package sg.edu.rp.c346.id22024905.mymovies_insertmovie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    Button btnShow;
    ListView lvMovies;
    ArrayList<Movies> items = new ArrayList<>();
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnShow = findViewById(R.id.buttonShow);
        lvMovies = findViewById(R.id.listViewMovies);

        DBHelper db = new DBHelper(SecondActivity.this);
        items = db.getMovies();

        adapter = new CustomAdapter(this, R.layout.row, items);
        lvMovies.setAdapter(adapter);

        lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movies data = items.get(position);
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("data", data);
                startActivity(intent);
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Movies> moviesWith5Stars = new ArrayList<>();
                for (int i = 0; i < items.size(); i++) {
                    if ("PG13".equals(items.get(i).getRating())) {
                        moviesWith5Stars.add(items.get(i));
                    }
                    CustomAdapter filteredAdapter = new CustomAdapter(SecondActivity.this, R.layout.row, moviesWith5Stars);

                    lvMovies.setAdapter(filteredAdapter);
//                    } else{
//                        adapter.clear();
//                    }
                }
            }
        });
    }
    protected void onResume() {
        super.onResume();
        if (getIntent().getBooleanExtra("dataModified", false)) {
            // Refresh the data in the ListView
            loadMoviesData(); // Replace this with the actual method you use to load the data
        }
    }

    private void loadMoviesData() {
        // Retrieve the song data from the database or any other data source
        DBHelper dbHelper = new DBHelper(SecondActivity.this);
        ArrayList<Movies> songList = dbHelper.getMovies();

        // Create an ArrayAdapter or a custom adapter for the ListView
        ArrayAdapter<Movies> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, songList);

        // Set the adapter to the ListView
        lvMovies.setAdapter(adapter);
    }
}