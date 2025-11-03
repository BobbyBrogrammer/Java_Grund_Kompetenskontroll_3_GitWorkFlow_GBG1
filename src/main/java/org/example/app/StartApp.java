package org.example.app;

import org.example.cli.Menu;

public class StartApp {
    public static void main(String[] args) {
           AppConfig app = new AppConfig();
           Menu menu = app.menuRunner();
           menu.showMainMenu();
    }
}
