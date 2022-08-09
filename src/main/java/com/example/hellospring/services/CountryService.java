package com.example.hellospring.services;


import com.example.hellospring.beans.Country;
import com.example.hellospring.controllers.AddResponse;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Configuration
public class CountryService {

    static HashMap<Integer, Country> countryMap;

    public CountryService(){
        countryMap  = new HashMap<>();
        Country indiaCountry = new Country(1,"India","Delhi");
        Country usaCountry = new Country(2,"USA","Washington");
        Country ukCountry = new Country(3,"UK","London");

        countryMap.put(1, indiaCountry);
        countryMap.put(2, usaCountry);
        countryMap.put(3, ukCountry);

    }

    public List getAllCountries(){
        List listCountries = new ArrayList<>(countryMap.values());

        return listCountries;
    }
    public Country getCountryById(int country_id){
        Country country = countryMap.get(country_id);
        return country;
    }
    public Country getCountryByName(String country_name){
        Country country = null;
        for(int i : countryMap.keySet()){
            if (countryMap.get(i).getCountryName().toLowerCase().equals(country_name.toLowerCase()) ){
                country = countryMap.get(i);
            }
        }
        return country;
     }
    public Country addCountry(Country country){
        country.setId(getMaxId());
        Country addcountry = countryMap.put(country.getId(),country);
        return addcountry;
    }

    public Country updateCountry(Country country){
        if (country.getId()>0)
             countryMap.put(country.getId(),country);
        return country;
    }
    public AddResponse deleteCountry(int id){
        countryMap.remove(id);
        AddResponse addResponse = new AddResponse(id,"Success to delete");
        return addResponse;
    }

    public static int getMaxId(){
        int max = 0;
                for(int id : countryMap.keySet())
                    if (max<=id)
                        max = id;
        return max+1;
    }
}
