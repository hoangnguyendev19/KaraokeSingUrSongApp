package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Table;

import dao.ChiTietDichVu_DAO;
import dao.ChiTietHoaDon_DAO;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.LoaiPhong_DAO;
import dao.NhanVien_DAO;
import dao.PhieuDatPhong_DAO;
import dao.Phong_DAO;
import dao.TrangThaiPhong_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.LoaiPhong;
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.Phong;
import entity.TrangThaiPhong;
import other.HelpRamDomMa;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import java.awt.Font;

/**
 * CardPhong
 * 
 * 
 */
public class JPanel_CardPhong extends JPanel {
	private static ChiTietHoaDon_DAO DAO_CTHD;
	private Phong phong;
	private int width = 150;
	private int height = 150;

	private String hexColor_Blue1 = "#054A91";
	private String hexColor_Blue2 = "#3E7CB1";
	private String hexColor_Blue3 = "#81A4CD";
	private String hexColor_Blue4 = "#DBE4EE";

	private LoaiPhong loaiP;
	private boolean selectDatPhong;
	private JCheckBox cbox_DatPhong;
	private DefaultTableModel model;
	private JTable table;
	private HoaDon hoaDon;
	private KhachHang khachHang;
	private PhieuDatPhong_DAO DAO_PDP;
	private NhanVien_DAO DAO_NV;
	private Phong_DAO DAO_P;
	private KhachHang_DAO DAO_KH;
	private LoaiPhong_DAO DAO_LP;
	private TrangThaiPhong_DAO DAO_TTP;
	private NhanVien nv;
	private PhieuDatPhong phieu;
	private ArrayList<PhieuDatPhong> dsPhieuDatPhong;
	private ArrayList<Phong> dsPhong;
	private TrangThaiPhong_DAO dao_TrangThaiPhong;
	private String tenNV;
	private KhachHang kh;
	private String tenKH;
	private String sdtKH;
	private HoaDon_DAO DAO_HD;
	private HoaDon hd;
	private ArrayList<HoaDon> dsHoaDon;
	private ArrayList<ChiTietHoaDon> dsChiTietHoaDon;
	private final DecimalFormat dcf = new DecimalFormat("#,##0 VND");
	/**
	 * @param phong
	 */

