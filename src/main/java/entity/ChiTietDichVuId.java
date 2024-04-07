package entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"hoaDon", "dichVu", "phong"})
public class ChiTietDichVuId implements Serializable{
	private HoaDon hoaDon;
	private DichVu dichVu;
	private Phong phong;
}
