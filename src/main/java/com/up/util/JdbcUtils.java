package com.up.util;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
/**
 * 类作用:该类用于封装jdbc对数据库(打开、关流)操作！
 */
public class JdbcUtils {
    static Properties prs=null;
    //加载资源文件处理类、只加载一次、且最先要加载到内存堆中
    /*
     * static{}(静态代码块)与{}(非静态代码块)的异同点
        相同点：都是在JVM加载类时且在构造方法执行之前执行，在类中都可以定义多个，
    　　　　         一般在代码块中对一些static变量进行赋值。
        不同点：静态代码块在非静态代码块之前执行(静态代码块—>非静态代码块—>构造方法)。
    　　　　             静态代码块只在第一次new执行一次，之后不再执行，而非静态代码块在每new
    　　　　         一次就执行一次。非静态代码块可在普通方法中定义(不过作用不大)；而静态代码块不行。*/
    static {
        prs=new Properties();
        try {
            //加载数据库配置文件,如果加载xml,可用loadXML()
            prs.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("database.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * @describe 用于建立与mysql数据库之间的连接
     * @create at 2017年11月15日 下午6:51:35 by lzj
     * @Parameters1 无
     * @Returns Connection 数据库连接对象
     */
    public static Connection getMysqlConnection(){
        try {
            //注册mysql驱动类
            Class.forName(prs.getProperty("mysql.driver"));
            //将根据所传入url,用户名,密码所建立的mysql数据库连接对象返回
            return DriverManager.getConnection(
                    prs.getProperty("mysql.url"),
                    prs.getProperty("mysql.username"),
                    prs.getProperty("mysql.password"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("加载mysql驱动类时出错！");
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("建立数据库连接时出错！");
            return null;
        }
    }
    /**
     * @descibe 该静态方法用于关闭对数据库的流操作！(可重载)
     * 此处为了简单演示只写了一个关闭类，实际开发中该类重载很多次！
     * @create at 2017年11月15日 下午6:54:45 by lzj
     * @Parameters1 ps 数据库操作对象 、预编译
     * @Parameters2 conn 数据库连接对象
     * @Returns void
     */
    public static void closeResource(PreparedStatement ps, ResultSet rs, Connection conn){
        try {
            if(ps!=null) {
                ps.close();
            }
            if(rs!=null){
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ps流关闭异常！");
        }
        try {
            if(conn!=null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("conn流关闭异常！");
        }
    }
}
