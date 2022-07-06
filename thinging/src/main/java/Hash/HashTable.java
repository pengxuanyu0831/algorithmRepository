package Hash;

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
}
