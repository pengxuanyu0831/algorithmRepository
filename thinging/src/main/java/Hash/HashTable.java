package Hash;

import org.junit.Test;

import java.util.*;

/**
 * @program algorithm
 * @description:
 * @author: pengxuanyu
 * @create: 2022/07/06 23:25
 */
public class HashTable {


    /**
     * #383
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] chars = new int[26];
        for (char c : magazine.toCharArray()) {
            chars[c - 'a'] += 1;
        }

        for (char a : ransomNote.toCharArray()) {
            chars[a - 'a'] -= 1;
            if (chars[a - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * #49
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, ArrayList<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);

            if (map.containsKey(String.valueOf(chars))) {
                map.get(String.valueOf(chars)).add(s);
            } else {
                map.put(String.valueOf(chars), new ArrayList<>());
            }
        }
        return new ArrayList<>(map.values());
    }


    /**
     * #438
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if(s.length() < p.length()){
            return result;
        }
        char[] charsA = new char[26];
        char[] charsB = new char[26];
        for (int i = 0 ; i < p.length();i++) {
            charsA[p.charAt(i) - 'a']++;
            charsB[s.charAt(i) - 'a']++;
        }

        if (Arrays.equals(charsA,charsB)){
            result.add(0);
        }
        for (int i = p.length(); i < s.length(); i++) {
            int start = s.charAt(i) - 'a';
            int end = s.charAt(i - p.length()) - 'a';
            charsB[start]++;
            charsB[end]--;
            if (Arrays.equals(charsA, charsB)) {
                result.add(i-p.length()+1);
            }
        }
        return result;
    }


    /**
     * #204
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                if (map.get(s.charAt(i)) != t.charAt(i)) {
                    return false;
                }
            } else {
                if (map.containsValue(t.charAt(i))) {
                    return false;
                } else {
                    map.put(s.charAt(i), t.charAt(i));
                }
            }
        }
        return true;
    }


    /**
     * #392
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        int index = -1;
        for (char c : s.toCharArray()) {
            index = t.indexOf(c, index);
            if (index == -1) {
                return false;
            }
        }

        return true;
    }
}
