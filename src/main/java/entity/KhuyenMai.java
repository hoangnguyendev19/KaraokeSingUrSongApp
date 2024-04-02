package entity;

import java.sql.Date;

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
@Table(name = "KhuyenMai")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "maKhuyenMai")
public class KhuyenMai {
	@Id
	@Column(name = "maKhuyenMai", columnDefinition = "NCHAR(16)")
	private String maKhuyenMai;
	@Column(name = "tenKhuyenMai", columnDefinition = "NVARCHAR(40)")
	private String tenKhuyenMai;
	@Column(name = "maGiamGia", columnDefinition = "NCHAR(16)")
	private String maGiamGia;
	private Date ngayBatDau;
	private Date ngayKetThuc;
	private int tongSoLuong;
	private double chietKhau;
	@Column(name = "moTa", columnDefinition = "NVARCHAR(40)")
	private String moTa;
	
	public KhuyenMai(String maKhuyenMai) {
		super();
		this.maKhuyenMai = maKhuyenMai;
	}
	
}