package com.up;

import com.up.util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author lzj
 * @create 2017年11月15日 下午6:40:39
 * @describe
 */
public class test {

    /**
     * at 2017年11月15日 下午6:40:39 by lzj
     * @Parameters1 String[] args
     * @Returns void
     */
    public static void main(String[] args) throws SQLException {
        Connection conn = JdbcUtils.getMysqlConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        String sql="insert into userinfo (id,username,password) values (?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 666);
            ps.setString(2, "lzj");
            ps.setString(3, "7777");
            ps.execute();
            System.out.println("数据插入成功！");
        } catch (SQLException e) {
            e.printStackTrace();
            conn.rollback();
            System.out.println("数据插入失败！");
        }finally {
            JdbcUtils.closeResource(ps,rs, conn);
        }
    }
}