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
@Table(name = "KhachHang")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "maKhachHang")
public class KhachHang {
	@Id
	@Column(name = "maKhachHang", columnDefinition = "NCHAR(16)")
	private String maKhachHang;
	@Column(name = "hoTen", columnDefinition = "NVARCHAR(40)")
	private String hoTen;
	private boolean gioiTinh;
	private Date ngaySinh;
	@Column(name = "diaChi", columnDefinition = "NCHAR(40)")
	private String diaChi;
	@Column(name = "soDienThoai", columnDefinition = "NCHAR(10)")
	private String soDienThoai;
	private int diemThuong;
	@Column(name = "ghiChu", columnDefinition = "NVARCHAR(40)")
	private String ghiChu;

	public KhachHang(String maKhachHang) {
		super();
		this.maKhachHang = maKhachHang;
	}

}