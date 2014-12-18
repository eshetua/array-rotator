import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
/**
 * 
 * @author Eshetu A.
 *
 *	Integer array rotator class.
 */
public class ArrayRotator {
	public static void main(String args[]) {
		
		/**
		 * Initialize the options variable
		 */
		int option = -1;
		
		try
		{
			/**
			 * Parse command line argument
			 */
			option = Integer.parseInt(args[0]);
		}catch(Exception ex) {
			/**
			 * Exception and we will exit with error.
			 */
			System.out.println("Unable to parse arguments into integer ... exiting!");
			System.exit(1);
		}
		
		/**
		 * Rotator instance
		 */
		ArrayRotator thisClass = new ArrayRotator();
		
		if(option == 0) {
			/**
			 * Option zero runs unit tests and exits with success.
			 */
			thisClass.runUnitTests();
			System.exit(0);
		} else {
			
			/**
			 * Sample array for the sample run
			 */
			int[] array = new int[]{1,2,3,4,5,6};
			
			try {
				/**
				 * Try to shift the array three positions to the right.
				 */
				thisClass.rotate(array, 3);
			}catch(ArrayIndexOutOfBoundsException aio) {
				/**
				 * Exception so we exit with error.
				 */
				System.out.println(aio.getMessage());
				System.exit(1);
			}

			System.out.println("[ Rotated array is ] :");
			
			/**
			 * Print the shifted array separated by a space.
			 */
			for(int k = 0; k < array.length; k ++) {
				System.out.print(array[k]);
				System.out.print(" ");
			}
		}
		

	}

	/**
	 * Shifts elements of an integer array by number of positions to the right.
	 * Elements at the right end of the array will be moved to the beginning of the
	 * array during the shift operation. This is in-place modification of the supplied
	 * array object without using a temporary array for the purpose.
	 * 
	 * Takes two parameters.
	 * 
	 * array: the actual array object that needs to be shifted.
	 * position: the number of positions to shift the elements to the right.
	 * 
	 * @param array
	 * @param position
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public void rotate(int[] array, int position) throws ArrayIndexOutOfBoundsException {

		/**
		 * If supplied position is less than zero or greater than the total number of
		 * elements, then this is an edge case and we need to throw an exception.
		 */
		if(position < 0 || position > array.length)
			throw new ArrayIndexOutOfBoundsException("Supplied index is out of bounds -- either less than zero or greater than array length");

		/**
		 * The array length.
		 */
		int len = array.length;
		
		/**
		 * A temporary variable we use to save the last element of the array during
		 * the shift operation. This element will be assigned to the first element
		 * of the array after all elements has been shifted to the right.
		 */
		int temp;
		for( int i = 0; i < position; i++) {
			
			/**
			 * Save the last element of the array into a temporary value.
			 */
			temp = array[len-1];

			/**
			 * Iterate over the list of array elements starting from the last element
			 * and assign the value of the array element just before the current element being
			 * inspected to the current array index.
			 * 
			 * This will effectively shift all the elements of the array to the right one position at a time.
			 * At the end of this loop, we will have the first and second elements of the array having 
			 * similar values and the last element of the array before this loop being removed from
			 * the array. Since we have the last element in a temporary variable, we will assign
			 * the value of the temporary value to the first element.
			 */
			for(int j = len-1; j > 0; j--) {
				array[j] = array[j-1];
			}

			/**
			 * Update the first element of the array so that it takes the value of the last element.
			 */
			array[0] = temp;
		}
	}

	/**
	 * Runs sample unit tests for the rotating logic.
	 */
	public void runUnitTests()
	{
		System.out.println("Now running unit tests ...");
		
		/**
		 * Kick JUnit tests
		 */
		Result result = JUnitCore.runClasses(ArrayRotator.class);
		
		/**
		 * Grab the list of failed unit tests. Later, iterate over each failed test
		 * and notify when necessary
		 */
		List<Failure> failures = result.getFailures();

		if(failures.size() > 0) {//tests has failed.
			System.out.println("[The following unit tests has failed] :");
			for (Failure failure : result.getFailures()) {
				System.out.println(failure.toString());
			}
		} else {//all tests has succeeded.
			System.out.println("All unit tests has passed!");
		}
	}
	
	/**
	 * Run simple unit tests.
	 */
	@Test
	public void testRotations() {
		/**
		 * Sample array
		 */
		int[] array = new int[]{1,2,3,4,5,6,7,8,9,0};

		/**
		 * Rotator instance
		 */
		ArrayRotator rotator = new ArrayRotator();
		
		/**
		 * Sample array shifted two positions to the right.
		 */
		int[] twoPositions = new int[]{9,0,1,2,3,4,5,6,7,8};

		/**
		 * Shift the original array using our function
		 */
		rotator.rotate(array, 2);

		/**
		 * Assert equality
		 */
		Assert.assertArrayEquals(twoPositions, array);

		/**
		 * Sample array shifted further two positions to the right.
		 */
		int[] furtherTwoPositions = new int[]{7,8,9,0,1,2,3,4,5,6};

		/**
		 * Shift the original array (which has been shifted two places to the right), another
		 * two positions to the right.
		 */
		rotator.rotate(array, 2);

		/**
		 * Assert equality
		 */
		Assert.assertArrayEquals(furtherTwoPositions, array);
	}

	/**
	 * Run simple unit test -- to see if our method throws the necessary execption
	 */
	@Test(expected=IndexOutOfBoundsException.class)
	public void testExceptions() {
		/**
		 * The sample array
		 */
		int[] array = new int[]{1,2,3,4,5,6,7,8,9,0};

		/**
		 * Rotator instance
		 */
		ArrayRotator rotator = new ArrayRotator();
		
		/**
		 * Attempt to shift the array 20 positions to the right. This should throw
		 * exception as the supplied value is greater than the array length.
		 */
		rotator.rotate(array, 20);
	}
}
