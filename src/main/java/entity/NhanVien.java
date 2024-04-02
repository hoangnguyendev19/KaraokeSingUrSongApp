package entity;

import java.sql.Date;

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
@Table(name = "NhanVien")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "maNhanVien")
public class NhanVien {
	@Id
	@Column(name = "maNhanVien", columnDefinition = "NCHAR(16)")
	private String maNhanVien;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maLoaiNhanVien", columnDefinition = "NCHAR(16)", nullable = false)
	private LoaiNhanVien loaiNhanVien;
	@Column(name = "hoTen", columnDefinition = "NVARCHAR(40)")
	private String hoTen;
	private boolean gioiTinh;
	private Date ngaySinh;
	@Column(name = "soDienThoai", columnDefinition = "NCHAR(10)")
	private String soDienThoai;
	@Column(name = "CCCD", columnDefinition = "NCHAR(12)")
	private String CCCD;
	@Column(name = "diaChi", columnDefinition = "NVARCHAR(40)")
	private String diaChi;
	@Column(name = "trangThai", columnDefinition = "NVARCHAR(40)")
	private String trangThai;
	@Column(name = "anhThe", columnDefinition = "NCHAR(100)")
	private String anhThe;
	
	public NhanVien(String maNhanVien) {
		super();
		this.maNhanVien = maNhanVien;
	}

}
