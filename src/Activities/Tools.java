package Activities;

import Classes.*;
import Classes.Date;

import java.sql.*;
import java.util.ArrayList;

public class Tools {
    public static ArrayList<Hotel> hotels;
    public static ArrayList<Restaurant> restaurants;
    public static ArrayList<Entertaining> entertainings;
    public static ArrayList<ThingsToDo> thingsToDos;
    public static ArrayList<Client> clients;

    public static String filledStar = "\"M121.215,44.212l-34.899-3.3c-2.2-0.2-4.101-1.6-5-3.7l-12.5-30.3c-2-5-9.101-5-11.101,0l-12.4,30.3 c-0.8,2.1-2.8,3.5-5,3.7l-34.9,3.3c-5.2,0.5-7.3,7-3.4,10.5l26.3,23.1c1.7,1.5,2.4,3.7,1.9,5.9l-7.9,32.399 c-1.2,5.101,4.3,9.3,8.9,6.601l29.1-17.101c1.9-1.1,4.2-1.1,6.1,0l29.101,17.101c4.6,2.699,10.1-1.4,8.899-6.601l-7.8-32.399 c-0.5-2.2,0.2-4.4,1.9-5.9l26.3-23.1C128.615,51.212,126.415,44.712,121.215,44.212z\"";
    public static String emptyStar = "\"M453.443 160.214l-119.7-8.6c-2.5-0.2-4.7-1.7-5.6-4.1l-45.6-111c-6.3-15.3-20.5-24.8-37.1-24.8s-30.7 9.5-37.1 24.9   l-45.7 111.1c-1 2.3-3.1 3.9-5.6 4.1l-119.5 8.6c-16.2 0.9-30 11.5-35.3 27.1c-5.4 15.8-0.9 32.4 11.9 43.3l92.5 76.4   c1.9 1.6 2.8 4.2 2.2 6.6l-28.2 115.4c-3.3 12-1 24.4 6.3 34.1c7.5 9.8 19.2 15.7 31.5 15.7c7.6 0 15-2.3 21.1-6.4l101.1-62.9   c2.1-1.3 4.8-1.3 6.9 0l102.1 62.7c6.4 4.3 13.8 6.6 21.3 6.6c11.5 0 22.9-5.5 30.3-14.8c7.7-9.6 10.5-22.1 7.7-34.6l-28.3-115.7   c-0.6-2.5 0.3-5.1 2.2-6.7l93.9-76.6c12.6-10.8 17.1-27.4 11.8-43.2C483.343 171.814 469.443 161.114 453.443 160.214z    M461.143 211.714l-93.7 76.5c-9.2 7.5-13.4 19.9-10.5 31.5l28.3 115.5c1.5 6.8-1.2 11.5-3 13.7c-2.8 3.5-7 5.7-11.2 5.7   c-2.6 0-5.2-0.8-8-2.7l-102.5-63c-4.9-3-10.5-4.6-16.3-4.6s-11.5 1.6-16.4 4.7l-101.5 63.1c-6.5 4.3-15.2 2.4-19.8-3.6   c-1.9-2.5-3.9-6.8-2.1-13.1l28.3-115.8c2.8-11.5-1.3-23.8-10.4-31.3l-92.3-76.3c-7.3-6.3-5.3-14.4-4.5-16.7s4.1-10 13.7-10.5   l119.7-8.6c11.8-0.9 22-8.2 26.5-19.2l45.7-111.1c3.7-9 12-9.7 14.4-9.7c2.4 0 10.7 0.7 14.4 9.7l45.7 111.1   c4.5 11 14.7 18.3 26.5 19.2l119.9 8.7c9.4 0.5 12.7 8.2 13.5 10.5C466.143 197.514 468.143 205.614 461.143 211.714z\"";

    public static void init() {
        hotels = new ArrayList<>();
        restaurants = new ArrayList<>();
        entertainings = new ArrayList<>();
        thingsToDos = new ArrayList<>();
        clients = new ArrayList<>();
    }

    public static Client searchClient(String username) {
        for (Client client : clients)
            if (client.getUsername().equals(username))
                return client;
        return null;
    }
}

class SQLDataBase {
    private static String path = "jdbc:sqlite:src//Databases//database.db";
    private static Connection connection = null;

    public static void connect() {
        try {
            connection = DriverManager.getConnection(path);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addClient(Client client) {
        try {
            Statement statement = connection.createStatement();
            String query = String.format("INSERT INTO Client(Username,Password,Credits,ObjectsId,StartDate,EndDate) VALUES('%s','%s','%d','%s','%s','%s');", client.getUsername(), client.getPassword(), client.getNumberOfCredits(), client.getIds(), client.getStartDate().toString(), client.getEndDate().toString());
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void editClient(Client client, String username) {
        try {
            Statement statement = connection.createStatement();
            String query = String.format("UPDATE Client SET Username='%s', Password='%s', Credits='%d', ObjectsId='%s', StartDate='%s', EndDate='%s' WHERE Username='%s';", client.getUsername(), client.getPassword(), client.getNumberOfCredits(), client.getIds(), client.getStartDate().toString(), client.getEndDate().toString(), username);
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeClient(String username) {
        try {
            Statement statement = connection.createStatement();
            String query = String.format("DELETE FROM Client WHERE Username='%s'", username);
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void loadClients() {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM Client;";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                String username = result.getString("Username");
                String password = result.getString("Password");
                String credits = result.getString("Credits");
                String objects = result.getString("ObjectsId");
                String startDate = result.getString("StartDate");
                String endDate = result.getString("EndDate");
                Client client = new Client();
                client.setUsername(username);
                client.setPassword(password);
                client.setNumberOfCredits(Integer.parseInt(credits));
                client.setObjectId(objects);
                client.setStartDate(new Date(startDate));
                client.setEndDate(new Date(endDate));
                Tools.clients.add(client);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}