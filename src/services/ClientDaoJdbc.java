package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import db.DB;
import exceptions.DbException;
import model.entites.Client;
import model.entites.ClientDao;
import util.EnumsRegex;
import util.ValidateRegex;

public class ClientDaoJdbc implements ClientDao {

	private Connection conn;

	public ClientDaoJdbc(Connection conn) {
		this.conn = conn;
	}
	
	public static Client checkClientExists(Scanner sc, List<Client> clients) {
		System.out.print("Insira o CPF do cliente (xxx.xxx.xxx-xx): ");
		String cpf = sc.nextLine();
		ValidateRegex.validate(cpf, EnumsRegex.CPF.getRegex());
		
		Client client = clients.stream()
				.filter(c -> c.getCpf().equals(cpf))
				.findAny().get();
				
		return client;
	}

	private static Client instantiateClient(ResultSet rs) throws SQLException {

		Client client = new Client();

		client.setId(rs.getInt("id"));
		client.setName(rs.getString("full_name"));
		client.setEmail(rs.getString("email"));
		client.setCpf(rs.getString("cpf"));
		client.setPhoneNumber(rs.getString("phone_number"));

		return client;

	}

	@Override
	public void insertClient(Client client) {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			preparedStatement = conn.prepareStatement(
					"INSERT INTO clients (full_name, email, cpf, phone_number)" + " VALUES (?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setString(1, client.getName());
			preparedStatement.setString(2, client.getEmail());
			preparedStatement.setString(3, client.getCpf());
			preparedStatement.setString(4, client.getPhoneNumber());
			
			int rowsAffected = preparedStatement.executeUpdate();
			
			if(rowsAffected <= 0) {
				throw new DbException("Unexpected error: insert failed");
			}
			
			resultSet = preparedStatement.getGeneratedKeys();
			
			if(resultSet.next()) {
				client.setId(resultSet.getInt(1));
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(preparedStatement);
			DB.closeResultSet(resultSet);
		}

	}

	@Override
	public Client findById(Integer id) {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			preparedStatement = conn.prepareStatement("SELECT * FROM clients WHERE id = ?");
			preparedStatement.setInt(1, id);
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				return instantiateClient(resultSet);
			}
			
			throw new DbException("Unexpected error: findById failed");
			
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(preparedStatement);
			DB.closeResultSet(resultSet);
		}
		
	}

	@Override
	public List<Client> findAll() {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			preparedStatement = conn.prepareStatement("SELECT * FROM clients");
			resultSet = preparedStatement.executeQuery();

			List<Client> listClients = new ArrayList<>();
			Map<Integer, Client> mapClients = new HashMap<>();

			while (resultSet.next()) {
				Client client = mapClients.getOrDefault(resultSet.getInt("id"), instantiateClient(resultSet));
				listClients.add(client);
			}

			return listClients;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(preparedStatement);
			DB.closeResultSet(resultSet);
		}

	}

	@Override
	public void update(Client client) {
		
		PreparedStatement preparedStatement = null;
		
		try {
			
			preparedStatement = conn.prepareStatement("UPDATE clients SET full_name = ?, email= ?, cpf = ?, phone_number = ? WHERE id = ?");
			preparedStatement.setString(1, client.getName());
			preparedStatement.setString(2, client.getEmail());
			preparedStatement.setString(3, client.getCpf());
			preparedStatement.setString(4, client.getPhoneNumber());
			preparedStatement.setInt(5, client.getId());
			
			int rowsAffected = preparedStatement.executeUpdate();
			
			if(rowsAffected <= 0) {
				throw new DbException("Unexpected error: update failed");
			}
			
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(preparedStatement);
		}

	}

	@Override
	public void delete(Client client) {
		
		PreparedStatement preparedStatement = null;
		
		try {
			
			preparedStatement = conn.prepareStatement("Delete FROM clients WHERE id = ?");
			preparedStatement.setInt(1, client.getId());
			
			int rowsAffected = preparedStatement.executeUpdate();
			
			if(rowsAffected <= 0) {
				throw new DbException("Unexpected error: delete failed");
			}
			
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(preparedStatement);
		}

	}

}
