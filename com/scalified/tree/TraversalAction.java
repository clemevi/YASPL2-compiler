

package com.scalified.tree;


public interface TraversalAction<T extends TreeNode> {

	
	void perform(T node);

	
	boolean isCompleted();

}
