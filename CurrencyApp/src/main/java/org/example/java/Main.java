package org.example.java;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        CountryService service = new CountryService();
        MapPrinter mapPrinter = new MapPrinter();
        Scanner scanner = new Scanner(System.in);
        Map<String, String> countries = new HashMap<String, String>();
        Map<String, List<String>> currencies = new HashMap<>();

        System.out.println("To get a list of European countries with their currency type 'europe'");
        System.out.println("To sort this list on name type 'name' and 'ascending' or 'descending");
        System.out.println("To sort this list on currency type 'currency' and 'ascending' or 'descending'");
        System.out.println("To get a list of all currencies in the world and the countries where they are used type 'currencies");

        while (scanner.hasNextLine()) {
            String command = scanner.next();
            if (command.equals("europe")) {
                if (countries.isEmpty()) countries = service.getCountriesWithNameAndCurrency();
                mapPrinter.printCountryToCurrencyList(countries);
            } else if (command.equals("name")) {
                String ascDesc = scanner.next();
                if (ascDesc.equals("ascending")) {
                    countries = service.sortCountries(countries, CountryToSort.NAME, AscDesc.ASCENDING);
                    mapPrinter.printCountryToCurrencyList(countries);
                } else if (ascDesc.equals("descending")) {
                    countries = service.sortCountries(countries, CountryToSort.NAME, AscDesc.DESCENDING);
                    mapPrinter.printCountryToCurrencyList(countries);
                }
            } else if (command.equals("currency")) {
                String ascDesc = scanner.next();
                if (ascDesc.equals("ascending")) {
                    countries = service.sortCountries(countries, CountryToSort.CURRENCY, AscDesc.ASCENDING);
                    mapPrinter.printCountryToCurrencyList(countries);
                } else if (ascDesc.equals("descending")) {
                    countries = service.sortCountries(countries, CountryToSort.CURRENCY, AscDesc.DESCENDING);
                    mapPrinter.printCountryToCurrencyList(countries);
                }
            } else if (command.equals("currencies")) {
                if (currencies.isEmpty()) currencies = service.getCurrenciesWithCountryName();
                mapPrinter.printCurrencyToCountryList(currencies);
            }
        }
    }
}