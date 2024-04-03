package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.KhuyenMai;
import jakarta.persistence.EntityManager;

public class KhuyenMai_DAO {

	private EntityManager em;
	public KhuyenMai_DAO() {
		em = new ConnectDB().getEntityManager();
	}

	public ArrayList<KhuyenMai> layTatCaKhuyenMai() {
//		ArrayList<KhuyenMai> danhSachKhuyenMai = new ArrayList<KhuyenMai>();
//		try {
//			ConnectDB.getInstance();
//			Connection con = ConnectDB.getConnection();
//			String sql = "SELECT * FROM KhuyenMai";
//			Statement statement = con.createStatement();
//			ResultSet rs = statement.executeQuery(sql);
//			while (rs.next()) {
//				String maKhuyenMai = rs.getString("maKhuyenMai");
//				String tenKhuyenMai = rs.getString("tenKhuyenMai");
//				String maGiamGia = rs.getString("maGiamGia");
//				java.sql.Date ngayBatDau = rs.getDate("ngayBatDau");
//				java.sql.Date ngayKetThuc = rs.getDate("ngayKetThuc");
//				int tongSoLuong = rs.getInt("tongSoLuong");
//				Double chietKhau = rs.getDouble("chieuKhau");
//				String moTa = rs.getString("moTa");
//				KhuyenMai khuyenMai = new KhuyenMai(maKhuyenMai, tenKhuyenMai, maGiamGia, ngayBatDau, ngayKetThuc,
//						tongSoLuong, chietKhau, moTa);
//				danhSachKhuyenMai.add(khuyenMai);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return danhSachKhuyenMai;
		try {
			String sql = "SELECT * FROM KhuyenMai";
			em.getTransaction().begin();
			ArrayList<KhuyenMai> list = (ArrayList<KhuyenMai>) em.createQuery(sql).getResultList();
			em.close();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return  null;
		}
	}

