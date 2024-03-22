package gui;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.AbstractBorder;

import dao.KhachHang_DAO;
import entity.KhachHang;
import entity.Phong;
import gui.JPanel_QuanLyDatPhong.RoundedTransparentBorder;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.geom.RoundRectangle2D;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JCheckBox;
import com.toedter.calendar.JDateChooser;

public class JPanel_QuanLyKhachHang extends JPanel implements ActionListener, ItemListener {

	private JDialog_ThemKhachHang modal_ThemKhachHang;

	/**
	 * Color
	 */

	private String hexColor_Blue1 = "#054A91";
	private String hexColor_Blue2 = "#3E7CB1";
	private String hexColor_Blue3 = "#81A4CD";
	private String hexColor_Blue4 = "#DBE4EE";
	private String hexColor_Orange = "#F17300";
	private String hexColor_Red = "#E11F1F";
	private String hexColor_Green = "#4BAC4D";

	private JTable table_KhachHang;

	private JTextField txt_TimKiem;
	private KhachHang_DAO DAO_KH;
	private ArrayList<KhachHang> dsKH;
	private DefaultTableModel model;
	private JRadioButton rdBtn_TimTheoMaKH;
	private JRadioButton rdBtn_TimTheoSoDT;
	private JTextField txt_DiemThuongTu;
	private JTextField txt_DiemThuongDen;
	private JTextField txt_TuoiDen;
	private JTextField txt_TuoiTu;
	private JCheckBox chcbx_Nam;
	private JCheckBox chcbx_Nu;
	private JCheckBox chcbx_TatCa;

	long khoangTuoi;
	private JButton btnThem;
	private JButton btnLamMoiBoLoc;
	private JButton btnXoa;
	private JButton btnLamMoi;
	private JButton btnTimKiem;

	private ButtonGroup btnGr_TimTheoLoai;

	private ButtonGroup btnGr_LocTheoGioiTinh;

	/**
	 * Rounded JPanel
	 */
	public class RoundedTransparentBorder extends AbstractBorder {
		private int cornerRadius;
		private Color borderColor;
		private Color backgroundColor;
		private float alpha;

		public RoundedTransparentBorder(int cornerRadius, Color borderColor, Color backgroundColor, float alpha) {
			this.cornerRadius = cornerRadius;
			this.borderColor = borderColor;
			this.backgroundColor = backgroundColor;
			this.alpha = alpha;
		}

		@Override
		public Insets getBorderInsets(Component c) {
			return new Insets(cornerRadius, cornerRadius, cornerRadius, cornerRadius);
		}

		@Override
		public Insets getBorderInsets(Component c, Insets insets) {
			insets.left = insets.top = insets.right = insets.bottom = cornerRadius;
			return insets;
		}

		@Override
		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
			if (width <= 0 || height <= 0) {
				return;
			}

			Graphics2D g2d = (Graphics2D) g.create();

			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

			RoundRectangle2D.Float roundRect = new RoundRectangle2D.Float(x, y, width - 1, height - 1, cornerRadius,
					cornerRadius);

			g2d.setColor(backgroundColor);
			g2d.fill(roundRect);
			g2d.setColor(borderColor);
			g2d.draw(roundRect);
			g2d.dispose();
		}

	}

	/**
	 * Create the panel.
	 */
	public JPanel_QuanLyKhachHang() {
		setBackground(Color.decode(hexColor_Blue1));
		setLayout(null);
		setBounds(0, 0, 1296, 672);

		modal_ThemKhachHang = new JDialog_ThemKhachHang();
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1296, 672);
		panel.setBackground(Color.decode(hexColor_Blue1));
		add(panel);
		panel.setLayout(null);

		JPanel panel_Table = new JPanel();
		panel_Table.setBorder(new RoundedTransparentBorder(20, Color.decode(hexColor_Blue1), Color.WHITE, 1.0f));
		panel_Table.setBackground(Color.decode(hexColor_Blue1));
