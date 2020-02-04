package jdbc;

import java.sql.*;

public class DBUtil {
    private static String drivername = "com.mysql.cj.jdbc.Driver";

    /**
     * ��̬����飬ע������?
     */
    static {
        try {
            Class.forName(drivername);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private String url = "jdbc:mysql://localhost:3306/MedicalXSM?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
    private String username = "root";
    private String password = "root";
    //Connection�ӿڣ���ָ�����ݿ������?
    private Connection conn;
    //�����ݿ⴫��sql���?
    private PreparedStatement pst;
    //�����?
    private ResultSet rs;

    /**
     * ��������
     */
    public void getConnection() {
        try {
            //DrivaerManager�࣬�������ݿ��е�������������
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * ��ѯ
     *
     * @param sql Ҫִ�в�ѯ��sql���?
     * @return ��ѯ���Ľ����?
     */
    public ResultSet cx(String sql, Object[] obj) {
        getConnection();
        try {
            pst = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                pst.setObject(i + 1, obj[i]);
            }
            rs = pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return rs;
        }
        return rs;
    }

    /**
     * ����ɾ����
     *
     * @param sql Ҫִ�е�sql���?
     * @return ����int���͵���ֵ���������ֵ�?0�������ʧ�ܣ��������ֵΪ1�������ɹ�
     */

    public int zsg(String sql, Object[] obj) {
        getConnection();
        int a = 0;
        try {
            pst = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                pst.setObject(i + 1, obj[i]);
            }
            a = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return a;
        }
        return a;
    }
	/*public int[] zsg2(String sql1,String sql2) {
		getConnection();
		int[] a = null;
		try {
			st=conn.createStatement();
			st.addBatch(sql1);
			st.addBatch(sql2);
			a=st.executeBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}*/

    /**
     * �ͷ���Դ
     */
    public void close() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
