import java.util.*;

/**
 * Created by kevint on 5/31/2016.
 */
public class ComparatorPractice {
    public static void main(String[] args) {
        List<Dog> list = new ArrayList<>();
        list.add(new Dog("Shaggy",3));
        list.add(new Dog("Lacy", 2));
        list.add(new Dog("Roger", 10));
        list.add(new Dog("Tommy", 4));
        list.add(new Dog("Tammy", 1));

        // Sorts by age. Defined the compareTo() for the
        // Comparable interface.
        System.out.println("Sort dog by age using Comparable: ");
        Collections.sort(list);
        for (Dog d : list) {
            System.out.print(d.getDogName() + ": " + d.getDogAge() + ", ");
        }
        System.out.println();
        System.out.println("Sort dog by age using Comparator: ");
        Collections.sort(list, (o1, o2) -> o1.getDogAge() - o2.getDogAge());
        for (Dog d : list) {
            System.out.print(d.getDogName() + ": " + d.getDogAge() + ", ");
        }
        System.out.println();
        System.out.println("Sort dog by name using Comparator (inline Override with lambda expressions): ");
        Collections.sort(list, (o1, o2) -> o1.getDogName().compareTo(o2.getDogName()));
        for (Dog d : list) {
            System.out.print(d.getDogName() + ": " + d.getDogAge() + ", ");
        }
        System.out.println();
        System.out.println();

        List<Cat> list1 = new ArrayList<>();
        list1.add(new Cat("D", 4));
        list1.add(new Cat("A", 1));
        list1.add(new Cat("B", 2));
        list1.add(new Cat("C", 100));
        list1.add(new Cat("E", 5));

        // Sorts by name. Defined the compareTo() for the
        // Comparable interface.
        System.out.println("Sort cat by name using Comparable: ");
        Collections.sort(list1);
        for (Cat c : list1) {
            System.out.print(c.getCatName() + ": " + c.getCatAge() + ", ");
        }
        System.out.println();

        // Sorts cat by age using a comparator
        System.out.println("Sort cat by age using Comparator: ");
        Collections.sort(list1, (o1, o2) -> o1.getCatAge() - o2.getCatAge());
        for (Cat c : list1) {
            System.out.print(c.getCatName() + ": " + c.getCatAge() + ", ");
        }
        System.out.println();

        System.out.println("Sort cat by name using Comparator (inline Overrride): ");
        Collections.sort(list1, new Comparator<Cat>() {
           @Override
           public int compare(Cat c1, Cat c2) {
               return c1.getCatName().compareTo(c2.getCatName());
           }
        });
        for (Cat c : list1) {
            System.out.print(c.getCatName() + ": " + c.getCatAge() + ", ");
        }
        System.out.println();
    }
}

class Dog implements Comparable<Dog> {
    private String name;
    private int age;

    public Dog(String n, int a) {
        name = n;
        age = a;
    }

    public String getDogName() {
        return name;
    }

    public int getDogAge() {
        return age;
    }

    public int compareTo(Dog d) {
        return this.age - d.age;
    }
}

class Cat implements Comparable<Cat> {
    private String name;
    private int age;

    public String getCatName() { return this.name; }

    public int getCatAge() { return this.age; }

    public Cat(String n, int a){
        this.name = n;
        this.age = a;
    }

    public int compareTo(Cat c) {
        return this.name.compareTo(c.name);
    }
}




