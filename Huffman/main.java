/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Huffman;
import java.util.*;

/**
 *
 * @author NubOfTheDay
 */
public class main {


    /*
    INSTRUCTIONS:
    Look at Switch below for options

    FOR NOW USE ONLY 1 WORD MESSAGES FOR EVERYTHING

    THINGS IT DOES:
    - Generates Frequency from a single string (no spaces at the moment :\ use Number 1 )
    - Encodes, Prints to Screen then Decodes and Prints to Screen a single string (on press of 3, AFTER PREVIOUS FREQUENCY GENERATION)
    - Encoding can handle letters not in the frequency table by skipping them and printing them out as bad character "*" feel free to try it
    - Able to view frequencies and code for each letter, if code is null (not generated yet) just displays frequencies
    - Essentially works for basic functionality
    - Can read in for frequencies multiple times and will update the frequency table accordingly (use number  1, then use it again lol, slight bug, see below)

    THINGS IT DOESN'T DO YET OR HAS ISSUES WITH:
    - No file support (reading from file) for anything (generating frequency, encoding a message from file, or writing coded message to file) // MAIN THING TO BE ADDED pls do if you can
    - Currently only supports single string for everything because there's no good way to read paragraphs from system.in without
           -going into infinite loop because scanner.hasnext() always returns true
           -using a special character or word to indicate stop reading which would mean we could never send that message or character
           -Basically its hard so fix if you can plz do, there's not end of file (EOF) in system.in (that we can nicely use)
    -PLEASE TEST THE READING IN PART FOR FREQUENCIES, TRY LOTS OF STRINGS AND MAKE SURE ITS WORKING, IT HAS BEEN DIFFICULT AND I HATE IT AND I HATE VECTORS AS WELL
      -The menu system sucks......alot
      -No GUI...... :(
     */



    
    public static void main(String[] args){
        /* ORIGINAL TEST - USED FOR REFERENCE
        HuffmanCharacter a, b, c, d, e;
        
        a = new HuffmanCharacter("a", 12, null);
        b = new HuffmanCharacter("b", 5, null);
        c = new HuffmanCharacter("c", 7, null);
        d = new HuffmanCharacter("d", 1, null);
        e = new HuffmanCharacter("e", 3, null);
        
        HuffmanTree tree = new HuffmanTree();
        HuffmanCharacter[] chars = {a, c, b, e, d};
        tree.generateTree(chars);
        Vector<HuffmanCharacter> data = tree.generateCodes();
        for(int i=0; i < 5; i++){
            System.out.println(data.get(i).toString());
        }
        */

        boolean mainLoop = true; //MAIN LOOP
        String input = null; //INPUT STRING
        Scanner scan = new Scanner(System.in); //SCANNER
        String output = null; //OUTPUT STRING  CHANGE: Added

        HuffmanWorker worker = new HuffmanWorker(scan); //WORKER

        HuffmanTree tree = new HuffmanTree(); //TREE 

        Vector<HuffmanCharacter> data = new Vector<HuffmanCharacter>(1, 1); //MAIN VECTOR


        /*
        Switch to determine which to select
         */

        while(mainLoop){
            System.out.println("0: Exit, 1: read data, 2: view frequencies, 3: Encode a message, 4: Encode from file, 5: Decode from file, 6: Read from file");
            System.out.println(data.size()+" "+data.capacity()); //This helped me debug, feel free to comment out
            /*
              CHANGE: Instead of using next(), which grabs strings based on delimiters that include whitespace,
            use nextLine(). The delimiter for nextLine() is a carriage return. BE CAREFUL WITH THIS! 
            Make sure the last newline entered as input was consumed by the scanner. This is not true with
            next() and other methods like it. If the last '\n' wasn't consumed, you can get a string of length
            zero as input.
            */
            input = scan.nextLine();
            switch(input.charAt(0)){

                case '0':
                    scan.close();
                    mainLoop = false;;
                    break;
                case '1':
                   /*
                    CHANGE: Main menu now gets input from user. See HuffmanWorker.readData() for
                    more information.
                    */
                    System.out.println("Enter String to generate frequency");
                    input = scan.nextLine();
                    worker.readData(data, input);

                    selectionSort(data);
                    /*
                    CHANGE: instead of referencing the capacity of the vector, the size of the array
                    should be dependant on the size of the vector. size() returns the number of elements
                    in the vector while capacity() returns the total number of elements that can be 
                    stored currently.
                    */
                    tree.generateTree(data.toArray(new HuffmanCharacter[data.size()])); 

                    data = tree.generateCodes();
                    break;
                case '2':
                    viewFreq(data);
                    break;
                case '3':
                    
                    System.out.println("Enter the Message to Encode");
                    //CHANGE: changed from scan.next() to scan.nextLine()
                    input = scan.nextLine();
                    output = worker.encodeMessage(data, input);
                    System.out.println("Here is the Encoded Message");
                    System.out.println();
                    System.out.println(output);                    
                    worker.decodeMessage(data);
                    break;
                case '4':
                    System.out.println("Enter a file name to encode from");
                    input = scan.nextLine();
                    worker.encodeFromFile(data, input);
                    break;
                    
                case '5':
                    System.out.println("Enter a file name to decode from");
                    input = scan.nextLine();
                    worker.decodeFromFile(data, input);
                    break;
                    
                case '6':
                    System.out.println("Enter a file name to read from");
                    input = scan.nextLine();
                    worker.readFromFile(data, input);
                    tree.generateTree(data.toArray(new HuffmanCharacter[data.size()]));
                    data = tree.generateCodes();
                    break;
                    
                default:
                    System.out.println("Bad Input");
                    break;


            }



            input = null;
        }
    }


    /*
    Goes through the vector and prints characters, frequencies, and codes
     */
    public static void viewFreq(Vector<HuffmanCharacter> freq){

        if(freq.get(0).getCode()!=null){ //If the codes have yet to generated, skip down and just print frequencies and characters
            for(int i = 0; i<freq.size(); i++){
                System.out.println(freq.get(i).getCharacter() + " " + freq.get(i).getFrequency() + " " + freq.get(i).getCode());
            }

        }
        else{
            for(int i = 0; i<freq.capacity(); i++){
                System.out.println(freq.get(i).getCharacter() + " " + freq.get(i).getFrequency());
            }
        }


    }
    //No explanation here, look up selection sort, i barely understand it

    public static void selectionSort(Vector<HuffmanCharacter> freq){

        for(int i = 0; i<freq.size(); i++){
            int min = i;

            for(int j = i; j<freq.size(); j++){

                if(freq.get(min).getFrequency() < freq.get(j).getFrequency())
                    min = j;

            }
            //swapValues(i, min)

            HuffmanCharacter temp = freq.get(i);
            freq.set(i, freq.get(min));
            freq.set(min, temp);


        }


    }


    
}