	public KhuyenMai layKhuyenMai_TheoMaKhuyenMai(String maKM) {
//		KhuyenMai khuyenMai = null;
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		try {
//			String sql = "SELECT * FROM KhuyenMai WHERE maKhuyenMai = ?";
//			statement = con.prepareStatement(sql);
//			statement.setString(1, maKM);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				String maKhuyenMai = rs.getString("maKhuyenMai");
//				String tenKhuyenMai = rs.getString("tenKhuyenMai");
//				String maGiamGia = rs.getString("maGiamGia");
//				java.sql.Date ngayBatDau = rs.getDate("ngayBatDau");
//				java.sql.Date ngayKetThuc = rs.getDate("ngayKetThuc");
//				int tongSoLuong = rs.getInt("tongSoLuong");
//				Double chietKhau = rs.getDouble("chietKhau");
//				String moTa = rs.getString("moTa");
//				khuyenMai = new KhuyenMai(maKhuyenMai, tenKhuyenMai, maGiamGia, ngayBatDau, ngayKetThuc, tongSoLuong,
//						chietKhau, moTa);
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
//		return khuyenMai;
		
		try {
            String sql = "SELECT * FROM KhuyenMai WHERE maKhuyenMai = ?";
            em.getTransaction().begin();
            KhuyenMai km = (KhuyenMai) em.createNativeQuery(sql, KhuyenMai.class).setParameter(1, maKM).getSingleResult();
            em.close();
            return km;
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }
	}

	public KhuyenMai layKhuyenMai_TheoMaGiamGia(String maGG) {
//		KhuyenMai khuyenMai = null;
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		try {
//			String sql = "SELECT * FROM KhuyenMai WHERE maGiamGia = ?";
//			statement = con.prepareStatement(sql);
//			statement.setString(1, maGG);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				String maKhuyenMai = rs.getString("maKhuyenMai");
//				String tenKhuyenMai = rs.getString("tenKhuyenMai");
//				String maGiamGia = rs.getString("maGiamGia");
//				java.sql.Date ngayBatDau = rs.getDate("ngayBatDau");
//				java.sql.Date ngayKetThuc = rs.getDate("ngayKetThuc");
//				int tongSoLuong = rs.getInt("tongSoLuong");
//				Double chietKhau = rs.getDouble("chietKhau");
//				String moTa = rs.getString("moTa");
//				khuyenMai = new KhuyenMai(maKhuyenMai, tenKhuyenMai, maGiamGia, ngayBatDau, ngayKetThuc, tongSoLuong,
//						chietKhau, moTa);
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
//		return khuyenMai;
		
		try {
			String sql = "SELECT * FROM KhuyenMai WHERE maGiamGia = ?";
			em.getTransaction().begin();
			KhuyenMai km = (KhuyenMai) em.createNativeQuery(sql, KhuyenMai.class).setParameter(1, maGG)
					.getSingleResult();
			em.close();
			return km;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean taoKhuyenMai(KhuyenMai khuyenMai) {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		int n = 0;
//		try {
//			statement = con.prepareStatement("INSERT INTO KhuyenMai values(?,?,?,?,?,?,?,?)");
//			statement.setString(1, khuyenMai.getMaKhuyenMai());
//			statement.setString(2, khuyenMai.getTenKhuyenMai());
//			statement.setString(3, khuyenMai.getMaGiamGia());
//			statement.setDate(4, khuyenMai.getNgayBatDau());
//			statement.setDate(5, khuyenMai.getNgayKetThuc());
//			statement.setInt(6, khuyenMai.getTongSoLuong());
//			statement.setDouble(7, khuyenMai.getChietKhau());
//			statement.setString(8, khuyenMai.getMoTa());
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
			String sql = "INSERT INTO KhuyenMai values(?,?,?,?,?,?,?,?)";
			em.getTransaction().begin();
			int result = em.createNativeQuery(sql).setParameter(1, khuyenMai.getMaKhuyenMai())
					.setParameter(2, khuyenMai.getTenKhuyenMai()).setParameter(3, khuyenMai.getMaGiamGia())
					.setParameter(4, khuyenMai.getNgayBatDau()).setParameter(5, khuyenMai.getNgayKetThuc())
					.setParameter(6, khuyenMai.getTongSoLuong()).setParameter(7, khuyenMai.getChietKhau())
					.setParameter(8, khuyenMai.getMoTa()).executeUpdate();
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
			return false;
		}
		
	}

	public boolean capNhatKhuyenMai(KhuyenMai khuyenMai) {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		int n = 0;
//		try {
//			statement = con.prepareStatement(
//					"UPDATE KhuyenMai SET tenKhuyenMai = ?, maGiamGia = ?, ngayBatDau = ?, ngayKetThuc = ?, tongSoLuong = ?, chietKhau = ?, moTa = ?"
//							+ " WHER maKhuyenMai = ?");		
//			statement.setString(1, khuyenMai.getTenKhuyenMai());
//			statement.setString(2, khuyenMai.getMaGiamGia());
//			statement.setDate(3, khuyenMai.getNgayBatDau());
//			statement.setDate(4, khuyenMai.getNgayKetThuc());
//			statement.setInt(5, khuyenMai.getTongSoLuong());
//			statement.setDouble(6, khuyenMai.getChietKhau());
//			statement.setString(7, khuyenMai.getMoTa());
//			statement.setString(8, khuyenMai.getMaKhuyenMai());
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
			String sql ="UPDATE KhuyenMai SET tenKhuyenMai = ?, maGiamGia = ?, ngayBatDau = ?, ngayKetThuc = ?, tongSoLuong = ?, chietKhau = ?, moTa = ?"
					+ " WHER maKhuyenMai = ?";
			em.getTransaction().begin();
			int result = em.createNativeQuery(sql).setParameter(1, khuyenMai.getTenKhuyenMai())
					.setParameter(2, khuyenMai.getMaGiamGia()).setParameter(3, khuyenMai.getNgayBatDau())
					.setParameter(4, khuyenMai.getNgayKetThuc()).setParameter(5, khuyenMai.getTongSoLuong())
					.setParameter(6, khuyenMai.getChietKhau()).setParameter(7, khuyenMai.getMoTa())
					.setParameter(8, khuyenMai.getMaKhuyenMai()).executeUpdate();
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
	
	public boolean xoaKhuyenMai(KhuyenMai khuyenMai) {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		int n = 0;
//		try {
//			statement = con.prepareStatement(
//					"DELTETE FROM KhuyenMai"
//							+ " WHER maKhuyenMai = ?");
//			statement.setString(1, khuyenMai.getMaKhuyenMai());
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
			String sql = "DELTETE FROM KhuyenMai WHERE maKhuyenMai = ?";
			em.getTransaction().begin();
			int result = em.createNativeQuery(sql).setParameter(1, khuyenMai.getMaKhuyenMai()).executeUpdate();
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
