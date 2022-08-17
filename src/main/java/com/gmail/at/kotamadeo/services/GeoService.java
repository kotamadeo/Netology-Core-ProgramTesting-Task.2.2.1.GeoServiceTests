package com.gmail.at.kotamadeo.services;

import com.gmail.at.kotamadeo.models.Location;

public interface GeoService {
    Location byIp(String ip);

    Location byCoordinates(double latitude, double longitude);
}
