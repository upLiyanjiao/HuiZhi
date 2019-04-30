package com.up.servlet;

import com.up.dao.userDao;
import com.up.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
//    获取从页面中提交过来的数据
        int id = Integer.parseInt(request.getParameter("uid"));
        String username = request.getParameter("userName");
        String password = request.getParameter("passWord");
        User user = new User();
        user.setUid(id);
        user.setUserName(username);
        user.setPassWord(password);
//      调用dao方法
        userDao dao = new userDao();
        try {
            int i = dao.addUser(user);
            if (i == 1) {
                request.getRequestDispatcher("index.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
