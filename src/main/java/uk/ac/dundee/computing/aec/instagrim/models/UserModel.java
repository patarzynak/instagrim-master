/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.dundee.computing.aec.instagrim.models;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.UDTValue;
import com.datastax.driver.core.UserType;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import uk.ac.dundee.computing.aec.instagrim.lib.AeSimpleSHA1;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;
import uk.ac.dundee.computing.aec.instagrim.stores.UserInfo;

/**
 *
 * @author Administrator
 */
public class UserModel {
    Cluster cluster;
    public UserModel(){
        
    }
    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }
    
    public UserInfo getProfile(String username){
        UserInfo profile = new UserInfo();
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select * from userprofiles where login =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        if (rs.isExhausted()) {
            System.out.println("No Profile");
            return null;
        } else {
            
            for (Row row : rs) {
                Map<String, UDTValue> addressMap = row.getMap("addresses", String.class, UDTValue.class);
                String str = null, cit = null;
                int zip = -1;
                System.out.println(addressMap.toString());
                if(addressMap.get("addresses") != null){
                    str = addressMap.get("addresses").getString("street");
                    cit = addressMap.get("addresses").getString("city");
                    zip = addressMap.get("addresses").getInt("zip");
                }
                
                profile.setInfo(row.getString("login"),
                                row.getString("first_name"),
                                row.getString("last_name"),
                                str,
                                cit,
                                zip,
                                row.getSet("email", String.class).toString()
                                );

            }
        }
        
        profile.setProfilePic(profilePic(username));
        
        return profile;
    }
    
    private String profilePic(String username){
        String rtn = "";
        
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select * from profilepics where login =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        
        
        if (rs.isExhausted()) {
            //This is UUID for a default profile picture (manuallly inserted into the table)
            //it's in the pics table, and has empty string in 'user' column
            return "51ef7370-79ca-11e5-853f-844bf5b07446";
        } else {
            
            for (Row row : rs) {
                rtn = row.getUUID("picid").toString();
            }
        }
        return rtn;
    }
    
    
    
     public boolean UpdateUser(String username,String firstname, String lastname, String street, String city, String zip, String email){
                
        Session session = cluster.connect("instagrim");
        if(!firstname.equals("")){
            PreparedStatement psFirst = session.prepare("UPDATE userprofiles SET first_name='"+firstname+"' WHERE login=?");
            BoundStatement bsFirst = new BoundStatement(psFirst);
            session.execute( 
                    bsFirst.bind( 
                            username));
        }
        
        if(!lastname.equals("")){
        PreparedStatement psLast = session.prepare("UPDATE userprofiles SET last_name='"+lastname+"' WHERE login=?");
        BoundStatement bsLast = new BoundStatement(psLast);
        session.execute( 
                bsLast.bind( 
                        username));
        }
        
        if(!street.equals("") && !city.equals("") && !street.equals("")){
            int numzip = Integer.parseInt(zip);
            Map<String, UDTValue> addresses = new HashMap(); 
            UserType addressType = cluster.getMetadata().getKeyspace("instagrim").getUserType("address");
            UDTValue fullAddress = addressType.newValue()
                    .setString("street", street)
                    .setString("city", city)
                    .setInt("zip", numzip);
            addresses.put("addresses", fullAddress);

            PreparedStatement psAdr = session.prepare("UPDATE userprofiles SET addresses=? WHERE login=?");
            BoundStatement bsAdr = new BoundStatement(psAdr);
            session.execute( 
                    bsAdr.bind( 
                            addresses, username));
        }
        
        if(!email.equals("")){
            Set<String> enEmail = new HashSet<String>();
            enEmail.add(email);
            PreparedStatement psEmail = session.prepare("UPDATE userprofiles SET email=? WHERE login=?");
            BoundStatement bsEmail = new BoundStatement(psEmail);
            session.execute( 
                    bsEmail.bind( 
                            enEmail, username));
        }
        
        return true;
    }
    public boolean ChangePass(String username, String password){
        AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();
        String EncodedPassword=null;
        try {
            EncodedPassword= sha1handler.SHA1(password);
            
        }catch (UnsupportedEncodingException | NoSuchAlgorithmException et){
            System.out.println("Can't check your password");
            return false;
        }
        
        Session session = cluster.connect("instagrim");
        if(!password.equals("")){
            PreparedStatement ps = session.prepare("UPDATE userprofiles SET password=? WHERE login=?");
            BoundStatement bs = new BoundStatement(ps);
            session.execute( 
                    bs.bind( 
                            EncodedPassword, username));
        }
        else{
            return false;
        }
        
        
        return true;
    }
    
    public boolean RegisterUser(String username, String Password, String firstname, String lastname, String street, String city, int zip, String email){
        AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();
        String EncodedPassword=null;
        
        Set<String> enEmail = new HashSet<String>();
        Map<String, UDTValue> addresses = new HashMap();   
        UserType addressType = cluster.getMetadata().getKeyspace("instagrim").getUserType("address");
        UDTValue fullAddress = addressType.newValue()
                .setString("street", street)
                .setString("city", city)
                .setInt("zip", zip);
        enEmail.add(email);
        addresses.put("addresses", fullAddress);
        try {
            EncodedPassword= sha1handler.SHA1(Password);
            
        }catch (UnsupportedEncodingException | NoSuchAlgorithmException et){
            System.out.println("Can't check your password");
            return false;
        }
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("insert into userprofiles (login, password, first_name, last_name, addresses, email) Values(?,?,?,?,?,?) if not exists");
       
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username,EncodedPassword,firstname,lastname,addresses,enEmail));
        //TODO: We are assuming this always works.  Also a transaction would be good here !
        
        return true;
    }
    
    public boolean IsValidUser(String username, String Password){
        AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();
        String EncodedPassword=null;
        try {
            EncodedPassword= sha1handler.SHA1(Password);
        }catch (UnsupportedEncodingException | NoSuchAlgorithmException et){
            System.out.println("Can't check your password");
            return false;
        }
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select password from userprofiles where login =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        if (rs.isExhausted()) {
            System.out.println("No Users returned");
            return false;
        } else {
            for (Row row : rs) {
               
                String StoredPass = row.getString("password");
                if (StoredPass.compareTo(EncodedPassword) == 0)
                    return true;
            }
        }
   
    
    return false;  
    }

    
}
