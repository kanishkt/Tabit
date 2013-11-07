package com.tabituiuc.tabit;

/**
 * Created by Forrest on 6/11/13.
 */

/*
Calls the Tuners Module ultermately and returns 2D arrays with recursion methods
 */
public class ShortestDistCalculations {
  
	public static double max=32767;   // actually is the min box difference sum record
	public static int all=3;   //all step---can be changed
	public static int[] best=new int[all];   //shortest path record --3 digit number
	public static int pmove=3;   //possible box difference
	public static int[] nowstep=new int[all];    // record the step we are having now
	public static double[] stepsum=new double[all];   // to record the box difference sum we are having now
	public static int[][] possible=new int[all][4];    //possible string and box position for every step
	public static int[] frequency=new int [all];    //get data from ken---not using it now
	public static void find(int gen){
		for(int i=0;i<4;i++){
			if(possible[gen][i]==0) return;            //no more possible position for this step, then return to before step
			if(gen==0){                                //condition for the first step                     
				stepsum[gen]=0;
				nowstep[gen]=possible[gen][i];
			}else{                                    //condition for other steps
				int string=possible[gen][i]/100;
				int box=possible[gen][i]%100;
				int pre=1;
				while(nowstep[gen-pre]%100==0){         
					pre++;
				}
				int prestring=nowstep[gen-pre]/100;
				int prebox=nowstep[gen-pre]%100;
				double diff=Math.abs(prebox-box)+0.1*Math.abs(prestring-string);
				if(box==0){                                                 //condtion for pushing strings without pressing box
					nowstep[gen]=possible[gen][i];
					stepsum[gen]=0+stepsum[gen-1];
				}
				else if(diff<=pmove){                                 // when the change of boxes is within the range allowed
				   stepsum[gen]=diff+stepsum[gen-1];
				   nowstep[gen]=possible[gen][i];
				}else continue;
			}
			if(gen==all-1){             //reached the last step
				if(stepsum[gen]<max){
					max=stepsum[gen];
					for(int j=0;j<all;j++)
						best[j]=nowstep[j];
					
				}
			}else{                //go to next step
				find(gen+1);
			}
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        for(int i=0;i<all;i++){
        	for(int j=0;j<4;j++)
        		possible[i][j]=0;
        }
        possible[0][0]=103;
        possible[0][1]=201;
        possible[0][2]=402;
        possible[1][0]=102;
        possible[1][1]=305;
        possible[1][2]=407;
        possible[2][0]=104;
        possible[2][1]=306;
        possible[2][2]=403;
        find(0);
        System.out.println(max);
        for(int i=0;i<all;i++)
        	System.out.print(best[i]+" ");
	}

}
