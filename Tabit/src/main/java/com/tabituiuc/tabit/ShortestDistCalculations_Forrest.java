package com.tabituiuc.tabit;

/**
 * Created by Susan on 6/11/13.
 */

/*
Calls the Tuners Module continuously and returns 2D arrays with recursion methods
 */

/* Notes from Forrest:
    Basically what my code does is that i find analyze the frequencies and find all the box and string combinations possible of the current note,
    then return the closest option, recording this combination and find the next frequency, and making use of the combination i recorded to
    analyze the next possible options. Then finally returning an array. I think we better have a meeting on monday to discuss further about this.

 */

public class ShortestDistCalculations_Forrest extends ShortestDistCalculations {



    private static final double MAX_DIFF = 32767;   // this holds the maximum allowable differnce in frequency
    private static final int BOX_DIFF = 3;   //maximum allowed difference in box
    private int[] possible = new int[6];    //possible string and box position for every step, like something like 606 represents 6th string 6th box, Instead of using arrays, since this saves time.
    private int[] frequencies = new int[numSteps];// Input frequency from Forrest's Main Activity method, i will call a constructor to initialize such method.
    private int[] results = new int[numSteps];
    private int numSteps;   //number of notes in the music
    private int previousStep;  // to hold the previous step

    public ShortestDistCalculations_Forrest(int[] input) // Constructor
    {
        this.numSteps = input.length - 1;
        this.frequencies = input;
    }

    private int[] possibleSteps(int frequency){

        //return all possible frequencies for a note to be played.
        throw new RuntimeException("Not implemented");

    }

    private int nextStep(int[] possibleSteps, int previousStep) {

            // implement methods that follows the criteria for changing strings.

        throw new RuntimeException("Not implemented");

    }

    private int findMin(int[] src, int numElementsLeft){

        // finds the minimum distance.
        throw new RuntimeException("Not implemented");
    }


    private int findMinFreq(int[] src, int numElementsLeft){
        // finds the minimum frequency in the array recursively
        throw new RuntimeException("Not implemented");
    }


    private int findMaxFreq(int[] src, int numElementsLeft){
        // finds the maximum frequency in the array recursively
        throw new RuntimeException("Not implemented");
    }

    private int findStepsForFreq(int[] src, int startElement, int finishElement){
        // finds steps required to achieve the steppings we need
        /* Basically what i am trying to do here is to divide to entire music into various parts, and find the maximum and minimum in between, and then since the main target we need is to know the difference between max and min within a small segment of music,
        since if this segment of music contains a large leap, we want to jump the most steps possible to achieve the change in music. Or I am thinking is this actually needed? cuz all we have to do is to figure out the lowest and highest notes.
         */

        throw new RuntimeException("Not implemented");
    }

    private void analyzer(){
        // this method is the main wrapper class that analyzes all frequencies input and then store them into results
        throw new RuntimeException("Not implemented");
    }

    public int[] results(){
        return results;

    }
/* your methods:
    public void find(int gen) {
        for (int i = 0; i < 4; i++) {
            if (possible[gen][i] == 0)
                return;            //no more possible position for this step, then return to before step (Forrest's Comment: USE CONTINUE INSTEAD)
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
                } else if (diff <= BOX_DIFF) {                                 // when the change of boxes is within the range allowed
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
    */

    // testing methods following

    public static void main(String[] args) {
    // To be implemented
    }
}

