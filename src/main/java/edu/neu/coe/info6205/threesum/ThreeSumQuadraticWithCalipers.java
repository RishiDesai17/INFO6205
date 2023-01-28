package edu.neu.coe.info6205.threesum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * Implementation of ThreeSum which follows the approach of dividing the solution-space into
 * N sub-spaces where each sub-space corresponds to a fixed value for the middle index of the three values.
 * Each sub-space is then solved by expanding the scope of the other two indices outwards from the starting point.
 * Since each sub-space can be solved in O(N) time, the overall complexity is O(N^2).
 * <p>
 * The array provided in the constructor MUST be ordered.
 */
public class ThreeSumQuadraticWithCalipers implements ThreeSum {
    /**
     * Construct a ThreeSumQuadratic on a.
     *
     * @param a a sorted array.
     */
    public ThreeSumQuadraticWithCalipers(int[] a) {
        this.a = a;
        length = a.length;
    }

    /**
     * Get an array or Triple containing all of those triples for which sum is zero.
     *
     * @return a Triple[].
     */
    public Triple[] getTriples() {
        List<Triple> triples = new ArrayList<>();
        Collections.sort(triples); // ???
        for (int i = 0; i < length - 2; i++)
            triples.addAll(calipers(a, i, Triple::sum));
        return triples.stream().distinct().toArray(Triple[]::new);
    }

    /**
     * Get a set of candidate Triples such that the first index is the given value i.
     * Any candidate triple is added to the result if it yields zero when passed into function.
     *
     * @param a        a sorted array of ints.
     * @param i        the index of the first element of resulting triples.
     * @param function a function which takes a triple and returns a value which will be compared with zero.
     * @return a List of Triples.
     */
    public static List<Triple> calipers(int[] a, int i, Function<Triple, Integer> function) {
        List<Triple> triples = new ArrayList<>();
        
        int leftIndex = i;
        int midIndex = i + 1;
        int rightIndex = a.length - 1;
        
        while (midIndex < rightIndex) {
        	Triple triple = new Triple(a[leftIndex], a[midIndex], a[rightIndex]);
        	int sum = triple.sum();
        	
        	if (sum == 0) {
        		triples.add(triple);
        		
        		do {
        			midIndex += 1;
        		}
        		while(midIndex < rightIndex && a[midIndex - 1] == a[midIndex]);
        		
        		do {
        			rightIndex -= 1;
        		}
        		while(rightIndex > midIndex && a[rightIndex] == a[rightIndex + 1]);
        	}
        	else if (sum < 0) {
        		do {
        			midIndex += 1;
        		}
        		while(midIndex < rightIndex && a[midIndex - 1] == a[midIndex]);
        	}
        	else {
        		do {
        			rightIndex -= 1;
        		}
        		while(rightIndex > midIndex && a[rightIndex] == a[rightIndex + 1]);
        	}
        }

        return triples;
    }

    private final int[] a;
    private final int length;
}
