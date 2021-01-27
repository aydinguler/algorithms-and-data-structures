/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpledatabaseprogram;

/**
 *
 * @author aydin
 */
public class Customer {
    private String Name;
    private String Surname;
    private int ID;
    private Item Link;
    
    @Override
    public String toString(){
        String retstr = "Name: " + Name;
        retstr = retstr + " Surname: " + Surname;
        retstr = retstr + " ID: " + ID;
        return retstr;
    }
    Item first;
    public Customer(){
        first = null;
    }
    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }
    public String getSurname() {
        return Surname;
    }
    public void setSurname(String Surname) {
        this.Surname = Surname;
    }
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public Item getLink() {   // Created to get customer's link
        return Link;
    }
    public void setLink(Item Link) {   // Created to set customer's link
        this.Link = Link;
    }
}
