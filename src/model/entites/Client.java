package model.entites;

import java.io.Serializable;
import java.util.Objects;

public class Client implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String cpf;
	private String phoneNumber;
	private String email;

	public Client() {
		
	}

	public Client(Integer id, String name, String cpf, String phoneNumber, String email) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("ID: " + id + "\n");
		sb.append("Nome: " + name + "\n");
		sb.append("CPF: " + cpf + "\n");
		sb.append("Celular: " + phoneNumber + "\n");
		sb.append("Email: " + email + "\n");
		
		return sb.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(cpf, other.cpf);
	}

}
