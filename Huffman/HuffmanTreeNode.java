/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * This is the class for the nodes of the huffman tree. All nodes must have a 
 * frequency in order to generate a huffman code for the characters in the tree.
 * 
 * If a node has no nodes below it (ie. they are assigned as null) then the 
 * frequency of this node is the frequency of the data. (All leafs must be a 
 * character in the code...)
 *
 * If a node has a null data, it must have two children, a left and right
 * node. In this case, a dummy data is created where the character is null,
 * and the frequency is the sum of the frequencies of the children. 
 */
package Huffman;

/**
 *
 * @author NubOfTheDay
 */
public class HuffmanTreeNode {
    private final HuffmanTreeNode leftNode;
    private final HuffmanTreeNode rightNode;
    private final HuffmanCharacter data;
    
    public HuffmanTreeNode(HuffmanCharacter data, HuffmanTreeNode leftNode, HuffmanTreeNode rightNode){
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        //leftNode and rightNode are null if the node is a leaf
        //if this is a leaf, the frequency is equal to the data frequency
        //(The data is not null)
        if(this.leftNode == null && this.rightNode == null){
            this.data = data;
            return;
        }    
        //if the data is null, this is not a leaf node and it will have
        //two nodes below it. The frequency for this node is the sum of
        //the two frequencies below it.
        //(ALL NODES MUST HAVE A FREQUENCY TO GENERATE A HUFFMAN CODE!)
        else{
            //The frequency is the sum of the frequencies below it.
            //in the case of a single character being encoded in the
            //huffman code, one of the children may be null. Just check
            //for this and add the frequency if it exists.
            //SANITY CHECK: what happens if the character list is empty?
            int frequency = 0;
            if(this.leftNode != null)
                frequency += leftNode.getData().getFrequency();
            if(this.rightNode != null)
                frequency += rightNode.getData().getFrequency();
            this.data = new HuffmanCharacter(null, frequency, null);
            return;
        }
    }
    
    public HuffmanTreeNode getLeftNode(){
        return this.leftNode;
    }
    
    public HuffmanTreeNode getRightNode(){
        return this.rightNode;
    }
    
    public HuffmanCharacter getData(){
        return this.data;
    }
}
