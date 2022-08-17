package com.gmail.at.kotamadeo;

import com.gmail.at.kotamadeo.models.Country;
import com.gmail.at.kotamadeo.models.Location;
import com.gmail.at.kotamadeo.services.GeoService;
import com.gmail.at.kotamadeo.services.GeoServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import static com.gmail.at.kotamadeo.models.Country.RUSSIA;
import static com.gmail.at.kotamadeo.models.Country.USA;
import static com.gmail.at.kotamadeo.services.GeoServiceImpl.*;
import static org.mockito.Mockito.when;

class GeoServiceTest {
    private static GeoService geoService;

    @BeforeAll
    static void initSuite() {
        geoService = Mockito.mock(GeoServiceImpl.class);
        when(geoService.byIp(LOCALHOST)).thenReturn(new Location(null, null, null, 0));
        when(geoService.byIp(MOSCOW_IP)).thenReturn(new Location("Moscow", RUSSIA, "Lenina", 15));
        when(geoService.byIp(NEW_YORK_IP)).thenReturn(new Location("New York", USA, " 10th Avenue", 32));
        when(geoService.byIp(Mockito.startsWith("172."))).thenReturn(new Location("Moscow", RUSSIA, null, 0));
        when(geoService.byIp(Mockito.startsWith("96."))).thenReturn(new Location("New York", Country.USA, null, 0));
        when(geoService.byCoordinates(Mockito.anyDouble(), Mockito.anyDouble())).thenThrow(new RuntimeException("Not implemented"));
        System.out.println("Начинаем тесты");
    }

    @AfterAll
    static void completeSuite() {
        System.out.println("Все тесты пройдены успешно!");
    }

    @Test
    @DisplayName("Test byIp() for localhost")
    void byIpLocalhostTest(TestInfo byIpLocalhostTestInfo) {
        Assertions.assertNull(geoService.byIp("127.0.0.1").getCountry(), byIpLocalhostTestInfo.getDisplayName() + " упал с ошибкой!");
        System.out.println(byIpLocalhostTestInfo.getDisplayName() + " завершен!");
    }

    @ParameterizedTest
    @DisplayName("Test byIp() for russian IP ")
    @ValueSource(strings = {"172.0.32.11", "172.123.12.19"})
    void byIpRussiaTest(String ip) {
        Assertions.assertEquals(RUSSIA, geoService.byIp(ip).getCountry());
        System.out.println("Тест по айпи (" + ip + ") завершен!");
    }

    @ParameterizedTest
    @DisplayName("Test byIp() for USA IP ")
    @ValueSource(strings = {"96.44.183.149", "96.25.145.201"})
    void byIpUsaTest(String ip) {
        Assertions.assertEquals(USA, geoService.byIp(ip).getCountry());
        System.out.println("Тест по айпи (" + ip + ") завершен!");
    }

    @Test
    @DisplayName("Test byCoordinatesTest()")
    void byCoordinatesTest(TestInfo byCoordinatesTestInfo) {
        Assertions.assertThrows(RuntimeException.class, () -> geoService.byCoordinates(124.56, 45.78),
                byCoordinatesTestInfo.getDisplayName() + " упал с ошибкой!");
        System.out.println(byCoordinatesTestInfo.getDisplayName() + " завершен!");
    }
}
