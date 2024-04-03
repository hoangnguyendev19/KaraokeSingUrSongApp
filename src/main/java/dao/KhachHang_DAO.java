package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhachHang;
import jakarta.persistence.EntityManager;

public class KhachHang_DAO {

	private EntityManager em;
	public KhachHang_DAO() {
		em = new ConnectDB().getEntityManager();
	}

	public ArrayList<KhachHang> layTatCaKhachHang() {
//		ArrayList<KhachHang> danhSachKhachHang = new ArrayList<KhachHang>();
//		try {
//			ConnectDB.getInstance();
//			Connection con = ConnectDB.getConnection();
//			String sql = "SELECT * FROM KhachHang";
//			Statement statement = con.createStatement();
//			ResultSet rs = statement.executeQuery(sql);
//
//			while (rs.next()) {
//				String maKhachHang = rs.getString("maKhachHang");
//				String hoTen = rs.getString("hoTen");
//				Boolean gioiTinh = rs.getBoolean("gioiTinh");
//				java.sql.Date ngaySinh = rs.getDate("ngaySinh");
//				String diaChi = rs.getString("diaChi");
//				String soDienThoai = rs.getString("soDienThoai");
//				int diemThuong = rs.getInt("diemThuong");
//				String ghiChu = rs.getString("ghiChu");
//				KhachHang khachHang = new KhachHang(maKhachHang, hoTen, gioiTinh, ngaySinh, diaChi, soDienThoai,
//						diemThuong, ghiChu);
//				danhSachKhachHang.add(khachHang);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return danhSachKhachHang;
		
		try {
			String sql = "SELECT * FROM KhachHang";
			em.getTransaction().begin();
			ArrayList<KhachHang> list = (ArrayList<KhachHang>) em.createQuery(sql).getResultList();
			
			em.close();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return  null;
		}
	}

	public KhachHang layKhachHang_TheoMaKhachHang(String maKH) {

//		KhachHang khachHang = null;
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//
//		try {
//			String sql = "SELECT * FROM KhachHang WHERE maKhachHang = ?";
//			statement = con.prepareStatement(sql);
//			statement.setString(1, maKH);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				String maKhachHang = rs.getString("maKhachHang");
//				String hoTen = rs.getString("hoTen");
//				Boolean gioiTinh = rs.getBoolean("gioiTinh");
//				java.sql.Date ngaySinh = rs.getDate("ngaySinh");
//				String diaChi = rs.getString("diaChi");
//				String soDienThoai = rs.getString("soDienThoai");
//				int diemThuong = rs.getInt("diemThuong");
//				String ghiChu = rs.getString("ghiChu");
//				khachHang = new KhachHang(maKhachHang, hoTen, gioiTinh, ngaySinh, diaChi, soDienThoai, diemThuong,
//						ghiChu);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				statement.close();
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//		return khachHang;
		
		try {
            String sql = "SELECT * FROM KhachHang WHERE maKhachHang = ?";
            em.getTransaction().begin();
            KhachHang kh = (KhachHang) em.createNativeQuery(sql, KhachHang.class).setParameter(1, maKH).getSingleResult();
            em.close();
            return kh;
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }
	}

