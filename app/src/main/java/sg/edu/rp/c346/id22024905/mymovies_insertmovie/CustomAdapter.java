package sg.edu.rp.c346.id22024905.mymovies_insertmovie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Movies> moviesList;

    public CustomAdapter(Context context, int resource, ArrayList<Movies> objects){
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        moviesList = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvTitle = rowView.findViewById(R.id.textViewTitle);
        TextView tvGenre = rowView.findViewById(R.id.textViewGenre);
        TextView tvYear = rowView.findViewById(R.id.textViewYear);

        // Obtain the Android Version information based on the position
        Movies currentItems = moviesList.get(position);
        tvTitle.setText(currentItems.getTitle());
        tvGenre.setText(currentItems.getGenre());
        tvYear.setText(" " +currentItems.getYear());

        ImageView ivRating = rowView.findViewById((R.id.imageViewRating));

        if ("G".equals(currentItems.getRating())) {
            ivRating.setImageResource(R.drawable.rating_g);
        } else if ("PG".equals(currentItems.getRating())) {
            ivRating.setImageResource(R.drawable.rating_pg);
        } else if ("PG13".equals(currentItems.getRating())) {
            ivRating.setImageResource(R.drawable.rating_pg13);
        } else if ("NC16".equals(currentItems.getRating())) {
            ivRating.setImageResource(R.drawable.rating_nc16);
        } else if ("M18".equals(currentItems.getRating())) {
            ivRating.setImageResource(R.drawable.rating_m18);
        } else {
            ivRating.setImageResource(R.drawable.rating_r21);
        }

        return rowView;
    }

}
