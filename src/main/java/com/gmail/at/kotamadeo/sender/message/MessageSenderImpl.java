package com.gmail.at.kotamadeo.sender.message;

import com.gmail.at.kotamadeo.models.Country;
import com.gmail.at.kotamadeo.models.Location;
import com.gmail.at.kotamadeo.services.GeoService;
import com.gmail.at.kotamadeo.services.LocalizationService;

import java.util.Map;

public class MessageSenderImpl implements MessageSender {
    public static final String IP_ADDRESS_HEADER = "x-real-ip";
    private final GeoService geoService;

    private final LocalizationService localizationService;

    public MessageSenderImpl(GeoService geoService, LocalizationService localizationService) {
        this.geoService = geoService;
        this.localizationService = localizationService;
    }

    @Override
    public String send(Map<String, String> headers) {
        String ipAddress = String.valueOf(headers.get(IP_ADDRESS_HEADER));
        if (ipAddress != null && !ipAddress.isEmpty()) {
            Location location = geoService.byIp(ipAddress);
            System.out.printf("Отправлено сообщение: %s", localizationService.locale(location.getCountry()));
            return localizationService.locale(location.getCountry());
        }
        return localizationService.locale(Country.USA);
    }
}
