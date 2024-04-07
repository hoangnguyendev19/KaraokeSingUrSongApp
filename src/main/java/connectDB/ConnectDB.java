package connectDB;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class ConnectDB {
	public static EntityManager connect() {
		return Persistence.createEntityManagerFactory("SingUrSong_vnew").createEntityManager();
	}
	
}
