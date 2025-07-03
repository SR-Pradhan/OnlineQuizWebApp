package com.sr.backend;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/QuizServlet")
public class QuizServlet extends HttpServlet {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;
    private String dbDriver;

    	@Override
    	public void init() throws ServletException {
    	    try {
    	        InputStream in = getClass().getClassLoader().getResourceAsStream("com/sr/backend/db.properties");
    	        if (in == null) {
    	            throw new ServletException("Property file not found in classpath");
    	        }

    	        Properties props = new Properties();
    	        props.load(in);

    	        dbDriver = props.getProperty("db.driver");
    	        dbUrl = props.getProperty("db.url");
    	        dbUser = props.getProperty("db.username");
    	        dbPassword = props.getProperty("db.password");

    	        Class.forName(dbDriver);
    	    } catch (Exception e) {
    	        throw new ServletException("DB configuration loading failed", e);
    	    }
    	}
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int total = Integer.parseInt(req.getParameter("total"));
        int score = 0;

        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {

            for (int i = 1; i <= total; i++) {
                String qidStr = req.getParameter("qid" + i);
                String ansStr = req.getParameter("q" + i);

                if (qidStr == null || ansStr == null) continue;

                int qid = Integer.parseInt(qidStr);
                int userAns = Integer.parseInt(ansStr);

                PreparedStatement ps = con.prepareStatement("SELECT correct_option FROM answers WHERE question_id = ?");
                ps.setInt(1, qid);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    int correct = rs.getInt("correct_option");
                    if (correct == userAns) {
                        score++;
                    }
                }

                rs.close();
                ps.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.sendRedirect("quiz.jsp?score=" + score);
    }
}
