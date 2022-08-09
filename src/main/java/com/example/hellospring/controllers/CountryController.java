package com.example.hellospring.controllers;

import com.example.hellospring.beans.Country;
import com.example.hellospring.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CountryController {
    @Autowired
    CountryService countryService;

    @GetMapping("/getCountries")
    public List getCountries(){
        return countryService.getAllCountries();
    }
    @GetMapping("/getCountries/{country_id}")
    public Country getCountriesById(@PathVariable(value = "country_id") int country_id){
        return countryService.getCountryById(country_id);
    }
    @GetMapping("/getCountries/country_name")
    public Country getCountriesByName(@RequestParam(value = "country_name") String country_name){
        return countryService.getCountryByName(country_name);
    }
    @PostMapping("/addCountry")
    public Country addCountry(@RequestBody Country country){

        return countryService.addCountry(country);
    }
    @PutMapping("/updateCountry")
    public Country updateCountry(@RequestBody Country country){
        return countryService.updateCountry(country);
    }
    @DeleteMapping("/deleteCountry/{country_id}")
    public AddResponse deleteCountry(@PathVariable(value = "country_id") int id){
        return countryService.deleteCountry(id);
    }



}
