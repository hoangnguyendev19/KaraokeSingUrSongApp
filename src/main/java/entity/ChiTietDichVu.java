package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "ChiTietDichVu")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@IdClass(ChiTietDichVuId.class)
public class ChiTietDichVu {
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maHoaDon", columnDefinition = "	NCHAR(15)", nullable = false)
	private HoaDon hoaDon;
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maDichVu", columnDefinition = "	NCHAR(15)", nullable = false)
	private DichVu dichVu;
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maPhong", columnDefinition = "	NCHAR(16)", nullable = false)
    private Phong phong;
	private int soLuong;

	public ChiTietDichVu(HoaDon hoaDon, DichVu dichVu, int soLuong) {
		super();
		this.hoaDon = hoaDon;
		this.dichVu = dichVu;
		this.soLuong = soLuong;
	}

	public ChiTietDichVu(HoaDon hoaDon, DichVu dichVu) {
		super();
		this.hoaDon = hoaDon;
		this.dichVu = dichVu;
	}

	public double thanhTien() {
		return (double) getSoLuong() * getDichVu().getDonGia();

	}
	
}