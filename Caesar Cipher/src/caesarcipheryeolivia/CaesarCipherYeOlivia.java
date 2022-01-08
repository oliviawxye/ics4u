/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caesarcipheryeolivia;

// Imports the keyboard scanner
import java.util.Scanner;

/**
 *
 * @author 335462040
 */
public class CaesarCipherYeOlivia {

    /**
     * @param input audience input
     * @param check whether the audience input is empty or full
     * @return returns true or false, whether or not there isn't or is an empty
     * string, respectively
     */
    // Checks the audience input for an empty input
    private static boolean emptyCheck(String input, boolean check) {
        // Try catch statement to check for an empty inputted string
        try {
            input.substring(0, 1);
            check = true;
        } catch (StringIndexOutOfBoundsException e) {
            check = false;
        }
        return check;
    }

    /**
     * @param message audience inputted message
     * @param lowerAlphabet lowercase alphabet to check from
     * @param upperAlphabet uppercase alphabet to check from
     * @param messageLength length of the message the audience inputted
     * @param shift number of spaces to shift the letter
     * @return returns the newly shifted message
     */
    // Method to decode/encode the message based on the number of shifts
    private static String cipher(String message, String lowerAlphabet, String upperAlphabet, int messageLength, int shift) {
        // Specific letter to get replaced in that message
        char letter;
        // Whether or not a letter has been replaced
        boolean replace;
        // The position of the alphabet the letter in the message is in
        int move;
        // The newly shifted message string
        String newmessage = "";
        // For loop to replace all the alphabetical character in the new message according to the shift value
        for (int messagePosition = 0; messagePosition < messageLength; messagePosition++) {
            // One letter at a time
            letter = message.charAt(messagePosition);
            replace = false;
            // Goes through the alphabet
            for (int count = 26; count < 52; count++) {
                move = count;
                // If conditional to add the newly shifted lowercase letter
                if (letter == upperAlphabet.charAt(count)) {
                    newmessage = newmessage + (upperAlphabet.charAt(move + shift));
                    replace = true;
                    // Else if conditional to add the newly shifted uppercase letter
                } else if (letter == lowerAlphabet.charAt(count)) {
                    newmessage = newmessage + (lowerAlphabet.charAt(move + shift));
                    replace = true;
                }
                // If conditional to check if all the letter in the alphabet have been checked, if so, add on the original character to the new message
                if (count == 51 && replace == false) {
                    newmessage = newmessage + (message.charAt(messagePosition));
                }
            }
        }
        return newmessage;
    }

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        // ICS4UE
        // Olivia Ye
        // Assignment 2: Cipher Assignment

        // Calls on the keyboard scanner
        Scanner keyboard = new Scanner(System.in);

        // Number of spaces to shift the message, length of the input, number of the most e's in the message, count variable, number of a specific letter
        int shift, messageLength = 0, numE = 0, count, numLetter = 0;
        // Type of encryption, letter variable
        char choice = 'm', letter;
        // Check if the satisfies the input requirements, whether or a best decode has been decided
        boolean checkInput = false, replace;
        // message input, other (game type, number of shifts) input, decoded/encoded message, lowercase string to check auto decodes, best decode
        String message = "", input = "", deEncode, sentCheck, best;
        // Stores each string for the automatic decode
        String[] automatic = new String[26];
        // Stores the frequency of e of each string for the automatic decode
        int[] eFreq = new int[26];
        // Upper case alphabet
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //                      0         1         2         3         4         5         6         7  
        //                      012345678901234567890123456789012345678901234567890123456789012345678901234567    
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
        // Lower case alphabet

        // Introductions and initial prompts
        System.out.println("Caeser cipher.");
        System.out.println("There are two different ways to use this program. To encode and decode.");
        System.out.println("To encode, please enter a number between 1-25, inclusive and your message. \nYour message will be moved to the right that number of spaces.");
        System.out.println("\nTo decode, please enter the encoded message. \nThe computer will display all 26 variations of the message and recommend one that seems most plausible.");
        System.out.print("\nPlease choose an option. Please enter 'e' to encode, 'd' to decode, 'a' for automatic, and 'q' to quit: ");

