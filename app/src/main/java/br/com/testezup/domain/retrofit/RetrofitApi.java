package br.com.testezup.domain.retrofit;

import br.com.testezup.domain.service.FilmeService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApi {
    private final Retrofit retrofit;
    private  final String URL_BASE = "http://www.omdbapi.com/";

    public RetrofitApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public FilmeService getFilmeService() {
        return this.retrofit.create(FilmeService.class);
    }

}
