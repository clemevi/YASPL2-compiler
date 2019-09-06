

package com.scalified.tree.multinode;

import com.scalified.tree.TreeNode;
import com.scalified.tree.TreeNodeException;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;


public abstract class MultiTreeNode<T> extends TreeNode<T> {

	
	public MultiTreeNode(T data) {
		super(data);
	}

	
	public boolean addSubtrees(Collection<? extends MultiTreeNode<T>> subtrees) {
		if (areAllNulls(subtrees)) {
			return false;
		}
		for (MultiTreeNode<T> subtree : subtrees) {
			linkParent(subtree, this);
			if (!add(subtree)) {
				return false;
			}
		}
		return true;
	}

	
	public Collection<? extends MultiTreeNode<T>> siblings() {
		if (isRoot()) {
			String message = String.format("Unable to find the siblings. The tree node %1$s is root", root());
			throw new TreeNodeException(message);
		}
		Collection<? extends TreeNode<T>> parentSubtrees = parent.subtrees();
		int parentSubtreesSize = parentSubtrees.size();
		if (parentSubtreesSize == 1) {
			return Collections.emptySet();
		}
		Collection<MultiTreeNode<T>> siblings = new HashSet<>(parentSubtreesSize - 1);
		for (TreeNode<T> parentSubtree : parentSubtrees) {
			if (!parentSubtree.equals(this)) {
				siblings.add((MultiTreeNode<T>) parentSubtree);
			}
		}
		return siblings;
	}

	
	public boolean hasSubtrees(Collection<? extends MultiTreeNode<T>> subtrees) {
		if (isLeaf()
				|| areAllNulls(subtrees)) {
			return false;
		}
		for (MultiTreeNode<T> subtree : subtrees) {
			if (!this.hasSubtree(subtree)) {
				return false;
			}
		}
		return true;
	}

	
	public boolean dropSubtrees(Collection<? extends MultiTreeNode<T>> subtrees) {
		if (isLeaf()
				|| areAllNulls(subtrees)) {
			return false;
		}
		boolean result = false;
		for (MultiTreeNode<T> subtree : subtrees) {
			boolean currentResult = dropSubtree(subtree);
			if (!result && currentResult) {
				result = true;
			}
		}
		return result;
	}

}
