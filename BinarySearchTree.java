import java.util.*;

class BinarySearchTree<T extends Comparable<T>> {
    
    private Node<T> root;
    public BinarySearchTree() {
        root = null;
    }

    // add your methods here
    public void add(T data) {
        root = add(data, root);
    }

    private Node<T> add(T data, Node<T> current) {
        if (current == null) {
            return new Node<T>(data);
        }

        if (data.compareTo(current.data) < 0) {
            current.left = add(data, current.left);
        }
        if (data.compareTo(current.data) > 0) {
            current.right = add(data, current.right);
        }
        return current;
    }

    // inOrder
    private String inOrder(Node<T> current) {
		if (current == null) {
			return "";
		}
		return inOrder(current.left) 
			 + current.data + ", " 
			 + inOrder(current.right);
	}

	// preOrder
	public String preOrder() {
		String str = preOrder(root);
		return "[" + str.substring(0, str.length() - 2) + "]";
	}

	private String preOrder(Node<T> current) {
		if (current == null) {
			return "";
		}
		return current.data + ", " 
			 + preOrder(current.left) 
			 + preOrder(current.right);
	}


	// Post-Order
	public String postOrder() {
		String str = postOrder(root);
		return "[" + str.substring(0, str.length() - 2) + "]";
	}

	private String postOrder(Node<T> current) {
		if (current == null) {
			return "";
		}
		return postOrder(current.left) 
			 + postOrder(current.right)
			 + current.data + ", ";
	}	

	/**
	* returns the number of levels from root.left
	* plus the number of levels from root.right
	* plus one.
	*/
	public int getWidth() {
		return getNumLevels(root.left) + getNumLevels(root.right) + 1;
	}

	/**
	* returns the number of leaves in the tree. A 
	* leaf is a node that does not have a child 
	* node(s).
	*/
	public int getNumLeaves() {
		return getNumLeaves(root);
	}

	private int getNumLeaves(Node<T> current) {
		if (current == null) {
			return 0;
		} else if (current.left == null && current.right == null) {
			return 1;
		}

		return getNumLeaves(current.left) + getNumLeaves(current.right);
	}
	
	/**
	* returns the number of levels in the tree
	*/
	public int getNumLevels() {
		return getNumLevels(root);
	}

	private int getNumLevels (Node<T> current) {
		if (current == null) {
			return 0;
		} else {
			int numLeft = getNumLevels(current.left);
			int numRight = getNumLevels(current.right);
			if (numLeft > numRight) {
				return 1 + numLeft;
			} else {
				return 1 + numRight;
			}
		}
	}

	/**
	* returns the height of the tree which is 
	* the number of levels in the tree less one.
	*/
	public int getHeight() { 
		return getNumLevels() - 1;
	}

	/** 
	* returns the number of nodes in the tree
	*/
	public int size() {
		return size(root) - 1;
	}

	private int size(Node<T> current) {
		if (current == null) {
			return 1;
		}

		return size(current.left) + size(current.right);
	}

    // method copy end

    // START METHOD
    /**
    * returns the lowest value in the tree according
    * to the classes natural ordering. Returns null 
    * if the tree is empty.
    */
    public T getLowest() {
        Node<T> result = getLowest(root);
        if (result == null) { return null; }
        return result.getValue();
    }
    private Node<T> getLowest(Node<T> current) {
        if (current == null) {
            return null;
        }

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    /**
    * returns the highest value in the tree according
    * to the classes natural ordering. Returns null 
    * if the tree is empty.
    */
    public T getHighest() {
        Node<T> result = getHighest(root);
        if (result == null) { return null; }
        return result.getValue();
    }
    private Node<T> getHighest(Node<T> current) {
        if (current == null) {
            return null;
        }

        while (current.right != null) {
            current = current.right;
        }

        return current;
    }

    /**
    * returns true if the number of levels to the
    * left and right of every parent node differs
    * by 1 or less. Otherwise, it returns false.
    */
    public boolean isBalanced() {
        return isBalanced(root);
    }
    private boolean isBalanced(Node<T> current) {
        if (current == null) {
			return true;
		}

        int numLeft = getNumLevels(current.left);
        int numRight = getNumLevels(current.right);
        
        if (Math.abs(numLeft - numRight) <= 1 && isBalanced(current.left) && isBalanced(current.right)) {
            return true;
        }

        return false;        
    }

    /**
    * returns a levelOrder representation of the
    * tree. You don't need a recursive algorithm 
    * if you implement a queue (FIFO) type
    * data structure for this.
    */
    public String levelOrder() {
        if (root == null) {
            return "";
        }

        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        String str = "";

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            str += current.getValue() + ", ";

            if (current.left != null) {
                queue.add(current.left);
            }

            if (current.right != null) {
                queue.add(current.right);
            }
        }

        return "[" + str.substring(0, str.length() - 2) + "]";
    }


	
    // END METHOD
    // BST Part 2 START

