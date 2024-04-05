package Hash;

import org.junit.Test;

import javax.management.StandardEmitterMBean;
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
                ArrayList<String> values = new ArrayList<>();
                values.add(s);

                map.put(String.valueOf(chars), values);
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
        // 滑动窗口 + 数组 维护数组内各个元素的数量，和p比较
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
            // 移动左指针，因为长度为length1
            int end = s.charAt(i - p.length()) - 'a';
            charsB[start]++;
            charsB[end]--;
            if (Arrays.equals(charsA, charsB)) {
                //起始索引，左指针被抛弃了，所以起始索引+1
                result.add(i - p.length() + 1);
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


    /**
     * #349
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int i : nums1) {
            set1.add(i);
        }

        for (int i : nums2) {
            if (set1.contains(i)) {
                set2.add(i);
            }
        }

        int[] result = new int[set2.size() + 1];
        for (int i :set2) {
            result[i++] = i;
        }
        return result;
    }


    /**
     * #350
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>(nums1.length);
        // 将 nums1 出现的数值及频次放入映射中
        for (int num : nums1) {
            Integer count = map.get(num);
            if (count == null) {
                map.put(num, 1);
            } else {
                map.put(num, ++count);
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            // 获取映射中该数值出现的频次
            Integer count = map.get(num);
            if (count != null && count != 0) {
                list.add(num);
                // 注意每次匹配后，该数值的频次需要减 1（nums1 和 nums2 匹配的数值的频次要相同）
                map.put(num, --count);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }


    /**
     * #202
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        Set<Integer> record = new HashSet<>();
        while (n != 1 && !record.contains(n)) {
            record.add(n);
            n = getNextNumber(n);
        }
        return n == 1;
    }

    private int getNextNumber(int n) {
        int res = 0;
        while (n > 0) {
            // 取余
            int temp = n % 10;
            res += temp * temp;
            // 求商
            n = n / 10;
        }
        return res;
    }


    /**
     * #1  噩梦开始的地方
     * @param nums
     * @param target
     * 双指针法
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int left = 0;
        int right = 1;
        int[] result = new int[2];
        while (right > left && right < nums.length) {
            if (nums[left] + nums[right] == target) {
                result[0] = left;
                result[1] = right;
                return result;
            } else {
                if (right == nums.length - 1) {
                    left++;
                    right = left + 1;
                } else {
                    right++;
                }
            }
        }
        return result;
    }
}
