/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stores;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import java.util.ArrayList;

/**
 *
 * @author Phil
 */
public class User {
    Cluster cluster;
    public User(){
        
    }
    
    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }
    
    public boolean RegisterUser(String username, String Password, String email){
        String encoded = org.apache.commons.codec.digest.DigestUtils.sha1Hex(Password); //encrypts password in SHA1

        Session session = cluster.connect("cfgteam15");
        PreparedStatement ps = session.prepare("insert into users (username,password,email) Values(?,?,?)");
       
        BoundStatement boundStatement = new BoundStatement(ps);
        ResultSet rs = session.execute( boundStatement.bind(username,encoded,email) );
        
        session.close();
        
        if ( rs.getAvailableWithoutFetching() == 0) {
            return false;
        }
        else {
            return true;
        }
    }
    
    //@TODO
    public void addInterest(String toAdd) {
        return;
    }
    
    //returns true if volunteer, false if old person
    public boolean getType(String username) {
        //@TODO
        return true;
    }
    
    public ArrayList<String> getInterests(String username) {
        ArrayList<String> toReturn = new ArrayList();
        
        cluster = database.DBHost.getCluster();
        
        Session session = cluster.connect("cfgteam15");
        PreparedStatement ps = session.prepare("select preferences from users where username =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        
        for (Row row : rs) {
            String str = row.toString();
            String[] result = str.substring(str.lastIndexOf("[")+1, str.indexOf("]")).split(",");
            for(String str1 : result) {
                String str2 = str1.replace("[", "");
                String str3 = str2.replace(" ", "");
                toReturn.add(str3);
            }
        }
        
        return toReturn;
    }
    
        /**
     * Checks if the user is valid in the database and returns a LoggedIn object for the user's data
     * @param username The username to check
     * @param Password The password to check
     * @return The LoggedIn object for that user - null if user does not exist
     */
    public LoggedIn IsValidUser(String username, String Password){
        LoggedIn toReturn = new LoggedIn();
        
        String encoded = org.apache.commons.codec.digest.DigestUtils.sha1Hex(Password); //encrypts password in SHA1
        
        Session session = cluster.connect("cfgteam15");
        PreparedStatement ps = session.prepare("select * from users where username =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        if (rs.isExhausted()) {
            System.out.println("User not found.");
            
            toReturn.clearData();
            return toReturn;
        } else {
            for (Row row : rs) {
               
                String StoredPass = row.getString("password");
                if (StoredPass.compareTo(encoded) == 0) {
                    toReturn.setUsername(username);
                    toReturn.setPassword(encoded);
                    toReturn.setInterests(getInterests(username));
                    toReturn.setBio(row.getString("bio"));
                    LoggedIn newlg = getUserData(toReturn);
                    newlg.setLoggedin();
                    return newlg;
                }
            }
        }
        session.close();
    
        toReturn.clearData();
        return toReturn;
    }
    
        /**
     * Completes the missing data for the passed LoggedIn object
     * @param lg The LoggedIn object to find data for
     * @return The complete LoggedIn object
     * @throws NullPointerException If user does not exist in database
     */
    public LoggedIn getUserData(LoggedIn lg) throws NullPointerException {
        String username = lg.getUsername();
        
        Session session = cluster.connect("cfgteam15");
        PreparedStatement ps = session.prepare("select * from users where username =?");
        BoundStatement boundStatement = new BoundStatement(ps);
        ResultSet rs = session.execute( boundStatement.bind(username) );
        
        //convert result database to single row
        Row row = rs.one();
        //set values from returned data
        lg.setUsername(row.getString("username"));
        lg.setName(row.getString("name"));
        lg.setEmail(row.getString("email"));
        lg.setInterests(getInterests(username));
        lg.setBio(row.getString("bio"));
        
        session.close();
        
        return lg;
    }
    
    public static String encodePass(String pass) {
        return org.apache.commons.codec.digest.DigestUtils.sha1Hex(pass); //encrypts password in SHA1
    }
}
