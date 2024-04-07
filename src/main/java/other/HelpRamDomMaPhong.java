package other;

import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.Phong;
import jakarta.persistence.EntityManager;




public class HelpRamDomMaPhong {

	public static String taoMa(String ma) {
		
		String maP = "P";
		int flag;
		int count = 0;
		
		ArrayList<String> column = maToDaTaBase();
		flag = Integer.parseInt(column.get(0).trim().substring(2));
		
		for(String value : column) {
			if(Character.toString(value.trim().charAt(1)).equals(ma)) {
				count++;
			}
		}
		
		if(count == 0) {
			return maP = maP + ma + String.format("%02d", 01);
		}
		
		
		for(String value : column) {
			String secondCharAsString = Character.toString(value.trim().charAt(1));
			if(secondCharAsString.equals(ma)) {
				String result = value.trim().substring(2);
				int number = Integer.parseInt(result);
				if(number > flag) {
					flag = number;
				}
			}
		}
		
		
		maP = maP + ma + String.format("%02d", flag+1);
		return maP;
	}

	public static ArrayList<String> maToDaTaBase() {
//		String jdbcUrl = "jdbc:sqlserver://localhost:1433;databasename=SingUrSong_vnew";
//		String user = "sa";
//		String password = "sapassword";
//		String maPhong = "";
//		
//		 ArrayList<String> maCot = new ArrayList<>();
//        
//        try (Connection con = DriverManager.getConnection(jdbcUrl, user, password)) {
//        	String sql = "SELECT * FROM Phong";
//            PreparedStatement preparedStatement = con.prepareStatement(sql);
//            ResultSet rs = preparedStatement.executeQuery();
//
//            while (rs.next()) {
//            	maPhong = rs.getString("maPhong");           	
//            	maCot.add(maPhong);		
//            }
//               
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//		return maCot;
		
		EntityManager em = ConnectDB.connect();
		ArrayList<String> maCot = new ArrayList<>();
		String sql = "SELECT maPhong FROM Phong";
		try {
			List<Object> listObj = em.createNativeQuery(sql, Phong.class).getResultList();
			
			for (Object object : listObj) {
				maCot.add(object.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return maCot;
	}
	
	public static void main(String[] args) {

		String ma = taoMa("6");
		System.out.println(ma);
	}

}