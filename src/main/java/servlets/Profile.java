package servlets;

import com.datastax.driver.core.Cluster;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import database.DBHost;
import java.net.URLDecoder;
import java.util.StringTokenizer;
import stores.*;


/**
 *
 * @author Phil
 */
@WebServlet(urlPatterns = {
    "/Profile",
    "/Profile/*",
    "/editProfile",
    "/editProfile/*"
})
@MultipartConfig
public class Profile extends HttpServlet{
    Cluster cluster=null;
    private HashMap CommandsMap = new HashMap();
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
        super();
        // TODO Auto-generated constructor stub
        CommandsMap.put("Profile", 1);
        CommandsMap.put("editProfile", 2);
    }
    
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = DBHost.getCluster();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session=request.getSession();
        
        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
        boolean checkPass = false;
        
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        String encodedPass = User.encodePass(password);
        checkPass = lg.comparePass(encodedPass);
        
        
        User us = new User();
        
        if(checkPass) {    
        //    us.updateUser(username, firstName, lastName, email, address);
        }
        
        LoggedIn newlg = new LoggedIn();
        lg.setUsername(username);
        
        newlg = us.getUserData(lg);
        session.setAttribute("LoggedIn", newlg);
        
	response.sendRedirect("/CFG2015/Profile/"+username);
        
    }
    
    private static StringTokenizer SplitString(String str) {
        return new StringTokenizer(str, "/");

    }
    
    public static String[] SplitRequestPath(HttpServletRequest request) {
        String args[] = null;

        StringTokenizer st = SplitString(request.getRequestURI());
        args = new String[st.countTokens()];
		//Lets assume the number is the last argument

        int argv = 0;
        while (st.hasMoreTokens()) {;
            args[argv] = new String();

            args[argv] = st.nextToken();
            try {
                //System.out.println("String was "+URLDecoder.decode(args[argv],"UTF-8"));
                args[argv] = URLDecoder.decode(args[argv], "UTF-8");

            } catch (Exception et) {
                System.out.println("Bad URL Encoding" + args[argv]);
            }
            argv++;
        }

	//so now they'll be in the args array.  
        // argv[0] should be the user directory
        return args;
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String args[] = SplitRequestPath(request);
        int command;
        RequestDispatcher rd;
        
        try {
            command = (Integer) CommandsMap.get(args[1]);
        } catch (Exception et) {
            error("Bad Operator", response);
            return;
        }
        switch (command) {
            case 2:
                rd = request.getRequestDispatcher("/editProfile.jsp");
                break;
            case 1:
                LoggedIn lg = getUser(request, args);
                request.setAttribute("user", lg);
                
                //tm.setCluster(cluster);
                
                rd = request.getRequestDispatcher("/viewProfile.jsp");
                break;
            default:
                rd = request.getRequestDispatcher("/viewProfile.jsp");
                error("Bad Operator", response);
        }
        
        //RequestDispatcher rd = request.getRequestDispatcher("/UserProfile.jsp");
        rd.forward(request, response);
    }
    
    /**
     * Method to get a LoggedIn object from the URL
     * @param request The HTTP request
     * @param args The split URL
     * @return A LoggedIn object for the requested user if requested, the current user if not requested
     */
    private LoggedIn getUser(HttpServletRequest request, String[] args) {
        try {
            User us = new User();
            us.setCluster(cluster);
            LoggedIn lg = new LoggedIn();
            
            String username;
            username = "";
            username = args[2];
            lg.setUsername(username);
            
            return us.getUserData(lg);
        }
        catch (ArrayIndexOutOfBoundsException | NullPointerException ex) {
            System.out.println("Oops");
            
            return (LoggedIn)request.getAttribute("LoggedIn");
        }
    }
    
    private void error(String mess, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = null;
        out = new PrintWriter(response.getOutputStream());
        out.println("<h1>You have a an error in your input</h1>");
        out.println("<h2>" + mess + "</h2>");
        out.close();
        return;
    }
}
