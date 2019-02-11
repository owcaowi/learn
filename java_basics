package com.example.aprzybylo.nfcapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static java.util.Arrays.sort;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // arrayMethods();
        // stringMethods();
        switchString("abc");
    }

    /********* String *************/

    private void stringMethods() {

        String someString = "Abc";
        String someString2 = new String("XYZ");
        String someString3 = new StringBuilder()
                .append("This ")
                .append("is ")
                .append("string")
                .toString();

        char[] charArray = {'a', 'b', 'c'};

        String stringFromChar = charToString(charArray);
        //return 0 for identical
        int isIdentical = someString.compareToIgnoreCase(stringFromChar);
        String concated = someString.concat(someString2);
        int size = concated.length();
        String replaced = concated.replace("A", "z");
        String[] splited = concated.split("c");

        System.out.printf("Ola %s \n", someString2);
    }

    private String charToString(char[] charArray) {
        return new String(charArray);
    }


    /********* Array *************/

    private void arrayMethods() {
        int[] someArray = new int[5];
        int[] someArray2 = {0, 1, 2, 5, 4, 3};
        int size = someArray.length;

        sort(someArray2);
        //boolean abc = equals(someArray, someArray2);

        printArray(new int[]{5, 4, 3, 2, 1, 0});
    }

    // Przekazanie jako argument
    private void printArray(int[] someArray) {
        for (int i : someArray) {
            System.out.printf("Ola %d \n", i);
        }
    }


    /********* Switch *************/

    private void switchString(String str) {

        switch (str) {
            case "abc":
                System.out.printf("Ola abc \n");
                break;

            case "xzy":
                System.out.printf("Ola xyz \n");
                break;
        }
    }

}
