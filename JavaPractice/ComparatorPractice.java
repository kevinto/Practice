import java.util.*;

/**
 * Created by kevint on 5/31/2016.
 */
public class ComparatorPractice {
    public static void main(String[] args) {
        List<Dog> list = new ArrayList<Dog>();
        list.add(new Dog("Shaggy",3));
        list.add(new Dog("Lacy", 2));
        list.add(new Dog("Roger", 10));
        list.add(new Dog("Tommy", 4));
        list.add(new Dog("Tammy", 1));

        // Sorts by natural ordering. Uses the defined compareTo()
        // for the comparable interface
//        Collections.sort(list);
//        for (Dog d : list) {
//            System.out.print(d.getDogName() + ", ");
//        }
//        System.out.println();

        // Sorts by age. Defined in the compare() for the
        // comparable interface
        Collections.sort(list, new Dog());
        for (Dog d : list) {
            System.out.print(d.getDogName() + ": " + d.getDogAge() + ", ");
        }
    }
}

//class Dog implements Comparator<Dog>, Comparable<Dog> {
class Dog implements Comparator<Dog> {
    private String name;
    private int age;

    Dog() {}

    Dog(String n, int a) {
        name = n;
        age = a;
    }

    public String getDogName() {
        return name;
    }

    public int getDogAge() {
        return age;
    }

//    public int compareTo(Dog d) {
//        return (this.name).compareTo(d.name);
//    }

    // Overrride for the comparator
    public int compare(Dog d1, Dog d2) {
        return d1.age - d2.age;
    }
}


