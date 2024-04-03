package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import connectDB.ConnectDB;

import java.sql.Date;

import entity.KhachHang;
import entity.LoaiPhong;
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.Phong;
import entity.TrangThaiPhong;
import jakarta.persistence.EntityManager;

public class PhieuDatPhong_DAO {

//	private PhieuDatPhong phieuDatPhong;

	private EntityManager em;
	public PhieuDatPhong_DAO() {
		em = new ConnectDB().getEntityManager();
	}

	public ArrayList<PhieuDatPhong> locPhieuDatPhong(String maPhieuDatLoc, String tenKhachHang, String maPhong,
			String soDienThoai, String thoiGianNhanPhongLoc) {
//		ArrayList<PhieuDatPhong> danhSachPhieuDatPhong = new ArrayList<PhieuDatPhong>();
//		try {
//			ConnectDB.getInstance();
//			Connection con = ConnectDB.getConnection();
//			String sql = "SELECT * \r\n" + "FROM PhieuDatPhong pd\r\n"
//					+ "JOIN KhachHang kh ON kh.maKhachHang = pd.maKhachHang\r\n" + "WHERE maPhieuDat LIKE '%"
//					+ maPhieuDatLoc + "%' AND maPhong LIKE '%" + maPhong + "%' AND CAST(thoiGianNhanPhong AS DATE) = '"
//					+ thoiGianNhanPhongLoc + "' AND kh.hoTen LIKE N'%" + tenKhachHang + "%' \r\n"
//					+ "AND kh.soDienThoai LIKE '%" + soDienThoai + "%'";
//			Statement statement = con.createStatement();
//			ResultSet rs = statement.executeQuery(sql);
//		
//			while (rs.next()) {
//				String maPhieuDat = rs.getString("maPhieuDat");
//				Phong phong = new Phong(rs.getString("maPhong"));
//				NhanVien nhanVien = new NhanVien(rs.getString("maNhanVien"));
//				KhachHang khachHang = new KhachHang(rs.getString("maKhachHang"));
//				java.sql.Timestamp thoiGianDatPhong = rs.getTimestamp("thoiGianDatPhong");
//				java.sql.Timestamp thoiGianNhanPhong = rs.getTimestamp("thoiGianNhanPhong");
//				Double tienCoc = rs.getDouble("tienCoc");
//				String trangThai = rs.getString("trangThai");
//				String moTa = rs.getString("moTa");
//				PhieuDatPhong phieuDatPhong = new PhieuDatPhong(maPhieuDat, phong, nhanVien, khachHang,
//						thoiGianDatPhong, thoiGianNhanPhong, tienCoc, trangThai, moTa);
//				danhSachPhieuDatPhong.add(phieuDatPhong);
//
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return danhSachPhieuDatPhong;
		
		try {
			String sql = "SELECT * \r\n" + "FROM PhieuDatPhong pd\r\n"
					+ "JOIN KhachHang kh ON kh.maKhachHang = pd.maKhachHang\r\n" + "WHERE maPhieuDat LIKE '%"
					+ "?" + "%' AND maPhong LIKE '%" + "?" + "%' AND CAST(thoiGianNhanPhong AS DATE) = '"
					+ "?" + "' AND kh.hoTen LIKE N'%" + "?" + "%' \r\n"
					+ "AND kh.soDienThoai LIKE '%" + "?" + "%'";
			 em.getTransaction().begin();
			 ArrayList<PhieuDatPhong> list = (ArrayList<PhieuDatPhong>) em.createNativeQuery(sql, PhieuDatPhong.class).setParameter(1,maPhieuDatLoc)
						.setParameter(2, maPhong).setParameter(3, thoiGianNhanPhongLoc)
						.setParameter(4, tenKhachHang).setParameter(5,soDienThoai).getSingleResult();
	         em.close();
	         return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<PhieuDatPhong> layTatCaPhieuDatPhong() {
//		ArrayList<PhieuDatPhong> danhSachPhieuDatPhong = new ArrayList<PhieuDatPhong>();
//		try {
//			ConnectDB.getInstance();
//			Connection con = ConnectDB.getConnection();
//			String sql = "SELECT * FROM PhieuDatPhong ORDER BY thoiGianNhanPhong";
//			Statement statement = con.createStatement();
//			ResultSet rs = statement.executeQuery(sql);
//			while (rs.next()) {
//				String maPhieuDat = rs.getString("maPhieuDat");
//				Phong phong = new Phong(rs.getString("maPhong"));
//				NhanVien nhanVien = new NhanVien(rs.getString("maNhanVien"));
//				KhachHang khachHang = new KhachHang(rs.getString("maKhachHang"));
//				java.sql.Timestamp thoiGianDatPhong = rs.getTimestamp("thoiGianDatPhong");
//				java.sql.Timestamp thoiGianNhanPhong = rs.getTimestamp("thoiGianNhanPhong");
//				Double tienCoc = rs.getDouble("tienCoc");
//				String trangThai = rs.getString("trangThai");
//				String moTa = rs.getString("moTa");
//				PhieuDatPhong phieuDatPhong = new PhieuDatPhong(maPhieuDat, phong, nhanVien, khachHang,
//						thoiGianDatPhong, thoiGianNhanPhong, tienCoc, trangThai, moTa);
//				danhSachPhieuDatPhong.add(phieuDatPhong);
//
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return danhSachPhieuDatPhong;
		try {
			String sql = "SELECT * FROM PhieuDatPhong ORDER BY thoiGianNhanPhong";
			em.getTransaction().begin();
			ArrayList<PhieuDatPhong> list = (ArrayList<PhieuDatPhong>) em.createNativeQuery(sql, PhieuDatPhong.class).getResultList();
			em.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	
	}

	public PhieuDatPhong layPhieuDatPhong_TheoMaPhieuDat(String maPD) {
//		PhieuDatPhong phieuDatPhong = null;
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		try {
//			String sql = "SELECT * FROM PhieuDatPhong WHERE maPhieuDat = ?";
//			statement = con.prepareStatement(sql);
//			statement.setString(1, maPD);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				String maPhieuDat = rs.getString("maPhieuDat");
//				Phong phong = new Phong(rs.getString("maPhong"));
//				NhanVien nhanVien = new NhanVien(rs.getString("maNhanVien"));
//				KhachHang khachHang = new KhachHang(rs.getString("maKhachHang"));
//				java.sql.Timestamp thoiGianDatPhong = rs.getTimestamp("thoiGianDatPhong");
//				java.sql.Timestamp thoiGianNhanPhong = rs.getTimestamp("thoiGianNhanPhong");
//				Double tienCoc = rs.getDouble("tienCoc");
//				String trangThai = rs.getString("trangThai");
//				String moTa = rs.getString("moTa");
//				phieuDatPhong = new PhieuDatPhong(maPhieuDat, phong, nhanVien, khachHang, thoiGianDatPhong,
//						thoiGianNhanPhong, tienCoc, trangThai, moTa);
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
//		return phieuDatPhong;
		
		try {
			String sql = "SELECT * FROM PhieuDatPhong WHERE maPhieuDat = ?";
			em.getTransaction().begin();
			PhieuDatPhong list = (PhieuDatPhong) em.createNativeQuery(sql, PhieuDatPhong.class).setParameter(1, maPD).getSingleResult();
			em.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public PhieuDatPhong layPhieuDatPhong_TheoMaPhong(String maPh) {
//		PhieuDatPhong phieuDatPhong = null;
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		try {
//			String sql = "SELECT * FROM PhieuDatPhong WHERE maPhong = ?";
//			statement = con.prepareStatement(sql);
//			statement.setString(1, maPh);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				String maPhieuDat = rs.getString("maPhieuDat");
//				Phong phong = new Phong(rs.getString("maPhong"));
//				NhanVien nhanVien = new NhanVien(rs.getString("maNhanVien"));
//				KhachHang khachHang = new KhachHang(rs.getString("maKhachHang"));
//				java.sql.Timestamp thoiGianDatPhong = rs.getTimestamp("thoiGianDatPhong");
//				java.sql.Timestamp thoiGianNhanPhong = rs.getTimestamp("thoiGianNhanPhong");
//				Double tienCoc = rs.getDouble("tienCoc");
//				String trangThai = rs.getString("trangThai");
//				String moTa = rs.getString("moTa");
//				phieuDatPhong = new PhieuDatPhong(maPhieuDat, phong, nhanVien, khachHang, thoiGianDatPhong,
//						thoiGianNhanPhong, tienCoc, trangThai, moTa);
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
//		return phieuDatPhong;
		
		try {
			String sql = "SELECT * FROM PhieuDatPhong WHERE maPhong = ?";
			em.getTransaction().begin();
			PhieuDatPhong list = (PhieuDatPhong) em.createNativeQuery(sql, PhieuDatPhong.class).setParameter(1, maPh).getSingleResult();
			em.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public PhieuDatPhong layPhieuDatPhong_TheoMaNhanVien(String maNV) {
//		PhieuDatPhong phieuDatPhong = null;
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		try {
//			String sql = "SELECT * FROM PhieuDatPhong WHERE maNhanVien = ?";
//			statement = con.prepareStatement(sql);
//			statement.setString(1, maNV);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				String maPhieuDat = rs.getString("maPhieuDat");
//				Phong phong = new Phong(rs.getString("maPhong"));
//				NhanVien nhanVien = new NhanVien(rs.getString("maNhanVien"));
//				KhachHang khachHang = new KhachHang(rs.getString("maKhachHang"));
//				java.sql.Timestamp thoiGianDatPhong = rs.getTimestamp("thoiGianDatPhong");
//				java.sql.Timestamp thoiGianNhanPhong = rs.getTimestamp("thoiGianNhanPhong");
//				Double tienCoc = rs.getDouble("tienCoc");
//				String trangThai = rs.getString("trangThai");
//				String moTa = rs.getString("moTa");
//				phieuDatPhong = new PhieuDatPhong(maPhieuDat, phong, nhanVien, khachHang, thoiGianDatPhong,
//						thoiGianNhanPhong, tienCoc, trangThai, moTa);
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
//		return phieuDatPhong;
		
		try {
			String sql = "SELECT * FROM PhieuDatPhong WHERE maNhanVien = ?";
			em.getTransaction().begin();
			PhieuDatPhong pdp = (PhieuDatPhong) em.createNativeQuery(sql, PhieuDatPhong.class).setParameter(1, maNV).getSingleResult();
			em.close();
			return pdp;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public PhieuDatPhong layPhieuDatPhong_TheoMaKhachHang(String maKH) {
//		PhieuDatPhong phieuDatPhong = null;
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		try {
//			String sql = "SELECT * FROM PhieuDatPhong WHERE maKhachHang = ?";
//			statement = con.prepareStatement(sql);
//			statement.setString(1, maKH);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				String maPhieuDat = rs.getString("maPhieuDat");
//				Phong phong = new Phong(rs.getString("maPhong"));
//				NhanVien nhanVien = new NhanVien(rs.getString("maNhanVien"));
//				KhachHang khachHang = new KhachHang(rs.getString("maKhachHang"));
//				java.sql.Timestamp thoiGianDatPhong = rs.getTimestamp("thoiGianDatPhong");
//				java.sql.Timestamp thoiGianNhanPhong = rs.getTimestamp("thoiGianNhanPhong");
//				Double tienCoc = rs.getDouble("tienCoc");
//				String trangThai = rs.getString("trangThai");
//				String moTa = rs.getString("moTa");
//				phieuDatPhong = new PhieuDatPhong(maPhieuDat, phong, nhanVien, khachHang, thoiGianDatPhong,
//						thoiGianNhanPhong, tienCoc, trangThai, moTa);
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
//		return phieuDatPhong;
		
		try {
			String sql = "SELECT * FROM PhieuDatPhong WHERE maKhachHang = ?";
			em.getTransaction().begin();
			PhieuDatPhong pdp = (PhieuDatPhong) em.createNativeQuery(sql, PhieuDatPhong.class).setParameter(1, maKH).getSingleResult();
			em.close();
			return pdp;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<PhieuDatPhong> layPhieuDatPhong_TheoTrangThaiPhieu(String ttp) {
//		ArrayList<PhieuDatPhong> danhSachPhieuDatPhong = new ArrayList<PhieuDatPhong>();
//		PhieuDatPhong phieuDatPhong = null;
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		try {
//			String sql = "SELECT * FROM PhieuDatPhong WHERE trangThai = ?";
//			statement = con.prepareStatement(sql);
//			statement.setString(1, ttp);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				String maPhieuDat = rs.getString("maPhieuDat");
//				Phong phong = new Phong(rs.getString("maPhong"));
//				NhanVien nhanVien = new NhanVien(rs.getString("maNhanVien"));
//				KhachHang khachHang = new KhachHang(rs.getString("maKhachHang"));
//				java.sql.Timestamp thoiGianDatPhong = rs.getTimestamp("thoiGianDatPhong");
//				java.sql.Timestamp thoiGianNhanPhong = rs.getTimestamp("thoiGianNhanPhong");
//				Double tienCoc = rs.getDouble("tienCoc");
//				String trangThai = rs.getString("trangThai");
//				String moTa = rs.getString("moTa");
//				phieuDatPhong = new PhieuDatPhong(maPhieuDat, phong, nhanVien, khachHang, thoiGianDatPhong,
//						thoiGianNhanPhong, tienCoc, trangThai, moTa);
//				danhSachPhieuDatPhong.add(phieuDatPhong);
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
//		return danhSachPhieuDatPhong;
		
		try {
			String sql = "SELECT * FROM PhieuDatPhong WHERE trangThai = ?";
			em.getTransaction().begin();
			ArrayList<PhieuDatPhong> list = (ArrayList<PhieuDatPhong>) em.createNativeQuery(sql, PhieuDatPhong.class).setParameter(1, ttp).getResultList();
			em.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<PhieuDatPhong> layPhieuDatPhong_TheoTenKhachHangvsSDT(String tenKH) {
//		ArrayList<PhieuDatPhong> danhSachPhieuDatPhong = new ArrayList<PhieuDatPhong>();
//		PhieuDatPhong phieuDatPhong = null;
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		try {
//			String sql = "SELECT PhieuDatPhong.* FROM PhieuDatPhong "
//					+ "JOIN KhachHang ON PhieuDatPhong.maKhachHang = KhachHang.maKhachHang "
//					+ "WHERE KhachHang.hoTen LIKE ? OR KhachHang.soDienThoai LIKE ?";
//			;
//			statement = con.prepareStatement(sql);
//			statement.setString(1, "%" + tenKH + "%");
//			statement.setString(2, "%" + tenKH + "%");
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				String maPhieuDat = rs.getString("maPhieuDat");
//				Phong phong = new Phong(rs.getString("maPhong"));
//				NhanVien nhanVien = new NhanVien(rs.getString("maNhanVien"));
//				KhachHang khachHang = new KhachHang(rs.getString("maKhachHang"));
//				java.sql.Timestamp thoiGianDatPhong = rs.getTimestamp("thoiGianDatPhong");
//				java.sql.Timestamp thoiGianNhanPhong = rs.getTimestamp("thoiGianNhanPhong");
//				Double tienCoc = rs.getDouble("tienCoc");
//				String trangThai = rs.getString("trangThai");
//				String moTa = rs.getString("moTa");
//				phieuDatPhong = new PhieuDatPhong(maPhieuDat, phong, nhanVien, khachHang, thoiGianDatPhong,
//						thoiGianNhanPhong, tienCoc, trangThai, moTa);
//				danhSachPhieuDatPhong.add(phieuDatPhong);
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
//		return danhSachPhieuDatPhong;
		
		try {
			String sql = "SELECT PhieuDatPhong.* FROM PhieuDatPhong "
					+ "JOIN KhachHang ON PhieuDatPhong.maKhachHang = KhachHang.maKhachHang "
					+ "WHERE KhachHang.hoTen LIKE ? OR KhachHang.soDienThoai LIKE ?";
			em.getTransaction().begin();
			ArrayList<PhieuDatPhong> list = (ArrayList<PhieuDatPhong>) em.createNativeQuery(sql, PhieuDatPhong.class)
					.setParameter(1, "%" + tenKH + "%").setParameter(2, "%" + tenKH + "%").getResultList();
			em.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<PhieuDatPhong> layPhieuDatPhong_TheoTenPhong(String tenP) {
//		ArrayList<PhieuDatPhong> danhSachPhieuDatPhong = new ArrayList<PhieuDatPhong>();
//		PhieuDatPhong phieuDatPhong = null;
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		try {
//			String sql = "SELECT PhieuDatPhong.* FROM PhieuDatPhong "
//					+ "JOIN Phong ON PhieuDatPhong.maPhong = Phong.maPhong " + "WHERE Phong.tenPhong LIKE ?";
//			;
//			statement = con.prepareStatement(sql);
//			statement.setString(1, "%" + tenP + "%");
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				String maPhieuDat = rs.getString("maPhieuDat");
//				Phong phong = new Phong(rs.getString("maPhong"));
//				NhanVien nhanVien = new NhanVien(rs.getString("maNhanVien"));
//				KhachHang khachHang = new KhachHang(rs.getString("maKhachHang"));
//				java.sql.Timestamp thoiGianDatPhong = rs.getTimestamp("thoiGianDatPhong");
//				java.sql.Timestamp thoiGianNhanPhong = rs.getTimestamp("thoiGianNhanPhong");
//				Double tienCoc = rs.getDouble("tienCoc");
//				String trangThai = rs.getString("trangThai");
//				String moTa = rs.getString("moTa");
//				phieuDatPhong = new PhieuDatPhong(maPhieuDat, phong, nhanVien, khachHang, thoiGianDatPhong,
//						thoiGianNhanPhong, tienCoc, trangThai, moTa);
//				danhSachPhieuDatPhong.add(phieuDatPhong);
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
//		return danhSachPhieuDatPhong;
		
		try {
			String sql = "SELECT PhieuDatPhong.* FROM PhieuDatPhong "
					+ "JOIN Phong ON PhieuDatPhong.maPhong = Phong.maPhong " + "WHERE Phong.tenPhong LIKE ?";
			em.getTransaction().begin();
			ArrayList<PhieuDatPhong> list = (ArrayList<PhieuDatPhong>) em.createNativeQuery(sql, PhieuDatPhong.class)
					.setParameter(1, "%" + tenP + "%").getResultList();
			em.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Tìm những phiếu đặt phòng thỏa thông tin truyền vào dưới đây
	 *
	 * @param tenPhong:     tên phòng
	 * @param tenKhachHang: tên khách hàng
	 * @param trangThai:    trạng thái
	 * @param ngayDat:      ngày đặt
	 * @return danh sách phiếu đặt phòng
	 * 
	 * 
	 */

	public ArrayList<Phong> danhSachPhongDat_theoPhieuDat(Timestamp ngayDat, String soGIoDuKien, String maloaiPhong) {
//		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		String sql = "SELECT \r\n" + "Phong.maPhong,Phong.tenPhong,Phong.maTrangThai,Phong.maLoaiPhong,\r\n"
//				+ "ABS(DATEDIFF(SECOND, PhieuDatPhong.thoiGianNhanPhong, cast('" + ngayDat
//				+ "' as dateTime))) AS KhoangCachThoiGian\r\n" + "FROM PhieuDatPhong\r\n"
//				+ "INNER JOIN Phong ON Phong.maPhong = PhieuDatPhong.maPhong\r\n" + "WHERE\r\n"
//				+ "trangThai = N'Chờ nhận phòng'\r\n" + "AND\r\n" + "maLoaiPhong LIKE '%" + maloaiPhong + "%'"
//				+ "AND ABS( DATEDIFF(SECOND, PhieuDatPhong.thoiGianNhanPhong, cast('" + ngayDat
//				+ "' as dateTime))) <= 3600*" + soGIoDuKien + "\r\n"
//				+ "AND CONVERT(date, thoiGianNhanPhong) <= CONVERT(date, '" + ngayDat + "')";
//		try {
//
//			statement = con.prepareStatement(sql);
//			ResultSet rs = statement.executeQuery();
//
//			while (rs.next()) {
//				String maP = rs.getString("maPhong");
//				String tenP = rs.getString("tenPhong");
//				String loaiP = rs.getString("maloaiPhong");
//				String maTTP = rs.getString("maTrangThai");
//				LoaiPhong_DAO DAO_LP = new LoaiPhong_DAO();
//				TrangThaiPhong_DAO DAO_TTP = new TrangThaiPhong_DAO();
//				LoaiPhong lp = DAO_LP.layLoaiPhong_TheoMaLoaiPhong(loaiP);
//				TrangThaiPhong ttp = DAO_TTP.timTrangThaiPhong_TheoMaTrangThai(maTTP);
//				Phong ph = new Phong(maP, tenP, lp, ttp, null, "", "", "");
//				dsPhong.add(ph);
//			}
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return dsPhong;
		
		try {
			String sql = "SELECT \r\n" + "Phong.maPhong,Phong.tenPhong,Phong.maTrangThai,Phong.maLoaiPhong,\r\n"
					+ "ABS(DATEDIFF(SECOND, PhieuDatPhong.thoiGianNhanPhong, cast('" + "?"
					+ "' as dateTime))) AS KhoangCachThoiGian\r\n" + "FROM PhieuDatPhong\r\n"
					+ "INNER JOIN Phong ON Phong.maPhong = PhieuDatPhong.maPhong\r\n" + "WHERE\r\n"
					+ "trangThai = N'Chờ nhận phòng'\r\n" + "AND\r\n" + "maLoaiPhong LIKE '%" + "?" + "%'"
					+ "AND ABS( DATEDIFF(SECOND, PhieuDatPhong.thoiGianNhanPhong, cast('" + "?"
					+ "' as dateTime))) <= 3600*" + "?" + "\r\n"
					+ "AND CONVERT(date, thoiGianNhanPhong) <= CONVERT(date, '" + "?" + "')";
			em.getTransaction().begin();
			ArrayList<Phong> list = (ArrayList<Phong>) em.createNativeQuery(sql, Phong.class).setParameter(1, ngayDat)
					.setParameter(2, maloaiPhong).setParameter(3, ngayDat).setParameter(4, soGIoDuKien)
					.setParameter(5, ngayDat).getResultList();
			em.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<PhieuDatPhong> timDSPhieuDatPhongByAllProperty(String maPhong, String maKhachHang,
			String trangThai, String ngayDat) {
//		ArrayList<PhieuDatPhong> danhSachPhieuDatPhong = new ArrayList<PhieuDatPhong>();
//		PhieuDatPhong phieuDatPhong = null;
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		String sql;
//		if (ngayDat == null) {
//			sql = "SELECT PhieuDatPhong.* FROM KhachHang JOIN PhieuDatPhong ON KhachHang.maKhachHang = PhieuDatPhong.maKhachHang JOIN Phong ON PhieuDatPhong.maPhong = Phong.maPhong \n"
//					+ "where Phong.maPhong like '%" + maPhong + "%' and KhachHang.maKhachHang like N'%" + maKhachHang
//					+ "%' and PhieuDatPhong.trangThai like '%" + trangThai + "%'" + "order by PhieuDatPhong.maPhieuDat";
//		} else {
//			sql = "SELECT PhieuDatPhong.* FROM KhachHang JOIN PhieuDatPhong ON KhachHang.maKhachHang = PhieuDatPhong.maKhachHang JOIN Phong ON PhieuDatPhong.maPhong = Phong.maPhong \n"
//					+ "where Phong.maPhong like '%" + maPhong + "%' and KhachHang.maKhachHang like N'%" + maKhachHang
//					+ "%' and PhieuDatPhong.trangThai like '%" + trangThai
//					+ "%' and CONVERT(dateTime, thoiGianDatPhong) = CONVERT(dateTime, '" + ngayDat + "') "
//					+ "order by PhieuDatPhong.maPhieuDat";
//		}
//		try {
//			statement = con.prepareStatement(sql);
//			ResultSet rs = statement.executeQuery();
//
//			while (rs.next()) {
//				String maPhieuDat = rs.getString("maPhieuDat");
//				Phong phong = new Phong(rs.getString("maPhong"));
//				NhanVien nhanVien = new NhanVien(rs.getString("maNhanVien"));
//				KhachHang khachHang = new KhachHang(rs.getString("maKhachHang"));
//				java.sql.Timestamp thoiGianDatPhong = rs.getTimestamp("thoiGianDatPhong");
//				java.sql.Timestamp thoiGianNhanPhong = rs.getTimestamp("thoiGianNhanPhong");
//				Double tienCoc = rs.getDouble("tienCoc");
//				String trangThai1 = rs.getString("trangThai");
//				String moTa = rs.getString("moTa");
//				phieuDatPhong = new PhieuDatPhong(maPhieuDat, phong, nhanVien, khachHang, thoiGianDatPhong,
//						thoiGianNhanPhong, tienCoc, trangThai1, moTa);
//				danhSachPhieuDatPhong.add(phieuDatPhong);
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
//		return danhSachPhieuDatPhong;
		
		try {
			String sql;
			ArrayList<PhieuDatPhong> list;
			if (ngayDat == null) {
				sql = "SELECT PhieuDatPhong.* FROM KhachHang JOIN PhieuDatPhong ON KhachHang.maKhachHang = PhieuDatPhong.maKhachHang JOIN Phong ON PhieuDatPhong.maPhong = Phong.maPhong \n"
						+ "where Phong.maPhong like '%" + "?" + "%' and KhachHang.maKhachHang like N'%" + "?"
						+ "%' and PhieuDatPhong.trangThai like '%" + "?" + "%'" + "order by PhieuDatPhong.maPhieuDat";
				list = (ArrayList<PhieuDatPhong>) em.createNativeQuery(sql, PhieuDatPhong.class).setParameter(1, maPhong).setParameter(2, maKhachHang).setParameter(3, trangThai).getResultList();
			} else {
				sql = "SELECT PhieuDatPhong.* FROM KhachHang JOIN PhieuDatPhong ON KhachHang.maKhachHang = PhieuDatPhong.maKhachHang JOIN Phong ON PhieuDatPhong.maPhong = Phong.maPhong \n"
						+ "where Phong.maPhong like '%" + "?" + "%' and KhachHang.maKhachHang like N'%" + "?"
						+ "%' and PhieuDatPhong.trangThai like '%" + "?"
						+ "%' and CONVERT(dateTime, thoiGianDatPhong) = CONVERT(dateTime, '" + "?" + "') "
						+ "order by PhieuDatPhong.maPhieuDat";
				list = (ArrayList<PhieuDatPhong>) em.createNativeQuery(sql, PhieuDatPhong.class).setParameter(1, maPhong).setParameter(2, maKhachHang).setParameter(3, trangThai).setParameter(4, ngayDat).getResultList();
			}
			em.getTransaction().begin();
			em.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public ArrayList<PhieuDatPhong> layPhieuDatPhong_TheoNgayNhan(java.util.Date ngayNhan) {
//		ArrayList<PhieuDatPhong> danhSachPhieuDatPhong = new ArrayList<PhieuDatPhong>();
//		PhieuDatPhong phieuDatPhong = null;
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		try {
//			String sql = "SELECT PhieuDatPhong.* FROM PhieuDatPhong WHERE CONVERT(date, thoiGianNhanPhong) = ?";
//
//			statement = con.prepareStatement(sql);
//			statement.setDate(1, (Date) ngayNhan);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				String maPhieuDat = rs.getString("maPhieuDat");
//				Phong phong = new Phong(rs.getString("maPhong"));
//				NhanVien nhanVien = new NhanVien(rs.getString("maNhanVien"));
//				KhachHang khachHang = new KhachHang(rs.getString("maKhachHang"));
//				java.sql.Timestamp thoiGianDatPhong = rs.getTimestamp("thoiGianDatPhong");
//				java.sql.Timestamp thoiGianNhanPhong = rs.getTimestamp("thoiGianNhanPhong");
//				Double tienCoc = rs.getDouble("tienCoc");
//				String trangThai = rs.getString("trangThai");
//				String moTa = rs.getString("moTa");
//				phieuDatPhong = new PhieuDatPhong(maPhieuDat, phong, nhanVien, khachHang, thoiGianDatPhong,
//						thoiGianNhanPhong, tienCoc, trangThai, moTa);
//				danhSachPhieuDatPhong.add(phieuDatPhong);
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
//		return danhSachPhieuDatPhong;
		
		try {
			String sql = "SELECT PhieuDatPhong.* FROM PhieuDatPhong WHERE CONVERT(date, thoiGianNhanPhong) = ?";
			em.getTransaction().begin();
			ArrayList<PhieuDatPhong> list = (ArrayList<PhieuDatPhong>) em.createNativeQuery(sql, PhieuDatPhong.class).setParameter(1, ngayNhan).getResultList();
			em.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<PhieuDatPhong> layPhieuDatPhongTuongDoi_TheoMaPhieuDatPhong(String ma) {
//		ArrayList<PhieuDatPhong> danhSachPhieuDatPhong = new ArrayList<PhieuDatPhong>();
//		PhieuDatPhong phieuDatPhong = null;
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		try {
//			String sql = "SELECT PhieuDatPhong.* FROM PhieuDatPhong WHERE maPhieuDat LIKE ?";
//			statement = con.prepareStatement(sql);
//			statement.setString(1, "%" + ma + "%");
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				String maPhieuDat = rs.getString("maPhieuDat");
//				Phong phong = new Phong(rs.getString("maPhong"));
//				NhanVien nhanVien = new NhanVien(rs.getString("maNhanVien"));
//				KhachHang khachHang = new KhachHang(rs.getString("maKhachHang"));
//				java.sql.Timestamp thoiGianDatPhong = rs.getTimestamp("thoiGianDatPhong");
//				java.sql.Timestamp thoiGianNhanPhong = rs.getTimestamp("thoiGianNhanPhong");
//				Double tienCoc = rs.getDouble("tienCoc");
//				String trangThai = rs.getString("trangThai");
//				String moTa = rs.getString("moTa");
//				phieuDatPhong = new PhieuDatPhong(maPhieuDat, phong, nhanVien, khachHang, thoiGianDatPhong,
//						thoiGianNhanPhong, tienCoc, trangThai, moTa);
//				danhSachPhieuDatPhong.add(phieuDatPhong);
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
//		return danhSachPhieuDatPhong;
		
		try {
			String sql = "SELECT PhieuDatPhong.* FROM PhieuDatPhong WHERE maPhieuDat LIKE ?";
			em.getTransaction().begin();
			ArrayList<PhieuDatPhong> list = (ArrayList<PhieuDatPhong>) em.createNativeQuery(sql, PhieuDatPhong.class).setParameter(1, "%" + ma + "%").getResultList();
			em.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<PhieuDatPhong> layPhieuDatPhongNgayHienTai() {
//		ArrayList<PhieuDatPhong> danhSachPhieuDatPhong = new ArrayList<PhieuDatPhong>();
//		PhieuDatPhong phieuDatPhong = null;
//		// Lấy ngày hiện tại
//		java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		String currentDateString = dateFormat.format(currentDate);
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		try {
//			String sql = "SELECT PhieuDatPhong.* FROM PhieuDatPhong WHERE CONVERT(DATE, thoiGianNhanPhong) = ?";
//			statement = con.prepareStatement(sql);
//			statement.setString(1, currentDateString);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				String maPhieuDat = rs.getString("maPhieuDat");
//				Phong phong = new Phong(rs.getString("maPhong"));
//				NhanVien nhanVien = new NhanVien(rs.getString("maNhanVien"));
//				KhachHang khachHang = new KhachHang(rs.getString("maKhachHang"));
//				java.sql.Timestamp thoiGianDatPhong = rs.getTimestamp("thoiGianDatPhong");
//				java.sql.Timestamp thoiGianNhanPhong = rs.getTimestamp("thoiGianNhanPhong");
//				Double tienCoc = rs.getDouble("tienCoc");
//				String trangThai = rs.getString("trangThai");
//				String moTa = rs.getString("moTa");
//
//				phieuDatPhong = new PhieuDatPhong(maPhieuDat, phong, nhanVien, khachHang, thoiGianDatPhong,
//						thoiGianNhanPhong, tienCoc, trangThai, moTa);
//				danhSachPhieuDatPhong.add(phieuDatPhong);
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
//		return danhSachPhieuDatPhong;
		
		try {
			String sql = "SELECT PhieuDatPhong.* FROM PhieuDatPhong WHERE CONVERT(DATE, thoiGianNhanPhong) = ?";
			em.getTransaction().begin();
			ArrayList<PhieuDatPhong> list = (ArrayList<PhieuDatPhong>) em.createNativeQuery(sql, PhieuDatPhong.class).setParameter(1, new java.sql.Date(System.currentTimeMillis())).getResultList();
			em.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean taoPhieuDatPhong(PhieuDatPhong phieuDatPhong) {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		int n = 0;
//		try {
//			statement = con.prepareStatement("INSERT INTO PhieuDatPhong values(?,?,?,?,?,?,?,?,?)");
//			statement.setString(1, phieuDatPhong.getMaPhieuDat());
//			statement.setString(2, phieuDatPhong.getPhong().getMaPhong());
//			statement.setString(3, phieuDatPhong.getNhanVien().getMaNhanVien());
//			statement.setString(4, phieuDatPhong.getKhachHang().getMaKhachHang());
//			statement.setTimestamp(5, phieuDatPhong.getThoiGianDatPhong());
//			statement.setTimestamp(6, phieuDatPhong.getThoiGianNhanPhong());
//			statement.setDouble(7, phieuDatPhong.getTienCoc());
//			statement.setString(8, phieuDatPhong.getTrangThai());
//			statement.setString(9, phieuDatPhong.getMoTa());
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
			String sql = "INSERT INTO PhieuDatPhong values(?,?,?,?,?,?,?,?,?)";
			em.getTransaction().begin();
			int result = em.createNativeQuery(sql).setParameter(1, phieuDatPhong.getMaPhieuDat())
					.setParameter(2, phieuDatPhong.getPhong().getMaPhong())
					.setParameter(3, phieuDatPhong.getNhanVien().getMaNhanVien())
					.setParameter(4, phieuDatPhong.getKhachHang().getMaKhachHang())
					.setParameter(5, phieuDatPhong.getThoiGianDatPhong())
					.setParameter(6, phieuDatPhong.getThoiGianNhanPhong()).setParameter(7, phieuDatPhong.getTienCoc())
					.setParameter(8, phieuDatPhong.getTrangThai()).setParameter(9, phieuDatPhong.getMoTa())
					.executeUpdate();
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

	public boolean capNhatPhieuDatPhong(PhieuDatPhong phieuDatPhong) {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		int n = 0;
//		try {
//			statement = con.prepareStatement(
//					"UPDATE PhieuDatPhong SET  maPhong = ?, maNhanVien = ?, maKhachHang = ?, thoiGianDatPhong = ?, thoiGianNhanPhong = ?, tienCoc = ?, trangThai = ?, moTa = ?"
//							+ " WHERE maPhieuDat = ?");
//			statement.setString(1, phieuDatPhong.getPhong().getMaPhong());
//			statement.setString(2, phieuDatPhong.getNhanVien().getMaNhanVien());
//			statement.setString(3, phieuDatPhong.getKhachHang().getMaKhachHang());
//			statement.setTimestamp(4, phieuDatPhong.getThoiGianDatPhong());
//			statement.setTimestamp(5, phieuDatPhong.getThoiGianNhanPhong());
//			statement.setDouble(6, phieuDatPhong.getTienCoc());
//			statement.setString(7, phieuDatPhong.getTrangThai());
//			statement.setString(8, phieuDatPhong.getMoTa());
//			statement.setString(9, phieuDatPhong.getMaPhieuDat());
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
			String sql = "UPDATE PhieuDatPhong SET  maPhong = ?, maNhanVien = ?, maKhachHang = ?, thoiGianDatPhong = ?, thoiGianNhanPhong = ?, tienCoc = ?, trangThai = ?, moTa = ?"
					+ " WHERE maPhieuDat = ?";
			em.getTransaction().begin();
			int result = em.createNativeQuery(sql).setParameter(1, phieuDatPhong.getPhong().getMaPhong())
					.setParameter(2, phieuDatPhong.getNhanVien().getMaNhanVien())
					.setParameter(3, phieuDatPhong.getKhachHang().getMaKhachHang())
					.setParameter(4, phieuDatPhong.getThoiGianDatPhong())
					.setParameter(5, phieuDatPhong.getThoiGianNhanPhong()).setParameter(6, phieuDatPhong.getTienCoc())
					.setParameter(7, phieuDatPhong.getTrangThai()).setParameter(8, phieuDatPhong.getMoTa())
					.setParameter(9, phieuDatPhong.getMaPhieuDat()).executeUpdate();
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

	public PhieuDatPhong layThongTinPhieuDatTrangThai_DangCho_MaPhong(String maP) {
//		PhieuDatPhong phieuDatPhong = null;
//
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		String sql = "select * from PhieuDatPhong PD\r\n" + "JOIN Phong P  ON P.maPhong = PD.maPhong\r\n" + "WHERE \r\n"
//				+ "	  P.maPhong = '" + maP + "'\r\n" + "	  AND\r\n"
//				+ "      CAST(PD.thoiGianNhanPhong AS DATE) = CAST(GETDATE() AS DATE)\r\n"
//				+ "      AND PD.trangThai = N'Chờ nhận phòng'\r\n"
//				+ "      AND PD.thoiGianNhanPhong >= CONVERT(datetime, DATEADD(HOUR, 0, GETDATE()), 100)\r\n"
//
//		;
//
//		try {
//
//			statement = con.prepareStatement(sql);
//			ResultSet rs = statement.executeQuery();
//
//			while (rs.next()) {
//				String maPhieuDat = rs.getString("maPhieuDat");
//				Phong phong = new Phong(rs.getString("maPhong"));
//				NhanVien nhanVien = new NhanVien(rs.getString("maNhanVien"));
//				KhachHang khachHang = new KhachHang(rs.getString("maKhachHang"));
//				java.sql.Timestamp thoiGianDatPhong = rs.getTimestamp("thoiGianDatPhong");
//				java.sql.Timestamp thoiGianNhanPhong = rs.getTimestamp("thoiGianNhanPhong");
//				Double tienCoc = rs.getDouble("tienCoc");
//				String trangThai = rs.getString("trangThai");
//				String moTa = rs.getString("moTa");
//				phieuDatPhong = new PhieuDatPhong(maPhieuDat, phong, nhanVien, khachHang, thoiGianDatPhong,
//						thoiGianNhanPhong, tienCoc, trangThai, moTa);
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
//		return phieuDatPhong;
		try {
			String sql = "select * from PhieuDatPhong PD\r\n" + "JOIN Phong P  ON P.maPhong = PD.maPhong\r\n"
					+ "WHERE \r\n" + "	  P.maPhong = '" + "?" + "'\r\n" + "	  AND\r\n"
					+ "      CAST(PD.thoiGianNhanPhong AS DATE) = CAST(GETDATE() AS DATE)\r\n"
					+ "      AND PD.trangThai = N'Chờ nhận phòng'\r\n"
					+ "      AND PD.thoiGianNhanPhong >= CONVERT(datetime, DATEADD(HOUR, 0, GETDATE()), 100)\r\n";
			em.getTransaction().begin();
			PhieuDatPhong phieuDatPhong = (PhieuDatPhong) em.createNativeQuery(sql, PhieuDatPhong.class).setParameter(1, maP).getSingleResult();
			em.close();
			return phieuDatPhong;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean capNhatPhieuDatTrangThai_DaNhanPhong_MaPhieuDat(String maPhieuDat) {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		int n = 0;
//		String sql = "UPDATE PhieuDatPhong\r\n" + "Set trangThai = N'Đã nhận phòng'\r\n" + "WHERE\r\n"
//				+ "tienCoc > 0\r\n" + "AND\r\n" + "maPhieuDat = '" + maPhieuDat + " '\r\n" + "AND \r\n"
//				+ "trangThai=N'Chờ nhận phòng'\r\n" + "AND\r\n"
//				+ "CAST(thoiGianNhanPhong AS DATE) = CAST(GETDATE() AS DATE)\r\n" + "AND\r\n"
//				+ "DATEADD(hour, 0, GETDATE()) <= thoiGianNhanPhong\r\n" + "AND\r\n"
//				+ "DATEDIFF(hour, thoiGianNhanPhong, GETDATE()) <= 1";
//		try {
//			statement = con.prepareStatement(sql);
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
			String sql = "UPDATE PhieuDatPhong\r\n" + "Set trangThai = N'Đã nhận phòng'\r\n" + "WHERE\r\n"
					+ "tienCoc > 0\r\n" + "AND\r\n" + "maPhieuDat = '" + "?" + " '\r\n" + "AND \r\n"
					+ "trangThai=N'Chờ nhận phòng'\r\n" + "AND\r\n"
					+ "CAST(thoiGianNhanPhong AS DATE) = CAST(GETDATE() AS DATE)\r\n" + "AND\r\n"
					+ "DATEADD(hour, 0, GETDATE()) <= thoiGianNhanPhong\r\n" + "AND\r\n"
					+ "DATEDIFF(hour, thoiGianNhanPhong, GETDATE()) <= 1";
			em.getTransaction().begin();
			int result = em.createNativeQuery(sql).setParameter(1, maPhieuDat).executeUpdate();
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

	public boolean HuyPhieuDatPhong(String maPDP) {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		int n = 0;
//		try {
//			statement = con.prepareStatement("UPDATE PhieuDatPhong SET TrangThai = N'Đã hủy' WHERE maPhieuDat = ?");
//			statement.setString(1, maPDP);
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
			String sql = "UPDATE PhieuDatPhong SET TrangThai = N'Đã hủy' WHERE maPhieuDat = ?";
			em.getTransaction().begin();
			int result = em.createNativeQuery(sql).setParameter(1, maPDP).executeUpdate();
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
