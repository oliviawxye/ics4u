/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yeoliviasortsearch;

// Importing relevant libraries
import java.io.File;
import java.util.ArrayList; 
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

/**
 *
 * @author oliviaye
 */
public class YeOliviaSortSearch {

    /**
     * Method to sort via the selection sort method
     *
     * @param list list of names
     * @param numNames number of names in the file
     * @param ascending whether or not the audience wants ascending names (true)
     * or descending names (false)
     * @return returns the sorted list of names
     */
    private static ArrayList<String> selectionSort(ArrayList<String> list, int numNames, boolean ascending) {
        String temp;
        boolean sorted = false;
        // While loop to sort while there are values being moved
        while (sorted == false) {
            sorted = true;
            // For loop to run for the number of names
            for (int j = 0; j < numNames - 1; j++) {
                int minVal = j;
                int i = j + 1;
                // While loop to select the name not in its proper position through each array pass and place it where it belongs
                while (i < numNames) {
                    // If conditional to execute if the current name comes earlier in the alphabet than the set earliest name
                    if (list.get(i).compareTo(list.get(minVal)) < 0) {
                        minVal = i;
                        sorted = false;
                    }
                    i++;
                }
                temp = list.get(minVal);
                list.set(minVal, (list.get(j)));
                list.set(j, temp);
            }
        }
        // If conditional to sort the list into descending order if the user chose descending
        if (ascending == false) {
            list = descending(list, numNames);
        }
        return list;
    }

    /**
     * Method to sort via the bubble sort method
     *
     * @param list list of names
     * @param numNames number of names in the file
     * @param ascending whether or not the audience wants ascending names (true)
     * or descending names (false)
     * @return returns the sorted list of names
     */
    private static ArrayList<String> bubbleSort(ArrayList<String> list, int numNames, boolean ascending) {
        String temp;
        boolean sorted = false;
        // While loop to sort while there are values being moved
        while (sorted == false) {
            sorted = true;
            // For loop to run for the number of names
            for (int d = 0; d < numNames - 1; d++) {
                // If the name comes later in the alphabet than the name in the next array, swaps their positions
                if (list.get(d).compareTo(list.get(d + 1)) > 0) {
                    sorted = false;
                    temp = list.get(d);
                    list.set(d, list.get(d + 1));
                    list.set(d + 1, temp);
                }
            }
        }
        // If conditional to sort the list into descending order if the user chose descending
        if (ascending == false) {
            list = descending(list, numNames);
        }
        return list;
    }

    /**
     * Method to sort via the insertion sort method
     *
     * @param list list of names
     * @param numNames number of names in the file
     * @param ascending whether or not the audience wants ascending names (true)
     * or descending names (false)
     * @return returns the sorted list of names
     */
    private static ArrayList<String> insertionSort(ArrayList<String> list, int numNames, boolean ascending) {
        int pos = 1;
        String temp;
        // While loop to run while position is less than the number of names
        while (pos < numNames) {
            int pos2 = pos;
            // While loop to inset the name in the proper position if the name in the position index comes later in the alphabet then the next name
            while (pos2 > 0 && list.get(pos2 - 1).compareTo(list.get(pos2)) > 0) {
                temp = list.get(pos2);
                list.set(pos2, list.get(pos2 - 1));
                list.set(pos2 - 1, temp);
                pos2--;
            }
            pos++;
        }
        // If conditional to sort the list into descending order if the user chose descending
        if (ascending == false) {
            list = descending(list, numNames);
        }
        return list;
    }

    /**
     * Method to sort any list of ascending names into descending order
     *
     * @param list list of names
     * @param numNames number of names in the file
     * @return returns the list of sorted names in descending order
     */
    private static ArrayList<String> descending(ArrayList<String> list, int numNames) {
        ArrayList<String> aList = new ArrayList<String>(numNames);
        // For loop to load the list of names into a separate array
        for (int count = 0; count < numNames; count++) {
            aList.add(list.get(count));
        }
        int count1 = numNames - 1;
        // For loop to reverse the order of the names in the sorted ascending array using the separate array
        for (int count = 0; count < numNames; count++) {
            list.set(count, aList.get(count1));
            count1--;
        }
        return list;
    }

    /**
     * Binary search method
     *
     * @param list list of sorted names
     * @param numNames number of names in the file
     * @param target target name to look for
     * @return returns the index
     */
    private static int binarySearch(ArrayList<String> list, int numNames, String target) {
        int low = 0;
        int high = numNames;
        int mid = 0;
        int return1 = -1;
        String name = "";
        // While loop to run while the low index is smaller than the high index
        while (low < high) {
            // Finds the midpoint between the low and high index
            mid = (low + high) / 2;
            // If conditional to return the mid index if the name at that index matches the target
            if ((list.get(mid).trim()).compareTo(target) == 0) {
                return mid;
            } else if (list.get(mid).compareTo(target) > 0) {
                // else if conditional to set the high index to one below mid if the target is before the name at the mid index
                high = mid;
            } else {
                // sets the low index to one above the mid if the target is after the name at the mid index
                low = mid;
            }
        }
        // Returns a value of -1 if the name is not found
        return -1;
    }

