package com.gmail.at.kotamadeo.services;

import com.gmail.at.kotamadeo.models.Country;

public interface LocalizationService {
    String locale(Country country);
}
