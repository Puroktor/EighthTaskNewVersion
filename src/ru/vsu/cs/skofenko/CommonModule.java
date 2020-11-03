package ru.vsu.cs.skofenko;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CommonModule {

    public static boolean isSequence(int[][] matrix) {
        boolean isIncreasing = true;
        boolean isDecreasing = true;
        int h = matrix.length;
        if(h==0)
            return true;
        int l = matrix[0].length;
        for (int j = 0; j < l; j++) {
            int c = getC(j);
            int till = getTill(h,j);
            for (int i = h - 1 - till; Math.abs(till - i) != 0; i += c) {
                if (matrix[i][j] > matrix[i + c][j])
                    isDecreasing = false;
                if (matrix[i][j] < matrix[i + c][j])
                    isIncreasing = false;
            }
            if (j != l - 1) {
                if (matrix[till][j] > matrix[till][j + 1])
                    isDecreasing = false;
                if (matrix[till][j] < matrix[till][j + 1])
                    isIncreasing = false;
            }

        }
        return isDecreasing || isIncreasing;
    }
    //раз уж обязательно разбивать на несколько функций
    private static int getC(int j){
        return (int) Math.pow(-1, j + 1);
    }

    private static int getTill(int h,int j){
        return (int) Math.pow(h, j % 2) - 1;
    }

    public static int[][] readMatrixFromFile(String path) throws FileNotFoundException,NullPointerException,NotRightMatrixException {
        try (Scanner scanner = new Scanner(new File(path))) {
            ArrayList<String> list = new ArrayList<String>();
            while (scanner.hasNext()) {
                list.add(scanner.nextLine());
            }
            int[][] mat = new int[list.size()][];
            int l=-1;
            for (int i = 0; i < list.size(); i++) {
                int[] temp=Arrays.stream(list.get(i).split(" ")).mapToInt(Integer::parseInt).toArray();
                if(temp.length!=l){
                    if(l==-1)
                        l= temp.length;
                    else
                        throw new NotRightMatrixException();
                }
                mat[i] = Arrays.stream(temp).toArray();
            }
            return mat;
        }
    }

    public static void writeAnswerToFile(String path, String answer) throws FileNotFoundException {
        try (PrintWriter pw = new PrintWriter(path)) {
            pw.println(answer);
        }
    }

    public static void writeMatrixToFile(String path, int[][] mat) throws FileNotFoundException,NullPointerException {
        try (PrintWriter pw = new PrintWriter(path)) {
            for (int i = 0; i < mat.length; i++) {
                StringBuilder stringBuilder = new StringBuilder();
                for(int j=0;j<mat[0].length;j++)
                {
                    stringBuilder.append(mat[i][j]);
                    stringBuilder.append(" ");
                }

                pw.println(stringBuilder.toString());
            }
        }
    }
}
