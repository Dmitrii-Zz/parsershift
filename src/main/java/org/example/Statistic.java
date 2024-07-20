package org.example;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class Statistic {
    private BigInteger countInteger;
    private BigInteger countFloat;
    private BigInteger countString;
    private BigInteger maxInteger;
    private BigInteger minInteger;
    private BigInteger sumInteger = new BigInteger("0");
    private BigDecimal minFloat;
    private BigDecimal maxFloat;
    private BigDecimal sumFloat = new BigDecimal("0");
    private long maxString = 0;
    private long minString = 0;

    public void statInteger(BigInteger integer) {
        if (countInteger == null) {
            countInteger = new BigInteger("0");
        }
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
        if (countFloat == null) {
            countFloat = new BigInteger("0");
        }

        countFloat = countFloat.add(new BigInteger("1"));

        if (maxFloat == null && minFloat == null) {
            maxFloat = aFloat;
        } else if (minFloat == null) {
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
        if (countString == null) {
            countString = new BigInteger("0");
        }
        countString = countString.add(new BigInteger("1"));

        if (maxString < str.length()) {
            maxString = str.length();
        } else if (minString == 0) {
            minString = str.length();
        } else if (minString > str.length()) {
            minString = str.length();
        }
    }

    public void printShortStat() {

        if (countInteger == null && countFloat == null && countString == null) {
            System.out.println("\nНе было записано ни одного элемента.");
        } else {
            System.out.println("\nКраткая статистика по записанным элементам:");
        }

        if (countInteger != null) {
            System.out.println("    Кол-во целых чисел: " + countInteger + ".");
        }

        if (countFloat != null) {
            System.out.println("    Кол-во дробных чисел: " + countFloat + ".");
        }

        if (countString != null) {
            System.out.println("    Кол-во строк: " + countString + ".");
        }
    }

    public void printFullStat() {

        if (countInteger == null && countFloat == null && countString == null) {
            System.out.println("\nНе было записано ни одного элемента.");
        } else {
            System.out.println("\nПолная статистика по записанным элементам:\n");
        }

        if (countInteger != null) {
            System.out.println("    Кол-во целых чисел: " + countInteger + ".\n" +
                               "        Максимально целое число: " + maxInteger + ".");

            if (minInteger != null) {
                System.out.println(
                        "        Минимальное целое число: " + minInteger + ".\n" +
                        "        Сумма целых чисел: " + sumInteger + ".\n" +
                        "        Среднее значение целых чисел: " + sumInteger.divide(countInteger) + ".");
            }
        }

        if (countFloat != null) {
            System.out.println(
                    "    Кол-во дробных чисел: " + countFloat + ".\n" +
                    "        Максимально дробное число: " + maxFloat + ".");

            if (minFloat != null) {
                System.out.println(
                        "        Минимальное дробное число: " + minFloat + ".\n" +
                        "        Сумма дробных чисел: " + sumFloat + ".\n" +
                        "        Среднее значение дробных чисел: "
                        + sumFloat.divide(new BigDecimal(countFloat), sumFloat.scale(), RoundingMode.DOWN)  + ".");
            }
        }

        if (countString != null) {
            System.out.println("    Кол-во строк: " + countString + ".\n" +
                               "        Длина максимальной строки: " + maxString + ".");
            if (minString != 0) {
                System.out.println("        Длина минимальной строки: " + minString + ".");
            }
        }
    }
}
