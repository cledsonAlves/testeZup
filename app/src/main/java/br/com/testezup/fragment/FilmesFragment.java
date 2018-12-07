package br.com.testezup.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.testezup.R;
import br.com.testezup.adapter.FilmesAdapter;
import br.com.testezup.domain.dao.FavoritosService;
import br.com.testezup.model.FilmeModel;

import java.util.List;

public class FilmesFragment extends Fragment {

    public RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filmes, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getFilmes();
    }

    private void getFilmes() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setHasFixedSize(true);
                List<FilmeModel> lista = FavoritosService.INSTANCE.getFilmes();
                recyclerView.setAdapter(new FilmesAdapter(lista));
            }
        }).start();
    }

}
