package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TaiKhoan")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "nhanVien")
public class TaiKhoan {
	@Id
	@OneToOne
	@JoinColumn(name = "maNhanVien", columnDefinition = "NCHAR(16)")
	private NhanVien nhanVien;
	@Column(name = "tenDangNhap", columnDefinition = "NCHAR(16)")
	private String tenDangNhap;
	@Column(name = "matKhau", columnDefinition = "NCHAR(40)")
	private String matKhau;
	private boolean trangThai;
	@Column(name = "email", columnDefinition = "VARCHAR(255)")
	private String email;

	public TaiKhoan(NhanVien nhanVien) {
		super();
		this.nhanVien = nhanVien;
	}

}