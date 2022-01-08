/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.yeoliviacoffeeklatch;

/**
 *
 * @author 335462040
 */

/**
 * A coffee cup
 * In part 2, additional information will be added to this cup
 * @author RD
 */
public class CoffeeCup {

    /**
     * Initial constructor of the CoffeeCup class
     */
    public CoffeeCup(){
    }
    
    // Name of the customer
    private String name;
    // State of the cup (full/not full)
    private boolean isFull;
    // Display sentence based on the executed commands
    private String sentence;
    
    /**
     * Returns whether this cup is full (true) or empty(false);
     * @return the filled status of the cup
     */
    public boolean isFull() {
        return isFull;
    }
    
    /**
     * Fills the cup to the top
     */
    public void fill() {
        setIsFull(true);
    }
    
    /**
     * Drink the cup entirely
     */
    public boolean drink() {
        if (isFull) {
            sentence = "You glug the coffee down...";
            setIsFull(false);
            return true;
        } else {
            sentence = "You sip furiously, but only suck air.";
            return false;
        }

    }

    /**
     * Gets the name of the customer
     * @return name of the customer
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the customer
     * @param name name of the customer 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setting the fullness of the cup, whether it is empty(false) or full(true)
     * @param isFull fullness of the cup
     */
    public void setIsFull(boolean isFull) {
        this.isFull = isFull;
    }

    /**
     * Instructions sentence based on the actions taken
     * @return the sentence
     */
    public String getSentence() {
        return sentence;
    }

}
