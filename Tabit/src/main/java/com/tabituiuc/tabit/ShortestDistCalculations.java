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

public class ShortestDistCalculations {


    //  private static final double MAX_DIFF = 32767;   // this holds the maximum allowable differnce in frequency
    private static final int BOX_DIFF = 2;   //maximum allowed difference in box
    private int[] possible = new int[6];    //possible string and box position for every step, like something like 606 represents 6th string 6th box, Instead of using arrays, since this saves time.
    private int[] frequencies;// Input frequency from Forrest's Main Activity method, i will call a constructor to initialize such method.
    private int[] results;
    private int numSteps;   //number of notes in the music
   //  private int previousStep;  // to hold the previous step
    private int beginString; // that records the previous string
    private int beginBox; // that records the previous box
    private static final int A_FREQ = 440;
    private static final double TONE_CONST = Math.pow(2, 1.0/12);

    public ShortestDistCalculations(int[] input) // Constructor
    {
        this.numSteps = input.length;
        this.frequencies = input;
        results = new int[numSteps];
    }

    private int[] possibleSteps(double indexoffrequencies) {
        int EString, AString, DString, GString, BString, highEString;

        int semiTonesFromLowE;
        semiTonesFromLowE = (int)Math.round((Math.log(indexoffrequencies/A_FREQ)/Math.log(TONE_CONST))) + 17;
        EString = semiTonesFromLowE;
        AString = semiTonesFromLowE - 5;
        DString = semiTonesFromLowE - 10;
        GString = semiTonesFromLowE - 15;
        BString = semiTonesFromLowE - 19;
        highEString = semiTonesFromLowE - 24;


        int[] results = new int[]{EString, AString, DString, GString, BString, highEString};
        int j=0;
        for (int i = 0; i < results.length; i++)
        {
            if (results[i] < 0 || results[i] > 16) // not an available option
            {
                results[i] =0;

            }

            else{
                results[j] =results[i]+ (i+1)*100;
                if(i>j)
                    results[i]=0;
                j++;
            }
        }

        return results;

        //return all possible position for a frequency to be played.

    }


    private int findMin(){
        int indexOfMin = 0;
        int i = 0;
        while(i < 6 && possible[i] != 0 && possible[i] != 999){

        	if(Math.abs(possible[i]%100-beginBox)<Math.abs(possible[indexOfMin]%100-beginBox))
        		indexOfMin=i;
        	i++;
        }
        return indexOfMin;
        // finds the minimum distance.
        // returns the INDEX of the shortest distance in possible array
    }


    private int findMinFreq(){

        int min=0;
        for(int i=0;i<numSteps;i++)
        	if(frequencies[i]<frequencies[min] && frequencies[i] != 800)
        		min=i;
        return min;
    }


    private int findMaxFreq(){
        // finds the maximum frequency in the array recursively
        int max=0;
        for(int i=0;i<numSteps;i++)
        	if(frequencies[i]>frequencies[max] && frequencies[i] != 800)
        		max=i;
        return max;
    }

    private void setEmpty(int indexOfNote){
        results[indexOfNote] = 800;
    }
    private void set(int indexOfPosition,int indexOfNote){
    	results[indexOfNote]=possible[indexOfPosition];
    }
    private void switchString(int indexOfPosition){
    	beginString = possible[indexOfPosition]/100;
    	beginBox = possible[indexOfPosition]%100;
    	//if(temp!=0) beginBox = temp;      // if this note is push string without pressing box, then we change string but don't change beginbox--Is that correct?
    }

    private int haveGoodPlaceOnSameString( int indexOfNote){
    	int i=0;
    	while(i<6 && possible[i]!=0){
    		if(possible[i]/100==beginString  &&(Math.abs(possible[i]%100-beginBox)<=BOX_DIFF))
    			// if(results[indexOfNote-1]%100==0 || Math.abs(results[indexOfNote-1]%100-possible[i]%100)<=BOX_DIFF)
    		     	return i;
            i++;
    	}
    	return -1;
    }
    private void firstnote(int i){

        int maxindex=findMaxFreq();
        int minindex=findMinFreq();

        possible=possibleSteps(frequencies[maxindex]);

        beginBox=0;
        int maxbox=possible[findMin()]%100;

        possible=possibleSteps(frequencies[minindex]);
        beginBox=14;
        int minbox=possible[findMin()]%100;

        beginBox=(minbox+maxbox)/2;

        possible=possibleSteps(frequencies[i]);    //the first note;
        int index=findMin();

        set(index,i);
        switchString(index);

    }

    public void nextnote(int i){
        if (frequencies[i] == 800)
        {
            setEmpty(i);
        }

        else {
    	possible=possibleSteps(frequencies[i]);
    	int have = haveGoodPlaceOnSameString(i);
    	if(have!=-1)
    		set(have,i);
    	else{
    		int indexOfMin=findMin();
    		set(indexOfMin,i);
    		switchString(indexOfMin);
    	}
        }
    }
    private void analyzer(){
        int j = 0;
        // this method is the main wrapper class that analyzes all frequencies input and then store them into results
        while(frequencies[j] == 800){
            setEmpty(j);
            j++;
        }

        firstnote(j);
        for(int i = j+1; i<numSteps; i++){
             nextnote(i);
        }
    }


    public int[] getResults() {
        analyzer();
        return results;

    }

    public static void main(String[] args){





        ShortestDistCalculations test = new ShortestDistCalculations(new int[] {494, 523, 494, 440});
        int[] result = test.getResults();
        int[] firstresult = result;
        for(int i=0;i<4;i++){
          System.out.println(firstresult[i]);

        }



        // do nothing

    }


}
