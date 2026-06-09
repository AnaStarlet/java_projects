package lab6;

import java.util.Scanner;

public class Water {
    private double waterLevel;
    private double temperature;
    private double pH;

    public Water(double waterLevel, double temperature, double pH) {
        this.waterLevel = waterLevel;
        this.temperature = temperature;
        this.pH = pH;
    }

    public double getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(double waterLevel) {
        this.waterLevel = waterLevel;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getpH() {
        return pH;
    }

    public void setpH(double pH) {
        this.pH = pH;
    }

    @Override
    public String toString() {
        return "Уровень воды: " + waterLevel + ", Температура: " + temperature + "°C, pH: " + pH;
    }

    public static Water set(Scanner scanner) {
        System.out.println("Настройка воды:");
        System.out.print("Введите уровень воды: ");
        double waterLevel = scanner.nextDouble();
        System.out.print("Введите температуру воды (°C): ");
        double temperature = scanner.nextDouble();
        System.out.print("Введите pH воды: ");
        double pH = scanner.nextDouble();
        return new Water(waterLevel, temperature, pH);
    }
}