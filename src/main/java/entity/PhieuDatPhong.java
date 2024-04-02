package entity;

import java.sql.Timestamp;

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
@Table(name = "PhieuDatPhong")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "maPhieuDat")
public class PhieuDatPhong {
	@Id
	@Column(name = "maPhieuDat", columnDefinition = "NCHAR(15)")
	private String maPhieuDat;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maPhong", columnDefinition = "NCHAR(16)", nullable = false)
	private Phong phong;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maNhanVien", columnDefinition = "NCHAR(16)", nullable = false)
	private NhanVien nhanVien;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maKhachHang", columnDefinition = "NCHAR(16)", nullable = false)
	private KhachHang khachHang;
	private Timestamp thoiGianDatPhong;
	private Timestamp thoiGianNhanPhong;
	private double tienCoc;
	@Column(name = "trangThai", columnDefinition = "NVARCHAR(40)")
	private String trangThai;
	@Column(name = "moTa", columnDefinition = "NVARCHAR(100)")
	private String moTa;
	
	public PhieuDatPhong(String maPhieuDat, Phong phong, NhanVien nhanVien, KhachHang khachHang, java.util.Date tgDatPhong,
			java.sql.Timestamp tgNhanPhong, double tienCoc, String trangThai, String moTa) {
		super();
		this.maPhieuDat = maPhieuDat;
		this.phong = phong;
		this.nhanVien = nhanVien;
		this.khachHang = khachHang;
		this.thoiGianDatPhong =  (java.sql.Timestamp) tgDatPhong;
		this.thoiGianNhanPhong =  (java.sql.Timestamp) tgNhanPhong;
		this.tienCoc = tienCoc;
		this.trangThai = trangThai;
		this.moTa = moTa;
	}
	
	public PhieuDatPhong(String maPhieuDat) {
		super();
		this.maPhieuDat = maPhieuDat;
	}
	
}