package br.com.testezup.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import br.com.testezup.R;
import br.com.testezup.domain.FilmesServiceOkhttp;
import br.com.testezup.domain.dao.FavoritosService;
import br.com.testezup.domain.retrofit.RetrofitApi;
import br.com.testezup.model.FilmeModel;
import com.google.gson.Gson;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;


public class Main extends BaseActivity {
    Button btnBuscar;
    EditText editTextTitulo;
    ProgressBar progressBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initComponents();


        /**   new Thread(new Runnable() {
        @Override public void run() {
        String json  = FilmesServiceOkhttp.INSTANCE.getFilme();
        FilmeModel film = new Gson().fromJson(json, FilmeModel.class);

        Log.i("#FILME", "..."+film.getTitle()+ "... "+ film.getActors());
        }
        }).start();**/


    }

    private void initComponents() {
        editTextTitulo = this.findViewById(R.id.editTitulo);
        btnBuscar = this.findViewById(R.id.btBuscar);
        progressBar = this.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                taskFilme();
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

    private void taskFilme() {

        Call<FilmeModel> call = new RetrofitApi().getFilmeService().getFilme(editTextTitulo.getText().toString());
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
                editTextTitulo.setText("");

            }

            @Override
            public void onFailure(Call<FilmeModel> call, Throwable t) {
                Log.i("#FILME-ERROR", "..." + t.getMessage());
                progressBar.setVisibility(View.INVISIBLE);
                editTextTitulo.setText("");
            }
        });


    }

}
