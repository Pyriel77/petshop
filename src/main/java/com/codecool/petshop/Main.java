package com.codecool.petshop;

import com.codecool.petshop.service.dbManager;
import com.codecool.petshop.view.UserInterface;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        UserInterface ui = new UserInterface(System.in, System.out);
        dbManager dBmanager = new dbManager(ui);
        dBmanager.setup();
    }


}
