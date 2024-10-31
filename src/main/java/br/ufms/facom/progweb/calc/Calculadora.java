package br.ufms.facom.progweb.calc;

public class Calculadora {

    public static int calcular(int a, int b, String op) {
        int result = 0;
        switch (op) {
            case "somar":
                result = somar(a, b);
                break;
            case "subtrair":
                result = subtrair(a, b);
                break;
            case "multiplicar":
                result = multiplicar(a, b);
                break;

        }
        return result;

    }

    public static int somar(int a, int b) {
        return a + b;
    }

    public static int subtrair(int a, int b) {
        return a - b;
    }

    public static int multiplicar(int a, int b) {
        return a * b;
    }

    public static double dividir(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Divisor não pode ser zero.");
        }
        return (double) a / b;
    }
}