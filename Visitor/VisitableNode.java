package Visitor;

import com.scalified.tree.multinode.ArrayMultiTreeNode;
import com.scalified.tree.*;
import java.util.*;


public class VisitableNode<T> extends ArrayMultiTreeNode<T> implements Visitable {
	
	private String Lexem;
	private String Type;
	private ArrayList<VisitableNode<String>> ChildList = new ArrayList<VisitableNode<String>>();
	
	
    public VisitableNode(T data) {
        super(data);
    }
    
    
	public VisitableNode(T data,  ArrayList<VisitableNode<String>> ... mynode) {
    	super(data);
    	
    	for(int i = 0; i < mynode.length; i ++){
    		
    		
    		VisitableNode<String> child_list = new VisitableNode<String>("child_list");
    		child_list.getChildList().addAll(mynode[i]);
    		this.add((TreeNode<T>) child_list);
    		
        	   
           
        }
    	
    }
    
   


	public VisitableNode(T data, String a) {
    	
    	super(data);
    	this.Lexem = a;
    	
    }
    
    public String getLexem() {
    	return this.Lexem;
    }
    public void setLexem(String label) {
    	this.Lexem = label;
    }
    public ArrayList<VisitableNode<String>> getChildList() {
    	return this.ChildList;
    }
    
    @Override
    public String accept(Visitor visitor) {
        return visitor.visit(this);
    }

    public Collection<? extends TreeNode<T>> subtrees() {
        if (isLeaf()) {
            return Collections.emptySet();
        }
        Collection<TreeNode<T>> subtrees = new ArrayList<>(subtreesSize);
        for (int i = 0; i < subtreesSize; i++) {
            TreeNode<T> subtree = (TreeNode<T>) this.subtrees[i];
            subtrees.add(subtree);
        }
        return subtrees;
    }


	public String getType() {
		return Type;
	}


	public void setType(String type) {
		Type = type;
	}
}
