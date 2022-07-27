package Ordinary;

import org.apache.logging.log4j.util.Strings;

import java.util.*;

/**
 * @program algorithm
 * @description:
 * @author: pengxuanyu
 * @create: 2022/04/12 22:04
 */
public class Ordinary {

    /**
     * #806
     * @param widths
     * @param s
     * @return 这勾八题目都看不懂的真的傻逼
     */
    public int[] numberOfLines(int[] widths, String s) {
        char[] chars = s.toCharArray();
        int target = 0;
        int row = 1;
        for (int i = 0; i < chars.length; i++) {
            // char[i] - 'a' -->>>等价于减去字母a的ASCII码 97
            target += widths[chars[i] - 'a'];
            if (target > 100) {
                row++;
                target = widths[chars[i] - 'a'];
            }
        }
        return new int[]{row, target};
    }


    /**
     * #804
     * @param words
     * @return
     */
    public int uniqueMorseRepresentations(String[] words) {
        String[] mos = new String[]{".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        String[] abc = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        Set<String> result = new HashSet<>();
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < mos.length; i++) {
            map.put(abc[i], mos[i]);
        }
        for (String s : words) {
            char[] c = s.toCharArray();
            StringBuffer sb = new StringBuffer();
            for (int j = 0; j < c.length; j++) {
                System.out.println(c[j] + "");
                String mosStr = map.get(c[j]+"");
                sb.append(mosStr);
                System.out.println(s + "=" + sb);
            }
            result.add(sb.toString());
        }
        return result.size();
    }


    /**
     * 每日一题  #380
     * HashSet 空间复杂度太高，排名太后
     */
    private static class RandomizedSet{

        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        public RandomizedSet() {
        }

        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            } else {
                list.add(val);
                map.put(val, list.size() - 1);

                return true;
            }
        }

        public boolean remove(int val) {
            if (map.containsKey(val)) {
                int index = map.get(val);
                int last = list.get(list.size() - 1);
                list.set(index, last);
                list.remove(list.size() - 1);
                map.put(last, index);
                map.remove(val);
                return true;
            } else {
                return false;
            }
        }

        public int getRandom() {
            return list.get(random.nextInt(list.size() - 1));
        }

    }


    /**
     * 278
     * @param 
     * @return
     */

    boolean isBadVersion(int version){
        return true;
    }

    public int firstBadVersion(int n) {
        int i = 1;
        while (i < n) {
            int mid = i + ((n - i) / 2);
            // 如果第mid个版本为错误版本，要继续往前找
            if (this.isBadVersion(mid)) {
                n = mid;
            } else {
                i = mid + 1;
            }
        }
        return n;
    }


    /**
     * #344
     * @param s
     */
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            //a ^ b ^ b = a ^ 0 = a;
            s[left] ^= s[right];
            s[right] ^= s[left];
            s[left] ^= s[right];
            left++;
            right--;
        }
    }


    /**
     * #541
     * @param s
     * @param k
     * @return
     */
    public String reverseStr(String s, int k) {
        StringBuffer result = new StringBuffer();
        int start = 0;
        while (start < s.length()) {
            StringBuffer tempStr = new StringBuffer();
            // 先处理额外情况
            int firstK = (start + k) > s.length() ? s.length() : start + k;
            int secondK = (start + k + k ) > s.length() ? s.length() : start + k + k;

            // 无论k是多少，start至少翻转一次
            tempStr.append(s.substring(start, firstK));
            result.append(tempStr.reverse());

            if (firstK < secondK) {
                result.append(s.substring(firstK, secondK));
            }
            start += (2 * k);
        }
        return result.toString();
    }
}
