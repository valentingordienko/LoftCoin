package ru.valentingordienko.loftcoin.data.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import ru.valentingordienko.loftcoin.data.api.model.RateResponse;

public interface Api {

    String CONVERT = "USD,EUR,RUB";

    @GET("cryptocurrency/listings/latest")
    @Headers("X-CMC_PRO_API_KEY: a5fdaba8-b3d0-41c4-9fff-7c615fcbb599")
    Call<RateResponse> rates(@Query("convert") String converter);
}
