package ru.geekbrains.lesson4;

import java.util.Random;
import java.util.Scanner;

public class Homework4 {
    private static char[][] map;
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    public static final int SIZE = 5;

    public static final char EMPTY_CELL = '*';
    public static final char PLAYER_CELL = 'X';
    public static final char AI_CELL = 'O';

    public static int xCoordinate = 0;
    public static int yCoordinate = 0;

    public static void main(String[] args) {
        createMap();
        printMap();
        while (true) {
            playersTurn();
            printMap();
            if (isWin('X')) {
                System.out.println("Победил игрок");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }

            aiTurn();
            printMap();
            if (isWin('O')) {
                System.out.println("Победил ИИ");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }

        }
        System.out.println("Игра окончена!");


    }

    public static void createMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = EMPTY_CELL;
            }
        }
    }

    public static void printMap() {
        System.out.print("  ");
        for (int i = 1; i < SIZE + 1; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[j][i] + " ");
            }
            System.out.println();
        }
    }

    public static void playersTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты хода Х и У:");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCoordinatesValid(x, y));
        xCoordinate = x;
        yCoordinate = y;
        map[x][y] = PLAYER_CELL;
        System.out.println("Ваш ход: X - " + (x + 1) + " Y - " + (y + 1));
    }

    public static boolean isCoordinatesValid(int x, int y) {
        return x >= 0 && x <= SIZE - 1 && y >= 0 && y <= SIZE - 1 && map[x][y] == EMPTY_CELL;
    }

    public static void aiTurn() {
        double lineWeight = 0;
        double columnWeight = 0;
        double diagonalWeight = 0;
        double reversedDiagonalWeight = 0;
        for (int i = 0; i < SIZE; i++) {
            if (map[xCoordinate][i] == PLAYER_CELL) {
                columnWeight++;
            }
            if (map[i][yCoordinate] == PLAYER_CELL) {
                lineWeight++;
            }
            if (map[i][i] == PLAYER_CELL) {
                diagonalWeight++;
            }
            if (map[i][SIZE - i - 1] == PLAYER_CELL) {
                reversedDiagonalWeight++;
            }
        }
        double smallDiagonalOne = 0;
        double smallDiagonalTwo = 0;
        double smallDiagonalThree = 0;
        double smallDiagonalFour = 0;
        for (int i = 0; i < SIZE - 1; i++) {
            if ((xCoordinate == yCoordinate + 1) && (map[i + 1][i] == PLAYER_CELL)) {
                smallDiagonalOne++;
            }
            if ((xCoordinate == yCoordinate - 1) && (map[i][i + 1] == PLAYER_CELL)) {
                smallDiagonalTwo++;
            }
            if ((xCoordinate == SIZE - yCoordinate - 2) && (map[SIZE - 2 - i][i] == PLAYER_CELL)) {
                smallDiagonalThree++;
            }
            if ((xCoordinate == SIZE - yCoordinate) && (map[SIZE - 1 - i][i + 1] == PLAYER_CELL)) {
                smallDiagonalFour++;
            }
        }

        int x = xCoordinate;
        int y = yCoordinate;
        char[] arrLine = new char[SIZE];
        char[] arrColumn = new char[SIZE];
        char[] arrDiagonal = new char[SIZE];
        char[] arrReversedDiagonal = new char[SIZE];
        char[] arrSmallOne = new char[SIZE - 1];
        char[] arrSmallTwo = new char[SIZE - 1];
        char[] arrSmallThree = new char[SIZE - 1];
        char[] arrSmallFour = new char[SIZE - 1];
        for (int i = 0; i < SIZE; i++) {
            arrLine[i] = map[i][yCoordinate];
            arrColumn[i] = map[xCoordinate][i];
            arrDiagonal[i] = map[i][i];
            arrReversedDiagonal[i] = map[i][SIZE - i - 1];
            if (i < SIZE - 1) {
                arrSmallOne[i] = map[i + 1][i];
                arrSmallTwo[i] = map[i][i + 1];
                arrSmallThree[i] = map[SIZE - 2 - i][i];
                arrSmallFour[i] = map[SIZE - i - 1][i + 1];
            }
        }

        int i = 0;

        do {
            while (i < SIZE) {
                if (isAnyPlace(arrSmallOne) && smallDiagonalOne > SIZE - 3) {
                    x = xCoordinate + i;
                    y = yCoordinate + i;
                    break;
                } else if (isAnyPlace(arrSmallTwo) && smallDiagonalTwo > SIZE - 3) {
                    x = xCoordinate + i;
                    y = yCoordinate + i;
                    break;
                } else if (isAnyPlace(arrSmallThree) && smallDiagonalThree > SIZE - 3) {
                    x = xCoordinate + i;
                    y = yCoordinate - i;
                    break;
                } else if (isAnyPlace(arrSmallFour) && smallDiagonalFour > SIZE - 3) {
                    x = xCoordinate - i;
                    y = yCoordinate + i;
                    break;
                }
                if (isAnyPlace(arrLine) && lineWeight > SIZE - 3) {
                    x = xCoordinate + i;
                    break;
                } else if (isAnyPlace(arrColumn) && columnWeight > SIZE - 3) {
                    y = yCoordinate + i;
                    break;
                } else if (isAnyPlace(arrDiagonal) && diagonalWeight > SIZE - 3) {
                    x = i;
                    y = i;
                    break;
                } else if (isAnyPlace(arrReversedDiagonal) && reversedDiagonalWeight > SIZE - 3) {
                    x = SIZE - 1 - i;
                    y = i;
                    break;
                }

                if (isAnyPlace(arrLine) && ((lineWeight > (columnWeight + diagonalWeight + reversedDiagonalWeight) / 3))) {
                    x = xCoordinate + i;
                    break;
                } else if (isAnyPlace(arrColumn) && ((columnWeight > (lineWeight + diagonalWeight + reversedDiagonalWeight) / 3))) {
                    y = yCoordinate + i;
                    break;
                } else if (isAnyPlace(arrDiagonal) && ((diagonalWeight > (lineWeight + columnWeight + reversedDiagonalWeight) / 3))) {
                    x = i;
                    y = i;
                    break;
                } else if (isAnyPlace(arrReversedDiagonal) && ((reversedDiagonalWeight > (lineWeight + columnWeight + diagonalWeight) / 3))) {
                    x = SIZE - 1 - i;
                    y = i;
                    break;
                } else {
                    x = random.nextInt(SIZE);
                    y = random.nextInt(SIZE);
                    break;
                }
            }
            i = i >= 0 ? i * (-1) - 1 : i * (-1);
        } while (!isCoordinatesValid(x, y));
        System.out.println("Ход ИИ: X - " + (x + 1) + " Y - " + (y + 1));
        map[x][y] = AI_CELL;
    }

    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == EMPTY_CELL) return false;
            }
        }
        return true;
    }

    public static boolean isWin(char c) {
        return isLineSame(c) || isColumnSame(c) || isDiagonalsSame(c);
    }

