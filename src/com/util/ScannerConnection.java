package com.util;

public class ScannerConnection {

    private static java.util.Scanner sc = null;

    static
    {
            sc = new java.util.Scanner(System.in);
    }
    public static java.util.Scanner getScanner()
    {
        return sc;
    }

    public static void closeScanner()
    {
        sc.close();
    }
}
