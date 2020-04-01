
/**
 * Shift Cypher class, I tried to make everything independent of each other incase we had to make future changes to the program.
 * I ended up making my methods as small as possible, as well as using get methods and boolean statements for easy use. 
 * 
 * @author (David Proia) 
 * @version (Oct. 19, 2013)
 */
import java.util.Scanner;
import java.io.*;
import java.util.*;


public class ShiftCypher
{
    //variable statements the whole class can use since I made everything in different methods
    boolean myFile = false;
    boolean done = false;
    String file;
    char alphanum;
    char newAlphaEncode;
    int key;
    String wordText;
    
    // the main.java class calls this method 1st
    public void Start() 
    {
        String choice;
        Scanner scan = new Scanner(System.in);
        //while statement that is setup as boolean as a public variable to false so the program runs this code 1st no matter, then stops only when the statement
        //is true
        while (!done){
            //Scanner question that returns input from user to var 'choice'
            System.out.print("\nType E to Encrypt a word or D to Decrypt a file:\n");
            choice = scan.next();
            //if/else statements that once picked returns either the decrypt or encrypt method
            if (choice.equalsIgnoreCase("D")){
                //runs getDecrypt method
                getDecrypt();
            }
            else if (choice.equalsIgnoreCase("E")){
                //runs getEncrypt method
                getEncrypt();
            }
        }
    }
    
    //Decrypt method
    public void getDecrypt() 
    {
               
               //only continues to run the program once a correct file path is found
               while (!myFile) {
               getFile();
              }
              //runs getKey method
               getKey();
               
               //changes the var name from wordText to encodedText and converts to uppercase
               String encodedText = wordText.toUpperCase();
               
               
                char[] charArray = new char[encodedText.length()];   
                System.out.print("\nText before encoding:\n" + encodedText + "\n\nText after its encoded:\n");
        
                    for (int i = 0; i <encodedText.length(); i++){         
                       char x = encodedText.charAt(i); 
                       //boolean true/false checks to see if b=a-z and does the following if statement
                       boolean b = Character.isLetter(x);       
                         //runs a quick check on 'b'
                         if (b)
                             {
                                 
                                 //var alphanum added to var 'x' + key
                                 alphanum = (char)(x-key);
                                        
                                        if(getValidAlpha(alphanum)== true)
                                        {
                                            charArray[i] = alphanum;
                                        }
                                        else if(getValidAlpha(alphanum)== false)
                                        {
                                            //changes charArray back to alphanum and does another check to verify this time its within the correct boundaries
                                            //this will keep running til boundaries of A-Z are met
                                            //charArray[i] = (char)(alphanum + 26);
                                            charArray[i] = (char)(alphanum + 26);
                                            getValidAlpha(alphanum);
                                            
                                            
                                        }
                             }
                             else
                             {
                                    //return the non-numeric value back
                                    charArray[i] = (char)(x);
                             }
                    }
                    //Combines all the charArray letters into a single word for printing
                    String encryptWord = new String(charArray);
                    System.out.println(encryptWord);
                    //gets End Comment asking the user if they wish to go again   
                    getEndComment();
        }
        
    //encrypt method
    public void getEncrypt()
    {
        //ask the user to input a line of text to be encrypted
        System.out.println("Enter the text to encrypt here: ");
        //scanner variable
        Scanner in = new Scanner(System.in);
        //reads the input to var myLine
        String myLine= in.nextLine();
        //converts myLine to upperCase and converts it to a new string
        String encodedText = myLine.toUpperCase();
        
        //runs getKey method
        getKey();
        char[] charArray = new char[encodedText.length()];   
        System.out.print("\nText before encoding:\n" + encodedText + "\n\nText after its encoded:\n");
        
        for (int i = 0; i <encodedText.length(); i++){         
           char x = encodedText.charAt(i); 
           //boolean true/false checks to see if b=a-z and does the following if statement
           boolean b = Character.isLetter(x);
           
             if (b)
             {
                 //var alphanum added to var 'x' + key
                 alphanum = (char)(x+key);
                        
                        if(getValidAlpha(alphanum)== true)
                        {
                            charArray[i] = alphanum;
                        }
                        else if(getValidAlpha(alphanum)== false)
                        {
                            //changes charArray back to alphanum and does another check to verify this time its within the correct boundaries
                            //this will keep running til boundaries of A-Z are met
                            //charArray[i] = (char)(alphanum - 26);
                            charArray[i] = (char)(alphanum - 26);
                            getValidAlpha(alphanum);
                            
                            
                        }
             }
             else
             {
                    //return the non-numeric value back
                    charArray[i] = (char)(x);
             }
        }
        //changes the charArray into encryptedWord var
        String encryptWord = new String(charArray);
        //prints out new word
        System.out.println(encryptWord + "\n");
        //repeat loop to run again yes or no
        getEndComment();
        
        
    }  
    
    //boolean statement checks to see whether or not the charArray[i] being test is in this List
    public static boolean getValidAlpha(char alphanumeric) {
        //list of good possible characters
        List<Character> alphabets = Arrays.asList('A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'); 
        //returns true if in list or false if not in list
        if (alphabets.contains(alphanumeric))  {
            //true = Array of A-Z
            return true;
        }  else {
            //false = outside of Array
            return false;
        }
    }
    
    //runs the end comment method that asks the user to go again or not
    public void getEndComment()
    {
        //makes the file false again so the user can input a different file if they choose to
        myFile = false;
        //independent var for method when asking the user to input something its changed to an answer var
        String answer;
        //system scanner
        Scanner answerScan = new Scanner(System.in);
        //input message
        System.out.print("Would like to do this again yes or no?\n");
        //text user inputed, (has to be yes or no) or it keeps looping
        answer = answerScan.next();
        //yes returns false and returns to the original boolean statement at the top, no returns true and restarts the 'start()' method
        if (answer.equals("yes"))
            {
                done = false;
            }
        else if(answer.equals("no"))
            {
                done = true;
            }
        //if the user types something other than yes or no it prints out a message and runs this method again
        else
            {
                System.out.println("Please type only 'yes' to go again or 'no' to exit this program.\n");
                getEndComment();
            }
    }
    
    //method for geting a file for the user
    public void getFile()
    {
       
       //method var for fileName
       String fileName;
       //try and catch setup that returns an error if a wrong or no file is selected
       try{
           //scanner for input file
           Scanner inputFile=new Scanner(System.in);
           System.out.print("Enter the file name: \n");
           //allows the next input to be the filename
           fileName=inputFile.next();
           //uses the java class File to record a message to it
           File messageFile = new File(fileName);
           //reader for the message file var
           Scanner reader = new Scanner(messageFile);
           //var wordtext is blank since nothing has been found
           wordText = "";
           //changes myFile to true so it continues to decrypt
           myFile = true;
           //scans the letters in the txt file til it runs out
           while (reader.hasNext())
           {
               //appends the characters onto each other
               wordText = wordText+reader.nextLine() + "\n";    
           }
       }
       //file not found except, throws an error 
       catch (FileNotFoundException e)
            {
              System.out.println("There was an exception!  The file was not found!");
              //returns this method so the user can try again
              getFile();
            } 
    }
    
    //getKey method
    public void getKey()
    {
        Scanner inputKey=new Scanner(System.in);
        System.out.println("What key are you using?");
        //class var key to interchange between methods easier
        key = inputKey.nextInt();
    }
}


