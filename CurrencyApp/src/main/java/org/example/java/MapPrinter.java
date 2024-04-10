package org.example.java;

import java.util.List;
import java.util.Map;

public class MapPrinter {

    public void printCountryToCurrencyList(Map<String, String> map) {
        map.forEach((country, currency) -> System.out.printf("%-23s : %s%n", country, currency));
    }

    public void printCurrencyToCountryList(Map<String, List<String>> map) {
        map.forEach((currency, countryList) -> System.out.printf("%-3s : %s%n", currency, countryList));
    }
}
