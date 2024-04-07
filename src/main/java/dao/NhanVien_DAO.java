package dao;

import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.LoaiNhanVien;
import entity.NhanVien;
import jakarta.persistence.EntityManager;
import other.ConvertObjToEntity;

public class NhanVien_DAO {
	private EntityManager em;

	public NhanVien_DAO() {
		em = ConnectDB.connect();
	}

	public ArrayList<NhanVien> layTatCaNhanVien() {
//		ArrayList<NhanVien> danhSachNhanVien = new ArrayList<NhanVien>();
//		try {
//			ConnectDB.getInstance();
//			Connection con = ConnectDB.getConnection();
//			String sql = "SELECT * FROM NhanVien";
//			Statement statement = con.createStatement();
//			ResultSet rs = statement.executeQuery(sql);
//			while (rs.next()) {
//				String maNhanVien = rs.getString("maNhanVien");
//				LoaiNhanVien loaiNhanVien = new LoaiNhanVien(rs.getString("maLoaiNhanVien"));
//				java.sql.Date ngaySinh = rs.getDate("ngaySinh");
//				String hoTen = rs.getString("hoTen");
//				Boolean gioiTinh = rs.getBoolean("gioiTinh");
//				String SoDienThoai = rs.getString("SoDienThoai");
//				String CCCD = rs.getString("CCCD");
//				String diaChi = rs.getString("diaChi");
//				String trangThai = rs.getString("trangThai");
//				String anhThe = rs.getString("anhThe");
//				NhanVien nhanVien = new NhanVien(maNhanVien, loaiNhanVien, hoTen, gioiTinh, ngaySinh, SoDienThoai, CCCD,
//						diaChi, trangThai, anhThe);
//				danhSachNhanVien.add(nhanVien);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return danhSachNhanVien;

		try {
			String query = "SELECT * FROM NhanVien";
			List<Object> listObj = em.createNativeQuery(query, NhanVien.class).getResultList();

			ArrayList<NhanVien> list = ConvertObjToEntity.convertToNhanVienList(listObj);

			return list;
		} catch (Exception e) {
			// TODO: handle exception
			em.close();
			e.printStackTrace();
			return null;
		}
	}

