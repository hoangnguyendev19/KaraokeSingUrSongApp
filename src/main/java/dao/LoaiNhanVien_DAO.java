package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.LoaiNhanVien;
import jakarta.persistence.EntityManager;

/**
 * LoaiNhanVien_DAO
 * 
 * @author THANH CUONG
 *
 */
public class LoaiNhanVien_DAO {
	private EntityManager em;

	public LoaiNhanVien_DAO() {
		em = new ConnectDB().getEntityManager();
	}

	/**
	 * @return danhSachLoaiNhanVien
	 */
	public ArrayList<LoaiNhanVien> layTatCaLoaiNhanVien() {
//		ArrayList<LoaiNhanVien> danhSachLoaiNhanVien = new ArrayList<LoaiNhanVien>();
//		try {
//			ConnectDB.getInstance();
//			Connection con = ConnectDB.getConnection();
//			String sql = "SELECT * FROM LoaiNhanVien";
//			Statement statement = con.createStatement();
//			ResultSet rs = statement.executeQuery(sql);
//			
//			while (rs.next()) {
//				
//				String maLoaiNhanVien = rs.getString("maLoaiNhanVien");
//				String tenLoaiNhanVien = rs.getString("tenLoaiNhanVien");
//				LoaiNhanVien loaiNhanVien = new LoaiNhanVien(maLoaiNhanVien, tenLoaiNhanVien);
//				danhSachLoaiNhanVien.add(loaiNhanVien);
//				
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return danhSachLoaiNhanVien;

		try {
			String sql = "SELECT * FROM LoaiNhanVien";
			em.getTransaction().begin();
			ArrayList<LoaiNhanVien> list = (ArrayList<LoaiNhanVien>) em.createNativeQuery(sql, LoaiNhanVien.class)
					.getResultList();
			em.close();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			em.close();
			return null;

		}
	}

	/**
	 * @param maLoaiNV
	 * @return loaiNhanVien
	 */
	public LoaiNhanVien layLoaiNhanVien_TheoMaLoaiNhanVien(String maLoaiNV) {
//		LoaiNhanVien loaiNhanVien = null;
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		try {
//			String sql = "SELECT * FROM LoaiNhanVien WHERE maLoaiNhanVien = ?";
//			statement = con.prepareStatement(sql);
//			statement.setString(1, maLoaiNV);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				String maLoaiNhanVien = rs.getString("maLoaiNhanVien");
//				String tenLoaiNhanVien = rs.getString("tenLoaiNhanVien");
//				loaiNhanVien = new LoaiNhanVien(maLoaiNhanVien, tenLoaiNhanVien);
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
//		return loaiNhanVien;

		try {
			String sql = "SELECT * FROM LoaiNhanVien WHERE maLoaiNhanVien = ?";
			em.getTransaction().begin();
			LoaiNhanVien loaiNhanVien = (LoaiNhanVien) em.createNativeQuery(sql, LoaiNhanVien.class)
					.setParameter(1, maLoaiNV).getSingleResult();
			em.close();
			return loaiNhanVien;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			em.close();
			return null;
		}
	}

	/**
	 * @param xTenLoaiNV
	 * @return loaiNhanVien
	 */
	public LoaiNhanVien layLoaiNhanVien_TheoTenLoaiNhanVien(String xTenLoaiNV) {
//		LoaiNhanVien loaiNhanVien = null;
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		try {
//			String sql = "SELECT * FROM LoaiNhanVien WHERE tenLoaiNhanVien = ?";
//			statement = con.prepareStatement(sql);
//			statement.setString(1, xTenLoaiNV);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				String maLoaiNhanVien = rs.getString("maLoaiNhanVien");
//				String tenLoaiNhanVien = rs.getString("tenLoaiNhanVien");
//				loaiNhanVien = new LoaiNhanVien(maLoaiNhanVien, tenLoaiNhanVien);
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
//		return loaiNhanVien;

		try {
			String sql = "SELECT * FROM LoaiNhanVien WHERE tenLoaiNhanVien = ?";
			em.getTransaction().begin();
			LoaiNhanVien loaiNhanVien = (LoaiNhanVien) em.createNativeQuery(sql, LoaiNhanVien.class)
					.setParameter(1, xTenLoaiNV).getSingleResult();
			em.close();
			return loaiNhanVien;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			em.close();
			return null;
		}
	}

	/**
	 * Thêm loại nhân viên
	 * @param loaiNhanVien
	 * @return true / false
	 */
	public boolean taoLoaiNhanVien(LoaiNhanVien loaiNhanVien) {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		int n = 0;
//		try {
//			statement = con.prepareStatement("INSERT INTO LoaiNhanVien values(?,?)");
//			statement.setString(1, loaiNhanVien.getMaLoaiNhanVien());
//			statement.setString(2, loaiNhanVien.getTenLoaiNhanVien());
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
			String sql = "INSERT INTO LoaiNhanVien values(?,?)";
			em.getTransaction().begin();
			int result = em.createNativeQuery(sql).setParameter(1, loaiNhanVien.getMaLoaiNhanVien())
					.setParameter(2, loaiNhanVien.getTenLoaiNhanVien()).executeUpdate();
			if(result == 0) {
				em.getTransaction().rollback();
				em.close();
				return false;
			}
			
			em.getTransaction().commit();
			em.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			em.close();		
			return false;
		}
	}

	/**
	 * Cập nhật loại nhân viên
	 * @param loaiNhanVien
	 * @return true / false
	 */
	public boolean capNhatLoaiNhanVien(LoaiNhanVien loaiNhanVien) {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		int n = 0;
//		try {
//			statement = con.prepareStatement(
//					"UPDATE LoaiNhanVien SET tenLoaiNhanVien = ?"
//							+ " WHERE maLoaiNhanVien = ?");		
//			statement.setString(1, loaiNhanVien.getTenLoaiNhanVien());
//			statement.setString(2, loaiNhanVien.getMaLoaiNhanVien());
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
			String sql = "UPDATE LoaiNhanVien SET tenLoaiNhanVien = ? WHERE maLoaiNhanVien = ?";
			em.getTransaction().begin();
			int result = em.createNativeQuery(sql).setParameter(1, loaiNhanVien.getTenLoaiNhanVien())
					.setParameter(2, loaiNhanVien.getMaLoaiNhanVien()).executeUpdate();
            if(result == 0) {
				em.getTransaction().rollback();
				em.close();
				return false;
            }
			em.getTransaction().commit();
			em.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			em.close();
			return false;
		}
	}

	/**
	 * Xóa loại nhân viên
	 * @param loaiNhanVien
	 * @return
	 */
	public boolean xoaLoaiNhanVien(LoaiNhanVien loaiNhanVien) {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		int n = 0;
//		try {
//			statement = con.prepareStatement("DELETE FROM LoaiNhanVien" + " WHERE maLoaiNhanVien = ?");
//			statement.setString(1, loaiNhanVien.getMaLoaiNhanVien());
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
			String sql = "DELETE FROM LoaiNhanVien WHERE maLoaiNhanVien = ?";
			em.getTransaction().begin();
			int result = em.createNativeQuery(sql).setParameter(1, loaiNhanVien.getMaLoaiNhanVien()).executeUpdate();
			if (result == 0) {
				em.getTransaction().rollback();
				em.close();
				return false;
			}
			
			em.getTransaction().commit();
			em.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			em.getTransaction().rollback();
			em.close();
			return false;
		}
		
	}
}
