package kz.bootcamp.springboot.springBootcamp.beans;

import kz.bootcamp.springboot.springBootcamp.dto.Items;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MySqlKirillItemManager implements ItemManager {

    private Connection connection;

    public MySqlKirillItemManager(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bootcamp_db?serverTimezone=UTC", "root", "");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void addItem(Items item) {

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO items(name, description, price, amount) " +
                    "VALUES (?, ?, ?, ?)");

            statement.setString(1, item.getName());
            statement.setString(2, item.getDescription());
            statement.setDouble(3, item.getPrice());
            statement.setInt(4, item.getAmount());

            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public Items getItem(Long id) {
        Items item = null;

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM items WHERE id = ?");

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){

                item = new Items(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getInt("amount"),
                        resultSet.getDouble("price")
                );

            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return item;

    }

    @Override
    public ArrayList<Items> getItems() {

        ArrayList<Items> items = new ArrayList<>();

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM items");

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){

                items.add(new Items(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getInt("amount"),
                        resultSet.getDouble("price")
                ));

            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return items;

    }
}
