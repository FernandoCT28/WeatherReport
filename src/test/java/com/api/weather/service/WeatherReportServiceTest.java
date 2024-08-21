package com.api.weather.service;

import com.api.weather.data.WeatherApiResponse;
import com.api.weather.data.WeatherReport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

public class WeatherReportServiceTest {

    @InjectMocks
    private WeatherReportService weatherReportService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetWeatherReport() {
        // Arrange
        double latitude = 37.8267;
        double longitude = -122.4233;

        WeatherApiResponse.Main main = new WeatherApiResponse.Main();
        main.setTemperature(0.0);
        main.setHumidity(73.0);

        WeatherApiResponse response = new WeatherApiResponse();
        response.setMain(main);

        when(restTemplate.getForObject(anyString(), any(Class.class))).thenReturn(response);

        // Act
        WeatherReport weatherReport = weatherReportService.getWeatherReport(latitude, longitude);

        // Assert
        assertEquals(0.0, weatherReport.getTemperature());
        assertEquals(73.0, weatherReport.getHumidity());
    }

}
