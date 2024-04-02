package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dao.ThongTinDichVu_DAO;
import entity.DichVu;
import entity.ThongTinDichVu;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

public class JPanel_CardDichVu extends JPanel {
	private DichVu dichVu;
	private ThongTinDichVu ttdv;
	private String hexColor_Blue1 = "#054A91";
	private String hexColor_Blue2 = "#3E7CB1";
	private String hexColor_Blue3 = "#81A4CD";
	private String hexColor_Blue4 = "#DBE4EE";
	private final DecimalFormat dcf = new DecimalFormat("#,##0 VND");
	private int width = 190;
	private int height = 230;
	private ThongTinDichVu_DAO DAO_TTDV;
	private JLabel img_show_panel;
	private ImageIcon originalIcon;

	public JPanel_CardDichVu(DichVu dichVu) {
		setBackground(new Color(255, 255, 255));
		this.dichVu = dichVu;

		setPreferredSize(new Dimension(width, height));
		setLayout(null);

		JPanel main_card = new JPanel();
		main_card.setBackground(new Color(255, 255, 255));
		main_card.setBounds(18, 18, 150, 150);
		add(main_card);
		main_card.setLayout(null);

		img_show_panel = new JLabel();
		img_show_panel.setBounds(0, 0, 150, 150);

//		---mới 
		ImageIcon originalIcon = new ImageIcon(
				JPanel_CardDichVu.class.getResource("/img/" + dichVu.getThongTinDichVu().getHinhAnh()));
		Image originalImage = originalIcon.getImage();
		Image resizedImage = originalImage.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon = new ImageIcon(resizedImage);
		img_show_panel.setIcon(resizedIcon);
		main_card.add(img_show_panel);

		JLabel lbl_giaTien = new JLabel(dcf.format(dichVu.getDonGia()));
		lbl_giaTien.setForeground(new Color(255, 69, 0));
		lbl_giaTien.setBounds(18, 190, 140, 20);
		add(lbl_giaTien);
		lbl_giaTien.setFont(new Font("Segoe UI", Font.BOLD, 12));

		JLabel lbl_tenDichVu = new JLabel(dichVu.getTenDichVu());
		lbl_tenDichVu.setForeground(new Color(0, 102, 153));
		lbl_tenDichVu.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lbl_tenDichVu.setBounds(18, 170, 150, 21);
		add(lbl_tenDichVu);

		JButton btn_Xem = new JButton("Chi tiết");
		btn_Xem.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btn_Xem.setForeground(new Color(255, 255, 255));
		btn_Xem.setBackground(new Color(50, 205, 50));
		btn_Xem.setBounds(87, 190, 80, 21);
		add(btn_Xem);

		btn_Xem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DAO_TTDV = new ThongTinDichVu_DAO();
				ttdv = DAO_TTDV
						.timThongTinDichVu_TheoMaThongTinDichVu(dichVu.getThongTinDichVu().getMaThongTinDichVu());
				String tenDV = dichVu.getTenDichVu();
				String donViTinh = dichVu.getDonViTinh();
				double donGia = dichVu.getDonGia();
				int soLuongTon = ttdv.getSoLuong() - ttdv.getSoLuongDaSuDung();
				String trangThai = dichVu.isTrangThai() ? "Còn hàng" : "Hết hàng";
				String mieuTa = ttdv.getMoTa();
				JDialog_XemThongDichVu modal_XemTTDV = new JDialog_XemThongDichVu();
				modal_XemTTDV.SetModal_XemThongTinDichVu(tenDV, donViTinh, donGia, soLuongTon, trangThai, mieuTa);
				modal_XemTTDV.setVisible(true);

			}
		});
	}

	public ImageIcon ResizeImage(String ImagePath) {
		ImageIcon MyImage = new ImageIcon(ImagePath);
		Image img = MyImage.getImage();
		Image newImg = img.getScaledInstance(img_show_panel.getWidth(), img_show_panel.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(newImg);
		return image;
	}
}