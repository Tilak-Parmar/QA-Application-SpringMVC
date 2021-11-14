/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asklite.controllers;

import com.asklite.DAO.AnswerDAO;
import com.asklite.DAO.QuestionDAO;
import com.asklite.DAO.UserDAO;
import com.asklite.mailer.Mailer;
import com.asklite.pojo.Answer;
import com.asklite.pojo.Question;
import com.asklite.pojo.User;
import java.io.IOException;
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
public class LoginController {

    public LoginController() {
    }

    @GetMapping("/index.htm")
    public ModelAndView index(HttpServletRequest request,
            HttpServletResponse response, HttpSession session, UserDAO userDao, QuestionDAO queDao, AnswerDAO ansDao) {
        request.setAttribute("error", "false");
        List<Question> quelist = queDao.getApprovedQuestions();
        for (Question que : quelist) {
            que.setAnswersList((List<Answer>) ansDao.getAnswersbyQueID(que.getID()));
        }
        request.setAttribute("userDao", userDao);
        if (session.getAttribute("user") == null || session.getAttribute("user").equals("")) {
            return new ModelAndView("index", "questions", quelist);
        } else {
            return new ModelAndView("login", "questions", quelist);
        }
    }

    @PostMapping("/index")
    public ModelAndView login(HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session, UserDAO userDao, QuestionDAO queDao, AnswerDAO ansDao) {

        String email = request.getParameter("email");
        email = email.replace("<", "_");
        email = email.replace(">", "_");

        String password = request.getParameter("password");
        password = password.replace("<", "_");
        password = password.replace(">", "_");

        User user = userDao.verifyUser(email, password);
        List<Question> quelist = queDao.getApprovedQuestions();
        for (Question que : quelist) {
            que.setAnswersList((List<Answer>) ansDao.getAnswersbyQueID(que.getID()));
        }
        request.setAttribute("userDao", userDao);
        if (user != null) {
            session.setAttribute("user", user);
            return new ModelAndView("login", "questions", quelist);
        } else {
            request.setAttribute("error", "true");
            request.setAttribute("errormsg", "Please check the username and password");
            return new ModelAndView("index", "questions", quelist);
        }
    }

    @GetMapping("/logout.htm")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, HttpSession session, UserDAO userDao, QuestionDAO queDao, AnswerDAO ansDao) {
        session.removeAttribute("user");
        List<Question> quelist = queDao.getApprovedQuestions();
        for (Question que : quelist) {
            que.setAnswersList((List<Answer>) ansDao.getAnswersbyQueID(que.getID()));
        }
        request.setAttribute("userDao", userDao);
        return new ModelAndView("index", "questions", quelist);
    }

    @GetMapping("/signup.htm")
    public void getSignUpError(HttpServletRequest request,
            HttpServletResponse response, HttpSession session, UserDAO userDao) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    @PostMapping("/signup.htm")
    public ModelAndView signUp(HttpServletRequest request,
            HttpServletResponse response, HttpSession session, UserDAO userDao, QuestionDAO queDao, AnswerDAO ansDao) {

        User user = new User();

        String fname = request.getParameter("fname");
        fname = fname.replace("<", "_");
        fname = fname.replace(">", "_");
        user.setFirstName(fname);

        String lname = request.getParameter("lname");
        lname = lname.replace("<", "_");
        lname = lname.replace(">", "_");
        user.setLastName(lname);

        String email = request.getParameter("email");
        email = email.replace("<", "_");
        email = email.replace(">", "_");
        user.setEmail(email);

        String pwd = request.getParameter("pwd");
        pwd = pwd.replace("<", "_");
        pwd = pwd.replace(">", "_");
        user.setPassword(pwd);

        if (userDao.UserExists(email)) {
            request.setAttribute("error", "true");
            request.setAttribute("errormsg", "Email already exists. Please login to continue...");
            List<Question> quelist = queDao.getApprovedQuestions();
            for (Question que : quelist) {
                que.setAnswersList((List<Answer>) ansDao.getAnswersbyQueID(que.getID()));
            }
            request.setAttribute("userDao", userDao);
            return new ModelAndView("index", "questions", quelist);
        } else {
            userDao.addUser(user);
            session.setAttribute("user", user);
            request.setAttribute("msg", "Success! Logging you in.");
            String subject = "Thank you for registering with us!";
            String body = "AskLite welcomes you " + fname + " " + lname + "! We hope you have a great experience.";
            Mailer.send(email, subject, body);
            return new ModelAndView("signup");
        }
    }

}
