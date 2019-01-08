import java.util.Scanner;

public class GameApp {
	/**
	 * Scanner instance for reading input from console
	 */
	private static final Scanner STDIN = new Scanner(System.in);
	static Game game;

	static int seed;
	static int timeToPlay;

	/**
	 * Constructor for instantiating game class
	 * 
	 * @param seed:
	 *            Seed value as processed in command line
	 * @param timeToPlay:
	 *            Total time to play from command line
	 */
	public GameApp(int seed, int timeToPlay) {
		// Create a new instance of Game class
		game = new Game(seed, timeToPlay);
		GameApp.seed = seed;
		GameApp.timeToPlay = timeToPlay;

	}

	/**
	 * Main function which takes the command line arguments and instantiate the
	 * GameApp class. The main function terminates when the game ends. Use the
	 * getIntegerInput function to read inputs from console
	 *
	 * @param args:
	 *            Command line arguments <seed> <timeToPlay>
	 */
	public static void main(String[] args) {
		validateMainArgs(args); // probably move this back in here
		System.out.println("Welcome to the Job Market!");
		GameApp gameApp = new GameApp(seed, timeToPlay);
		gameApp.start();
	}

	/**
	 *Contains game structure and prompts 
	 */
	private void start() { 
		// The interactive game logic goes here
		int jobNum;
		int timeToWork;
		Job jobWorked;
		int insertionIndex;
		do {
			// print the remaining time
			System.out.println(
					"You have " + game.getTimeToPlay() + " left in the game!");
			game.createJobs(); // if condition first time than create new jobs

			game.displayActiveJobs(); // show the user what jobs they can pick

			// use the getIntegerInput to update a Job with the user's picked
			// index and
			// duration to work on the job
			jobNum = getJobIndex(); // prompt for job
			// System.out.println("Penalty is: " + insertionIndex);

			// index time penalty is updated elsewhere
			timeToWork = getTimeToWork(); // prompt for time to work
			jobWorked = game.updateJob(jobNum, timeToWork);

			// System.out.println(jobWorked.isCompleted());

			// handle if the job is not completed
			if (!jobWorked.isCompleted()) {
				insertionIndex = getInsertionIndex();
				game.addJob(insertionIndex, jobWorked);
			} else { // if the job was completed successfully...
				System.out.println("Job completed! Current Score: "
						+ game.getTotalScore());
				System.out.println("Total Score = " + game.getTotalScore());
				game.displayCompletedJobs();
			}
		} while (!game.isOver());

		///////////////////// WHEN THE GAME IS OVER ////////////////////
		System.out.println("Game Over!");
		System.out.println("Total Score: " + game.getTotalScore());
	}

	/**
	 * Displays the prompt and returns the integer entered by the user to the
	 * standard input stream.
	 *
	 * Does not return until the user enters an integer. Does not check the
	 * integer value in any way.
	 * 
	 * @param prompt
	 *            The user prompt to display before waiting for this integer.
	 */
	public static int getIntegerInput(String prompt) {
		System.out.print(prompt);
		while (!STDIN.hasNextInt()) {
			System.out.print(
					STDIN.next() + " is not an int.  Please enter an integer.");
		}
		return STDIN.nextInt();
	}
	
	/**
	 * Validates arguments passed into main 
	 *
	 *@throws IllegalArugmentException if number of arguments is not 
	 * equal to 2 or if seed or time to play are less than zero
	 * 
	 * @param args
	 *           Command line argumenst <seed><timeToPlay>
	 */
	private static void validateMainArgs(String[] args) {
		if (args.length != 2) {
			throw new IllegalArgumentException();
		}
		try {
			seed = Integer.parseInt(args[0]);
			timeToPlay = Integer.parseInt(args[1]);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException();
		}
		if (seed < 0 || timeToPlay < 0) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 *If user does not fully complete job, gets index from user to insert job 
	 * back into job list 
	 *
	 * 
	 * @return
	 *           insertionIndex if inputValid is not false 
	 */
	private int getInsertionIndex() {
		int insertionIndex;
		boolean inputValid = false;
		do {
			insertionIndex = getIntegerInput(
					"At what position would you like to insert the job back into the list?\n");
			if (insertionIndex < 0) {
				System.out.println("ERROR. Please choose a valid index.");
			} else {
				inputValid = true;
			}
			if (insertionIndex >= game.getNumberOfJobs()) {
				insertionIndex = game.getNumberOfJobs();
			}
		} while (!inputValid);
		return insertionIndex;
	}
	
	/**
	 *Get number of displayed job to work on  
	 * if user input is greater than zero and less than or equal
	 * to the number of jobs in list.
	 *
	 * 
	 * @return
	 *           jobNum if inputValid is not false 
	 */
	private int getJobIndex() {
		int jobNum;
		boolean inputValid = false;
		do {
			System.out.println();
			jobNum = getIntegerInput("Select a job to work on: ");
			if (jobNum < 0 || jobNum >= game.getNumberOfJobs()) {
				System.out.println("ERROR. Please choose a valid job.");
			} else {
				inputValid = true;
			}
		} while (!inputValid);
		return jobNum;
	}
	
	/**
	 *Returns time to work on selected job if user input is greater than
	 * or equal to zero.
	 * 
	 * @return
	 *           timeToWork if inputValid is not false 
	 */
	private int getTimeToWork() {
		int timeToWork;
		boolean inputValid = false;
		do {
			timeToWork = getIntegerInput(
					"For how long would you like to work on this job?: ");
			if (timeToWork <= 0) {
				System.out.println("ERROR. Please enter a positive integer.");
			} else {
				inputValid = true;
			}
		} while (!inputValid);
		return timeToWork;
	}

}