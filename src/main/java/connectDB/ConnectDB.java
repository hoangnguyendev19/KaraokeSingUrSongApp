package connectDB;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class ConnectDB {
	private EntityManager em;
	
	public ConnectDB() {
		this.em = Persistence.createEntityManagerFactory("KaraokeSingUrSongApp").createEntityManager();
	}
	
	public EntityManager getEntityManager() {
		return this.em;
	}
	
}
