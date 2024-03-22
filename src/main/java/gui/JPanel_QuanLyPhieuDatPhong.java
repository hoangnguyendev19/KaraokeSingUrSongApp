package gui;

import javax.swing.JPanel;
import javax.swing.border.AbstractBorder;

import dao.KhachHang_DAO;
import dao.NhanVien_DAO;
import dao.PhieuDatPhong_DAO;
import dao.Phong_DAO;
import entity.KhachHang;
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.Phong;
import gui.JPanel_QuanLyDatPhong.RoundedTransparentBorder;
import other.CustomTable;
import other.CustomTable.ButtonEditor;
import other.CustomTable.ButtonRenderer;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.EventObject;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

import org.jfree.data.Values;

import com.toedter.calendar.JDateChooser;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ItemEvent;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.SystemColor;

public class JPanel_QuanLyPhieuDatPhong extends JPanel implements ActionListener {

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

	private JTable table_PhieuDatPhong;

	private ImageIcon addIcon;
	private JTextField txtTenPhong;
	private JPanel panel_TimKiem;
	private JTextField txt_hoTen;
	private JTextField textField_2;
	private DefaultTableModel model;
	private JDialog_PhieuDatPhongTruoc modal_PhieuDatPhongTruoc;
	private JDialog_ChiTietPhieuDatPhongTruoc dialog_CTPDP;
	private JDialog_CapNhatPhieuDatPhongTruoc modal_CapNhatPhieuDatPhongTruoc;
	private String[] rowData;

	private ArrayList<PhieuDatPhong> dsPDP;
	private ArrayList<KhachHang> dsKH;
	private ArrayList<NhanVien> dsNV;
	private ArrayList<Phong> dsPhong;

	private PhieuDatPhong_DAO DAO_PDP;
	private KhachHang_DAO DAO_KH;
	private NhanVien_DAO DAO_NV;
	private Phong_DAO DAO_P;

	private KhachHang kh;
	private NhanVien nv;
	private Phong p;
	private PhieuDatPhong pdp;
	private JButton btnHuyPDP;
	private JButton btnLamMoi;
	private JButton btnSua;
	private JButton btnThem;
//	private CustomTable table_PhieuDatPhong;
	private int row;
	private JTextField txtmaPhieu;
	private JRadioButton rdbChoNhanPhong;
	private JRadioButton rdbDaNhanPhong;
	private JRadioButton rdbHetHan;
	private ButtonGroup searchGroup;
	private JRadioButton rdbDaHuy;
	private JRadioButton rdbTatCa;
	private JDateChooser dateNgayNhan;
	private JTextField txt_soDienThoai;
	private JButton btn_loc;

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
	public JPanel_QuanLyPhieuDatPhong(NhanVien nv) {
		this.nv = nv;

		setBackground(Color.decode(hexColor_Blue1));
		setLayout(null);
		setBounds(0, 0, 1296, 672);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1296, 672);
		panel.setBackground(Color.decode(hexColor_Blue1));
		add(panel);
		panel.setLayout(null);

		modal_PhieuDatPhongTruoc = new JDialog_PhieuDatPhongTruoc(nv);
		dialog_CTPDP = new JDialog_ChiTietPhieuDatPhongTruoc();
		modal_CapNhatPhieuDatPhongTruoc = new JDialog_CapNhatPhieuDatPhongTruoc(nv);

		JPanel panel_Table = new JPanel();
		panel_Table.setBorder(new RoundedTransparentBorder(20, Color.decode(hexColor_Blue1), Color.WHITE, 1.0f));
		panel_Table.setBackground(Color.decode(hexColor_Blue1));
		panel_Table.setBounds(0, 37, 1296, 635);
		panel.add(panel_Table);
		panel_Table.setLayout(null);

		addIcon = new ImageIcon(new ImageIcon(JPanel_QuanLyPhieuDatPhong.class.getResource("/icon/add.png")).getImage()
				.getScaledInstance(20, 20, Image.SCALE_DEFAULT));

