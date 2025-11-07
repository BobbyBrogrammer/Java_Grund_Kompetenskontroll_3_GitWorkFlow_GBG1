package org.example.app;


import org.example.menus.Menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.example.service.InitializationService;


/**
 * StartApp är huvudklassen som startar programmet.
 */
public class StartApp {
    // Logger för att skriva ut information i loggen
    private static final Logger logger = LoggerFactory.getLogger(StartApp.class);

    public static void main(String[] args) {
        // Startmeddelande
        logger.info("Programmet startar.");

        // Skapa applikationskonfiguration
        AppConfig app = new AppConfig();

        // Ladda in exempeldata
        InitializationService initService = app.getInitializationService();
        initService.loadInitialData();
        logger.info("Exempeldata laddades in.");

        // Starta huvudmenyn
        Menu menu = app.menuRunner();
        menu.showMainMenu();

        // Programmet avslutas
        logger.info("Programmet avslutades.");
    }
}
