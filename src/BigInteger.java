class BigInteger implements Integer {
    // TODO define user specified fields
    // Warning 1: For Open and Close Principle, non-private fields are illegal.
    // Warning 2: References, arrays contain High-precision Numbers are illegal.
    //          For extension, all non-primitive or non-Number types are illegal.
    // Example for 1: public int len;(error) -> private int len;(correct)
    // Example for 2: java.math.BigInteger $233;(error) -> Number $233;(error) -> Object $233;(error)
    // Example for 2(extension): java.util.Vector $233;(error)
    // Example(primitive): int[] num, nn[];(correct)
    // Example(box primitive to Number): Integer[] num, nn[];(correct)

    private int[] num;

    /**
     * This constructor convert String to BigInteger.
     * @param s The input String, only contains character 0-9.
     */
    BigInteger(final String s) {
        // TODO implements this constructor
        if (s.length() == 0) {
            this.num = new int[1];
        } else {
            this.num = new int[s.length()];
            for (int i = s.length() - 1;i >= 0;i--) {
                this.num[s.length() - 1 - i] = s.charAt(i) - '0';
            }
        }
    }
    /**
     * Calculate the value of (this + val).
     * This method don't change input arguments.
     * @param val The value to add to this.
     * @return A new Integer whose value is (this + val).
     */
    @Override
    public Integer add(final Integer val) {
        final BigInteger v=(BigInteger) val;
        BigInteger result = new BigInteger("");
        // TODO let result <= this + v

        int maxLength = Math.max(this.num.length, v.num.length) + 1;
        int[] numA = new int[maxLength];
        for (int i = 0;i < this.num.length;i++) {
            numA[i] = this.num[i];
        }
        for (int i = 0;i < v.num.length;i++) {
            numA[i] += v.num[i];
        }
        for (int i = 0;i < maxLength - 1;i++) {
            numA[i+1] += numA[i] / 10;
            numA[i] %= 10;
        }

        int newLen = maxLength;
        for (int i = maxLength-1;i >= 0;i--) {
            if (numA[i] == 0) {
                newLen = i;
            } else {
                break;
            }
        }

        if (newLen == 0) {
            // 0
            newLen = 1;
        }

        result.num = new int[newLen];
        for (int i = 0;i < newLen;i++) {
            result.num[i] = numA[i];
        }
        return result;
    }
    /**
     * Convert this to String.
     * @return The String representation of this.
     */
    @Override
    public String toString() {
        final StringBuilder s=new StringBuilder();
        for (int i = this.num.length - 1;i >= 0;i--) {
            s.append((char)('0' + this.num[i]));
        }
        // TODO use s.append(...) to build the string
        return s.toString();
    }

    // TODO define user specified methods
}
