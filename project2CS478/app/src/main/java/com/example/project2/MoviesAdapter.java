package com.example.project2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private Context context;
    private List<Movie> moviesList;
    private OnItemClickListener clickListener;
    private OnItemLongClickListener longClickListener;

    public MoviesAdapter(Context context, List<Movie> moviesList, OnItemClickListener clickListener, OnItemLongClickListener longClickListener) {
        this.context = context;
        this.moviesList = moviesList;
        this.clickListener = clickListener;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.movie_list_item, parent, false);
        return new MovieViewHolder(itemView, clickListener, longClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = moviesList.get(position);
        holder.titleTextView.setText(movie.getTitle());
        holder.actorsTextView.setText(movie.getActors());
        Picasso.get().load(movie.getImageUrl()).into(holder.imageView); // Using Picasso to load images
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView, actorsTextView;

        public MovieViewHolder(View itemView, final OnItemClickListener clickListener, final OnItemLongClickListener longClickListener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.movieImageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            actorsTextView = itemView.findViewById(R.id.actorsTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                        clickListener.onItemClick(getAdapterPosition());
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (longClickListener != null) {
                        return longClickListener.onItemLongClick(getAdapterPosition());
                    }
                    return false;
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(int position);
    }
}