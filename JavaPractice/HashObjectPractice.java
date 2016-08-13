import java.util.HashMap;

/**
 * Created by Kevin on 8/12/16.
 *
 * Why are we adding numbers to the hash calculation after summing
 * all the parts?
 *  In cases where we have property values that sum up to be the same.
 *  The formula ensures that these numbers are different.
 *      System.out.println("-1, -2, 3 -- Hash: " + calcHash(-1, -2, 3));
        System.out.println("-1, 2, -1 -- Hash: " + calcHash(-1, 2, -1));
    Notice that both these sum up to 0. The properties are different.
    So special hash codes are in order.
 */
public class HashObjectPractice {
    public static void main(String[] args) {
        HashMap<PhoneNumber, String> map = new HashMap<>();
        HashObjectPractice hp = new HashObjectPractice();
        map.put(hp.new PhoneNumber(408, 111, 1111), "Kevin");
        System.out.println(map.get(hp.new PhoneNumber(408, 111, 1111)));
        System.out.println("-1, -2, 3 -- Hash: " + calcHash(-1, -2, 3));
        System.out.println("-1, 2, -1 -- Hash: " + calcHash(-1, 2, -1));
    }

    public static int calcHash(int first, int second, int third) {
        int result = 17;
        result = 31 * result + first;
        result = 31 * result + second;
        result = 31 * result + third;
        return result;
    }

    public class PhoneNumber {
        private final int areaCode;
        private final int prefix;
        private final int lineNumber;

        PhoneNumber(int areaCode, int prefix, int lineNmber) {
            this.areaCode = areaCode;
            this.prefix = prefix;
            this.lineNumber = lineNmber;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PhoneNumber)) {
                return false;
            }

            PhoneNumber pn = (PhoneNumber)o;
            return pn.areaCode == areaCode
                    && pn.prefix == prefix
                    && pn.lineNumber == lineNumber;
        }

        @Override
        public int hashCode(){
            int result = 17;
            result = result * 31 + areaCode;
            result = result * 31 + prefix;
            result = result * 31 + lineNumber;
            return result;
        }
    }
}