//		panel_Table.setBackground(Color.WHITE);
		panel_Table.setBounds(0, 37, 1296, 635);
		panel.add(panel_Table);
		panel_Table.setLayout(null);

		Object[] rowData = new String[] { "M\u00E3 kh\u00E1ch h\u00E0ng", "H\u1ECD t\u00EAn", "Gi\u1EDBi t\u00EDnh",
				"Ng\u00E0y sinh", "\u0110\u1ECBa ch\u1EC9", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i",
				"\u0110i\u1EC3m th\u01B0\u1EDFng", "Ghi ch\u00FA" };

		table_KhachHang = new JTable();
		table_KhachHang.setBackground(Color.WHITE);

		model = (DefaultTableModel) table_KhachHang.getModel();
		table_KhachHang.setModel(new DefaultTableModel(new Object[][] {}, rowData) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -143705667217047914L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});

		table_KhachHang.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 20, 1019, 615);
		scrollPane.add(table_KhachHang);
		scrollPane.setViewportView(table_KhachHang);

		panel_Table.add(scrollPane);

		DAO_KH = new KhachHang_DAO();

		table_KhachHang.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() == 2) {
					int row = table_KhachHang.getSelectedRow();
					String maKhachHang = model.getValueAt(row, 0).toString();
					String hoTen = model.getValueAt(row, 1).toString();
					String gioiTinh = model.getValueAt(row, 2).toString();
					String ngaySinh = model.getValueAt(row, 3).toString();
					String diaChi = model.getValueAt(row, 4).toString();
					String sdt = model.getValueAt(row, 5).toString();
					String ghiChu = model.getValueAt(row, 7).toString();
					modal_ThemKhachHang.setVisible(true);
					modal_ThemKhachHang.setModal_ThemKhachHang(maKhachHang, hoTen, gioiTinh, ngaySinh, sdt, diaChi,
							ghiChu);

				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});

		JPanel pnl_Loc = new JPanel();
		pnl_Loc.setBackground(Color.WHITE);
		pnl_Loc.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnl_Loc.setBounds(1039, 20, 255, 615);
		panel_Table.add(pnl_Loc);
		pnl_Loc.setLayout(null);

		JPanel pnl_Loc_TheoGioiTinh = new JPanel();
		pnl_Loc_TheoGioiTinh.setBackground(new Color(255, 255, 255));
		pnl_Loc_TheoGioiTinh.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		pnl_Loc_TheoGioiTinh.setBounds(10, 35, 235, 70);
		pnl_Loc.add(pnl_Loc_TheoGioiTinh);
		pnl_Loc_TheoGioiTinh.setLayout(null);

		JLabel lbl_Loc_GioTinh = new JLabel("Giới tính");
		lbl_Loc_GioTinh.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lbl_Loc_GioTinh.setBounds(10, 10, 100, 16);
		pnl_Loc_TheoGioiTinh.add(lbl_Loc_GioTinh);

		chcbx_Nam = new JCheckBox("Nam");
		chcbx_Nam.setBackground(new Color(255, 255, 255));
		chcbx_Nam.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chcbx_Nam.setBounds(6, 35, 64, 21);
		pnl_Loc_TheoGioiTinh.add(chcbx_Nam);
		chcbx_Nam.addItemListener(this);

		chcbx_Nu = new JCheckBox("Nữ");
		chcbx_Nu.setBackground(new Color(255, 255, 255));
		chcbx_Nu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chcbx_Nu.setBounds(72, 35, 64, 21);
		pnl_Loc_TheoGioiTinh.add(chcbx_Nu);
		chcbx_Nu.addItemListener(this);

		chcbx_TatCa = new JCheckBox("Tất cả");
		chcbx_TatCa.setBackground(new Color(255, 255, 255));
		chcbx_TatCa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chcbx_TatCa.setBounds(138, 35, 64, 21);
		pnl_Loc_TheoGioiTinh.add(chcbx_TatCa);
		chcbx_TatCa.addItemListener(this);

		btnGr_LocTheoGioiTinh = new ButtonGroup();
		btnGr_LocTheoGioiTinh.add(chcbx_Nam);
		btnGr_LocTheoGioiTinh.add(chcbx_Nu);
		btnGr_LocTheoGioiTinh.add(chcbx_TatCa);

		JLabel lbl_Loc = new JLabel("BỘ LỌC TÌM KIẾM");
		lbl_Loc.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Loc.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lbl_Loc.setBounds(10, 5, 235, 25);
		pnl_Loc.add(lbl_Loc);

		JPanel pnl_Loc_TheoDiemThuong = new JPanel();
		pnl_Loc_TheoDiemThuong.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pnl_Loc_TheoDiemThuong.setBackground(new Color(255, 255, 255));
		pnl_Loc_TheoDiemThuong.setLayout(null);
		pnl_Loc_TheoDiemThuong.setBounds(10, 195, 235, 70);
		pnl_Loc.add(pnl_Loc_TheoDiemThuong);

		JLabel lbl_Loc_DiemThuong = new JLabel("Điểm thưởng");
		lbl_Loc_DiemThuong.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lbl_Loc_DiemThuong.setBounds(10, 10, 100, 18);
		pnl_Loc_TheoDiemThuong.add(lbl_Loc_DiemThuong);

		JLabel lbl_DiemBatDau = new JLabel("Từ :");
		lbl_DiemBatDau.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lbl_DiemBatDau.setBounds(10, 40, 45, 13);
		pnl_Loc_TheoDiemThuong.add(lbl_DiemBatDau);

		JLabel lbl_DiemKetThuc_1 = new JLabel("Đến :");
		lbl_DiemKetThuc_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lbl_DiemKetThuc_1.setBounds(120, 40, 45, 13);
		pnl_Loc_TheoDiemThuong.add(lbl_DiemKetThuc_1);

		txt_DiemThuongDen = new JTextField();
		txt_DiemThuongDen.setColumns(10);
		txt_DiemThuongDen.setBounds(155, 38, 70, 19);
		pnl_Loc_TheoDiemThuong.add(txt_DiemThuongDen);

		txt_DiemThuongTu = new JTextField();
		txt_DiemThuongTu.setColumns(10);
		txt_DiemThuongTu.setBounds(40, 38, 70, 19);
		pnl_Loc_TheoDiemThuong.add(txt_DiemThuongTu);

		JPanel pnl_Loc_TheoTuoi = new JPanel();
		pnl_Loc_TheoTuoi.setLayout(null);
		pnl_Loc_TheoTuoi.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		pnl_Loc_TheoTuoi.setBackground(Color.WHITE);
		pnl_Loc_TheoTuoi.setBounds(10, 115, 235, 70);
		pnl_Loc.add(pnl_Loc_TheoTuoi);

		JLabel lbl_Loc_Tuoi = new JLabel("Tuổi");
		lbl_Loc_Tuoi.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lbl_Loc_Tuoi.setBounds(10, 10, 100, 18);
		pnl_Loc_TheoTuoi.add(lbl_Loc_Tuoi);

		JLabel lbl_TuoiBatDau = new JLabel("Từ :");
		lbl_TuoiBatDau.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lbl_TuoiBatDau.setBounds(10, 40, 45, 13);
		pnl_Loc_TheoTuoi.add(lbl_TuoiBatDau);

		JLabel lbl_TuoiKetThuc = new JLabel("Đến :");
		lbl_TuoiKetThuc.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lbl_TuoiKetThuc.setBounds(120, 40, 45, 13);
		pnl_Loc_TheoTuoi.add(lbl_TuoiKetThuc);

		txt_TuoiDen = new JTextField();
		txt_TuoiDen.setColumns(10);
		txt_TuoiDen.setBounds(155, 38, 70, 19);
		pnl_Loc_TheoTuoi.add(txt_TuoiDen);

		txt_TuoiTu = new JTextField();
		txt_TuoiTu.setColumns(10);
		txt_TuoiTu.setBounds(40, 38, 70, 19);
		pnl_Loc_TheoTuoi.add(txt_TuoiTu);

		btnLamMoiBoLoc = new JButton("Làm mới bộ lọc");
		btnLamMoiBoLoc.setBounds(60, 275, 150, 35);
		pnl_Loc.add(btnLamMoiBoLoc);
		btnLamMoiBoLoc.setForeground(Color.WHITE);
		btnLamMoiBoLoc.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnLamMoiBoLoc.setBackground(Color.decode(hexColor_Blue2));
		btnLamMoiBoLoc.setForeground(Color.WHITE);
		btnLamMoiBoLoc.addActionListener(this);

		btnThem = new JButton("Thêm");
		btnThem.setForeground(Color.WHITE);
		btnThem.setIcon(new ImageIcon(JPanel_QuanLyKhachHang.class.getResource("/icon/add.png")));
		btnThem.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnThem.setBackground(Color.decode(hexColor_Green));
		btnThem.setBounds(10, 0, 125, 35);
		panel.add(btnThem);
		btnThem.addActionListener(this);

		btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(this);
		btnXoa.setIcon(new ImageIcon(JPanel_QuanLyKhachHang.class.getResource("/icon/trash.png")));
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnXoa.setBackground(Color.decode(hexColor_Red));
		btnXoa.setBounds(145, 0, 125, 35);
		panel.add(btnXoa);

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setIcon(new ImageIcon(JPanel_QuanLyKhachHang.class.getResource("/icon/refresh.png")));
		btnLamMoi.setForeground(Color.WHITE);
		btnLamMoi.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnLamMoi.setBackground(Color.LIGHT_GRAY);
		btnLamMoi.setBounds(280, 0, 125, 35);
		panel.add(btnLamMoi);
		btnLamMoi.addActionListener(this);

		txt_TimKiem = new JTextField();
		txt_TimKiem.setBounds(545, 1, 223, 34);
		panel.add(txt_TimKiem);
		txt_TimKiem.setColumns(10);

		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setBounds(415, 0, 123, 35);
		panel.add(btnTimKiem);
		btnTimKiem.setIcon(new ImageIcon(JPanel_QuanLyKhachHang.class.getResource("/icon/search.png")));
		btnTimKiem.setBackground(Color.decode(hexColor_Blue2));
		btnTimKiem.setForeground(Color.WHITE);

		btnTimKiem.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnTimKiem.addActionListener(this);

		rdBtn_TimTheoMaKH = new JRadioButton("Mã khách hàng");
		rdBtn_TimTheoMaKH.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		rdBtn_TimTheoMaKH.setHorizontalAlignment(SwingConstants.CENTER);
		rdBtn_TimTheoMaKH.setBackground(new Color(255, 255, 255));
		rdBtn_TimTheoMaKH.setBounds(774, 4, 125, 30);
		panel.add(rdBtn_TimTheoMaKH);

		rdBtn_TimTheoSoDT = new JRadioButton("Số điện thoại");
		rdBtn_TimTheoSoDT.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		rdBtn_TimTheoSoDT.setHorizontalAlignment(SwingConstants.CENTER);
		rdBtn_TimTheoSoDT.setBackground(Color.WHITE);
		rdBtn_TimTheoSoDT.setBounds(901, 4, 125, 30);
		panel.add(rdBtn_TimTheoSoDT);

		btnGr_TimTheoLoai = new ButtonGroup();
		btnGr_TimTheoLoai.add(rdBtn_TimTheoMaKH);
		btnGr_TimTheoLoai.add(rdBtn_TimTheoSoDT);

		DocDuLieu();

