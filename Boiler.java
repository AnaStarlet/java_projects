package lab6;

import java.util.Scanner;

public class Boiler {
    public enum State {
        ON, OFF
    }

    public enum Type {
        Summer, Winter
    }

    private State state;
    private Type type;
    private double power;

    public Boiler(State state, Type type, double power) {
        this.state = state;
        this.type = type;
        this.power = power;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "Состояние: " + state + ", Тип: " + type + ", Мощность: " + power + " кВт";
    }

    public static Boiler set(Scanner scanner) {
        System.out.println("Настройка котла:");
        System.out.print("Введите состояние (ON/OFF): ");
        String stateInput = scanner.next().toUpperCase();
        State state = State.valueOf(stateInput);

        System.out.print("Введите тип (Summer/Winter): ");
        String typeInput = scanner.next();
        Type type = Type.valueOf(typeInput);

        System.out.print("Введите мощность котла (кВт): ");
        double power = scanner.nextDouble();

        return new Boiler(state, type, power);
    }
}