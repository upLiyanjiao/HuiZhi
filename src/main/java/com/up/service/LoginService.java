package com.up.service;

import com.up.dao.userDao;
import com.up.entity.User;

import java.sql.SQLException;
//https://www.cnblogs.com/zhangyongl/p/6208993.html
public class LoginService {
    public boolean checkUser(String username,String passWord) throws SQLException {
//        在这里调用dao方法
        userDao dao = new userDao();
//      把名字传入对象中查询
        User user = dao.findUserByName(username);
        System.out.println(user);
//      如果找到了user并且自己输入的password跟数据库查出来的user.getpassword()一致，则返回true，否则返回false
        return user !=null&&passWord.equals(user.getPassWord())?true:false;
          /*if(user != null){
            if(password.equals(user.getUserPass())){
                return true;
            }
        }
        return false;*/
    }
}
