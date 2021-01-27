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
public class HashObject {
    String[] table; // A array of Strings named table is created.
    int tableSize; 
    private int primeSize;
    
    public HashObject(int tSize) { //Constructor is created.
        tableSize = tSize;
        table = new String[tableSize];
        for (int i = 0; i < tableSize; i++){ // Null is assigned to all
            table[i] = null;                 // the elements of the array.
        }
        primeSize = getPrime();
    }
    
    /* Gets a prime number less than table size for hashFunction2 and returns it*/
    private int getPrime()
    {
        for (int i = tableSize - 1; i >= 1; i--)
        {
            int fact = 0;
            for (int j = 2; j <= (int) Math.sqrt(i); j++)
                if (i % j == 0)
                    fact++;
            if (fact == 0)
                return i;
        }
        return 3; // Return a prime number
    }
    
    public String key(int i){ // table[i] string is splitted and the
        String key;           // key part of the string is returned.
        key = table[i].split(" ")[0];
        return key;
    }
    
    
    public int frequency(int i){ // table[i] string is splitted and the
        int freq;                // frequency part of the string is returned.
        freq = Integer.parseInt(table[i].split(" ")[1]);
        return freq;
    }
    
    /* hashFunction1 gives a hash value for a given string */
    public int hashFunction1(String hashThisKey )
    {
        /*Here, an unique numeric value of the key is created.
        Numeric value is computed as: s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1].*/
        int hashVal = hashThisKey.hashCode();
        hashVal %= tableSize;       // A remainder is computed after dividing it to table size.
        if (hashVal < 0)            // It is computed until get an positive remainder.
            hashVal += tableSize;   // So, it can be fitted into the table.
        return hashVal;
    }
    
    /* hashFunction2 for double hashing */
    public int hashFunction2(String hashThisKey )
    {
        int hashVal = hashThisKey.hashCode();
        //hashVal %= tableSize;
        if (hashVal < 0)
            hashVal += tableSize;
        return primeSize - hashVal % primeSize; // primeSize is smaller than the tableSize
    }
    
    /* Function to insert a key frequency pair */
    public void insert(int index, String key) 
    {
        int dummyFrequency;
        if (table[index] != null && key(index).equals(key)){ //if the key is already in the table
            String stringFrequency;                          //then, just increment the frequency
            dummyFrequency =+ frequency(index);              //of the key in table[index].
            dummyFrequency++;
            stringFrequency = String.valueOf(dummyFrequency);
            table[index] = key(index) +" "+ stringFrequency;
        }
        else{
            String newKeyValue;         //if the key is being inserted first time
            newKeyValue = key +" "+1;   //then, give initial frequency as 1.
            table[index] = newKeyValue;
        }
    }
}
