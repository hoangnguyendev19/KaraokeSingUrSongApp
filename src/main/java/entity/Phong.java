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
@Table(name = "Phong")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "maPhong")
public class Phong {
	@Id
	@Column(name = "maPhong", columnDefinition = "NCHAR(16)")
	private String maPhong;
	@Column(name = "tenPhong", columnDefinition = "NVARCHAR(40)")
	private String tenPhong;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maLoaiPhong", columnDefinition = "NCHAR(16)", nullable = false)
	private LoaiPhong loaiPhong;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maTrangThai", columnDefinition = "NCHAR(16)", nullable = false)
	private TrangThaiPhong trangThaiPhong;
	private Date ngayTaoPhong;
	@Column(name = "viTriPhong", columnDefinition = "NCHAR(8)")
	private String viTriPhong;
	@Column(name = "ghiChu", columnDefinition = "NVARCHAR(100)")
	private String ghiChu;
	@Column(name = "tinhTrangPhong", columnDefinition = "NVARCHAR(100)")
	private String tinhTrangPhong;
	
	public Phong(String maPhong) {
		super();
		this.maPhong = maPhong;
	}
	
	public Phong(String maPhong, String tenPhong, TrangThaiPhong trangThaiPhong, String viTriPhong, String ghiChu,
			String tinhTrangPhong) {
		super();
		this.maPhong = maPhong;
		this.tenPhong = tenPhong;
		this.trangThaiPhong = trangThaiPhong;
		this.viTriPhong = viTriPhong;
		this.ghiChu = ghiChu;
		this.tinhTrangPhong = tinhTrangPhong;
		
	}
}
