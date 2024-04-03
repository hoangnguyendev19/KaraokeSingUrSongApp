package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.DichVu;
import entity.LoaiPhong;
import entity.Phong;
import entity.TrangThaiPhong;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;

public class Phong_DAO {

	private EntityManager em;

	public Phong_DAO() {
		em = new ConnectDB().getEntityManager();
	}

	public boolean capNhat_TinhTrangPhong(String maPh, String tinhTrang) {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		int n = 0;
//		try {
//			statement = con.prepareStatement("UPDATE Phong SET tinhTrangPhong=? " + " WHERE maPhong=?");
//			statement.setString(1, tinhTrang);
//			statement.setString(2, maPh);
//
//			n = statement.executeUpdate();
//
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
			String sql = "UPDATE Phong SET tinhTrangPhong=? WHERE maPhong=?";
			em.getTransaction().begin();
			int result = em.createNativeQuery(sql).setParameter(1, tinhTrang).setParameter(2, maPh).executeUpdate();

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

	public ArrayList<Phong> layTatCaPhong() {
//		ArrayList<Phong> danhSachPhong = new ArrayList<Phong>();
//		try {
//			ConnectDB.getInstance();
//			Connection con = ConnectDB.getConnection();
//			String sql = "SELECT * FROM Phong";
//			Statement statement = con.createStatement();
//			ResultSet rs = statement.executeQuery(sql);
//			while (rs.next()) {
//
//				String maPhong = rs.getString("maPhong");
//				String tenPhong = rs.getString("tenPhong");
//				LoaiPhong loaiPhong = new LoaiPhong(rs.getString("maLoaiPhong"));
//
//				TrangThaiPhong trangThaiPhong = new TrangThaiPhong(rs.getString("maTrangThai"));
//				java.sql.Date ngayTaoPhong = rs.getDate("ngayTaoPhong");
//				String viTriPhong = rs.getString("viTriPhong");
//				String ghiChu = rs.getString("ghiChu");
//				String tinhTrangPhong = rs.getString("tinhTrangPhong");
//				Phong phong = new Phong(maPhong, tenPhong, loaiPhong, trangThaiPhong, ngayTaoPhong, viTriPhong, ghiChu,
//						tinhTrangPhong);
//
//				danhSachPhong.add(phong);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return danhSachPhong;

		try {
			String sql = "SELECT * FROM Phong";
			em.getTransaction().begin();
			List<Phong> danhSachPhong = em.createNativeQuery(sql, Phong.class).getResultList();
			em.close();
			return (ArrayList<Phong>) danhSachPhong;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean capNhatTatCaPhong_TrangThaiPhongMoiNhat() {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		int n = 0;
//		String sql = "--- B1: cập nhật PDP hết hạn\r\n" + "UPDATE PhieuDatPhong\r\n" + "SET trangThai = N'Hết hạn'\r\n"
//				+ "WHERE \r\n" + "thoiGianNhanPhong < GETDATE()\r\n" + "AND trangThai =  N'Chờ nhận phòng'\r\n"
//				+ "--- B2: cập nhật  trạng thái Hóa đơn = đã hủy\r\n" + "\r\n" + "UPDATE HoaDon \r\n"
//				+ "SET trangThai = N'Đã hủy'\r\n" + "FROM HoaDon\r\n" + "WHERE \r\n"
//				+ "CONVERT(date, HoaDon.ngayLap) < CONVERT(date, GETDATE())\r\n" + "AND\r\n"
//				+ "trangThai = N'Đang chờ thanh toán';\r\n"
//				+ "--- B2.2: cập nhật trạng thái phòng trống cho phiếu đặt = hết hạn, hóa đơn = đã thanh toán\r\n"
//				+ "UPDATE Phong\r\n" + "SET maTrangThai = 'VC'\r\n" + "FROM Phong \r\n"
//				+ "INNER JOIN ChiTietHoaDon ON Phong.maPhong = ChiTietHoaDon.maPhong\r\n"
//				+ "INNER JOIN HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon\r\n"
//				+ "INNER JOIN PhieuDatPhong ON Phong.maPhong = PhieuDatPhong.maPhong\r\n" + "WHERE \r\n"
//				+ "HoaDon.trangThai = N'Đã thanh toán'\r\n" + "OR\r\n" + "HoaDon.trangThai = N'Đã hủy'\r\n" + "OR\r\n"
//				+ "PhieuDatPhong.trangThai = N'Hết hạn'\r\n" + "OR Phong.matrangThai <> 'OOO' \r\n"
//				+ "--- B3: cập nhật trạng thái phòng đã đặt\r\n" + "UPDATE Phong\r\n" + "SET maTrangThai = 'OCP'\r\n"
//				+ "FROM Phong\r\n" + "INNER JOIN PhieuDatPhong ON Phong.maPhong = PhieuDatPhong.maPhong\r\n"
//				+ "WHERE\r\n"
//				+ " ABS( DATEDIFF(SECOND, PhieuDatPhong.thoiGianNhanPhong, cast(GETDATE() as dateTime))) <= 3600\r\n"
//				+ "AND PhieuDatPhong.trangThai = N'Chờ nhận phòng'\r\n"
//				+ "AND CONVERT(date, PhieuDatPhong.thoiGianNhanPhong) = CONVERT(date, GETDATE());\r\n" + "\r\n"
//				+ "--- B4: cập nhật trạng thái phòng đang sử dụng\r\n" + "UPDATE Phong\r\n"
//				+ "SET maTrangThai = 'OC'\r\n" + "FROM Phong\r\n"
//				+ "INNER JOIN ChiTietHoaDon ON Phong.maPhong = ChiTietHoaDon.maPhong\r\n"
//				+ "INNER JOIN HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon\r\n"
//				+ "WHERE CONVERT(date, HoaDon.ngayLap) = CONVERT(date, GETDATE())\r\n"
//				+ "AND HoaDon.trangThai = N'Đang chờ thanh toán';\r\n" + "\r\n"
//				+ "--- B5: cập nhật trạng thái phòng đang trống\r\n" + "UPDATE Phong\r\n" + "SET maTrangThai = 'VC'\r\n"
//				+ "FROM Phong\r\n" + "INNER JOIN ChiTietHoaDon ON Phong.maPhong = ChiTietHoaDon.maPhong\r\n"
//				+ "INNER JOIN HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon\r\n"
//				+ "INNER JOIN PhieuDatPhong ON Phong.maPhong = PhieuDatPhong.maPhong\r\n" + "WHERE \r\n"
//				+ "maTrangThai <> 'OOO'\r\n" + "AND\r\n" + "maTrangThai <> 'OCP'\r\n" + "AND\r\n"
//				+ "maTrangThai <> 'OC'\r\n" + ";";
//
//		try {
//			statement = con.prepareStatement(sql);
//			n = statement.executeUpdate();
//
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
			String sql = "--- B1: cập nhật PDP hết hạn\r\n" + "UPDATE PhieuDatPhong\r\n"
					+ "SET trangThai = N'Hết hạn'\r\n" + "WHERE \r\n" + "thoiGianNhanPhong < GETDATE()\r\n"
					+ "AND trangThai =  N'Chờ nhận phòng'\r\n" + "--- B2: cập nhật  trạng thái Hóa đơn = đã hủy\r\n"
					+ "\r\n" + "UPDATE HoaDon \r\n" + "SET trangThai = N'Đã hủy'\r\n" + "FROM HoaDon\r\n" + "WHERE \r\n"
					+ "CONVERT(date, HoaDon.ngayLap) < CONVERT(date, GETDATE())\r\n" + "AND\r\n"
					+ "trangThai = N'Đang chờ thanh toán';\r\n"
					+ "--- B2.2: cập nhật trạng thái phòng trống cho phiếu đặt = hết hạn, hóa đơn = đã thanh toán\r\n"
					+ "UPDATE Phong\r\n" + "SET maTrangThai = 'VC'\r\n" + "FROM Phong \r\n"
					+ "INNER JOIN ChiTietHoaDon ON Phong.maPhong = ChiTietHoaDon.maPhong\r\n"
					+ "INNER JOIN HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon\r\n"
					+ "INNER JOIN PhieuDatPhong ON Phong.maPhong = PhieuDatPhong.maPhong\r\n" + "WHERE \r\n"
					+ "HoaDon.trangThai = N'Đã thanh toán'\r\n" + "OR\r\n" + "HoaDon.trangThai = N'Đã hủy'\r\n"
					+ "OR\r\n" + "PhieuDatPhong.trangThai = N'Hết hạn'\r\n" + "OR Phong.matrangThai <> 'OOO' \r\n"
					+ "--- B3: cập nhật trạng thái phòng đã đặt\r\n" + "UPDATE Phong\r\n"
					+ "SET maTrangThai = 'OCP'\r\n" + "FROM Phong\r\n"
					+ "INNER JOIN PhieuDatPhong ON Phong.maPhong = PhieuDatPhong.maPhong\r\n" + "WHERE\r\n"
					+ " ABS( DATEDIFF(SECOND, PhieuDatPhong.thoiGianNhanPhong, cast(GETDATE() as dateTime))) <= 3600\r\n"
					+ "AND PhieuDatPhong.trangThai = N'Chờ nhận phòng'\r\n"
					+ "AND CONVERT(date, PhieuDatPhong.thoiGianNhanPhong) = CONVERT(date, GETDATE());\r\n" + "\r\n"
					+ "--- B4: cập nhật trạng thái phòng đang sử dụng\r\n" + "UPDATE Phong\r\n"
					+ "SET maTrangThai = 'OC'\r\n" + "FROM Phong\r\n"
					+ "INNER JOIN ChiTietHoaDon ON Phong.maPhong = ChiTietHoaDon.maPhong\r\n"
					+ "INNER JOIN HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon\r\n"
					+ "WHERE CONVERT(date, HoaDon.ngayLap) = CONVERT(date, GETDATE())\r\n"
					+ "AND HoaDon.trangThai = N'Đang chờ thanh toán';\r\n" + "\r\n"
					+ "--- B5: cập nhật trạng thái phòng đang trống\r\n" + "UPDATE Phong\r\n"
					+ "SET maTrangThai = 'VC'\r\n" + "FROM Phong\r\n"
					+ "INNER JOIN ChiTietHoaDon ON Phong.maPhong = ChiTietHoaDon.maPhong\r\n"
					+ "INNER JOIN HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon\r\n"
					+ "INNER JOIN PhieuDatPhong ON Phong.maPhong = PhieuDatPhong.maPhong\r\n" + "WHERE \r\n"
					+ "maTrangThai <> 'OOO'\r\n" + "AND\r\n" + "maTrangThai <> 'OCP'\r\n" + "AND\r\n";

			em.getTransaction().begin();
			int result = em.createNativeQuery(sql).executeUpdate();

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

	public ArrayList<Phong> timPhong_TheoTongHop(String tenLoaiPhong, String tenKhachHang, String soDienThoai,
			String tenTrangThai, String maPhongTK) {
//
//		ArrayList<Phong> dsPhong = new ArrayList<>();
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		String sqlTenLoaiPhong = "";
//
//		if (tenLoaiPhong.trim().equals("Tất cả"))
//			sqlTenLoaiPhong = "LoaiPhong.tenLoaiPhong Like N'%%'";
//		else {
//			sqlTenLoaiPhong = "LoaiPhong.tenLoaiPhong = N'" + tenLoaiPhong + "' \r\n";
//		}
//		String sqlTenKhachHang = "KhachHang.hoTen Like N'%" + tenKhachHang + "%'\r\n";
//		String sqlSoDienThoai = "KhachHang.soDienThoai LIKE '%" + soDienThoai + "%'";
//		String sqlTenTrangThai = "TrangThaiPhong.tenTrangThai =N'" + tenTrangThai + "'\r\n";
//		String sqlMaPhong = "Phong.maPhong LIKE '%" + maPhongTK + "%'";
//
//		String sqlAND = "AND\r\n";
//		String sqlTop1 = "TOP 1";
//		String sqlWhere = "\n where \n";
//		String sqlJoinLoaiP = "INNER JOIN LoaiPhong ON LoaiPhong.maLoaiPhong = Phong.maLoaiPhong\r\n";
//		String sqlJoinTrangThaiP = "INNER JOIN TrangThaiPhong ON TrangThaiPhong.maTrangThai = Phong.maTrangThai\r\n";
//
//		String sqlJoinPhieuDat = "INNER JOIN PhieuDatPhong ON Phong.maPhong = PhieuDatPhong.maPhong\r\n";
//
//		String sqlJoinKhachHangPDP = "INNER JOIN KhachHang ON KhachHang.maKhachHang = PhieuDatPhong.maKhachHang\r\n";
//		String sqlJoinKhachHangHD = "INNER JOIN KhachHang ON KhachHang.maKhachHang = HoaDon.maKhachHang\r\n";
//
//		String sqlJoinCTHoaDon = "INNER JOIN ChiTietHoaDon ON Phong.maPhong = ChiTietHoaDon.maPhong\r\n";
//		String sqlJoinHoaDon = "INNER JOIN HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon\r\n";
//
//		//////////////////////////////////////////////////////////////////////////////////////////////////////
//		String sqlTongHop = "select * from Phong\r\n";
//		String sqlPhongTrong = "select * from Phong\r\n"
//				+ "INNER JOIN LoaiPhong ON LoaiPhong.maLoaiPhong = Phong.maLoaiPhong\r\n"
//				+ "INNER JOIN TrangThaiPhong ON TrangThaiPhong.maTrangThai = Phong.maTrangThai\r\n" + "where\r\n"
//				+ sqlTenLoaiPhong + sqlAND + "TrangThaiPhong.tenTrangThai =N'Trống'\r\n" + sqlAND
//				+ "Phong.maPhong LIKE '%P%'";
//
//		if (tenTrangThai.trim().equals("Trống")) {
//
//			sqlTongHop = sqlTongHop + sqlJoinLoaiP + sqlJoinTrangThaiP + sqlWhere + sqlTenLoaiPhong + sqlAND
//					+ sqlTenTrangThai + sqlAND + sqlMaPhong;
//		}
//		if (tenTrangThai.trim().equals("Đã đặt")) {
//			sqlTongHop = sqlTongHop + sqlJoinLoaiP + sqlJoinTrangThaiP + sqlJoinPhieuDat + sqlJoinKhachHangPDP
//					+ sqlWhere + sqlTenLoaiPhong + sqlAND + sqlTenTrangThai + sqlAND + sqlMaPhong + sqlAND
//					+ sqlTenKhachHang + sqlAND + sqlSoDienThoai + sqlAND
//					+ "ABS( DATEDIFF(SECOND, PhieuDatPhong.thoiGianNhanPhong, cast(GETDATE() as dateTime))) <= 3600";
//		}
//		if (tenTrangThai.trim().equals("Đang sử dụng")) {
//
//			sqlTongHop = "";
//			sqlTongHop = "select * from Phong \n" + sqlJoinLoaiP + sqlJoinTrangThaiP + sqlJoinCTHoaDon + sqlJoinHoaDon
//					+ sqlJoinKhachHangHD + sqlWhere + sqlTenLoaiPhong + sqlAND + sqlTenTrangThai + sqlAND + sqlMaPhong
//					+ sqlAND + sqlTenKhachHang + sqlAND + sqlSoDienThoai + sqlAND
//					+ "(HoaDon.trangThai = N'Đang chờ thanh toán' AND HoaDon.maKhachHang = KhachHang.maKhachHang) \n"
//					+ sqlAND + "CONVERT(date, HoaDon.ngayLap) = CONVERT(date, GETDATE())";
//		}
//
//		try {
//			PreparedStatement statement = con.prepareStatement(sqlTongHop);
//
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				String maPhong = rs.getString("maPhong");
//				String tenPhong1 = rs.getString("tenPhong");
//				LoaiPhong loaiPhong = new LoaiPhong(rs.getString("maLoaiPhong"));
//				TrangThaiPhong trangThaiPhong = new TrangThaiPhong(rs.getString("maTrangThai"));
//				java.sql.Date ngayTaoPhong = rs.getDate("ngayTaoPhong");
//				String viTriPhong = rs.getString("viTriPhong");
//				String ghiChu = rs.getString("ghiChu");
//				String tinhTrangPhong = rs.getString("tinhTrangPhong");
//
//				Phong phong = new Phong(maPhong, tenPhong1, loaiPhong, trangThaiPhong, ngayTaoPhong, viTriPhong, ghiChu,
//						tinhTrangPhong);
//
//				dsPhong.add(phong);
//			}
//		} catch (SQLException e) {
//
//		}
//		return dsPhong;
		
		try {
			String sql = "select * from Phong\r\n"
					+ "INNER JOIN LoaiPhong ON LoaiPhong.maLoaiPhong = Phong.maLoaiPhong\r\n"
					+ "INNER JOIN TrangThaiPhong ON TrangThaiPhong.maTrangThai = Phong.maTrangThai\r\n" + "where\r\n"
					+ "LoaiPhong.tenLoaiPhong Like N'%%' AND\r\n" + "TrangThaiPhong.tenTrangThai =N'Trống' AND\r\n"
					+ "Phong.maPhong LIKE '%" + maPhongTK + "%'";
			
			em.getTransaction().begin();
			
			ArrayList<Phong> dsPhong = (ArrayList<Phong>) em.createNativeQuery(sql, Phong.class).getResultList();
			
			em.close();
			return dsPhong;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public Phong timPhong_TheoMaPhong(String maPh) {
//		Phong phong = null;
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		LoaiPhong_DAO DAO_LP = new LoaiPhong_DAO();
//		PreparedStatement statement = null;
//		try {
//			String sql = "SELECT * FROM Phong WHERE maPhong = ?";
//			statement = con.prepareStatement(sql);
//			statement.setString(1, maPh);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				String maPhong = rs.getString("maPhong");
//				String tenPhong = rs.getString("tenPhong");
//				LoaiPhong loaiPhong = DAO_LP.layLoaiPhong_TheoMaLoaiPhong(rs.getString("maLoaiPhong"));
//				TrangThaiPhong trangThaiPhong = new TrangThaiPhong(rs.getString("maTrangThai"));
//				java.sql.Date ngayTaoPhong = rs.getDate("ngayTaoPhong");
//				String viTriPhong = rs.getString("viTriPhong");
//				String ghiChu = rs.getString("ghiChu");
//				String tinhTrangPhong = rs.getString("tinhTrangPhong");
//				phong = new Phong(maPhong, tenPhong, loaiPhong, trangThaiPhong, ngayTaoPhong, viTriPhong, ghiChu,
//						tinhTrangPhong);
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
//		return phong;

		try {
			String sql = "SELECT * FROM Phong WHERE maPhong = ?";
			em.getTransaction().begin();
			Phong phong = (Phong) em.createNativeQuery(sql, Phong.class).setParameter(1, maPh).getSingleResult();
			em.close();
			return phong;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Phong> timDSPhongTheoMaLoaiPhong(String maLP) {
//		Phong phong = null;
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//
//		ArrayList<Phong> dsPhong = new ArrayList<>();
//		try {
//			String sql = "SELECT * FROM Phong WHERE maLoaiPhong LIKE '%" + maLP + "%'";
//			statement = con.prepareStatement(sql);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				String maPhong = rs.getString("maPhong");
//				String tenPhong = rs.getString("tenPhong");
//				LoaiPhong loaiPhong = new LoaiPhong(rs.getString("maLoaiPhong"));
//				TrangThaiPhong trangThaiPhong = new TrangThaiPhong(rs.getString("maTrangThai"));
//				java.sql.Date ngayTaoPhong = rs.getDate("ngayTaoPhong");
//				String viTriPhong = rs.getString("viTriPhong");
//				String ghiChu = rs.getString("ghiChu");
//				String tinhTrangPhong = rs.getString("tinhTrangPhong");
//				phong = new Phong(maPhong, tenPhong, loaiPhong, trangThaiPhong, ngayTaoPhong, viTriPhong, ghiChu,
//						tinhTrangPhong);
//				dsPhong.add(phong);
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
//		return dsPhong;

		try {
			String sql = "SELECT * FROM Phong WHERE maLoaiPhong = ?";
			em.getTransaction().begin();
			ArrayList<Phong> dsPhong = (ArrayList<Phong>) em.createNativeQuery(sql, Phong.class).setParameter(1, maLP)
					.getResultList();

			em.close();
			return dsPhong;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Phong timPhong_TheoMaLoaiPhong(String maLoaiPh) {
//		Phong phong = null;
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		try {
//			String sql = "SELECT * FROM Phong WHERE maLoaiPhong = ?";
//			statement = con.prepareStatement(sql);
//			statement.setString(1, maLoaiPh);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				String maPhong = rs.getString("maPhong");
//				String tenPhong = rs.getString("tenPhong");
//				LoaiPhong loaiPhong = new LoaiPhong(rs.getString("maLoaiPhong"));
//				TrangThaiPhong trangThaiPhong = new TrangThaiPhong(rs.getString("maTrangThai"));
//				java.sql.Date ngayTaoPhong = rs.getDate("ngayTaoPhong");
//				String viTriPhong = rs.getString("viTriPhong");
//				String ghiChu = rs.getString("ghiChu");
//				String tinhTrangPhong = rs.getString("tinhTrangPhong");
//				phong = new Phong(maPhong, tenPhong, loaiPhong, trangThaiPhong, ngayTaoPhong, viTriPhong, ghiChu,
//						tinhTrangPhong);
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
//		return phong;

		try {
			String sql = "SELECT * FROM Phong WHERE maLoaiPhong = ?";
			em.getTransaction().begin();
			Phong phong = (Phong) em.createNativeQuery(sql, Phong.class).setParameter(1, maLoaiPh).getSingleResult();
			em.close();
			return phong;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Phong> timPhong_TheoMaTrangThai(String maTrThai) {
//		ArrayList<Phong> listP = new ArrayList<Phong>();
//		Phong phong = null;
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		try {
//			String sql = "SELECT * FROM Phong WHERE maTrangThai = ?";
//			statement = con.prepareStatement(sql);
//			statement.setString(1, maTrThai);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				String maPhong = rs.getString("maPhong");
//				String tenPhong = rs.getString("tenPhong");
//				LoaiPhong loaiPhong = new LoaiPhong(rs.getString("maLoaiPhong"));
//				TrangThaiPhong trangThaiPhong = new TrangThaiPhong(rs.getString("maTrangThai"));
//				java.sql.Date ngayTaoPhong = rs.getDate("ngayTaoPhong");
//				String viTriPhong = rs.getString("viTriPhong");
//				String ghiChu = rs.getString("ghiChu");
//				String tinhTrangPhong = rs.getString("tinhTrangPhong");
//				phong = new Phong(maPhong, tenPhong, loaiPhong, trangThaiPhong, ngayTaoPhong, viTriPhong, ghiChu,
//						tinhTrangPhong);
//				listP.add(phong);
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
//		return listP;

		try {
			String sql = "SELECT * FROM Phong WHERE maTrangThai = ?";
			em.getTransaction().begin();
			ArrayList<Phong> listP = (ArrayList<Phong>) em.createNativeQuery(sql, Phong.class).setParameter(1, maTrThai)
					.getResultList();
			em.close();
			return listP;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean taoPhong(Phong phong) {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		int n = 0;
//		try {
//			statement = con.prepareStatement("INSERT INTO Phong values(?,?,?,?,?,?,?,?)");
//			statement.setString(1, phong.getMaPhong());
//			statement.setString(2, phong.getTenPhong());
//			statement.setString(3, phong.getLoaiPhong().getMaLoaiPhong());
//			statement.setString(4, phong.getTrangThaiPhong().getMaTrangThai());
//			statement.setDate(5, phong.getNgayTaoPhong());
//			statement.setString(6, phong.getViTriPhong());
//			statement.setString(7, phong.getGhiChu());
//			statement.setString(8, phong.getTinhTrangPhong());
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
			String sql = "INSERT INTO Phong values(?,?,?,?,?,?,?,?)";
			em.getTransaction().begin();
			int result = em.createNativeQuery(sql, Phong.class).setParameter(1, phong.getMaPhong())
					.setParameter(2, phong.getTenPhong()).setParameter(3, phong.getLoaiPhong().getMaLoaiPhong())
					.setParameter(4, phong.getTrangThaiPhong().getMaTrangThai())
					.setParameter(5, phong.getNgayTaoPhong()).setParameter(6, phong.getViTriPhong())
					.setParameter(7, phong.getGhiChu()).setParameter(8, phong.getTinhTrangPhong()).executeUpdate();

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

	public boolean capNhatPhong(Phong phong) {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		int n = 0;
//		try {
//			statement = con.prepareStatement(
//					"UPDATE Phong SET tenPhong=?, maLoaiPhong=?, maTrangThai=?, ngayTaoPhong = ? , viTriPhong=?, ghiChu=?, tinhTrangPhong=?"
//							+ " WHERE maPhong=?");
//			statement.setString(1, phong.getTenPhong());
//			statement.setString(2, phong.getLoaiPhong().getMaLoaiPhong());
//			statement.setString(3, phong.getTrangThaiPhong().getMaTrangThai());
//			statement.setDate(4, phong.getNgayTaoPhong());
//			statement.setString(5, phong.getViTriPhong());
//			statement.setString(6, phong.getGhiChu());
//			statement.setString(7, phong.getTinhTrangPhong());
//			statement.setString(8, phong.getMaPhong());
//			n = statement.executeUpdate();
//
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
			String sql = "UPDATE Phong SET tenPhong=?, maLoaiPhong=?, maTrangThai=?, ngayTaoPhong = ? , viTriPhong=?, ghiChu=?, tinhTrangPhong=?"
					+ " WHERE maPhong=?";

			em.getTransaction().begin();
			int result = em.createNativeQuery(sql, Phong.class).setParameter(1, phong.getTenPhong())
					.setParameter(2, phong.getLoaiPhong().getMaLoaiPhong())
					.setParameter(3, phong.getTrangThaiPhong().getMaTrangThai())
					.setParameter(4, phong.getNgayTaoPhong()).setParameter(5, phong.getViTriPhong())
					.setParameter(6, phong.getGhiChu()).setParameter(7, phong.getTinhTrangPhong())
					.setParameter(8, phong.getMaPhong()).executeUpdate();

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

	public boolean capNhat_TranThaiPhong(String maPh, String trThPh) {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		int n = 0;
//		try {
//			statement = con.prepareStatement("UPDATE Phong SET maTrangThai=? " + " WHERE maPhong=?");
//			statement.setString(2, maPh);
//			statement.setString(1, trThPh);
//
//			n = statement.executeUpdate();
//
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
			String sql = "UPDATE Phong SET maTrangThai=? " + " WHERE maPhong=?";

			em.getTransaction().begin();
			int result = em.createNativeQuery(sql, Phong.class).setParameter(1, trThPh).setParameter(2, maPh)
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

	public boolean xoaPhong(Phong phong) {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		int n = 0;
//		try {
//			statement = con.prepareStatement("DELETE FROM Phong" + " WHERE maPhong=?");
//			statement.setString(1, phong.getMaPhong());
//			n = statement.executeUpdate();
//
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
			String sql = "DELETE FROM Phong" + " WHERE maPhong=?";

			em.getTransaction().begin();
			int result = em.createNativeQuery(sql, Phong.class).setParameter(1, phong.getMaPhong()).executeUpdate();

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

	public List<Phong> layDanhSachPhongTrongTheoNgayVaLoaiPhong(Timestamp startTime, Timestamp endTime, String lp) {
//		List<Phong> danhSachPhong = new ArrayList<Phong>();
//		PreparedStatement statement = null;
//
//		try {
//			ConnectDB.getInstance();
//			Connection con = ConnectDB.getConnection();
//			String sql = "SELECT * FROM Phong WHERE maLoaiPhong = ? AND maPhong NOT IN "
//					+ "(SELECT maPhong FROM PhieuDatPhong " + "WHERE thoiGianNhanPhong BETWEEN ? AND ?)";
//
//			statement = con.prepareStatement(sql);
//			statement.setString(1, lp);
//			statement.setTimestamp(2, startTime);
//			statement.setTimestamp(3, endTime);
//
//			ResultSet rs = statement.executeQuery();
//
//			while (rs.next()) {
//				String maPhong = rs.getString("maPhong");
//				String tenPhong = rs.getString("tenPhong");
//				LoaiPhong loaiPhong = new LoaiPhong(rs.getString("maLoaiPhong"));
//
//				TrangThaiPhong trangThaiPhong = new TrangThaiPhong(rs.getString("maTrangThai"));
//				java.sql.Date ngayTaoPhong = rs.getDate("ngayTaoPhong");
//				String viTriPhong = rs.getString("viTriPhong");
//				String ghiChu = rs.getString("ghiChu");
//				String tinhTrangPhong = rs.getString("tinhTrangPhong");
//				Phong phong = new Phong(maPhong, tenPhong, loaiPhong, trangThaiPhong, ngayTaoPhong, viTriPhong, ghiChu,
//						tinhTrangPhong);
//
//				danhSachPhong.add(phong);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				statement.close();
//			} catch (Exception e2) {
//				// TODO: handle exception
//				e2.printStackTrace();
//			}
//		}
//		return danhSachPhong;

		try {
			String sql = "SELECT * FROM Phong WHERE maLoaiPhong = ? AND maPhong NOT IN "
					+ "(SELECT maPhong FROM PhieuDatPhong " + "WHERE thoiGianNhanPhong BETWEEN ? AND ?)";
			em.getTransaction().begin();
			List<Phong> danhSachPhong = em.createNativeQuery(sql, Phong.class).setParameter(1, lp)
					.setParameter(2, startTime).setParameter(3, endTime).getResultList();
			em.close();
			return danhSachPhong;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Phong> layDanhSachPhongTheoTrangThai(TrangThaiPhong trangThai) {
//		List<Phong> danhSachPhong = new ArrayList<Phong>();
//		try {
//			ConnectDB.getInstance();
//			Connection con = ConnectDB.getConnection();
//			String sql = "select * from Phong where trangThai = '" + trangThai;
//			Statement statement = con.createStatement();
//			ResultSet rs = statement.executeQuery(sql);
//			while (rs.next()) {
//
//				String maPhong = rs.getString("maPhong");
//				String tenPhong = rs.getString("tenPhong");
//				LoaiPhong loaiPhong = new LoaiPhong(rs.getString("maLoaiPhong"));
//
//				TrangThaiPhong trangThaiPhong = new TrangThaiPhong(rs.getString("maTrangThai"));
//				java.sql.Date ngayTaoPhong = rs.getDate("ngayTaoPhong");
//				String viTriPhong = rs.getString("viTriPhong");
//				String ghiChu = rs.getString("ghiChu");
//				String tinhTrangPhong = rs.getString("tinhTrangPhong");
//				Phong phong = new Phong(maPhong, tenPhong, loaiPhong, trangThaiPhong, ngayTaoPhong, viTriPhong, ghiChu,
//						tinhTrangPhong);
//
//				danhSachPhong.add(phong);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return danhSachPhong;
		
		try {
			String sql = "select * from Phong where trangThai = ?";
			em.getTransaction().begin();
			List<Phong> danhSachPhong = em.createNativeQuery(sql, Phong.class).setParameter(1, trangThai)
					.getResultList();
			em.close();
			return danhSachPhong;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public int soLuongPhongtheoTrangThaiPhong(TrangThaiPhong trangThai) {
//		Connection con = new ConnectDB().getConnection();
//		int dem = 0;
//		String sql = "select count(*) from Phong where trangThai = '" + trangThai + "'";
//		try {
//			PreparedStatement statement = con.prepareStatement(sql);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				dem = rs.getInt("Dem");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return dem;
		
		try {
			String sql = "select count(*) from Phong where trangThai = ?";
			em.getTransaction().begin();
			int dem = (int) em.createNativeQuery(sql).setParameter(1, trangThai).getSingleResult();
			em.close();
			return dem;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int soLuongPhong() {
//		Connection con = new ConnectDB().getConnection();
//		int dem = 0;
//		try {
//			PreparedStatement statement = con.prepareStatement("select count(maPhong) as Dem from Phong");
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				dem = rs.getInt("Dem");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return dem;
		
		try {
			String sql = "select count(maPhong) as Dem from Phong";
			em.getTransaction().begin();
			int dem = (int) em.createNativeQuery(sql).getSingleResult();
			em.close();
			return dem;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public ArrayList<Phong> phanTrangPhong(int fn, int ln) {
//		Connection con = new ConnectDB().getConnection();
//		ArrayList<Phong> danhSachPhong = new ArrayList<Phong>();
//		PreparedStatement statement = null;
//
//		String sql = "select *from(select ROW_NUMBER() over (order by maPhong)as STT,maPhong,tenPhong,maLoaiPhong,maTrangThai,ngayTaoPhong,viTriPhong,ghiChu,tinhTrangPhong from Phong) as PhanTrang where PhanTrang.STT Between ? and ?";
//
//		try {
//			statement = con.prepareStatement(sql);
//			statement.setInt(1, fn);
//			statement.setInt(2, ln);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				String maPhong = rs.getString("maPhong");
//				String tenPhong = rs.getString("tenPhong");
//				LoaiPhong loaiPhong = new LoaiPhong(rs.getString("maLoaiPhong"));
//
//				TrangThaiPhong trangThaiPhong = new TrangThaiPhong(rs.getString("maTrangThai"));
//				java.sql.Date ngayTaoPhong = rs.getDate("ngayTaoPhong");
//				String viTriPhong = rs.getString("viTriPhong");
//				String ghiChu = rs.getString("ghiChu");
//				String tinhTrangPhong = rs.getString("tinhTrangPhong");
//				Phong phong = new Phong(maPhong, tenPhong, loaiPhong, trangThaiPhong, ngayTaoPhong, viTriPhong, ghiChu,
//						tinhTrangPhong);
//
//				danhSachPhong.add(phong);
//			}
//			statement.close();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//
//		}
//
//		return danhSachPhong;
		
		try {
			String sql = "select *from(select ROW_NUMBER() over (order by maPhong)as STT,maPhong,tenPhong,maLoaiPhong,maTrangThai,ngayTaoPhong,viTriPhong,ghiChu,tinhTrangPhong from Phong) as PhanTrang where PhanTrang.STT Between ? and ?";
			em.getTransaction().begin();
			ArrayList<Phong> danhSachPhong = (ArrayList<Phong>) em.createNativeQuery(sql, Phong.class)
					.setParameter(1, fn).setParameter(2, ln).getResultList();
			em.close();
			return danhSachPhong;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Phong> timPhongTheoLau(String floor) {
//		Phong phong = null;
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//
//		ArrayList<Phong> dsPhong = new ArrayList<>();
//
//		try {
//			String sql = "SELECT * FROM phong WHERE viTriPhong = ?";
//			statement = con.prepareStatement(sql);
//			statement.setString(1, floor);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				String maPhong = rs.getString("maPhong");
//				String tenPhong = rs.getString("tenPhong");
//				LoaiPhong loaiPhong = new LoaiPhong(rs.getString("maLoaiPhong"));
//				TrangThaiPhong trangThaiPhong = new TrangThaiPhong(rs.getString("maTrangThai"));
//				java.sql.Date ngayTaoPhong = rs.getDate("ngayTaoPhong");
//				String viTriPhong = rs.getString("viTriPhong");
//				String ghiChu = rs.getString("ghiChu");
//				String tinhTrangPhong = rs.getString("tinhTrangPhong");
//				phong = new Phong(maPhong, tenPhong, loaiPhong, trangThaiPhong, ngayTaoPhong, viTriPhong, ghiChu,
//						tinhTrangPhong);
//				dsPhong.add(phong);
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
//		return dsPhong;
		
		try {
			String sql = "SELECT * FROM phong WHERE viTriPhong = ?";
			em.getTransaction().begin();
			ArrayList<Phong> dsPhong = (ArrayList<Phong>) em.createNativeQuery(sql, Phong.class).setParameter(1, floor)
					.getResultList();
			em.close();
			return dsPhong;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Phong> timDSPhongWhenSelectCBO(String floor, String maTrangThai) {
//		Phong phong = null;
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//
//		ArrayList<Phong> dsPhong = new ArrayList<>();
//		String sql = "SELECT * FROM Phong WHERE 1 = 1";
//		if (floor != null && !floor.isEmpty() && !floor.equals("Tất cả")) {
//			sql += " AND viTriPhong = ?";
//		}
//
//		if (maTrangThai != null && !maTrangThai.isEmpty() && !maTrangThai.equals("Tất cả")) {
//			sql += " AND maTrangThai = ?";
//		}
//
//		try {
//			statement = con.prepareStatement(sql);
//			int parameterIndex = 1;
//
//			if (floor != null && !floor.isEmpty() && !floor.equals("Tất cả")) {
//				statement.setString(parameterIndex++, floor);
//
//			}
//
//			if (maTrangThai != null && !maTrangThai.isEmpty() && !maTrangThai.equals("Tất cả")) {
//
//				statement.setString(parameterIndex++, maTrangThai);
//
//			}
//
////			statement.setString(1, floor);
////			statement.setString(2, maTrangThai);
////			statement.setString(3, maTinhTrang);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				String maPhong = rs.getString("maPhong");
//				String tenPhong = rs.getString("tenPhong");
//				LoaiPhong loaiPhong = new LoaiPhong(rs.getString("maLoaiPhong"));
//				TrangThaiPhong trangThaiPhong = new TrangThaiPhong(rs.getString("maTrangThai"));
//				java.sql.Date ngayTaoPhong = rs.getDate("ngayTaoPhong");
//				String viTriPhong = rs.getString("viTriPhong");
//				String ghiChu = rs.getString("ghiChu");
//				String tinhTrangPhong = rs.getString("tinhTrangPhong");
//				phong = new Phong(maPhong, tenPhong, loaiPhong, trangThaiPhong, ngayTaoPhong, viTriPhong, ghiChu,
//						tinhTrangPhong);
//				dsPhong.add(phong);
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
//
//		return dsPhong;
			
		try {
			String sql = "SELECT * FROM Phong WHERE 1 = 1";
			if (floor != null && !floor.isEmpty() && !floor.equals("Tất cả")) {
				sql += " AND viTriPhong = ?";
			}
			
			if (maTrangThai != null && !maTrangThai.isEmpty() && !maTrangThai.equals("Tất cả")) {
				sql += " AND maTrangThai = ?";
			}
			
			em.getTransaction().begin();
			ArrayList<Phong> dsPhong = (ArrayList<Phong>) em.createNativeQuery(sql, Phong.class).setParameter(1, floor)
					.setParameter(2, maTrangThai).getResultList();
			
			em.close();
			return dsPhong;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Phong> timdsPhongtheoThoigian(String from, String to) {
//		Phong phong = null;
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//
//		ArrayList<Phong> dsPhong = new ArrayList<>();
//		try {
//			String sql = "SELECT * FROM Phong WHERE ngayTaoPhong >= ? AND ngayTaoPhong <= ?";
//			statement = con.prepareStatement(sql);
//			statement.setString(1, from);
//			statement.setString(2, to);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				String maPhong = rs.getString("maPhong");
//				String tenPhong = rs.getString("tenPhong");
//				LoaiPhong loaiPhong = new LoaiPhong(rs.getString("maLoaiPhong"));
//				TrangThaiPhong trangThaiPhong = new TrangThaiPhong(rs.getString("maTrangThai"));
//				java.sql.Date ngayTaoPhong = rs.getDate("ngayTaoPhong");
//				String viTriPhong = rs.getString("viTriPhong");
//				String ghiChu = rs.getString("ghiChu");
//				String tinhTrangPhong = rs.getString("tinhTrangPhong");
//				phong = new Phong(maPhong, tenPhong, loaiPhong, trangThaiPhong, ngayTaoPhong, viTriPhong, ghiChu,
//						tinhTrangPhong);
//				dsPhong.add(phong);
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
//		return dsPhong;
		
		try {
			String sql = "SELECT * FROM Phong WHERE ngayTaoPhong >= ? AND ngayTaoPhong <= ?";
			em.getTransaction().begin();
			ArrayList<Phong> dsPhong = (ArrayList<Phong>) em.createNativeQuery(sql, Phong.class).setParameter(1, from)
					.setParameter(2, to).getResultList();
			em.close();
			return dsPhong;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Phong> timDSPhongTheoMaPhong(String maP) {
//		Phong phong = null;
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//
//		ArrayList<Phong> dsPhong = new ArrayList<>();
//		try {
//			String sql = "SELECT * FROM Phong WHERE maPhong LIKE ?";
//			statement = con.prepareStatement(sql);
//			statement.setString(1, "%" + maP + "%");
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				String maPhong = rs.getString("maPhong");
//				String tenPhong = rs.getString("tenPhong");
//				LoaiPhong loaiPhong = new LoaiPhong(rs.getString("maLoaiPhong"));
//				TrangThaiPhong trangThaiPhong = new TrangThaiPhong(rs.getString("maTrangThai"));
//				java.sql.Date ngayTaoPhong = rs.getDate("ngayTaoPhong");
//				String viTriPhong = rs.getString("viTriPhong");
//				String ghiChu = rs.getString("ghiChu");
//				String tinhTrangPhong = rs.getString("tinhTrangPhong");
//				phong = new Phong(maPhong, tenPhong, loaiPhong, trangThaiPhong, ngayTaoPhong, viTriPhong, ghiChu,
//						tinhTrangPhong);
//				dsPhong.add(phong);
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
//		return dsPhong;
		
		try {
			String sql = "SELECT * FROM Phong WHERE maPhong LIKE ?";
			em.getTransaction().begin();
			ArrayList<Phong> dsPhong = (ArrayList<Phong>) em.createNativeQuery(sql, Phong.class)
					.setParameter(1, "%" + maP + "%").getResultList();
			em.close();
			return dsPhong;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean capNhat_TrangThaiPhong(String maPh, String trThPh) {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		int n = 0;
//		try {
//			statement = con.prepareStatement("UPDATE Phong SET maTrangThai=? " + " WHERE maPhong=?");
//			statement.setString(2, maPh);
//			statement.setString(1, trThPh);
//
//			n = statement.executeUpdate();
//
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
            String sql = "UPDATE Phong SET maTrangThai=? " + " WHERE maPhong=?";

            em.getTransaction().begin();
            int result = em.createNativeQuery(sql, Phong.class).setParameter(1, trThPh).setParameter(2, maPh)
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
