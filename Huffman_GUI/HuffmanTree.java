/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Huffman;
//for vector
import java.util.*;
/**
 *
 * @author NubOfTheDay
 */
public class HuffmanTree {
    private HuffmanTreeNode head;
    private int charactersInTree;
    Vector<HuffmanCharacter> characters;
    //generateTree generates a huffman tree from the supplied huffman
    //characters. When this is finished, head will point to the root of
    //a huffman tree.
    
    public HuffmanTree(){
        head = null;
        charactersInTree = 0;
        characters = new Vector<HuffmanCharacter>();
    }
    //generate a tree with the provided huffman characters.
    //assume the characters are already sorted.
    public void generateTree(HuffmanCharacter[] characters){
        charactersInTree = characters.length;
        int charactersLeft = charactersInTree;
        
        //special case where there is only one character
        if(charactersInTree == 1){
            head = new HuffmanTreeNode(characters[0], null, null);
            return;
        }
        //special case where the list is empty (dammit...)
        //nothing to do, so just return.
        if(charactersInTree == 0){
            return;
        }
        //all other cases go here
        //create nodes with all of the characters in the array.
        //These nodes will be the leaf nodes of the tree. 
        HuffmanTreeNode[] nodes = new HuffmanTreeNode[charactersInTree];
        for(int i = 0; i < charactersInTree; i++){
            nodes[i] = new HuffmanTreeNode(characters[i], null, null);
        }
        //Build the huffman tree.
        //Two leaf nodes are grabbed at a time and
        //are replaced by a HuffmanTreeNode that points
        //to them. This is repeated until there is only one node left. That
        //would be the root of the tree.
        HuffmanTreeNode right = null;
        HuffmanTreeNode left = null;
        HuffmanTreeNode temp = null;
        while(charactersLeft > 1){
            //Grab the two characters on the far left
            right = nodes[charactersLeft - 1];
            left = nodes[charactersLeft - 2];
            //Make a new node that points to those characters
            temp = new HuffmanTreeNode(null, left, right);
            //delete the old nodes from the list and update the length.
            //This is to ensure re-insertion goes smoothly. (Thank you
            //kentucky jam)
            nodes[charactersLeft - 1] = null;
            nodes[charactersLeft - 2] = null;
            charactersLeft = charactersLeft - 2;
            //re-insert the new node into an appropriate place in the array.
            //This will be as far left in the array as possible. (The array is
            //ordered from largest to smallest.)

            for(int i = 0; i <= charactersLeft; i++){
                //either reached the end of the array or there was only
                //two nodes left. Either way, insert the node here, increment
                //charactersLeft and move on.
                if(nodes[i] == null){
                    nodes[i] = temp;
                    charactersLeft++;
                    break;
                }
                //Check to see if the node has a higher or equalfrequency than the current
                //node. If it does, then insert it here. 
                if(temp.getData().getFrequency() >= nodes[i].getData().getFrequency()){
                    //start at the back of the array and move the nodes over
                    //by one space
                    for(int k = charactersLeft; k > i; k--){
                        nodes[k] = nodes[k - 1];
                    }
                    //insert node here, increment charactersLeft and move on.
                    nodes[i] = temp;
                    charactersLeft++;
                    break;
                }
            }
            //rinse and repeat
        }
        //There should only be one node now. That's the head node.
        this.head = nodes[0];
        return;
    }

    //Visit node, visit left branch, visit right branch, end it.
    public void searchTree(HuffmanTreeNode currentNode, String code){
        //leaf node is here
        if(currentNode.getLeftNode() == null && currentNode.getRightNode() == null){
            currentNode.getData().setCode(code);
            characters.add(currentNode.getData());
        }
        
        if(currentNode.getLeftNode() != null){
            searchTree(currentNode.getLeftNode(), code + "0");
        }
        
        if(currentNode.getRightNode() != null){
            searchTree(currentNode.getRightNode(), code + "1");
        }
        
    }
    public Vector<HuffmanCharacter> generateCodes(){
        String currentCode = "";
        //HuffmanTreeNode currentNode = head;
        /*
        CHANGE: Throw out the old vector of characters and make a new one.
        Memory grows on trees, right? Well this memory does ;)
        */
        characters = new Vector<HuffmanCharacter>();
        
        if(head == null)
            return null;
        
        //I'm doing this with recursion because it should work, right? RIGHT?!?
        //If there's only one character, just give it a code of 0.
        if(charactersInTree == 1){
            head.getData().setCode("0");
            characters.add(head.getData());
        }
        else{
            searchTree(head, currentCode);
        }
         
        return characters;
    }
    
    
}
