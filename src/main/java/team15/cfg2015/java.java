/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team15.cfg2015;

import database.DBHost;
import com.datastax.driver.core.Cluster;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import stores.User;

/**
 *
 * @author Phil
 */
@WebServlet(name = "Test", urlPatterns = {"/Test"})
public class java extends HttpServlet {
    Cluster cluster = null;

    public void init(ServletConfig config) throws ServletException {
        cluster = DBHost.getCluster();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cluster = DBHost.getCluster();
        
        User us = new User();
        ArrayList<String> a = us.getInterests("a");
        ArrayList<String> b = us.getInterests("b");
        ArrayList<String> c = new ArrayList<String>();
        
        c = matching.MatchingImp.commonInterestsList(a, b, c);
        
        HttpSession session=request.getSession();
        session.setAttribute("matches", c);
            
        RequestDispatcher rd = request.getRequestDispatcher("/CFG2015/index.jsp");
        rd.forward(request, response);
    }
}