	public JPanel_CardPhong(Phong phong, DefaultTableModel model, JTable table) {
		this.phong = phong;
		this.model = model;
		this.table = table;
		try {

			LoaiPhong_DAO DAO_LP = new LoaiPhong_DAO();
			loaiP = DAO_LP.layLoaiPhong_TheoMaLoaiPhong(phong.getLoaiPhong().getMaLoaiPhong());

		} catch (Exception e) {
			e.printStackTrace();
		}
		setPreferredSize(new Dimension(width, height));

		/**
		 * tenPhong
		 */
		setBackground(Color.decode(hexColor_Blue4));
		setLayout(null);

		JLabel nameLabel = new JLabel(phong.getMaPhong());
		nameLabel.setBackground(new Color(5, 74, 145));
		nameLabel.setBounds(54, 59, 96, 43);
		nameLabel.setForeground(Color.decode(hexColor_Blue1));
		nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(nameLabel);

		ImageIcon imgVIP = new ImageIcon(JFrame_DangNhap.class.getResource("/icon/vip.png"));

		Image scaled_imgVIP = imgVIP.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		imgVIP = new ImageIcon(scaled_imgVIP);

		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(imgVIP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(120, 0, 30, 30);
		if (phong.getLoaiPhong().getMaLoaiPhong().trim().equals("ORD1")
				|| phong.getLoaiPhong().getMaLoaiPhong().trim().equals("ORD2")) {
			add(lblNewLabel);
		}
		cbox_DatPhong = new JCheckBox("Đặt phòng này");
		cbox_DatPhong.setFont(new Font("Segoe UI", Font.BOLD, 12));
		cbox_DatPhong.setForeground(new Color(51, 153, 255));
		cbox_DatPhong.setBounds(0, 6, 114, 21);

		if (phong.getTrangThaiPhong().getMaTrangThai().trim().equals("VC")) {
			add(cbox_DatPhong);
			cbox_DatPhong.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {

					if (e.getStateChange() == ItemEvent.SELECTED) {
						selectDatPhong = true;
						if (kiemTraTrungMaPhongTrongDSPhong(phong.getMaPhong().toString().trim(), table)) {
							model.addRow(new Object[] { table.getRowCount() + 1, phong.getMaPhong().toString(),
									phong.getTenPhong().toString(), Double.toString(loaiP.getGiaTien()) });
						}
					} else {
						selectDatPhong = false;
					}
					if (e.getStateChange() != ItemEvent.SELECTED) {
						cbox_DatPhong.setSelected(false);
						String maP = getPhong().getMaPhong();
						for (int i = 0; i < table.getRowCount(); i++) {
							if (maP.trim().equals(table.getValueAt(i, 1).toString().trim())) {
								model.removeRow(i);
							}
						}
//						model.removeRow(ERROR)
					}
				}
			});
		}
		addMouseListener((MouseListener) new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					showPopupMenu(e);
				}
			}

		});

	}

	public boolean isSelectDatPhong() {
		return selectDatPhong;
	}

	public void setSelectDatPhong(boolean selectDatPhong) {
		this.selectDatPhong = selectDatPhong;
	}

	public Phong getPhong() {
		return phong;
	}

	public void setPhong(Phong phong) {
		this.phong = phong;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		TrangThaiPhong trangThaiP = null;

		TrangThaiPhong_DAO DAO_TTP = new TrangThaiPhong_DAO();

		try {
			trangThaiP = DAO_TTP.timTrangThaiPhong_TheoMaTrangThai(phong.getTrangThaiPhong().getMaTrangThai());
		} catch (Exception e) {
			// TODO: handle exception
		}

		// Trong
		if (phong.getTrangThaiPhong().getMaTrangThai().trim().equals("VC")) {
			g.setColor(Color.decode("#00a65a"));
		}

		// Dang hat
		if (phong.getTrangThaiPhong().getMaTrangThai().trim().equals("OC")) {
			g.setColor(Color.decode("#ff6868"));
		}
		// Dat truoc
		if (phong.getTrangThaiPhong().getMaTrangThai().trim().equals("OCP")) {
			g.setColor(Color.decode("#3c8dbc"));
		}
		// Dang sua chua
		if (phong.getTrangThaiPhong().getMaTrangThai().trim().equals("OOO")) {
			g.setColor(Color.decode("#b0bec5"));
		}
		g.fillRect(0, getHeight() - 20, getWidth(), 20);
		g.setColor(Color.white);
		g.drawString(trangThaiP.getTenTrangThai(), 10, getHeight() - 5);
	}

	private void showPopupMenu(MouseEvent e) {

		DAO_PDP = new PhieuDatPhong_DAO();
		DAO_NV = new NhanVien_DAO();
		DAO_P = new Phong_DAO();
		DAO_KH = new KhachHang_DAO();
		DAO_LP = new LoaiPhong_DAO();
		DAO_TTP = new TrangThaiPhong_DAO();
		DAO_HD = new HoaDon_DAO();
		DAO_CTHD = new ChiTietHoaDon_DAO();

		nv = new NhanVien();
		phieu = new PhieuDatPhong();
		hd = new HoaDon();

		dsPhieuDatPhong = DAO_PDP.layTatCaPhieuDatPhong();
		dsHoaDon = DAO_HD.layTatCaHoaDon();
		dsPhong = DAO_P.layTatCaPhong();
		dsChiTietHoaDon = DAO_CTHD.layTatCaChiTietHoaDon();
		dao_TrangThaiPhong = new TrangThaiPhong_DAO();

		JPopupMenu menu = new JPopupMenu();

		JMenuItem xemThongTinMenuItem = new JMenuItem("Xem thông tin phòng");
		JMenuItem chuyenPhongMenuItem = new JMenuItem("Chuyển phòng");
		JMenuItem themDichVuItem = new JMenuItem("Cập nhật dịch vụ");
		JMenuItem traPhongMenuItem = new JMenuItem("Trả phòng");
		JMenuItem nhanPhongMenuItem = new JMenuItem("Nhận phòng");

		if (phong.getTrangThaiPhong().getMaTrangThai().trim().equals("OOO")) {
			themDichVuItem.setEnabled(false);
			chuyenPhongMenuItem.setEnabled(false);
			traPhongMenuItem.setEnabled(false);
			nhanPhongMenuItem.setEnabled(false);
		}

		if (phong.getTrangThaiPhong().getMaTrangThai().trim().equals("VC")) {
			themDichVuItem.setEnabled(false);
			chuyenPhongMenuItem.setEnabled(false);
			traPhongMenuItem.setEnabled(false);
			nhanPhongMenuItem.setEnabled(false);
		}
		if (phong.getTrangThaiPhong().getMaTrangThai().trim().equals("OC")) {
			nhanPhongMenuItem.setEnabled(false);

		}
		if (phong.getTrangThaiPhong().getMaTrangThai().trim().equals("OCP")) {
			themDichVuItem.setEnabled(false);
			traPhongMenuItem.setEnabled(false);
			nhanPhongMenuItem.setEnabled(true);

		}
		nhanPhongMenuItem.addActionListener(e1 -> {

			PhieuDatPhong_DAO DAO_PDP = new PhieuDatPhong_DAO();
			HoaDon_DAO DAO_HD = new HoaDon_DAO();
			try {
				PhieuDatPhong pdp = DAO_PDP.layThongTinPhieuDatTrangThai_DangCho_MaPhong(phong.getMaPhong());

				if (DAO_PDP.capNhatPhieuDatTrangThai_DaNhanPhong_MaPhieuDat(pdp.getMaPhieuDat()) && datPhong(pdp)) {

					DAO_P.capNhat_TranThaiPhong(pdp.getPhong().getMaPhong(), "OC");
					JOptionPane.showMessageDialog(null, "Nhận phòng thành công");

				}

			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}

		});
		traPhongMenuItem.addActionListener(e1 -> {
			try {
				DAO_HD = new HoaDon_DAO();
				hoaDon = DAO_HD.layHoaDon_DangChoThanhToan(phong.getMaPhong());
				JDialog_ThanhToan thanhToan = new JDialog_ThanhToan(hoaDon, phong, loaiP, hoaDon.getKhachHang());
				thanhToan.setVisible(true);

			} catch (Exception e11) {
				e11.printStackTrace();
			}

		});
		xemThongTinMenuItem.addActionListener(e1 -> {
			try {
				phong = DAO_P.timPhong_TheoMaPhong(phong.getMaPhong());
				loaiP = DAO_LP.layLoaiPhong_TheoMaLoaiPhong(phong.getLoaiPhong().getMaLoaiPhong());
				String anhPhong = loaiP.getHinhAnh();

				String tenPhong = phong.getTenPhong();
				String viTriPhong = phong.getViTriPhong();
				String tinhTrang = phong.getTinhTrangPhong();

				LoaiPhong loaiP = null;
				loaiP = DAO_LP.layLoaiPhong_TheoMaLoaiPhong(phong.getLoaiPhong().getMaLoaiPhong());
				String tenLoaiPhong = loaiP.getTenLoaiPhong();
				String giaPhong = loaiP.getGiaTien() + "";

				TrangThaiPhong trThaiP = null;
				trThaiP = DAO_TTP.timTrangThaiPhong_TheoMaTrangThai(phong.getTrangThaiPhong().getMaTrangThai());
				String trangThaiPhong = trThaiP.getTenTrangThai();
				JDialog_XemThongTinPhong thongTinPhong = new JDialog_XemThongTinPhong(phong);
				thongTinPhong.setVisible(true);
				thongTinPhong.SetModal_XemThongTinPhong(anhPhong, tenPhong, tenLoaiPhong, viTriPhong, giaPhong,
						trangThaiPhong, tinhTrang);
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		});

		themDichVuItem.addActionListener(e1 -> {
			JFrame modal_datDichVu = new JDialog_DatDichVu(phong);
			modal_datDichVu.setVisible(true);

		});

		chuyenPhongMenuItem.addActionListener(e1 -> {

			try {
				DAO_HD = new HoaDon_DAO();
				hoaDon = DAO_HD.layHoaDon_DangChoThanhToan(phong.getMaPhong());

				ChiTietHoaDon cthd = DAO_CTHD.timCTHoaDon_TheoMaHoaDon_MaPhong(hoaDon.getMaHoaDon(),
						phong.getMaPhong());

				JDialog_PhieuChuyenPhong phieuChuyenPhong = new JDialog_PhieuChuyenPhong(phong, hoaDon, cthd);

				phieuChuyenPhong.setVisible(true);
				phieuChuyenPhong.SetModal_PhieuChuyenPhong(hoaDon);

			} catch (Exception e2) {
				e2.printStackTrace();
				JOptionPane.showMessageDialog(null, "Phòng này chưa được đặt!");
			}

		});

		menu.add(xemThongTinMenuItem);
		menu.add(chuyenPhongMenuItem);
		menu.add(traPhongMenuItem);
		menu.add(nhanPhongMenuItem);
		menu.add(themDichVuItem);

		menu.show(this, e.getX(), e.getY());
	}

	public boolean kiemTraTrungMaPhongTrongDSPhong(String code, JTable table) {
		for (int row = 0; row < table.getRowCount(); row++) {
			String roomCode = table.getValueAt(row, 1).toString();
			if (roomCode.trim().equals(code)) {
				return false;
			}
		}
		return true;
	}

	public boolean datPhong(PhieuDatPhong pdp) {

		PhieuDatPhong_DAO DAO_PDP = new PhieuDatPhong_DAO();

		HoaDon_DAO DAO_HD = new HoaDon_DAO();

		KhachHang_DAO DAO_KH = new KhachHang_DAO();
		KhachHang kh = new KhachHang();
		kh = DAO_KH.layKhachHang_TheoMaKhachHang(pdp.getKhachHang().getMaKhachHang());

		Double tienCoc = 0.0;
		HelpRamDomMa help = new HelpRamDomMa();
		String maHD = help.taoMa("HoaDon", "maHoaDon", "HD");
		String maKhuyenMai = null;
		Date layNgayHienTai = new Date();
		Timestamp timestampNhanPhong = new Timestamp(layNgayHienTai.getTime());
		Timestamp timestampDatPhong = new Timestamp(layNgayHienTai.getTime());
		try {

			HoaDon hd = new HoaDon(maHD, kh, pdp.getNhanVien(), pdp, new KhuyenMai(), timestampDatPhong,
					"Đang chờ thanh toán", null);
			if (DAO_HD.taoHoaDon(hd)) {
				ChiTietHoaDon cthd = new ChiTietHoaDon(hd, getPhong());
				ChiTietHoaDon_DAO DAO_CTHD = new ChiTietHoaDon_DAO();
				DAO_CTHD.taoCTHoaDon(cthd);

				return true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return false;
	}
}
