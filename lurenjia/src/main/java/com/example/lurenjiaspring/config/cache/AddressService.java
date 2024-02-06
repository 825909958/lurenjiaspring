package com.example.lurenjiaspring.config.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class AddressService {

    private static final Map<Long, AddressDTO> ADDRESS_TABLE = new HashMap<>();
    static {
        ADDRESS_TABLE.put(1L, new AddressDTO(1, "广东"));
        ADDRESS_TABLE.put(2L, new AddressDTO(2, "深圳"));
        ADDRESS_TABLE.put(3L, new AddressDTO(3, "坂田"));
    }

    @Cacheable(value = "address_cache", key = "#addressId")
    public AddressDTO getAddress(String addressId) {
        log.info("AddressService getAddress, addressId: {}", addressId);
        return ADDRESS_TABLE.get(Long.parseLong(addressId));
    }

}