		table_PhieuDatPhong = new JTable();
		table_PhieuDatPhong.setBackground(Color.WHITE);
		table_PhieuDatPhong.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		table_PhieuDatPhong.setModel(model = new DefaultTableModel(new Object[][] {}, rowData = new String[] {
				"Mã phiếu đặt", "Ngày nhận", "Khách hàng", "Số điện thoại", "Phòng", "Trạng thái", "Ghi chú" }) {
			private static final long serialVersionUID = -143705667217047914L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return column >= 7; // Đặt tất cả các ô không thể chỉnh sửa
			}

		});

//		table_PhieuDatPhong = new CustomTable(model, 7);
//		table_PhieuDatPhong.setBackground(Color.WHITE);
//		table_PhieuDatPhong.setRowHeight(25);

		table_PhieuDatPhong.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() == 2) {
					int row = table_PhieuDatPhong.getSelectedRow();
					String maPD = model.getValueAt(row, 0).toString();
					dialog_CTPDP.setVisible(true);
					dialog_CTPDP.HienThongTinTheoMaPDP(maPD);

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
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 37, 1008, 588);
		scrollPane.add(table_PhieuDatPhong);
		scrollPane.setViewportView(table_PhieuDatPhong);
		panel_Table.add(scrollPane);

		DAO_PDP = new PhieuDatPhong_DAO();
		DAO_KH = new KhachHang_DAO();
		DAO_NV = new NhanVien_DAO();
		DAO_P = new Phong_DAO();

		pdp = new PhieuDatPhong();
		kh = new KhachHang();
		nv = new NhanVien();
		p = new Phong();

		DefaultTableModel model = (DefaultTableModel) table_PhieuDatPhong.getModel();
		DocDuLieuTrenSQL();

		panel_TimKiem = new JPanel();
		panel_TimKiem.setBackground(Color.WHITE);
		panel_TimKiem.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_TimKiem.setBounds(1028, 37, 258, 588);
		panel_Table.add(panel_TimKiem);
		panel_TimKiem.setLayout(null);

		txtTenPhong = new JTextField();
		txtTenPhong.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTenPhong.setBackground(new Color(255, 255, 255));
		txtTenPhong.setToolTipText("Nhập tên phòng");
		txtTenPhong.setBounds(10, 100, 235, 25);
		panel_TimKiem.add(txtTenPhong);
		txtTenPhong.setColumns(10);

		JLabel lblNewLabel = new JLabel("Tên phòng");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 70, 235, 30);
		panel_TimKiem.add(lblNewLabel);

		JLabel lblTnKhchHng = new JLabel("Họ tên khách hàng");
		lblTnKhchHng.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTnKhchHng.setBounds(10, 141, 235, 30);
		panel_TimKiem.add(lblTnKhchHng);

		txt_hoTen = new JTextField();
		txt_hoTen.setToolTipText("Nhập tên phòng");
		txt_hoTen.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_hoTen.setColumns(10);
		txt_hoTen.setBackground(Color.WHITE);
		txt_hoTen.setBounds(10, 171, 235, 25);
		panel_TimKiem.add(txt_hoTen);

		java.util.Date currentDate = new Date();
		dateNgayNhan = new JDateChooser(currentDate);
		dateNgayNhan.setDateFormatString("yyyy-MM-dd");

		dateNgayNhan.setBounds(10, 316, 235, 27);
		panel_TimKiem.add(dateNgayNhan);

		JLabel dcNgayNhanPhong = new JLabel("Ngày nhận phòng");
		dcNgayNhanPhong.setFont(new Font("Tahoma", Font.PLAIN, 12));
		dcNgayNhanPhong.setBounds(10, 276, 122, 30);
		panel_TimKiem.add(dcNgayNhanPhong);

		DefaultComboBoxModel<String> dataModelTT = new DefaultComboBoxModel<String>();
		dataModelTT.addElement("Chờ nhận phòng");
		dataModelTT.addElement("Đã hủy");
		dataModelTT.addElement("Hết hạn");

		JLabel lblNewLabel_1 = new JLabel("Mã phiếu đặt");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 0, 122, 30);
		panel_TimKiem.add(lblNewLabel_1);

		txtmaPhieu = new JTextField();
		txtmaPhieu.setToolTipText("Nhập tên phòng");
		txtmaPhieu.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtmaPhieu.setColumns(10);
		txtmaPhieu.setBackground(Color.WHITE);
		txtmaPhieu.setBounds(10, 30, 235, 25);
		panel_TimKiem.add(txtmaPhieu);

		JLabel lblSinThoi = new JLabel("Số điện thoại khách hàng");
		lblSinThoi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSinThoi.setBounds(10, 210, 235, 30);
		panel_TimKiem.add(lblSinThoi);

		txt_soDienThoai = new JTextField();
		txt_soDienThoai.setToolTipText("Nhập tên phòng");
		txt_soDienThoai.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_soDienThoai.setColumns(10);
		txt_soDienThoai.setBackground(Color.WHITE);
		txt_soDienThoai.setBounds(10, 241, 235, 25);
		panel_TimKiem.add(txt_soDienThoai);

		btn_loc = new JButton("Lọc");
		btn_loc.setBackground(SystemColor.textHighlight);
		btn_loc.setForeground(new Color(255, 255, 255));
		btn_loc.setFont(new Font("Tahoma", Font.BOLD, 17));
		btn_loc.setBounds(10, 374, 235, 30);
		panel_TimKiem.add(btn_loc);

		rdbChoNhanPhong = new JRadioButton("Chờ  nhận phòng");
		rdbChoNhanPhong.setBounds(89, 7, 118, 23);
		panel_Table.add(rdbChoNhanPhong);

		rdbDaNhanPhong = new JRadioButton("Đã  nhận phòng");
		rdbDaNhanPhong.setBounds(214, 7, 114, 23);
		panel_Table.add(rdbDaNhanPhong);

		rdbHetHan = new JRadioButton("Hết hạn");
		rdbHetHan.setBounds(330, 7, 91, 23);
		panel_Table.add(rdbHetHan);

		rdbDaHuy = new JRadioButton("Đã hủy");
		rdbDaHuy.setBounds(423, 7, 114, 23);
		panel_Table.add(rdbDaHuy);

		rdbTatCa = new JRadioButton("Tất cả");
		rdbTatCa.setBounds(10, 7, 77, 23);
		rdbTatCa.setSelected(true);
		panel_Table.add(rdbTatCa);

		searchGroup = new ButtonGroup();
		searchGroup.add(rdbChoNhanPhong);
		searchGroup.add(rdbDaNhanPhong);
		searchGroup.add(rdbHetHan);
		searchGroup.add(rdbDaHuy);
		searchGroup.add(rdbTatCa);
		btnHuyPDP = new JButton("Hủy phiếu");
		btnHuyPDP.setIcon(new ImageIcon(JPanel_QuanLyPhieuDatPhong.class.getResource("/icon/trash.png")));
		btnHuyPDP.setForeground(Color.WHITE);
		btnHuyPDP.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnHuyPDP.setBackground(Color.decode(hexColor_Red));
		btnHuyPDP.setBounds(301, 0, 135, 35);
		panel.add(btnHuyPDP);

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setIcon(new ImageIcon(JPanel_QuanLyPhieuDatPhong.class.getResource("/icon/refresh.png")));
		btnLamMoi.setForeground(Color.WHITE);
		btnLamMoi.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnLamMoi.setBackground(Color.LIGHT_GRAY);
		btnLamMoi.setBounds(446, 0, 135, 35);
		panel.add(btnLamMoi);

		btnSua = new JButton("Sửa phiếu");
