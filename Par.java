public class Par {

    private static final boolean TESTING = false;
    public static double parallel(double r1, double r2) {
        double mult = r1 * r2;
        double sum = r1 + r2;
        return mult / sum;
    }

    /** Given an array of resistor values, finds the equivalent resistor value
        of all of them in parallel. */
    private static double findPar(double[] nums) {
        double result = parallel(nums[0], nums[1]);
        for (int count = 2; count < nums.length; count++) {
            result = parallel(result, nums[count]);
        }
        return result;
    }

    /** Parse through the user input and convert all the values to doubles. */
    private static double[] parseNums(String[] args) {
        double[] nums = new double[args.length];
        for (int i = 0; i < args.length; i++) {
            String curr = args[i];
            try {
                nums[i] = Double.parseDouble(curr);
            } catch (NumberFormatException e) {
                System.out.println("\"" + curr + "\"" + " is not a valid int/double input!");
                System.exit(1);
            }
        }
        return nums;
    }

    public static final int NUM_DECIMALS = 3;

    /** Given a double number, pretties it up for printing purposes. */
    private static String roundDub(double number) {
        int decs = NUM_DECIMALS + 1;
        String in = "" + number;
        int indx = in.indexOf(".");
        if (indx < in.length() - decs) {
            in = in.substring(0, indx + decs);
        }
        return in;
    }

    public static void main(String[] args) {
        if (TESTING) {
            args = new String[] {"1000.0", "500"};
        }
        int len = args.length;
        if (len == 0) {
            System.out.println("Need to give args!");
            return;
        }
        double[] nums = parseNums(args);
        double result = findPar(nums);
        System.out.println(roundDub(result));
    }
}