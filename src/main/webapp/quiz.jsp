<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quizzes</title>
</head>
<body>
<form action="QuizServlet" method="post">
<%
    String score = request.getParameter("score");
    if (score != null) {
%>
    <h3>Your Score: <%= score %></h3>
<%
    }
%>
<h2>Questions</h2>

<p>1. What is the default value of long variable?</p>
<input type="radio" name="q1" value="1"> 0 <br>
<input type="radio" name="q1" value="2"> 0.0 <br>
<input type="radio" name="q1" value="3"> 0L <br>
<input type="hidden" name="qid1" value="1">  <br><br>


<p>2. Which method must be implemented by all threads?</p>
<input type="radio" name="q2" value="1"> wait() <br>
<input type="radio" name="q2" value="2"> run() <br>
<input type="radio" name="q2" value="3"> stop() <br>
<input type="hidden" name="qid2" value="2">  <br><br>


<p>3. What is the default value of Boolean variable?</p>
<input type="radio" name="q3" value="1"> null <br>
<input type="radio" name="q3" value="2"> true <br>
<input type="radio" name="q3" value="3"> false <br>
<input type="hidden" name="qid3" value="3"><br><br>

<input type="hidden" name="total" value="3">  


<button type="submit">Submit</button>
</form>

</body>
</html>