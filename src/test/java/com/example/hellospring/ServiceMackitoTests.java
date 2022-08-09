package com.example.hellospring;

import com.example.hellospring.beans.Country;
import com.example.hellospring.repositories.CountryRepository;
import com.example.hellospring.services.CountryDatabaseService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {ServiceMackitoTests.class})
public class ServiceMackitoTests {
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

    }
}
