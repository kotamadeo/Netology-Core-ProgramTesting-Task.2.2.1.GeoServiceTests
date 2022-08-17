package com.gmail.at.kotamadeo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    private String city;
    private Country country;
    private String street;
    private int building;
}
