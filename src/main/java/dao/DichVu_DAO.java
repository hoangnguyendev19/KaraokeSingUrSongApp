package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.DichVu;
import entity.ThongTinDichVu;
import jakarta.persistence.EntityManager;

public class DichVu_DAO {

	private EntityManager em;

	public DichVu_DAO() {
		em = new ConnectDB().getEntityManager();
	}

	public int soLuongDichVu() {
//		Connection con = new ConnectDB().getConnection();
//		int dem = 0;
//		try {
//			PreparedStatement statement = con.prepareStatement("select count(maDichVu) as Dem from DichVu");
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				dem = rs.getInt("Dem");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return dem;

		try {
			// create native query
			String sql = "select count(maDichVu) as Dem from DichVu";
			em.getTransaction().begin();
			int dem = (int) em.createNativeQuery(sql).getSingleResult();

			em.close();
			return dem;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}

	public ArrayList<DichVu> phanTrangDichVu(int fn, int ln) {
//		Connection con = new ConnectDB().getConnection();
//		ArrayList<DichVu> list = new ArrayList<DichVu>();
//		PreparedStatement statement = null;
//
//		String sql = "select *from(select ROW_NUMBER() over (order by maDichVu)as STT,maDichVu,tenDichVu,donViTinh,donGia,trangThai,maThongTinDichVu from DichVu) as PhanTrang where PhanTrang.STT Between ? and ?";
//
//		try {
//			statement = con.prepareStatement(sql);
//			statement.setInt(1, fn);
//			statement.setInt(2, ln);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//
//				String maDichVu = rs.getString("maDichVu");
//				String tenDichVu = rs.getString("tenDichVu");
//				String donViTinh = rs.getString("donViTinh");
//				Double donGia = rs.getDouble("donGia");
//				boolean trangThai = rs.getBoolean("trangThai");
//				String maTTDV = rs.getString("maThongTinDichVu");
//
//				ThongTinDichVu_DAO DAO_TTDV = new ThongTinDichVu_DAO();
//				ThongTinDichVu ttdv = new ThongTinDichVu();
//				ttdv = DAO_TTDV.timThongTinDichVu_TheoMaThongTinDichVu(rs.getString("maThongTinDichVu"));
//
//				DichVu dichVu = new DichVu(maDichVu, tenDichVu, donViTinh, donGia, trangThai, ttdv);
//				list.add(dichVu);
//			}
//			statement.close();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//
//		}
//
//		return list;

		try {
			String sql = "select *from(select ROW_NUMBER() over (order by maDichVu)as STT,maDichVu,tenDichVu,donViTinh,donGia,trangThai,maThongTinDichVu from DichVu) as PhanTrang where PhanTrang.STT Between ? and ?";
			em.getTransaction().begin();
			ArrayList<DichVu> ds = (ArrayList<DichVu>) em.createNativeQuery(sql, DichVu.class).setParameter(1, fn)
					.setParameter(2, ln).getResultList();

			em.close();
			return ds;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}

	public ArrayList<DichVu> layTatCaDichVu() {
//		ArrayList<DichVu> danhSachDichVu = new ArrayList<DichVu>();
//		try {
//			ConnectDB.getInstance();
//			Connection con = ConnectDB.getConnection();
//			String sql = "SELECT * FROM DichVu";
//			Statement statement = con.createStatement();
//			ResultSet rs = statement.executeQuery(sql);
//			while (rs.next()) {
//				String maDichVu = rs.getString("maDichVu");
//				String tenDichVu = rs.getString("tenDichVu");
//				String donViTinh = rs.getString("donViTinh");
//				Double donGia = rs.getDouble("donGia");
//				boolean trangThai = rs.getBoolean("trangThai");
//				String maTTDV = rs.getString("maThongTinDichVu");
//				ThongTinDichVu_DAO DAO_TTDV = new ThongTinDichVu_DAO();
//				ThongTinDichVu ttdv = DAO_TTDV.timThongTinDichVu_TheoMaThongTinDichVu(maTTDV);
//
//				DichVu dichVu = new DichVu(maDichVu, tenDichVu, donViTinh, donGia, trangThai, ttdv);
//				danhSachDichVu.add(dichVu);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return danhSachDichVu;

		try {
			String sql = "SELECT * FROM DichVu";
			em.getTransaction().begin();
			ArrayList<DichVu> ds = (ArrayList<DichVu>) em.createNativeQuery(sql, DichVu.class).getResultList();

			em.close();
			return ds;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public DichVu layDichVu_TheoMaDichVu(String maDV) {
//		DichVu dichVu = null;
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		try {
//			String sql = "SELECT * FROM DichVu WHERE maDichVu = ?";
//			statement = con.prepareStatement(sql);
//			statement.setString(1, maDV);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				String maDichVu = rs.getString("maDichVu");
//				String tenDichVu = rs.getString("tenDichVu");
//				String donViTinh = rs.getString("donViTinh");
//				Double donGia = rs.getDouble("donGia");
//				boolean trangThai = rs.getBoolean("trangThai");
//				String maTTDV = rs.getString("maThongTinDichVu");
//				ThongTinDichVu_DAO DAO_TTDV = new ThongTinDichVu_DAO();
//				ThongTinDichVu ttdv = DAO_TTDV.timThongTinDichVu_TheoMaThongTinDichVu(maTTDV);
//
//				dichVu = new DichVu(maDichVu, tenDichVu, donViTinh, donGia, trangThai, ttdv);
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
//		return dichVu;

		try {
			String sql = "SELECT * FROM DichVu WHERE maDichVu = ?";
			em.getTransaction().begin();
			DichVu dv = (DichVu) em.createNativeQuery(sql, DichVu.class).setParameter(1, maDV).getSingleResult();

			em.close();
			return dv;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public DichVu layDuyNhatMotDichVu_TheoTenDichVu(String tenDichVuTK) {
//		DichVu dichVu = null;
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		try {
//			String sql = "SELECT * FROM DichVu WHERE tenDichVu Like N'%"+tenDichVuTK+"%'";
//			statement = con.prepareStatement(sql);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				String maDichVu = rs.getString("maDichVu");
//				String tenDichVu = rs.getString("tenDichVu");
//				String donViTinh = rs.getString("donViTinh");
//				Double donGia = rs.getDouble("donGia");
//				boolean trangThai = rs.getBoolean("trangThai");
//				String maTTDV = rs.getString("maThongTinDichVu");
//				ThongTinDichVu_DAO DAO_TTDV = new ThongTinDichVu_DAO();
//				ThongTinDichVu ttdv = DAO_TTDV.timThongTinDichVu_TheoMaThongTinDichVu(maTTDV);
//
//				dichVu = new DichVu(maDichVu, tenDichVu, donViTinh, donGia, trangThai, ttdv);
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
//		return dichVu;

		try {
			String sql = "SELECT * FROM DichVu WHERE tenDichVu = Like N'%?%'";
			em.getTransaction().begin();
			DichVu dv = (DichVu) em.createNativeQuery(sql, DichVu.class).setParameter(1, tenDichVuTK).getSingleResult();

			em.close();
			return dv;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<DichVu> layDichVu_TheoTenDichVu(String tenDichVuTK) {
//		ArrayList<DichVu> danhSachDichVu = new ArrayList<DichVu>();
//		DichVu dichVu = null;
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		try {
//			String sql = "SELECT * FROM DichVu WHERE tenDichVu Like N'%"+tenDichVuTK+"%'";
//			statement = con.prepareStatement(sql);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				String maDichVu = rs.getString("maDichVu");
//				String tenDichVu = rs.getString("tenDichVu");
//				String donViTinh = rs.getString("donViTinh");
//				Double donGia = rs.getDouble("donGia");
//				boolean trangThai = rs.getBoolean("trangThai");
//				String maTTDV = rs.getString("maThongTinDichVu");
//				ThongTinDichVu_DAO DAO_TTDV = new ThongTinDichVu_DAO();
//				ThongTinDichVu ttdv = DAO_TTDV.timThongTinDichVu_TheoMaThongTinDichVu(maTTDV);
//
//				dichVu = new DichVu(maDichVu, tenDichVu, donViTinh, donGia, trangThai, ttdv);
//				danhSachDichVu.add(dichVu);
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
//		return danhSachDichVu;

		try {
			String sql = "SELECT * FROM DichVu WHERE tenDichVu = Like N'%?%'";
			em.getTransaction().begin();
			ArrayList<DichVu> ds = (ArrayList<DichVu>) em.createNativeQuery(sql, DichVu.class)
					.setParameter(1, tenDichVuTK).getResultList();

			em.close();
			return ds;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public boolean taoDichVu(DichVu dichVu) {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		int n = 0;
//		try {
//			statement = con.prepareStatement("INSERT INTO DichVu values(?,?,?,?,?,?)");
//			statement.setString(1, dichVu.getMaDichVu());
//			statement.setString(2, dichVu.getTenDichVu());
//			statement.setString(3, dichVu.getDonViTinh());
//			statement.setDouble(4, dichVu.getDonGia());
//			statement.setBoolean(5, dichVu.isTrangThai());
//			statement.setString(6, dichVu.getThongTinDichVu().getMaThongTinDichVu());
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
			String sql = "INSERT INTO DichVu values(?,?,?,?,?,?)";
			em.getTransaction().begin();
			int result = em.createNativeQuery(sql).setParameter(1, dichVu.getMaDichVu())
					.setParameter(2, dichVu.getTenDichVu()).setParameter(3, dichVu.getDonViTinh())
					.setParameter(4, dichVu.getDonGia()).setParameter(5, dichVu.isTrangThai())
					.setParameter(6, dichVu.getThongTinDichVu().getMaThongTinDichVu()).executeUpdate();

			em.close();
			return result > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public int capNhatDichVu(DichVu dichVu) {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		int n = 0;
//		try {
//			statement = con.prepareStatement(
//					"UPDATE DichVu SET tenDichVu = ?, donViTinh = ?, donGia = ?, trangThai = ?, maThongTinDichVu = ?"
//							+ " WHERE maDichVu = ?");
//			statement.setString(1, dichVu.getTenDichVu());
//			statement.setString(2, dichVu.getDonViTinh());
//			statement.setDouble(3, dichVu.getDonGia());
//			statement.setBoolean(4, dichVu.isTrangThai());
//			statement.setString(5, dichVu.getThongTinDichVu().getMaThongTinDichVu());
//			statement.setString(6, dichVu.getMaDichVu());
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
//
//		return n;
		
		try {
			String sql = "UPDATE DichVu SET tenDichVu = ?, donViTinh = ?, donGia = ?, trangThai = ?, maThongTinDichVu = ?"
					+ " WHERE maDichVu = ?";
			
			em.getTransaction().begin();
			int result = em.createNativeQuery(sql).setParameter(1, dichVu.getTenDichVu())
					.setParameter(2, dichVu.getDonViTinh()).setParameter(3, dichVu.getDonGia())
					.setParameter(4, dichVu.isTrangThai())
					.setParameter(5, dichVu.getThongTinDichVu().getMaThongTinDichVu())
					.setParameter(6, dichVu.getMaDichVu()).executeUpdate();
			
			if (result == 0) {
				em.getTransaction().rollback();
				em.close();
				return 0;
			}
			
			em.getTransaction().commit();
			em.close();
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}

	public ArrayList<DichVu> locDichVu(String ngayNhap, String trangThaiLoc, String giaBD, String giaKT) {
//		ConnectDB.getInstance();
//
//		String sql = "SELECT *\r\n" + "FROM DichVu dv\r\n"
//				+ "JOIN ThongTinDichVu tt ON dv.maThongTinDichVu = tt.maThongTinDichVu\r\n" + "WHERE "
//				+ "tt.ngayNhap= '" + ngayNhap + "'\r\n" + "    AND dv.trangThai = " + trangThaiLoc + "\r\n"
//				+ "    AND dv.donGia BETWEEN " + giaBD + " AND " + giaKT;
//		ArrayList<DichVu> danhSachDichVu = new ArrayList<DichVu>();
//		try {
//			ConnectDB.getInstance();
//			Connection con = ConnectDB.getConnection();
//			Statement statement = con.createStatement();
//			ResultSet rs = statement.executeQuery(sql);
//			while (rs.next()) {
//				String maDichVu = rs.getString("maDichVu");
//				String tenDichVu = rs.getString("tenDichVu");
//				String donViTinh = rs.getString("donViTinh");
//				Double donGia = rs.getDouble("donGia");
//				boolean trangThai = rs.getBoolean("trangThai");
//				String maTTDV = rs.getString("maThongTinDichVu");
//				ThongTinDichVu_DAO DAO_TTDV = new ThongTinDichVu_DAO();
//				ThongTinDichVu ttdv = DAO_TTDV.timThongTinDichVu_TheoMaThongTinDichVu(maTTDV);
//
//				DichVu dichVu = new DichVu(maDichVu, tenDichVu, donViTinh, donGia, trangThai, ttdv);
//				danhSachDichVu.add(dichVu);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return danhSachDichVu;
		
		try {
			String sql = "SELECT *\r\n" + "FROM DichVu dv\r\n"
					+ "JOIN ThongTinDichVu tt ON dv.maThongTinDichVu = tt.maThongTinDichVu\r\n" + "WHERE "
					+ "tt.ngayNhap= ?\r\n" + "    AND dv.trangThai = ?\r\n" + "    AND dv.donGia BETWEEN ? AND ?";
			
			em.getTransaction().begin();
			ArrayList<DichVu> ds = (ArrayList<DichVu>) em.createNativeQuery(sql, DichVu.class).setParameter(1, ngayNhap)
					.setParameter(2, trangThaiLoc).setParameter(3, giaBD).setParameter(4, giaKT).getResultList();
			
			em.close();
			return ds;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public boolean xoaDichVu(DichVu dichVu) {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		int n = 0;
//		try {
//			statement = con.prepareStatement("DELETE FROM DichVu" + " WHERE maDichVu = ?");
//			statement.setString(1, dichVu.getMaDichVu());
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
			String sql = "DELETE FROM DichVu" + " WHERE maDichVu = ?";
			em.getTransaction().begin();
			int result = em.createNativeQuery(sql).setParameter(1, dichVu.getMaDichVu()).executeUpdate();
			
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
			return false;
		}
	}

	public ArrayList<DichVu> loc_TongHop(String ngayBD, String ngayKT, int thang, int nam, int quy) {
//		ArrayList<DichVu> dsDV = new ArrayList<>();
//
//		boolean locTheoThang = false;
//		boolean locTheoNam = false;
//		boolean locTheoQuy = false;
//
//		if (thang != 0)
//			locTheoThang = true;
//
//		if (quy != 0)
//			locTheoQuy = true;
//
//		if (nam != 0)
//			locTheoNam = true;
//
//		String sql = "select * from DichVu dv join ThongTinDichVu ttdv on dv.maThongTinDichVu = ttdv.maThongTinDichVu "
//				+ "WHERE ttdv.ngayNhap BETWEEN '" + ngayBD + "' AND '" + ngayKT + "' ";
//
//		if (locTheoThang) {
//			sql += " AND MONTH(ngayNhap) = ? ";
//		}
//
//		if (locTheoNam) {
//			sql += " AND YEAR(ngayNhap) = ? ";
//		}
//
//		if (locTheoQuy) {
//			sql += " AND DATENAME(QUARTER, ngayNhap) = ? ";
//		}
//
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//
//		try {
//			statement = con.prepareStatement(sql);
//
//			int parameterIndex = 1;
//
//			if (locTheoThang) {
//				statement.setInt(parameterIndex++, thang);
//			}
//
//			if (locTheoNam) {
//				statement.setInt(parameterIndex++, nam);
//			}
//
//			if (locTheoQuy) {
//				statement.setInt(parameterIndex++, quy);
//			}
//
//			ResultSet rs = statement.executeQuery();
//
//			while (rs.next()) {
//				String maDichVu = rs.getString("maDichVu");
//				String tenDichVu = rs.getString("tenDichVu");
//				String donViTinh = rs.getString("donViTinh");
//				Double donGia = rs.getDouble("donGia");
//				boolean trangThai = rs.getBoolean("trangThai");
//				String maTTDV = rs.getString("maThongTinDichVu");
//				ThongTinDichVu_DAO DAO_TTDV = new ThongTinDichVu_DAO();
//				ThongTinDichVu ttdv = DAO_TTDV.timThongTinDichVu_TheoMaThongTinDichVu(maTTDV);
//				DichVu dichVu = new DichVu(maDichVu, tenDichVu, donViTinh, donGia, trangThai, ttdv);
//				dsDV.add(dichVu);
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return dsDV;
		
		try {
			// Please help me customize that code and use to create native query
			String sql = "select * from DichVu dv join ThongTinDichVu ttdv on dv.maThongTinDichVu = ttdv.maThongTinDichVu "
					+ "WHERE ttdv.ngayNhap BETWEEN ? AND ? ";
			
			if (thang != 0) {
				sql += " AND MONTH(ngayNhap) = ? ";
            }
			
			if (nam != 0) {
				sql += " AND YEAR(ngayNhap) = ? ";
            }
			
			if (quy != 0) {
				sql += " AND DATENAME(QUARTER, ngayNhap) = ? ";
            }
			
			em.getTransaction().begin();
			ArrayList<DichVu> ds = (ArrayList<DichVu>) em.createNativeQuery(sql, DichVu.class)
                    .setParameter(1, ngayBD).setParameter(2, ngayKT).setParameter(3, thang).setParameter(4, nam)
                    .setParameter(5, quy).getResultList();
			
			em.close();
			return ds;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
}
