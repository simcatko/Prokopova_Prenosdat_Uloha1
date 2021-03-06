package sk.fri.uniza;

import retrofit2.Call;
import retrofit2.Response;
import sk.fri.uniza.model.Location;
import sk.fri.uniza.model.Token;
import sk.fri.uniza.model.Weather;
import sk.fri.uniza.model.WeatherData;


import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

/**
 * Hello IoT!
 */
public class App {
    public static void main(String[] args) {
        IotNode iotNode = new IotNode();
        // Vytvorenie požiadavky na získanie údajov o aktuálnom počasí z
        // meteo stanice s ID: station_1
        Call<Map<String, String>> currentWeather =
                iotNode.getWeatherStationService()
                        .getCurrentWeatherAsMap("station_1",
                                List.of("time", "date",
                                        "airTemperature"));

        try {
            // Odoslanie požiadavky na server pomocou REST rozhranie
            Response<Map<String, String>> response = currentWeather.execute();

            if (response.isSuccessful()) { // Dotaz na server bol neúspešný
                //Získanie údajov vo forme Mapy stringov
                Map<String, String> body = response.body();
                System.out.println(body);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        // Vytvorenie požiadavky na získanie údajov o všetkých meteo staniciach
        Call<List<Location>> stationLocations =
                iotNode.getWeatherStationService().getStationLocations();

        try {
            Response<List<Location>> response = stationLocations.execute();

            if (response.isSuccessful()) { // Dotaz na server bol neúspešný
                //Získanie údajov vo forme Zoznam lokacií
                List<Location> body = response.body();

                System.out.println(body);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        // Vytvorenie požiadavky na získanie údajov o aktuálnom počasí z
        // meteo stanice s ID: station_1
        Call<WeatherData> currentWeatherPojo =
                iotNode.getWeatherStationService()
                        .getCurrentWeather("station_1");


        try {
            // Odoslanie požiadavky na server pomocou REST rozhranie
            Response<WeatherData> response = currentWeatherPojo.execute();

            if (response.isSuccessful()) { // Dotaz na server bol neúspešný
                //Získanie údajov vo forme inštancie triedy WeatherData
                WeatherData body = response.body();
                System.out.println(body);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        Call<List<WeatherData>> historyWeatherPojo =
                iotNode.getWeatherStationService()
                        .getHistoryWeather("station_1", "20/01/2021 14:00", "25/01/2021 15:00");
        try {
            // Odoslanie požiadavky na server pomocou REST rozhranie
            Response<List<WeatherData>> response = historyWeatherPojo.execute();

            if (response.isSuccessful()) { // Dotaz na server bol neúspešný
                //Získanie údajov vo forme inštancie triedy WeatherData
                List<WeatherData> body = response.body();
                System.out.println(body);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        Call<List<WeatherData>> historyWeatherPojo2 =
                iotNode.getWeatherStationService()
                        .getHistoryWeather("station_1", "20/01/2021 14:00", "25/01/2021 15:00", List.of("time", "date",
                                "airTemperature"));
        try {
            // Odoslanie požiadavky na server pomocou REST rozhranie
            Response<List<WeatherData>> response = historyWeatherPojo2.execute();

            if (response.isSuccessful()) { // Dotaz na server bol neúspešný
                //Získanie údajov vo forme inštancie triedy WeatherData
                List<WeatherData> body = response.body();
                System.out.println(body);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            double result = iotNode.getAverageTemperature("station_1", "20/01/2021 14:00", "25/01/2021 15:00");
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String authorizationRaw = "admin:heslo";
        String authorization = Base64.getEncoder().encodeToString(authorizationRaw.getBytes());

        Call<Token> tokenPojo = iotNode.getWeatherStationService().getToken("Basic " + authorization, List.of("all"));

        try {
            Response<Token> response = tokenPojo.execute();

            if (response.isSuccessful()) {
                Token token = response.body();
                System.out.println(token.getToken());

                Call<List<Location>> stationLocationsAuthPojo =
                        iotNode.getWeatherStationService().getStationLocationsAuth(token.getToken());

                try {
                    Response<List<Location>> responseLocationAuth = stationLocationsAuthPojo.execute();

                    if (responseLocationAuth.isSuccessful()) { // Dotaz na server bol neúspešný
                        //Získanie údajov vo forme Zoznam lokacií
                        List<Location> body = responseLocationAuth.body();

                        System.out.println(body);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                // Vytvorenie požiadavky na získanie údajov o aktuálnom počasí z
                // meteo stanice s ID: station_1
                Call<WeatherData> currentWeatherAuthPojo =
                        iotNode.getWeatherStationService()
                                .getCurrentWeatherAuth(token.getToken(), "station_1");

                try {

                    // Odoslanie požiadavky na server pomocou REST rozhranie
                    Response<WeatherData> responseWeatherAuth = currentWeatherAuthPojo.execute();

                    if (responseWeatherAuth.isSuccessful()) { // Dotaz na server bol neúspešný
                        //Získanie údajov vo forme inštancie triedy WeatherData
                        WeatherData body = responseWeatherAuth.body();
                        System.out.println(body);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

                Call<List<WeatherData>> historyWeatherAuthPojo =
                        iotNode.getWeatherStationService()
                                .getHistoryWeatherAuth(token.getToken(), "station_1","20/01/2021 14:00", "25/01/2021 15:00");
                try {
                    // Odoslanie požiadavky na server pomocou REST rozhranie
                    Response<List<WeatherData>> responseAuth = historyWeatherAuthPojo.execute();

                    if (responseAuth.isSuccessful()) { // Dotaz na server bol neúspešný
                        //Získanie údajov vo forme inštancie triedy WeatherData
                        List<WeatherData> body = responseAuth.body();
                        System.out.println(body);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

                Call<List<WeatherData>> historyWeatherAuthPojo2 =
                        iotNode.getWeatherStationService()
                                .getHistoryWeatherAuth(token.getToken(), "station_1", "20/01/2021 14:00", "25/01/2021 15:00", List.of("time", "date",
                                        "airTemperature"));
                try {
                    // Odoslanie požiadavky na server pomocou REST rozhranie
                    Response<List<WeatherData>> responseAuth = historyWeatherAuthPojo2.execute();

                    if (responseAuth.isSuccessful()) { // Dotaz na server bol neúspešný
                        //Získanie údajov vo forme inštancie triedy WeatherData
                        List<WeatherData> body = responseAuth.body();
                        System.out.println(body);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        Boolean cnt = true;
//        while (cnt) {
//            try {
//                Call<WeatherData> currentWeatherCnt =
//                        iotNode.getWeatherStationService()
//                                .getCurrentWeather("station_1",
//                                        List.of("time", "date",
//                                                "airTemperature", "humidity", "rainIntensity"));
//
//                Response<WeatherData> response = currentWeatherCnt.execute();
//
//                if (response.isSuccessful()) {
//                    WeatherData body = response.body();
//                    Weather weather = new Weather();
//                    weather.setTime(body.getTime());
//                    weather.setHumidity(body.getHumidity());
//                    weather.setAirTemperature(body.getAirTemperature());
//                    weather.setRainIntensity(body.getRainIntensity());
//
//                    iotNode.getHouseholdService().createWeather(weather).execute();
//                }
//
//                Thread.sleep(60000);
//            } catch (IOException e) {
//                e.printStackTrace();
//                cnt = false;
//            } catch (InterruptedException e) {
//                cnt = false;
//            }
//        }
    }
}
