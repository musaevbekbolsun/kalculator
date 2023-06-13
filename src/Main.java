import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String result = calc(input);
        System.out.println(result);
    }

    public static String calc(String input) {
        String[] tokens = input.split(" ");
        if (tokens.length != 3) {
            throw new IllegalArgumentException("Неправильный формат выражения");
        }

        String operand1 = tokens[0];
        String operator = tokens[1];
        String operand2 = tokens[2];

        if (!isNumeric(operand1) || !isNumeric(operand2)) {
            throw new IllegalArgumentException("Калькулятор может работать только с арабскими цифрами");
        }

        int num1 = Integer.parseInt(operand1);
        int num2 = Integer.parseInt(operand2);

        if (num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10) {
            throw new IllegalArgumentException("Калькулятор принимает числа от 1 до 10 включительно");
        }

        int result;
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
            default:
                throw new IllegalArgumentException("Неподдерживаемая арифметическая операция");
        }

        if (isRoman(operand1) && isRoman(operand2)) {
            return toRoman(result);
        } else if (!isRoman(operand1) && !isRoman(operand2)) {
            return Integer.toString(result);
        } else {
            throw new IllegalArgumentException("Калькулятор должен работать только с арабскими или римскими числами одновременно");
        }
    }

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isRoman(String str) {
        return str.matches("[IVX]+");
    }

    private static String toRoman(int number) {
        if (number < 1) {
            throw new IllegalArgumentException("Римские числа могут быть только положительными");
        }

        StringBuilder roman = new StringBuilder();
        Map<Integer, String> romanSymbols = new HashMap<>();
        romanSymbols.put(1000, "M");
        romanSymbols.put(900, "CM");
        romanSymbols.put(500, "D");
        romanSymbols.put(400, "CD");
        romanSymbols.put(100, "C");
        romanSymbols.put(90, "XC");
        romanSymbols.put(50, "L");
        romanSymbols.put(40, "XL");
        romanSymbols.put(10, "X");
        romanSymbols.put(9, "IX");
        romanSymbols.put(5, "V");
        romanSymbols.put(4, "IV");
        romanSymbols.put(1, "I");

        for (Map.Entry<Integer, String> entry                : romanSymbols.entrySet()) {
            int value = entry.getKey();
            String symbol = entry.getValue();
            while (number >= value) {
                roman.append(symbol);
                number -= value;
            }
        }

        return roman.toString();
    }
}

