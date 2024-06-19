import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение: ");
        String line = scanner.nextLine();
        System.out.println(calc(line));
    }
    public static String calc(String input) throws Exception {
        String[] parts = input.split(" ");
        // проверка на количество элементов в списке
        if (parts.length != 3) {
            throw new Exception("Ошибка ввода");
        }
        // проверка являются ли числами 0 и 2 элемент в списке
        boolean isdigit = isNumeric(parts);
        String operation = parts[1];
        int num1;
        int num2;

        if (isdigit) {
            try {
                num1 = Integer.parseInt(parts[0]);
                num2 = Integer.parseInt(parts[2]);
                switch (operation) {
                    case "+":
                        return String.valueOf(num1 + num2);
                    case "-":
                        return String.valueOf(num1 - num2);
                    case "/":
                        if (num2 == 0) {
                            throw new Exception("Нельзя делить на 0");
                        }
                        return String.valueOf(num1 / num2);
                    case "*":
                        return String.valueOf(num1 * num2);
                    default:
                        throw new Exception("Неверный ввод");
                }
            } catch (Exception e) {
                throw new InputMismatchException("Неверный формат данных");
            }
        } else {
            return grek(parts);
        }
    }

    private static String grek(String[] parts) throws Exception {
        String grek1 = parts[0];
        String operation = parts[1];
        String grek2 = parts[2];
        int count1 = -1;
        int count2 = -1;
        int result;
        String[] roman = {
                "O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
                "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
                "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
                "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };

        // Преобразование римских цифр в арабские
        for (int i = 0; i < roman.length; i++) {
            if (grek1.equals(roman[i])) {
                count1 = i;
            }
            if (grek2.equals(roman[i])) {
                count2 = i;
            }
        }

        if (count1 == -1 || count2 == -1) {
            throw new Exception("Ошибка ввода");
        }

        // Выполнение операции
        switch (operation) {
            case "+":
                result = count1 + count2;
                break;
            case "-":
                result = count1 - count2;
                break;
            case "*":
                result = count1 * count2;
                break;
            case "/":
                if (count2 == 0) {
                    throw new ArithmeticException("Деление на ноль");
                }
                result = count1 / count2;
                break;
            default:
                throw new Exception("Неизвестная операция");
        }

        // Проверка результата на выход за пределы массива римских цифр
        if (result < 0 || result >= roman.length) {
            throw new Exception("Результат выходит за допустимые пределы");
        }

        // Преобразование результата обратно в римскую цифру
        return roman[result];
    }

    public static boolean isNumeric(String[] parts) throws Exception {
        try {
            int num1 = Integer.parseInt(parts[0]);
            int num2 = Integer.parseInt(parts[2]);
            if (num1 <= 10 && num2 <= 10 && num1 >= 1 && num2 >= 1) {
                return true;
            } else {
                throw new Exception("Ошибка ввода числа");
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
