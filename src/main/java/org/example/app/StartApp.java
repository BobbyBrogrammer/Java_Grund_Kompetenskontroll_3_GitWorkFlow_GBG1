package org.example.app;


import org.example.menus.Menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.example.service.InitializationService;


public class StartApp {
    private static final Logger logger = LoggerFactory.getLogger(StartApp.class);

    public static void main(String[] args) {
            logger.info("Programmet startar. ");
           AppConfig app = new AppConfig();
        InitializationService initService = app.getInitializationService();
        initService.loadInitialData();
        logger.info("Exempeldata laddades in.");

        Menu menu = app.menuRunner();
           menu.showMainMenu();
        logger.info("Programmet avslutades.");
    }
}
