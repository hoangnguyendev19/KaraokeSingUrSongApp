package dao;

import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.Phong;
import jakarta.persistence.EntityManager;
import other.ConvertObjToEntity;

public class ChiTietHoaDon_DAO {

	private EntityManager em;

	public ChiTietHoaDon_DAO() {
		em = ConnectDB.connect();
	}

	public ArrayList<ChiTietHoaDon> layTatCaChiTietHoaDon() {
//		ArrayList<ChiTietHoaDon> danhSachHoaDon = new ArrayList<ChiTietHoaDon>();
//		try {
//			ConnectDB.getInstance();
//			Connection con = ConnectDB.getConnection();
//
//			String sql = "SELECT * FROM ChiTietHoaDon";
//
//			Statement statement = con.createStatement();
//			ResultSet rs = statement.executeQuery(sql);
//			while (rs.next()) {
//				HoaDon hoaDon = new HoaDon(rs.getString("maHoaDon"));
//				Phong phong = new Phong(rs.getString("maPhong"));
//				ChiTietHoaDon ctHoaDon = new ChiTietHoaDon(hoaDon, phong);
//				danhSachHoaDon.add(ctHoaDon);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return danhSachHoaDon;

		try {
			String sql = "SELECT * FROM ChiTietHoaDon";
			List<Object> listObj = em.createNativeQuery(sql, ChiTietHoaDon.class).getResultList();

			ArrayList<ChiTietHoaDon> list = ConvertObjToEntity.convertToChiTietHoaDonList(listObj);
			
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public ChiTietHoaDon timCTHoaDon_TheoMaHoaDon_MaPhong(String maHD, String maPh) {
//		ChiTietHoaDon ctHoaDon = null;
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		try {
//			String sql = "SELECT * FROM ChiTietHoaDon WHERE maHoaDon = ? AND maPhong = ?";
//			statement = con.prepareStatement(sql);
//			statement.setString(1, maHD);
//			statement.setString(2, maPh);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				HoaDon hoaDon = new HoaDon(rs.getString("maHoaDon"));
//				Phong_DAO DAO_P = new Phong_DAO();
//
//				Phong phong = DAO_P.timPhong_TheoMaPhong(rs.getString("maPhong"));
//				ctHoaDon = new ChiTietHoaDon(hoaDon, phong);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//
//		} finally {
//			try {
//				statement.close();
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//		return ctHoaDon;

		try {
			String sql = "SELECT * FROM ChiTietHoaDon WHERE maHoaDon = ? AND maPhong = ?";
			Object obj = em.createNativeQuery(sql, ChiTietHoaDon.class).setParameter(1, maHD)
					.setParameter(2, maPh).getResultList().stream().findFirst().orElse(null);

			ChiTietHoaDon cthd = (ChiTietHoaDon) obj;
			
			return cthd;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<ChiTietHoaDon> timCTHoaDon_TheoMaHoaDon(String maHD) {
//		ArrayList<ChiTietHoaDon> dsCTHD = new ArrayList<ChiTietHoaDon>();
//		Phong_DAO p_DAO = new Phong_DAO();
//		ChiTietHoaDon ctHoaDon = null;
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		try {
//			String sql = "SELECT * FROM ChiTietHoaDon WHERE maHoaDon = ?";
//			statement = con.prepareStatement(sql);
//			statement.setString(1, maHD);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				HoaDon hoaDon = new HoaDon(rs.getString("maHoaDon"));
//				Phong phong = p_DAO.timPhong_TheoMaPhong(rs.getString("maPhong"));
//				ctHoaDon = new ChiTietHoaDon(hoaDon, phong);
//				dsCTHD.add(ctHoaDon);
//
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//
//		} finally {
//			try {
//				statement.close();
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//		return dsCTHD;

		try {
			String sql = "SELECT * FROM ChiTietHoaDon WHERE maHoaDon = ?";
			List<Object> listObj = em.createNativeQuery(sql, ChiTietHoaDon.class).setParameter(1, maHD).getResultList();

			ArrayList<ChiTietHoaDon> list = ConvertObjToEntity.convertToChiTietHoaDonList(listObj);
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ChiTietHoaDon timCTHoaDon_TheoMaPhong(String maPhong) {
//
//		ChiTietHoaDon ctHoaDon = null;
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		Phong_DAO DAO_P = new Phong_DAO();
//		HoaDon_DAO DAO_HD = new HoaDon_DAO();
//		try {
//			String sql = "SELECT * FROM ChiTietHoaDon WHERE maPhong = ?";
//			statement = con.prepareStatement(sql);
//			statement.setString(1, maPhong);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				HoaDon hoaDon = DAO_HD.layHoaDon_TheoMaHoaDon(rs.getString("maHoaDon"));
//				Phong phong = DAO_P.timPhong_TheoMaPhong(rs.getString("maPhong"));
//				ctHoaDon = new ChiTietHoaDon(hoaDon, phong);
//
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//
//		} finally {
//			try {
//
//				statement.close();
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//
//		return ctHoaDon;

		try {
			String sql = "SELECT * FROM ChiTietHoaDon WHERE maPhong = ?";
			Object obj = em.createNativeQuery(sql, ChiTietHoaDon.class).setParameter(1, maPhong)
					.getResultList().stream().findFirst().orElse(null);
			
			ChiTietHoaDon cthd = (ChiTietHoaDon) obj;
			return cthd;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public boolean taoCTHoaDon(ChiTietHoaDon ctHoaDon) {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		int n = 0;
//		try {
//			statement = con.prepareStatement("INSERT INTO ChiTietHoaDon values(?,?)");
//			statement.setString(1, ctHoaDon.getHoaDon().getMaHoaDon());
//			statement.setString(2, ctHoaDon.getPhong().getMaPhong());
//
//			n = statement.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				statement.close();
//			} catch (Exception e2) {
//				// TODO: handle exception
//				e2.printStackTrace();
//			}
//		}
//		return n > 0;

		try {
			String sql = "INSERT INTO ChiTietHoaDon values(?,?)";
			em.getTransaction().begin();
			int result = em.createNativeQuery(sql).setParameter(1, ctHoaDon.getHoaDon().getMaHoaDon())
					.setParameter(2, ctHoaDon.getPhong().getMaPhong()).executeUpdate();

			if (result == 0) {
				return false;
			}

			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			return false;
		} finally {
			em.close();
		}
	}

	public boolean capNhatCTHoaDon_TheoMaHoaDon_MaPhong(ChiTietHoaDon ctHoaDon, String maPhongCu) {
//
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		int n = 0;
//		try {
//			statement = con
//					.prepareStatement("UPDATE ChiTietHoaDon set maPhong = ? " + " WHERE maHoaDon = ? and maPhong = ?");
//			statement.setString(1, ctHoaDon.getPhong().getMaPhong());
//			statement.setString(2, ctHoaDon.getHoaDon().getMaHoaDon());
//			statement.setString(3, maPhongCu);
//			n = statement.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				statement.close();
//			} catch (Exception e2) {
//				// TODO: handle exception
//				e2.printStackTrace();
//			}
//		}
//		return n > 0;

		try {
			String sql = "UPDATE ChiTietHoaDon set maPhong = ? WHERE maHoaDon = ? and maPhong = ?";
			em.getTransaction().begin();
			int result = em.createNativeQuery(sql).setParameter(1, ctHoaDon.getPhong().getMaPhong())
					.setParameter(2, ctHoaDon.getHoaDon().getMaHoaDon()).setParameter(3, maPhongCu).executeUpdate();

			if (result == 0) {
				return false;
			}

			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			return false;
		} finally {
			em.close();
		}
	}

	public boolean xoaCTHoaDon(ChiTietHoaDon ctHoaDon) {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		int n = 0;
//		try {
//			statement = con.prepareStatement("DELETE FROM ChiTietHoaDon" + " WHERE maHoaDon = ? AND maPhong = ?");
//			statement.setString(1, ctHoaDon.getHoaDon().getMaHoaDon());
//			statement.setString(2, ctHoaDon.getPhong().getMaPhong());
//			n = statement.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				statement.close();
//			} catch (Exception e2) {
//				// TODO: handle exception
//				e2.printStackTrace();
//			}
//		}
//		return n > 0;

		try {
			String sql = "DELETE FROM ChiTietHoaDon WHERE maHoaDon = ? AND maPhong = ?";
			em.getTransaction().begin();
			int result = em.createNativeQuery(sql).setParameter(1, ctHoaDon.getHoaDon().getMaHoaDon())
					.setParameter(2, ctHoaDon.getPhong().getMaPhong()).executeUpdate();

			if (result == 0) {
				return false;
			}

			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			return false;
		} finally {
			em.close();
		}
	}
}
