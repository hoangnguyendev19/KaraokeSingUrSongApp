package other;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import entity.ChiTietDichVu;
import entity.ChiTietHoaDon;
import entity.DichVu;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.LoaiNhanVien;
import entity.LoaiPhong;
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.Phong;
import entity.TaiKhoan;
import entity.ThongTinDichVu;
import entity.TrangThaiPhong;

public class ConvertObjToEntity {
	
	public static ArrayList<HoaDon> convertToHoaDonList(List<Object> listObj) {
		ArrayList<HoaDon> list = new ArrayList<>();
		for (Object object : listObj) {
			HoaDon hd = (HoaDon) object;

			list.add(hd);
		}
		return list;
	}
	
	public static ArrayList<DichVu> convertToDichVuList(List<Object> listObj) {
		ArrayList<DichVu> list = new ArrayList<>();
		for (Object object : listObj) {
			DichVu dv = (DichVu) object;

			list.add(dv);
		}
		
		return list;
	}
	
	public static ArrayList<ChiTietDichVu> convertToChiTietDichVuList(List<Object> listObj) {
		ArrayList<ChiTietDichVu> list = new ArrayList<>();
		for (Object obj : listObj) {
			ChiTietDichVu ctDichVu = (ChiTietDichVu) obj;
			list.add(ctDichVu);
		}
		
		return list;
	}
	
	public static ArrayList<ChiTietHoaDon> convertToChiTietHoaDonList(List<Object> listObj) {
		ArrayList<ChiTietHoaDon> list = new ArrayList<>();
		for (Object obj : listObj) {
			ChiTietHoaDon cthd = (ChiTietHoaDon) obj;

			list.add(cthd);
		}

		return list;
	}
	
	public static ArrayList<KhachHang> convertToKhachHangList(List<Object> listObj) {
		ArrayList<KhachHang> list = new ArrayList<>();
		for (Object obj : listObj) {
			KhachHang kh = (KhachHang) obj;

			list.add(kh);
		}
		
		return list;
	}
	
	public static ArrayList<KhuyenMai> convertToKhuyenMaiList(List<Object> listObj) {
		ArrayList<KhuyenMai> list = new ArrayList<>();
		for (Object obj : listObj) {
			KhuyenMai km = (KhuyenMai) obj;

			list.add(km);
		}

		return list;
	}
	
	public static ArrayList<LoaiNhanVien> convertToLoaiNhanVienList(List<Object> listObj) {
		ArrayList<LoaiNhanVien> list = new ArrayList<>();
		for (Object obj : listObj) {
			LoaiNhanVien lNV = (LoaiNhanVien) obj;

			list.add(lNV);
		}

		return list;
	}
	
	public static ArrayList<LoaiPhong> convertToLoaiPhongList(List<Object> listObj) {
		ArrayList<LoaiPhong> list = new ArrayList<>();
		for (Object obj : listObj) {
			LoaiPhong lP = (LoaiPhong) obj;

			list.add(lP);
		}

		return list;
	}
	
	public static ArrayList<NhanVien> convertToNhanVienList(List<Object> listObj) {
		ArrayList<NhanVien> list = new ArrayList<>();
		for (Object obj : listObj) {
			NhanVien nv = (NhanVien) obj;

			list.add(nv);
		}

		return list;
	}
	
	public static ArrayList<PhieuDatPhong> convertToPhieuDatPhongList(List<Object> listObj) {
		ArrayList<PhieuDatPhong> list = new ArrayList<>();
		for (Object obj : listObj) {
			PhieuDatPhong pdp = (PhieuDatPhong) obj;

			list.add(pdp);
		}

		return list;
	}
	
	public static ArrayList<Phong> convertToPhongList(List<Object> listObj) {
		ArrayList<Phong> list = new ArrayList<>();
		for (Object obj : listObj) {
			Phong p = (Phong) obj;

			list.add(p);
		}

		return list;
	}
	
	public static ArrayList<TaiKhoan> convertToTaiKhoanList(List<Object> listObj) {
		ArrayList<TaiKhoan> list = new ArrayList<>();
		for (Object obj : listObj) {
			TaiKhoan tk = (TaiKhoan) obj;

			list.add(tk);
		}

		return list;
	}
	
	public static ArrayList<ThongTinDichVu> convertToThongTinDichVuList(List<Object> listObj) {
		ArrayList<ThongTinDichVu> list = new ArrayList<>();
		for (Object obj : listObj) {
			ThongTinDichVu ttdv = (ThongTinDichVu) obj;

			list.add(ttdv);
		}

		return list;
	}

	public static ArrayList<TrangThaiPhong> convertToTrangThaiPhongList(List<Object> listObj) {
		ArrayList<TrangThaiPhong> list = new ArrayList<>();
		for (Object obj : listObj) {
			TrangThaiPhong ttp = (TrangThaiPhong) obj;

			list.add(ttp);
		}

		return list;
	}
}
