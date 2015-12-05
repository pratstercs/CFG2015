package stores;

//import java.util.Date;
//import java.util.Map;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Row;
import database.DBHost;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class to store the data for the currently logged in user.
 * Also can be used for other users not logged in (misnamed, really)
 */
public class LoggedIn {
    boolean loggedin=false;
    
    String username=null;
    String name=null;
    private String encodedPass = null;
    int contactTypes;
    String email=null;
    int[] timeList;
    String bio=null;
    ArrayList<String> interests;
    
    
    /**
     * Constructor
     */
    public void LoggedIn(){
        
    }
    
    /**
     * Clears all stored data
     */
    public void clearData() {
        loggedin=false;
    
        username=null;
        name=null;
        email=null;
        bio = null;
        encodedPass = null;
    }
    
    /**
     * Compares stored encoded password with new encoded password
     * @param compare The password, encoded the same way, to compare with
     * @return Whether they are the identical
     */
    public boolean comparePass(String compare) {
        return encodedPass.contentEquals(new StringBuffer(compare));
    }
    
    public void setUsername(String name){
        this.username=name;
    }
    public String getUsername(){
        return username;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }    
    public void setEmail(String name){
        this.email=name;
    }
    public String getEmail(){
        return email;
    }   
    public void setLoggedin(){
        loggedin=true;
    }
    public void setLoggedout(){
        loggedin=false;
    }
    public void setPassword(String newPass) {
        encodedPass = org.apache.commons.codec.digest.DigestUtils.sha1Hex(newPass); //encrypts password in SHA1
    }
    
    public void setLoginState(boolean loggedin){
        this.loggedin=loggedin;
    }
    public boolean getlogedin(){
        return loggedin;
    }
    
    public void setInterests(ArrayList<String> al) {
        interests = al;
    }
    public ArrayList<String> getInterests() {
        return interests;
    }
    
    public void setBio(String newBio) {
        bio = newBio;
    }
    public String getBio() {
        return bio;
    }
    
    public int noCallFor() {
        Date today = new Date();
        long sec = today.getTime();
        Date date = new Date();
        Cluster cluster = DBHost.getCluster();
        Session session = cluster.connect("cfgteam15");
        PreparedStatement s;
        try {
            s = session.prepare("SELECT * FROM CALLLOG " + "WHERE CALLLOG.old='" + this.getName() + "' ");
            BoundStatement bs = new BoundStatement(s);
            ResultSet rs = session.execute( bs.bind() );
            
            for(Row row : rs) {
                date = row.getDate("Time");
            }
        } catch (Exception e) {
            System.out.println("problem");
        }
        long sec2 = date.getTime();
        return (int) ((sec - sec2) / (8.64 * 10000000));
    }
    
    public ArrayList<LoggedIn> testPreference() {

        ArrayList<LoggedIn> res = new ArrayList<LoggedIn>();
        Cluster cluster = CassandraHost.getCluster();
        Session session = cluster.connect("cfgteam15");
        PreparedStatement s;
        Boolean res = false;

        if (this.type == 'O') {

            try {
                s = session.prepare("SELECT * FROM user WHERE USER.gender = this.getPreference().get(0) AND USER.age>this.getPreference().get(1) AND USER.age<this.getPreference().get(2)");
                ResultSet rs = session.execute();
                while (rs.next()) {
                    Users user0 = new Users(rs.getString("name"), rs.getInteger("contact"), rs.getChar("gender"), rs.getInteger("age"),
                            rs.getString("interests"), rs.getChar("type"), rs.getInterests("time"), rs.getDate("anniversary"), rs.getInterests("previousContacts"), rs.getInterests("favContacts"),
                            rs.getInterests("preference"), rs.getString("bio"));
                    res.add(user0);
                }

            } catch (Exception e) {
                syso("problem encountered");
            }
            return res;
        }
    }
    
    public ArrayList<LoggedIn> fromDataToMatch() {
        ArrayList<LoggedIn> listPref = this.testPreference();
        ArrayList<LoggedIn> listContacts = this.testContacts(listPref);
        ArrayList<LoggedIn> listAvailable = this.testAvailable(listContacts);
        getMatching(this, listAvailable);
    }
}
