package com.up.dao;

import com.up.entity.User;
import com.up.util. JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userDao {
    //    根据用户名查找用户
    public User findUserByName(String userName) throws SQLException {
//        编写sql语句
        String sql = "select * from userinfo where userName=?";
//        开启数据库连接
        Connection conn =  JdbcUtils.getMysqlConnection();
//        结果集对象
        ResultSet rs = null;
        PreparedStatement pre = null;
        User user = new User();
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1, userName);
            rs = pre.executeQuery();
            if (rs.next()) {
                user.setUid(rs.getInt(1));
                user.setUserName(rs.getString(2));
                user.setPassWord(rs.getString(3));
            }
            if (pre != null) {
                pre.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
             JdbcUtils.closeResource(pre, rs,conn);
        }
        return user;
    }

    //    添加用户
    public int addUser(User user) throws SQLException {

        String sql = "insert into userinfo values(?,?,?)";
        Connection conn =  JdbcUtils.getMysqlConnection();
        ResultSet rs = null;
        PreparedStatement pre = null;
        int i = 0;
        try {
            pre = conn.prepareStatement(sql);
            pre.setInt(1, user.getUid());
            pre.setString(2, user.getUserName());
            pre.setString(3, user.getPassWord());
            i = pre.executeUpdate();
            if (pre != null) {
                pre.close();
            }
            if(rs!=null){
                rs.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeResource(pre,null,conn);
        }
        return i;
    }

/*    public static void main(String[] args) {
        userDao userdao = new userDao();
        try {
            User i = userdao.findUserByName("zhang");
            System.out.println(i.getPassWord());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}
