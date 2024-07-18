package org.example;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Statistic {
    private BigInteger countInteger;
    private BigDecimal countFloat;
    private int countString = 0;
    private BigInteger maxInteger;
    private BigInteger minInteger;
    private BigInteger sumInteger = new BigInteger("0");
    private BigDecimal minFloat;
    private BigDecimal maxFloat;
    private BigDecimal sumFloat = new BigDecimal("0");
    private int maxString = 0;
    private int minString = 0;

    public void statInteger(BigInteger integer) {
        countInteger = countInteger.add(new BigInteger("1"));

        if (maxInteger == null && minInteger == null) {
            maxInteger = integer;
        } else if (minInteger == null) {
            if (integer.compareTo(maxInteger) > 0) {
                minInteger = maxInteger;
                maxInteger = integer;
            } else {
                minInteger = integer;
            }
        }

        if (maxInteger != null && maxInteger.compareTo(integer) < 0) {
            maxInteger = integer;
        } else if (minInteger != null && minInteger.compareTo(integer) > 0) {
            minInteger = integer;
        }

        sumInteger = sumInteger.add(integer);
    }

    public void statFloat(BigDecimal aFloat) {
        countFloat = countFloat.add(new BigDecimal("1"));

        if (maxFloat == null && minFloat == null) {
            maxFloat = aFloat;
        } else if (minInteger == null) {
            if (aFloat.compareTo(maxFloat) > 0) {
                minFloat = maxFloat;
                maxFloat = aFloat;
            } else {
                minFloat = aFloat;
            }
        }

        if (maxFloat != null && maxFloat.compareTo(aFloat) < 0) {
            maxFloat = aFloat;
        } else if (minFloat != null && minFloat.compareTo(aFloat) > 0) {
            minFloat = aFloat;
        }

        sumFloat = sumFloat.add(aFloat);
    }

    public void statString(String str) {
        countString++;

        if (maxString < str.length()) {
            maxString = str.length();
        } else if (minString == 0) {
            minString = str.length();
        } else if (minString > str.length()) {
            minString = str.length();
        }
    }

    public void printShortStat() {
        System.out.println("Краткая статистика по записанным элементам:\n" +
                "    Кол-во целых чисел: " + countInteger + ";\n" +
                "    Кол-во дробных чисел: " + countFloat + ";\n" +
                "    Кол-во строк: " + countString + ".\n");
    }

    public void printFullStat() {

        BigInteger averageInteger = new BigInteger("0");
        BigDecimal averageFloat = new BigDecimal("0");

        if (countInteger != null) {
            averageInteger = sumInteger.divide(countInteger);
        }

        if (countFloat != null) {
            averageFloat = sumFloat.divide(countFloat, sumFloat.scale(), 1);
        }

        System.out.println("Полная статистика по записанным элементам:\n" +
                "    Кол-во целых чисел: " + countInteger + ".\n" +
                "        Минимальное целое число: " + minInteger + ";\n" +
                "        Максимально целое число: " + maxInteger + ";\n" +
                "        Сумма целых чисел: " + sumInteger + ";\n" +
                "        Среднее значение целых чисел: " + averageInteger + ".\n\n" +
                "    Кол-во дробных чисел: " + countFloat + ".\n" +
                "        Минимальное дробное число: " + minFloat + ";\n" +
                "        Максимально дробное число: " + maxFloat + ";\n" +
                "        Сумма дробных чисел: " + sumFloat + ";\n" +
                "        Среднее значение дробных чисел: " + averageFloat  + ";\n\n" +
                "    Кол-во строк: " + countString + ".\n" +
                "        Длина максимальной строки: " + maxString + ";\n" +
                "        Длина минимальной строки: " + minString + ".");
    }
}
