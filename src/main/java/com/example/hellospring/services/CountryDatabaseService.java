package com.example.hellospring.services;

import com.example.hellospring.beans.Country;
import com.example.hellospring.controllers.AddResponse;
import com.example.hellospring.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class CountryDatabaseService
{
    @Autowired
    CountryRepository countryRepository;

    public List getAllCountries(){
        return countryRepository.findAll();
    }
    public Country getCountryById(int id){
        List<Country> countries = countryRepository.findAll();
        Country country = null;
        for (Country con : countries){
            if (con.getId() == id){
                country = con;
            }
        }
         return country;
    }
    public Country getCountryByName(String country_name){
       List<Country> countryList=  countryRepository.findAll();
       Country country= null;
       for (Country con : countryList){
           if (con.getCountryName().equalsIgnoreCase(country_name)){
               country = con;
           }
       }
       return country;
    }

    public Country addCountry(Country country){
        country.setId(getMaxId());
        return countryRepository.save(country);
    }
    public  int getMaxId(){
        return countryRepository.findAll().size()+1;

    }

    public Country updateCountry(Country country){
        countryRepository.save(country);
        return country;
    }

    public AddResponse deleteCountry(int id){
        countryRepository.deleteById(id);
        AddResponse addResponse = new AddResponse(id,"Success to delete");
        return addResponse;
    }
}
