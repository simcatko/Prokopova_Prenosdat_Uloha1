package sk.fri.uniza;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import sk.fri.uniza.api.WeatherStationService;
import sk.fri.uniza.model.WeatherData;

import java.io.IOException;
import java.util.List;

public class IotNode {
    private final Retrofit retrofit;
    private final WeatherStationService weatherStationService;

    public IotNode() {

        retrofit = new Retrofit.Builder()
                // Url adresa kde je umietnená WeatherStation služba
                .baseUrl("http://localhost:9000/")
                // Na konvertovanie JSON objektu na java POJO použijeme
                // Jackson knižnicu
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        // Vytvorenie inštancie komunikačného rozhrania
        weatherStationService = retrofit.create(WeatherStationService.class);

    }

    public WeatherStationService getWeatherStationService() {
        return weatherStationService;
    }

    public double getAverageTemperature(String station,String from, String to) throws IOException {
        Call<List<WeatherData>> historyWeatherPojo2 =
                getWeatherStationService().getHistoryWeather(station, from, to, List.of("airTemperature"));

        Response<List<WeatherData>> response = historyWeatherPojo2.execute();

        if (response.isSuccessful()) { // Dotaz na server bol neúspešný
            double sum = 0;
            for (WeatherData data : response.body()) sum += data.getAirTemperature();
            return sum/response.body().size();
        } else {
            return 0;
        }
    }
}
