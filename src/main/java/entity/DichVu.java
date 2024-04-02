package entity;

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
@Table(name = "DichVu")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "maDichVu")
public class DichVu {
	@Id	
	@Column(name = "maDichVu", columnDefinition = "NCHAR(15)")
	private String maDichVu;
	@Column(name = "tenDichVu", columnDefinition = "NVARCHAR(40)")
	private String tenDichVu;
	@Column(name = "donViTinh", columnDefinition = "NVARCHAR(12)")
	private String donViTinh;
	private double donGia;
	private boolean trangThai;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maThongTinDichVu", columnDefinition = "NCHAR(16)", nullable = false)
	private ThongTinDichVu thongTinDichVu;

	public DichVu(String maDichVu, String tenDichVu, double donGia) {
		super();
		this.maDichVu = maDichVu;
		this.tenDichVu = tenDichVu;
		this.donGia = donGia;
	}
	
	public DichVu(String maDichVu) {
		super();
		this.maDichVu = maDichVu;
	}

}