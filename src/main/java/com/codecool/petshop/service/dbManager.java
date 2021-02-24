package com.codecool.petshop.service;

import com.codecool.petshop.dao.*;
import com.codecool.petshop.view.UserInterface;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class dbManager {
    UserInterface ui;
    DataSource dataSource;
    private FoodDao foodDao;
    private StockDao stockDao;

    public dbManager(UserInterface ui) {
        this.ui = ui;
    }

    public void setup() throws SQLException {
        dataSource = connect();
        foodDao = new FoodDaoJdbc(dataSource);
        stockDao = new StockDaoJdbc(dataSource);
        executeExternalQuery(dataSource,"/drop_tables.sql");
        executeExternalQuery(dataSource,"/create_tables.sql");
        executeExternalQuery(dataSource, "/add_data.sql");
        run();
    }

    public void run() {
        boolean running = true;

        while (running) {
            ui.printTitle("Main menu");
            ui.printOption('l', "Customer menu");
            ui.printOption('e', "Admin menu");
            ui.printOption('q', "Quit");
            char choice = ui.choice("leq");

            switch (choice) {
                case 'l':
                    new FoodService(ui, foodDao, stockDao).run();
                    break;
                case 'e':
                    new StockService(ui, stockDao).run();
                    break;
                case 'q':
                    running = false;
                    break;
            }
        }
    }

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String dbName = "petshop";
        String user = System.getenv("PSQL_USER");
        String password = System.getenv("PSQL_PASSWORD");

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        ui.println("Trying to connect");
        dataSource.getConnection().close();
        ui.println("Connection ok.");

        return dataSource;
    }

    private void executeExternalQuery(DataSource dataSource, String relativeResourcePath) {
        try (Connection conn = dataSource.getConnection()) {
            InputStream is = dbManager.class.getResourceAsStream(relativeResourcePath);
            Scanner scanner = new Scanner(is);
            scanner.useDelimiter(";");
            while (scanner.hasNext()) {
                String statement = scanner.next();
                conn.createStatement().execute(statement);
            }
            is.close();
        } catch (IOException | SQLException e) {
            throw new RuntimeException("Error while executing query: " + relativeResourcePath, e);
        }
    }
}