//		btnSua.setIcon(new ImageIcon(JPanel_QuanLyPhieuDatPhong.class.getResource("/icon/edit.png")));
		btnSua.setSelectedIcon(new ImageIcon(JPanel_QuanLyPhieuDatPhong.class.getResource("/icon/update.png")));
		btnSua.setForeground(Color.WHITE);
		btnSua.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnSua.setBackground(Color.decode(hexColor_Orange));
		btnSua.setBounds(156, 0, 135, 35);
		panel.add(btnSua);

		btnThem = new JButton("Thêm");
		btnThem.setIcon(new ImageIcon(JPanel_QuanLyPhieuDatPhong.class.getResource("/icon/add.png")));
		btnThem.setSelectedIcon(new ImageIcon(JPanel_QuanLyPhieuDatPhong.class.getResource("/icon/add.png")));
		btnThem.setForeground(Color.WHITE);
		btnThem.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnThem.setBackground(new Color(75, 172, 77));
		btnThem.setBounds(10, 0, 135, 35);
		panel.add(btnThem);

		// Add event:

		btnHuyPDP.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnSua.addActionListener(this);
		btnThem.addActionListener(this);
		btn_loc.addActionListener(this);

		rdbChoNhanPhong.addActionListener(this);
		rdbDaHuy.addActionListener(this);
		rdbDaNhanPhong.addActionListener(this);
		rdbHetHan.addActionListener(this);
		rdbTatCa.addActionListener(this);

