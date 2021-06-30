package sk.fri.uniza.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import sk.fri.uniza.model.Weather;

public interface HouseholdService {
    @POST("/weather")
    Call<Weather> createWeather(@Body Weather weather);
}
