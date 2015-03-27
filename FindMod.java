public class FindMod {

	public static final int MAXRESULTS = 36;

	public static final int RANGE = 35;
	public static final int START = 0;
	public static final int STEP = 1;

	public static void main(String[] args) {
		System.out.println("\nFinding mod....\n");

		int[] answers = new int[MAXRESULTS];
		int currLoc = 0;
		for (int i = START; i <= RANGE; i += STEP) {
			if (FindMod.works(i)) {
				answers[currLoc++] = i;
				if (currLoc >= MAXRESULTS) {
					String msg = "Max results reached @ ";
					msg += i + "!";
					System.out.println(msg);
					break;
				}
			}
		}

		// Report the number of results
		System.out.println("Number of results: " + currLoc + ".");
		System.out.println();
		for (int i = 0; i < currLoc; i++) {
			System.out.print((i + 1) + ") " + answers[i]);
			System.out.println();
		}

		System.out.println("\nDONE!\n");
	}

	private static boolean works(int n) {
		if ((n * 4) % 12 != 6) {
			return false;
		}
		return true;
	}
}