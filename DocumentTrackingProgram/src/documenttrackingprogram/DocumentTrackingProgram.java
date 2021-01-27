/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package documenttrackingprogram;

/**
 *
 * @author aydin
 */
public class DocumentTrackingProgram {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HashTable myHashTable = new HashTable();
        myHashTable.ReadFileandGenerateHash("d:\\FileToHash.txt", 1000);
        myHashTable.DisplayResult();
        myHashTable.DisplayResult("d:\\NotOrdered.txt");
        myHashTable.DisplayResultOrdered("d:\\DescendingOrder.txt");
        myHashTable.showFrequency("telescreen");
        myHashTable.checkWord("helicopter");
        myHashTable.showMaxRepeatedWord();
        myHashTable.TestEfficiency();
    }
    
}
