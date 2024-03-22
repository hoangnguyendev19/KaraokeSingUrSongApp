package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.formdev.flatlaf.FlatLightLaf;

import connectDB.ConnectDB;
import dao.DichVu_DAO;
import dao.LoaiPhong_DAO;
import dao.ThongTinDichVu_DAO;
import entity.DichVu;
import entity.LoaiPhong;
import entity.ThongTinDichVu;
import other.HelpRamDomKH;
import other.HelpRamDomMa;
import other.HelpRanDomDichVu;
import other.HelpValidate;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.Color;
import java.awt.Component;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import com.toedter.calendar.JDateChooser;

public class JDialog_ThemDichVu extends JFrame {

	private JPanel contentPane;
	private JTextField txt_maDichVu;
	private JTextField txt_tenDichVu;
	private JTextField txt_SoLuong;
	private JTextField txt_GiaTien;
	private JTextArea txtA_moTa;
	private JLabel img_show_panel;
	private String pathImg = "src/img";

	private JTextField txt_soLuongDaSuDung;
	private SimpleDateFormat dateFormat_YMD = new SimpleDateFormat("yyyy-MM-dd");
	private JDateChooser dateChooser_ngayHetHan;
	private JDateChooser dateChooser_ngayNhap;
	private Date ngayNhap;

	private String hexColor_Blue1 = "#054A91";
	private String hexColor_Blue2 = "#3E7CB1";
	private String hexColor_Blue3 = "#81A4CD";
	private String hexColor_Blue4 = "#DBE4EE";
	private String hexColor_Orange = "#F17300";
	private String hexColor_Red = "#E11F1F";
	private String hexColor_Green = "#4BAC4D";

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */

