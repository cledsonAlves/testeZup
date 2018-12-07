package br.com.testezup.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.testezup.R;
import br.com.testezup.model.FilmeModel;
import java.util.List;

public class FilmesAdapter   extends RecyclerView.Adapter<FilmesAdapter.FilmesViewHolder> {

    List<FilmeModel> filmes;

    public static class FilmesViewHolder extends RecyclerView.ViewHolder {
        public TextView year;
        public TextView title;
        public TextView writer;
        public ImageView poster;

        public FilmesViewHolder(View view) {
            super(view);
             title = view.findViewById(R.id.title);
             year = view.findViewById(R.id.year);
             writer = view.findViewById(R.id.writer);
             poster = view.findViewById(R.id.imageViewPoster);
        }
    }

    public FilmesAdapter(List<FilmeModel> filmes) {
        this.filmes = filmes;
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
        holder.writer.setText(filmes.get(position).getWriter());

    }


    @Override
    public int getItemCount() {
        return filmes.size();
    }
}
