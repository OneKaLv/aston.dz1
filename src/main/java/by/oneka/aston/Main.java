package by.oneka.aston;

import by.oneka.aston.list.ArrayList;
import by.oneka.aston.list.LinkedList;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        System.out.println("ArrayList");
        System.out.println("__________________________________");
        System.out.println(" ");

        ArrayList<String> list = new ArrayList<>();
        list.add("Sergei");
        list.add("Dmitry");
        list.add("Anatoliy");
        list.sort(Comparator.naturalOrder());
        System.out.println(list);
        System.out.println(list.getSize());
        list.remove(2);
        System.out.println(list);
        System.out.println(list.get("Anatoliy"));
        System.out.println(list.get(1));
        System.out.println(list.getSize());
        System.out.println(" ");
        System.out.println("__________________________________");

        System.out.println("LinkedList");
        System.out.println("__________________________________");
        System.out.println(" ");
        LinkedList<String> testLinked = new LinkedList<>();
        testLinked.addLast("Vanya");
        testLinked.addLast("Alina");
        testLinked.addLast("Kirill");
        testLinked.addLast("Baratrum");
        testLinked.addLast("Petya");
        System.out.println("list: " + testLinked);
        //TODO LinkedList add with index
        testLinked.addFirst("Alexey");
        System.out.println("list: " + testLinked);
        testLinked.sort(Comparator.naturalOrder());
        System.out.println(testLinked);
        System.out.println(testLinked);
        System.out.println(testLinked.add("Dima",3));
        System.out.println(testLinked);
        System.out.println(" ");
        System.out.println("__________________________________");
    }
}