	public JDialog_ThemDichVu() {
		setFont(new Font("Segoe UI", Font.PLAIN, 13));
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(JDialog_CapNhatDichVu.class.getResource("/icon/microphone.png")));
		setTitle("SING UR SONG");
		setBounds(100, 100, 1024, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pnl_TieuDe = new JPanel();
		pnl_TieuDe.setBackground(Color.WHITE);
		pnl_TieuDe.setBounds(22, 11, 230, 25);
		contentPane.add(pnl_TieuDe);
		pnl_TieuDe.setLayout(null);

		JLabel lbl_Title = new JLabel("THÊM DỊCH VỤ MỚI");
		lbl_Title.setForeground(Color.decode(hexColor_Blue1));
		lbl_Title.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_Title.setBounds(0, 0, 278, 25);
		lbl_Title.setFont(new Font("Segoe UI", Font.BOLD, 17));
		pnl_TieuDe.add(lbl_Title);

		JPanel pnl_Anh = new JPanel();
		pnl_Anh.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pnl_Anh.setBackground(Color.WHITE);
		pnl_Anh.setBounds(26, 50, 180, 180);
		contentPane.add(pnl_Anh);
		pnl_Anh.setLayout(null);

		img_show_panel = new JLabel();
		img_show_panel.setBounds(10, 11, 160, 158);
		pnl_Anh.add(img_show_panel);

		JPanel pnl_ThongTin = new JPanel();
		pnl_ThongTin.setBackground(Color.WHITE);
		pnl_ThongTin.setBounds(224, 50, 776, 292);
		;
		contentPane.add(pnl_ThongTin);
		pnl_ThongTin.setLayout(null);

		JPanel pnl_MaLoaiPhong = new JPanel();
		pnl_MaLoaiPhong.setBackground(Color.WHITE);
		pnl_MaLoaiPhong.setBounds(10, 5, 350, 35);
		pnl_ThongTin.add(pnl_MaLoaiPhong);
		pnl_MaLoaiPhong.setLayout(null);

		JLabel lbl_MaDichVu = new JLabel("Mã dịch vụ");
		lbl_MaDichVu.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_MaDichVu.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lbl_MaDichVu.setBounds(0, 0, 110, 25);
		pnl_MaLoaiPhong.add(lbl_MaDichVu);

		txt_maDichVu = new JTextField();
		txt_maDichVu.setBounds(125, 0, 225, 25);
		pnl_MaLoaiPhong.add(txt_maDichVu);
		txt_maDichVu.setEditable(false);
		txt_maDichVu.setColumns(10);

		JPanel pnl_TenLoaiPhong = new JPanel();
		pnl_TenLoaiPhong.setBackground(Color.WHITE);
		pnl_TenLoaiPhong.setLayout(null);
		pnl_TenLoaiPhong.setBounds(10, 50, 350, 35);
		pnl_ThongTin.add(pnl_TenLoaiPhong);

		JLabel lbl_TenDichVu = new JLabel("Tên dịch vụ");
		lbl_TenDichVu.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_TenDichVu.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lbl_TenDichVu.setBounds(0, 0, 110, 25);
		pnl_TenLoaiPhong.add(lbl_TenDichVu);

		txt_tenDichVu = new JTextField();
		txt_tenDichVu.setColumns(10);
		txt_tenDichVu.setBounds(125, 0, 225, 25);
		pnl_TenLoaiPhong.add(txt_tenDichVu);

		JPanel pnl_SoLuongKhachToiDa = new JPanel();
		pnl_SoLuongKhachToiDa.setBackground(Color.WHITE);
		pnl_SoLuongKhachToiDa.setLayout(null);
		pnl_SoLuongKhachToiDa.setBounds(405, 5, 360, 25);
		pnl_ThongTin.add(pnl_SoLuongKhachToiDa);

		JLabel lbl_SoLuong = new JLabel("Số lượng");
		lbl_SoLuong.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_SoLuong.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lbl_SoLuong.setBounds(0, 0, 143, 25);
		pnl_SoLuongKhachToiDa.add(lbl_SoLuong);

		txt_SoLuong = new JTextField();
		txt_SoLuong.setColumns(10);
		txt_SoLuong.setBounds(153, 0, 197, 25);
		pnl_SoLuongKhachToiDa.add(txt_SoLuong);

		JPanel pnl_GiaTien = new JPanel();
		pnl_GiaTien.setLayout(null);
		pnl_GiaTien.setBackground(Color.WHITE);
		pnl_GiaTien.setBounds(10, 95, 350, 34);
		pnl_ThongTin.add(pnl_GiaTien);

		JLabel lbl_GiaiTien = new JLabel("Giá tiền");
		lbl_GiaiTien.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_GiaiTien.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lbl_GiaiTien.setBounds(0, 0, 110, 25);
		pnl_GiaTien.add(lbl_GiaiTien);

		txt_GiaTien = new JTextField();
		txt_GiaTien.setColumns(10);
		txt_GiaTien.setBounds(125, 0, 225, 25);
		pnl_GiaTien.add(txt_GiaTien);

		JButton btn_Luu = new JButton("Thêm");
		btn_Luu.setIcon(new ImageIcon(JDialog_ThemDichVu.class.getResource("/icon/add_16px.png")));
		btn_Luu.setBackground(Color.decode(hexColor_Green));
		btn_Luu.setForeground(new Color(255, 255, 255));
		btn_Luu.setBounds(532, 253, 110, 30);
		pnl_ThongTin.add(btn_Luu);
		btn_Luu.setFont(new Font("Segoe UI", Font.BOLD, 13));

		JButton btn_BoQua = new JButton("Thoát");
		btn_BoQua.setIcon(new ImageIcon(JDialog_ThemDichVu.class.getResource("/icon/exit_16px.png")));
		btn_BoQua.setBackground(Color.decode(hexColor_Blue2));
		btn_BoQua.setForeground(new Color(255, 255, 255));
		btn_BoQua.setBounds(645, 253, 110, 30);
		pnl_ThongTin.add(btn_BoQua);
		btn_BoQua.setFont(new Font("Segoe UI", Font.BOLD, 13));

		JPanel pnl_GiaTien_1 = new JPanel();
		pnl_GiaTien_1.setLayout(null);
		pnl_GiaTien_1.setBackground(Color.WHITE);
		pnl_GiaTien_1.setBounds(10, 139, 350, 39);
		pnl_ThongTin.add(pnl_GiaTien_1);

		JLabel lbl_TrangThai = new JLabel("Trạng thái");
		lbl_TrangThai.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_TrangThai.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lbl_TrangThai.setBounds(0, 0, 110, 25);
		pnl_GiaTien_1.add(lbl_TrangThai);

		JComboBox cbox_trangThai = new JComboBox();

		cbox_trangThai.addItem("Còn hàng");
		cbox_trangThai.addItem("Hết hàng");

		cbox_trangThai.setBounds(126, 3, 224, 25);
		pnl_GiaTien_1.add(cbox_trangThai);

		JPanel pnl_GiaTien_2 = new JPanel();
		pnl_GiaTien_2.setLayout(null);
		pnl_GiaTien_2.setBackground(Color.WHITE);
		pnl_GiaTien_2.setBounds(405, 95, 360, 34);
		pnl_ThongTin.add(pnl_GiaTien_2);

		JLabel lbl_NgayNhap = new JLabel("Ngày nhập");
		lbl_NgayNhap.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_NgayNhap.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lbl_NgayNhap.setBounds(0, 0, 110, 25);
		pnl_GiaTien_2.add(lbl_NgayNhap);

		dateChooser_ngayNhap = new JDateChooser();
		dateChooser_ngayNhap.setBounds(155, 0, 195, 25);
		pnl_GiaTien_2.add(dateChooser_ngayNhap);

		JPanel pnl_GiaTien_2_1 = new JPanel();
		pnl_GiaTien_2_1.setLayout(null);
		pnl_GiaTien_2_1.setBackground(Color.WHITE);
		pnl_GiaTien_2_1.setBounds(405, 139, 360, 39);
		pnl_ThongTin.add(pnl_GiaTien_2_1);

		JLabel lbl_NgayHetHan = new JLabel("Ngày hết hạn");
		lbl_NgayHetHan.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_NgayHetHan.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lbl_NgayHetHan.setBounds(0, 0, 110, 25);
		pnl_GiaTien_2_1.add(lbl_NgayHetHan);

		dateChooser_ngayHetHan = new JDateChooser();
		dateChooser_ngayHetHan.setBounds(155, 0, 195, 25);
		pnl_GiaTien_2_1.add(dateChooser_ngayHetHan);

		JPanel pnl_GiaTien_2_2 = new JPanel();
		pnl_GiaTien_2_2.setLayout(null);
		pnl_GiaTien_2_2.setBackground(Color.WHITE);
		pnl_GiaTien_2_2.setBounds(405, 50, 350, 30);
		pnl_ThongTin.add(pnl_GiaTien_2_2);

		JLabel lbl_SoLuongDaSuDung = new JLabel("Số lượng đã sử dụng");
		lbl_SoLuongDaSuDung.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_SoLuongDaSuDung.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lbl_SoLuongDaSuDung.setBounds(0, 0, 143, 25);
		pnl_GiaTien_2_2.add(lbl_SoLuongDaSuDung);

		txt_soLuongDaSuDung = new JTextField();
		txt_soLuongDaSuDung.setColumns(10);
		txt_soLuongDaSuDung.setBounds(153, 1, 207, 25);
		pnl_GiaTien_2_2.add(txt_soLuongDaSuDung);

		JPanel pnl_GiaTien_1_1 = new JPanel();
		pnl_GiaTien_1_1.setLayout(null);
		pnl_GiaTien_1_1.setBackground(Color.WHITE);
		pnl_GiaTien_1_1.setBounds(10, 188, 745, 54);
		pnl_ThongTin.add(pnl_GiaTien_1_1);

		JLabel lbl_moTa = new JLabel("Mô Tả");
		lbl_moTa.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_moTa.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lbl_moTa.setBounds(0, 0, 110, 25);
		pnl_GiaTien_1_1.add(lbl_moTa);

		txtA_moTa = new JTextArea();
		txtA_moTa.setBounds(137, 11, 598, 32);
		pnl_GiaTien_1_1.add(txtA_moTa);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(127, 0, 619, 53);
//		panel.add(txtA_moTa);
		pnl_GiaTien_1_1.add(panel);

		JButton btn_ChonAnh = new JButton("Chọn ảnh");
		btn_ChonAnh.setIcon(new ImageIcon(JDialog_ThemDichVu.class.getResource("/icon/upload.png")));
		btn_ChonAnh.setSelectedIcon(new ImageIcon(JDialog_ThemDichVu.class.getResource("/icon/upload.png")));
		btn_ChonAnh.setBounds(27, 240, 179, 32);
		contentPane.add(btn_ChonAnh);
		btn_ChonAnh.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btn_ChonAnh.setForeground(new Color(255, 255, 255));

		btn_ChonAnh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				img_show_panel.setIcon(ResizeImage(chooseFileEvent("image")));
			}

