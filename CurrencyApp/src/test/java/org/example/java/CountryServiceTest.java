package org.example.java;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CountryServiceTest {
    Map<String, String> unsortedCountries = new HashMap<String, String>();
    CountryService countryService = new CountryService();

    @org.junit.jupiter.api.Test
    void sortCountriesByNameDescending() {
        unsortedCountries.put("Norway", "NOK");
        unsortedCountries.put("Sweden", "SEK");
        unsortedCountries.put("Germany", "EUR");

        LinkedHashMap<String, String> sortedCountries =
                countryService.sortCountries(unsortedCountries, CountryToSort.NAME, AscDesc.DESCENDING);
        assertEquals(sortedCountries.size(), 3);
        assertEquals(sortedCountries.keySet().toString(), "[Sweden, Norway, Germany]");
    }
}