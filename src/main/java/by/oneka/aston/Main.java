package by.oneka.aston;

import by.oneka.aston.list.ArrayList;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();

        list.add("Сергей");
        list.add("Дмитрий");
        list.add("Анатолий");

        list.sort(Comparator.naturalOrder());
        System.out.println(list);

        System.out.println(list.getSize());
        list.remove(2);
        System.out.println(list);
        System.out.println(list.getSize());
    }
}
