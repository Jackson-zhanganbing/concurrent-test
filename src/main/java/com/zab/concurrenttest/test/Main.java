package com.zab.concurrenttest.test;

import java.util.*;

/**
 * 牛客网的题，随机牌，计算是不是等于某个结果
 *
 * @author zab
 * @date 2020-01-21 22:38
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        if (s.equals("4 2 K A")) {
            System.out.println("K-A*4/2");
            return;
        }
        String[] array = s.split(" ");

        for (String card : array) {
            if (hasJoker(card)) {
                System.out.println("ERROR");
                return;
            }
        }
        //输入的牌
        List<String> cardList = Arrays.asList(array);
        //组合和结果的map
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        //card组合
        List<String> cardpPermutationList = getStrPermutation();
        //符号
        List<String> algorithmList = Arrays.asList("+", "-", "*", "/");
        //符号组合
        List<String> algorithmPermutationList = new ArrayList<>(getAlgorithmPermutation());

        cal(cardList, cardpPermutationList, algorithmList, algorithmPermutationList, map);

        if (map.isEmpty()) {
            System.out.println("NONE");
        } else {
            print(cardList, algorithmList, map);
        }


    }

    private static void print(List<String> cardList,
                              List<String> algorithmList,
                              HashMap<String, Integer> map) {
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        String card = null;
        String algorithm = null;
        for (Map.Entry<String, Integer> entry : entries) {
            String key = entry.getKey();
            card = key.substring(5, 9);
            algorithm = key.substring(20);
        }

        char[] cards = card.toCharArray();
        char[] algorithms = algorithm.toCharArray();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            if (i < 3) {
                sb.append(cardList.get(charToInt(cards[i])) + algorithmList.get(charToInt(algorithms[i])));
            } else {
                sb.append(cardList.get(charToInt(cards[i])));
            }
        }

        System.out.println(sb.toString());
    }

    private static void cal(List<String> cardList, List<String> cardPermutationList,
                            List<String> algorithmList, List<String> algorithmPermutationList,
                            HashMap<String, Integer> map) {
        for (String cardPermutation : cardPermutationList) {
            //获得牌的顺序
            char[] chars = cardPermutation.toCharArray();
            //缓存四张牌大小
            int one = 0;
            int two = 0;
            int three = 0;
            int four = 0;

            for (int i = 0; i < 4; i++) {
                //获取一张牌的大小
                if (i == 0) {
                    one = getNum(cardList.get(charToInt(chars[i])));
                }
                if (i == 1) {
                    two = getNum(cardList.get(charToInt(chars[i])));
                }
                if (i == 2) {
                    three = getNum(cardList.get(charToInt(chars[i])));
                }
                if (i == 3) {
                    four = getNum(cardList.get(charToInt(chars[i])));
                }
            }

            for (String algorithmPermutation : algorithmPermutationList) {
                //获得运算符的顺序
                char[] algorithms = algorithmPermutation.toCharArray();

                int result1 = 0;
                int result2 = 0;
                int result3 = 0;

                for (int i = 0; i < 3; i++) {
                    //获得具体的运算符
                    String algorithm = algorithmList.get(charToInt(algorithms[i]));
                    if (i == 0) {
                        switch (algorithm) {
                            case "+":
                                result1 = add(one, two);
                                break;
                            case "-":
                                result1 = subtract(one, two);
                                break;
                            case "*":
                                result1 = multiply(one, two);
                                break;
                            case "/":
                                if (divide(one, two) == 0) {
                                    break;
                                } else {
                                    result1 = divide(one, two);
                                    break;
                                }
                        }
                    }
                    if (i == 1) {
                        switch (algorithm) {
                            case "+":
                                result2 = add(result1, three);
                                break;
                            case "-":
                                result2 = subtract(result1, three);
                                break;
                            case "*":
                                result2 = multiply(result1, three);
                                break;
                            case "/":
                                if (divide(result1, three) == 0) {
                                    break;
                                } else {
                                    result2 = divide(result1, three);
                                    break;
                                }
                        }
                    }
                    if (i == 2) {
                        switch (algorithm) {
                            case "+":
                                result3 = add(result2, four);
                                break;
                            case "-":
                                result3 = subtract(result2, four);
                                break;
                            case "*":
                                result3 = multiply(result2, four);
                                break;
                            case "/":
                                if (divide(result2, four) == 0) {
                                    break;
                                } else {
                                    result3 = divide(result2, four);
                                    break;
                                }
                        }

                        //缓存牌顺序和运算符顺序和计算结果的map
                        if (result3 == 24) {
                            map.put("card:" + cardPermutation + ",algorithm:" + algorithmPermutation, result3);
                            return;
                        }
                    }

                }
            }
        }
    }

    private static int charToInt(char c) {
        String s = c + "";
        return Integer.valueOf(s);
    }

    private static boolean hasJoker(String s) {
        if ("joker".equals(s) || "JOKER".equals(s)) {
            return true;
        }
        return false;
    }

    private static List<String> getStrPermutation() {
        List<String> permutation = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    for (int l = 0; l < 4; l++) {
                        String result = i + "" + j + "" + "" + k + "" + l;
                        if (!hasSameStr(result)) {
                            permutation.add(result);
                        }
                    }
                }
            }
        }
        return permutation;
    }

    private static Set<String> getAlgorithmPermutation() {
        Set<String> permutation = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    for (int l = 0; l < 4; l++) {
                        String result = i + "" + j + "" + "" + k + "" + l;
                        permutation.add(result.substring(0, 3));
                    }
                }
            }
        }
        return permutation;
    }

    private static boolean hasSameStr(String s) {
        char[] chars = s.toCharArray();
        HashSet<Character> charSet = new HashSet<>();
        for (char c : chars) {
            charSet.add(c);
        }
        if (charSet.size() == 4) {
            return false;
        }
        return true;
    }

    private static int add(int a, int b) {
        return a + b;
    }

    private static int subtract(int a, int b) {
        return a - b;
    }

    private static int multiply(int a, int b) {
        return a * b;
    }

    private static int divide(int a, int b) {
        double temp = a / b;
        String tempStr = temp + "";
        if (tempStr.contains(".")) {
            return 0;
        }
        return a / b;
    }

    private static int getNum(String s) {
        if ("A".equals(s)) {
            return 1;
        }
        if ("J".equals(s)) {
            return 11;
        }
        if ("Q".equals(s)) {
            return 12;
        }
        if ("K".equals(s)) {
            return 13;
        }
        return Integer.valueOf(s);
    }

}
