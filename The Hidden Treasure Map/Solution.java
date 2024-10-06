import java.util.*;

public class Solution {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Please provide all the paths seperated by comma: ");
            String input = sc.nextLine();
            String[] paths = input.split(",");
            
            TrieNode root = new TrieNode();
            for(int i = 0; i< paths.length; i++){
                insert(paths[i].trim(), root);
            }

            System.out.println("Enter the path you want to search: ");
            String path = sc.nextLine();
            System.out.println( "Treasure can" + (searchPath(path, root) ? " be" :" not be") + " along this path!" );

        }
        
    }
    
    public static void insert(String path, TrieNode node){
        TrieNode currentNode = node;
        for(int i= 0; i<path.length() ;i++){
             char c = path.charAt(i);
            if(currentNode.children.containsKey(c)){
               currentNode = currentNode.children.get(c); 
            } else{
                TrieNode newNode = new TrieNode();
                currentNode.children.put(c, newNode);
                currentNode = newNode;
            }

        }
        currentNode.isEndOfPath = true;
    }

    public static boolean searchPath(String path, TrieNode node){
        TrieNode currentNode = node;
        for(int i = 0; i < path.length(); i++ ){
            if(currentNode.children.containsKey(path.charAt(i))){
                currentNode = currentNode.children.get(path.charAt(i));
            } else {
                return false;
            }
        }
        return currentNode.isEndOfPath;
    }
}


class TrieNode {

    Map<Character, TrieNode> children = new HashMap<>();
    boolean isEndOfPath = false;

}


    


