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
        public TextView year;
        public TextView title;
        public TextView writer;
        public ImageView poster;

        public FilmesViewHolder(View view) {
            super(view);
             title = view.findViewById(R.id.title);
             year = view.findViewById(R.id.year);
            poster = view.findViewById(R.id.imageViewPoster);
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
                .inflate(R.layout.adapter_filme, parent, false);

        FilmesViewHolder vh = new FilmesViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final FilmesViewHolder holder, final int position) {
        holder.title.setText(filmes.get(position).getTitle());
        holder.year.setText(filmes.get(position).getYear());
        Picasso.with(ctx)
                .load(filmes.get(position).getPoster())
                .into(holder.poster);

    }


    @Override
    public int getItemCount() {
        return filmes.size();
    }
}
