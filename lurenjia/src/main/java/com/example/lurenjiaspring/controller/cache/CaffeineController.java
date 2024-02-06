package com.example.lurenjiaspring.controller.cache;

import com.example.lurenjiaspring.config.cache.AddressDTO;
import com.example.lurenjiaspring.config.cache.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController()
public class CaffeineController {
    @Autowired
    private AddressService addressService;

    @Autowired
    private CacheManager cacheManager;

    @GetMapping("/{addressId}")
    public AddressDTO getAddress(@PathVariable String addressId) {
        return addressService.getAddress(addressId);
    }

    @GetMapping("/cache/{addressId}")
    public AddressDTO findAddressFromCache(@PathVariable String addressId) {
        Cache addressCache = cacheManager.getCache("address_cache");
        if (addressCache != null) {
            return Optional.ofNullable(addressCache.get(addressId)).map(i->(AddressDTO)i.get()).orElse(new AddressDTO());
        }
        return null;
    }
}