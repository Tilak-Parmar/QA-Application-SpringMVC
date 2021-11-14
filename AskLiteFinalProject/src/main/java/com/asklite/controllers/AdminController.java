/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asklite.controllers;

import com.asklite.DAO.QuestionDAO;
import com.asklite.DAO.UserDAO;
import com.asklite.pojo.Question;
import com.asklite.pojo.User;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author tilak
 */
@Controller
public class AdminController {

    @GetMapping("/adminhome.htm")
    public ModelAndView login(HttpServletRequest request,
            HttpServletResponse response, HttpSession session, QuestionDAO queDao) {
        if (session.getAttribute("admin") == null || session.getAttribute("admin").equals("")) {
            return new ModelAndView("admin/adminlogin");
        } else if (request.getParameter("queID") != null) {
            int queID = Integer.valueOf(request.getParameter("queID"));
            try {
                System.out.println("The fetched queID is " + queID);
                Question que = queDao.searchQuestionbyID(queID);
                que.setApproved(true);
                queDao.updateQuestion(que);
            } catch (Exception e) {
                request.setAttribute("errormsg", "You're attempting to approve a non-existing question. Please hit the back button.");
                return new ModelAndView("error");
            }
        }
            List<Question> quelist = queDao.getUnapprovedQuestions();
            return new ModelAndView("admin/adminindex", "questions", quelist);
    }

    @PostMapping("/adminhome.htm")
    public String index(HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session, QuestionDAO queDao) {
        String email = request.getParameter("email");
        email = email.replace("<", "_");
        email = email.replace(">", "_");

        String password = request.getParameter("pwd");
        password = password.replace("<", "_");
        password = password.replace(">", "_");

        if (email.equalsIgnoreCase("tilak@gmail.com") && password.equals("admin")) {
            session.setAttribute("admin", email);
            List<Question> quelist = queDao.getUnapprovedQuestions();
            request.setAttribute("questions", quelist);
            return "admin/adminindex";
        } else {
            request.setAttribute("error", "true");
            request.setAttribute("errormsg", "Please check the username and password");
            return "admin/adminlogin";
        }
    }

    @GetMapping("/adminlogout.htm")
    public String logout(HttpSession session) {
        session.removeAttribute("admin");
        return "admin/adminlogin";
    }

    @GetMapping("/adminUserList.htm")
    public ModelAndView getUserList(HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session, UserDAO userDao) {
        if (session.getAttribute("admin") == null || session.getAttribute("admin").equals("")) {
            return new ModelAndView("admin/adminlogin");
        } else if (request.getParameter("userID") != null) {
            int userID = Integer.valueOf(request.getParameter("userID"));
            try {
                User user = userDao.searchUserbyID(userID);
                userDao.deleteUser(user);
            } catch (IllegalArgumentException e) {

                request.setAttribute("errormsg", "You're attempting to delete a non-existing user. Please hit the back button.");
                return new ModelAndView("error");
            }
        }
            List<User> userlist = userDao.getUsers();
            return new ModelAndView("admin/adminUserList", "users", userlist);
    }
}
