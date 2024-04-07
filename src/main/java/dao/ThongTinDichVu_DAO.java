package dao;

import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.ThongTinDichVu;
import jakarta.persistence.EntityManager;
import other.ConvertObjToEntity;

public class ThongTinDichVu_DAO {

	private EntityManager em;

	public ThongTinDichVu_DAO() {
		em = ConnectDB.connect();
	}

	public ArrayList<ThongTinDichVu> layTatCaThongTinDichVu() {
//		ArrayList<ThongTinDichVu> danhSachThongTinDichVu = new ArrayList<ThongTinDichVu>();
//		try {
//			ConnectDB.getInstance();
//			Connection con = ConnectDB.getConnection();
//			String sql = "SELECT * FROM ThongTinDichVu";
//			Statement statement = con.createStatement();
//			ResultSet rs = statement.executeQuery(sql);
//			while (rs.next()) {
//				String maThongTinDichVu = rs.getString("maThongTinDichVu");
//				int soLuong = rs.getInt("soLuong");
//				int soLuongDaSuDung = rs.getInt("soLuongDaSuDung");
//				java.sql.Date ngayNhap = rs.getDate("ngayNhap");
//				java.sql.Date ngayHetHan = rs.getDate("ngayHetHan");
//				String moTa = rs.getString("moTa");
//				String hinhAnh = rs.getString("hinhAnh");
//				ThongTinDichVu thongTinDichVu = new ThongTinDichVu(maThongTinDichVu, soLuong, soLuongDaSuDung,
//						ngayNhap, ngayHetHan, moTa, hinhAnh);
//				danhSachThongTinDichVu.add(thongTinDichVu);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return danhSachThongTinDichVu;

		try {
			String sql = "SELECT * FROM ThongTinDichVu";
			List<Object> listObj = em.createNativeQuery(sql, ThongTinDichVu.class)
					.getResultList();

			ArrayList<ThongTinDichVu> list = ConvertObjToEntity.convertToThongTinDichVuList(listObj);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ThongTinDichVu timThongTinDichVu_TheoMaThongTinDichVu(String maTTDichVu) {
//		ThongTinDichVu thongTinDichVu = null;
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		try {
//			String sql = "SELECT * FROM ThongTinDichVu WHERE maThongTinDichVu = ?";
//			statement = con.prepareStatement(sql);
//			statement.setString(1, maTTDichVu);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				String maThongTinDichVu = rs.getString("maThongTinDichVu");
//				int soLuong = rs.getInt("soLuong");
//				int soLuongDaSuDung = rs.getInt("soLuongDaSuDung");
//				java.sql.Date ngayNhap = rs.getDate("ngayNhap");
//				java.sql.Date ngayHetHan = rs.getDate("ngayHetHan");
//				String moTa = rs.getString("moTa");
//				String hinhAnh = rs.getString("hinhAnh");
//				thongTinDichVu = new ThongTinDichVu(maThongTinDichVu, soLuong, soLuongDaSuDung, ngayNhap,
//						ngayHetHan, moTa, hinhAnh);
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
//		return thongTinDichVu;

		try {
			String sql = "SELECT * FROM ThongTinDichVu WHERE maThongTinDichVu = ?";
			Object obj = em.createNativeQuery(sql, ThongTinDichVu.class)
					.setParameter(1, maTTDichVu).getResultList().stream().findFirst().orElse(null);

			ThongTinDichVu ttdv = (ThongTinDichVu) obj;
			return ttdv;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean taoThongTinDichVu(ThongTinDichVu thongTinDichVu) {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		int n = 0;
//		try {
//			statement = con.prepareStatement("INSERT INTO ThongTinDichVu values(?,?,?,?,?,?,?)");
//			statement.setString(1, thongTinDichVu.getMaThongTinDichVu());
//			statement.setInt(2, thongTinDichVu.getSoLuong());
//			statement.setInt(3, thongTinDichVu.getSoLuongDaSuDung());
//			statement.setDate(4, thongTinDichVu.getNgayNhap());
//			statement.setDate(5, thongTinDichVu.getNgayHetHan());
//			statement.setString(6, thongTinDichVu.getMoTa());
//			statement.setString(7, thongTinDichVu.getHinhAnh());
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
			String sql = "INSERT INTO ThongTinDichVu values(?,?,?,?,?,?,?)";
			em.getTransaction().begin();
			int result = em.createNativeQuery(sql).setParameter(1, thongTinDichVu.getMaThongTinDichVu())
					.setParameter(2, thongTinDichVu.getSoLuong()).setParameter(3, thongTinDichVu.getSoLuongDaSuDung())
					.setParameter(4, thongTinDichVu.getNgayNhap()).setParameter(5, thongTinDichVu.getNgayHetHan())
					.setParameter(6, thongTinDichVu.getMoTa()).setParameter(7, thongTinDichVu.getHinhAnh())
					.executeUpdate();

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

	public int capNhatThongTinDichVu(ThongTinDichVu thongTinDichVu) {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		int n = 0;
//		try {
//			statement = con.prepareStatement(
//					"UPDATE ThongTinDichVu SET  soLuong = ?, soLuongDaSuDung = ?, ngayNhap = ?, ngayHetHan = ?, moTa = ?, hinhAnh = ?"
//							+ " WHERE maThongTinDichVu = ?");
//			statement.setInt(1, thongTinDichVu.getSoLuong());
//			statement.setInt(2, thongTinDichVu.getSoLuongDaSuDung());
//			statement.setDate(3, thongTinDichVu.getNgayNhap());
//			statement.setDate(4, thongTinDichVu.getNgayHetHan());
//			statement.setString(5, thongTinDichVu.getMoTa());
//			statement.setString(6, thongTinDichVu.getHinhAnh());
//			statement.setString(7, thongTinDichVu.getMaThongTinDichVu());
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
			String sql = "UPDATE ThongTinDichVu SET  soLuong = ?, soLuongDaSuDung = ?, ngayNhap = ?, ngayHetHan = ?, moTa = ?, hinhAnh = ?"
					+ " WHERE maThongTinDichVu = ?";
			em.getTransaction().begin();
			
			int result = em.createNativeQuery(sql).setParameter(1, thongTinDichVu.getSoLuong())
					.setParameter(2, thongTinDichVu.getSoLuongDaSuDung()).setParameter(3, thongTinDichVu.getNgayNhap())
					.setParameter(4, thongTinDichVu.getNgayHetHan()).setParameter(5, thongTinDichVu.getMoTa())
					.setParameter(6, thongTinDichVu.getHinhAnh()).setParameter(7, thongTinDichVu.getMaThongTinDichVu())
					.executeUpdate();
			
			if (result == 0) {
                return 0;
            }
			
			em.getTransaction().commit();
			return result;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			return 0;
		} finally {
			em.close();
		}
	}

	public boolean xoaThongTinDichVu(ThongTinDichVu thongTinDichVu) {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		int n = 0;
//		try {
//			statement = con.prepareStatement("DELETE FROM ThongTinDichVu" + " WHERE maThongTinDichVu = ?");
//			statement.setString(1, thongTinDichVu.getMaThongTinDichVu());
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
			String sql = "DELETE FROM ThongTinDichVu WHERE maThongTinDichVu = ?";
			
			em.getTransaction().begin();
			int result = em.createNativeQuery(sql).setParameter(1, thongTinDichVu.getMaThongTinDichVu()).executeUpdate();
			
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
