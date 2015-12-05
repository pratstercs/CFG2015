<!--<%@page import="java.util.*"%>-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="stores.*" %>

<!doctype html>
<html>
    <head>
        <title>Profile</title>
        <link rel="stylesheet" type="text/css" href="/CFG2015/assets/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="http://snipplicious.com/css/font-awesome-4.1.0.min.css">
        <link rel="stylesheet" type="text/css" href="/CFG2015/assets/viewProfile.css" />
        <link rel="stylesheet" type="text/css" href="/CFG2015/assets/Styles.css" />
        <link rel="shortcut icon" href="/CFG2015/assets/favicon.ico" />
    </head>
    <body>  
        <%
             String username = "";
            String name = "";
            String email = "";

            LoggedIn lg = (LoggedIn) request.getAttribute("user");
            if (lg == null) {
                lg = (LoggedIn) session.getAttribute("LoggedIn");
            }
            if (lg == null) {
                lg = new LoggedIn();
                lg.clearData();
                lg.setUsername("NOT LOGGED IN");
            }
            
            username = lg.getUsername();
            name = lg.getName();
            email = lg.getEmail();
            
            ArrayList<String> interests = lg.getInterests();
            String interestList = "";
            
            if(interests == null || interests.size() == 0) {
                interestList = "None!";
            }
            else {
                for(String inter : interests) {
                    interestList += inter;
                    interestList += ", ";
                }
                interestList = interestList.substring(0, interestList.length() -2);
            }
            
            String profilePic;
            if (username.compareTo("a") == 0) {
                profilePic = "/CFG2015/assets/mrd.jpg";
            }
            else {
                profilePic = "/CFG2015/assets/blank.png";
            }
            
            String bio = lg.getBio();
        %>
        <a href="/CFG2015/"><h1>Age UK</h1></a>
        <table>
            <tr>
                <td>
                    <div class="container">
                        <div class="row">
                            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad" >
                                <div class="panel panel-info">
                                    <div class="panel-heading">
                                        <h3 class="panel-title"><%=username%></h3>
                                    </div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-md-3 col-lg-3 " align="center">
                                                <a href="/CFG2015/assets/blank.png"><img alt="Profile Picture" src="<%=profilePic%>" class="img-circle img-responsive"></a>
                                            </div>
                                            <div class=" col-md-9 col-lg-9 "> 
                                                <table class="table table-user-information">
                                                    <tbody>
                                                        <tr>
                                                            <td>Name:</td>
                                                                <td><%=name%></td>
                                                        </tr>
                                                        <tr>
                                                        <tr>
                                                            <td>Email</td>
                                                            <td><a href="mailto:<%=email%>"><%=email%></a></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Interests</td>
                                                            <td><%=interestList%></td>
                                                        </tr>
                                                        <tr>
                                                            <td>About me:</td>
                                                            <td><%=bio%></td>
                                                        </tr>
                                                            
                                                    </tbody>
                                                </table>
                                                <form method="POST" action="/CFG2015/call">
                                                    <span class="pull-right"><button class="btn btn-lg btn-primary btn-block" type="submit">Call me!</button></span>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="panel-footer">
                                        <a href="/CFG2015/editProfile" data-original-title="Edit profile" data-toggle="tooltip" type="button" class="btn btn-sm btn-warning"><!-- @TODO edit profile link here --><i class="glyphicon glyphicon-edit"></i></a>
                                        <span class="pull-right">
                                            <a data-original-title="Logout" data-toggle="tooltip" type="button" class="btn btn-sm btn-danger" href="/CFG2015/logout.jsp"><i class="glyphicon glyphicon-remove"></i></a>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
        </table>
        <br /><br /><br />
        
    </body>
</html>
