/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Huffman;
/**
 *
 * @author NubOfTheDay
 */
public class HuffmanCharacter implements Comparable<HuffmanCharacter>{
    private final String character;   //holds the character this code references (A, B, C, etc)
    private int frequency; //holds the number of times this character has shown up/ CHANGE: Not Final
    private String code; //holds the binary representation of this character (01101... etc)
    
    //constructor
    public HuffmanCharacter(String character, int frequency, String code){
        this.character = character;
        this.frequency = frequency;
        this.code = code;
    }
    
    //copy constructor
    public HuffmanCharacter(HuffmanCharacter copy){
        this.character = copy.getCharacter();
        this.code = copy.getCode();
        this.frequency = copy.getFrequency();
    }
    
    public String getCharacter(){
        return this.character;
    }
    
    public int getFrequency(){
        return this.frequency;
    }

    public void setFrequency(int frequency) { this.frequency = frequency; }//CHANGE: Added
    
    public String getCode(){
        return this.code;
    }
    
    public void setCode(String code){
        this.code = code;
    }
    
    public int compareTo(HuffmanCharacter other){
        if (other.getFrequency() > this.frequency )
            return -1;
        if (other.getFrequency() < this.frequency )
            return 1;
        else
            return 0;
    }
    
    public boolean equals(HuffmanCharacter other){
        if (other.frequency == this.frequency)
            return true;
        return false;
    }
    
    public String toString(){
        String out = "Character: " + character + "\n" +
                "Frequency: " + frequency + "\n" +
                "Code: " + code + "\n";
        return out;
    }
}

