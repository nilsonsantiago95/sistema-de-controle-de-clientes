package application;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import db.DB;
import exceptions.DomainException;
import model.entites.Client;
import model.entites.ClientDao;
import services.ClientDaoJdbc;
import services.FactoryDao;
import util.MenuClient;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		boolean programIsCompleted = false;
		ClientDao clientDaoJdbc = FactoryDao.createClientDao();

		do {

			try {
				System.out.println("==========================================");
				System.out.println("Digite o número da opção desejada:\n" + "[ 1 ] Cadastrar cliente\n"
						+ "[ 2 ] Listar todos os clientes\n" + "[ 3 ] Buscar um cliente\n" + "[ 4 ] Editar cliente\n"
						+ "[ 5 ] Excluir cliente\n" + "[ 6 ] Sair");

				String option = sc.nextLine().trim();
				System.out.println("==========================================");
				switch (option) {

				case "1":
					Client newClient = MenuClient.menuRegisterClient(sc);
					clientDaoJdbc.insertClient(newClient);
					System.out.println("Cliente cadastrado com sucesso! ");
					break;

				case "2":
					clientDaoJdbc.findAll().forEach(System.out::println);
					break;

				case "3":
					List<Client> clients = clientDaoJdbc.findAll();
					Client client = ClientDaoJdbc.checkClientExists(sc, clients);
					System.out.println(clientDaoJdbc.findById(client.getId()));

					break;

				case "4":
					Client clientToUpdate = ClientDaoJdbc.checkClientExists(sc, clientDaoJdbc.findAll());
					MenuClient.menuUpdateClient(sc, clientToUpdate);
					clientDaoJdbc.update(clientToUpdate);
					System.out.println("Alteração concluída");

					break;

				case "5":
					Client clientToDelete = ClientDaoJdbc.checkClientExists(sc, clientDaoJdbc.findAll());
					clientDaoJdbc.delete(clientToDelete);
					System.out.println("Cliente removido");
					break;

				case "6":
					System.out.println("Fim do programa");
					DB.closeConnection();
					programIsCompleted = true;

					break;

				default:
					throw new InputMismatchException("Opção inválida, tente novamente");

				}

			} catch (DomainException e) {
				System.out.println("ERRO: " + e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println("ERRO: " + e.getMessage());
			}

		} while (!programIsCompleted);

		sc.close();

	}

}
