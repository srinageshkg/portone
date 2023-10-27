package com.dcp.portone.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordVowel {
    public void wordVowelCount() {
        List<String> t1 = new ArrayList<String>(Arrays.asList("sat", "bcd", "it"));
        int unReadRows = t1.size();
        int a = 0;
        while (unReadRows > 0) {
            int i = 1;
            boolean b = true;
            String c = t1.get(unReadRows - 1);
            while (i <= c.length()) {
                if (isVowel(c.charAt(i - 1))) {
                    b = false;
                }
                i = i + 1;
            }
            if (b) {
                a = a + 1;
            }
            unReadRows = unReadRows - 1;
        }
        System.out.println(a);
    }

    public static boolean isVowel(Character c) {
        return switch (c) {
            case 'a' -> true;
            case 'e' -> true;
            case 'i' -> true;
            case 'o' -> true;
            case 'u' -> true;
            default -> false;
        };
    }
}

