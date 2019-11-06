import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class AverageMax {

	static int[] findMinCrossingSubarray(int ar[], int low, int mid, int high)
	  {
	    int leftSum = Integer.MAX_VALUE,maxleft=0, maxright=0;
	    int sum = 0;
	    

	    for (int i=mid; i>=low; i--)
	    {
	      sum = sum+ar[i];
	      if (sum<leftSum)
	      {
	        leftSum = sum;
	        maxleft=i;
	      }
	    }
	    
	    int rightSum = Integer.MAX_VALUE;
	    sum = 0;

	    for (int j=mid+1; j<=high; j++)
	    {
	      sum=sum+ar[j];
	      if (sum<rightSum)
	      {
	        rightSum = sum;
	        maxright=j;
	      }
	    }

	    
	    return new int[]{maxleft,maxright,leftSum+rightSum};
	  }
	
	static int[] findMinimumSubSubarray(int ar[], int low, int high)
	  {
	    if (high == low) // only one element in an array
	    {
	      return new int[] {low,high,ar[high]};
	    }
	    else
	    {
	    	    int mid = (high+low)/2;
	    	    int arrleft[],arright[],arrarross[];
	    	    

	    	    arrleft=findMinimumSubSubarray(ar, low, mid);
	    	    int leftlow=arrleft[0];
	    	    int lefthight=arrleft[1];
	    	    int leftsum = arrleft[2];
	    
	    	    arright=findMinimumSubSubarray(ar, mid+1, high);
	    	    int rightlow=arright[0];
	    	    int righthight=arright[1];
	    	    int rightsum = arright[2];
	    	    
	    	    arrarross=findMinCrossingSubarray(ar, low, mid, high);
	    	    int crosslow=arrarross[0];
	    	    int crosshight=arrarross[1];
	    	    int crosssum = arrarross[2];

	    	    if((leftsum<=rightsum) && (leftsum<=crosssum))
	    	    	return new int[] {leftlow,lefthight,leftsum};
	    	    else if((rightsum<=leftsum) && (rightsum<=crosssum))
	    	    	return new int[] {rightlow,righthight,rightsum};
	    	    return new int[] {crosslow,crosshight,crosssum};
	    }
	  }
	
	static float maxAvg(int ar[],int start, int end)
	{
		float max=0,sumavg=0,sum=0;
		int j=1;
		if(start<=end)
		{
			sum=ar[start];
			max=sum;
			for(int i=start+1;i<=end;i++)
			{
				j=j+1;
				sum+=ar[i];
				sumavg=sum/j;
				
				//compare the 
				if(max<sumavg)
					max=sumavg;
				
				
				
			}
		}
		if (max<0)
			return 0;
		return max;
	}
	static float maxAvgB(int ar[],int start, int end)
	{
		float max=0,sumavg=0,sum=0;
		int j=1;
		if(start<=end)
		{
			sum=ar[end];
			max=sum;
			for(int i=end-1;i>=start;i--)
			{
				j=j+1;
				sum+=ar[i];
				sumavg=sum/j;
				
				//compare the 
				if(max<sumavg)
					max=sumavg;				
				
			}
		}
		if (max<0)
			return 0;
		return max;
	}
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//read file
		 File file = new File("F:\\Master_Canada\\Advance Data Structures and Algorithms\\StopCountingSampleData\\09_rand_medium.in"); 
		  
		 BufferedReader br = new BufferedReader(new FileReader(file)); 
		  
		  String st; 
		  int i=1;
		  int[] a = null;
		  while ((st = br.readLine()) != null) 
		  {
			  if(i>1)
			  {
				  a=Arrays.stream(st.split(" ")).mapToInt(Integer::parseInt).toArray(); 
			  }
			  i++;
		  }
		  //
		
		  if(a.length==1) 
		  { 
			  float max=0; 
			  max=a[0]; 
			  if(max<0) 
				  max=0;
			  System.out.println(max); 
		  } 
		  else 
		  { 
			  int[] minimumArr;
			  minimumArr=findMinimumSubSubarray(a, 0,a.length-1);
			  int start=minimumArr[0]; 
			  int end=minimumArr[1];
			 
			  float maxAvgA=maxAvg(a,0,start-1); 
			  float maxAvgB=maxAvgB(a,end+1,a.length-1);
 
			  if (maxAvgA>maxAvgB) 
				  System.out.println(maxAvgA); 
			  else
				  System.out.println(maxAvgB); 
		}
		 
	}

}
