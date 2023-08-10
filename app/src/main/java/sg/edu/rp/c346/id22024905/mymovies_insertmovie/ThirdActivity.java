package sg.edu.rp.c346.id22024905.mymovies_insertmovie;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {
    EditText etMovieID, etTitle, etGenres, etYears;
    Spinner spnEditRating;
    Movies data;
    Button btnUpdate, btnDelete, btnCancel;
    ArrayList<Movies> mList = new ArrayList<>();

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
                mList = db.getMovies();
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ThirdActivity.this);
                myBuilder.setTitle("Danger");
                for(int i = 0; i < mList.size(); i++) {
                    if (mList.get(i).getId() == Integer.parseInt(etMovieID.getText().toString())) {
                        String mTitle = mList.get(i).getTitle();
                        myBuilder.setMessage("Are you sure you want to delete the movie " + mTitle);
                        myBuilder.setCancelable(false);
                    }
                    myBuilder.setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            db.deleteMovie(data.getId());
                            Intent intent = new Intent(ThirdActivity.this, CustomAdapter.class);
                            intent.putExtra("dataModified", true);
                            startActivity(intent);
                            finish();
                        }
                    });
                    myBuilder.setPositiveButton("CANCEL", null);
                    AlertDialog myDialog = myBuilder.create();
                    myDialog.show();

                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ThirdActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to discard the changes");
                myBuilder.setCancelable(false);

                myBuilder.setNegativeButton("DISCARD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                myBuilder.setPositiveButton("DO NOT DISCARD", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });
    }
}