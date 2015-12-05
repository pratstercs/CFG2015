package matching;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpSession;



public class Users {
    
    private static final ArrayList<Integer> points = null;
	private String name;
    private int age;
    private boolean [] contact;
    private ArrayList<String> interests;
    private Times time;
    private Date anniversary;
    private String condition;
    private ArrayList<Users> previousContacts;
    private ArrayList<Users> favContacts;
    private Preference preference;
    private String bio;
    
    //Constructor
    
    
    
    // Getters & Setters
    
    public ArrayList<Users> getPreviousContacts() {
   	 return previousContacts;
    }
    public Users(String name, int age, boolean[] contact,
   		 ArrayList<String> interests, Times time, Date anniversary,
   		 String condition, ArrayList<Users> previousContacts,
   		 ArrayList<Users> favContacts, Preference preference, String bio) {
   	 super();
   	 this.name = name;
   	 this.age = age;
   	 this.contact = contact;
   	 this.interests = interests;
   	 this.time = time;
   	 this.anniversary = anniversary;
   	 this.condition = condition;
   	 this.previousContacts = previousContacts;
   	 this.favContacts = favContacts;
   	 this.preference = preference;
   	 this.bio = bio;
    }
    public void setPreviousContacts(ArrayList<Users> previousContacts) {
   	 this.previousContacts = previousContacts;
    }

    public int getAge() {
   	 return age;
    }

    public void setAge(int age) {
   	 this.age = age;
    }
    
    public ArrayList<String> getInterests() {
   	 return interests;
    }
    public void setInterests(ArrayList<String> interests) {
   	 this.interests = interests;
    }
    public ArrayList<Users> getFavContacts() {
   	 return favContacts;
    }
    public void setFavContacts(ArrayList<Users> favContacts) {
   	 this.favContacts = favContacts;
    }
    public Preference getPreference() {
   	 return preference;
    }
    public void setPreference(Preference preference) {
   	 this.preference = preference;
    }
    public String getBio() {
   	 return bio;
    }
    public void setBio(String bio) {
   	 this.bio = bio;
    }
    public String getName() {
   	 return name;
    }
    public void setName(String name) {
   	 this.name = name;
    }
    public boolean[] getContact() {
   	 return contact;
    }
    public void setContact(boolean[] contact) {
   	 this.contact = contact;
    }
    public Times getTime() {
   	 return time;
    }
    public void setTime(Times time) {
   	 this.time = time;
    }
    public Date getAnniversary() {
   	 return anniversary;
    }
    public void setAnniversary(Date anniversary) {
   	 this.anniversary = anniversary;
    }
    public String getCondition() {
   	 return condition;
    }
    public void setCondition(String condition) {
   	 this.condition = condition;
    }
    public ArrayList<Users> getPreviousContact() {
   	 return this.previousContacts;
    }
    public void setPreviousContact(ArrayList<Users> previousContact) {
   	 this.previousContacts = previousContact;
    }
	public ArrayList<Integer> getPoints() {
		// TODO Auto-generated method stub
		return this.points;
	}
 
//    public boolean isFav(Users old){
//   	 
//   	 Cluster cluster = CassandraHosts.getCluster();
//    	Session session = cluster.connect("cfgteam15");
//   	 
//   	 PreparedStatement s;
//   	 Boolean res = false;
//   	 
//   	 try {
//   		 s= database.getConnexion().prepareStatement("SELECT * FROM CALLLOG "
//   				 + "WHERE CALLLOG.VOLO='" + this.getName()+"' AND CALLLOG.old ='" + user.getName()+"'"  );
//   		 ResultSet rs = s.executeQuery();
//   		 while(rs.next()){
//   			 
//   		 }
//   						 
//   	 }  catch (SQLException e) {
//   		 e.printStackTrace();
//   	 }
//   	 return d;
//   	 
//    }
    

}
