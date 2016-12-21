package Activities;

import Classes.*;
import Classes.Date;
import javafx.scene.control.DatePicker;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

class Tools {
    static ArrayList<Hotel> hotels;
    static ArrayList<Restaurant> restaurants;
    static ArrayList<Entertaining> entertainings;
    static ArrayList<ThingsToDo> thingsToDos;
    static ArrayList<Client> clients;
    static Client client;
    static Hotel hotel;
    static Restaurant restaurant;
    static ThingsToDo thingsToDo;
    static Entertaining entertaining;

    //region Stars
    static String filledStar = "\"M121.215,44.212l-34.899-3.3c-2.2-0.2-4.101-1.6-5-3.7l-12.5-30.3c-2-5-9.101-5-11.101,0l-12.4,30.3 c-0.8,2.1-2.8,3.5-5,3.7l-34.9,3.3c-5.2,0.5-7.3,7-3.4,10.5l26.3,23.1c1.7,1.5,2.4,3.7,1.9,5.9l-7.9,32.399 c-1.2,5.101,4.3,9.3,8.9,6.601l29.1-17.101c1.9-1.1,4.2-1.1,6.1,0l29.101,17.101c4.6,2.699,10.1-1.4,8.899-6.601l-7.8-32.399 c-0.5-2.2,0.2-4.4,1.9-5.9l26.3-23.1C128.615,51.212,126.415,44.712,121.215,44.212z\"";
    static String emptyStar = "\"M453.443 160.214l-119.7-8.6c-2.5-0.2-4.7-1.7-5.6-4.1l-45.6-111c-6.3-15.3-20.5-24.8-37.1-24.8s-30.7 9.5-37.1 24.9   l-45.7 111.1c-1 2.3-3.1 3.9-5.6 4.1l-119.5 8.6c-16.2 0.9-30 11.5-35.3 27.1c-5.4 15.8-0.9 32.4 11.9 43.3l92.5 76.4   c1.9 1.6 2.8 4.2 2.2 6.6l-28.2 115.4c-3.3 12-1 24.4 6.3 34.1c7.5 9.8 19.2 15.7 31.5 15.7c7.6 0 15-2.3 21.1-6.4l101.1-62.9   c2.1-1.3 4.8-1.3 6.9 0l102.1 62.7c6.4 4.3 13.8 6.6 21.3 6.6c11.5 0 22.9-5.5 30.3-14.8c7.7-9.6 10.5-22.1 7.7-34.6l-28.3-115.7   c-0.6-2.5 0.3-5.1 2.2-6.7l93.9-76.6c12.6-10.8 17.1-27.4 11.8-43.2C483.343 171.814 469.443 161.114 453.443 160.214z    M461.143 211.714l-93.7 76.5c-9.2 7.5-13.4 19.9-10.5 31.5l28.3 115.5c1.5 6.8-1.2 11.5-3 13.7c-2.8 3.5-7 5.7-11.2 5.7   c-2.6 0-5.2-0.8-8-2.7l-102.5-63c-4.9-3-10.5-4.6-16.3-4.6s-11.5 1.6-16.4 4.7l-101.5 63.1c-6.5 4.3-15.2 2.4-19.8-3.6   c-1.9-2.5-3.9-6.8-2.1-13.1l28.3-115.8c2.8-11.5-1.3-23.8-10.4-31.3l-92.3-76.3c-7.3-6.3-5.3-14.4-4.5-16.7s4.1-10 13.7-10.5   l119.7-8.6c11.8-0.9 22-8.2 26.5-19.2l45.7-111.1c3.7-9 12-9.7 14.4-9.7c2.4 0 10.7 0.7 14.4 9.7l45.7 111.1   c4.5 11 14.7 18.3 26.5 19.2l119.9 8.7c9.4 0.5 12.7 8.2 13.5 10.5C466.143 197.514 468.143 205.614 461.143 211.714z\"";
    //endregion

