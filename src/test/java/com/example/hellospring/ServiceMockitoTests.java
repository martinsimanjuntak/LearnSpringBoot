package com.example.hellospring;

import com.example.hellospring.beans.Country;
import com.example.hellospring.controllers.AddResponse;
import com.example.hellospring.repositories.CountryRepository;
import com.example.hellospring.services.CountryDatabaseService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
public class ServiceMockitoTests {
    @Mock
    CountryRepository countryRepository;

    @InjectMocks
    CountryDatabaseService countryDatabaseService;
    public List<Country> countryList;

    @Test
    @Order(1)
    public void test_getAllCountries(){
        countryList = new ArrayList<>();
        countryList.add(new Country(1,"India","delhi"));
        countryList.add(new Country(2,"USA","Washington"));

        when(countryRepository.findAll()).thenReturn(countryList);
        countryDatabaseService.getAllCountries();
        assertEquals(2, countryDatabaseService.getAllCountries().size());
        log.warn("Success test_getAllCountries");


    }
    @Test
    @Order(2)
    public void test_getCountryById(){
        countryList = new ArrayList<>();
        countryList.add(new Country(1,"India","delhi"));
        countryList.add(new Country(2,"USA","Washington"));
        int countryId = 1;
        when(countryRepository.findAll()).thenReturn(countryList);
        assertEquals(1, countryDatabaseService.getCountryById(countryId).getId());
        log.warn("Success test_getCountryById");
    }
    @Test
    @Order(3)
    public void test_getCountryByName(){
        countryList = new ArrayList<>();
        countryList.add(new Country(1,"India","delhi"));
        countryList.add(new Country(2,"USA","Washington"));
        String countryName = "delhi";
        when(countryRepository.findAll()).thenReturn(countryList);
        assertEquals(countryName, countryDatabaseService.getCountryByName(countryName).getCountryName());
        log.warn("Success test_getCountryByName");
    }

    @Test
    @Order(4)
    public void test_addCountry(){
        Country country = new Country(3, "Indonesia", "Jakarta");
        when(countryRepository.save(country)).thenReturn(country);
        assertEquals(country, countryDatabaseService.addCountry(country));
        log.warn("Success test_addCountry");
    }

    @Test
    @Order(5)
    public void test_updateCountry(){
        Country country = new Country(3, "Indonesia", "Jakarta");
        when(countryRepository.save(country)).thenReturn(country);
        assertEquals(country, countryDatabaseService.updateCountry(country));
        log.warn("Success test_updateCountry");
    }

    @Test
    @Order(6)
    public void test_deleteCountry(){
        countryList = new ArrayList<>();
        countryList.add(new Country(1,"India","delhi"));
        countryList.add(new Country(2,"USA","Washington"));
        countryDatabaseService.deleteCountry(1);
        verify(countryRepository,times(1)).deleteById(1);
        log.warn("Success test_deleteCountry");
    }
}
