/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpledatabaseprogram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aydin
 */

/*
DataBase is a class that consist the following methods, addCustomer, getNewCustomer,
addNewItem, getTotalTradeofCustomer, getTotalTrade, search_Customer, readFromFile and IDNotFoundException.
*/
public class DataBase implements DataBaseInterface {
    
    private Customer customerArray[] = {};    // create a new array to keep Customers
    
    
    // Method to add Customer object to the customer array. 
    @Override
    public void addCustomer(Customer newCustomer) {
        
        List<Customer> arrlist = new ArrayList<>(Arrays.asList(customerArray));   // create a new ArrayList 
        arrlist.add(newCustomer);   // Add the new element 
        customerArray = arrlist.toArray(customerArray); // Convert the Arraylist to array
    }

    
    // Method to list all the items(nodes) in the list of a Customer
    @Override
    public void listItems(int ID) {
        
        Item current = search_Customer(ID).getLink();   // Item current will point to first item(node)
        if(current == null) {   // Checks if current item(node) have nothing inside in it.
            System.out.println(search_Customer(ID)+" Item List is empty");    
            return;    
        }
        System.out.println(search_Customer(ID)+" Item List :");
        while(current != null) {    // gets into a loop until the last item(node) to be printed
            System.out.print(current.ItemName+" "+ current.Date+" "+current.Price+"\n");
            current = current.Link; // Increments pointer to get each Item(node).
        }
    }

    
    /*
    Method to get the information of new customer from user.
    The information contains Name, Surname and ID.
    */
    @Override
    public Customer getNewCustomer(String Name, String Surname, int ID) {
        
        Customer newCustomer = new Customer();
        newCustomer.setName(Name);  //newCustomer's Name, Surname and ID
        newCustomer.setSurname(Surname);   //are beeing setted here with the help
        newCustomer.setID(ID);  //of setters that defined in Customer class
        return newCustomer;
    }

    
    /*
    Method to add the new item to the linked list of
    corresponding array location which contains the ID.
    When ID is not found, IDNotFoundException is thrown.
    */
    @Override
    public void addNewItem(Integer ID, String ItemName, String Date, float Price) {
        
        Item newItem = new Item();  // Object newItem is created.
        if(search_Customer(ID) == null) {                       //if the entered ID number is not registered to any Customer,
            throw new IDNotFoundException("Enter a Valid ID");  //then it will throw IDNotFoundException Exception
        }
        else 
        {
            newItem.ItemName = ItemName;
            newItem.Price = Price;
            newItem.Date = Date;
            search_Customer(ID).setLink(newItem);                           //It reaches the Item class by using Customer class's 
            search_Customer(ID).getLink().Link = search_Customer(ID).first; //getLink and setLink methods. Thus, new added Item
            search_Customer(ID).first = search_Customer(ID).getLink();      //will belong to specific Customer with the given ID.
        }
    }

    
    /*
    Method to get the ID of the user and finds the total amount of 
    expenses of her/him and returns the result.
    */
    @Override
    public Float getTotalTradeofCustomer(int ID) {
        
        Item current = search_Customer(ID).getLink();   // Item current will point to first item(node)
        float expenses = 0; // Expenses will be kept within this variable.
        if(current == null) {   // Checks whether the Customer does have item.
            System.out.println("List is empty");
        }
        while(current != null) {        // If Customer have at least 1 Item, then the
            expenses += current.Price;  // Item prices will be added up inside the while loop.
            current = current.Link; // Increments pointer to get each Item(node).
        }
        return expenses;
    }
    
    
    //Method to find and show the total amount of trades of the company.
    @Override
    public Float getTotalTrade() {
        
        float totalTrade = 0;   // Trades will be kept within this variable.
        for(int i = 0; i < customerArray.length; i++) {   // All Customer objects stored in the customer array
            Customer allCustomers = customerArray[i];     // are obtained with the help of for loop.
            Item current = search_Customer(allCustomers.getID()).getLink(); //Item current will point to first item(node).
            
            while(current != null) {        //If Customer have at least 1 Item, then the
                totalTrade += current.Price;//Item prices will be added up inside the while loop.
                current = current.Link; //to get each Item(node) by incrementing pointer.
            }
        }
        return totalTrade;
    }

    
    /*
    Method to read the trade text data from file.
    This file may contain information for a new customer or a new item.
    */
    @Override
    public void readFromFile(String path) {
        
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();    //First line of reader
            
            /*
            Each content inside of the file will be taken line by line.
            While a line may contain a Customer information,
            the other line may contain Item information.
            */
            while (line != null){   //Checks if the line is empty or not.
                                
                if (Character.isLetter(line.charAt(0))){    // It is checked here whether the first character is a letter or not.
                    Customer customerFromFile;
                    
                    //getNewCustomer method requires 3 part(String,String,int).In order to get that parts, line is splitted into 3 part.
                    customerFromFile = getNewCustomer(line.split(" ")[0],line.split(" ")[1],Integer.parseInt(line.split(" ")[2]));//
                    addCustomer(customerFromFile);  // Add Customer object to the customer array
                }
                if (Character.isDigit(line.charAt(0))){ // It is checked here whether the first character is a Digit or not.
                    
                    //addNewItem method requires 4 part(Integer,String,String,float). In order to get that parts, line is splitted into 4 part.
                    addNewItem(Integer.parseInt(line.split(" ")[0]),line.split(" ")[1],line.split(" ")[2],Float.parseFloat(line.split(" ")[3]));
                }
                line = reader.readLine();   // To read next line. Returns null when end of file is reached.
            }
            System.out.println("The content of file has been read");
        }
        catch (IOException | IDNotFoundException ex)
        {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    //Method to return the customer object of ID
    @Override
    public Customer search_Customer(int ID) {
        
        for (Customer foundCustomer : customerArray) {
            if (ID == (foundCustomer.getID())) {    //When the Customer is found in the Customer array, it will be returned.
                return foundCustomer;
            } 
        }
    return null;
    }
}