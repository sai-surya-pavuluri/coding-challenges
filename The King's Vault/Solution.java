import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your collection of artifacts separated by comma: ");
        String input = sc.nextLine();
        String[] artifacts = input.split(",");

        // Create the root node using the first artifact
        Node rootNode = new Node(artifacts[0].trim());
        
        // Insert the remaining artifacts into the BST
        for (int i = 1; i < artifacts.length; i++) {
            Node currentNode = new Node(artifacts[i].trim());
            rootNode.insert(currentNode); // Change to call insert on rootNode
        }

        System.out.println("Enter the artifact to search: ");
        String searchArtifact = sc.nextLine().trim(); // Trim whitespace from the search input
        System.out.println("Searching for artifact: " + searchArtifact);

        // Search for the artifact in the BST
        if (rootNode.search(searchArtifact)) {
            System.out.println("Artifact found: " + searchArtifact);
        } else {
            System.out.println("Artifact not found: " + searchArtifact);
        }

        sc.close(); // Close the scanner to avoid resource leaks
    }

    static class Node {
        private String artifact;
        private Node left, right;

        public Node(String artifact) {
            this.artifact = artifact;
            left = right = null;
        }

        // Method to insert a new node in the BST
        public void insert(Node currentNode) {
            if (currentNode.artifact.compareTo(this.artifact) < 0) {
                if (this.right == null) {
                    this.right = currentNode; // Insert as right child
                } else {
                    this.right.insert(currentNode); // Recur on right subtree
                }
            } else if (currentNode.artifact.compareTo(this.artifact) > 0) {
                if (this.left == null) {
                    this.left = currentNode; // Insert as left child
                } else {
                    this.left.insert(currentNode); // Recur on left subtree
                }
            } else {
                System.out.println("Duplicate artifact found: " + currentNode.artifact);
            }
        }

        // Search method to find an artifact in the BST
        public boolean search(String artifact) {
            if (artifact.equals(this.artifact)) {
                return true; // Found the artifact
            } else if (artifact.compareTo(this.artifact) < 0 && this.left != null) {
                return this.left.search(artifact); // Search in the left subtree
            } else if (artifact.compareTo(this.artifact) > 0 && this.right != null) {
                return this.right.search(artifact); // Search in the right subtree
            }
            return false; // Artifact not found
        }

        // Getters and Setters
        public Node getRight() {
            return this.right;
        }

        public Node getLeft() {
            return this.left;
        }

        public void setArtifact(String artifact) {
            this.artifact = artifact;
        }

        public void setRight(Node node) {
            this.right = node;
        }

        public void setLeft(Node node) {
            this.left = node;
        }

        public String getArtifact() {
            return this.artifact;
        }
    }
}