	// containVal
	public Node<T> containVal(String val) {
		return containVal(val, root);
	}

	private Node<T> containVal(String val, Node<T> current) {
		if (current == null){
			return null;
		} else if (val.compareTo(current.data.toString()) < 0) {
			return containVal(val, current.left);
		} else if (val.compareTo(current.data.toString()) > 0) {
			return containVal(val, current.right);
		} else {
			return current;
		}
	}

    /**
    * Rewrite your public and private inorder
    * methods so they use an ArrayList.
    */
    public String inOrder() {
        List<Node<T>> list = new ArrayList<>();
        inOrder(list, root);
        return list.toString();
    }
    
    /**
    * Overloaded inOrder helper method. Given a 
    * list, this methods adds nodes to that list 
    * inOrder. The method works like the method in
    * your previous assignments except it adds each 
    * Node to a list instead of a String.
    */
    private void inOrder(List<Node<T>> list, Node<T> current) {
        String str = inOrder(root);
        List<String> inOrderBefore = new ArrayList<String>(Arrays.asList(str.split(", ")));


        for (int i=0; i<inOrderBefore.size(); i++) {
            list.add(containVal(inOrderBefore.get(i)));
        }
    }
    
    /**
    * This is the public method that a user will 
    * call to rebalance your binary search tree. 
    * You will need to create an inorder list that
    * stores the nodes in your tree.
    * 1. Call the private inOrder method to get an
    *    in order list.
    * 2. Call reBuildTree and pass it the list 
    *    followed by the starting and ending index 
    *    of the list. The Node returned will be 
    *    the new Root of your balanced tree.
    */
    public void reBalanceTree() {
        List<Node<T>> list = new ArrayList<>();

        // call inOrder to get list
        inOrder(list, root);
        
        // call reBuildTree
		root = reBuildTree(list, 0, list.size()-1);
    }

    /**
    * Recursively rebuild a new Tree from the nodes
    * in the given inorder list. Given an inorder 
    * list of Nodes, implement a binary search over 
    * the given list except you will not be searching
    * for a value. You will be traversing the entire
    * list using a binary search.
    */
    private Node<T> reBuildTree(List<Node<T>> list, int leftIdx, int rightIdx) {
        // return null when leftIdx > rightIdx
        if (leftIdx > rightIdx) { return null; }

        // get the midPoint
        int midPoint = (leftIdx + rightIdx) / 2;

        // create Node and assign it the Node at the midpoint in the list
        Node current = list.get(midPoint);

        // assign current.left to the returned Node of reBuildTree(list, leftIdx, midPoint - 1)
        current.left = reBuildTree(list, leftIdx, midPoint - 1);

        // assign current.right to the returned Node of reBuildTree(list, midPoint + 1, rightIdx)
        current.right = reBuildTree(list, midPoint + 1, rightIdx);

        return current;
    }
    // BST Part 2 END
	// BST Part 4 START
	/**
	* Deletes the the node with the given data.
	*/
	public void delete(T data) {
		root = delete(root, data);
	}
	
