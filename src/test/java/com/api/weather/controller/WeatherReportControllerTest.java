package com.api.weather.controller;

import com.api.weather.data.WeatherReport;
import com.api.weather.service.WeatherReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WeatherReportController.class)
public class WeatherReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherReportService weatherReportService;

    @BeforeEach
    public void setup() {
        // Inicializa los mocks
        MockitoAnnotations.openMocks(this);

        // Mock response
        WeatherReport mockWeatherReport = new WeatherReport();
        mockWeatherReport.setTemperature(0.0);
        mockWeatherReport.setHumidity(74.0);

        when(weatherReportService.getWeatherReport(anyDouble(), anyDouble())).thenReturn(mockWeatherReport);
    }

    @Test
    public void testGetWeatherReport() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/api/weather-report")
                        .param("latitude", "37.8267")
                        .param("longitude", "-122.4233"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"temperature\":0.0,\"humidity\":74.0}"));
    }

}
