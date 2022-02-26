package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Food;

public class FoodDao {
	
	private Connection connection;
	private final String GET_FOODS_QUERY = "SELECT * FROM foods";
	private final String GET_FOOD_BY_ID_QUERY = "SELECT * FROM foods WHERE id = ?";
	private final String CREATE_FOOD_QUERY = "INSERT INTO foods(name, description) VALUES(?,?)";
	private final String DELETE_FOOD_BY_ID_QUERY = "DELETE FROM foods WHERE id = ?";
	private final String EDIT_FOOD_DESCRIPTION_BY_ID_QUERY = "UPDATE foods SET description = ? WHERE id = ?";
	
	public FoodDao() {
		connection = DBConnection.getConnection();
		
	}
	
	public List<Food> getFoods() throws SQLException{
		ResultSet rs = connection.prepareStatement(GET_FOODS_QUERY).executeQuery();
		List<Food> foods = new ArrayList<Food>();
		
		while (rs.next()) {
			foods.add(describeFood(rs.getInt(1), rs.getString(2), rs.getString(3)));
		}
		return foods;
	}
	
	public Food getFoodById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_FOOD_BY_ID_QUERY);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next()	;
		return describeFood(rs.getInt(1), rs.getString(2), rs.getString(3));
	}
		
	public void createNewFood(String name, String description) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_FOOD_QUERY);
		ps.setString(1, name);
		ps.setString(2, description);
		ps.executeUpdate();
		
	}
	
	public void deleteFoodById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_FOOD_BY_ID_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
		
	}
	
	public void editFoodById(int id, String description) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(EDIT_FOOD_DESCRIPTION_BY_ID_QUERY);
		ps.setInt(2, id);
		ps.setString(1, description);
		ps.executeUpdate();
	}
	
	private Food describeFood(int id, String name, String description) {
		return new Food(id, name, description);
	}
}