			public ImageIcon ResizeImage(String ImagePath) {
				ImageIcon MyImage = new ImageIcon(ImagePath);
				Image img = MyImage.getImage();
				Image newImg = img.getScaledInstance(img_show_panel.getWidth(), img_show_panel.getHeight(),
						Image.SCALE_SMOOTH);
				ImageIcon image = new ImageIcon(newImg);
				return image;
			}
		});
		btn_ChonAnh.setBackground(Color.decode(hexColor_Green));

		btn_BoQua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});

		btn_Luu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// REGEX
				if (Validate() == true) {

					// Table Dich vu

					String tenDichVu = txt_tenDichVu.getText();
					int soLuong = Integer.parseInt(txt_SoLuong.getText());
					String donViTinh = "VND";

					boolean trangThai = cbox_trangThai.getSelectedItem().toString().trim().equals("Còn hàng");
					double giaTien = Double.parseDouble(txt_GiaTien.getText());

					// Table Thong tin dich vu
					HelpRanDomDichVu helpMa = new HelpRanDomDichVu();
					String maDichVu = helpMa.taoMaDV();
					String maThongTinDichVu = helpMa.taoMaTTDV();

					txt_maDichVu.setText(maDichVu);
					int soLuongDaSuDung = Integer.parseInt(txt_soLuongDaSuDung.getText());
					Date ngayNhap = new Date((dateChooser_ngayNhap).getDate().getTime());
					Date ngayHetHan = new Date((dateChooser_ngayHetHan).getDate().getTime());

					File file = new File(pathImg);
					String fileName = file.getName().toString();
					String moTa = txtA_moTa.getText();

					ThongTinDichVu ttdv = new ThongTinDichVu(maThongTinDichVu, soLuong, soLuongDaSuDung, ngayNhap,
							ngayHetHan, moTa, fileName);
					DichVu dv = new DichVu(maDichVu, tenDichVu, donViTinh, giaTien, trangThai, ttdv);

					try {

						DichVu_DAO DAO_DV = new DichVu_DAO();
						ThongTinDichVu_DAO DAO_TTDV = new ThongTinDichVu_DAO();

						if (DAO_TTDV.taoThongTinDichVu(ttdv) && DAO_DV.taoDichVu(dv)) {
							JOptionPane.showMessageDialog(null, "Tạo dịch vụ thành công.");
							setVisible(false);
						} else {
							JOptionPane.showMessageDialog(null, "Tạo dịch vụ thất bại, vui lòng thử lại.");
						}

					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Tạo dịch vụ thất bại, vui lòng thử lại.");

					}

				}
			}
		});
	}

	public void setModal_ThemDichVu(DichVu dv) {

		dateChooser_ngayNhap.setDate(dv.getThongTinDichVu().getNgayNhap());
		dateChooser_ngayHetHan.setDate(dv.getThongTinDichVu().getNgayHetHan());
		txt_maDichVu.setText(dv.getMaDichVu());
		txt_GiaTien.setText(String.valueOf(dv.getDonGia()));
		txt_SoLuong.setText(String.valueOf(dv.getThongTinDichVu().getSoLuong()));
		txt_soLuongDaSuDung.setText(String.valueOf(dv.getThongTinDichVu().getSoLuongDaSuDung()));
		txt_tenDichVu.setText(dv.getTenDichVu());
		txtA_moTa.setText(dv.getThongTinDichVu().getMoTa());

//		txtA_moTa;

	}

	public String chooseFileEvent(String typeFile) {
		JFileChooser file = new JFileChooser();
		String path = "";
		file.setCurrentDirectory(new File("src/img"));

		FileNameExtensionFilter filterImage = new FileNameExtensionFilter("*.Images", "jpg", "gif", "png", "xlsx",
				"xls");
		FileNameExtensionFilter filterExcel = new FileNameExtensionFilter("xlsx", "xls");

		// Doc path image
		if (typeFile.equals("image")) {
			file.addChoosableFileFilter(filterImage);
		}
		// Doc path excel
		else if (typeFile.equals("excel")) {
			file.addChoosableFileFilter(filterExcel);
		}

		int result = file.showSaveDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {

			File selectedFile = file.getSelectedFile();
			path = selectedFile.getAbsolutePath();
			pathImg += path;
			return path;
		}

		else if (result == JFileChooser.CANCEL_OPTION) {

			JOptionPane.showMessageDialog(null, "Không tìm thấy file tải lên");
		}
		return path;
	}

	@SuppressWarnings("unused")
	public boolean Validate() {

		String tenDichVu = txt_tenDichVu.getText().trim();
		Date ngayNhap = null;
		Date ngayHetHan = null;
		try {
			if (new Date((dateChooser_ngayNhap).getDate().getTime()) != null) {
				ngayNhap = new Date((dateChooser_ngayNhap).getDate().getTime());
			}
			if (new Date((dateChooser_ngayHetHan).getDate().getTime()) != null) {
				ngayHetHan = new Date((dateChooser_ngayHetHan).getDate().getTime());
			}
		} catch (Exception e) {
			// TODO: handle exception
			if (tenDichVu.equals("") && txt_SoLuong.getText().trim().equals("")
					&& txt_soLuongDaSuDung.getText().trim().equals("") && ngayNhap == null && ngayHetHan == null) {
				JOptionPane.showMessageDialog(this, "Các trường không được bỏ trống");
				return false;
			}
		}

		File file = new File(pathImg);
		String fileName = file.getName().toString().trim();
		String moTa = txtA_moTa.getText();

		int soLuong = Integer.parseInt(txt_SoLuong.getText().trim());
		String donViTinh = "";

		double giaTien = Double.parseDouble(txt_GiaTien.getText().trim());
		int soLuongDaSuDung = Integer.parseInt(txt_soLuongDaSuDung.getText().trim());

		if (tenDichVu.equals("")) {
			JOptionPane.showMessageDialog(this, "Tên Dịch Vụ không được bỏ trống!");
			return false;
		} 
		if (txt_GiaTien.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Giá tiền dịch vụ không được bỏ trống!");
			return false;
		} else if (giaTien < 5000) {
			JOptionPane.showMessageDialog(this, "Giá tiền dịch vụ phải lớn hơn hoặc bằng 5.000VNĐ!");
			return false;
		}
		if (txt_soLuongDaSuDung.getText().trim().equals("")) {
			txt_soLuongDaSuDung.setText("0");
		}
		if (txt_SoLuong.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Số lượng tổng dịch vụ không được bỏ trống");
			return false;
		} else

		if (soLuong < soLuongDaSuDung) {
			JOptionPane.showMessageDialog(this, "Số lượng tổng dịch vụ phải lớn hơn số lượng đã sử dụng");
			return false;
		}

		if (fileName.equals("img")) {
			JOptionPane.showMessageDialog(this, "Ảnh dịch vụ không được bỏ trống!");
			return false;
		}

		try {
			java.util.Date currentDate = new java.util.Date();

			if (ngayNhap.after(currentDate)) {
				JOptionPane.showMessageDialog(this, "Ngày nhập hàng phải bé hơn hoặc bằng ngày hiện tại!");
				return false;
			}
			if (ngayHetHan.before(ngayNhap)) {
				JOptionPane.showMessageDialog(this, "Ngày hết hạn phải lớn hơn  ngày nhập hàng!");
				return false;
			}
			if (ngayHetHan.before(currentDate)) {
				JOptionPane.showMessageDialog(this, "Ngày hết hạn phải lớn hơn ngày hiện tại!");
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, "Ngày nhập , ngày hết hạn của dịch vụ không được để trống!");
			return false;
		}

		return true;
	}
}