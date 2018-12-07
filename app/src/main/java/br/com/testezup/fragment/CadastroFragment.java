package br.com.testezup.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import br.com.testezup.R;
import br.com.testezup.domain.dao.FavoritosService;
import br.com.testezup.domain.retrofit.RetrofitApi;
import br.com.testezup.model.FilmeModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class CadastroFragment extends Fragment {

    public EditText editTitulo;
    public ProgressBar progressBar;
    public Button btnCadastrar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cadastro, container, false);

        editTitulo = view.findViewById(R.id.editTextTitulo);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        btnCadastrar =view.findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                taskFilme();
            }
        });


        return view;
    }

    private void taskFilme() {

        Call<FilmeModel> call = new RetrofitApi().getFilmeService().getFilme(editTitulo.getText().toString());
        call.enqueue(new Callback<FilmeModel>() {
            @Override
            public void onResponse(Call<FilmeModel> call, Response<FilmeModel> response) {
                if (response.isSuccessful()) {
                    FilmeModel filme = response.body();
                    Log.i("#URL CHAMADA:", "URL..." + call.request().url());
                    Log.i("#FILME", "..." + filme.getTitle());
                    Toast.makeText(getContext(), "Atores : " + filme.getActors(), Toast.LENGTH_LONG).show();
                    //  salva o filme no banco
                    taskSaveFilme(filme);


                } else {
                    Log.i("#ERROR", "URL..." + call.request().url());
                    Log.i("#ERROR", "MSG..." + response.message());
                    Toast.makeText(getContext(), "Error : " + response.message(), Toast.LENGTH_LONG).show();

                }
                progressBar.setVisibility(View.INVISIBLE);
                editTitulo.setText("");

            }

            @Override
            public void onFailure(Call<FilmeModel> call, Throwable t) {
                Log.i("#FILME-ERROR", "..." + t.getMessage());
                progressBar.setVisibility(View.INVISIBLE);
                editTitulo.setText("");
            }
        });


    }

    private void taskSaveFilme(final FilmeModel filme) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                FavoritosService.INSTANCE.update(filme);
                List<FilmeModel> lista = FavoritosService.INSTANCE.getFilmes();
                for (FilmeModel filme : lista){
                    Log.i("#BANCO", "-- TITULO --" + filme.getTitle());
                }

            }
        }).start();
    }



}
