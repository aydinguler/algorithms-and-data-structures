/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package documenttrackingprogram;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aydin
 */
public class HashTable implements HashTableInterface{
    
    private HashObject ht;
    
    private int collisions; // It keeps the number of coullusions occured.

    public HashTable() {
        
        collisions = 0;
    }

    @Override
    public Integer GetHash(String mystring) { // Here, an hash value of mystring is computed and this value returned.
        int hash1 = ht.hashFunction1( mystring ); // First hash fuction.
        int hash2 = ht.hashFunction2( mystring ); // Second hash fuction for double hashing.
        while (ht.table[hash1] != null && !ht.key(hash1).equals(mystring))
        {
            hash1 += hash2;         // If table[hash1] is not null and the key that assigned to that location
            hash1 %= ht.tableSize;  // is not equal to mystring then, hash2 is added to hash1. A remainder is
            collisions++;           // computed after dividing hash1 to table size. Also number of collusions are incremented here.
        }
        return hash1;
    }

    @Override
    public void ReadFileandGenerateHash(String filename, int size) { // File is read and an open address
        BufferedReader reader;                                       // hash structure is created.
        ht = new HashObject(size); // An object of HashObject class is created.
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine(); // File is read line by line.
            while (line != null){
                String[] lineArray = line.split("[ ]"); // Line is splitted by the spaces to get each word.
                for (int k = 0; k<lineArray.length; k++){
                    String myWord = lineArray[k].replaceAll("[,.!?:;]",""); // Punctuation marks are removed.
                    int index;
                    index = GetHash(myWord); // Gets the hash value.
                    ht.insert(index, myWord); // Inserts the word to the index location
                }
                line = reader.readLine(); // Reads the next line.
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HashTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HashTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void DisplayResult(String Outputfile) { // All the keys and their frequencies are written to a file.
        BufferedWriter output;
        FileWriter file;
        try {
            file = new FileWriter(Outputfile); // Creates a FileWriter
            output = new BufferedWriter(file); // Creates a BufferedWriter
            for (int i = 0; i < ht.tableSize; i++){
                if (ht.table[i] != null)
                    output.write(ht.key(i) +" "+ht.frequency(i)+"\n"); // The string contains key and frequency
            }                                                          // is written to the file.
            output.close(); // Closes the writer
        }
        catch (IOException e) {
            e.getStackTrace();
        }
        System.out.println("\nText file is created.");
    }

    @Override
    public void DisplayResult() { // All the keys and their frequencies are displayed on the screen.
        for (int i = 0; i < ht.tableSize; i++)
            if (ht.table[i] != null)
                System.out.println(ht.key(i) +" "+ht.frequency(i));
    }

    @Override
    public void DisplayResultOrdered(String Outputfile) { // All the keys and their frequencies
        String dummy;                                     // are ordered and written to a file.
        int capacity = 1;
        FileWriter file;
        BufferedWriter output;
        String []orderedArray = new String[1];
        int orderedArraySize = 0;
        
        for (int i = 0; i < ht.tableSize; i++){ // orderedArray's capacity is incremented here.
            if(orderedArraySize == capacity){   // After incrementing the capacity, orderedArray's elements are still remain.
                String dummyTemp[] = new String[capacity+1];
                for (int p=0; p<capacity; p++){
                    dummyTemp[p] = orderedArray[p];
                }
                orderedArray = dummyTemp;
                capacity = capacity +1;
            }
            if(ht.table[i] != null){ // Here, new key and frequency are combined then, assigned to the new area.
                orderedArray[orderedArraySize] = ht.key(i)+" "+ht.frequency(i);
                orderedArraySize++;
            }
            
        }
        //Here, the array is sorted in descending order
        for (int i = 0; i < orderedArray.length; i++){
            for (int j = i+1; j < orderedArray.length; j++){
                if (orderedArray[i] != null && orderedArray[j] != null && 
                        Integer.parseInt(orderedArray[i].split(" ")[1]) < 
                        Integer.parseInt(orderedArray[j].split(" ")[1])){
                    dummy = orderedArray[i];
                    orderedArray[i] = orderedArray[j];
                    orderedArray[j] = dummy;
                }
            }
        }
        try {
            file = new FileWriter(Outputfile); // Creates a FileWriter
            output = new BufferedWriter(file); // Creates a BufferedWriter
            for (int k = 0; k <orderedArray.length-1; k++) {   
                output.write(orderedArray[k]+"\n"); // Sorted array is written to the file.
            }
            output.close(); // Closes the writer
        }
        catch (IOException e) {
            e.getStackTrace();
        }
        System.out.println("Ordered in descending order and the text file is created.");
    }

    @Override
    public int showFrequency(String myword) { // Here, myword is found then, its frequency is display on the screen.
        int index = GetHash( myword ); // Gets the hash value.
        int freq;
        if (ht.table[index] != null && ht.key(index).equals(myword)){
            System.out.println("The frequency of '" +myword+"' is: " +ht.frequency(index));
            freq = ht.frequency(index);
        }
        else{
            System.out.println("The frequency of '" +myword+"' is: " +0);
            freq = -1;
        }
        return(freq);
    }

    @Override
    public String showMaxRepeatedWord() { // Finds the max repeated word and its frequency.
        String maxRepeatedWord = null;
        int frequencyofMaxRepeatedWord = 0;
        for (int i = 0; i < ht.tableSize; i++){
            if (ht.table[i] != null && ht.frequency(i) >= frequencyofMaxRepeatedWord){
                maxRepeatedWord = ht.key(i);
                frequencyofMaxRepeatedWord = ht.frequency(i);
            }
        }
        System.out.println("The most repeated word is '" +maxRepeatedWord
                            +"' and the frequency is: "
                            +frequencyofMaxRepeatedWord);
        return(maxRepeatedWord);
    }

    @Override
    public boolean checkWord(String myword) { // Checks the word whether it is in the table or not.
        int index = GetHash( myword ); // Gets the hash value.
        if (ht.table[index] != null && ht.key(index).equals(myword)){
            System.out.println("'" +myword+"' is found and number of occurrences is: "
                                +ht.frequency(index));
            return true;
        }else{
            System.out.println("'" +myword+"' is not found in the text");
            return false;
        }
    }

    @Override
    public float TestEfficiency() { // Prints the number of collisions during parsing the file.
        System.out.println("There are "+collisions+" collusion occurred.");
        return(collisions);
    }
    
}
