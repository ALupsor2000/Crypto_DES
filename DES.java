package com.company;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class DES {

    //variables declaration section
    public static String[] cArray = new String[16];
    public static String[] dArray = new String[16];
    public static String[] keySet = new String[16];

    //constants declaration section
    public static int[] ip =  { 58, 50, 42, 34, 26, 18, 10, 2,
                                60, 52, 44, 36, 28, 20, 12, 4,
                                62, 54, 46, 38, 30, 22, 14, 6,
                                64, 56, 48, 40, 32, 24, 16, 8,
                                57, 49, 41, 33, 25, 17,  9, 1,
                                59, 51, 43, 35, 27, 19, 11, 3,
                                61, 53, 45, 37, 29, 21, 13, 5,
                                63, 55, 47, 39, 31, 23, 15, 7,
                                };

    public static int[] ip_ = { 40, 8, 48, 16, 56, 24, 64, 32,
                                39, 7, 47, 15, 55, 23, 63, 31,
                                38, 6, 46, 14, 54, 22, 62, 30,
                                37, 5, 45, 13, 53, 21, 61, 29,
                                36, 4, 44, 12, 52, 20, 60, 28,
                                35, 3, 43, 11, 51, 19, 59, 27,
                                34, 2, 42, 10, 50, 18, 58, 26,
                                33, 1, 41,  9, 49, 17, 57, 25
                                };

    public static int[] exp = { 32,  1,	 2,	 3,	 4,	 5,
                                 4,  5,	 6,	 7,	 8,	 9,
                                 8,  9,	10,	11,	12,	13,
                                12, 13,	14,	15,	16,	17,
                                16,	17,	18,	19,	20,	21,
                                20,	21,	22,	23,	24,	25,
                                24,	25,	26,	27,	28,	29,
                                28,	29,	30,	31,	32,	 1
                                };

    public static int[] p =   { 16,	 7,	20,	21,
                                29,	12,	28,	17,
                                 1,	15,	23,	26,
                                 5,	18,	31,	10,
                                 2,  8,	24,	14,
                                32,	27,	 3,	 9,
                                19,	13,	30,	 6,
                                22,	11,	 4,	25
                                };

    public static int[] pc1 = { 57, 49, 41, 33, 25, 17,  9,
                                 1, 58, 50, 42, 34, 26, 18,
                                10,  2, 59, 51, 43, 35, 27,
                                19, 11,  3, 60, 52, 44, 36,
                                63, 55, 47, 39, 31, 23, 15,
                                 7, 62, 54, 46, 38, 30, 22,
                                14,  6, 61, 53, 45, 37, 29,
                                21, 13,  5, 28, 20, 12,  4
                                };

    public static int[] pc2 = { 14, 17, 11, 24, 1, 5,
                                3, 28, 15, 6, 21, 10,
                                23, 19, 12, 4, 26, 8,
                                16, 7, 27, 20, 13, 2,
                                41, 52, 31, 37, 47, 55,
                                30, 40, 51, 45, 33, 48,
                                44, 49, 39, 56, 34, 53,
                                46, 42, 50, 36, 29, 32
                                };

    public static int[] leftShift = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};

                                                                //s1
    public static int[][] sBox = {   {14,  4, 13,  1,  2, 15, 11,  8,  3, 10,  6, 12,  5,  9,  0,  7},
                                    { 0, 15,  7,  4, 14,  2, 13,  1, 10,  6, 12, 11, 19,  5,  3,  8},
                                    { 4,  1, 14,  8, 13,  6,  2, 11, 15, 12,  9,  7,  3, 10,  5,  0},
                                    {15, 12,  8,  2,  4,  9,  1,  7,  5, 11,  3, 14, 10,  0,  6, 13},

                                                                //s2
                                    {15,  1,  8, 14,  6, 11,  3,  4,  9,  7,  2, 13, 12,  0,  5, 10},
                                    { 3, 13,  4,  7, 15,  2,  8, 14, 12,  0,  1, 10,  6,  9, 11,  5},
                                    { 0, 14,  7, 11, 10,  4, 13,  1,  5,  8, 12,  6,  9,  3,  2, 15},
                                    {13,  8, 10,  1,  3, 15,  4,  2, 11,  6,  7, 12,  0,  5, 14,  9},

                                                                //s3
                                    {10,  0,  9, 14,  6,  3, 15,  5,  1, 13, 12,  7, 11,  4,  2,  8},
                                    {13,  7,  0,  9,  3,  4,  6, 10,  2,  8,  5, 14, 12, 11, 15,  1},
                                    {13,  6,  4,  9,  8, 15,  3,  0, 11,  1,  2, 12,  5, 10, 14,  7},
                                    { 1, 10, 13,  0,  6,  9,  8,  7,  4, 15, 14,  3, 11,  5,  2, 12},

                                                                //s4
                                    { 7, 13, 14,  3,  0,  6,  9, 10,  1,  2,  8,  5, 11, 12,  4, 15},
                                    {13,  8, 11,  5,  6, 15,  0,  3,  4,  7,  2, 12,  1, 10, 14,  9},
                                    {10,  6,  9,  0, 12, 11,  7, 13, 15,  1,  3, 14,  5,  2,  8,  4},
                                    { 3, 15,  0,  6, 10,  1, 13,  8,  9,  4,  5, 11, 12,  7,  2, 14},

                                                                //s5
                                    { 2, 12,  4,  1,  7, 10, 11,  6,  8,  5,  3, 15, 13,  0, 14,  9},
                                    {14, 11,  2, 12,  4,  7, 13,  1,  5,  0, 15, 10,  3,  9,  8,  6},
                                    { 4,  2,  1, 11, 10, 13,  7,  8, 15,  9, 12,  5,  6,  3,  0, 14},
                                    {11,  8, 12,  7,  1, 14,  2, 13,  6, 15,  0,  9, 10,  4,  5,  3},

                                                                //s6
                                    {12,  1, 10, 15,  9,  2,  6,  8,  0, 13,  3,  4, 14,  7,  5, 11},
                                    {10, 15,  4,  2,  7, 12,  9,  5,  6,  1, 13, 14,  0, 11,  3,  8},
                                    { 9, 14, 15,  5,  2,  8, 12,  3,  7,  0,  4, 10,  1, 13, 11,  6},
                                    { 4,  3,  2, 12,  9,  5, 15, 10, 11, 14,  1,  7,  6,  0,  8, 13},

                                                                //s7
                                    { 4, 11,  2, 14, 15,  0,  8, 13,  3, 12,  9,  7,  5, 10,  6,  1},
                                    {13,  0, 11,  7,  4,  9,  1, 10, 14,  3,  5, 12,  2, 15,  8,  6},
                                    { 1,  4, 11, 13, 12,  3,  7, 14, 10, 15,  6,  8,  0,  5,  9,  2},
                                    { 6, 11, 13,  8,  1,  4, 10,  7,  9,  5,  0, 15, 14,  2,  3, 12},

                                                                //s8
                                    {13,  2,  8,  4,  6, 15, 11,  1, 10,  9,  3, 14,  5,  0, 12,  7},
                                    { 1, 15, 13,  8, 10,  3,  7,  4, 12,  5,  6, 11,  0, 14,  9,  2},
                                    { 7, 11,  4,  1,  9, 12, 14,  2,  0,  6, 10, 13, 15,  3,  5,  8},
                                    { 2,  1, 14,  7,  4, 10,  8, 13, 15, 12,  9,  0,  3,  5,  6, 11},
                                };

    //functionality section
    public static String convertHexToBinary(String hexText){

        HashMap<Character, String> binValues = new HashMap<>();

        binValues.put('0', "0000");
        binValues.put('1', "0001");
        binValues.put('2', "0010");
        binValues.put('3', "0011");
        binValues.put('4', "0100");
        binValues.put('5', "0101");
        binValues.put('6', "0110");
        binValues.put('7', "0111");
        binValues.put('8', "1000");
        binValues.put('9', "1001");
        binValues.put('A', "1010");
        binValues.put('B', "1011");
        binValues.put('C', "1100");
        binValues.put('D', "1101");
        binValues.put('E', "1110");
        binValues.put('F', "1111");

        String binaryText = "";
        hexText = hexText.replaceAll("\\s+","");
        char[] hexArray = hexText.toUpperCase().toCharArray();

        for (int i = 0; i < hexText.length(); i++) {

            if (binValues.containsKey(hexArray[i])) {
                binaryText += binValues.get(hexArray[i]);
            }
            else {
                binaryText = "Invalid Hexadecimal String";
                return binaryText;
            }
        }

        return binaryText;
    }



    //--------------------------------------------------
    // KEY        COMPUTING       SECTION
    //--------------------------------------------------

    //create 56-bit long key with pc1 permutation of the original key
    public static String permuteKey(String initKey){

        StringBuilder permutedKey = new StringBuilder();

        for (int i = 0; i < pc1.length; i++) {
            permutedKey.append(initKey.charAt(pc1[i] - 1));
        }

        return permutedKey.toString();
    }

    //the initial permutedKey K+ will be split in two half c and d
    //we iterate the leftShift array and shift each bit of c and d to the left
    //after each iteration we store the new shifted Strings into cArray and dArray
    public static void createHalfKeys(String permutedKey){

        //split permuted Key K+ in half
        char[] c = permutedKey.substring(0, (permutedKey.length() / 2)).toCharArray();
        char[] d = permutedKey.substring(permutedKey.length() / 2).toCharArray();

        String strC, strD;
        char auxC, auxD;
        for(int i = 0; i < leftShift.length; i++){

            strC = "";
            strD = "";

            //shifting each element of c and d with leftShift[i] position to the left
            for(int j = 0; j < leftShift[i]; j++){
                auxC = c[0];
                auxD = d[0];
                for(int l = 0; l < c.length - 1; l++){
                    c[l] = c[l + 1];
                    d[l] = d[l + 1];
                }
                c[c.length - 1] = auxC;
                d[d.length - 1] = auxD;
            }



            for(int j = 0; j < c.length; j++){
                strC += c[j];
                strD += d[j];
            }

            //add each ci di to the public array
            cArray[i] = strC;
            dArray[i] = strD;
        }
    }

    //concatenating each pair ci and di from cArray and dArray
    //after concatenation we permute according to the pc2
    //the result after permuting is a key wich is stored in keySet
    public static void computeFinalKeys(){

        String concatenated = "";
        StringBuilder finalKey = new StringBuilder();

        for(int i = 0; i < cArray.length; i++){
            concatenated += cArray[i] + dArray[i];

            for(int j = 0; j < pc2.length; j++) {
                finalKey.append(concatenated.charAt(pc2[j] - 1));
            }
            keySet[i] = finalKey.toString();
            concatenated = "";
            finalKey = new StringBuilder();
        }
    }



    //--------------------------------------------------
    // BLOCK        ENCODING      SECTION
    //--------------------------------------------------

    //first step we have to permute the given message according to ip
    public static String initialPermutation(String plaintext){

        StringBuilder firstPerm = new StringBuilder();

        for (int i = 0; i < ip.length; i++) {
            firstPerm.append(plaintext.charAt(ip[i] - 1));
        }

        return firstPerm.toString();
    }

    //expand the 32-bit r-String of bits according to expansion table
    public static char[] expansion(char[] lastR){

        char[] expansion = new char[48];

        for(int i = 0; i < exp.length; i++){
            expansion[i] = lastR[exp[i] - 1];
        }

        return expansion;
    }

    //XOR expanded r with the corresponding key
    public static char[] xor(char[] expansion, char[] key){

        char[] xor = new char[48];
        int val;
        for(int i = 0; i < expansion.length; i++){
            val = (expansion[i] - 48) ^ (key[i] - 48);
            xor[i] = (char) (val + 48);
        }

        return xor;
    }

    //substitute each group of 6 bits with the corresponding element from the substitution box
    public static char[] sub(char[] xor){

        char[] substitution = new char[32];

        StringBuilder m = new StringBuilder();
        StringBuilder n = new StringBuilder();
        int row, column;
        int sBoxNr = 0;     //S-Box number [1, 8]
        int substitutedNr;  //element from S-Box from row "row" and column "column"
        String binaryString = "";

        char[] bit6 = new char[6];
        int j = 0, l = 0;

        for(int i = 0; i < xor.length; i++){

            bit6[l] = xor[i];
            l++;
            if(l == 6){
                m.append(bit6[0]).append(bit6[5]);
                n.append(bit6[1]).append(bit6[2]).append(bit6[3]).append(bit6[4]);

                row = Integer.parseInt(m.toString(), 2);
                column = Integer.parseInt(n.toString(), 2);
                substitutedNr = sBox[row + sBoxNr][column];
                binaryString = Integer.toBinaryString(substitutedNr);

                while(binaryString.length() != 4){
                    binaryString = "0" + binaryString;
                }

                substitution[j] = binaryString.charAt(0);
                substitution[j+1] = binaryString.charAt(1);
                substitution[j+2] = binaryString.charAt(2);
                substitution[j+3] = binaryString.charAt(3);

                m = new StringBuilder();
                n = new StringBuilder();

                sBoxNr += 4;
                j += 4;
                l = 0;
            }
        }

        return substitution;
    }

    //function f
    //expand rn-1 => xor the expansion with the corresponding key => substitute the result from the xor
    //in the final step we permute the substituted result according to p
    public static char[] f(char[] r, String key){

        char[] expansionString = expansion(r);
        //System.out.println("exp: " + Arrays.toString(expansionString));
        char[] expXorKey = xor(expansionString, key.toCharArray());
        //System.out.println("xor: " + Arrays.toString(expXorKey));
        char[] substitutedString = sub(expXorKey);
        //System.out.println("sub: " + Arrays.toString(substitutedString));

        char[] permutation = new char[32];

        for(int i = 0; i < 32; i++){
            permutation[i] = substitutedString[p[i] - 1];
        }

        return permutation;
    }

    //compute each ln and rn where  1<=n<=16
    //the last pair l16 and r16 are reversed and concatenated into r16l16
    //the concatenated char Array is permuted according to ip_ (final permutation)
    //last step convert permuted char array into hex text ===> ciphertext
    //return ciphertext
    public static String encodeDataBlock(String firstPerm){

        //old Values
        char[] oldL = firstPerm.substring(0, (firstPerm.length() / 2)).toCharArray();
        char[] oldR = firstPerm.substring(firstPerm.length() / 2).toCharArray();

        System.out.println("L" + 0 + " " + Arrays.toString(oldL));
        System.out.println("R" + 0 + " " + Arrays.toString(oldR));

        //new Values
        char[] newL;
        char[] newR = new char[32];
        char[] funcF;

        for(int i = 0; i < 16; i++){

            newL = oldR;
            funcF = f(oldR, keySet[i]);

            int val;
            for(int j = 0; j < 32; j++){
                val = (oldL[j] - 48) ^ (funcF[j] - 48);
                newR[j] = (char) (val + 48);
            }

            for(int l = 0; l < newL.length; l++){
                oldL[l] = newL[l];
                oldR[l] = newR[l];
            }

            System.out.println("L" + (i + 1) + " " + Arrays.toString(oldL));
            System.out.println("R" + (i + 1) + " " + Arrays.toString(oldR));
        }

        char[] reversed = new char[64];
        for(int i = 0; i < newR.length; i++){
            reversed[i] = oldR[i];
            reversed[i+32] = oldL[i];
        }

        char[] finalPerm = new char[64];
        for(int i = 0; i < 64; i++){
            finalPerm[i] = reversed[ip_[i] - 1];
        }

        System.out.println("Final permutation: " + Arrays.toString(finalPerm));

        //convert the final permutation from binary into hex
        StringBuilder cipher = new StringBuilder();
        StringBuilder bit4 = new StringBuilder();
        int decimal;
        String hex;

        for(int i = 0; i < finalPerm.length; i++){
            if((i + 1) % 4 == 0){

                bit4.append(finalPerm[i]);
                decimal = Integer.parseInt(bit4.toString(), 2);
                hex = Integer.toString(decimal, 16);
                cipher.append(hex);
                bit4 = new StringBuilder();
            }
            else{
                bit4.append(finalPerm[i]);
            }
        }

        return cipher.toString();
    }

    public static void main(String[] args) {

        /*
            Assignment:
            key  = 3b3898371520f75e
            text = 8f 03 45 6d 3f 78 e2 c5

        */

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter key: ");
        String hexKey = sc.nextLine();
        System.out.println("Enter plaintext: ");
        String hexPlaintext = sc.nextLine();


        //convert the input from hex to binary
        String binKey = convertHexToBinary(hexKey);
        String binPlaintext = convertHexToBinary(hexPlaintext);

        //First step: create the 16 subkeys
        String permutedKey = permuteKey(binKey);
        createHalfKeys(permutedKey);
        computeFinalKeys();

        for(int i = 0; i < keySet.length; i++){
            System.out.println();
            System.out.print("K" + i + ": ");
            for(int j = 0; j < keySet[i].length(); j++){
                if((j + 1) % 6 == 0)
                    System.out.print(keySet[i].charAt(j) + " ");
                else
                    System.out.print(keySet[i].charAt(j));
            }
        }

        System.out.println("\n");

        //Second step: encode the 64-bit block
        String firstPermutation = initialPermutation(binPlaintext);
        String ciphertext = encodeDataBlock(firstPermutation);
        System.out.println(ciphertext);
    }
}