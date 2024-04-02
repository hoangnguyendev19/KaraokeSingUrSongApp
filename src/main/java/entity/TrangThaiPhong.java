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
@Table(name = "TrangThaiPhong")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "maTrangThai")
public class TrangThaiPhong {
	@Id
	@Column(name = "maTrangThai", columnDefinition = "NCHAR(16)")
	private String maTrangThai;
	@Column(name = "tenTrangThai", columnDefinition = "NVARCHAR(40)")
	private String tenTrangThai;
	
	public TrangThaiPhong(String maTrangThai) {
		super();
		this.maTrangThai = maTrangThai;
	}
	
}