    static void init() {
        hotels = new ArrayList<>();
        restaurants = new ArrayList<>();
        entertainings = new ArrayList<>();
        thingsToDos = new ArrayList<>();
        clients = new ArrayList<>();
    }

    static Client searchClient(String username) {
        for (Client client : clients)
            if (client.getUsername().equals(username))
                return client;
        return null;
    }

    static Client logInClient(String username, String password) {
        for (Client client : clients)
            if (client.getUsername().equals(username) && client.getPassword().equals(password))
                return client;
        return null;
    }

    static boolean contains(String word, String part) {
        if (part.length() > word.length())
            return false;
        for (int i = 0; i < word.length(); i++) {
            if ((i + part.length() - 1) < word.length()) {
                boolean isPart = true;
                for (int j = 0; j < part.length(); j++)
                    if (word.charAt(i + j) != part.charAt(j))
                        isPart = false;
                if (isPart)
                    return true;
            }
        }
        return false;
    }

    static ArrayList<Hotel> findHotel(String location) {
        ArrayList<Hotel> returnHotels = new ArrayList<>();
        for (Hotel hotel : hotels)
            if (contains(hotel.getLocation().toLowerCase(), location.toLowerCase()))
                returnHotels.add(hotel);
        return returnHotels;
    }

    static ArrayList<Restaurant> findRestaurants(String location) {
        ArrayList<Restaurant> returnRestaurants = new ArrayList<>();
        for (Restaurant restaurant : restaurants)
            if (contains(restaurant.getLocation().toLowerCase(), location.toLowerCase()))
                returnRestaurants.add(restaurant);
        return returnRestaurants;
    }

    static ArrayList<ThingsToDo> findThingsToDo(String location, DatePicker startDate, DatePicker endDate) {
        ArrayList<ThingsToDo> returnThingsToDo = new ArrayList<>();
        for (ThingsToDo thingsToDo : thingsToDos) {
            DatePicker start = new DatePicker();
            start.setValue(LocalDate.of(thingsToDo.getStartDate().getYear(), thingsToDo.getStartDate().getMonth(), thingsToDo.getStartDate().getDay()));
            DatePicker end = new DatePicker();
            end.setValue(LocalDate.of(thingsToDo.getEndDate().getYear(), thingsToDo.getEndDate().getMonth(), thingsToDo.getEndDate().getDay()));

            if (contains(thingsToDo.getLocation().toLowerCase(), location.toLowerCase()) && start.getValue().isBefore(startDate.getValue()) || start.getValue().isEqual(startDate.getValue()) && (end.getValue().isAfter(endDate.getValue()) || end.getValue().isEqual(endDate.getValue())))
                returnThingsToDo.add(thingsToDo);
        }
        return returnThingsToDo;
    }

    static boolean checkDate(DatePicker startDate, DatePicker endDate, ThingsToDo thingsToDo) {
        DatePicker start = new DatePicker();
        start.setValue(LocalDate.of(thingsToDo.getStartDate().getYear(), thingsToDo.getStartDate().getMonth(), thingsToDo.getStartDate().getDay()));
        DatePicker end = new DatePicker();
        end.setValue(LocalDate.of(thingsToDo.getEndDate().getYear(), thingsToDo.getEndDate().getMonth(), thingsToDo.getEndDate().getDay()));

        return (start.getValue().isBefore(startDate.getValue()) || start.getValue().isEqual(startDate.getValue())) && (end.getValue().isAfter(endDate.getValue()) || end.getValue().isEqual(endDate.getValue()));
    }
}

class SQLDataBase {
    private static Connection connection = null;

