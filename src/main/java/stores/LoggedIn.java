package stores;

//import java.util.Date;
//import java.util.Map;

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
}
