package ru.valentingordienko.loftcoin.data.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import ru.valentingordienko.loftcoin.data.api.model.RateResponse;

public interface Api {

    String CONVERT = "USD,EUR,RUB";

    @GET("cryptocurrency/listings/latest")
    @Headers("X-CMC_PRO_API_KEY: a5fdaba8-b3d0-41c4-9fff-7c615fcbb599")
    Observable<RateResponse> rates(@Query("convert") String converter);
}
