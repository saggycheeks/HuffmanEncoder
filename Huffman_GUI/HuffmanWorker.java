package Huffman;

//It begins...
import java.util.Scanner;
import java.util.Vector;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by Johnathan Elson on 12/6/13.
 */
public class HuffmanWorker {

    private Scanner scan;
    private String tempChar;
    private String tempString;
    private String encodedMsg;

    HuffmanWorker(Scanner s){
        scan = s;
        tempString = " ";
    }


    /*
    reads in data to generate frequencies

    steps:
        1. read in the input string (into temp string)
        2. for each letter in the string (put that letter into temp char) DO 3
        3. search the vector to see if that character from the string is in the vector, if so increment it
        4. if the array is empty (capacity of 1) then just add it in
        5. or if the index is at the last cell and we failed the test for  they are the same that must mean its new
           (as in it made it all the way through the vector and this is a new and unique character, add it)
           THIS IS THE PART I HAD TROUBLE WITH PLEASE TEST THE HELL OUT OF IT
    
    **This should work now. It passes the tests I give it at the moment, but more testing can't hurt. - Matt

     */
    
    /**
     * 
     * CHANGE: HuffmanWorker.readData now takes a string as a parameter. This allows it
     * to work on strings that were taken from any kind of input, including files. This'll
     * make my life easier. Hope you don't mind, John.
     */
    public void readData(Vector<HuffmanCharacter> freq, String input){
        /*
        System.out.println("Enter String to generate frequency");
        
        CHANGE: Main menu now gets input
        
            tempString = scan.nextLine(); */
        tempString = input;
        
            A: for(int i=0; i<tempString.length(); i++){
                tempChar = (String.valueOf(tempString.charAt(i)));

                B: for(int j = 0; j<=freq.size(); j++){

                    //if(freq.capacity()==1){
                    if(freq.isEmpty()){
                        freq.addElement(new HuffmanCharacter(tempChar, 1, null));
                        break B;
                    }
                    /*
                    CHANGE: String.matches(String) expects a regular expression as input,
                    so let's use equals instead. This will allow special characters like
                    "?", "!", etc.
                    */
                    else if(freq.get(j).getCharacter().equals(tempChar)){
                        freq.get(j).setFrequency((1+ freq.get(j).getFrequency()));
                        break B;
                    }
                    //else if(j==(freq.capacity()-1)){
                   // else if(freq.lastElement().getCharacter().matches(freq.get(j).getCharacter())){
                    /*
                    CHANGE: The line above allowed for several entries of the same letter. If we've 
                    reached the last element in the vector, the letter must be unique. Add it in.
                    */
                    else if(j == freq.size() - 1){
                        freq.addElement(new HuffmanCharacter(tempChar, 1, null));
                        break B;
                    }




                }

            }
    }
    /*
    msg is what we want to send/encode
    encoded will be the binary
    check each character of the string against each character in the vector and if match add binary
    miss if the currently letter is not the correct one, if misses equal size of vector that means
    the letter is not even in the vector and was never added in during frequency generation so print out bad character
     */
    
    /**
     * 
     * CHANGE: HuffmanWorker.encodeMessage now takes a string as a parameter and returns an encoded string.
     * This allows a message to be encoded from any input, including files. Also, we can redirect the encoded
     * output anywhere we want now. Even to a file. Again, hope you don't mind, John.
     */

    public String encodeMessage(Vector<HuffmanCharacter> freq, String msg){

        //String msg;

        encodedMsg = "";
        /*
        CHANGE: Moved to Main Menu
        System.out.println("Enter the Message to Encode");
        //CHANGE: changed from scan.next() to scan.nextLine()
        msg = scan.nextLine();
        */
        for(int i = 0; i<msg.length(); i++){
            int miss = 0;

            for(int j = 0; j<freq.size(); j++){
                //CHANGE: changed String.matches(String) to String.equals(String)
                if(freq.get(j).getCharacter().equals(String.valueOf(msg.charAt(i))))
                    encodedMsg += freq.get(j).getCode();
                else
                    miss++;

                if(miss==freq.size())
                    System.out.println("Bad Character " + String.valueOf(msg.charAt(i)));
            }
        }
        /* CHANGE: Moved to main menu
        System.out.println("Here is the Encoded Message");
        System.out.println();
        System.out.println(encodedMsg);
                */
        return encodedMsg;

    }

