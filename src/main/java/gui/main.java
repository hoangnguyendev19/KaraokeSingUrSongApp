package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.formdev.flatlaf.FlatLightLaf;

import connectDB.ConnectDB;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class main extends JFrame {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			private JFrame_DangNhap app;
			private JFrame_ThuNgan thuNgan;

			public void run() {
				try {
					EntityManager em = ConnectDB.connect();
					EntityTransaction tr = em.getTransaction();
					try {
						tr.begin();
						
						FlatLightLaf.setup();
						app = new JFrame_DangNhap();
						app.setVisible(true);
						
						tr.commit();
					} catch (Exception e) {
						e.printStackTrace();
						tr.rollback();
					}
					


				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}