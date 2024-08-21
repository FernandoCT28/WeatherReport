package com.api.weather.service;

import com.api.weather.data.WeatherApiResponse;
import com.api.weather.data.WeatherReport;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class WeatherReportService {
    private static final String API_KEY = "a4d21cac8652555b84672b3eb78de59a";
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather";

    public WeatherReport getWeatherReport(double latitude, double longitude) {
        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + "?lat=" + latitude + "&lon=" + longitude + "&appid=" + API_KEY;
        WeatherApiResponse response = restTemplate.getForObject(url, WeatherApiResponse.class);

        WeatherReport report = new WeatherReport();
        WeatherApiResponse.Main main = response.getMain();
        report.setTemperature(main.getTemperature());
        report.setHumidity(main.getHumidity());

        return report;
    }
}
