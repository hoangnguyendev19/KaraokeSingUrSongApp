package entity;

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
@Table(name = "LoaiPhong")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "maLoaiPhong")
public class LoaiPhong {
	@Id
	@Column(name = "maLoaiPhong", columnDefinition = "NCHAR(16)")
	private String maLoaiPhong;
	@Column(name = "tenLoaiPhong", columnDefinition = "NVARCHAR(40)")
	private String tenLoaiPhong;
	private int soLuongKhachToiDa;
	private double giaTien;
	@Column(name = "hinhAnh", columnDefinition = "NCHAR(255)")
	private String hinhAnh;
	@Column(name = "moTa", columnDefinition = "NVARCHAR(100)")
	private String moTa;

	public LoaiPhong(String maLoaiPhong) {
		super();
		this.maLoaiPhong = maLoaiPhong;
	}
	
}
