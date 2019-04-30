package com.up.servlet;

import com.up.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void service(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
//        设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
//        获取从页面中提交过来的数据
        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");

        LoginService service = new LoginService();
//调用service 方法把用户名和密码传入service
        boolean flag = false;
        try {
            flag = service.checkUser(userName,passWord);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String msg = flag?"success":"error";
        request.setAttribute("msg",msg);
        if(msg=="success"){
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }else{
            System.out.println("账户名或密码错误");
        }
    }
}