//		addEventForGUI();

	}

	public void addEventForGUI() {
		btnHuyPDP.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnSua.addActionListener(this);
		btnThem.addActionListener(this);

		rdbChoNhanPhong.addActionListener(this);
		rdbDaHuy.addActionListener(this);
		rdbDaNhanPhong.addActionListener(this);
		rdbHetHan.addActionListener(this);
		rdbTatCa.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		// Hủy phiếu đặt phòng có trạng thái "Chờ nhận phòng"
		if (o.equals(btnHuyPDP)) {
			row = table_PhieuDatPhong.getSelectedRow();
			if (row >= 0) {
				String maPD = model.getValueAt(row, 0).toString().trim();
				String ttp = model.getValueAt(row, 5).toString();
				if (ttp.equals("Chờ nhận phòng")) {
					HuyPhieu();
				} else {
					Toolkit.getDefaultToolkit().beep(); // Phát ra tiếng "beep" để cảnh báo
					JOptionPane.showMessageDialog(null, "Phiếu " + maPD + " không thể hủy!");
				}
			} else {
				Toolkit.getDefaultToolkit().beep(); // Phát ra tiếng "beep" để cảnh báo
				JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu cần hủy!");
			}
		}
		if (o.equals(btn_loc)) {
			String hoten = txt_hoTen.getText().trim();
			String maP = txtTenPhong.getText().trim();
			String soDienThoai = txt_soDienThoai.getText().trim();
			String maPhieu = txtmaPhieu.getText().trim();
			Date ngayLap = dateNgayNhan.getDate();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			String ngayLapLoc = formatter.format(ngayLap);
			try {
				DAO_PDP = new PhieuDatPhong_DAO();
				ArrayList<PhieuDatPhong> dsPDLoc = DAO_PDP.locPhieuDatPhong(maPhieu, hoten, maP, soDienThoai,
						ngayLapLoc);
				if (dsPDLoc != null) {
					clearTable();
					for (PhieuDatPhong pdp : dsPDLoc) {
						KhachHang kh = DAO_KH.layKhachHang_TheoMaKhachHang(pdp.getKhachHang().getMaKhachHang());
						NhanVien nv = DAO_NV.timNhanVien_TheoMaNhanVien(pdp.getNhanVien().getMaNhanVien());
						Phong p = DAO_P.timPhong_TheoMaPhong(pdp.getPhong().getMaPhong());

						Object[] rowData = { pdp.getMaPhieuDat(), pdp.getThoiGianNhanPhong(), kh.getHoTen(),
								kh.getSoDienThoai(), p.getMaPhong(), pdp.getTrangThai(), pdp.getMoTa() };
						model.addRow(rowData);

					}
				}

			} catch (Exception e2) {

				e2.printStackTrace();
			}
		}

		// Làm mới table
		if (o.equals(btnLamMoi)) {
			rdbTatCa.setSelected(true);
			clearTable();
			clearFindField();
			DocDuLieuTrenSQL();
		}

		// Sửa thông tin phiếu đặt phòng có trạng thái "Chờ nhận phòng"
		if (o.equals(btnSua)) {
			row = table_PhieuDatPhong.getSelectedRow();

			if (row >= 0) {
				String maPD = model.getValueAt(row, 0).toString().trim();
				String ttp = model.getValueAt(row, 5).toString();
				if (ttp.equals("Chờ nhận phòng")) {
					modal_CapNhatPhieuDatPhongTruoc.setVisible(true);
					modal_CapNhatPhieuDatPhongTruoc.HienThongTinTheoMaPDP(maPD);
				} else {
					Toolkit.getDefaultToolkit().beep(); // Phát ra tiếng "beep" để cảnh báo
					JOptionPane.showMessageDialog(null, "Phiếu " + maPD + " không thể cập nhật!");
				}
			} else {
				Toolkit.getDefaultToolkit().beep(); // Phát ra tiếng "beep" để cảnh báo
				JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu cần cập nhật!");
			}

		}

		// Thêm phiếu đặt phòng trước
		if (o.equals(btnThem)) {
			modal_PhieuDatPhongTruoc.setVisible(true);
		}

		if (o.equals(rdbChoNhanPhong) || o.equals(rdbDaHuy) || o.equals(rdbDaNhanPhong) || o.equals(rdbHetHan)
				|| o.equals(rdbTatCa)) {
			TimKiemTheoTrangThai();
		}
	}

	public void clearTable() {
		DefaultTableModel model = (DefaultTableModel) table_PhieuDatPhong.getModel();
		model.setRowCount(0);
	}

	public void clearFindField() {
		txt_hoTen.setText("");
		txtmaPhieu.setText("");
		txtTenPhong.setText("");
		txtmaPhieu.setText("");
		java.util.Date currentDate = new Date();
		dateNgayNhan.setDate(currentDate);
	}

	private void DocDuLieuTrenSQL() {
		clearTable();
		dsPDP = DAO_PDP.layTatCaPhieuDatPhong();

		if (dsPDP != null) {
			for (PhieuDatPhong pdp : dsPDP) {
				KhachHang kh = DAO_KH.layKhachHang_TheoMaKhachHang(pdp.getKhachHang().getMaKhachHang());
				NhanVien nv = DAO_NV.timNhanVien_TheoMaNhanVien(pdp.getNhanVien().getMaNhanVien());
				Phong p = DAO_P.timPhong_TheoMaPhong(pdp.getPhong().getMaPhong());

				Object[] rowData = { pdp.getMaPhieuDat(), pdp.getThoiGianNhanPhong(), kh.getHoTen(),
						kh.getSoDienThoai(), p.getMaPhong(), pdp.getTrangThai(), pdp.getMoTa() };
				model.addRow(rowData);

			}
		}
	}

	public void TimKiemTheoTrangThai() {
		boolean choNhanPhong = rdbChoNhanPhong.isSelected();
		boolean daNhanPhong = rdbDaNhanPhong.isSelected();
		boolean hetHan = rdbHetHan.isSelected();
		boolean daHuy = rdbDaHuy.isSelected();
		boolean tatCa = rdbTatCa.isSelected();
		// Tạo danh sách dsPDP để lưu kết quả
		List<PhieuDatPhong> dsPDP = new ArrayList<>();
//		DAO_PDP.capNhatTrangThaiPhieu(pdp);
		// Lấy danh sách phiếu đặt phòng dựa trên trạng thái được chọn
		if (choNhanPhong) {
			dsPDP.addAll(DAO_PDP.layPhieuDatPhong_TheoTrangThaiPhieu("Chờ nhận phòng"));
		}
		if (daNhanPhong) {
			dsPDP.addAll(DAO_PDP.layPhieuDatPhong_TheoTrangThaiPhieu("Đã nhận phòng"));
		}
		if (hetHan) {
			dsPDP.addAll(DAO_PDP.layPhieuDatPhong_TheoTrangThaiPhieu("Hết hạn"));
		}
		if (daHuy) {
			dsPDP.addAll(DAO_PDP.layPhieuDatPhong_TheoTrangThaiPhieu("Đã hủy"));
		}

		if (!dsPDP.isEmpty()) {
			clearTable();
			for (PhieuDatPhong pdp : dsPDP) {
				// Lấy thông tin liên quan từ các DAO tương ứng
				KhachHang kh = DAO_KH.layKhachHang_TheoMaKhachHang(pdp.getKhachHang().getMaKhachHang());
				NhanVien nv = DAO_NV.timNhanVien_TheoMaNhanVien(pdp.getNhanVien().getMaNhanVien());
				Phong p = DAO_P.timPhong_TheoMaPhong(pdp.getPhong().getMaPhong());

				Object[] rowData = { pdp.getMaPhieuDat(), pdp.getThoiGianNhanPhong(), kh.getHoTen(),
						kh.getSoDienThoai(), p.getTenPhong(), pdp.getTrangThai(), pdp.getMoTa() };
				model.addRow(rowData);
			}
		} else {
			clearTable();
		}

		if (tatCa) {
			DocDuLieuTrenSQL();
		}
	}

	private void TimKiemTheoTenKH() {
		String ttKH = txt_hoTen.getText();
		dsPDP = DAO_PDP.layPhieuDatPhong_TheoTenKhachHangvsSDT(ttKH);

		if (dsPDP != null) {
			clearTable();
			for (PhieuDatPhong pdp : dsPDP) {
				KhachHang kh = DAO_KH.layKhachHang_TheoMaKhachHang(pdp.getKhachHang().getMaKhachHang());
				NhanVien nv = DAO_NV.timNhanVien_TheoMaNhanVien(pdp.getNhanVien().getMaNhanVien());
				Phong p = DAO_P.timPhong_TheoMaPhong(pdp.getPhong().getMaPhong());

				Object[] rowData = { pdp.getMaPhieuDat(), pdp.getThoiGianNhanPhong(), kh.getHoTen(),
						kh.getSoDienThoai(), p.getTenPhong(), pdp.getTrangThai(), pdp.getMoTa() };
				model.addRow(rowData);

				model.addRow(rowData);

			}
		} else {
			Toolkit.getDefaultToolkit().beep(); // Phát ra tiếng "beep" để cảnh báo
			JOptionPane.showMessageDialog(null, "Không tìm thấy");

		}
	}

	private void TimKiemTheoTenPhong() {
		String ttKH = txtTenPhong.getText();
		dsPDP = DAO_PDP.layPhieuDatPhong_TheoTenPhong(ttKH);

		if (dsPDP != null) {
			clearTable();
			for (PhieuDatPhong pdp : dsPDP) {
				KhachHang kh = DAO_KH.layKhachHang_TheoMaKhachHang(pdp.getKhachHang().getMaKhachHang());
				NhanVien nv = DAO_NV.timNhanVien_TheoMaNhanVien(pdp.getNhanVien().getMaNhanVien());
				Phong p = DAO_P.timPhong_TheoMaPhong(pdp.getPhong().getMaPhong());

				Object[] rowData = { pdp.getMaPhieuDat(), pdp.getThoiGianNhanPhong(), kh.getHoTen(),
						kh.getSoDienThoai(), p.getTenPhong(), pdp.getTrangThai(), pdp.getMoTa() };
				model.addRow(rowData);

			}
		} else {
			Toolkit.getDefaultToolkit().beep(); // Phát ra tiếng "beep" để cảnh báo
			JOptionPane.showMessageDialog(null, "Không tìm thấy");

		}
	}

