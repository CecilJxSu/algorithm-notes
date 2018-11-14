package chapter01;

public class GCD {

    public static void main(String[] args) {
        System.out.println(gcd(18, 48));
        System.out.println(gcd(48, 18));
    }

    /**
     * find two number's greatest common divisor.
     * @param p
     * @param q
     * @return  greatest common divisor
     */
    private static int gcd(int p, int q) {
        if (q == 0) {
            return p;
        }

        int remainder;
        while ((remainder = p % q) != 0) {
            p = q;
            q = remainder;
        }

        return q;
    }

    //    递归版本
    //    private static int gcd(int p, int q) {
    //        if (q == 0) return p;
    //        int remainder = p % q;
    //        return gcd(q, remainder);
    //    }
}
