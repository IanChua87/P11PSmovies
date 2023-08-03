package sg.edu.rp.c346.id22024905.mymovies_insertmovie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ThirdActivity extends AppCompatActivity {
    EditText etMovieID, etTitle, etGenres, etYears;
    Spinner spnEditRating;
    Movies data;
    Button btnUpdate, btnDelete, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        etMovieID = findViewById(R.id.etMovieID);
        etTitle = findViewById(R.id.etTitle);
        etGenres = findViewById(R.id.etGenres);
        etYears = findViewById(R.id.etYears);
        spnEditRating = findViewById(R.id.spinnerEditRating);
        btnUpdate = findViewById(R.id.buttonUpdate);
        btnDelete = findViewById(R.id.buttonDelete);
        btnCancel = findViewById(R.id.buttonCancel);

        Intent i = getIntent();
        data = (Movies) i.getSerializableExtra("data");

        etMovieID.setText(String.valueOf(data.getId()));
        etTitle.setText(data.getTitle());
        etGenres.setText(data.getGenre());
        etYears.setText(String.valueOf(data.getYear()));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(ThirdActivity.this);
                String selectedRating = spnEditRating.getSelectedItem().toString();

                data.setTitle(etTitle.getText().toString());
                data.setGenre(etGenres.getText().toString());
                data.setYear(Integer.parseInt(etYears.getText().toString()));
                data.setRating(selectedRating);

                db.updateMovie(data);
                db.close();

                Intent intent = new Intent(ThirdActivity.this, CustomAdapter.class);
                intent.putExtra("dataModified", true);
                startActivity(intent);

                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(ThirdActivity.this);
                db.deleteMovie(data.getId());

                Intent intent = new Intent(ThirdActivity.this, CustomAdapter.class);
                intent.putExtra("dataModified", true);
                startActivity(intent);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}