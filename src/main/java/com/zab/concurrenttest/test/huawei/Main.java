package com.zab.concurrenttest.test.huawei;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 华为机试
 *
 * @author zab
 * @date 2019-12-21 17:02
 */
public class Main {
    public static void main(String[] args) {

        int left = 300;

        Scanner scanner = new Scanner(System.in);
        List<Integer> bookPriceList = new ArrayList<>(16);
        List<Integer> preDaysList = new ArrayList<>(16);
        List<Integer> daysList = new ArrayList<>(16);

        while (scanner.hasNextLine()) {
            String record = scanner.nextLine();
            if ("".equals(record)) {
                break;
            }
            String[] priceAndDays = record.split(",");

            //记录所借图书信息
            for (int i = 0, len = priceAndDays.length; i < len; i++) {
                if (i == 0) {
                    bookPriceList.add(Integer.valueOf(priceAndDays[0]));
                }
                if (i == 1) {
                    preDaysList.add(Integer.valueOf(priceAndDays[1]));
                }
                if (i == 2) {
                    daysList.add(Integer.valueOf(priceAndDays[2]));
                }
            }
        }
        int totalPrice = cal(bookPriceList, preDaysList, daysList);

        //todo 判断单条记录，不满足的删除

        //余额小于借用天数的所花金额时，不允许借用
        if (left > totalPrice) {
            System.out.println(left - totalPrice);
        } else {
            //总花费金额高于卡内余额，不允许借用，直接输出余额
            System.out.println(left);
        }

    }

    /**
     * 传入价格，预借天数，实际借用天数,计算借书所花金额
     *
     * @param bookPriceList 图书价格列表
     * @param preDayList    预借天数列表
     * @param daysList      实际借书天数列表
     * @return int
     */
    private static int cal(List<Integer> bookPriceList, List<Integer> preDayList, List<Integer> daysList) {

        int sumPrice = 0;

        for (int i = 0, len = bookPriceList.size(); i < len; i++) {

            int totalPrice = 0;

            //取出图书价格列表的价格
            int bookPrice = bookPriceList.get(i);

            //取出当前图书借用的天数
            int days = daysList.get(i);

            //取出当前图书预借天数
            int preDays = preDayList.get(i);

            //计算正常借用图书的金额
            if (bookPrice >= 100) {

                if (days > 15) {
                    totalPrice += 15 * 5 + (days - preDays) * 3;
                } else {
                    totalPrice += days * 5;
                }

            } else if (bookPrice >= 50) {

                if (days > 15) {
                    totalPrice += 15 * 3 + (days - preDays) * 2;
                } else {
                    totalPrice += days * 3;
                }

            } else {

                totalPrice += days * 1;

            }

            //计算超期还书的金额

            if (days > preDays) {
                totalPrice += (days - preDays) * 1;
            }

            //不能超过图书本身的价格
            if (totalPrice > bookPrice) {
                totalPrice = bookPrice;
            }
            //统计多本书
            sumPrice += totalPrice;

        }


        return sumPrice;
    }

    private static int calSingle(Integer bookPrice, Integer preDays, Integer days) {

        int sumPrice = 0;


        int totalPrice = 0;

        //计算正常借用图书的金额
        if (bookPrice >= 100) {

            if (days > 15) {
                totalPrice += 15 * 5 + (days - preDays) * 3;
            } else {
                totalPrice += days * 5;
            }

        } else if (bookPrice >= 50) {

            if (days > 15) {
                totalPrice += 15 * 3 + (days - preDays) * 2;
            } else {
                totalPrice += days * 3;
            }

        } else {

            totalPrice += days * 1;

        }

        //计算超期还书的金额

        if (days > preDays) {
            totalPrice += (days - preDays) * 1;
        }

        //不能超过图书本身的价格
        if (totalPrice > bookPrice) {
            totalPrice = bookPrice;
        }
        //统计多本书
        sumPrice += totalPrice;


        return sumPrice;
    }
}