    public void decodeMessage(Vector<HuffmanCharacter> freq){

        String finalMsg = "";
        String temp = "";

        for(int i = 0; i < encodedMsg.length(); i++){
            temp += encodedMsg.charAt(i);

            for(int j = 0; j<freq.size(); j++){
                //CHANGE: changes String.matches(String) to String.equals(String)
                if(temp.equals(freq.get(j).getCode())){
                    finalMsg += freq.get(j).getCharacter();
                    temp = "";
                }
            }



        }

        System.out.println("Here is the Decoded Message");
        System.out.println();
        System.out.println(finalMsg);


    }
    /**
     * HuffmanWorker.EncodeFromFile(Vector<HuffmanCharacter>, String)
     * 
     * 
     * This method opens a file and reads it in line by line. Each line is sent to
     * HuffmanWorker.encodeMessage and the encoded message is then written to another
     * file with a file name of "_" + filename. 
     * 
     * TODO: Ask user for output file name.
     */
    public void encodeFromFile(Vector<HuffmanCharacter> freq, String fileName){
        InputStream inFile;
        BufferedReader inBuffer;
        String input, output;
        OutputStream outFile;
        BufferedWriter outBuffer;
        
        try{
            //open the files for input and output.
            inFile = new FileInputStream(fileName);
            inBuffer = new BufferedReader(new InputStreamReader(inFile, Charset.forName("UTF-8")));
            outFile = new FileOutputStream("_" + fileName);
            outBuffer = new BufferedWriter(new OutputStreamWriter(outFile, Charset.forName("UTF-8")));
            //read a line from the input file            
            input = inBuffer.readLine();
            while(input != null){               
                //send the line to be encoded
                output = encodeMessage(freq, input);
                //write the encoded data to the output file 
                outBuffer.write(output);
                //get another line of input
                input = inBuffer.readLine();
            }
            
            inBuffer.close();
            outBuffer.close();

        }
        catch(FileNotFoundException e){
            System.out.println("Error: Couldn't open " + fileName + " or " + "_" + fileName + " for reading.");
            return;
        }
        catch(IOException e){
            System.out.println("IOException occurred in EncodeFromFile. Error reading or writing to a file.");
            return;
        }
    }
    /**
     * Huffman.decodeFromFile(Vector<HuffmanCharacter>, String)
     * 
     * Reads a line of input from a file and adds it to a buffer. That 
     * buffer is sent through the decoding algorithm written by John Elson. 
     * As characters are decoded, their code is removed from the buffer. After 
     * The data is decoded, it is written to an output file. More code
     * is appended to the buffer and it is run through the algorithm again. This
     * continues until there is no more input from the input file.
     * 
     * If there is data in the buffer after the last run of decoding, there
     * was an error during the decoding. Let the user know and exit.
     * 
     */
    public void decodeFromFile(Vector<HuffmanCharacter> freq, String fileName){
        InputStream inFile;
        BufferedReader inBuffer;
        String input, output;
        OutputStream outFile;
        BufferedWriter outBuffer;
        
        try{
            //open the files for input and output.
            inFile = new FileInputStream(fileName);
            inBuffer = new BufferedReader(new InputStreamReader(inFile, Charset.forName("UTF-8")));
            outFile = new FileOutputStream("_" + fileName);
            outBuffer = new BufferedWriter(new OutputStreamWriter(outFile, Charset.forName("UTF-8")));
            //read a line from the input file            
            input = inBuffer.readLine();
            while(input != null){
                //Begin Decode process for this data (Original code by John Elson. Modifications are noted.)
                String finalMsg = "";
                String temp = "";

                for(int i = 0; i < input.length(); i++){
                    temp += input.charAt(i);

                    for(int j = 0; j<freq.size(); j++){
                        //CHANGE: changed String.matches(String) to String.equals(String)
                        if(temp.equals(freq.get(j).getCode())){
                            finalMsg += freq.get(j).getCharacter();
                            temp = "";
                            //CHANGE: Delete the part of the string that was matched
                            //and start again from the new beginning. This should allow
                            //incomplete codes to be read in. After no match is found, the
                            //rest of the code should be added to the string.
                            input = input.substring(i+1, input.length());
                            //need to start at element 0 next time around
                            i = -1;
                            //we're done with this loop... for now...
                            break;
                        }
                    }
                }
                //write the decoded data to the output file
                outBuffer.write(finalMsg);
                //get another line of input
                temp = inBuffer.readLine();
                if (temp == null){
                    if(!input.equals(""))
                        System.out.println("Error decoding " + fileName);
                    input = null;
                    continue;
                }
                input = input + temp;
            }
            inBuffer.close();
            outBuffer.close();
        }
        catch(FileNotFoundException e){
            System.out.println("Error: Couldn't open " + fileName + " for reading.");
            return;
        }
        catch(IOException e){
            System.out.println("Couldn't read from " + fileName + ". IOException occurred.");
            return;
        }

    }
    /**
     *  Huffman.readFromFile(Vector<HuffmanCharacter>, String)
     * 
     * 
     * Reads characters from a file to add their frequencies to the huffman code.
     * the file is read one line at a time.
     */
    public void readFromFile(Vector<HuffmanCharacter> freq, String fileName){

        InputStream file;
        BufferedReader buffer;
        String input;
        try{
            file = new FileInputStream(fileName);
            buffer = new BufferedReader(new InputStreamReader(file, Charset.forName("UTF-8")));
            input = buffer.readLine();
            while(input != null){
                readData(freq, input);
                input = buffer.readLine();
            }
            buffer.close();
        }
        catch(FileNotFoundException e){
            System.out.println("Error: Couldn't open " + fileName + " for reading.");
            return;
        }
        catch(IOException e){
            System.out.println("Couldn't read from " + fileName + ". IOException occurred.");
            return;
        }

       
        
    }


}
