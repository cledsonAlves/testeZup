package br.com.testezup.domain.service;

import br.com.testezup.model.FilmeModel;
import retrofit2.Call;
import retrofit2.http.*;

public interface  FilmeService {
    @GET("?apikey=98a43b07")
    Call<FilmeModel> getFilme(@Query("t") String t);


}