    static void connect() {
        try {
            String path = "jdbc:sqlite:src//Databases//database.db";
            connection = DriverManager.getConnection(path);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void disconnect() {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String normalizeString(String string) {
        String returnString = "";

        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '\'')
                returnString += "\'" + string.charAt(i);
            else
                returnString += string.charAt(i);
        }

        return returnString;
    }


    //region Client
    static void addClient(Client client) {
        try {
            Statement statement = connection.createStatement();
            String query = String.format("INSERT INTO Client(Username,Password,Credits,ObjectsId,StartDate,EndDate) VALUES('%s','%s','%d','%s','%s','%s');", normalizeString(client.getUsername()), normalizeString(client.getPassword()), client.getNumberOfCredits(), normalizeString(client.getIds()), normalizeString(client.getStartDate().toString()), normalizeString(client.getEndDate().toString()));
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void editClient(Client client, String username) {
        try {
            Statement statement = connection.createStatement();
            String query = String.format("UPDATE Client SET Username='%s', Password='%s', Credits='%d', ObjectsId='%s', StartDate='%s', EndDate='%s' WHERE Username='%s';", normalizeString(client.getUsername()), normalizeString(client.getPassword()), client.getNumberOfCredits(), normalizeString(client.getIds()), normalizeString(client.getStartDate().toString()), normalizeString(client.getEndDate().toString()), normalizeString(username));
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void removeClient(String username) {
        try {
            Statement statement = connection.createStatement();
            String query = String.format("DELETE FROM Client WHERE Username='%s'", normalizeString(username));
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void loadClients() {
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
    //endregion


    //region Hotel
    static void loadHotels() {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM Hotel;";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                Hotel hotel = new Hotel();
                hotel.setId(result.getInt("Id"));
                hotel.setRatings(result.getString("Ratings"));
                hotel.setName(result.getString("Name"));
                hotel.setInfo(result.getString("Info"));
                hotel.setLocation(result.getString("Location"));
                hotel.setImages(result.getString("Images"));
                hotel.setContacts(result.getString("Contacts"));
                hotel.setComments(result.getString("Comments"));
                hotel.setAmenities(result.getString("Amenities"));
                hotel.setStartingPrice(result.getDouble("StartPrice"));
                hotel.setEndingPrice(result.getDouble("EndPrice"));
                hotel.setNumberOfRooms(result.getInt("Rooms"));

                Tools.hotels.add(hotel);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int lastHotelId() {
        int id = 0;
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT Id FROM Hotel ORDER BY Id DESC;";
            ResultSet result = statement.executeQuery(query);
            if (result.next())
                id = result.getInt("Id") + 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    static void addHotel(Hotel hotel, Client client) {
        int id = lastHotelId();

        if (!client.addObjectId("H" + id))
            return;

        String name = hotel.getName();
        String info = hotel.getInfo();
        String location = hotel.getLocation();
        String images = hotel.getImageLinks();
        String contacts = hotel.contactsToString();
        String comments = hotel.commentsToString();
        String amenitites = hotel.ammenityToString();
        String ratings = hotel.ratingsToString();
        double startPrice = hotel.getStartingPrice();
        double endPrice = hotel.getEndingPrice();
        int rooms = hotel.getNumberOfRooms();

        try {
            Statement statement = connection.createStatement();
            String query = String.format("INSERT INTO Hotel(Id,Ratings,Name,Info,Location,Images,Contacts,Comments,Amenities,StartPrice,EndPrice) VALUES('%d','%s','%s','%s','%s','%s','%s','%s','%s','%f','%f');", id, ratings, normalizeString(name), normalizeString(info), normalizeString(location), normalizeString(images), normalizeString(contacts), normalizeString(comments), normalizeString(amenitites), startPrice, endPrice);
            statement.executeUpdate(query);
            statement.close();
            editClient(client, client.getUsername());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void editHotel(Hotel hotel) {
        int id = hotel.getId();
        String ratings = hotel.ratingsToString();
        String name = hotel.getName();
        String info = hotel.getInfo();
        String location = hotel.getLocation();
        String images = hotel.getImageLinks();
        String contacts = hotel.contactsToString();
        String comments = hotel.commentsToString();
        String amenitites = hotel.ammenityToString();
        double startPrice = hotel.getStartingPrice();
        double endPrice = hotel.getEndingPrice();
        int rooms = hotel.getNumberOfRooms();

        try {
            Statement statement = connection.createStatement();
            String query = String.format("UPDATE Hotel SET Ratings='%s',Name='%s',Info='%s',Location='%s',Images='%s',Contacts='%s',Comments='%s',Amenities='%s',StartPrice='%f',EndPrice='%f' WHERE id='%d';", ratings, normalizeString(name), normalizeString(info), normalizeString(location), normalizeString(images), normalizeString(contacts), normalizeString(comments), normalizeString(amenitites), startPrice, endPrice, id);
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteHotel(String id) {
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM Hotel WHERE Id='" + id + "';";
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //endregion


    //region Restaurant
    static void loadRestaurant() {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM Restaurant;";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                Restaurant restaurant = new Restaurant();
                restaurant.setId(result.getInt("Id"));
                restaurant.setRatings(result.getString("Ratings"));
                restaurant.setName(result.getString("Name"));
                restaurant.setInfo(result.getString("Info"));
                restaurant.setLocation(result.getString("Location"));
                restaurant.setImages(result.getString("Images"));
                restaurant.setContacts(result.getString("Contacts"));
                restaurant.setComments(result.getString("Comments"));
                restaurant.setAmenities(result.getString("Amenities"));
                restaurant.setMenu(result.getString("Menu"));
                restaurant.setType(result.getString("Type"));
                restaurant.setNumberOfSeats(result.getInt("Seats"));

                Tools.restaurants.add(restaurant);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void addRestaurant(Restaurant restaurant, Client client) {
        int id = lastRestaurantId();

        if (!client.addObjectId("R" + id))
            return;

        String name = restaurant.getName();
        String info = restaurant.getInfo();
        String location = restaurant.getLocation();
        String images = restaurant.getImageLinks();
        String contacts = restaurant.contactsToString();
        String comments = restaurant.commentsToString();
        String amenitites = restaurant.ammenityToString();
        String ratings = restaurant.ratingsToString();
        String menu = restaurant.menuToString();
        String types = restaurant.typeToString();
        int seats = restaurant.getNumberOfSeats();

        try {
            Statement statement = connection.createStatement();
            String query = String.format("INSERT INTO Restaurant(Id, Ratings, Name, Info, Location, Images, Contacts, Comments, Amenities, Menu, Type, Seats) VALUES ('%d','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%d');", id, normalizeString(ratings), normalizeString(name), normalizeString(info), normalizeString(location), normalizeString(images), normalizeString(contacts), normalizeString(comments), normalizeString(amenitites), normalizeString(menu), normalizeString(types), seats);
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void editRestaurant(Restaurant restaurant) {
        int id = restaurant.getId();
        String name = restaurant.getName();
        String info = restaurant.getInfo();
        String location = restaurant.getLocation();
        String images = restaurant.getImageLinks();
        String contacts = restaurant.contactsToString();
        String comments = restaurant.commentsToString();
        String amenitites = restaurant.ammenityToString();
        String ratings = restaurant.ratingsToString();
        String menu = restaurant.menuToString();
        String types = restaurant.typeToString();
        int seats = restaurant.getNumberOfSeats();

        try {
            Statement statement = connection.createStatement();
            String query = String.format("UPDATE Restaurant SET Ratings='%s', Name='%s', Info='%s', Location='%s', Images='%s', Contacts='%s', Comments='%s', Amenities='%s', Menu='%s', Type='%s', Seats='%d' WHERE Id='%d';", normalizeString(ratings), normalizeString(name), normalizeString(info), normalizeString(location), normalizeString(images), normalizeString(contacts), normalizeString(comments), normalizeString(amenitites), normalizeString(menu), normalizeString(types), seats, id);
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void deleteRestaurant(String id) {
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM Restaurant WHERE Id='" + id + "';";
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int lastRestaurantId() {
        int id = 0;
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT Id FROM Restaurant ORDER BY Id DESC;";
            ResultSet result = statement.executeQuery(query);
            if (result.next())
                id = result.getInt("Id") + 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
    //endregion


    //region Entertaining
    static void loadEntertaining() {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM Entertaining;";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                Entertaining entertaining = new Entertaining();
                entertaining.setId(result.getInt("Id"));
                entertaining.setRatings(result.getString("Ratings"));
                entertaining.setName(result.getString("Name"));
                entertaining.setInfo(result.getString("Info"));
                entertaining.setLocation(result.getString("Location"));
                entertaining.setImages(result.getString("Images"));
                entertaining.setContacts(result.getString("Contacts"));
                entertaining.setComments(result.getString("Comments"));
                entertaining.setAmenities(result.getString("Amenities"));
                entertaining.setPrice(result.getDouble("Price"));
                entertaining.setRules(result.getString("Rules"));
                entertaining.setAgeLimit(result.getInt("AgeLimit"));

                Tools.entertainings.add(entertaining);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void addEntertaining(Entertaining entertaining, Client client) {
        int id = lastEntertainingId();

        if (!client.addObjectId("E" + id))
            return;

        String name = entertaining.getName();
        String info = entertaining.getInfo();
        String location = entertaining.getLocation();
        String images = entertaining.getImageLinks();
        String contacts = entertaining.contactsToString();
        String comments = entertaining.commentsToString();
        String amenitites = entertaining.ammenityToString();
        String ratings = entertaining.ratingsToString();
        double price = entertaining.getPrice();
        String rules = entertaining.getRules();
        int age = entertaining.getAgeLimit();

        try {
            Statement statement = connection.createStatement();
            String query = String.format("INSERT INTO Entertaining(Id, Ratings, Name, Info, Location, Images, Contacts, Comments, Amenities, Price, Rules, AgeLimit) VALUES ('%d','%s','%s','%s','%s','%s','%s','%s','%s','%f','%s','%d')", id, normalizeString(ratings), normalizeString(name), normalizeString(info), normalizeString(location), normalizeString(images), normalizeString(contacts), normalizeString(comments), normalizeString(amenitites), price, normalizeString(rules), age);
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void editEntertaining(Entertaining entertaining) {
        int id = entertaining.getId();
        String name = entertaining.getName();
        String info = entertaining.getInfo();
        String location = entertaining.getLocation();
        String images = entertaining.getImageLinks();
        String contacts = entertaining.contactsToString();
        String comments = entertaining.commentsToString();
        String amenitites = entertaining.ammenityToString();
        String ratings = entertaining.ratingsToString();
        double price = entertaining.getPrice();
        String rules = entertaining.getRules();
        int age = entertaining.getAgeLimit();

        try {
            Statement statement = connection.createStatement();
            String query = String.format("UPDATE Entertaining SET Ratings='%s', Name='%s', Info='%s', Location='%s', Images='%s', Contacts='%s', Comments='%s', Amenities='%s', Price='%f', Rules='%s', AgeLimit='%d' WHERE Id='%d';", normalizeString(ratings), normalizeString(name), normalizeString(info), normalizeString(location), normalizeString(images), normalizeString(contacts), normalizeString(comments), normalizeString(amenitites), price, normalizeString(rules), age, id);
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void deleteEntertaining(String id) {
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM Entertaining WHERE Id='" + id + "';";
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int lastEntertainingId() {
        int id = 0;
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT Id FROM Entertaining ORDER BY Id DESC;";
            ResultSet result = statement.executeQuery(query);
            if (result.next())
                id = result.getInt("Id") + 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
    //endregion


    //region Things To Do
    static void loadThingsToDo() {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM ThingsToDo;";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                ThingsToDo thingsToDo = new ThingsToDo();
                thingsToDo.setId(result.getInt("Id"));
                thingsToDo.setRatings(result.getString("Ratings"));
                thingsToDo.setName(result.getString("Name"));
                thingsToDo.setInfo(result.getString("Info"));
                thingsToDo.setLocation(result.getString("Location"));
                thingsToDo.setImages(result.getString("Images"));
                thingsToDo.setContacts(result.getString("Contacts"));
                thingsToDo.setComments(result.getString("Comments"));
                thingsToDo.setAmenities(result.getString("Amenities"));
                thingsToDo.setPrice(result.getDouble("Price"));
                thingsToDo.setRules(result.getString("Rules"));
                thingsToDo.setStartDate(new Date(result.getString("StartDate")));
                thingsToDo.setEndDate(new Date(result.getString("EndDate")));

                Tools.thingsToDos.add(thingsToDo);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void addThingsToDo(ThingsToDo thingsToDo, Client client) {
        int id = lastThingsToDoId();

        if (!client.addObjectId("E" + id))
            return;

        String name = thingsToDo.getName();
        String info = thingsToDo.getInfo();
        String location = thingsToDo.getLocation();
        String images = thingsToDo.getImageLinks();
        String contacts = thingsToDo.contactsToString();
        String comments = thingsToDo.commentsToString();
        String amenitites = thingsToDo.ammenityToString();
        String ratings = thingsToDo.ratingsToString();
        double price = thingsToDo.getPrice();
        String rules = thingsToDo.getRules();
        String startDate = thingsToDo.getStartDate().toString();
        String endDate = thingsToDo.getEndDate().toString();

        try {
            Statement statement = connection.createStatement();
            String query = String.format("INSERT INTO ThingsToDo(Id, Ratings, Name, Info, Location, Images, Contacts, Comments, Amenities, StartDate, EndDate, Price, Rules) VALUES ('%d','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%f','%s');", id, normalizeString(ratings), normalizeString(name), normalizeString(info), normalizeString(location), normalizeString(images), normalizeString(contacts), normalizeString(comments), normalizeString(amenitites), normalizeString(startDate), normalizeString(endDate), price, normalizeString(rules));
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void editThingsToDo(ThingsToDo thingsToDo) {
        int id = thingsToDo.getId();
        String name = thingsToDo.getName();
        String info = thingsToDo.getInfo();
        String location = thingsToDo.getLocation();
        String images = thingsToDo.getImageLinks();
        String contacts = thingsToDo.contactsToString();
        String comments = thingsToDo.commentsToString();
        String amenitites = thingsToDo.ammenityToString();
        String ratings = thingsToDo.ratingsToString();
        double price = thingsToDo.getPrice();
        String rules = thingsToDo.getRules();
        String startDate = thingsToDo.getStartDate().toString();
        String endDate = thingsToDo.getEndDate().toString();

        try {
            Statement statement = connection.createStatement();
            String query = String.format("UPDATE ThingsToDo SET Ratings='%s', Name='%s', Info='%s', Location='%s', Images='%s', Contacts='%s', Comments='%s', Amenities='%s', Price='%f', Rules='%s', StartDate='%s', EndDate='%s' WHERE Id='%d';", normalizeString(ratings), normalizeString(name), normalizeString(info), normalizeString(location), normalizeString(images), normalizeString(contacts), normalizeString(comments), normalizeString(amenitites), price, normalizeString(rules), startDate, endDate, id);
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void deleteThingsToDo(String id) {
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM ThingsToDo WHERE Id='" + id + "';";
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int lastThingsToDoId() {
        int id = 0;
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT Id FROM ThingsToDo ORDER BY Id DESC;";
            ResultSet result = statement.executeQuery(query);
            if (result.next())
                id = result.getInt("Id") + 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
    //endregion
}