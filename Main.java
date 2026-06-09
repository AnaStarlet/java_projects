package lab6;

import java.util.Scanner;

/* Создать объект класса Батарея, используя классы Вода, Котёл.
Методы: греть, течь, остывать */

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Battery battery = Battery.set(scanner);

        while (true) {
            System.out.println("\nВыберите пункт меню:");
            System.out.println("0. Выход");
            System.out.println("1. Греть");
            System.out.println("2. Течь");
            System.out.println("3. Остыть");
            System.out.println("4. Вывести информацию о батарее");
            System.out.println("5. Изменить информацию о батарее");
            System.out.print(": ");

            int choice;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                System.out.println("Ошибка: введите целое число.");
                scanner.next();
                continue;
            }

            if (choice == 0) break;

            switch (choice) {
                case 1:
                    battery.heat(scanner);
                    break;
                case 2:
                    battery.leak();
                    break;
                case 3:
                    battery.cool(scanner);
                    break;
                case 4:
                    System.out.println(battery);
                    break;
                case 5:
                    battery = Battery.set(scanner);
                    System.out.println("Информация о батарее обновлена.");
                    break;
                default:
                    System.out.println("Выбран неправильный пункт меню, повторите ввод.");
            }
        }
        scanner.close();
        System.out.println("Программа завершена.");
    }
}