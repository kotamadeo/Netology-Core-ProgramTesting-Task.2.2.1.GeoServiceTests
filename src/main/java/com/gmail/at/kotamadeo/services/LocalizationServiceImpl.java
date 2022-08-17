package com.gmail.at.kotamadeo.services;

import com.gmail.at.kotamadeo.models.Country;

public class LocalizationServiceImpl implements LocalizationService {
    @Override
    public String locale(Country country) {
        return switch (country) {
            case RUSSIA -> "Добро пожаловать";
            case GERMANY -> "Willkommen";
            case BRAZIL -> "Bem-vindo";
            default -> "Welcome";
        };
    }
}
