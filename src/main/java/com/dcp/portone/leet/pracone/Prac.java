package com.dcp.portone.leet.pracone;

import java.util.*;
import java.util.stream.Collectors;

public class Prac {

    public static void main(String[] args) {
        // Longest Substring Without Repeating Characters
        // twoSumOne
        playWithStrings();

        System.out.println("-----".repeat(20));
        int[] arrts = new int[]{1, 7, 3, 8, 12, 15};
        int target = 19;
        int[] ar = twoSumOne(arrts, target);
        for (int i=0; i<ar.length; i++) {
            System.out.print("twoSumOne: index " + ar[i] + " Val: " + arrts[i] + "  " + Arrays.stream(ar).sum());
        }
        System.out.println();

        // twoSumTwo
        ar = twoSumTwo(arrts, target);
        for (int i: ar) {
            System.out.print("twoSumTwo: index " + i + " Val: " + arrts[i] + "   ");
        }
        System.out.println();

        // twoSumMap
        ar = twoSumTwo(arrts, target);
        for (int i: ar) {
            System.out.print("twoSumMap: index " + i + " Val: " + arrts[i] + "   ");
        }
        System.out.println();

        // twoSumScanner
        //IntSummaryStatistics araa = twoSumScanner(arrts, target);
        //System.out.println("araa = " + araa);

        System.out.println("ValidParenthesis: " + validParamStack("({{)}[]}"));
        int[] prices = {7,1,5,3,9,4};
        System.out.println("ValidParenthesis: " + maxProfit(prices));
        int[] rot = {1,3,4,5, 7, 9};

        System.out.println("colRotate bef: " + Arrays.toString(rot));
        System.out.println("colRotate aft: " + Arrays.toString(colRotate(rot, -1)));
        System.out.println("colRotate aft: " + colRotateTar(rot, -1, 17));

    }



    public static int[] colRotate(int[] lis, int i) {
        List<Integer> list = new ArrayList<>(Arrays.stream(lis).boxed().toList());
        Collections.rotate(list.subList(2,5),i);
        return list.stream().mapToInt(Integer::intValue).toArray();

    }

    public static int colRotateTar(int[] nums, int i, int target) {
        List<Integer> list = new ArrayList<>(Arrays.stream(nums).boxed().toList());
        Collections.rotate(list.subList(2,5),i);
        List<Integer> sl = new ArrayList<>(List.of(target));
        return Collections.indexOfSubList(list,sl);
    }

    public static int maxProfit(int[] price) {
        int leastBuysofar = Integer.MAX_VALUE;
        int overallProfit = 0;
        int profitIfSoldToday = 0;
        for (int i=0; i<price.length; i++) {
            if (leastBuysofar > price[i]) {
                leastBuysofar = price[i];
            }
            profitIfSoldToday = price[i] - leastBuysofar;

            if (overallProfit < profitIfSoldToday) {
                overallProfit = profitIfSoldToday;
            }
        }
        return overallProfit;
    }

