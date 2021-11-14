/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asklite.controllers;

import com.asklite.DAO.AnswerDAO;
import com.asklite.DAO.QuestionDAO;
import com.asklite.DAO.UserDAO;
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
public class UserController {

    @GetMapping("/questions.htm")
    public ModelAndView index(HttpServletRequest request,
            HttpServletResponse response, HttpSession session, UserDAO userDao, QuestionDAO queDao, AnswerDAO ansDao) {
        if (session.getAttribute("user") == null || session.getAttribute("user").equals("")) {
            request.setAttribute("error", "true");
            request.setAttribute("errormsg", "Please login to continue...");
            List<Question> quelist = queDao.getApprovedQuestions();
            for (Question que : quelist) {
                que.setAnswersList((List<Answer>) ansDao.getAnswersbyQueID(que.getID()));
            }
            request.setAttribute("userDao", userDao);
            return new ModelAndView("index", "questions", quelist);
        } else if (request.getParameter("queID") != null) {
            int queID = Integer.valueOf(request.getParameter("queID"));
            try {
                Question que = queDao.searchQuestionbyID(queID);
                queDao.deleteQuestion(que);
            } catch (IllegalArgumentException e) {
                request.setAttribute("errormsg", "You're attempting to delete a non-existing question. Please hit the back button.");
                return new ModelAndView("error");
            }
        }
        User user = (User) session.getAttribute("user");
        List<Question> quelist = queDao.getQuestionsbyID(user.getID());
        return new ModelAndView("questions", "questions", quelist);
    }

    @PostMapping("/questions.htm")
    public ModelAndView postQuestionEdit(HttpServletRequest request,
            HttpServletResponse response, HttpSession session, QuestionDAO queDao) {

        String question = request.getParameter("question");
        question = question.replace(">", "_");
        question = question.replace("<", "_");
        question = question.replace("\'", "");
        question = question.replace("\"", "");
        question = question.replace(",", "");

        int maxSize = 255;
        if (question.length() > maxSize) {
            question = question.substring(0, maxSize);
        }

        int queID = Integer.valueOf(request.getParameter("queID"));
        Question que = queDao.searchQuestionbyID(queID);

        if (que != null) {
            try {
                que.setQuestion(question);
                que.setApproved(false);
                queDao.updateQuestion(que);
            } catch (IllegalArgumentException e) {
                request.setAttribute("errormsg", "You're attempting to edit a non-existing question. Please hit the back button.");
                return new ModelAndView("error");
            }
        }

        User user = (User) session.getAttribute("user");
        List<Question> quelist = queDao.getQuestionsbyID(user.getID());
        return new ModelAndView("questions", "questions", quelist);
    }

    @GetMapping("/askquestion.htm")
    public void getSignUpError(HttpServletRequest request,
            HttpServletResponse response, HttpSession session) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    @PostMapping("/askquestion.htm")
    public ModelAndView addQuestion(HttpServletRequest request,
            HttpServletResponse response, HttpSession session, UserDAO userDao, QuestionDAO queDao, AnswerDAO ansDao) {
        String question = request.getParameter("question");
        question = question.replace(">", "_");
        question = question.replace("<", "_");
        question = question.replace("\'", "");
        question = question.replace("\"", "");
        question = question.replace(",", "");

        int maxSize = 255;
        if (question.length() > maxSize) {
            question = question.substring(0, maxSize);
        }

        Question que = new Question();
        que.setQuestion(question);
        User user = (User) session.getAttribute("user");
        que.setUserID(user.getID());

        queDao.addQuestion(que);
        request.setAttribute("success", "true");
        request.setAttribute("successmsg", "Your Question has been submitted for Approval!");
        List<Question> quelist = queDao.getApprovedQuestions();
        for (Question ques : quelist) {
            ques.setAnswersList((List<Answer>) ansDao.getAnswersbyQueID(ques.getID()));
        }
        request.setAttribute("userDao", userDao);
        return new ModelAndView("login", "questions", quelist);
    }

