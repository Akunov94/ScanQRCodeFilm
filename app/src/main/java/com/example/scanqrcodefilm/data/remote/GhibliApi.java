package com.example.scanqrcodefilm.data.remote;

import com.example.scanqrcodefilm.data.model.Film;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GhibliApi {

    @GET(EndPoints.GET_FILM_ID)
    Call<Film> getFilmId(
            @Path("id") String id
    );
}
