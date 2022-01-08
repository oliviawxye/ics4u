/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.yeoliviacoffeeklatch;
/* A fancy coffee maker.  Makes coffee of varying strengths. */
/**
 *
 * @author RDCOMP
 */
public class CoffeeMachine {

    /**
     * Initial coffee machine constructor
     */
    public CoffeeMachine () {
    }
    
    // Strength of the coffee
    private String strength;
    // Water level in the machine
    private int waterLevel = 0;
    // Display sentence based on the executed commands
    private String sentence;
    // Numerical size of the cup
    private int cupSize;
    
    /**
     * Set the strength of the Coffee to s; affects the fineness of the
     * grind.
     * Options are "Weak, "Regular", "Strong"
     * @param s Text Description of Strength
     */
    public void setStrength(String s) {
        strength = s;
    }
    
    /**
     * Grinds the beans for the coffee
     */
    public void grindBeans() {
        // Creates a display sentence with the appropriate strength
        sentence = "Grinding beans for " + getStrength() + " coffee...";
    }

    /**
     * Brew the coffee into given cup c
     */
    public void brew() {
        sentence = "Brewing " + getStrength() + " coffee into the pot...";
    }

    /**
     * Adds 10 units of water to the machine reservoir
     */
    public void addWater() {
        setWaterLevel(10);
        // Creates a display sentence
        sentence = "Adding 10 units of water to the Machine...";
    }

    /**
     * Add the beans to the machine
     */
    public void addBeans() {
        // Creates a display sentence
        sentence = "Adding " + getStrength() + " Beans to the Machine...";
    }

    /**
     * Pours the coffee from the machine into the cup
     * @param size The size of the cup
     * @param waterLevel The current water level in the machine
     * @return The water level after pouring a cup of coffee
     */
    public int pourCup(int size, int waterLevel) {
        waterLevel = waterLevel - size;
        return waterLevel;
    }
    
    /**
     * Gets the numerical size (units) of the cup
     * @return the cupSize
     */
    public int getCupSize() {
        return cupSize;
    }

    /**
     * Sets the numerical size (units) of the cup
     * @param cupSize the cupSize to set
     */
    public void setCupSize(int cupSize) {
        this.cupSize = cupSize;
    }
    
    /**
     * Gets the customer's chosen strength
     * @return the strength
     */
    public String getStrength() {
        return strength;
    }

    /**
     * Gets the instructions sentence based on the actions taken
     * @return the sentence
     */
    public String getSentence() {
        return sentence;
    }
    
    /**
     * Gets the water level in the machine (max 10 units)
     * @return the waterLevel in the machine
     */
    public int getWaterLevel() {
        return waterLevel;
    }

    /**
     * Sets the water level in the machine
     * @param waterLevel the waterLevel to set
     */
    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
    }

}
