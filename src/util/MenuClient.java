package util;

import java.util.InputMismatchException;
import java.util.Scanner;

import exceptions.DomainException;
import model.entites.Client;

public class MenuClient {
	
	public static Client menuRegisterClient(Scanner sc) {

		System.out.print("Insira o nome: ");
		String name = sc.nextLine().trim();

		System.out.print("Insira o email (exemplo@gmail.com): ");
		String email = sc.nextLine().trim();
		ValidateRegex.validate(email, EnumsRegex.EMAIL.getRegex());

		System.out.print("Insira o CPF (xxx.xxx.xxx-xx): ");
		String cpf = sc.nextLine().trim();
		ValidateRegex.validate(cpf, EnumsRegex.CPF.getRegex());

		System.out.print("Insira o número de celular ((xx) xxxxx-xxxx): ");
		String phoneNumber = sc.nextLine().trim();
		ValidateRegex.validate(phoneNumber, EnumsRegex.PHONENUMBER.getRegex());

		return new Client(null, name, cpf, phoneNumber, email);

	}
	
	public static void menuUpdateClient(Scanner sc, Client client) {
		
		boolean isContinue = false;

		do {

			try {
				System.out.println("==========================================");
				System.out.println("Selecione a informação que deseja alterar:\n"
						+ "[ 1 ] Nome\n"
						+ "[ 2 ] Email\n"
						+ "[ 3 ] CPF\n"
						+ "[ 4 ] Número de celular\n"
						+ "[ 5 ] Sair");

				String option = sc.nextLine().trim();
				System.out.println("==========================================");
				switch (option) {

				case "1":
					System.out.print("Informe o nome: ");
					String name = sc.nextLine().trim();
					client.setName(name);
					break;

				case "2":
					System.out.print("Informe o email: ");
					String email = sc.nextLine().trim();
					ValidateRegex.validate(email, EnumsRegex.EMAIL.getRegex());
					client.setEmail(email);
					break;

				case "3":
					System.out.print("Informe o CPF: ");
					String cpf = sc.nextLine().trim();
					ValidateRegex.validate(cpf, EnumsRegex.CPF.getRegex());
					client.setCpf(cpf);
					break;

				case "4":
					System.out.print("Informe o número de celular: ");
					String phoneNumber = sc.nextLine().trim();
					ValidateRegex.validate(phoneNumber, EnumsRegex.PHONENUMBER.getRegex());
					client.setPhoneNumber(phoneNumber);
					break;

				case "5":
					System.out.println("Fim do programa");
					isContinue = true;
					break;

				default:
					throw new InputMismatchException("Opção inválida, tente novamente");

				}

			} catch (DomainException e) {
				System.out.println("ERRO: " + e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println("ERRO: " + e.getMessage());
			}

		} while (!isContinue);
		
	}

}
