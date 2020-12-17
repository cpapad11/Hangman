import java.io.*;
import java.util.*;

public class Hangman
{
	public static void main(String[] args)
   	{
         Scanner kbd = new Scanner(System.in);
         String[] worldLibrary = loadWords();
         String randomWord = worldLibrary[(int) (Math.random() * worldLibrary.length)];
  
         char[] RandomnWord = stringToChar(randomWord);
         char[] wordEmpty = new char[RandomnWord.length];
         
         for(int i=0; i<wordEmpty.length; i++)
         {
            wordEmpty[i] = '_';
         }
         
         System.out.println("Welcome to the game, Hangman!\nI am thinking of a word that is " + RandomnWord.length + " letters long");
         
         int guessCounter = 5;
         boolean completed = false;
         boolean finished = false;
         
         while(!finished)
         {            
            System.out.println("----------------");
            System.out.println("You have " + guessCounter + " guesses left");
            System.out.print("Please guess a letter: ");
            char input = kbd.next().charAt(0);
            
            boolean GoodGuess =  Correct(input, RandomnWord, wordEmpty);
            boolean badGuess = badGuess(input, RandomnWord, wordEmpty);
            completed = winner(wordEmpty);
            
            if(badGuess)
            {
               guessCounter--;
               
               System.out.print("Oops!  That letter is not in my word: ");
               
               for(int i=0; i<wordEmpty.length; i++)
               {
                  System.out.print(wordEmpty[i] + " ");
               }
               System.out.println();
            }
            
            else if(GoodGuess)
            {      
               System.out.print("Good guess: ");
               
               for(int i=0; i<wordEmpty.length; i++)
               {
                  System.out.print(wordEmpty[i] + " ");
               }
               System.out.println();
            }
            
            if(completed)
            {  
               System.out.println("----------------");
               System.out.println("Congratulations, you won!");
            }
            
            if(guessCounter == 0)
            {
               System.out.println("----------------");
               System.out.print("Sorry, you ran out of guesses.  The word was ");
               
               for(int i=0; i<RandomnWord.length; i++)
               {
                  System.out.print(RandomnWord[i] + " ");
               }
               System.out.print(".");
            }
            
            if(guessCounter == 0 || completed)
            {
               finished = true;
            }
         }

   	} 
      // End of main method
       
       public static boolean winner(char[] blanks)
       {
         boolean win = true;
         
         for(int i=0; i<blanks.length; i++)
         {
            if(blanks[i] == '_')
            {
               return false;
            }
         }
         
         return win;
       }
       
       
       public static boolean badGuess(char letter,char[] MyWord,char[] blanks)
       {
         // It tests if the letter is not in the randomn word.
         boolean rslt = true;
         for(int i=0; i<MyWord.length; i++)
         {
            if(letter == MyWord[i])
            {
               return false;
            }
         }
         
         return rslt;
       }
       
       
       public static boolean Correct(char letter,char[] MyWord,char[] blanks)
       {
         // It finds if letter in word and changes it. 
         // Also, it finds if letter already in word.
         boolean rslt = false;
         boolean letterAlreadyIn = false;
         
         
         for(int i=0; i<MyWord.length; i++)
         {
            if(letter == MyWord[i] && blanks[i] != letter)
            {
               blanks[i] = letter;
               rslt = true;
            }
            else if(blanks[i] == letter)
            {
               letterAlreadyIn = true;
            }
         }
         
         if(letterAlreadyIn)
         {
            System.out.print("Oops!  You’ve already guessed that letter: ");
            
            for(int i=0; i<blanks.length; i++)
            {
               System.out.print(blanks[i] + " ");
            }
            System.out.println();
         }
         
         return rslt;
       }  
      
   	public static String[] loadWords()
   	{
   		//Returns a String array of valid words.
   		//Also prints out the total number of words (Strings) in the array.
   
      	ArrayList<String> wordList = new ArrayList<String>();
      	File f = new File("words.txt");
      	String[] wordsArr = new String[wordList.size()];
      	try
      	{
         	Scanner in = new Scanner(f);
         	while(in.hasNext())
         	{
            	String word = in.next();
            	wordList.add(word);
         	}
         	in.close(); 
         	System.out.println("Loading words from the file......");
         	System.out.println(wordList.size()+" words loaded.");
         	System.out.println("-------------");
         	wordsArr = (String[])wordList.toArray(wordsArr);
      	} catch (FileNotFoundException ex) {
         	System.out.println("File not found.");
      	}
      	return wordsArr;
   	}
   
   	public static char[] stringToChar(String secretWord)
   	{
   		//takes a string which is a secretWord
   		//Returns a char array of secretWord
         
      	char[] secretArr = new char[secretWord.length()];    
      	for (int i = 0; i < secretArr.length; i++)
      	{
         	secretArr[i] = secretWord.charAt(i);
      	}
      	return secretArr; 
   	}    
} 
