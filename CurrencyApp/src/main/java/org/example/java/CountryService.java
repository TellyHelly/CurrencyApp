package org.example.java;

import org.json.JSONArray;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CountryService {

    public Map<String, String> getCountriesWithNameAndCurrency() {
        CountryIntegration countryIntegration = new CountryIntegration();
        String unparsedString = countryIntegration.getCountriesInEuropeWithCurrency().trim();

        JSONArray jsonArray = new JSONArray(unparsedString);
        Map<String, String> countries = new HashMap<String, String>();

        for (int i = 0; i < jsonArray.length(); i++) {
            String country = jsonArray.getJSONObject(i).getJSONObject("name").get("common").toString();
            String currency = jsonArray.getJSONObject(i).getJSONObject("currencies").keys().next();
            countries.put(country, currency);
        }

        return countries;
    }

    public LinkedHashMap<String, String> sortCountries(Map<String, String> countries, CountryToSort sortBy, AscDesc ascDesc) {
        Stream<Map.Entry<String, String>> countriesStream = countries.entrySet().stream();

        if (sortBy == CountryToSort.NAME)  {
            if (ascDesc == AscDesc.ASCENDING) {
                return countriesStream.sorted(Map.Entry.<String, String>comparingByKey())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
            }
            else {
                return countriesStream.sorted(Map.Entry.<String, String>comparingByKey().reversed())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
            }
        }
        else {
            if (ascDesc == AscDesc.ASCENDING) {
                return countriesStream.sorted(Map.Entry.comparingByValue())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
            }
            else {
               return countriesStream.sorted(Map.Entry.<String, String>comparingByValue().reversed())
                       .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
            }
        }
    }

    public Map<String, List<String>> getCurrenciesWithCountryName() {
        CountryIntegration countryIntegration = new CountryIntegration();
        String unparsedCurrencyString = countryIntegration.getCountriesWithCurrencies();

        JSONArray jsonArray = new JSONArray(unparsedCurrencyString);
        Map<String, List<String>> currencies = new HashMap<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            String currency = jsonArray.getJSONObject(i).getJSONObject("currencies").keys().next();
            String country = jsonArray.getJSONObject(i).getJSONObject("name").get("common").toString();
            currencies.putIfAbsent(currency, new ArrayList<>());
            currencies.get(currency).add(country);
        }
        return currencies;
    }

}
