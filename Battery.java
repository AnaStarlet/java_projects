package lab6;

import java.util.Scanner;

public class Battery {
    private Water water;
    private Boiler boiler;
    private String model;
    private double power;
    private String material;

    public Battery(Water water, Boiler boiler, String model, double power, String material) {
        this.water = water;
        this.boiler = boiler;
        this.model = model;
        this.power = power;
        this.material = material;
    }

    @Override
    public String toString() {
        return "Battery: " +
                "Модель: " + model + ", " +
                "Мощность: " + power + " кВт, " +
                "Материал: " + material + "; " +
                "\nWater: " + water.toString() + "; " +
                "\nBoiler: " + boiler.toString();
    }

    public static Battery set(Scanner scanner) {
        System.out.println("Определим состав компонентов системы батареи, включая котел и воду.");
        Water water = Water.set(scanner);
        Boiler boiler = Boiler.set(scanner);
        System.out.print("Введите модель батареи: ");
        String model = scanner.next();
        System.out.print("Введите мощность батареи (кВт): ");
        double power = scanner.nextDouble();
        System.out.print("Введите материал, из которого сделана батарея: ");
        String material = scanner.next();
        return new Battery(water, boiler, model, power, material);
    }

    public void heat(Scanner scanner) {
        if (water.getWaterLevel() <= 0) {
            System.out.println("Нет воды, котел не может работать.");
            return;
        }
        if (boiler.getState() == Boiler.State.OFF) {
            System.out.println("Котел не включен, нельзя нагреть воду.");
            return;
        }
        System.out.print("Введите желаемую температуру для увеличения (°C): ");
        double increase = scanner.nextDouble();
        if (increase < 0) {
            System.out.println("Температура не может быть уменьшена на отрицательное значение.");
            return;
        }
        double maxIncrease = 5;
        if (boiler.getType() == Boiler.Type.Summer && increase > maxIncrease) {
            System.out.println("Невозможно увеличить температуру на " + increase + "°C. Максимально допустимое увеличение: " + maxIncrease + "°C.");
            return;
        }
        double newTemperature = water.getTemperature() + increase;
        water.setTemperature(newTemperature);
        System.out.println("Температура воды увеличена на " + increase + "°C. Новая температура: " + newTemperature + "°C.");
    }

    public void leak() {
        double currentLevel = water.getWaterLevel();
        double pH = water.getpH();
        if (pH < 7) {
            System.out.println("Уровень pH низкий (кислый), что может привести к ускоренной коррозии.");
        } else if (pH > 7) {
            System.out.println("Уровень pH высокий (щелочной), что может привести к образованию отложений.");
        } else {
            System.out.println("Уровень pH нейтральный.");
        }
        if (currentLevel >= 10) {
            water.setWaterLevel(currentLevel - 10);
            System.out.println("Произошла течь: уровень воды уменьшен на 10.");
        } else {
            System.out.println("Уровень воды слишком низкий.");
        }
    }

    public void cool(Scanner scanner) {
        if (water.getWaterLevel() <= 0) {
            System.out.println("Нет воды, котел не может работать.");
            return;
        }
        if (boiler.getState() == Boiler.State.OFF) {
            System.out.println("Котел не включен, нельзя охладить воду.");
            return;
        }
        System.out.print("Введите желаемую температуру для уменьшения (°C): ");
        double decrease = scanner.nextDouble();
        if (decrease < 0) {
            System.out.println("Температура не может быть увеличена на отрицательное значение.");
            return;
        }
        double maxDecrease = 7;
        if (boiler.getType() == Boiler.Type.Winter && decrease > maxDecrease) {
            System.out.println("Невозможно уменьшить температуру на " + decrease + "°C. Максимально допустимое уменьшение: " + maxDecrease + "°C.");
            return;
        }
        double newTemperature = water.getTemperature() - decrease;
        if (newTemperature < 0) {
            System.out.println("Температура не может быть ниже 0°C. Установлено минимальное значение 0°C.");
            newTemperature = 0;
        }
        water.setTemperature(newTemperature);
        System.out.println("Температура воды уменьшена на " + decrease + "°C. Новая температура: " + newTemperature + "°C.");
    }
}