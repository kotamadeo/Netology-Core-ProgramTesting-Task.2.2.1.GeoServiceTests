package com.gmail.at.kotamadeo;

import com.gmail.at.kotamadeo.models.Country;
import com.gmail.at.kotamadeo.models.Location;
import com.gmail.at.kotamadeo.sender.message.MessageSender;
import com.gmail.at.kotamadeo.sender.message.MessageSenderImpl;
import com.gmail.at.kotamadeo.services.GeoService;
import com.gmail.at.kotamadeo.services.GeoServiceImpl;
import com.gmail.at.kotamadeo.services.LocalizationService;
import com.gmail.at.kotamadeo.services.LocalizationServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static com.gmail.at.kotamadeo.models.Country.RUSSIA;
import static com.gmail.at.kotamadeo.models.Country.USA;
import static com.gmail.at.kotamadeo.services.GeoServiceImpl.*;

class MessageSenderTest {
    private static MessageSender messageSender;

    @BeforeAll
    public static void initSuite() {
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(LOCALHOST)).thenReturn(new Location(null, null, null, 0));
        Mockito.when(geoService.byIp(MOSCOW_IP)).thenReturn(new Location("Moscow", RUSSIA, "Lenina", 15));
        Mockito.when(geoService.byIp(NEW_YORK_IP)).thenReturn(new Location("New York", USA, " 10th Avenue", 32));
        Mockito.when(geoService.byIp(Mockito.startsWith("172."))).thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        Mockito.when(geoService.byIp(Mockito.startsWith("96."))).thenReturn(new Location("New York", USA, null, 0));
        Mockito.when(geoService.byCoordinates(Mockito.anyDouble(), Mockito.anyDouble())).thenThrow(new RuntimeException("Not implemented"));
        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(RUSSIA)).thenReturn("Добро пожаловать");
        Mockito.when(localizationService.locale(USA)).thenReturn("Welcome");
        messageSender = new MessageSenderImpl(geoService, localizationService);
    }

    @AfterAll
    public static void completeSuite() {
        System.out.println("Все тесты пройдены успешно!");
    }

    @Test
    @DisplayName("Test send() for RUSSIA ip")
    void sendRussiaIpTest(TestInfo sendRussiaIpTestInfo) {
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");
        Assertions.assertEquals("Добро пожаловать", messageSender.send(headers),
                sendRussiaIpTestInfo.getDisplayName() + " упал с ошибкой!");
        System.out.println("\n" + sendRussiaIpTestInfo.getDisplayName() + " завершен!");
    }

    @Test
    @DisplayName("Test send() for USA ip")
    void sendUsaIpTest(TestInfo sendUsaIpTestInfo) {
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");
        Assertions.assertEquals("Welcome", messageSender.send(headers),
                sendUsaIpTestInfo.getDisplayName() + " упал с ошибкой!");
        System.out.println("\n" + sendUsaIpTestInfo.getDisplayName() + " завершен!");
    }
}
