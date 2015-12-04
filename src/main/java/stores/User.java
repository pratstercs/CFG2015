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
import com.datastax.driver.core.Session;

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
        PreparedStatement ps = session.prepare("insert into userprofiles (login,password,email) Values(?,?,?)");
       
        BoundStatement boundStatement = new BoundStatement(ps);
        ResultSet rs = session.execute( boundStatement.bind( username,encoded,email) );
        
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
}
