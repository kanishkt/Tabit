package com.tabituiuc.tabit;

/**
 * Created by Susan on 6/11/13.
 */

/*
Calls the Tuners Module continuously and returns 2D arrays with recursion methods
 */

/* Notes from Forrest:
    I guess it is better if you make of Object oriented programming (and apparently from this it shows that you are from a C background haha).
    And make each array that you are going to return to be an object, and work with the object itself. Moreover, i guess i will create a wrapper class,
    just to parse all the input from Ken and make it into an array of ints(i.e. frequencies), and I will call a constructor for a new object of your class,
    and you will create a method in your class to return the results. And i think you might have to write a constructor method so that it takes parameters
    of the length of array, and parse it into your method. I guess in this way it will be easier instead of using a static method. And we will do the
    testings in my wrapper class and wrapper methods. I changed some of your variable names as well as i found them to be a bit confusing,
    i also reformatted your codes.
  
  Notes from Susan:
    I will try to write another version using Object oriented programming so that we can choose a more suitable one for
    our project. Wrapper method sounds good.
 */

public class ShortestDistCalculations {

    public static double MAX_DIFF = 32767;   // actually is the min box difference sum record (Forrest: USE a different naming system for constant variables and final for fixed variables)
    public static int numSteps = 3;   //all step---can be changed
    public static int[] best = new int[numSteps];   //shortest path record --3 digit number
    public static int pmove = 3;   //possible box difference
    public static int[] nowstep = new int[numSteps];    // record the step we are having now
    public static double[] stepsum = new double[numSteps];   // to record the box difference sum we are having now
    public static int[][] possible = new int[numSteps][4];    //possible string and box position for every step
    public static int[] frequency = new int[numSteps];    //get data from ken---not using it now

    public static void find(int gen) {
        for (int i = 0; i < 4; i++) {
            if (possible[gen][i] == 0)
                return;            //no more possible position for this step, then return to before step (Forrest's Comment: USE CONTINUE INSTEAD)
            //Susan: I use return because I will store the possible position consequently, for example if posible[gen][2]==0, then there will be no more possible value in possible[gen][3], so I can return to last step instead of continue, it will save time.
            if (gen == 0) {                                //condition for the first step
                stepsum[gen] = 0;
                nowstep[gen] = possible[gen][i];
            } else {                                    //condition for other steps
                int string = possible[gen][i] / 100;
                int box = possible[gen][i] % 100;
                int pre = 1;
                while (nowstep[gen - pre] % 100 == 0) {
                    pre++;
                }
                int prestring = nowstep[gen - pre] / 100;
                int prebox = nowstep[gen - pre] % 100;
                double diff = Math.abs(prebox - box) + 0.1 * Math.abs(prestring - string);
                if (box == 0) {                                                 //condtion for pushing strings without pressing box
                    nowstep[gen] = possible[gen][i];
                    stepsum[gen] = 0 + stepsum[gen - 1];
                } else if (diff <= pmove) {                                 // when the change of boxes is within the range allowed
                    stepsum[gen] = diff + stepsum[gen - 1];
                    nowstep[gen] = possible[gen][i];
                } else continue;
            }
            if (gen == numSteps - 1) {             //reached the last step
                if (stepsum[gen] < MAX_DIFF) {
                    MAX_DIFF = stepsum[gen];
                    for (int j = 0; j < numSteps; j++)
                        best[j] = nowstep[j];

                }
            } else {                //go to next step
                find(gen + 1);
            }
        }

    }

    // testing methods following

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        for (int i = 0; i < numSteps; i++) {
            for (int j = 0; j < 4; j++)
                possible[i][j] = 0;
        }
        possible[0][0] = 103;
        possible[0][1] = 201;
        possible[0][2] = 402;
        possible[1][0] = 102;
        possible[1][1] = 305;
        possible[1][2] = 407;
        possible[2][0] = 104;
        possible[2][1] = 306;
        possible[2][2] = 403;
        find(0);
        System.out.println(MAX_DIFF);
        for (int i = 0; i < numSteps; i++)
            System.out.print(best[i] + " ");
    }

}

