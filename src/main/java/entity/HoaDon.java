package entity;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "HoaDon")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "maHoaDon")
public class HoaDon {
    @Id
    @Column(name = "maHoaDon", columnDefinition = "NCHAR(15)")
	private String maHoaDon;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maKhachHang", columnDefinition = "NCHAR(16)", nullable = false)
	private KhachHang khachHang;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maNhanVien", columnDefinition = "NCHAR(16)", nullable = false)
	private NhanVien nhanVien;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maPhieuDat", columnDefinition = "NCHAR(15)", nullable = false)
	private PhieuDatPhong phieuDatPhong;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maKhuyenMai", columnDefinition = "NCHAR(16)", nullable = false)
	private KhuyenMai khuyenMai;
	private Timestamp ngayLap;
	@Column(name = "trangThai", columnDefinition = "NVARCHAR(40)")
	private String trangThai;
	private Timestamp thoiGianKetThuc;

	public HoaDon(String maHoaDon) {
		super();
		this.maHoaDon = maHoaDon;
	}

	public float tinhGioHat() {
		// Lấy Timestamp kết thúc từ đối tượng HoaDon
		long gioHat = 0;
		float result;
		if (getThoiGianKetThuc() != null) {
			Timestamp thoiGianKetThuc = getThoiGianKetThuc();
			// Lấy Timestamp đặt phòng từ đối tượng PhieuDatPhong
			Timestamp thoiGianNhanPhong = getNgayLap();

			// Chuyển Timestamp thành LocalDateTime

			LocalDateTime ketThuc = thoiGianKetThuc.toLocalDateTime();
			LocalDateTime batDau = thoiGianNhanPhong.toLocalDateTime();

			// Tính khoảng thời gian (duration) giữa hai thời điểm
			Duration duration = Duration.between(batDau, ketThuc);

			// Chuyển khoảng thời gian thành số giờ
			gioHat = duration.toMinutes();
			result = (float) gioHat / 60;
		} else {
			// Lấy thời gian hiện tại
			long currentTimeMillis = System.currentTimeMillis();

			// Tạo đối tượng Timestamp từ thời gian hiện tại
			Timestamp currentTimestamp = new Timestamp(currentTimeMillis);
			Timestamp thoiGianNhanPhong = getNgayLap();

			Duration duration = Duration.between(thoiGianNhanPhong.toLocalDateTime(),
					currentTimestamp.toLocalDateTime());
			gioHat = duration.toMinutes();

			// In ra timestamp

			result = (float) gioHat / 60;
		}
		return result;// Trả về giờ hát dưới dạng số nguyên

	}

	public long tinhTienPhong(ArrayList<ChiTietHoaDon> dsCTHD) {
		long sum = 0;
		for (ChiTietHoaDon chiTietHoaDon : dsCTHD) {
			sum += chiTietHoaDon.thanhTien(tinhGioHat());
		}
		return sum;
	}

	public double tinhTienDichVu(ArrayList<ChiTietDichVu> dsCTDV) {
		double sum = 0;
		if (dsCTDV == null)
			return 0;
		for (ChiTietDichVu chiTietDichVu : dsCTDV) {
			sum += chiTietDichVu.thanhTien();
		}
		return sum;
	}

	public double tinhTongTien(ArrayList<ChiTietHoaDon> dsCTHD, ArrayList<ChiTietDichVu> dsCTDV) {
		if (dsCTDV == null)
			return 0;
		int thoiGian = 0;
		double sum = tinhTienDichVu(dsCTDV) + tinhTienPhong(dsCTHD);
		if (getPhieuDatPhong() != null) {
			sum = sum - getPhieuDatPhong().getTienCoc();
		}

		return sum * 1.05;
	}

}