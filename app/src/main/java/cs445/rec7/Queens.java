package cs445.rec7;

import java.util.Arrays;

public class Queens {
    /**
     * Checks if a partial solution is a complete solution.
     * @param partial The partial solution
     * @return true if the partial solution is complete, false otherwise.
     */
    public static boolean isFullSolution(int[] partial) {
        // TODO: Implement this method
        for (int i = 7; i >= 0; i--)
        {
            if (partial[i] == 0)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a partial solution should be rejected because it can never be extended to a
     * complete solution.
     * @param partial The partial solution
     * @return true if the partial solution should be rejected, false otherwise.
     */
    public static boolean reject(int[] partial) {
        // TODO: Implement this method

        //compare every pair of queens
        //diff colm, queens are not in same
        //positive or negative diagonal, slope = +/-1

        for (int i=0; i<8; i++)
        {
            for (int k =0; k< i; k++)
            {   
                //there's a queen that hasnt been placed yet
                //not a conflict yet
                if (partial[i] == 0 || partial[k] == 0)
                {
                    continue;
                }
                else if ( i!= k && partial[i] == partial[k])
                {
                    //there is a conflict
                    return true;
                }
                //positive diagonal
                else if (partial[i] - partial[k] == i-k)
                {
                    return true;
                }
                //negative diagonal
                else if (partial[i] - partial[k] == k-i)
                {
                    return true;
                }
            }
        }
        //no pair conflict found
        return false;
    }

    /**
     * Extends a partial solution by adding one additional queen.
     * @param partial The partial solution
     * @return a partial solution with one more queen added, or null if no queen can be added.
     */
    public static int[] extend(int[] partial) {
        // TODO: implement this method

        int[] temp = new int[8];
        for (int i = 0; i < 8; i++)
        {
            if (partial[i]!=0)
            {
                temp[i]=partial[i];
            }
            else
            {
                temp[i] = 1;
                return temp;
            }
        }


        //no queen can be added, all are already placed
        return null;
    }

    /**
     * Moves the most recently-placed queen to its next possible position.
     * @param partial The partial solution
     * @return a partial solution with the most recently-placed queen moved to its next possible
     * position, or null if we are out of options for the most recent queen.
     */
    public static int[] next(int[] partial) {
        // TODO: implement this method
        int[] temp = new int[8];
        int i = 0;
        while (i<8)
        {
            //change position of last placed queen
            if (i==7 || partial[i+1] == 0)
            {
                if (partial[i] == 8)
                {
                    return null;
                }
                else
                {
                    temp[i] = partial[i] + 1;
                }
            }
            else
            {
                temp[i]= partial[i];
            }
            i++;
        }

        return temp;
    }

    /**
     * Solves the 8-queens problem and outputs all solutions
     * @param partial The partial solution
     */
    public static void solve(int[] partial) {
        if (reject(partial)) return;
        if (isFullSolution(partial)) System.out.println(Arrays.toString(partial));
        int[] attempt = extend(partial);
        while (attempt != null) {
            solve(attempt);
            attempt = next(attempt);
        }
    }

    /**
     * Solves the 8-queens problem and returns one solution
     * @param partial The partial solution
     * @return A full, correct solution
     */
    public static int[] solveOnce(int[] partial) {
        if (reject(partial)) return null;
        if (isFullSolution(partial)) return partial;
        int[] attempt = extend(partial);
        while (attempt != null) {
            int[] solution = solveOnce(attempt);
            if (solution != null) return solution;
            attempt = next(attempt);
        }
        return null;
    }



    public static void main(String[] args) {
        if (args.length >= 1 && args[0].equals("-a")) {
            // Find all solutions starting from the empty solution
            solve(new int[] {0, 0, 0, 0, 0, 0, 0, 0});
        } else {
            // Find one solution starting from the empty solution
            int[] solution = solveOnce(new int[] {0, 0, 0, 0, 0, 0, 0, 0});
            System.out.println(Arrays.toString(solution));
        }
    }
}

