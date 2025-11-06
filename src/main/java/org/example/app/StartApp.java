package org.example.app;

import com.sun.tools.javac.Main;
import org.example.menus.Menu;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.PhantomReference;

public class StartApp {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
            logger.info("Programmet startar. ");
           AppConfig app = new AppConfig();
           Menu menu = app.menuRunner();
           menu.showMainMenu();
    }
}