//        public static boolean isColumnSame(char c) {
//        int count = 0;
//        for (int i = 0; i < SIZE; i++) {
//            for (int j = 0; j < SIZE - 1; j++) {
//                if (map[i][j] == c && map[i][j] == map[i][j + 1]) {
//                    count++;
//                }
//                if (count == SIZE - 1) {
//                    return true;
//                }
//            }
//            count = 0;
//        }
//        return false;
//    }
//
//    public static boolean isLineSame(char c) {
//        int count = 0;
//        for (int i = 0; i < SIZE; i++) {
//            for (int j = 0; j < SIZE - 1; j++) {
//                if (map[j][i] == c && map[j][i] == map[j + 1][i]) {
//                    count++;
//                }
//                if (count == SIZE - 1) {
//                    return true;
//                }
//            }
//            count = 0;
//        }
//        return false;
//    }
//
//    public static boolean isDiagonalsSame(char c) {
//        int count = 0;
//        for (int i = 0; i < SIZE - 1; i++) {
//            if (map[i][i] == c && map[i][i] == map[i + 1][i + 1]) {
//                count++;
//            }
//            if (count == SIZE - 1) {
//                return true;
//            }
//
//        }
//        count = 0;
//        for (int i = 0; i < SIZE - 1; i++) {
//            if (map[i][SIZE - 1-i] == c && map[i][SIZE - 1-i] == map[i+1][SIZE - 2-i]){
//                count++;
//            }
//            if (count == SIZE - 1) {
//                return true;
//            }
//        }
//        return false;
//    }

    public static boolean isColumnSame(char c) {
        int count = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE - 2; j++) {
                if (map[i][j] == c && map[i][j] == map[i][j + 1]) {
                    count++;
                }
                if (count >= SIZE - 2) {
                    return true;
                }
            }
            count = 0;
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = 1; j < SIZE - 1; j++) {
                if (map[i][j] == c && map[i][j] == map[i][j + 1]) {
                    count++;
                }
                if (count >= SIZE - 2) {
                    return true;
                }
            }
            count = 0;
        }
        return false;
    }

    public static boolean isLineSame(char c) {
        int count = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE - 2; j++) {
                if (map[j][i] == c && map[j][i] == map[j + 1][i]) {
                    count++;
                }
                if (count >= SIZE - 2) {
                    return true;
                }
            }
            count = 0;
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = 1; j < SIZE - 1; j++) {
                if (map[j][i] == c && map[j][i] == map[j + 1][i]) {
                    count++;
                }
                if (count >= SIZE - 2) {
                    return true;
                }
            }
            count = 0;
        }
        return false;
    }

    public static boolean isDiagonalsSame(char c) {
        int count = 0;
        int smallCount = 0;
        for (int i = 0; i < SIZE - 2; i++) {
            if (map[i][i] == c && map[i][i] == map[i + 1][i + 1]) {
                count++;
            }
            if (map[i][i + 1] == c && map[i][i + 1] == map[i + 1][i + 2]) {
                smallCount++;
            }
            if (count >= SIZE - 2 || smallCount == SIZE - 2) {
                return true;
            }
        }
        count = 0;
        smallCount = 0;
        for (int i = 1; i < SIZE - 1; i++) {
            if (map[i][i] == c && map[i][i] == map[i + 1][i + 1]) {
                count++;
            }
            if (map[i][i - 1] == c && map[i][i - 1] == map[i + 1][i]) {
                smallCount++;
            }
            if (count >= SIZE - 2 || smallCount == SIZE - 2) {
                return true;
            }
        }
        count = 0;
        smallCount = 0;
        for (int i = 0; i < SIZE - 2; i++) {
            if (map[i][SIZE - 1 - i] == c && map[i][SIZE - 1 - i] == map[i + 1][SIZE - 2 - i]) {
                count++;
            }
            if (map[i][SIZE - 2 - i] == c && map[i][SIZE - 2 - i] == map[i + 1][SIZE - 3 - i]) {
                smallCount++;
            }
            if (count >= SIZE - 2 || smallCount == SIZE - 2) {
                return true;
            }
        }
        count = 0;
        smallCount = 0;
        for (int i = 1; i < SIZE - 1; i++) {
            if (map[i][SIZE - 1 - i] == c && map[i][SIZE - 1 - i] == map[i + 1][SIZE - 2 - i]) {
                count++;
            }
            if (map[i][SIZE - i] == c && map[i][SIZE - i] == map[i + 1][SIZE - 1 - i]) {
                smallCount++;
            }
            if (count >= SIZE - 2 || smallCount == SIZE - 2) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAnyPlace(char[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == EMPTY_CELL) return true;
        }
        return false;
    }
}
