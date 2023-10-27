package com.dcp.portone.corejava.file;

public class Debugger {
    public static void main(String[] args) {
        StringUtilities utils = new StringUtilities();
        StringBuilder sb = new StringBuilder();
        while (sb.length() < 10) {
            utils.addChar(sb, 'a');
        }
        System.out.println(sb);
    }
}

class StringUtilities {
    private StringBuilder sBuilder = new StringBuilder();
    private int charsAdded = 0;

    public void addChar(StringBuilder sBuilder, char c) {
        sBuilder.append(c);
        charsAdded++;
    }

    // Returns a char array containing every nth char. When source array length < n , return sourceArray
    public char[] everyNthChar(char[] sourceArray, int n) {
        if (sourceArray == null || sourceArray.length < n) {
            return sourceArray;
        }
        int returnedLength = sourceArray.length/n;
        char[] result = new char[returnedLength];

        int index = 0;

        for (int i=n-1; i<sourceArray.length; i+=n) {
            result[index++] = sourceArray[i];
        }
        return result;
    }
    // Removes pairs of the same character that are next to each other by removing one occurred of the char
    // AABBCDEFF -> ABCDEF ; ABBABCCDEEFF -> ABABCDEF
    public String removePairs(String source) {
//        if (source == null) {
//            return null;
//        }
        if (source == null || source.length() < 2) {
            return source;
        }

        StringBuilder sBuilder = new StringBuilder();
        char[] string = source.toCharArray();

        for (int i=0; i<string.length-1; i++) {
            if (string[i] != string[i+1]) {
                sBuilder.append(string[i]);
            }
        }
        sBuilder.append(string[string.length-1]);
        //String.valueOf(sBuilder);
        return sBuilder.toString();
    }

    public int converter(int a, int b) {
        return (a/b) + (a*30) - 2;
    }

    public String nullIfOddLength(String source) {
        if(source.length() % 2 == 0) {
            return source;
        } else {
            return null;
        }
    }
}