	public KhachHang layKhachHang_TheoSoDienThoai(String soDT) {

//		KhachHang khachHang = null;
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//
//		try {
//			String sql = "SELECT * FROM KhachHang WHERE soDienThoai = ?";
//			statement = con.prepareStatement(sql);
//			statement.setString(1, soDT);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//
//				String maKhachHang = rs.getString("maKhachHang");
//				String hoTen = rs.getString("hoTen");
//				Boolean gioiTinh = rs.getBoolean("gioiTinh");
//				java.sql.Date ngaySinh = rs.getDate("ngaySinh");
//				String diaChi = rs.getString("diaChi");
//				String soDienThoai = rs.getString("soDienThoai");
//				int diemThuong = rs.getInt("diemThuong");
//				String ghiChu = rs.getString("ghiChu");
//				khachHang = new KhachHang(maKhachHang, hoTen, gioiTinh, ngaySinh, diaChi, soDienThoai, diemThuong,
//						ghiChu);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				statement.close();
//			} catch (Exception e2) {
//
//				e2.printStackTrace();
//			}
//		}
//		return khachHang;
		
		try {
			String sql = "SELECT * FROM KhachHang WHERE soDienThoai = ?";
			em.getTransaction().begin();
			KhachHang kh = (KhachHang) em.createNativeQuery(sql, KhachHang.class).setParameter(1, soDT)
					.getSingleResult();
			em.close();
			return kh;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public KhachHang layKhachHang_TheoSDT(String SDT) {

//		KhachHang khachHang = null;
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//
//		try {
//			String sql = "SELECT * FROM KhachHang WHERE soDienThoai = ?";
//			statement = con.prepareStatement(sql);
//			statement.setString(1, SDT);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//
//				String maKhachHang = rs.getString("maKhachHang");
//				String hoTen = rs.getString("hoTen");
//				Boolean gioiTinh = rs.getBoolean("gioiTinh");
//				java.sql.Date ngaySinh = rs.getDate("ngaySinh");
//				String diaChi = rs.getString("diaChi");
//				String soDienThoai = rs.getString("soDienThoai");
//				int diemThuong = rs.getInt("diemThuong");
//				String ghiChu = rs.getString("ghiChu");
//				khachHang = new KhachHang(maKhachHang, hoTen, gioiTinh, ngaySinh, diaChi, soDienThoai, diemThuong,
//						ghiChu);
//			}
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		} finally {
//			try {
//				statement.close();
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//		return khachHang;
		
		try {
			String sql = "SELECT * FROM KhachHang WHERE soDienThoai = ?";
			em.getTransaction().begin();
			KhachHang kh = (KhachHang) em.createNativeQuery(sql, KhachHang.class).setParameter(1, SDT)
					.getSingleResult();
			em.close();
			return kh;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<KhachHang> layKhachHang_TheoTen(String ten) {
//
//		KhachHang khachHang = null;
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		ArrayList<KhachHang> dsKH = new ArrayList<>();
//		try {
//			String sql = "SELECT * FROM KhachHang WHERE hoTen like N'%" + ten.trim() + "%'";
//			statement = con.prepareStatement(sql);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				String maKhachHang = rs.getString("maKhachHang");
//				String hoTen = rs.getString("hoTen");
//				Boolean gioiTinh = rs.getBoolean("gioiTinh");
//				java.sql.Date ngaySinh = rs.getDate("ngaySinh");
//				String diaChi = rs.getString("diaChi");
//				String soDienThoai = rs.getString("soDienThoai");
//				int diemThuong = rs.getInt("diemThuong");
//				String ghiChu = rs.getString("ghiChu");			
//				khachHang = new KhachHang(maKhachHang, hoTen, gioiTinh, ngaySinh, diaChi, soDienThoai, diemThuong,
//						ghiChu);
//				dsKH.add(khachHang);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				statement.close();
//			} catch (Exception e2) {
//
//				e2.printStackTrace();
//			}
//		}
//		return dsKH;
		
		try {
            String sql =	"SELECT * FROM KhachHang WHERE hoTen like N'% ? %'";
            em.getTransaction().begin();
            ArrayList<KhachHang> list = (ArrayList<KhachHang>) em.createNativeQuery(sql, KhachHang.class).setParameter(1, ten.trim()).getSingleResult();
            em.close();
            return list;
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }
	}

	public boolean taoKhachHang(KhachHang khachHang) {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		int n = 0;
//		try {
//			statement = con.prepareStatement("INSERT INTO KhachHang values(?,?,?,?,?,?,?,?)");
//			statement.setString(1, khachHang.getMaKhachHang());
//			statement.setString(2, khachHang.getHoTen());
//			statement.setBoolean(3, khachHang.isGioiTinh());
//			statement.setDate(4, khachHang.getNgaySinh());
//			statement.setString(5, khachHang.getDiaChi());
//			statement.setString(6, khachHang.getSoDienThoai());
//			statement.setInt(7, khachHang.getDiemThuong());
//			statement.setString(8, khachHang.getGhiChu());
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
			String sql = "INSERT INTO KhachHang values(?,?,?,?,?,?,?,?)";
			em.getTransaction().begin();
			int result = em.createNativeQuery(sql, KhachHang.class).setParameter(1, khachHang.getMaKhachHang())
					.setParameter(2, khachHang.getHoTen()).setParameter(3, khachHang.isGioiTinh())
					.setParameter(4, khachHang.getNgaySinh()).setParameter(5, khachHang.getDiaChi())
					.setParameter(6, khachHang.getSoDienThoai()).setParameter(7, khachHang.getDiemThuong())
					.setParameter(8, khachHang.getGhiChu()).executeUpdate();
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

	public boolean capNhatKhachHang(KhachHang khachHang) {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		int n = 0;
//		try {
//			statement = con.prepareStatement(
//					"UPDATE KhachHang SET hoTen = ?, gioiTinh = ?, ngaySinh = ?, diaChi = ?, soDienThoai = ?, diemThuong = ?, ghiChu = ? WHERE maKhachHang = ?");
//			statement.setString(1, khachHang.getHoTen());
//			statement.setBoolean(2, khachHang.isGioiTinh());
//			statement.setDate(3, khachHang.getNgaySinh());
//			statement.setString(4, khachHang.getDiaChi());
//			statement.setString(5, khachHang.getSoDienThoai());
//			statement.setInt(6, khachHang.getDiemThuong());
//			statement.setString(7, khachHang.getGhiChu());
//			statement.setString(8, khachHang.getMaKhachHang());
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
			String sql = "UPDATE KhachHang SET hoTen = ?, gioiTinh = ?, ngaySinh = ?, diaChi = ?, soDienThoai = ?, diemThuong = ?, ghiChu = ? WHERE maKhachHang = ?";
			em.getTransaction().begin();
			int result = em.createNativeQuery(sql, KhachHang.class).setParameter(1, khachHang.getHoTen())
					.setParameter(2, khachHang.isGioiTinh()).setParameter(3, khachHang.getNgaySinh())
					.setParameter(4, khachHang.getDiaChi()).setParameter(5, khachHang.getSoDienThoai())
					.setParameter(6, khachHang.getDiemThuong()).setParameter(7, khachHang.getGhiChu())
					.setParameter(8, khachHang.getMaKhachHang()).executeUpdate();
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

	public boolean xoaKhachHang(KhachHang khachHang) {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		int n = 0;
//		try {
//			statement = con.prepareStatement("DELETE FROM KhachHang" + " WHERE maKhachHang = ?");
//			statement.setString(1, khachHang.getMaKhachHang());
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
			String sql = "DELETE FROM KhachHang WHERE maKhachHang = ?";
			em.getTransaction().begin();
			int result = em.createNativeQuery(sql, KhachHang.class).setParameter(1, khachHang.getMaKhachHang())
					.executeUpdate();
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