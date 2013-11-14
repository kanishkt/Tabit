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

public class ShortestDistCalculations_Forrest {


    private static final double MAX_DIFF = 32767;   // this holds the maximum allowable differnce in frequency
    private static final int BOX_DIFF = 4;   //maximum allowed difference in box
    private int[] possible = new int[4];    //possible string and box position for every step, like something like 606 represents 6th string 6th box, Instead of using arrays, since this saves time.
    private int[] frequencies;// Input frequency from Forrest's Main Activity method, i will call a constructor to initialize such method.
    private int[] results;
    private int numSteps;   //number of notes in the music
    private int previousStep;  // to hold the previous step
    private int beginString;
    private int beginBox;
    
    public ShortestDistCalculations_Forrest(int[] input) // Constructor
    {
        this.numSteps = input.length;
        this.frequencies = input;
        results=new int[numSteps];
    }

    private int[] possibleSteps(int indexoffrequencies){

        //return all possible position for a frequency to be played.
        throw new RuntimeException("Need real data to implement this method-Susan");

    }

    private int nextStep(int[] possibleSteps, int previousStep) {

            // implement methods that follows the criteria for changing strings.
        throw new RuntimeException("Not implemented");

    }

    private int findMin(){
        int indexOfMin=0;
        int i=1;
        while(i<4 && possible[i]!=0){
        	if(Math.abs(possible[i]%100-beginBox)<Math.abs(possible[indexOfMin]%100-beginBox))
        		indexOfMin=i;
        	i++;
        }
        return indexOfMin;
        // finds the minimum distance.
        //throw new RuntimeException("Not implemented");
    }


    private int findMinFreq(){
        // finds the minimum frequency in the array recursively
        //throw new RuntimeException("Not implemented");
        int min=0;
        for(int i=0;i<numSteps;i++)
        	if(frequencies[i]<frequencies[min])
        		min=i;
        return min;
    }


    private int findMaxFreq(){
        // finds the maximum frequency in the array recursively
        int max=0;
        for(int i=0;i<numSteps;i++)
        	if(frequencies[i]>frequencies[max])
        		max=i;
        return max;
    }
    private void set(int indexOfPosition,int indexOfnote){
    	results[indexOfnote]=possible[indexOfPosition];
    }
    private void switchString(int indexOfPosition){
    	beginString=possible[indexOfPosition]/100;
    	beginBox=possible[indexOfPosition]%100;
    }
    private int haveGoodPlaceOnSameString( int indexOfNote){
    	int i=0;
    	while(i<4 && possible[i]!=0){
    		if(possible[i]/100==beginString  &&(possible[i]%100==0 || Math.abs(possible[i]%100-beginBox)<=BOX_DIFF))
    			if(results[indexOfNote-1]%100==0 || Math.abs(results[indexOfNote-1]%100-possible[i]%100)<=BOX_DIFF)
    		     	return i;
            i++;
    	}
    	return -1;
    }
    private void analyzer(){
        // this method is the main wrapper class that analyzes all frequencies input and then store them into results
        //throw new RuntimeException("Not implemented");
        int maxindex=findMaxFreq();
        int minindex=findMinFreq();
        possible=possibleSteps(maxindex);
        beginBox=0;
        int maxbox=findMin();
        possible=possibleSteps(minindex);
        beginBox=14;
        int minbox=findMin();
        beginBox=(minbox+maxbox)/2;
        possible=possibleSteps(frequencies[0]);    //the first note;
        int index=findMin();
        set(index,0);
        switchString(index);
        for(int i=1;i<numSteps;i++){
        	possible=possibleSteps(frequencies[i]);
        	int have=haveGoodPlaceOnSameString(i);
        	if(have!=-1)
        		set(have,i);
        	else{
        		int indexOfMin=findMin();
        		set(indexOfMin,i);
        		switchString(indexOfMin);
        	}
        		
        }
    }

    public int[] getResults(){
        analyzer();
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