    /**
     * Method to print all the names in the list
     *
     * @param list list of sorted names
     * @param numNames number of names in the file
     */
    private static void printArrayList(ArrayList<String> list, int numNames) {
        System.out.println("\nThe names: ");
        // For loop to print out all the names in the array list
        for (int count = 0; count < numNames; count++) {
            System.out.println(list.get(count));
        }
        System.out.println("");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Import the names
        File file = new File("names.txt");
        // Call on the keyboard scanner
        Scanner keyboard = new Scanner(System.in);
        // Initialize the array list that will contain the list of names
        ArrayList<String> list = new ArrayList<String>();
        // Initalize the array list that will contain the sorted list of names
        ArrayList<String> sortedList = new ArrayList<String>();
        // If the files are loaded, to load all the names into the arraylist, if the user wants an ascending list, if the input is valid
        boolean loaded = false, run = true, ascending = true, check = true;
        // Option variable, number of names in the arraylist
        int option = 0, numNames;
        // Keyboard entry, desired name for the binary search
        String enter, target = "", gunk;
        // While loop to run the program while the chosen option is not 9
        while (option != 9) {
            // Prints out all the options and asks for an entry
            System.out.println("Options:");
            System.out.println("1 - Read Names from File and display the list");
            System.out.println("2 - Selection Sort Ascending and display the list");
            System.out.println("3 - Selection Sort Descending and display the list");
            System.out.println("4 - Bubble Sort Ascending and display the list");
            System.out.println("5 - Bubble Sort Descending and display the list");
            System.out.println("6 - Insertion Sort Ascending and display the list");
            System.out.println("7 - Insertion Sort Descending and display the list");
            System.out.println("8 - Binary Search");
            System.out.println("9 - Exit");
            System.out.print("Please choose an option from above (number): ");
            enter = keyboard.nextLine();

            // Checks to ensure there is no empty entry and the entry is valid
            try {
                enter.substring(0, 1);
                check = true;
                if (check = true) {
                    option = Integer.parseInt(enter);
                    if (option > 9 || option < 1) {
                        check = false;
                        System.out.print("Input invalid, please re-enter your choice.\n");
                    }
                }
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Input invalid, please re-enter your choice.\n");
                check = false;
            }

            // While loop to run while the input is valid
            while (check == true && option != 9) {
                // If conditional run if the input is 1
                if (option == 1) {
                    // If conditional to check if the files have already been loaded
                    if (loaded == true) {
                        System.out.println("Files already loaded.");
                        check = false;
                    } else { // If not, the file is then loaded into the array list
                        // Try catch structure is used to prevent exceptions from crashing the code
                        try {
                            Scanner input = new Scanner(file);
                            while (run == true) {
                                list.add(input.nextLine());
                            }
                        } catch (FileNotFoundException ex) {
                            run = false;
                        } catch (NoSuchElementException e) {
                        }
                        // If all the names were successfully loaded, displays a success message and prints the names
                        if (run == true) {
                            System.out.println("Files read - success.");
                            numNames = list.size();
                            printArrayList(list, numNames);
                            check = false;
                            loaded = true;
                        }
                    }
                }

                // If conditional to check if the file was loaded when the other options are selected if the choice is not 9
                if (loaded == false && option != 9) {
                    loaded = false;
                    System.out.println("Error: File not loaded. Please choose option 1 first.\n");
                }

                // If conditional to run the options if the file is loaded and the choice is not 9
                if (loaded == true && option != 9) {
                    numNames = list.size();
                    // Switch case structure to run the different options
                    switch (option) {
                        case 2:
                            // Ascending selection sort
                            ascending = true;
                            // Calls on the selectionSort method to sort the list into ascending order
                            sortedList = selectionSort(list, numNames, ascending);
                            // Prints all the names
                            printArrayList(sortedList, numNames);
                            break;
                        case 3:
                            // Descending selection sort
                            ascending = false;
                            // Calls on the selectionSort method to sort the list into descending order
                            sortedList = selectionSort(list, numNames, ascending);
                            // Prints all the names
                            printArrayList(sortedList, numNames);
                            break;
                        case 4:
                            // Ascending bubble sort
                            ascending = true;
                            // Calls on the bubbleSort method to sort the list into ascending order
                            sortedList = bubbleSort(list, numNames, ascending);
                            // Prints all the names
                            printArrayList(sortedList, numNames);
                            break;
                        case 5:
                            // Descending bubble sort
                            ascending = false;
                            // Calls on the bubbleSort method to sort the list into descending order
                            sortedList = bubbleSort(list, numNames, ascending);
                            // Prints all the names
                            printArrayList(sortedList, numNames);
                            break;
                        case 6:
                            // Ascending insertion sort
                            ascending = true;
                            // Calls on the insertionSort method to sort the list into ascending order
                            sortedList = insertionSort(list, numNames, ascending);
                            // Prints all the names
                            printArrayList(sortedList, numNames);
                            break;
                        case 7:
                            // Descending insertion sort
                            ascending = false;
                            // Calls on the insertionSort method to sort the list into descending order
                            sortedList = insertionSort(list, numNames, ascending);
                            // Prints all the names
                            printArrayList(sortedList, numNames);
                            break;
                        case 8:
                            // Binary search
                            // Sorts the list, ascending, using bubble sort to prepare for the search
                            ascending = true;
                            sortedList = bubbleSort(list, numNames, ascending);
                            // Asks the audience for the target name
                            System.out.print("What name would you like to find? Enter: ");
                            target = keyboard.next();
                            gunk = keyboard.nextLine();
                            // Calls on the binarySearch method to find the location of the name in the arrayList
                            int position = binarySearch(sortedList, numNames, target);
                            // If conditional to print different outcome messages depending on the index
                            if (position != -1) {
                                System.out.println("The index of your chosen name is " + position + ".\n");
                            } else {
                                System.out.println("This name was not found.\n");
                            }
                            break;
                    }
                }
                // Sets the input check to false
                check = false;
            }
        }
        // Display the end message
        System.out.println("\nEnd.");
    }
}
