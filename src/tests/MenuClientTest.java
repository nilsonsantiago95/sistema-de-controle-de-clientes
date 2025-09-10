package tests;

import static org.junit.Assert.assertEquals;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

import model.entites.Client;
import util.MenuClient;

class MenuClientTest {

	@Test
	public void menuRegisterClientTest() {
		
		Client result = MenuClient.menuRegisterClient(new Scanner(System.in));
		
		assertEquals(result, new Client(null, "nilson", "151.767.967-24", "21 91234-5678", "nilson@gmail.com"));
		
	}

}
