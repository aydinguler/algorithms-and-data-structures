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
public interface HashTableInterface {
    Integer GetHash(String mystring);
    void ReadFileandGenerateHash(String filename, int size);
    void DisplayResult(String Outputfile);
    void DisplayResult();
    void DisplayResultOrdered(String Outputfile);
    int showFrequency(String myword);
    String showMaxRepeatedWord();
    boolean checkWord(String myword);
    float TestEfficiency();
}
