import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Scanner;

import org.junit.Ignore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import db.DB;
import model.entites.Client;
import model.entites.ClientDao;
import services.ClientDaoJdbc;

class ClientDaoJdbcTest {
	
	private ClientDao clientDao = new ClientDaoJdbc(DB.getConnection());
	private Client clientExpected = new Client(null, "nilson", "151.767.967-24", "21 91234-5678", "nilson@gmail.com");

	@DisplayName("Testa se o método retorna um objeto do tipo Client")
	@Test
	void testCheckClientExists() {
		
		Client client = ClientDaoJdbc.checkClientExists(new Scanner(System.in), clientDao.findAll());
		clientExpected.setId(client.getId());
		assertEquals(client, clientExpected);
	}

	@Ignore
	void testInsertClient() {
		fail("Not yet implemented");
	}

	@DisplayName("Testa se o método retorna um objeto do tipo Client")
	@Test
	void testFindById() {
		
		Client client = clientDao.findById(2);
		clientExpected.setId(2);
		assertEquals(client, clientExpected);
	}

	@DisplayName("Testa se o método retorna uma lista do tipo Client")
	@Test
	void testFindAll() {
		
		List<Client> clientsExpected = clientDao.findAll();
		
		assertEquals(clientDao.findAll(), clientsExpected);
		
	}

	@Ignore
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Ignore
	void testDelete() {
		fail("Not yet implemented");
	}

}
