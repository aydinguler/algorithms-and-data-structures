/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modifystrings;

/**
 *
 * @author aydin
 */
import java.util.Scanner;
import java.io.IOException;
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Absurd {
    
    private String MyString; // instance variable

    private String getMyString(){ // method to retrieve the MyString
        return MyString; // return value of MyString to caller
    }
    
    public Absurd() {
        
        Scanner scanUser = new Scanner(System.in); //Created a Scanner object
        
        boolean breakTheWhileLoop = false; //Created a boolean to break the while loop
        
        while(true){
            
            String newString = scanUser.next().toLowerCase(); //Read user input
            
            switch (newString.charAt(0)){ //Here, we are getting the first character of the entered string
                
                
                case ('x'):
                    breakTheWhileLoop = true; //When newString.charAt(0) is x it breaks the while loop
                    System.out.println("The Program has terminated");
                break;
                
                
                case ('s'):
                    MyString = GetString(scanUser);
                    PrintString();
                break;
                
                
                case ('p'):
                    String palindromeOutcome = "null";
                    switch (String.valueOf(Palindrome(getMyString()))){
                        case ("true"): 
                            palindromeOutcome = "The String is a palindrome"; //return true case message
                        break;
                        case ("false"):
                            palindromeOutcome = "The String is not a palindrome"; //return false case message
                        break;
                    }
                    System.out.println(palindromeOutcome); //it prints the above messages
                break;
                
                
                case ('d'):
                    String deleteCommand = scanUser.nextLine();
                    String removedNonDigits = deleteCommand.replaceAll("\\D+",""); //remove non-digits
                    int newInt = Integer.parseInt(removedNonDigits); //String to int
                    MyString = Delete(getMyString(), newInt-1);
                    PrintString();
                break;
                
                
                case ('r'):
                    MyString = OutReverse(getMyString());
                    PrintString();
                break;
                
                
                case ('f'):
                    MyString = WordReverse(getMyString());
                    PrintString();
                break;
                
                
                case ('i'):
                    String insertCommand = scanUser.nextLine(); //read input and creates a new string to get the command
                    String removedNonDigits2 = insertCommand.replaceAll("\\D+",""); //remove non-digits
                    int newInt2 = Integer.parseInt(removedNonDigits2); //String to int
                    String insertThat = insertCommand.substring(2).trim(); //The string that will be inserted
                    MyString = Insert(getMyString(),newInt2-1,insertThat);
                    PrintString();
                break;
                
                
                case ('m'):
                    MyString = MakeCapitalize(getMyString());
                    PrintString();
                break;
                
                
                case ('o'):
                    MyString = Output(getMyString());
                    PrintString();
                break;
                
                
                case ('w'):
                    String outFilePath = scanUser.next();
                    try {
                        MyString = OutFile(getMyString(), outFilePath);
                    } catch (IOException ex) {
                        Logger.getLogger(Absurd.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    PrintString();
                break;
                
                
                case ('t'):
                    String InFilePath = scanUser.next(); //reads input and creates a string to get the file path
                    String InFileOutcome;
                    try {
                        MyString = (InFile(InFilePath));
                    } catch (IOException ex) {
                        Logger.getLogger(Absurd.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    InFileOutcome = "The file has been opened and the string has been read";
                    System.out.println(InFileOutcome);
                break;
            
            }
            if (breakTheWhileLoop) break;
        }            
    }
    
    public String GetString(Scanner userInput){ 
        String userString = userInput.nextLine().trim(); //reads the userInput and put into userString
        return userString; 
    }
    
    public boolean Palindrome(String isThatPalindrome) { //checks the strig for palindrome
        int n = isThatPalindrome.length(); //determines the length of the string
        for( int i = 0; i < n/2; i++ ) 
            if (isThatPalindrome.charAt(i) != isThatPalindrome.charAt(n-i-1)) { //it compares the first and last characters of string
                return false;
            }
        return true;    
    }
    
    public String Delete(String removeLetter, int k){
        String fitstPart = removeLetter.substring(0,k); // The part of the String before the index:
        String secondPart = removeLetter.substring(k+1,removeLetter.length()); // The part of the String after the index:
        return fitstPart+secondPart; // These two parts together gives the String without the specified index
    }
    
    public String OutReverse(String reverseLetters){ //
        int i, len = reverseLetters.length();
        StringBuilder dest = new StringBuilder(len);
        for (i = (len - 1); i >= 0; i--){ //take the last character and makes it the new first character and so on.
            dest.append(reverseLetters.charAt(i));
        }
        return dest.toString();
    }
    
    public String WordReverse(String reverseWords){ //words string reverse order
        int i = reverseWords.length() - 1; 
        int start, end = i + 1; 
        String reversedString = ""; 
        while(i >= 0) 
        { 
            if(reverseWords.charAt(i) == ' ') 
            { 
                start = i + 1; 
                while(start != end) 
                    reversedString += reverseWords.charAt(start++); 
                    reversedString += ' '; 
                    end = i; 
            } 
            i--; 
        } 
        start = 0; 
        while(start != end) 
        reversedString += reverseWords.charAt(start++); 
        return reversedString; 
    }
    
    public String Insert(String inputString, int k, String insertX){
        String insertedX = insertX;
        String partOne = inputString.substring(0,k); // The part of the String before the index:
        String partTwo = inputString.substring(k,inputString.length()); // The part of the String after the index:
        return partOne+insertedX+partTwo; // These two parts together gives the String with the inserted index
    }
    
    public String MakeCapitalize(String makeCapitalizedMyString){
        String upperMyString = makeCapitalizedMyString.toUpperCase(); // it simply uses .toUpperCase
        return upperMyString;
    }
    
    public String Output(String getScreen){
        String showOutput = getScreen;
        return showOutput; //returns the string back
    }
    
    public String OutFile(String content, String fileDirection) throws IOException {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(fileDirection))) {
            bufferedWriter.write(content); // to write some data
            // bufferedWriter.write("");         // for empty file
        } // to write some data
    return null;
    }
    
    public String InFile(String fileDirection2) throws IOException{
        String content = new String(Files.readAllBytes(Paths.get(fileDirection2)), StandardCharsets.UTF_8);
    return content; //It uses readAllBytes to get the file content. The file is located in fileDriction2
    }
    
    private String PrintString()
    {
        System.out.println(""+getMyString()); //gets MyString and prints it
        return MyString;
    }

}
