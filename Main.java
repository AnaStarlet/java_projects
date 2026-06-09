package lab4;

import java.util.ArrayList;
import java.util.Scanner;

/* Создать программу для поиска седловых точек в матрице целых чисел.
   Методы: ввод матрицы, вывод матрицы, поиск седловых точек */

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество строк матрицы: ");
        int rows = scanner.nextInt();
        System.out.print("Введите количество столбцов матрицы: ");
        int cols = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Введите элементы матрицы через пробел (например: 1 2 3 4 ...):");
        String inputLine = scanner.nextLine();
        String[] inputElements = inputLine.split(" ");

        if (inputElements.length != rows * cols) {
            System.out.println("Ошибка: количество введённых элементов не соответствует размеру матрицы.");
            return;
        }

        int[][] matrix = new int[rows][cols];
        int index = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = Integer.parseInt(inputElements[index]); // преобразование строки в число
                index++;
            }
        }

        System.out.println("Введённая матрица:");
        printMatrix(matrix);

        ArrayList<Integer> saddlePoints = findSaddlePoints(matrix);

        if (saddlePoints.isEmpty()) {
            System.out.println("Седловые точки не найдены.");
        } else {
            System.out.println("Седловые точки: " + saddlePoints);
        }

        scanner.close();
    }

    public static ArrayList<Integer> findSaddlePoints(int[][] matrix) {
        ArrayList<Integer> saddlePoints = new ArrayList<>();

        for (int i = 0; i < matrix.length; i++) {
            int rowMin = matrix[i][0];
            int colIndex = 0;

            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][j] < rowMin) {
                    rowMin = matrix[i][j];
                    colIndex = j;
                }
            }

            boolean isSaddlePoint = true;
            for (int k = 0; k < matrix.length; k++) {
                if (matrix[k][colIndex] > rowMin) {
                    isSaddlePoint = false;
                    break;
                }
            }

            if (isSaddlePoint) {
                saddlePoints.add(rowMin);
            }
        }

        return saddlePoints;
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
}