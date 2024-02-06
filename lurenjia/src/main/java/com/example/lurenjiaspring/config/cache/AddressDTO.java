package com.example.lurenjiaspring.config.cache;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressDTO {
    private int id;
    private String address;
    public AddressDTO(int id, String address) {
        this.address = address;
        this.id = id;
    }
}
