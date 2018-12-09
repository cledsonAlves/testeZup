package br.com.testezup.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.testezup.R;
import br.com.testezup.model.FilmeModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FilmesAdapter   extends RecyclerView.Adapter<FilmesAdapter.FilmesViewHolder> {

    List<FilmeModel> filmes;
    Context ctx;

    public static class FilmesViewHolder extends RecyclerView.ViewHolder {
        public TextView directors;
        public TextView title;
        public TextView resume;
        public TextView category;
        public TextView amount_rating;
        public ImageView poster;

        public FilmesViewHolder(View view) {
            super(view);
             title = view.findViewById(R.id.tv_title);
             directors = view.findViewById(R.id.tv_directors);
            poster = view.findViewById(R.id.iv_banner);
            category = view.findViewById(R.id.tv_category);
            amount_rating = view.findViewById(R.id.tv_rating_amount);
            resume = view.findViewById(R.id.tv_resume);
        }
    }

    public FilmesAdapter(List<FilmeModel> filmes, Context ctx) {
        this.filmes = filmes;
        this.ctx = ctx;
    }

    @Override
    public FilmesAdapter.FilmesViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {

        View view =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);

        FilmesViewHolder vh = new FilmesViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final FilmesViewHolder holder, final int position) {
        holder.title.setText(filmes.get(position).getTitle());
        holder.directors.setText(filmes.get(position).getDirector());
        holder.category.setText(filmes.get(position).getGenre());
        holder.amount_rating.setText(filmes.get(position).getImdbVotes());
        holder.resume.setText(filmes.get(position).getWriter());
        Picasso.with(ctx)
                .load(filmes.get(position).getPoster())
                .into(holder.poster);

    }


    @Override
    public int getItemCount() {
        return filmes.size();
    }
}
