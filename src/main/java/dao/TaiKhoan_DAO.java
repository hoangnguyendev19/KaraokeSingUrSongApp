package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.ConnectDB;
import entity.NhanVien;
import entity.TaiKhoan;
import jakarta.persistence.EntityManager;

/**
 * TaiKhoanDAO Le Minh Quang 20/10/2023
 */
public class TaiKhoan_DAO {
	private EntityManager em;

	/**
	 * @param maNhanVien
	 * @param tenDangNhap
	 * @param matKhau
	 * @return boolean
	 */
	public TaiKhoan_DAO() {
		em = new ConnectDB().getEntityManager();
	}

	/**
	 * Tạo mới tài khoản
	 * @param maNhanVien
	 * @param tenDangNhap
	 * @param matKhau
	 * @param email
	 * @return true / false
	 */
	public boolean taoMoiTaiKhoan(String maNhanVien, String tenDangNhap, String matKhau, String email) {
	
//		Connection con = ConnectDB.getInstance().getConnection();
//		try {
//			PreparedStatement statement = con.prepareStatement("insert into TaiKhoan values(?,?,?,?,?)");
//			statement.setString(1, maNhanVien);
//			statement.setString(2, tenDangNhap);
//			statement.setString(3, matKhau);
//			statement.setBoolean(4, true);
//			statement.setString(5, email);
//			statement.executeUpdate();
//			con.close();
//			statement.close();
//		} catch (SQLException e) {
//			return false;
//		}
//		return true;
		
		try {
			String sql = "INSERT INTO TaiKhoan(maNhanVien, tenDangNhap, matKhau, trangThai, email) VALUES(?,?,?,?,?)";
			em.getTransaction().begin();
			int result = em.createNativeQuery(sql).setParameter(1, maNhanVien).setParameter(2, tenDangNhap)
					.setParameter(3, matKhau).setParameter(4, true).setParameter(5, email).executeUpdate();
			if(result ==0 ) {
                em.getTransaction().rollback();
                em.close();
                return false;
            }
			em.getTransaction().commit();
			em.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			em.close();
			return false;
		}
	}

	/**
	 * Tìm kiếm tài khoản theo tenDangNhap
	 * @param tenDangNhap
	 * @param matKhau
	 * @return taiKhoan
	 */
	public TaiKhoan timKiemTaiKhoan(String tenDangNhap) {
//		Connection con = ConnectDB.getInstance().getConnection();
//		TaiKhoan taiKhoan = null;
//		try {
//			PreparedStatement statement = con
//					.prepareStatement("SELECT * FROM TaiKhoan WHERE tenDangNhap = ?");
//			statement.setString(1, tenDangNhap);
//			ResultSet rs = statement.executeQuery();
//
//			while (rs.next()) {
//				NhanVien nhanVien = new NhanVien(rs.getString("maNhanVien"));
//				tenDangNhap = rs.getString("tenDangNhap");
//				String matKhau = rs.getString("matKhau");
//				Boolean trangThai = rs.getBoolean("trangThai");
//				String email = rs.getString("email");
//				taiKhoan = new TaiKhoan(nhanVien, tenDangNhap, matKhau, trangThai, email);
//			}
//
//		} catch (SQLException e) {
//			return null;
//		}
//		return taiKhoan;
		
		try {
			String sql = "SELECT * FROM TaiKhoan WHERE tenDangNhap = ?";
			em.getTransaction().begin();
			TaiKhoan taiKhoan = (TaiKhoan) em.createNativeQuery(sql, TaiKhoan.class).setParameter(1, tenDangNhap).getSingleResult();
			em.close();
			return taiKhoan;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			em.close();
			return null;
		}
	}
	

