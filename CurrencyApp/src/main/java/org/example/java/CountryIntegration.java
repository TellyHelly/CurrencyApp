package org.example.java;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CountryIntegration {
    private String europeanCountriesCurrenciesUrl = "https://restcountries.com/v3.1/region/europe?fields=name,currencies";
    private String worldwideCountriesCurrenciesUrl = "https://restcountries.com/v3.1/independent?status=true&fields=name,currencies";

    public String getCountriesInEuropeWithCurrency() {
        return getRequest(europeanCountriesCurrenciesUrl);
    }

    public String getCountriesWithCurrencies() {
        return getRequest(worldwideCountriesCurrenciesUrl);
    }

    private String getRequest(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        String jsonCountries = "";

        try {
            jsonCountries = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return jsonCountries;
    }
}
