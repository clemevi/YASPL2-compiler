

package com.scalified.tree.multinode;

import com.scalified.tree.TraversalAction;
import com.scalified.tree.TreeNode;
import com.scalified.tree.TreeNodeException;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;


public class LinkedMultiTreeNode<T> extends MultiTreeNode<T> {

	
	private static final long serialVersionUID = 1L;

	
	private LinkedMultiTreeNode<T> leftMostNode;

	
	private LinkedMultiTreeNode<T> rightSiblingNode;

	
	private LinkedMultiTreeNode<T> lastSubtreeNode;

	
	public LinkedMultiTreeNode(T data) {
		super(data);
	}

	
	@Override
	public Collection<? extends TreeNode<T>> subtrees() {
		if (isLeaf()) {
			return Collections.emptySet();
		}
		Collection<TreeNode<T>> subtrees = new LinkedHashSet<>();
		subtrees.add(leftMostNode);
		LinkedMultiTreeNode<T> nextSubtree = leftMostNode.rightSiblingNode;
		while (nextSubtree != null) {
			subtrees.add(nextSubtree);
			nextSubtree = nextSubtree.rightSiblingNode;
		}
		return subtrees;
	}

	
	@Override
	public boolean add(TreeNode<T> subtree) {
		if (subtree == null) {
			return false;
		}
		linkParent(subtree, this);
		if (isLeaf()) {
			leftMostNode = (LinkedMultiTreeNode<T>) subtree;
			lastSubtreeNode = leftMostNode;
		} else {
			lastSubtreeNode.rightSiblingNode = (LinkedMultiTreeNode<T>) subtree;
			lastSubtreeNode = lastSubtreeNode.rightSiblingNode;
		}
		return true;
	}

	
	@Override
	public boolean dropSubtree(TreeNode<T> subtree) {
		if (subtree == null
				|| isLeaf()
				|| subtree.isRoot()) {
			return false;
		}
		if (leftMostNode.equals(subtree)) {
			leftMostNode = leftMostNode.rightSiblingNode;
			unlinkParent(subtree);
			((LinkedMultiTreeNode<T>) subtree).rightSiblingNode = null;
			return true;
		} else {
			LinkedMultiTreeNode<T> nextSubtree = leftMostNode;
			while (nextSubtree.rightSiblingNode != null) {
				if (nextSubtree.rightSiblingNode.equals(subtree)) {
					unlinkParent(subtree);
					nextSubtree.rightSiblingNode = nextSubtree.rightSiblingNode.rightSiblingNode;
					((LinkedMultiTreeNode<T>) subtree).rightSiblingNode = null;
					return true;
				} else {
					nextSubtree = nextSubtree.rightSiblingNode;
				}
			}
		}
		return false;
	}

	
	@Override
	public void clear() {
		if (!isLeaf()) {
			LinkedMultiTreeNode<T> nextNode = leftMostNode;
			while (nextNode != null) {
				unlinkParent(nextNode);
				LinkedMultiTreeNode<T> nextNodeRightSiblingNode = nextNode.rightSiblingNode;
				nextNode.rightSiblingNode = null;
				nextNode.lastSubtreeNode = null;
				nextNode = nextNodeRightSiblingNode;
			}
			leftMostNode = null;
		}
	}

	
	@Override
	public TreeNodeIterator iterator() {
		return new TreeNodeIterator() {

			
			@Override
			protected TreeNode<T> leftMostNode() {
				return leftMostNode;
			}

			
			@Override
			protected TreeNode<T> rightSiblingNode() {
				return rightSiblingNode;
			}

		};
	}

	
	@Override
	public boolean isLeaf() {
		return leftMostNode == null;
	}

	
	@Override
	public boolean hasSubtree(TreeNode<T> subtree) {
		if (subtree == null
				|| isLeaf()
				|| subtree.isRoot()) {
			return false;
		}
		LinkedMultiTreeNode<T> nextSubtree = leftMostNode;
		while (nextSubtree != null) {
			if (nextSubtree.equals(subtree)) {
				return true;
			} else {
				nextSubtree = nextSubtree.rightSiblingNode;
			}
		}
		return false;
	}

	
	@Override
	public boolean contains(TreeNode<T> node) {
		if (node == null
				|| isLeaf()
				|| node.isRoot()) {
			return false;
		}
		LinkedMultiTreeNode<T> nextSubtree = leftMostNode;
		while (nextSubtree != null) {
			if (nextSubtree.equals(node)) {
				return true;
			}
			if (nextSubtree.contains(node)) {
				return true;
			}
			nextSubtree = nextSubtree.rightSiblingNode;
		}
		return false;
	}

	
	@Override
	public boolean remove(TreeNode<T> node) {
		if (node == null
				|| isLeaf()
				|| node.isRoot()) {
			return false;
		}
		if (dropSubtree(node)) {
			return true;
		}
		LinkedMultiTreeNode<T> nextSubtree = leftMostNode;
		while (nextSubtree != null) {
			if (nextSubtree.remove(node)) {
				return true;
			}
			nextSubtree = nextSubtree.rightSiblingNode;
		}
		return false;
	}

	
	@Override
	public void traversePreOrder(TraversalAction<TreeNode<T>> action) {
		if (!action.isCompleted()) {
			action.perform(this);
			if (!isLeaf()) {
				LinkedMultiTreeNode<T> nextNode = leftMostNode;
				while (nextNode != null) {
					nextNode.traversePreOrder(action);
					nextNode = nextNode.rightSiblingNode;
				}
			}
		}
	}

	
	@Override
	public void traversePostOrder(TraversalAction<TreeNode<T>> action) {
		if (!action.isCompleted()) {
			if (!isLeaf()) {
				LinkedMultiTreeNode<T> nextNode = leftMostNode;
				while (nextNode != null) {
					nextNode.traversePostOrder(action);
					nextNode = nextNode.rightSiblingNode;
				}
			}
			action.perform(this);
		}
	}

	
	@Override
	public int height() {
		if (isLeaf()) {
			return 0;
		}
		int height = 0;
		LinkedMultiTreeNode<T> nextNode = leftMostNode;
		while (nextNode != null) {
			height = Math.max(height, nextNode.height());
			nextNode = nextNode.rightSiblingNode;
		}
		return height + 1;
	}

	
	@Override
	public Collection<? extends MultiTreeNode<T>> siblings() {
		if (isRoot()) {
			String message = String.format("Unable to find the siblings. The tree node %1$s is root", root());
			throw new TreeNodeException(message);
		}
		LinkedMultiTreeNode<T> firstNode = ((LinkedMultiTreeNode<T>) parent()).leftMostNode;
		if (firstNode.rightSiblingNode == null) {
			return Collections.emptySet();
		}
		Collection<MultiTreeNode<T>> siblings = new LinkedHashSet<>();
		LinkedMultiTreeNode<T> nextNode = firstNode;
		while (nextNode != null) {
			if (!nextNode.equals(this)) {
				siblings.add(nextNode);
			}
			nextNode = nextNode.rightSiblingNode;
		}
		return siblings;
	}

}
