package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.DichVu;
import entity.ThongTinDichVu;

public class ThongTinDichVu_DAO {

	public ThongTinDichVu_DAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<ThongTinDichVu> layTatCaThongTinDichVu() {
		ArrayList<ThongTinDichVu> danhSachThongTinDichVu = new ArrayList<ThongTinDichVu>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM ThongTinDichVu";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maThongTinDichVu = rs.getString("maThongTinDichVu");
				int soLuong = rs.getInt("soLuong");
				int soLuongDaSuDung = rs.getInt("soLuongDaSuDung");
				java.sql.Date ngayNhap = rs.getDate("ngayNhap");
				java.sql.Date ngayHetHan = rs.getDate("ngayHetHan");
				String moTa = rs.getString("moTa");
				String hinhAnh = rs.getString("hinhAnh");
				ThongTinDichVu thongTinDichVu = new ThongTinDichVu(maThongTinDichVu, soLuong, soLuongDaSuDung,
						ngayNhap, ngayHetHan, moTa, hinhAnh);
				danhSachThongTinDichVu.add(thongTinDichVu);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return danhSachThongTinDichVu;
	}

	public ThongTinDichVu timThongTinDichVu_TheoMaThongTinDichVu(String maTTDichVu) {
		ThongTinDichVu thongTinDichVu = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT * FROM ThongTinDichVu WHERE maThongTinDichVu = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, maTTDichVu);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maThongTinDichVu = rs.getString("maThongTinDichVu");
				int soLuong = rs.getInt("soLuong");
				int soLuongDaSuDung = rs.getInt("soLuongDaSuDung");
				java.sql.Date ngayNhap = rs.getDate("ngayNhap");
				java.sql.Date ngayHetHan = rs.getDate("ngayHetHan");
				String moTa = rs.getString("moTa");
				String hinhAnh = rs.getString("hinhAnh");
				thongTinDichVu = new ThongTinDichVu(maThongTinDichVu, soLuong, soLuongDaSuDung, ngayNhap,
						ngayHetHan, moTa, hinhAnh);
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				statement.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return thongTinDichVu;
	}

	

	public boolean taoThongTinDichVu(ThongTinDichVu thongTinDichVu) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = con.prepareStatement("INSERT INTO ThongTinDichVu values(?,?,?,?,?,?,?)");
			statement.setString(1, thongTinDichVu.getMaThongTinDichVu());
			statement.setInt(2, thongTinDichVu.getSoLuong());
			statement.setInt(3, thongTinDichVu.getSoLuongDaSuDung());
			statement.setDate(4, thongTinDichVu.getNgayNhap());
			statement.setDate(5, thongTinDichVu.getNgayHetHan());
			statement.setString(6, thongTinDichVu.getMoTa());
			statement.setString(7, thongTinDichVu.getHinhAnh());
			n = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return n > 0;
	}

	public int capNhatThongTinDichVu(ThongTinDichVu thongTinDichVu) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = con.prepareStatement(
					"UPDATE ThongTinDichVu SET  soLuong = ?, soLuongDaSuDung = ?, ngayNhap = ?, ngayHetHan = ?, moTa = ?, hinhAnh = ?"
							+ " WHERE maThongTinDichVu = ?");
			statement.setInt(1, thongTinDichVu.getSoLuong());
			statement.setInt(2, thongTinDichVu.getSoLuongDaSuDung());
			statement.setDate(3, thongTinDichVu.getNgayNhap());
			statement.setDate(4, thongTinDichVu.getNgayHetHan());
			statement.setString(5, thongTinDichVu.getMoTa());
			statement.setString(6, thongTinDichVu.getHinhAnh());
			statement.setString(7, thongTinDichVu.getMaThongTinDichVu());
			n = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

		return n;
	}


	public boolean xoaThongTinDichVu(ThongTinDichVu thongTinDichVu) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = con.prepareStatement("DELETE FROM ThongTinDichVu" + " WHERE maThongTinDichVu = ?");
			statement.setString(1, thongTinDichVu.getMaThongTinDichVu());
			n = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return n > 0;
	}
}
