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
   
   Notes from Susan:
    I didn't test it because I need data to implement possibleSteps() method. But check to see if I got your idea right.
 */

public class ShortestDistCalculations_Forrest {


    //  private static final double MAX_DIFF = 32767;   // this holds the maximum allowable differnce in frequency
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
        results = new int[numSteps];
    }

    private int[] possibleSteps(int indexoffrequencies) {

        //return all possible position for a frequency to be played.
        throw new RuntimeException("Need real data to implement this method-Susan");

    }


    private int findMin(){
        int indexOfMin=0;
        int i=1;
        while(i<4 && possible[i]!=0){
        	if(possible[i]%100==0) return i;     //If we have a possible position that allow us to push string without pressing box then we consider that as the best choice--Is that right?
        	if(Math.abs(possible[i]%100-beginBox)<Math.abs(possible[indexOfMin]%100-beginBox))
        		indexOfMin=i;
        	i++;
        }
        return indexOfMin;
        // finds the minimum distance.
    }


    private int findMinFreq(){
        // finds the minimum frequency in the array recursively
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
    	int temp=possible[indexOfPosition]%100;
    	if(temp!=0) beginBox=temp;      // if this note is push string without pressing box, then we change string but don't change beginbox--Is that correct?
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
    private void firstnote(){
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
    }
    public void nextnote(int i){
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
    private void analyzer(){
        // this method is the main wrapper class that analyzes all frequencies input and then store them into results

        firstnote();
        for(int i=1;i<numSteps;i++){
             nextnote(i);
        }
    }


    public int[] getResults() {
        analyzer();
        return results;

    }


}
