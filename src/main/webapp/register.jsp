<%-- 
    Document   : register
    Created on : 04-Dec-2015, 22:25:41
    Author     : Phil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <script>
                //function modified from http://keithscode.com/tutorials/javascript/3-a-simple-javascript-password-validator.html
                function checkPass()
                {
                    //Setting which input fields to read
                    var pass1 = document.getElementById('inputPassword');
                    var pass2 = document.getElementById('confirmPassword');
                    
                    var badColor = "#ff6666";
                    var white = "#ffffff";
                    
                    //Message to display in case of non-match
                    var message = document.getElementById('confirmMessage');
                    message.style.color = badColor;
                    
                    //If passwords do not match
                    if(pass1.value !== pass2.value){
                        //notify user by changing background colour and displaying message
                            pass2.style.backgroundColor = badColor;
                            message.innerHTML = "Passwords do not match!"
                    }
                    else {
                        //otherwise reset
                        pass2.style.backgroundColor = white;
                        message.innerHTML = "";
                    }
                }
            </script>
        <h1>Register!</h1>
    </body>
</html>