//-------------------------
		txt_TimKiem.addActionListener(this);
		txt_DiemThuongDen.addActionListener(this);
		txt_DiemThuongTu.addActionListener(this);
		txt_TuoiDen.addActionListener(this);
		txt_TuoiTu.addActionListener(this);
	}

	public void XoaDuLieuTrenTable() {
		model = (DefaultTableModel) table_KhachHang.getModel();
		model.getDataVector().removeAllElements();
	}

	public void DocDuLieu() {
		model = (DefaultTableModel) table_KhachHang.getModel();
		model.getDataVector().removeAllElements();

		try {
			dsKH = DAO_KH.layTatCaKhachHang();
			if (dsKH != null) {
				dsKH.forEach(kh -> {
					String gender = kh.isGioiTinh() ? "Nam" : "Nữ";
					Object[] rowData = { kh.getMaKhachHang(), kh.getHoTen(), gender, kh.getNgaySinh(), kh.getDiaChi(),
							kh.getSoDienThoai(), kh.getDiemThuong(), kh.getGhiChu() };
					model.addRow(rowData);
					table_KhachHang.setModel(model);
				});
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Không thể đọc dữ liệu");
		}
	}

	public void XoaKhachHang() {
		int row = table_KhachHang.getSelectedRow();
		String maKhachHang = model.getValueAt(row, 0).toString();
		KhachHang khachHang = new KhachHang(maKhachHang);
		try {
			String tenKhachHang = DAO_KH.layKhachHang_TheoMaKhachHang(maKhachHang).getHoTen();
			int result = JOptionPane.showConfirmDialog(this,
					"Bạn có chắc chắn muốn xóa khách hàng " + tenKhachHang + "?", "Xác nhận",
					JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				DAO_KH.xoaKhachHang(khachHang);
				JOptionPane.showMessageDialog(null, "Xóa khách hàng " + tenKhachHang + " thành công");
				model.removeRow(row);
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Xóa thất bại");
		}
	}

	public void TimKhachHang_TheoMa() {
		String chuoiTimKiem = txt_TimKiem.getText().trim();
		KhachHang kh = DAO_KH.layKhachHang_TheoMaKhachHang(chuoiTimKiem);
		try {
			String gender = kh.isGioiTinh() ? "Nam" : "Nữ";
			modal_ThemKhachHang = new JDialog_ThemKhachHang();
			modal_ThemKhachHang.setModal_ThemKhachHang(kh.getMaKhachHang(), kh.getHoTen(), gender,
					kh.getNgaySinh().toString(), kh.getSoDienThoai(), kh.getDiaChi(), kh.getGhiChu());
			modal_ThemKhachHang.setVisible(true);
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Không tồn tại khách hàng có mã: " + chuoiTimKiem.trim());
		}
	}

	public void TimKhachHang_TheoSoDT() {
		String chuoiTimKiem = txt_TimKiem.getText().trim();
		KhachHang kh = DAO_KH.layKhachHang_TheoSoDienThoai(chuoiTimKiem);
		try {
			String gender = kh.isGioiTinh() ? "Nam" : "Nữ";
			modal_ThemKhachHang = new JDialog_ThemKhachHang();
			modal_ThemKhachHang.setModal_ThemKhachHang(kh.getMaKhachHang(), kh.getHoTen(), gender,
					kh.getNgaySinh().toString(), kh.getSoDienThoai(), kh.getDiaChi(), kh.getGhiChu());
			modal_ThemKhachHang.setVisible(true);
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Không tồn tại khách hàng có số điện thoại: " + chuoiTimKiem.trim());
		}

	}

	public void TimKhachHang_TheoTen() {
		model.getDataVector().removeAllElements();
		String chuoiTimKiem = txt_TimKiem.getText().trim();
		ArrayList<KhachHang> dsKH = DAO_KH.layKhachHang_TheoTen(chuoiTimKiem);
		if (dsKH.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Không có khách hàng nào được tìm thấy.");
		} else {
			for (KhachHang kh : dsKH) {
				String gender = kh.isGioiTinh() ? "Nam" : "Nữ";
				Object[] rowData = { kh.getMaKhachHang(), kh.getHoTen(), gender, kh.getNgaySinh(), kh.getDiaChi(),
						kh.getSoDienThoai(), kh.getDiemThuong(), kh.getGhiChu() };
				model.addRow(rowData);
			}
		}
	}

	public void LamMoiBoLoc() {
		txt_DiemThuongDen.setText("");
		txt_DiemThuongTu.setText("");
		txt_TimKiem.setText("");
		txt_TuoiDen.setText("");
		txt_TuoiTu.setText("");
		btnGr_TimTheoLoai.clearSelection();
		btnGr_LocTheoGioiTinh.clearSelection();
	}

	public void LocDuLieu() {
		int loc_tuoiTu, loc_tuoiDen; // Lấy tuổi
		try {
			loc_tuoiTu = Integer.parseInt(txt_TuoiTu.getText().trim());
		} catch (NumberFormatException e) {
			// Xử lý ngoại lệ nếu người dùng không nhập số tuổi tối thiểu
			loc_tuoiTu = 0;
		}
		try {
			loc_tuoiDen = Integer.parseInt(txt_TuoiDen.getText().trim());
		} catch (NumberFormatException e) {
			// Xử lý ngoại lệ nếu người dùng không nhập số tuổi tối đa
			loc_tuoiDen = Integer.MAX_VALUE; // Lọc theo tất cả các tuổi
		}

		int loc_diemThuongTu, loc_diemThuongDen; // Lấy điểm thưởng
		try {
			loc_diemThuongTu = Integer.parseInt(txt_DiemThuongTu.getText().trim());
		} catch (NumberFormatException e) {
			// Xử lý ngoại lệ nếu người dùng không nhập số tuổi tối thiểu
			loc_diemThuongTu = 0;
		}
		try {
			loc_diemThuongDen = Integer.parseInt(txt_DiemThuongDen.getText().trim());
		} catch (NumberFormatException e) {
			loc_diemThuongDen = Integer.MAX_VALUE; // Lọc theo tất cả điểm thưởng
		}

		if (loc_tuoiDen < loc_tuoiTu) {
			JOptionPane.showMessageDialog(null, "Tuổi bắt đầu phải lớn hơn tuổi kết thúc!");
			return;
		}

		if (loc_diemThuongTu > loc_diemThuongDen) {
			JOptionPane.showMessageDialog(null, "Điểm tưởng bắt đầu phải lớn hơn điểm thưởng kết thúc!");
			return;
		}

		model.getDataVector().removeAllElements();
		boolean ketQuaLoc = false;

		for (KhachHang kh : DAO_KH.layTatCaKhachHang()) {
			boolean kiemTra = true;

			String gender = kh.isGioiTinh() ? "Nam" : "Nữ";
			Calendar ngayHienTai = Calendar.getInstance();
			Calendar cal = Calendar.getInstance();
			cal.setTime(kh.getNgaySinh());
			int tuoi = ngayHienTai.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
			int diemThuong = kh.getDiemThuong();

			if ((chcbx_Nam.isSelected() && !gender.equals("Nam")) || (chcbx_Nu.isSelected() && !gender.equals("Nữ"))) {
				kiemTra = false; // Kiểm tra giới tính được chọn
			}

			if (tuoi < loc_tuoiTu || tuoi > loc_tuoiDen) {
				kiemTra = false; // Kiểm tra độ tuổi được nhập
			}

			if ((loc_diemThuongTu > diemThuong || diemThuong > loc_diemThuongDen)) {
				kiemTra = false; // Kiểm tra điểm thưởng được nhập
			}

			if (kiemTra) {
				Object[] rowData = { kh.getMaKhachHang(), kh.getHoTen(), gender, kh.getNgaySinh(), kh.getDiaChi(),
						kh.getSoDienThoai(), diemThuong, kh.getGhiChu() };
				model.addRow(rowData);
				ketQuaLoc = true;
			}
		}

		if (!ketQuaLoc) {
			model.fireTableDataChanged();
			JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả phù hợp với tiêu chí lọc.");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			modal_ThemKhachHang = new JDialog_ThemKhachHang();
			modal_ThemKhachHang.setVisible(true);
		}
		if (o.equals(btnXoa)) {
			XoaKhachHang();
		}
		if (o.equals(btnLamMoi)) {
			DocDuLieu();
			LamMoiBoLoc();
		}
		if (o.equals(btnLamMoiBoLoc)) {
			DocDuLieu();
			LamMoiBoLoc();
		}
		
		if ((o.equals(txt_TimKiem) && rdBtn_TimTheoSoDT.isSelected() == false
				&& rdBtn_TimTheoMaKH.isSelected() == false)
				|| (o.equals(btnTimKiem) & rdBtn_TimTheoSoDT.isSelected() == false
						&& rdBtn_TimTheoMaKH.isSelected() == false)) {
			TimKhachHang_TheoTen();
		}

		if (o.equals(txt_DiemThuongDen) || o.equals(txt_DiemThuongTu) || o.equals(txt_TuoiDen) || o.equals(txt_TuoiTu))
			LocDuLieu();

		if ((rdBtn_TimTheoSoDT.isSelected() && o.equals(btnTimKiem))
				|| (rdBtn_TimTheoSoDT.isSelected() && o.equals(txt_TimKiem))) {
			TimKhachHang_TheoSoDT();
		}

		if (rdBtn_TimTheoMaKH.isSelected() && o.equals(btnTimKiem)
				|| (rdBtn_TimTheoMaKH.isSelected() && o.equals(txt_TimKiem))) {
			TimKhachHang_TheoMa();
		}
	}

	public void setJPanel_QuanLyKhachHang(boolean b) {
		// TODO Auto-generated method stub
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getItem();
		if (o.equals(chcbx_Nam) || o.equals(chcbx_Nu) || o.equals(chcbx_TatCa))
			LocDuLieu();
	}

}