	public NhanVien timNhanVien_TheoMaNhanVien(String maNV) {
//		NhanVien nhanVien = null;
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		try {
//			String sql = "SELECT * FROM NhanVien where maNhanVien = ?";
//			statement = con.prepareStatement(sql);
//			statement.setString(1, maNV);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				String maNhanVien = rs.getString("maNhanVien");
//				LoaiNhanVien loaiNhanVien = new LoaiNhanVien(rs.getString("maLoaiNhanVien"));
//				java.sql.Date ngaySinh = rs.getDate("ngaySinh");
//				String hoTen = rs.getString("hoTen");
//				Boolean gioiTinh = rs.getBoolean("gioiTinh");
//				String SoDienThoai = rs.getString("SoDienThoai");
//				String CCCD = rs.getString("CCCD");
//				String diaChi = rs.getString("diaChi");
//				String trangThai = rs.getString("trangThai");
//				String anhThe = rs.getString("anhThe");
//
//				nhanVien = new NhanVien(maNhanVien, loaiNhanVien, hoTen, gioiTinh, ngaySinh, SoDienThoai, CCCD, diaChi,
//						trangThai, anhThe);
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
//		return nhanVien;

		try {
			String sql = "SELECT * FROM NhanVien where maNhanVien = ?";
			Object obj = em.createNativeQuery(sql, NhanVien.class).setParameter(1, maNV).getResultList().stream()
					.findFirst().orElse(null);

			NhanVien nv = (NhanVien) obj;
			return nv;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public NhanVien timNhanVien_TheoMaLoaiNhanVien(String maLoaiNV) {
//		NhanVien nhanVien = null;
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		try {
//			String sql = "SELECT * FROM NhanVien WHERE maLoaiNhanVien = ?";
//			statement = con.prepareStatement(sql);
//			statement.setString(1, maLoaiNV);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				String maNhanVien = rs.getString("maNhanVien");
//				LoaiNhanVien loaiNhanVien = new LoaiNhanVien(rs.getString("maLoaiNhanVien"));
//				java.sql.Date ngaySinh = rs.getDate("ngaySinh");
//				String hoTen = rs.getString("hoTen");
//				Boolean gioiTinh = rs.getBoolean("gioiTinh");
//				String SoDienThoai = rs.getString("SoDienThoai");
//				String CCCD = rs.getString("CCCD");
//				String diaChi = rs.getString("diaChi");
//				String trangThai = rs.getString("trangThai");
//				String anhThe = rs.getString("anhThe");
//				nhanVien = new NhanVien(maNhanVien, loaiNhanVien, hoTen, gioiTinh, ngaySinh, SoDienThoai, CCCD, diaChi,
//						trangThai, anhThe);
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
//		return nhanVien;
		try {
			String sql = "SELECT * FROM NhanVien WHERE maLoaiNhanVien = ?";
			Object obj = em.createNativeQuery(sql, NhanVien.class).setParameter(1, maLoaiNV).getResultList().stream()
					.findFirst().orElse(null);

			NhanVien nv = (NhanVien) obj;
			return nv;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public NhanVien timNhanVien_TheoSoDienThoai(String soDT) {
//		NhanVien nhanVien = null;
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		try {
//			String sql = "SELECT * FROM NhanVien WHERE SoDienThoai = ?";
//			statement = con.prepareStatement(sql);
//			statement.setString(1, soDT);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				String maNhanVien = rs.getString("maNhanVien");
//				LoaiNhanVien loaiNhanVien = new LoaiNhanVien(rs.getString("maLoaiNhanVien"));
//				java.sql.Date ngaySinh = rs.getDate("ngaySinh");
//				String hoTen = rs.getString("hoTen");
//				Boolean gioiTinh = rs.getBoolean("gioiTinh");
//				String SoDienThoai = rs.getString("SoDienThoai");
//				String CCCD = rs.getString("CCCD");
//				String diaChi = rs.getString("diaChi");
//				String trangThai = rs.getString("trangThai");
//				String anhThe = rs.getString("anhThe");
//				nhanVien = new NhanVien(maNhanVien, loaiNhanVien, hoTen, gioiTinh, ngaySinh, SoDienThoai, CCCD, diaChi,
//						trangThai, anhThe);
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
//		return nhanVien;

		try {
			String sql = "SELECT * FROM NhanVien WHERE SoDienThoai = ?";
			Object obj = em.createNativeQuery(sql, NhanVien.class).setParameter(1, soDT).getResultList().stream()
					.findFirst().orElse(null);

			NhanVien nv = (NhanVien) obj;
			return nv;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<NhanVien> timNhanVien_TheoHoTen(String ten) {
//		NhanVien nhanVien = null;
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		ArrayList<NhanVien> dsNV = new ArrayList<>();
//		try {
//			String sql = "SELECT * FROM NhanVien WHERE hoTen like N'%"+ ten.trim() +"%'";
//			statement = con.prepareStatement(sql);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				String maNhanVien = rs.getString("maNhanVien");
//				LoaiNhanVien loaiNhanVien = new LoaiNhanVien(rs.getString("maLoaiNhanVien"));
//				java.sql.Date ngaySinh = rs.getDate("ngaySinh");
//				String hoTen = rs.getString("hoTen");
//				Boolean gioiTinh = rs.getBoolean("gioiTinh");
//				String SoDienThoai = rs.getString("SoDienThoai");
//				String CCCD = rs.getString("CCCD");
//				String diaChi = rs.getString("diaChi");
//				String trangThai = rs.getString("trangThai");
//				String anhThe = rs.getString("anhThe");
//				nhanVien = new NhanVien(maNhanVien, loaiNhanVien, hoTen, gioiTinh, ngaySinh, SoDienThoai, CCCD, diaChi,
//						trangThai, anhThe);
//				dsNV.add(nhanVien);
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
//		return dsNV;

		try {
			String sql = "SELECT * FROM NhanVien WHERE hoTen like N'%?%'";
			List<Object> listObj = em.createNativeQuery(sql, NhanVien.class).setParameter(1, ten).getResultList();

			ArrayList<NhanVien> list = ConvertObjToEntity.convertToNhanVienList(listObj);

			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean taoNhanVien(NhanVien nhanVien) {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		int n = 0;
//		try {
//			statement = con.prepareStatement("INSERT INTO NhanVien values(?,?,?,?,?,?,?,?,?,?)");
//			statement.setString(1, nhanVien.getMaNhanVien());
//			statement.setString(2, nhanVien.getLoaiNhanVien().getMaLoaiNhanVien());
//			statement.setString(3, nhanVien.getHoTen());
//			statement.setBoolean(4, nhanVien.isGioiTinh());
//			statement.setDate(5, (java.sql.Date) nhanVien.getNgaySinh());
//			statement.setString(6, nhanVien.getSoDienThoai());
//			statement.setString(7, nhanVien.getCCCD());
//			statement.setString(8, nhanVien.getDiaChi());
//			statement.setString(9, nhanVien.getTrangThai());
//			statement.setString(10, nhanVien.getAnhThe());
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
			String sql = "INSERT INTO NhanVien values(?,?,?,?,?,?,?,?,?,?)";

			em.getTransaction().begin();

			int result = em.createNativeQuery(sql).setParameter(1, nhanVien.getMaNhanVien())
					.setParameter(2, nhanVien.getLoaiNhanVien().getMaLoaiNhanVien())
					.setParameter(3, nhanVien.getHoTen()).setParameter(4, nhanVien.isGioiTinh())
					.setParameter(5, nhanVien.getNgaySinh()).setParameter(6, nhanVien.getSoDienThoai())
					.setParameter(7, nhanVien.getCCCD()).setParameter(8, nhanVien.getDiaChi())
					.setParameter(9, nhanVien.getTrangThai()).setParameter(10, nhanVien.getAnhThe()).executeUpdate();

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

	public boolean capNhatNhanVien(NhanVien nhanVien) {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		int n = 0;
//		try {
//			statement = con.prepareStatement(
//					"UPDATE NhanVien SET maLoaiNhanVien = ?, hoTen =?, gioiTinh=?, ngaySinh=?, soDienThoai=?, CCCD=?, diaChi=?, trangThai=?, anhThe=?"
//							+ " WHERE maNhanVien=?");
//			statement.setString(1, nhanVien.getLoaiNhanVien().getMaLoaiNhanVien());
//			statement.setString(2, nhanVien.getHoTen());
//			statement.setBoolean(3, nhanVien.isGioiTinh());
//			statement.setDate(4, (java.sql.Date) nhanVien.getNgaySinh());
//			statement.setString(5, nhanVien.getSoDienThoai());
//			statement.setString(6, nhanVien.getCCCD());
//			statement.setString(7, nhanVien.getDiaChi());
//			statement.setString(8, nhanVien.getTrangThai());
//			statement.setString(9, nhanVien.getAnhThe());
//			statement.setString(10, nhanVien.getMaNhanVien());
//			n = statement.executeUpdate();
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
//		return n > 0;

		try {
			String sql = "UPDATE NhanVien SET maLoaiNhanVien = ?, hoTen =?, gioiTinh=?, ngaySinh=?, soDienThoai=?, CCCD=?, diaChi=?, trangThai=?, anhThe=?"
					+ " WHERE maNhanVien=?";

			em.getTransaction().begin();

			int result = em.createNativeQuery(sql).setParameter(1, nhanVien.getLoaiNhanVien().getMaLoaiNhanVien())
					.setParameter(2, nhanVien.getHoTen()).setParameter(3, nhanVien.isGioiTinh())
					.setParameter(4, nhanVien.getNgaySinh()).setParameter(5, nhanVien.getSoDienThoai())
					.setParameter(6, nhanVien.getCCCD()).setParameter(7, nhanVien.getDiaChi())
					.setParameter(8, nhanVien.getTrangThai()).setParameter(9, nhanVien.getAnhThe())
					.setParameter(10, nhanVien.getMaNhanVien()).executeUpdate();

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

	public boolean xoaNhanVien(NhanVien nhanVien) {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		int n = 0;
//		try {
//			statement = con.prepareStatement("DELETE FROM NhanVien WHERE maNhanVien=?");
//			statement.setString(1, nhanVien.getMaNhanVien());
//			n = statement.executeUpdate();
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//
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
			String sql = "DELETE FROM NhanVien WHERE maNhanVien=?";

			em.getTransaction().begin();

			int result = em.createNativeQuery(sql).setParameter(1, nhanVien.getMaNhanVien()).executeUpdate();

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