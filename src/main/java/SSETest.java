import com.mysql.cj.sse.Connection;
import com.mysql.cj.sse.DriverManager;
import com.mysql.cj.sse.ResultSet;
import com.mysql.cj.sse.Statement;

import java.sql.SQLException;


public class SSETest {
    public static void main(String[] args) {

        Connection conn = null;
        Statement st = null;
        ResultSet rt = null;
        try {
            //注册连接(可以写到一行里面)
            //com.mysql.cj.jdbc.Driver Driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(new com.mysql.cj.sse.Driver());
            //获取数据库连接
            conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:8888/points?serverTimezone=GMT%2B8","root","root");
            //获取数据库操作对象
            st = conn.createStatement();
            //执行sql语句
            String sql = "select * from table_0edaf31daa6130e2d9ffa5d9d1d8706d limit 10";
            rt = st.executeQuery(sql);
            //处理查询语句
            while(rt.next()){
                String age = rt.getString("age");
                String score = rt.getString("score");
                System.out.println(age + "," + score);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            //关闭连接
            if (rt != null) {
                try {
                    rt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            if (st != null) {
                try {
                    st.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }


        }
    }
}

