package com.gmail.at.kotamadeo;

import com.gmail.at.kotamadeo.services.LocalizationService;
import com.gmail.at.kotamadeo.services.LocalizationServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import static com.gmail.at.kotamadeo.models.Country.*;
import static org.mockito.Mockito.when;

class LocaleServiceTest {
    private static LocalizationService localizationService;
    private static final String ruWelcome = "Добро пожаловать";
    private static final String defaultWelcome = "Welcome";
    private static final String geWelcome = "Willkommen";
    private static final String brWelcome = "Bem-vindo";

    @BeforeAll
    static void initSuite() {
        localizationService = Mockito.mock(LocalizationServiceImpl.class);
        when(localizationService.locale(RUSSIA)).thenReturn(ruWelcome);
        when(localizationService.locale(USA)).thenReturn(defaultWelcome);
        when(localizationService.locale(BRAZIL)).thenReturn(brWelcome);
        when(localizationService.locale(GERMANY)).thenReturn(geWelcome);
        System.out.println("Начинаем тесты");
    }

    @AfterAll
    static void completeSuite() {
        System.out.println("Все тесты пройдены успешно!");
    }


    @Test
    @DisplayName("Test locale() for RUSSIA")
    void localeRussiaTest(TestInfo localeTestInfo) {
        Assertions.assertEquals(localizationService.locale(RUSSIA), ruWelcome,
                localeTestInfo.getDisplayName() + " упал с ошибкой!");
        System.out.println(localeTestInfo.getDisplayName() + " завершен!");
    }

    @Test
    @DisplayName("Test locale() for USA")
    void localeUsaTest(TestInfo localeTestInfo) {
        Assertions.assertEquals(localizationService.locale(USA), defaultWelcome,
                localeTestInfo.getDisplayName() + " упал с ошибкой!");
        System.out.println(localeTestInfo.getDisplayName() + " завершен!");
    }

    @Test
    @DisplayName("Test locale() for Germany")
    void localeGermanyTest(TestInfo localeTestInfo) {
        Assertions.assertEquals(localizationService.locale(GERMANY), geWelcome,
                localeTestInfo.getDisplayName() + " упал с ошибкой!");
        System.out.println(localeTestInfo.getDisplayName() + " завершен!");
    }

    @Test
    @DisplayName("Test locale() for Brasil")
    void localeBrasilTest(TestInfo localeTestInfo) {
        Assertions.assertEquals(localizationService.locale(BRAZIL), brWelcome,
                localeTestInfo.getDisplayName() + " упал с ошибкой!");
        System.out.println(localeTestInfo.getDisplayName() + " завершен!");
    }
}
