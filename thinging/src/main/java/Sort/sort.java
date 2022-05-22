package Sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @program algorithm
 * @description:
 * @author: pengxuanyu
 * @create: 2022/05/22 23:45
 */
public class sort {
    /**
     * #242
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        int[] record = new int[26];
        // 初始化
        for (int k = 0; k < record.length; k++) {
            record[k] = 0;
        }
        for (int j = 0; j < s.length(); j++) {
            record[s.charAt(j) - 'a'] += 1;
        }

        for (int l = 0; l < t.length(); l++) {
            record[t.charAt(l) - 'a'] -= 1;
        }

        for (int m = 0; m < record.length; m++) {
            if (record[m] != 0) {
                return false;
            }
        }
        return true;
    }

}
