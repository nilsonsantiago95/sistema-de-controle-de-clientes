package services;

import db.DB;
import model.entites.ClientDao;

public class FactoryDao {
	
	public static ClientDao createClientDao() {
		return new ClientDaoJdbc(DB.getConnection());
	}

}