        // Do while loop to run the game when choice does not equal 'q'
        do {
            // While loop to check the choice variable
            while (checkInput == false) {
                input = keyboard.nextLine();
                checkInput = true;
                checkInput = emptyCheck(input, checkInput);
                // If conditional to reprompt for empty inputs or invalid inputs
                if (checkInput == false) {
                    System.out.print("\nInvalid input. Please choose an option. Enter 'e' to encode, 'd' to decode, 'a' for automatic, and 'q' to quit: ");
                } else {
                    choice = input.charAt(0);
                    // Prompts for re-entery if the choice variable does not meet requirements
                    if ((choice != 'e' && choice != 'd' && choice != 'd' && choice != 'a' && choice != 'q') || checkInput == false) {
                        System.out.print("\nInvalid choice. Please choose an option. Enter 'e' to encode, 'd' to decode, 'a' for automatic, and 'q' to quit: ");
                        checkInput = false;
                    }
                }
            }
            checkInput = false;

            // If conditional to prompt the audience to enter their message if the choice does not equal 'q'
            if (choice != 'q') {
                System.out.print("\nPlease enter your message ");
                // If conditional to display encode vs decode depending on the choice variable
                if (choice == 'e') {
                    System.out.print("to encode: ");
                } else {
                    System.out.print("to decode: ");
                }

                // While loop to check the message input
                while (checkInput == false) {
                    message = keyboard.nextLine();
                    checkInput = emptyCheck(message, checkInput);
                    // If conditional to prompt for re-entery if the choice variable does not meet requirements
                    if (checkInput == false) {
                        System.out.print("\nInvalid input. Please enter your message again: ");
                    }
                }
                messageLength = message.length();
            }
            checkInput = false;

            // If condiitonal to shift (decode/encode) the message if the choice was either 'e' or 'd'
            if (choice == 'e' || choice == 'd') {
                if (choice == 'e') {
                    System.out.print("\nPlease enter the number of spaces to shift your message right: ");
                } else {
                    System.out.print("\nPlease enter the number of spaces to shift your message left: ");
                }
                // While loop to check the input for number of shifts
                while (checkInput == false) {
                    input = keyboard.nextLine();
                    checkInput = emptyCheck(input, checkInput);
                    // If conditionals to prompt for re-entery if the choice variable does not meet requirements
                    if (checkInput == false) {
                        System.out.print("\nInvalid input. Please re-input the number of spaces to shift your message: ");
                    } else if (checkInput == true) {
                        // Try catch statement to check if the inputted character is an integer
                        try {
                            Integer.parseInt(input);
                            checkInput = true;
                        } catch (NumberFormatException e) {
                            checkInput = false;
                            System.out.print("\nYou have entered a non-numerical input. Please re-input the number of spaces to shift your message: ");
                        }
                    }
                    // If conditional to check if their inputted number is out of bounds
                    if (checkInput == true) {
                        if (Integer.parseInt(input) < -25 || Integer.parseInt(input) > 25) {
                            System.out.print("\nInvalid input. Please re-enter the number of spaces to shift your message: ");
                            checkInput = false;
                        }
                    }
                }
                shift = Integer.parseInt(input);

                // If conditional to turn the shift negative if decode is chosen
                if (choice == 'd') {
                    shift = shift * -1;
                }
                
                // Calls on the cipher method to decode/encode the message
                deEncode = cipher(message, lowerAlphabet, upperAlphabet, messageLength, shift);

                // If conditionals to print either encoded or decoded depending on the choice
                if (choice == 'e') {
                    System.out.println("\nYour encoded message with a shift of " + shift + " right is: " + deEncode);
                } else {
                    System.out.println("\nYour decoded message with a shift of " + shift * -1 + "left is: " + deEncode);
                }
            }

            //If conditional to brute force the message if the choice was 'a'
            if (choice == 'a') {
                best = "e";
                // For loop to shift and print 26 sentences 
                for (shift = 1; shift < 26; shift++) {
                    automatic[shift] = cipher(message, lowerAlphabet, upperAlphabet, messageLength, shift);
                    System.out.println("For a key of " + shift + ", decoded is: " + automatic[shift]);
                    sentCheck = (automatic[shift]).toLowerCase();

                    // If conditional to check the sentence for an occurence of the 10 most common words in english or number of most e's
                    if (sentCheck.indexOf("the") > -1 || sentCheck.indexOf("of") > -1 || sentCheck.indexOf("and") > -1 || sentCheck.indexOf(" a ") > -1 || sentCheck.indexOf(" to ") > -1
                            || sentCheck.indexOf("in") > -1 || sentCheck.indexOf("is") > -1 || sentCheck.indexOf("you") > -1 || sentCheck.indexOf("that") > -1 || sentCheck.indexOf(" it ") > -1) {
                        best = automatic[shift];
                    } else {
                        // For loop to check the number of e's in each sentence
                        for (int i = 0; i < messageLength; i++) {
                            letter = 'e';
                            // If conditional to increase the count if e's are present
                            if (letter == sentCheck.charAt(i)) {
                                numLetter++;
                            }
                            eFreq[shift] = numLetter;
                        }
                        // Determines the maxmimum numbers of e's in the sentences
                        numE = Math.max(numE, eFreq[shift]);
                        numLetter = 0;
                    }
                }

                // If conditional to set the best decode to the one with the common word or one with most e's
                if (best.compareTo("e") != 0) {
                    replace = false;
                    // For loop to determine which key and sentence in the array was the best
                    for (count = 1; count < 26 && replace == false; count++) {
                        if (best.compareTo(automatic[count]) == 0) {
                            replace = true;
                            count--;
                        }
                    }
                } 
                else {
                    replace = false;
                    // For loop to determine which sentence has the most e's
                    for (count = 0; count < 26 && replace == false; count++) {
                        if (numE == eFreq[count]) {
                            best = automatic[count];
                            replace = true;
                            count--;
                        }
                    }
                }
                System.out.println("Best decode was with a key of " + count + " right.");
                System.out.println("Decode was: " + best);
            }
            
            // Prompts the player to run the program again
            if (choice != 'q') {
                System.out.print("\nPlease choose an option. Please enter 'e' to encode, 'd' to decode, 'a' for automatic, and 'q' to quit: ");
                checkInput = false;
            }
        } while (choice != 'q');
        
        // Displays an end message
        System.out.println("\nThank you for using cipher! End.");
    }
}
