package entity;

import java.sql.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "ThongTinDichVu")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "maThongTinDichVu")
public class ThongTinDichVu {
	@Id
	@Column(name = "maThongTinDichVu", columnDefinition = "NCHAR(16)")
	private String maThongTinDichVu;
	private int soLuong;
	private int soLuongDaSuDung;
	private Date ngayNhap;
	private Date ngayHetHan;
	@Column(name = "moTa", columnDefinition = "NVARCHAR(40)")
	private String moTa;
	@Column(name = "hinhAnh", columnDefinition = "VARCHAR(255)")
	private String hinhAnh;
	
	public ThongTinDichVu(String maThongTinDichVu) {
		super();
		this.maThongTinDichVu = maThongTinDichVu;
	}
	
	public int tinhSoLuongConLai() {
		if (getSoLuong() - getSoLuongDaSuDung() > 0)
			return getSoLuong() - getSoLuongDaSuDung();
		else {
			return 0;
		}
	}
	
}