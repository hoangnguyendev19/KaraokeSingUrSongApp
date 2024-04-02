package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "ChiTietHoaDon")
@NoArgsConstructor
@Getter
@Setter
@ToString
@IdClass(ChiTietHoaDonId.class)
public class ChiTietHoaDon {
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maHoaDon", columnDefinition = "	NCHAR(15)", nullable = false)
	private HoaDon hoaDon;
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maPhong", columnDefinition = "	NCHAR(16)", nullable = false)
	private Phong phong;

	public ChiTietHoaDon(HoaDon hoaDon, Phong phong) {
		super();
		this.hoaDon = hoaDon;
		this.phong = phong;
	}

	public double thanhTien(float soGioHat) {

		return (double) getPhong().getLoaiPhong().getGiaTien() * soGioHat;

	}

}