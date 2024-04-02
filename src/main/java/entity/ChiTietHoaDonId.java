package entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"hoaDon", "phong"})
public class ChiTietHoaDonId implements Serializable {
	private HoaDon hoaDon;
	private Phong phong;
}
