package ua.edu.ucu.tries;

import ua.edu.ucu.datastructures.Queue;

//TODO: everything

public class RWayTrie implements Trie {

    private static final int LETTERS_AMOUNT = 26;
    private TrieNode root;
    private int sizeOf;



    public RWayTrie() {
        root = new TrieNode('*');
        sizeOf = 0;
    }



    @Override
    public void add(Tuple t) {
        TrieNode current = root;
        String word = t.term;
        int weight = t.weight;
        for (int i = 0; i < weight; i++) {
            char curr_char = word.charAt(i);

            if (current.children[(int) curr_char-97] == null) {
                current.addChild(curr_char);
                current = current.children[(int) curr_char-97];
            }
            else {
                current = current.children[(int) curr_char-97];
            }
        }
        current.endOfWord = true;
    }



    @Override
    public boolean contains(String word) {
        TrieNode current = root;
        int weight = word.length();
        for (int i = 0; i < weight; i++) {
            char curr_char = word.charAt(i);

            if (current.children[(int) curr_char - 97] == null) {
                return false;
            }
            else {
                current = current.children[(int) curr_char - 97];
            }
        }
        if (current.endOfWord) {
            return true;
        }
        return false;
    }


    //Can fully delete
    //Can delete unique suffix
    //Can delete next char in same branch
    @Override
    public boolean delete(String word) {
        boolean present = contains(word);
        if (!present) {
            return false;
        }
        else {
            TrieNode current = root;
            TrieNode canDelete = null;
            int canCounter = 0;
            TrieNode end = null;
            int weight = word.length();

            for (int i = 0; i < weight; i++) {
                char curr_char = word.charAt(i);
                current = current.children[(int) curr_char - 97];
                if (current.childCount == 1) {
                    canDelete = current;
                }
            }
        }
        return true;
    }



    @Override
    public Iterable<String> words() {
        throw new UnsupportedOperationException("Not supported yet.");
    }



    @Override
    public Iterable<String> wordsWithPrefix(String pref) {
        throw new UnsupportedOperationException("Not supported yet.");
    }



    @Override
    public int size() {
        return sizeOf;
    }



    private static class TrieNode {

        int childCount = 0;
        char value;
        TrieNode[] children = new TrieNode[LETTERS_AMOUNT];
        boolean endOfWord;

        private TrieNode(char value) {
            this.value = value;
            endOfWord = false;
            for (int i = 0; i < LETTERS_AMOUNT; i++)
                children[i] = null;
        }

        private void addChild(char letter) {
            if (97<= (int) letter && (int) letter <= 122 && children[(int) letter -97] == null) {
                children[(int) letter - 97] = new TrieNode(letter);
                childCount++;
            }
        }
    }
}
