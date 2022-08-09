package com.example.hellospring.repositories;

import com.example.hellospring.beans.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,Integer>
{

}
