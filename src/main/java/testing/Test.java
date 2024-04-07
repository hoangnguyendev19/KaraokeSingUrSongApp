package testing;

import java.util.ArrayList;

import dao.LoaiPhong_DAO;
import dao.TrangThaiPhong_DAO;
import entity.LoaiPhong;
import entity.TrangThaiPhong;

public class Test {
	public static void main(String[] args) {
		
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SingUrSong_vnew");
		
		LoaiPhong_DAO DAO_LP = new LoaiPhong_DAO();
		TrangThaiPhong_DAO DAO_TTTP = new TrangThaiPhong_DAO();
		ArrayList<LoaiPhong> dsLoaiPhong = DAO_LP.layTatCaLoaiPhong();
		ArrayList<TrangThaiPhong> dsTrangThai = DAO_TTTP.layTatCaTrangThaiPhong();
		
		System.out.println("Danh sách loại phòng:");
		for (LoaiPhong loaiPhong : dsLoaiPhong) {
			System.out.println(loaiPhong.getMaLoaiPhong() + " - " + loaiPhong.getTenLoaiPhong());
		}
		
		System.out.println("Danh sách trạng thái phòng:");
		for (TrangThaiPhong trangThaiPhong : dsTrangThai) {
			System.out.println(trangThaiPhong.getMaTrangThai() + " - " + trangThaiPhong.getTenTrangThai());
		}
	}
}
