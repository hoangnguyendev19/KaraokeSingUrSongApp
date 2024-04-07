package dao;

import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.LoaiNhanVien;
import jakarta.persistence.EntityManager;
import other.ConvertObjToEntity;

public class LoaiNhanVien_DAO {
	private EntityManager em;

	public LoaiNhanVien_DAO() {
		em = ConnectDB.connect();
	}

	public ArrayList<LoaiNhanVien> layTatCaLoaiNhanVien() {
//		ArrayList<LoaiNhanVien> danhSachLoaiNhanVien = new ArrayList<LoaiNhanVien>();
//		try {
//			ConnectDB.getInstance();
//			Connection con = ConnectDB.getConnection();
//			String sql = "SELECT * FROM LoaiNhanVien";
//			Statement statement = con.createStatement();
//			ResultSet rs = statement.executeQuery(sql);
//			
//			while (rs.next()) {
//				
//				String maLoaiNhanVien = rs.getString("maLoaiNhanVien");
//				String tenLoaiNhanVien = rs.getString("tenLoaiNhanVien");
//				LoaiNhanVien loaiNhanVien = new LoaiNhanVien(maLoaiNhanVien, tenLoaiNhanVien);
//				danhSachLoaiNhanVien.add(loaiNhanVien);
//				
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return danhSachLoaiNhanVien;

		try {
			String sql = "SELECT * FROM LoaiNhanVien";

			List<Object> listObj = em.createNativeQuery(sql, LoaiNhanVien.class).getResultList();

			ArrayList<LoaiNhanVien> list = new ArrayList<LoaiNhanVien>();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	public LoaiNhanVien layLoaiNhanVien_TheoMaLoaiNhanVien(String maLoaiNV) {
//		LoaiNhanVien loaiNhanVien = null;
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		try {
//			String sql = "SELECT * FROM LoaiNhanVien WHERE maLoaiNhanVien = ?";
//			statement = con.prepareStatement(sql);
//			statement.setString(1, maLoaiNV);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				String maLoaiNhanVien = rs.getString("maLoaiNhanVien");
//				String tenLoaiNhanVien = rs.getString("tenLoaiNhanVien");
//				loaiNhanVien = new LoaiNhanVien(maLoaiNhanVien, tenLoaiNhanVien);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//
//		} finally {
//			try {
//				statement.close();
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//		return loaiNhanVien;

		try {
			String sql = "SELECT * FROM LoaiNhanVien WHERE maLoaiNhanVien = ?";
			Object obj = em.createNativeQuery(sql, LoaiNhanVien.class).setParameter(1, maLoaiNV).getResultList().stream().findFirst().orElse(null);
			
			LoaiNhanVien loaiNhanVien = (LoaiNhanVien) obj;
			return loaiNhanVien;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			em.close();
			return null;
		}
	}

	/**
	 * @param xTenLoaiNV
	 * @return loaiNhanVien
	 */
	public LoaiNhanVien layLoaiNhanVien_TheoTenLoaiNhanVien(String xTenLoaiNV) {
//		LoaiNhanVien loaiNhanVien = null;
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		try {
//			String sql = "SELECT * FROM LoaiNhanVien WHERE tenLoaiNhanVien = ?";
//			statement = con.prepareStatement(sql);
//			statement.setString(1, xTenLoaiNV);
//			ResultSet rs = statement.executeQuery();
//			while (rs.next()) {
//				String maLoaiNhanVien = rs.getString("maLoaiNhanVien");
//				String tenLoaiNhanVien = rs.getString("tenLoaiNhanVien");
//				loaiNhanVien = new LoaiNhanVien(maLoaiNhanVien, tenLoaiNhanVien);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//
//		} finally {
//			try {
//				statement.close();
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//		return loaiNhanVien;

		try {
			String sql = "SELECT * FROM LoaiNhanVien WHERE tenLoaiNhanVien = ?";
			Object obj = em.createNativeQuery(sql, LoaiNhanVien.class).setParameter(1, xTenLoaiNV).getResultList().stream().findFirst().orElse(null);

			LoaiNhanVien loaiNhanVien = (LoaiNhanVien) obj;
			return loaiNhanVien;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			em.close();
			return null;
		}
	}


	public boolean taoLoaiNhanVien(LoaiNhanVien loaiNhanVien) {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		int n = 0;
//		try {
//			statement = con.prepareStatement("INSERT INTO LoaiNhanVien values(?,?)");
//			statement.setString(1, loaiNhanVien.getMaLoaiNhanVien());
//			statement.setString(2, loaiNhanVien.getTenLoaiNhanVien());
//			n = statement.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				statement.close();
//			} catch (Exception e2) {
//				// TODO: handle exception
//				e2.printStackTrace();
//			}
//		}
//		return n > 0;

		try {
			String sql = "INSERT INTO LoaiNhanVien values(?,?)";
			em.getTransaction().begin();
			int result = em.createNativeQuery(sql).setParameter(1, loaiNhanVien.getMaLoaiNhanVien())
					.setParameter(2, loaiNhanVien.getTenLoaiNhanVien()).executeUpdate();
			
			if(result == 0) {
				return false;
			}
			
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			return false;
		} finally {
			em.close();
		}
	}

	public boolean capNhatLoaiNhanVien(LoaiNhanVien loaiNhanVien) {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		int n = 0;
//		try {
//			statement = con.prepareStatement(
//					"UPDATE LoaiNhanVien SET tenLoaiNhanVien = ?"
//							+ " WHERE maLoaiNhanVien = ?");		
//			statement.setString(1, loaiNhanVien.getTenLoaiNhanVien());
//			statement.setString(2, loaiNhanVien.getMaLoaiNhanVien());
//			n = statement.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				statement.close();
//			} catch (Exception e2) {
//				// TODO: handle exception
//				e2.printStackTrace();
//			}
//		}
//		return n > 0;

		try {
			String sql = "UPDATE LoaiNhanVien SET tenLoaiNhanVien = ? WHERE maLoaiNhanVien = ?";
			em.getTransaction().begin();
			int result = em.createNativeQuery(sql).setParameter(1, loaiNhanVien.getTenLoaiNhanVien())
					.setParameter(2, loaiNhanVien.getMaLoaiNhanVien()).executeUpdate();
			
            if(result == 0) {
				return false;
            }
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}
	}

	public boolean xoaLoaiNhanVien(LoaiNhanVien loaiNhanVien) {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		int n = 0;
//		try {
//			statement = con.prepareStatement("DELETE FROM LoaiNhanVien" + " WHERE maLoaiNhanVien = ?");
//			statement.setString(1, loaiNhanVien.getMaLoaiNhanVien());
//			n = statement.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				statement.close();
//			} catch (Exception e2) {
//				// TODO: handle exception
//				e2.printStackTrace();
//			}
//		}
//		return n > 0;
		
		try {
			String sql = "DELETE FROM LoaiNhanVien WHERE maLoaiNhanVien = ?";
			em.getTransaction().begin();
			int result = em.createNativeQuery(sql).setParameter(1, loaiNhanVien.getMaLoaiNhanVien()).executeUpdate();
			
			if (result == 0) {
				return false;
			}
			
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			return false;
		} finally {
			em.close();
		}	
	}
}
