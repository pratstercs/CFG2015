<%-- 
    Document   : index
    Created on : 04-Dec-2015, 21:51:39
    Author     : Phil
--%>

<%@ page import="java.util.ArrayList" %>
<%@page import="stores.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="assets/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="assets/Styles.css" />
        <link rel="stylesheet" type="text/css" href="assets/index.css" />
        <title>Team 15</title>
    </head>
    <body>
        
        <div id="cardBox">
            <div class="card hovercard">
                <div class="card-background">
                    <img class="card-bkimg" alt="" src="/CFG2015/assets/bg.png">
                </div>
                <div class="useravatar">
                    <img alt="" src="/CFG2015/assets/ageuk.png">
                </div>
                <div class="card-info">
                    <span class="card-title">Telephone Befriending Service</span>
                </div>
            </div>
            <div class="btn-pref btn-group btn-group-justified btn-group-lg" role="group" aria-label="...">
                <%
                        
                    LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                    if (lg != null) {
                        String UserName = lg.getUsername();
                        if (lg.getlogedin()) {
                %>
                <div class="btn-group" role="group">
                    <a href="/CFG2015/Matching/<%=lg.getUsername()%>">
                        <button type="button" id="images" class="btn btn-default"><span class="glyphicon glyphicon-camera" aria-hidden="true"></span>
                            <div class="hidden-xs">Matching</div>
                        </button>
                    </a>
                </div>
                <div class="btn-group" role="group">
                    <a href="/CFG2015/Profile/<%=lg.getUsername()%>">
                        <button type="button" id="profile" class="btn btn-default"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                            <div class="hidden-xs">Profile</div>
                        </button>
                    </a>
                </div>
                <div class="btn-group" role="group">
                    <a href="logout.jsp">
                        <button type="button" id="logout" class="btn btn-primary"><span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span>
                            <div class="hidden-xs">Logout</div>
                        </button>
                    </a>
                </div>
                
                <%}
                            } else {
                                %>
                <div class="btn-group" role="group">
                    <a href="register.jsp">
                        <button type="button" id="register" class="btn btn-primary"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                            <div class="hidden-xs">Register</div>
                        </button>
                    </a>
                </div>
                <div class="btn-group" role="group">
                    <a href="login.jsp">
                        <button type="button" id="login" class="btn btn-primary"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                            <div class="hidden-xs">Login</div>
                        </button>
                    </a>
                </div>
                <%}%>
            </div>
        </div>
    </body>
</html>
