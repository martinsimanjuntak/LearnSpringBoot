package com.example.hellospring.controllers;

import com.example.hellospring.beans.Country;
import com.example.hellospring.services.CountryDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CountryDatabaseController {
    @Autowired
    CountryDatabaseService countryDatabaseService;

    @GetMapping("/getCountriesDatabase")
    public List<Country> getAllCountries(){
       return countryDatabaseService.getAllCountries();
    }
    @GetMapping("/getCountriesDatabase/{id}")
    public ResponseEntity<Country> getCountriesById(@PathVariable(value = "id") int country_id){
        try {
            Country country= countryDatabaseService.getCountryById(country_id);
                return new ResponseEntity<Country>(country, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getCountriesDatabase/country_name")
    public ResponseEntity<Country> getCountriesByName(@RequestParam(value = "country_name") String country_name){
        try {
            Country country= countryDatabaseService.getCountryByName(country_name);
            return new ResponseEntity<Country>(country, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/addCountryDatabase")
    public Country addCountry(@RequestBody Country country){

        return countryDatabaseService.addCountry(country);
    }
    @PutMapping("/updateCountryDatabase/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable(value = "id") int id,@RequestBody Country country){
        try {
            Country existCountry =countryDatabaseService.getCountryById(id);
            existCountry.setCountryName(country.getCountryName());
            existCountry.setCountryCapital(country.getCountryCapital());
            Country countryUdate = countryDatabaseService.updateCountry(existCountry);
            return new ResponseEntity<Country>(countryUdate, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Country>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/deleteCountryDatabase/{country_id}")
    public AddResponse deleteCountry(@PathVariable(value = "country_id") int id){
        return countryDatabaseService.deleteCountry(id);
    }
}
