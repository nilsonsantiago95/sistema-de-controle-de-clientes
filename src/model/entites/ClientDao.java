package model.entites;

import java.util.List;

public interface ClientDao {
	
	void insertClient(Client client);
	Client findById(Integer id);
	List<Client> findAll();
	void update(Client client);
	void delete(Client client);

}
