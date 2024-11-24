package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class App {
    private static final int OPERATION_COUNT = 20000;

    public static void main(String[] args) {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        System.out.println("Сравнение производительности ArrayList и LinkedList:");
        System.out.println("Метод | Коллекция | Количество операций | Время выполнения (мс)");

        measurePerformance("add", arrayList, linkedList);

        for (int i = 0; i < OPERATION_COUNT; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }

        measurePerformance("get", arrayList, linkedList);

        measurePerformance("delete", arrayList, linkedList);
    }

    private static void measurePerformance(String methodName, List<Integer> arrayList, List<Integer> linkedList) {
        long arrayListTime = measureMethodPerformance(arrayList, methodName);
        long linkedListTime = measureMethodPerformance(linkedList, methodName);

        // Вывод результатов
        System.out.printf("%s | %s | %d | %d%n", methodName, "ArrayList", OPERATION_COUNT, arrayListTime);
        System.out.printf("%s | %s | %d | %d%n", methodName, "LinkedList", OPERATION_COUNT, linkedListTime);
    }

    private static long measureMethodPerformance(List<Integer> list, String methodName) {
        long startTime = System.nanoTime();

        switch (methodName) {
            case "add":
                for (int i = 0; i < OPERATION_COUNT; i++) {
                    list.add(i);
                }
                break;

            case "get":
                for (int i = 0; i < OPERATION_COUNT; i++) {
                    list.get(i % list.size());
                }
                break;

            case "delete":
                for (int i = 0; i < OPERATION_COUNT; i++) {
                    list.remove(0);
                }
                break;
        }

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000;
    }
}
