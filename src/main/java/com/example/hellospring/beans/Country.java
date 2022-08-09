package com.example.hellospring.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="country")
public class Country {
    @Id
    @Column(name="country_id")
    int id;
    @Column(name="capital")
    String countryCapital;
    @Column(name="country_name")
    String countryName;
    public Country(int id, String countryCapital, String countryName){
        this.id = id;
        this.countryCapital = countryCapital;
        this.countryName = countryName;
    }

    public Country() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryCapital() {
        return countryCapital;
    }

    public void setCountryCapital(String countryCapital) {
        this.countryCapital = countryCapital;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