	/**
	* See instructions below
	*/
	private Node<T> delete(Node<T> current, T data) {
		if (current == null) {
			return null;
		}

		// if val is less than current, traverse the left subtree
		if (data.compareTo(current.data) < 0) {
            System.out.println("MOVE LEFT");
			current.left = delete(current.left, data);
		}

		// if val is greater, traverse the right subtree instead
		else if (data.compareTo(current.data) > 0) {
            System.out.println("MOVE RIGHT");
			current.right = delete(current.right, data);
		}
		
		if (data.equals(current.data)) {
			// two children
			if (current.left != null && current.right != null) {
				Node<T> lowest = getLowest(current.right);
				current.data = lowest.data;
				// remove lowest
				Node<T> cur = current.right;
				if (cur.left == null) {
					current.data = cur.data;
					current.right = null;
					return current;
				}
				while (cur.left.left != null) {
					cur = cur.left;
				}
				cur.left = null;
				return current;
			}
			// one child
			if (current.left != null) {
				return current.left;
			} else if (current.right != null) {
				return current.right;
			}

			// no children
			if (current.left == null && current.right == null) {
				return null;
			}
		}
		return current;
	}

	// BST Part 4 END
    public String toString() {
        return inOrder();
    }

    public String printTree(int maxLevels) {
        return printTree(root, maxLevels);
    }

    public String printTree(Node<T> root, int maxLevels) {

        List<List<String>> allLevels = new ArrayList<>();
        List<Node<T>> currentLevel = new ArrayList<>();
        List<Node<T>> nextLevel = new ArrayList<>();

        currentLevel.add(root);
        int widestNode = 0;

        // build level order list of lists
        boolean hasNextLevel = true;
        while (hasNextLevel && allLevels.size() < maxLevels) {
            List<String> line = new ArrayList<>();

            hasNextLevel = false;
            for (Node<T> current : currentLevel) {
                if (current == null) {
                    line.add(null);
                    nextLevel.add(null);
                    nextLevel.add(null);
                } else {
                    line.add(current.toString());
                    if (current.toString().length() > widestNode) {
                        widestNode = current.toString().length();
                    }
                    nextLevel.add(current.left);
                    nextLevel.add(current.right);

                    if (current.left != null || current.right != null) {
                        hasNextLevel = true;
                    }
                }
            }
            allLevels.add(line);
            currentLevel = nextLevel;
            nextLevel = new ArrayList<>();
        }

        // build result string
        String result = "Binary Search Tree (Maximum of " + maxLevels + " levels shown.\n";

        // Set output line width using the last level, 
        widestNode += (widestNode % 2 == 1) ? 1 : 0;
        int sizeFactor = (allLevels.size() < 6) ? 4 : 2;
        int width = (int)Math.pow(2, allLevels.size() - 1) * (widestNode + sizeFactor);

        for (int i = 0; i < allLevels.size(); i++) {

            List<String> line = allLevels.get(i);
            int half = width / 2 - 1;
            String spaces = String.format("%" + half +"s", "");
            String dashes = String.format("%" + half +"s", "").replace(" ", "-");
            String lineOutput = "";
            String valueOutput = "";

            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {

                    // build line to add up arrow beneath parent nodes
                    lineOutput += (j % 2 == 1 && (line.get(j - 1) != null || 
                                                  line.get(j) != null)) ? "^" : " ";

                    // build line to add spaces, vertical bars, and lines to add to result string
                    if (line.get(j) == null) {
                        lineOutput += String.format("%" + (width - 1) + "s", "");
                    } else {
                        lineOutput += (j % 2 == 0) ? spaces + "|" + dashes : dashes + "|" + spaces;
                    }
                }
                result += lineOutput.replaceAll("\\s+$", "") + "\n";
            }

            // add node values to the result string
            for (String item : line) {
                int gap = (item == null) ? width / 2 : width / 2 - item.length() / 2;
                valueOutput += String.format("%" + gap +"S%s%" + gap + "S", 
                                             "", (item == null) ? "" : item, "");
            }
            result += valueOutput.replaceAll("\\s+$", "") + "\n";
            width /= 2;
        }
        return result;
    }




}
