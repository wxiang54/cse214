public class Complexity {

    /* 1a (Complexity Analysis - Part a)
     * pairSum() takes an array of int values and finds if there
       is a pair of numbers with a given sum.
       * PRECOND: none
       * POSTCOND: returns true if sum found, false otherwise
    */
    public static boolean pairSum(int[] arr, int sum) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == sum) {
                    System.out.println("Found pair: " +
                            arr[i] + " + " + arr[j] +
                            " = " + sum);
                    return true;
                }
            }
        }
        return false;
    }


    /* 1b (Complexity Analysis - Part b)
     * tripletSum() takes an array of int values and finds if there
       is a triplet of numbers with a given sum.
       * PRECOND: none
       * POSTCOND: returns true if sum found, false otherwise
    */
    public static boolean tripletSum(int[] arr, int sum) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                for (int k = i + 2; k < arr.length; k++) {
                    if (arr[i] + arr[j] + arr[k] == sum) {
                        System.out.println("Found triplet: " + arr[i] +
                                " + " + arr[j] + " + " +
                                arr[k] + " = " + sum);
                        return true;
                    }
                }
            }
        }
        return false;
    }


    // Helper method to print arrays
    public static String printArray(int[] arr) {
        String ret = "[";
        for (int i : arr)
            ret += i + ", ";
        return ret.substring(0, ret.length() - 2) + "]";
    }


    public static void main(String[] args) {
        //TESTING PAIRSUM()
        System.out.println("\nTesting pairSum()...");
        int[] arr = new int[]{11, 15, 6, 8, 9, 10, 11};
        int sum = 16;
        System.out.println("pairSum(" + printArray(arr) + ", " +
                sum + ") -> ");
        System.out.println(pairSum(arr, sum));

        sum = 27;
        System.out.println("pairSum(" + printArray(arr) + ", " +
                sum + ") -> ");
        System.out.println(pairSum(arr, sum));

        //TESTING TRIPLETSUM()
        System.out.println("\nTesting tripletSum()...");
        sum = 25;
        System.out.println("tripletSum(" + printArray(arr) + ", " +
                sum + ") -> ");
        System.out.println(tripletSum(arr, sum));

        sum = 39;
        System.out.println("tripletSum(" + printArray(arr) + ", " +
                sum + ") -> ");
        System.out.println(tripletSum(arr, sum));
    }
}