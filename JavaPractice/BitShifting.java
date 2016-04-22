/**
 * Created by Kevin on 4/11/16.
 */
import SamplePackage.Person;
public class BitShifting {
    public static void main(String[] args) {
        int bitMask = 0x000F;
        int val = 0x2222; //what does this mean? This is in hex
        System.out.println(val & bitMask);

        Person p = new Person();
        p.setName("kevin");
        System.out.println(p.getName());
    }
}
