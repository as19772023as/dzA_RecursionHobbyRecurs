
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        compare(1);
        compare(2);
        compare(5);
        compare(15);
    }

    public static void compare(int day) {
        System.out.println("=== Day " + day + " ===");
        int[] startNumbers = {21, 1, 20, 23};
        int iterative = chooseHobbyIterative(startNumbers, day);
        int recursive = chooseHobbyRecursive(startNumbers, day, new int[day]);
        System.out.println("Iterative = " + iterative + " | Recursive = " + recursive);
        System.out.println();
    }

    public static int chooseHobbyRecursive(int[] startNumbers, int day, int[] memory) {
        int memoryNamber;
        int day1 = startNumbers[3] * startNumbers[1] % 10 + 1;
        int day2 = day1 * startNumbers[2] % 10 + 1;

        if (day == 0) {
            return 0;
        }
        if (memory[day - 1] != 0) {
            return memory[day - 1];
        }
        if (day == 1) {
            memoryNamber = day1;
        } else if (day == 2) {
            memoryNamber = day2;
        } else if (day == 3) {
            memoryNamber = day2 * startNumbers[3] % 10 + 1;
        } else {

            System.out.println(">>> " + day);
            int prev = chooseHobbyRecursive(startNumbers, day - 1, memory);
            int prePrePrev = chooseHobbyRecursive(startNumbers, day - 3, memory);
            memoryNamber = (prev * prePrePrev) % 10 + 1;
        }
        if (day <= memory.length) {
            memory[day - 1] = memoryNamber;
        }
        return memory[day - 1];
    }

    public static int chooseHobbyIterative(int[] startNumbers, int day) {
        List<Integer> numbers = new ArrayList<>();

        numbers.add(startNumbers[0]);
        numbers.add(startNumbers[1]);
        numbers.add(startNumbers[2]);
        numbers.add(startNumbers[3]);

        for (int d = 0; d < day; d++) {
            int index = d + 4; // индексы дней в массиве сдвинуты на 4
            int prev = numbers.get(index - 1); // предыдущее значение
            int prePrePrev = numbers.get(index - 3); // пре-пре-предыдущее значение
            numbers.add((prev * prePrePrev) % 10 + 1);
        }
        return numbers.get(numbers.size() - 1);
    }
}

//TODO
//    public static int chooseHobbyRecursive(int[] startNumbers, int day, int[] memory) {
//        if (day <= memory.length && memory[day - 1] != 0) {
//            System.out.println(">>> " + day);
//            return memory[day - 1];
//        }
//        int result;
//        if (day == 1) {
//            result = startNumbers[startNumbers.length - 3] * startNumbers[startNumbers.length - 1] % 10 + 1;
//        } else if (day == 2) {
//            int day1 = chooseHobbyRecursive(startNumbers, 1, memory);
//            result = day1 * startNumbers[startNumbers.length - 2] % 10 + 1;
//        } else if (day == 3) {
//            int day2 = chooseHobbyRecursive(startNumbers, 2, memory);
//            result = day2 * startNumbers[startNumbers.length - 1] % 10 + 1;
//        } else {
//            int prev = chooseHobbyRecursive(startNumbers, day - 1, memory);
//            int prePrePrev = chooseHobbyRecursive(startNumbers, day - 3, memory);
//            result = (prev * prePrePrev) % 10 + 1;
//        }
//        if (day <= memory.length) {
//            memory[day - 1] = result;
//        }
//        return result;
//    }