	/**
	 * Tìm kiếm tài khoản theo maNhanVien
	 * @param maNV
	 * @return taiKhoan
	 */
	public TaiKhoan timTaiKhoan_TheoMaNhanVien(String maNV) {
//		TaiKhoan taiKhoan = null;
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		try {
//			String sql = "SELECT * FROM TaiKhoan where maNhanVien = ?";
//			statement = con.prepareStatement(sql);
//			statement.setString(1, maNV);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				NhanVien nhanVien = new NhanVien(rs.getString("maNhanVien"));
//				String tenDangNhap = rs.getString("tenDangNhap");
//				String matKhau = rs.getString("matKhau");
//				Boolean trangThai = rs.getBoolean("trangThai");
//				String email = rs.getString("email");
//				taiKhoan = new TaiKhoan(nhanVien, tenDangNhap, matKhau, trangThai, email);
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
//		return taiKhoan;
		
		try {
			String sql = "SELECT * FROM TaiKhoan WHERE maNhanVien = ?";
			em.getTransaction().begin();
			TaiKhoan taiKhoan = (TaiKhoan) em.createNativeQuery(sql, TaiKhoan.class).setParameter(1, maNV).getSingleResult();
			em.close();
			return taiKhoan;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			em.close();
			return null;
		}
	}

	/**
	 * Cập nhật tài khoản theo tenDangNhap
	 * @param tenDangNhap
	 * @param matKhau
	 * @return true / false
	 */
	public boolean capNhatTaiKhoan_TheoTenDangNhap(String tenDangNhap, String matKhau) {
//		Connection con = ConnectDB.getInstance().getConnection();
//		int n = 0;
//		TaiKhoan taiKhoan = null;
//		try {
//			PreparedStatement statement = con.prepareStatement("UPDATE TaiKhoan SET  matKhau = ? WHERE tenDangNhap = ?");
//			statement.setString(1, matKhau);
//			statement.setString(2, tenDangNhap);
//
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				NhanVien nhanVien = new NhanVien(rs.getString("maNhanVien"));
//				tenDangNhap = rs.getString("tenDangNhap");
//				matKhau = rs.getString("matKhau");
//				Boolean trangThai = rs.getBoolean("trangThai");
//				String email = rs.getString("email");
//				taiKhoan = new TaiKhoan(nhanVien, tenDangNhap, matKhau, trangThai, email);
//				n++;
//			
//			}
//		} catch (SQLException e) {
//			return n > 0;
//		}
//		return n > 0;
		
		try {
			String sql = "UPDATE TaiKhoan SET  matKhau = ? WHERE tenDangNhap = ?";
			em.getTransaction().begin();
			int result = em.createNativeQuery(sql).setParameter(1, matKhau).setParameter(2, tenDangNhap).executeUpdate();
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
			em.close();
			return false;
		}
	}

	/**
	 * Cập nhật tài khoản theo maNhanVien
	 * @param tenDangNhap
	 * @param maNhanVien
	 * @param matKhau
	 * @return taiKhoan
	 */
	public TaiKhoan capNhatTaiKhoan_TheoMaNhanVien(String tenDangNhap, String maNhanVien, String matKhau) {
//		Connection con = ConnectDB.getInstance().getConnection();
//		TaiKhoan taiKhoan = null;
//		try {
//			PreparedStatement statement = con.prepareStatement("UPDATE TaiKhoan SET  maKhau = ? WHERE maNhanVien = ?");
//			statement.setString(1, matKhau);
//			statement.setString(2, maNhanVien);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				NhanVien nhanVien = new NhanVien(rs.getString("maNhanVien"));
//				tenDangNhap = rs.getString("tenDangNhap");
//				matKhau = rs.getString("matKhau");
//				Boolean trangThai = rs.getBoolean("trangThai");
//				String email = rs.getString("email");
//				taiKhoan = new TaiKhoan(nhanVien, tenDangNhap, matKhau, trangThai, email);
//			}
//		} catch (SQLException e) {
//			return null;
//		}
//		return taiKhoan;
		
		try {
			String sql = "UPDATE TaiKhoan SET  matKhau = ? WHERE maNhanVien = ?";
			em.getTransaction().begin();
			int result = em.createNativeQuery(sql).setParameter(1, matKhau).setParameter(2, maNhanVien).executeUpdate();
			TaiKhoan taiKhoan = (TaiKhoan) em.createNativeQuery("SELECT * FROM TaiKhoan WHERE maNhanVien = ?", TaiKhoan.class).setParameter(1, maNhanVien).getSingleResult();
			
			if (result == 0) {
				em.getTransaction().rollback();
				em.close();
				return null;
			}
			
			em.getTransaction().commit();
			em.close();
			return taiKhoan;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			em.close();
			return null;
		}
	}
	
	/**
	 * Cập nhật email theo maNhanVien
	 * @param tenDangNhap
	 * @param maNhanVien
	 * @param email
	 * @return taiKhoan
	 */
	public TaiKhoan capNhatEmail_TheoMaNhanVien(String tenDangNhap, String maNhanVien, String email) {
//		Connection con = ConnectDB.getInstance().getConnection();
//		TaiKhoan taiKhoan = null;
//		try {
//			PreparedStatement statement = con.prepareStatement("UPDATE TaiKhoan SET  email = ? WHERE maNhanVien = ?");
//			statement.setString(1, email);
//			statement.setString(2, maNhanVien);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				NhanVien nhanVien = new NhanVien(rs.getString("maNhanVien"));
//				tenDangNhap = rs.getString("tenDangNhap");
//				String matKhau = rs.getString("matKhau");
//				Boolean trangThai = rs.getBoolean("trangThai");
//				email = rs.getString("email");
//				taiKhoan = new TaiKhoan(nhanVien, tenDangNhap, matKhau, trangThai, email);
//			}
//		} catch (SQLException e) {
//			return null;
//		}
//		return taiKhoan;
		
		try {
			String sql = "UPDATE TaiKhoan SET  email = ? WHERE maNhanVien = ?";
			em.getTransaction().begin();
			int result = em.createNativeQuery(sql).setParameter(1, email).setParameter(2, maNhanVien).executeUpdate();
			TaiKhoan taiKhoan = (TaiKhoan) em.createNativeQuery("SELECT * FROM TaiKhoan WHERE maNhanVien = ?", TaiKhoan.class).setParameter(1, maNhanVien).getSingleResult();
			if (result == 0) {
				em.getTransaction().rollback();
				em.close();
				return null;
			}

			em.getTransaction().commit();
			em.close();
			return taiKhoan;
		} catch (Exception e) {
			em.getTransaction().rollback();
			em.close();
			e.printStackTrace();
			return null;
		} 
		
	}
	
	/**
	 * Cập nhật trạng thái theo maNhanVien
	 * @param maNhanVien
	 * @param trangThai
	 * @return taiKhoan
	 */
	public TaiKhoan capNhatTrangThai_TheoMaNhanVien(String maNhanVien, Boolean trangThai) {
//		Connection con = ConnectDB.getInstance().getConnection();
//		TaiKhoan taiKhoan = null;
//		try {
//			PreparedStatement statement = con.prepareStatement("UPDATE TaiKhoan SET  trangThai = ? WHERE maNhanVien = ?");
//			statement.setBoolean(1, trangThai);
//			statement.setString(2, maNhanVien);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				NhanVien nhanVien = new NhanVien(rs.getString("maNhanVien"));
//				String tenDangNhap = rs.getString("tenDangNhap");
//				String matKhau = rs.getString("matKhau");
//				trangThai = rs.getBoolean("trangThai");
//				String email = rs.getString("email");
//				taiKhoan = new TaiKhoan(nhanVien, tenDangNhap, matKhau, trangThai, email);
//			}
//		} catch (SQLException e) {
//			return null;
//		}
//		return taiKhoan;
		
		try {
			String sql = "UPDATE TaiKhoan SET  trangThai = ? WHERE maNhanVien = ?";
			em.getTransaction().begin();
			int result = em.createNativeQuery(sql).setParameter(1, trangThai).setParameter(2, maNhanVien).executeUpdate();
			TaiKhoan taiKhoan = (TaiKhoan) em.createNativeQuery("SELECT * FROM TaiKhoan WHERE maNhanVien = ?", TaiKhoan.class).setParameter(1, maNhanVien).getSingleResult();
			if (result == 0) {
				em.getTransaction().rollback();
				em.close();
				return null;
			}
			
			em.getTransaction().commit();
			em.close();
			return taiKhoan;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			em.close();
			return null;
		}
	}
	
	/**
	 * Tìm tài khoản theo tenDangNhap
	 * @param tenDangNhap
	 * @return taiKhoan
	 */
	public TaiKhoan timTaiKhoan_TheoTenDangNhap(String tenDangNhap) {
//		Connection con = ConnectDB.getInstance().getConnection();
//		TaiKhoan taiKhoan = null;
//		try {
//			PreparedStatement statement = con
//					.prepareStatement("SELECT * FROM TaiKhoan WHERE tenDangNhap = ?");
//			statement.setString(1, tenDangNhap);
//			ResultSet rs = statement.executeQuery();
//
//			while (rs.next()) {
//				NhanVien nhanVien = new NhanVien(rs.getString("maNhanVien"));
//				tenDangNhap = rs.getString("tenDangNhap");
//				String matKhau = rs.getString("matKhau");
//				Boolean trangThai = rs.getBoolean("trangThai");
//				String email = rs.getString("email");
//				taiKhoan = new TaiKhoan(nhanVien, tenDangNhap, matKhau, trangThai, email);
//			}
//
//		} catch (SQLException e) {
//			return null;
//		}
//		return taiKhoan;
		
		try {
			String sql = "SELECT * FROM TaiKhoan WHERE tenDangNhap = ?";
			em.getTransaction().begin();
			TaiKhoan taiKhoan = (TaiKhoan) em.createNativeQuery(sql, TaiKhoan.class).setParameter(1, tenDangNhap).getSingleResult();
			em.close();
			return taiKhoan;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			em.close();
			return null;
		}
		
	}
}