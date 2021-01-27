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
public class SimpleDatabaseProgram {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DataBase MyDataBase = new DataBase();
        MyDataBase.readFromFile("d:\\MyData.txt");
        Float exps = MyDataBase.getTotalTradeofCustomer(98492);
        System.out.println(MyDataBase.search_Customer(98492) + " Total Expense : " +exps);
        System.out.println("The Total Trade : " +MyDataBase.getTotalTrade());
        MyDataBase.listItems(98492);
        Customer newc = new Customer();
        newc = MyDataBase.getNewCustomer("Loki", "Mischief", 2253);
        MyDataBase.addCustomer(newc);
        MyDataBase.addNewItem(2253, "Scepter", "Monday", 145.8f);
        MyDataBase.addNewItem(2253, "Skidbaldnir", "Saturday", 2340);
        System.out.println("The Total Trade : " +MyDataBase.getTotalTrade());
        MyDataBase.listItems(2253);
    }
    
}
