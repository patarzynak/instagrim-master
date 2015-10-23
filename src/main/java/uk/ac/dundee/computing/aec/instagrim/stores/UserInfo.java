/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.stores;

/**
 *
 * @author Kasia
 */
public class UserInfo {
    private String username;
    private String firstname;
    private String lastname;
    private String street;
    private String city;
    private int zip;
    private String email;
    private String picID;
    
    public UserInfo(){
        username = "";
        firstname = "";
        lastname = "";
        street = "";
        city = "";
        zip = -1;
        email = "";
        picID = "";
    }
    
    public void setInfo(String username, String firstname, String lastname, String street, String city, int zip, String email){
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.email = email;
        
    }
    
    public void setProfilePic(String picID){
        this.picID = picID;
    }
    
    public String getUname(){
        return username;
    }
    
    public String getFname(){
        return firstname;
    }
    
    public String getLname(){
        return lastname;
    }
    
    public String getStreet(){
        return street;
    }
    
    public String getCity(){
        return city;
    }
    
    public int getZip(){
        return zip;
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getProfPic(){
        return picID;
    }
}