//	private void TimKiemTheoNgayNhan() {
//		Date ngayNhan = dateNgayNhan.getDate();
//		ArrayList<PhieuDatPhong> dsPDP = DAO_PDP.layPhieuDatPhong_TheoNgayNhan(ngayNhan);
//		if (dsPDP != null) {
//			clearTable();
//			for (PhieuDatPhong pdp : dsPDP) {
//				KhachHang kh = DAO_KH.layKhachHang_TheoMaKhachHang(pdp.getKhachHang().getMaKhachHang());
//				NhanVien nv = DAO_NV.timNhanVien_TheoMaNhanVien(pdp.getNhanVien().getMaNhanVien());
//				Phong p = DAO_P.timPhong_TheoMaPhong(pdp.getPhong().getMaPhong());
//
//				Object[] rowData = { pdp.getMaPhieuDat(), pdp.getThoiGianNhanPhong(), kh.getHoTen(),
//						kh.getSoDienThoai(), p.getTenPhong(), pdp.getTrangThai(), pdp.getMoTa() };
//				model.addRow(rowData);
//
//			}
//		} else {
//			Toolkit.getDefaultToolkit().beep(); // Phát ra tiếng "beep" để cảnh báo
//			JOptionPane.showMessageDialog(null, "Không tìm thấy");
//
//		}
//	}
//
//	private void TimKiemTheoMaPDP() {
//		String ma = txtmaPhieu.getText();
//		dsPDP = DAO_PDP.layPhieuDatPhongTuongDoi_TheoMaPhieuDatPhong(ma);
//
//		if (dsPDP != null) {
//			clearTable();
//			for (PhieuDatPhong pdp : dsPDP) {
//				KhachHang kh = DAO_KH.layKhachHang_TheoMaKhachHang(pdp.getKhachHang().getMaKhachHang());
//				NhanVien nv = DAO_NV.timNhanVien_TheoMaNhanVien(pdp.getNhanVien().getMaNhanVien());
//				Phong p = DAO_P.timPhong_TheoMaPhong(pdp.getPhong().getMaPhong());
//
//				Object[] rowData = { pdp.getMaPhieuDat(), pdp.getThoiGianNhanPhong(), kh.getHoTen(),
//						kh.getSoDienThoai(), p.getTenPhong(), pdp.getTrangThai(), pdp.getMoTa() };
//				model.addRow(rowData);
//
//			}
//		} else {
//			Toolkit.getDefaultToolkit().beep(); // Phát ra tiếng "beep" để cảnh báo
//			JOptionPane.showMessageDialog(null, "Không tìm thấy");
//
//		}
//	}

	public void HuyPhieu() {
		row = table_PhieuDatPhong.getSelectedRow();
		String maPDP = ((String) model.getValueAt(row, 0)).trim();
		pdp = DAO_PDP.layPhieuDatPhong_TheoMaPhieuDat(maPDP);
		try {
			if (pdp.getTrangThai().equals("Chờ nhận phòng")) {
				int choice = JOptionPane.showConfirmDialog(this,
						"Bạn có chắc chắn muốn hủy phiếu đặt phòng " + maPDP + " không ?", "Cảnh báo",
						JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					if (DAO_PDP.HuyPhieuDatPhong(maPDP)) {
						JOptionPane.showMessageDialog(null, "Hủy thành công phiếu " + maPDP);
						clearTable();
						DocDuLieuTrenSQL();
					}
				}

			} else {
				Toolkit.getDefaultToolkit().beep(); // Phát ra tiếng "beep" để cảnh báo
				JOptionPane.showMessageDialog(null, "Phiếu " + maPDP + " không thể hủy!");
			}

		} catch (Exception e) {
			Toolkit.getDefaultToolkit().beep(); // Phát ra tiếng "beep" để cảnh báo
			JOptionPane.showMessageDialog(null, "Phiếu " + maPDP + "hủy thất bại!");
		}

	}
}
