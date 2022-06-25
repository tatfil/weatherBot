package demo.example.parser;

import demo.example.model.ForecastRequest;
import demo.example.model.WeatherUnit;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class WeatherParserTomorrowIO {

    private final static String TOMORROW_URL = "https://api.tomorrow.io/v4/timelines";


    public String getResponse(ForecastRequest forecastRequest) throws IOException {

        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(setRequestBody(forecastRequest), mediaType);

        Request request = new Request.Builder()
                .url(TOMORROW_URL)
                .post(body)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("apikey", getTomorrowKey())
                .build();

        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            int responseCode = response.code();
            throw new IllegalArgumentException(String.valueOf(responseCode));
        }

        return response.body().string();
    }

    public  Map<String, WeatherUnit> convertRawDataToMap(String data) throws Exception {

        JSONObject obj = new JSONObject(data);
        JSONArray intervals = obj.getJSONObject("data").getJSONArray("timelines").getJSONObject(0).getJSONArray("intervals");

        Map<String, WeatherUnit> weatherUnitMap = new HashMap<>();

        for (int i = 0; i < intervals.length(); i++)
        {
            JSONObject values = intervals.getJSONObject(i).getJSONObject("values");

            WeatherUnit weatherUnit = new WeatherUnit();
            weatherUnit.setCloudCover(values.get("cloudCover").toString());
            weatherUnit.setTemperature(values.get("temperature").toString());
            weatherUnit.setHumidity(values.get("humidity").toString());
            weatherUnit.setPrecipitationProbability(values.get("precipitationProbability").toString());

            String startTime = intervals.getJSONObject(i).getString("startTime");
            weatherUnitMap.put(startTime, weatherUnit);
        }
        return weatherUnitMap;
    }

    public String formatForecastData(Map<String, WeatherUnit> map){

        String string = new String();
        StringBuilder stringBuilder = new StringBuilder();

        map.forEach((k,v) -> stringBuilder.append(k + ":" + v));

        return stringBuilder.toString();
    }



    public String setRequestBody(ForecastRequest forecastRequest){

        return "{\"location\":" +
                "\"" + forecastRequest.getLocation() + "\"" + "," +
                "\"fields\":" +
                "[\"temperature\",\"cloudCover\",\"humidity\",\"precipitationProbability\"]" + "," +
                "\"units\":\"metric\"" + "," +
                "\"timesteps\":[\"1h\"]" + "," +
                "\"startTime\":" +
                "\"" + setStartTime() + "\"," +
                "\"endTime\":" +
                "\"" + setEndTime() + "\"}";
    }

    public String setStartTime(){
        LocalDate today = LocalDate.now();
        LocalDateTime summerDay = LocalDateTime.of(2022, 6, 25, 10, 0);

        //time in ISO 8601 format "2019-03-27T14:09:50Z"
        ZonedDateTime zonedDateTime = ZonedDateTime.of(summerDay, ZoneId.of("Europe/Paris"));
        String formattedZonedDateTime = DateTimeFormatter.ISO_INSTANT.format(zonedDateTime);

        return formattedZonedDateTime;
    }


    public String setEndTime(){
        LocalDate today = LocalDate.now();
        LocalDateTime summerDay = LocalDateTime.of(2022, 6, 25, 20, 0);

        ZonedDateTime zonedDateTime = ZonedDateTime.of(summerDay, ZoneId.of("Europe/Paris"));
        String formattedZonedDateTime = DateTimeFormatter.ISO_INSTANT.format(zonedDateTime);

        return formattedZonedDateTime;
    }

    public String getTomorrowKey() {

        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "tg.properties";


        Properties appProps = new Properties();
        try {
            appProps.load(new FileInputStream(appConfigPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return appProps.getProperty("tomorrowio.key");
    }
}