    @GetMapping("/editquestion.htm")
    public ModelAndView editQuestion(HttpServletRequest request,
            HttpServletResponse response, HttpSession session, UserDAO userDao, QuestionDAO queDao, AnswerDAO ansDao) {
        Question que = null;
        if (session.getAttribute("user") == null || session.getAttribute("user").equals("") || request.getParameter("queID") == null) {
            request.setAttribute("error", "true");
            request.setAttribute("errormsg", "Please login to continue...");
            List<Question> quelist = queDao.getApprovedQuestions();
            for (Question ques : quelist) {
                ques.setAnswersList((List<Answer>) ansDao.getAnswersbyQueID(ques.getID()));
            }
            request.setAttribute("userDao", userDao);
            return new ModelAndView("index", "questions", quelist);
        } else if (request.getParameter("queID") != null) {

            try {
                int queID = Integer.valueOf(request.getParameter("queID"));
                que = queDao.searchQuestionbyID(queID);
                User queUser = (User) session.getAttribute("user");
                if (queUser.getID() != que.getUserID()) {
                    request.setAttribute("errormsg", "You're unauthorized to view this page. Please hit the back button.");
                    return new ModelAndView("error");
                } else if (que == null) {
                    request.setAttribute("errormsg", "The question that you're looking for does not exist. Please hit the back button.");
                    return new ModelAndView("error");
                }
            } catch (NumberFormatException ne) {
                request.setAttribute("errormsg", "You're attempting to access an empty question ID. Please hit the back button.");
                return new ModelAndView("error");
            } catch (IllegalArgumentException e) {
                request.setAttribute("errormsg", "You're attempting to edit a non-existing question. Please hit the back button.");
                return new ModelAndView("error");
            }
        }
        return new ModelAndView("editquestion", "question", que);
    }

    @GetMapping("/changepassword.htm")
    public ModelAndView changeUserPassword(HttpServletRequest request,
            HttpServletResponse response, HttpSession session, QuestionDAO queDao, UserDAO userDao, AnswerDAO ansDao) {
        if (session.getAttribute("user") == null || session.getAttribute("user").equals("")) {
            request.setAttribute("error", "true");
            request.setAttribute("errormsg", "Please login to continue...");
            List<Question> quelist = queDao.getApprovedQuestions();
            for (Question que : quelist) {
                que.setAnswersList((List<Answer>) ansDao.getAnswersbyQueID(que.getID()));
            }
            request.setAttribute("userDao", userDao);
            return new ModelAndView("index", "questions", quelist);
        }
        request.setAttribute("success", false);
        return new ModelAndView("changeUserPassword");
    }

    @PostMapping("/changepassword.htm")
    public ModelAndView updateUserPassword(HttpServletRequest request,
            HttpServletResponse response, HttpSession session, UserDAO userDao) {
        String curpwd = request.getParameter("curpwd");
        curpwd = curpwd.replace("<", "_");
        curpwd = curpwd.replace(">", "_");

        String newpwd = request.getParameter("newpwd");
        newpwd = newpwd.replace("<", "_");
        newpwd = newpwd.replace(">", "_");

        User verifyUser = (User) session.getAttribute("user");
        String email = verifyUser.getEmail();
        User checkUser = userDao.verifyUser(email, curpwd);

        if (checkUser != null) {
            checkUser.setPassword(newpwd);
            userDao.updateUser(checkUser);
            request.setAttribute("success", true);
            request.setAttribute("successmsg", "Success! Your password has been updated!");
            return new ModelAndView("changeUserPassword");
        }

        request.setAttribute("success", true);
        request.setAttribute("successmsg", "The password you entered is incorrect. Please try again");

        return new ModelAndView("changeUserPassword");
    }

