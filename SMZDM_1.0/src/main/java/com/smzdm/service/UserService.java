package com.smzdm.service;

import com.smzdm.bean.User;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

public interface UserService {

    HashMap register(User user, HttpSession session);


    User selectUserByUsernameAndPassword(String username, String password);

    User selectUserByUsername(String username);

    User selectUserById(int id);

}
