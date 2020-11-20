package com.sparta.glowupgirls.sakilaproject.services;

import com.sparta.glowupgirls.sakilaproject.entities.Address;
import com.sparta.glowupgirls.sakilaproject.entities.City;
import com.sparta.glowupgirls.sakilaproject.entities.Country;
import com.sparta.glowupgirls.sakilaproject.repositories.AddressRepository;
import com.sparta.glowupgirls.sakilaproject.repositories.CityRepository;
import com.sparta.glowupgirls.sakilaproject.repositories.CountryRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    private AddressRepository addressRepository;
    private CityRepository cityRepository;
    private CountryRepository countryRepository;

    public AddressService(AddressRepository addressRepository, CityRepository cityRepository, CountryRepository countryRepository) {
        this.addressRepository = addressRepository;
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }


    public Address getAddressById(Integer addressId) {
        if (addressRepository.findById(addressId).isPresent()) {
           return addressRepository.findById(addressId).get();
        }
        return null;
    }

    public City getCityByCityId(Integer cityId) {
        if (cityRepository.findById(cityId).isPresent()) {
            return cityRepository.findById(cityId).get();
        }
        return null;
    }

    public Country getCountryByCountryId(Integer countryId) {
        if (countryRepository.findById(countryId).isPresent()) {
            return countryRepository.findById(countryId).get();
        }
        return null;
    }
}
