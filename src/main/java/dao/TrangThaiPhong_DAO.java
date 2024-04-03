package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhuyenMai;
import entity.TrangThaiPhong;
import jakarta.persistence.EntityManager;

public class TrangThaiPhong_DAO {

	private EntityManager em;
	public TrangThaiPhong_DAO() {
		em = new ConnectDB().getEntityManager();
	}

	public ArrayList<TrangThaiPhong> layTatCaTrangThaiPhong() {
//		ArrayList<TrangThaiPhong> danhSachTrangThaiPhong = new ArrayList<TrangThaiPhong>();
//		try {
//			ConnectDB.getInstance();
//			Connection con = ConnectDB.getConnection();
//			String sql = "SELECT * FROM TrangThaiPhong";
//			Statement statement = con.createStatement();
//			ResultSet rs = statement.executeQuery(sql);
//			while (rs.next()) {
//				String maTrangThai = rs.getString("maTrangThai");
//				String tenTrangThai = rs.getString("tenTrangThai");
//				TrangThaiPhong trangThaiPhong = new TrangThaiPhong(maTrangThai, tenTrangThai);
//				danhSachTrangThaiPhong.add(trangThaiPhong);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return danhSachTrangThaiPhong;
		try {
			String sql = "SELECT * FROM TrangThaiPhong";
			em.getTransaction().begin();
			ArrayList<TrangThaiPhong> list = (ArrayList<TrangThaiPhong>) em.createQuery(sql).getResultList();
			em.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public TrangThaiPhong timTrangThaiPhong_TheoMaTrangThai(String maTrThai) {
//		TrangThaiPhong trangThaiPhong = null;
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		try {
//			String sql = "SELECT * FROM TrangThaiPhong WHERE maTrangThai = ?";
//			statement = con.prepareStatement(sql);
//			statement.setString(1, maTrThai);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				String maTrangThai = rs.getString("maTrangThai");
//				String tenTrangThai = rs.getString("tenTrangThai");
//				trangThaiPhong = new TrangThaiPhong(maTrangThai, tenTrangThai);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return trangThaiPhong;
		
		try {
			String sql = "SELECT * FROM TrangThaiPhong WHERE maTrangThai = ?";
			em.getTransaction().begin();
			TrangThaiPhong ttp = (TrangThaiPhong) em.createNativeQuery(sql, TrangThaiPhong.class).setParameter(1, maTrThai).getSingleResult();
            em.close();
            return ttp;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public TrangThaiPhong timTrangThaiPhong_TheoTenTrangThai(String xTenTrThai) {
//		TrangThaiPhong trangThaiPhong = null;
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		try {
//			String sql = "SELECT * FROM TrangThaiPhong WHERE tenTrangThai = ?";
//			statement = con.prepareStatement(sql);
//			statement.setString(1, xTenTrThai);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				String maTrangThai = rs.getString("maTrangThai");
//				String tenTrangThai = rs.getString("tenTrangThai");
//				trangThaiPhong = new TrangThaiPhong(maTrangThai, tenTrangThai);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return trangThaiPhong;
		
		try {
			String sql = "SELECT * FROM TrangThaiPhong WHERE tenTrangThai = ?";
			em.getTransaction().begin();
			TrangThaiPhong ttp = (TrangThaiPhong) em.createNativeQuery(sql, TrangThaiPhong.class).setParameter(1, xTenTrThai).getSingleResult();
            em.close();
            return ttp;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean taoTrangThaiPhong(TrangThaiPhong trangThaiPhong) {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		int n = 0;
//		try {
//			statement = con.prepareStatement("INSERT INTO TrangThaiPhong values(?,?)");
//			statement.setString(1, trangThaiPhong.getMaTrangThai());
//			statement.setString(2, trangThaiPhong.getTenTrangThai());
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
			String sql = "INSERT INTO TrangThaiPhong values(?,?)";
			em.getTransaction().begin();
			int result = em.createNativeQuery(sql).setParameter(1, trangThaiPhong.getMaTrangThai())
					.setParameter(2, trangThaiPhong.getTenTrangThai()).executeUpdate();
			if (result ==  0) {
				em.getTransaction().rollback();
				em.close();
				return false;
			}
			em.getTransaction().commit();
			em.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean capNhatTrangThaiPhong(TrangThaiPhong trangThaiPhong) {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		int n = 0;
//		try {
//			statement = con.prepareStatement("UPDATE TrangThaiPhong SET tenTrangThai = ?" + " WHERE maTrangThai = ?");
//			statement.setString(1, trangThaiPhong.getTenTrangThai());
//			statement.setString(2, trangThaiPhong.getMaTrangThai());
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
			String sql = "UPDATE TrangThaiPhong SET tenTrangThai = ? WHERE maTrangThai = ?";
			em.getTransaction().begin();
			int result = em.createNativeQuery(sql).setParameter(1, trangThaiPhong.getTenTrangThai())
					.setParameter(2, trangThaiPhong.getMaTrangThai()).executeUpdate();
			if (result ==  0) {
				em.getTransaction().rollback();
				em.close();
				return false;
			}
			em.getTransaction().commit();
			em.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean xoaTrangThaiPhong(TrangThaiPhong trangThaiPhong) {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		int n = 0;
//		try {
//			statement = con.prepareStatement("DELETE FROM TrangThaiPhong" + " WHERE maTrangThai = ?");
//			statement.setString(1, trangThaiPhong.getTenTrangThai());
//			statement.setString(2, trangThaiPhong.getMaTrangThai());
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
			String sql = "DELETE FROM TrangThaiPhong WHERE maTrangThai = ?";
			em.getTransaction().begin();
			int result = em.createNativeQuery(sql).setParameter(1, trangThaiPhong.getMaTrangThai()).executeUpdate();
			if (result == 0) {
				em.getTransaction().rollback();
				em.close();
				return false;
			}
			em.getTransaction().commit();
			em.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