    public static boolean validParamStack(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(') stack.push(')');
            else if (c == '[') stack.push(']');
            else if (c == '{') stack.push('}');
            else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }
        return stack.isEmpty();
    }
    public boolean validParen1(String input) {

        if (input.isEmpty()) {
            return true;
        } else {
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < input.length(); i++) {
                char current = input.charAt(i);
                if (current == '(' || current == '[' || current == '{') {
                    stack.push(current);
                } else {
                    if(stack.isEmpty()) {
                        return false;
                    }
                    char peekChar = stack.peek();
                    if ((current == ')' && peekChar != '(')
                            || (current == '}' && peekChar != '{')
                            || (current == ']' && peekChar != '[')) {
                        return false;  // for a valid input, a close brackets must have an open brackets
                    } else {
                        stack.pop();
                    }
                }
            }
            return true;
        }
    }

    public static int[] twoSumMap(int[] arrts, int target) {
        int[] ar = null;
        Map<Integer, Integer> tsMap = new HashMap<>(arrts.length);
        for (int i=0; i<arrts.length; i++) {
            int complement = target - arrts[i];
            if (tsMap.containsKey(complement)) {
                return ar = new int[]{tsMap.get(complement), i};
            } else {
                tsMap.put(arrts[i], i);
            }
        }
        return ar;
    }
    public static IntSummaryStatistics twoSumScanner(int[] arrts, int target) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter no of values");
        int n = scanner.nextInt();

        int[] arr = new int[n];
        System.out.println("Enter values");

        for (int i=0; i<n; i++) {
            arr[i] = scanner.nextInt();
        }

        scanner.close();
        return Arrays.stream(arr).summaryStatistics();
    }

    public static int[] twoSumTwo(int[] arrts, int target) {
        int[] ar = null;
        for (int i=0; i<arrts.length; i++) {
            for (int j=0; j<arrts.length; j++) {
                if (i<j) {
                    if(arrts[i] + arrts[j] == target) {
                        return ar = new int[]{i,j};
                    }
                }
            }
        }
        return ar;
    }
    public static int[] twoSumOne(int[] arrts, int target) {
        int[] arr = null;
        arrts = Arrays.stream(arrts).sorted().toArray();
        List<Integer> tsIntValList = new ArrayList<>(Arrays.stream(arrts).mapToObj(Integer::valueOf).collect(Collectors.toList()));
        List<Integer> tsBoxColList = new ArrayList<>(Arrays.stream(arrts).boxed().sorted().collect(Collectors.toList()));
        List<Integer> tsboxedList = new ArrayList<>(Arrays.stream(arrts).boxed().sorted().toList());
        List<Integer> tsIntForList = new ArrayList<>(arrts.length);
        for (int i: arrts) {
            tsIntForList.add(i);
        }
        Collections.addAll(tsIntForList, Arrays.stream(arrts).boxed().sorted().toArray(Integer[]::new));
        int left = 0;
        int right = arrts.length - 1;
        while(left < right){
            if (arrts[left] + arrts[right] == target) {
                System.out.println("right = " + target + " " + arrts[left] + " " + arrts[right] + " " + left + " " + right);
                arr = new int[]{left,right};
                return arr;
            } else if (arrts[left] + arrts[right] < target) {
                left++;
            } else  {
                right--;
            }
        }
        return arr;
    }

    public static void playWithStrings() {
        char[] charrr = { 'a','h', 'g', 'j', 'i', 'j', 'y', 'p'};

        String str = new String(charrr);

        System.out.println("str.indexOf(charrr[4]) = " + str.indexOf(charrr[3]) + " " + charrr[3] + " " +
                str.lastIndexOf(charrr[3]) + "  " + str);

        System.out.println("str.indexOf(charrr[4]) = " + str.replace(charrr[3], 's') + " " + charrr[3] + " " +
                str.lastIndexOf(charrr[3]) + "  " + str + " " + str.toCharArray());

        boolean b = true;
        System.out.printf("%20s", String.valueOf(2.2323F));
        System.out.println();

        // Class StringBuffer - A thread-safe, mutable sequence of characters -  - append (end) and insert (specified point)
        StringBuffer sbuff = new StringBuffer(str);

        System.out.println("sbuff = " + sbuff.capacity() + " " + sbuff.append(2.232374427594545945D) + " " + sbuff.capacity() );
        sbuff.append(2.232374427594545945D);
        sbuff.delete(5,16);
        sbuff.insert(20,b);
        System.out.println("sbuff = " + sbuff + "  " + str);
        sbuff.setLength(sbuff.length() - 10);
        System.out.println("sbuff = " + sbuff.reverse());

        // Class StringBuilder - A mutable sequence of characters Not thread safe but faster - append (end) and insert
        // implements Comparable

        StringBuilder sbuld = new StringBuilder(str);
        System.out.println("sbuld = " + sbuld.capacity() + " " + sbuld.append(2.232374427594545945D) + " " + sbuld.capacity() );
        sbuld.append(2.232374427594545945D);
        sbuld.delete(5,16);
        sbuld.insert(20,b);
        System.out.println("sbuld = " + sbuld + "  " + str);
        sbuld.setLength(sbuld.length() - 10);
        System.out.println("sbuld = " + sbuld.reverse());

        // EntrySet in Map

        HashMap<Character, Integer> intMap = new HashMap<>();
        for (int i=0; i<sbuld.length(); i++) {
            char c = (char) sbuld.charAt(i);
//            if(!intMap.containsKey(c)) {
            intMap.putIfAbsent((char) c,i);
//            }
        }
        System.out.println("intMap = " + intMap + "\n " + intMap.keySet());

        TreeMap<Character, Integer> intTreeMap = new TreeMap<>();
        TreeMap<Character, Integer> itMap = new TreeMap<>();
        for (int i=0; i<sbuld.length(); i++) {
            char c = (char) sbuld.charAt(i);
//            if(!intMap.containsKey(c)) {
            intTreeMap.putIfAbsent((char) c,i);
//            }
        }

        int[] chanrint = new int[str.length()];
        for (int i=0; i<str.length(); i++) {
            if (intTreeMap.containsKey(str.charAt(i))) {
                chanrint[i] = intTreeMap.get(str.charAt(i));
            }
        }

        System.out.println("intTreeMap = " + intTreeMap + "\n " + intTreeMap.keySet() + " " + str);
        for (int i : chanrint) {
            System.out.printf("%d ,", i);

        }
    }
}
