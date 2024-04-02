package entity;

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
@Table(name = "LoaiNhanVien")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "maLoaiNhanVien")
public class LoaiNhanVien {
	@Id
	@Column(name = "maLoaiNhanVien", columnDefinition = "NCHAR(16)")
	private String maLoaiNhanVien;
	@Column(name = "tenLoaiNhanVien", columnDefinition = "NVARCHAR(40)")
	private String tenLoaiNhanVien;

	public LoaiNhanVien(String maLoaiNhanVien) {
		super();
		this.maLoaiNhanVien = maLoaiNhanVien;
	}
	
}