    @GetMapping("/submitanswer.htm")
    public void getAnswerError(HttpServletRequest request,
            HttpServletResponse response, HttpSession session) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    @PostMapping("/submitanswer.htm")
    public ModelAndView addAnswer(HttpServletRequest request,
            HttpServletResponse response, HttpSession session, UserDAO userDao, QuestionDAO queDao, AnswerDAO ansDao) {
        String answer = request.getParameter("answer");
        answer = answer.replace(">", "_");
        answer = answer.replace("<", "_");
        answer = answer.replace("\'", "");
        answer = answer.replace("\"", "");
        answer = answer.replace(",", "");

        int queID = Integer.valueOf(request.getParameter("queID"));
        User user = (User) session.getAttribute("user");

        Answer ans = new Answer();
        ans.setAnswer(answer);
        ans.setQuestionID(queID);
        ans.setUserID(user.getID());
        ans.setApproved(true);

        ansDao.addAnswer(ans);

        request.setAttribute("success", "true");
        request.setAttribute("successmsg", "Your Answer has been submitted!");
        List<Question> quelist = queDao.getApprovedQuestions();
        for (Question ques : quelist) {
            ques.setAnswersList((List<Answer>) ansDao.getAnswersbyQueID(ques.getID()));
        }
        request.setAttribute("userDao", userDao);
        return new ModelAndView("login", "questions", quelist);
    }

    @GetMapping("/editanswer.htm")
    public ModelAndView editAnswer(HttpServletRequest request,
            HttpServletResponse response, HttpSession session, UserDAO userDao, QuestionDAO queDao, AnswerDAO ansDao) {
        Answer ans = null;
        if (session.getAttribute("user") == null || session.getAttribute("user").equals("") || request.getParameter("ansID") == null) {
            request.setAttribute("error", "true");
            request.setAttribute("errormsg", "Please login to continue...");
            List<Question> quelist = queDao.getApprovedQuestions();
            for (Question ques : quelist) {
                ques.setAnswersList((List<Answer>) ansDao.getAnswersbyQueID(ques.getID()));
            }
            request.setAttribute("userDao", userDao);
            return new ModelAndView("index", "questions", quelist);
        } else if (request.getParameter("ansID") != null) {

            try {
                int ansID = Integer.valueOf(request.getParameter("ansID"));
                ans = ansDao.searchAnswerbyID(ansID);
                User ansUser = (User) session.getAttribute("user");
                
                if (ans == null) {
                    request.setAttribute("errormsg", "The answer that you're looking for does not exist. Please hit the back button.");
                    return new ModelAndView("error");
                } else if (ansUser.getID() != ans.getUserID()) {
                    request.setAttribute("errormsg", "You're unauthorized to view this page. Please hit the back button.");
                    return new ModelAndView("error");
                }
                
            } catch (NumberFormatException ne) {
                request.setAttribute("errormsg", "You're attempting to submit an empty answer ID. Please hit the back button.");
                return new ModelAndView("error");
            } catch (IllegalArgumentException e) {
                request.setAttribute("errormsg", "You're attempting to edit a non-existing answer. Please hit the back button.");
                return new ModelAndView("error");
            }
        }
        request.setAttribute("success", "false");
        return new ModelAndView("editanswer", "answer", ans);
    }

    @PostMapping("/editanswer.htm")
    public ModelAndView postAnswerEdit(HttpServletRequest request,
            HttpServletResponse response, HttpSession session, AnswerDAO ansDao, QuestionDAO queDao) {

        String answer = request.getParameter("answer");
        answer = answer.replace(">", "_");
        answer = answer.replace("<", "_");
        answer = answer.replace("\'", "");
        answer = answer.replace("\"", "");
        answer = answer.replace(",", "");

        int ansID = Integer.valueOf(request.getParameter("ansID"));
        Answer ans = ansDao.searchAnswerbyID(ansID);

        if (ans != null) {
            try {
                ans.setAnswer(answer);
                ansDao.updateAnswer(ans);
            } catch (IllegalArgumentException e) {
                request.setAttribute("errormsg", "You're attempting to edit a non-existing answer. Please hit the back button.");
                return new ModelAndView("error");
            }
        }

        Answer updatedAnswer = ansDao.searchAnswerbyID(ansID);
        request.setAttribute("success", true);
        request.setAttribute("successmsg", "Success! Your answer has been updated!");
        return new ModelAndView("editanswer", "answer", updatedAnswer);
    }
}
