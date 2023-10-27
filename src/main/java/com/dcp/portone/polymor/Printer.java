package com.dcp.portone.polymor;

import java.util.Scanner;

class main {
/*        dates.add(LocalDate.parse("2018-07-11"));
        dates.add(LocalDate.parse("1919-02-25"));
        dates.add(LocalDate.of(2020, 4, 8));
        dates.add(LocalDate.of(1980, Month.DECEMBER, 31));

        dates.removeIf(x -> x.getYear() < 2000);*/

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter Value");
            String type = scanner.nextLine();
            if ("Qq".contains(type)) {
                break;
            }
            System.out.println("Enter Title");
            String title = scanner.nextLine();

        }
    }

}
class Printer {
    private int tonerLevel;
    private int pagesPrinted;
    private boolean duplex;

    public Printer(int tonerLevel, boolean duplex) {
        this.pagesPrinted = 0;
        this.tonerLevel = (tonerLevel >= 0 && tonerLevel <= 100) ? tonerLevel : -1 ;
        this.duplex = duplex;
    }

    public int addToner(int tonerAmount) {
        int tempAmt = tonerLevel + tonerAmount;
        if (tempAmt > 100 || tempAmt < 0)
            return -1;
        tonerLevel += tonerAmount;
        return tonerLevel;
    }

    int printPages(int pages) {
        int jobPages = (duplex) ? (pages/2) + (pages % 2) : pages;
        pagesPrinted = jobPages;
        return jobPages;
    }

    public int getPagesPrinted() {
        return pagesPrinted;
    }
}
