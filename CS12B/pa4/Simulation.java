//--------------------------------------------------------------------------------------------------
// Sudeep Baniya
// CMPS 12B
// sucbaniy
// 5-15-2016
// The programs 
// Simulation.java
// Simulation.java a program thats find the time it takes to do m or totalnumjobs number of jobs
//-------------------------------------------------------------------------------------------------
import java.io.*;
import java.util.Scanner;

public class Simulation{

// helper functions

// The following function may be of use in assembling the initial backup and/or
// storage queues.  You may use it as is, alter it as you see fit, or delete it
// altogether.
// takes input as scanner and then create jobs  
public static Job getJob(Scanner in){
    
    String[] s = in.nextLine().split(" ");
    
    int a = Integer.parseInt(s[0]);
    
    int d = Integer.parseInt(s[1]);
    
    return new Job(a,d);
  }

  // prints time tracker
  public static void printTracker(PrintWriter tracker,Queue[] a, int n, int time){
    
    tracker.println("time=" +time);
    
    for(int i =0; i<n+1;i++){
    
      tracker.println(i+": "+ a[i]);
    
      System.out.println();
    }
  }

  // numjobs() takes scanner as input and computes the number of jobs it will need to compute
  public static int numJobs(Scanner in){
    
    int compute;
    
    String str = in.nextLine();
    
    compute = Integer.parseInt(str);
    
    return compute;
  }
  
  // finds the index of the Queue array
  public static int getArrayIndex(Queue[] a){
    
    int totalIndex =0;
    
    if( ((Job)a[totalIndex].peek()).getFinish() == -1){
    
        totalIndex = 1;
      }
    
    for(int i =1; i<a.length;i++){
    
      if(a[i].length() < a[totalIndex].length()){
    
        if(a[i].length() == a[totalIndex].length()){
    
          totalIndex = totalIndex;
    
        }else{
    
          totalIndex = i;
    
        }
    
      }else if( a[i].length() < a[totalIndex].length() && ((Job)a[totalIndex].peek()).getFinish() !=-1){
    
        totalIndex = totalIndex;
    
      }
    }
    
    return totalIndex;
  }
  
  //main
  public static void main(String[] args) throws IOException{

    //declares the variables
    Scanner in = null;  // input file
    
    PrintWriter reporter = null;   
    
    PrintWriter tracker = null;
    
    Queue storageQueue = new Queue();
    
    int n;  
    
    int totalnumjobs;
    
    int time = 0;
    

    //check commandline arguments
    if (args.length < 3){
    
      System.err.println("Usage: Simulation in out out");
    
      System.exit(1);
    }
    
    //open files for reading and writing
    in = new Scanner(new File(args[0]));
    
    reporter = new PrintWriter( new FileWriter(args[1]+".rpt"));
    
    tracker = new PrintWriter( new FileWriter(args[2]+ ".trc"));
    
    //read in m or totalnumjobs job and the Queue array
    totalnumjobs = numJobs(in);
    
    while(in.hasNext()){
    
      storageQueue.enqueue((Job)getJob(in));
    }
    
    tracker.println("Trace file: " + args[2] +".trc");
    
    tracker.println(totalnumjobs + " Jobs:");
    
    tracker.println(storageQueue+"\n");
    
    // loop for running simulations n=1 to n= (m-1)
    // there is always one less processor than jobs
    for( n=1; n<totalnumjobs; n++){
    
      time = 0;
      
      Queue[] simulation = new Queue[n+1];
    
      for(int i=0; i<n+1;i++){
    
        simulation[i]= new Queue();
    
      }
      
      // prints each time its runs from the main loop
      tracker.println("*****************************");
    
      tracker.println(n +" processor:");
    
      tracker.println("*****************************");
    
      // place the storage in the first index of the array
      simulation[0]=storageQueue;
    
      while( ((Job)simulation[0].peek()).getFinish() == -1  || simulation[0].length()!=totalnumjobs){
        if(time == 0){
         
         printTracker(tracker,simulation,n,time);
         
          time = ((Job)simulation[0].peek()).getArrival();
         
          simulation[1].enqueue(simulation[0].dequeue());
         
          Job temporary = (Job)simulation[1].peek();
         
          temporary.computeFinishTime(time);
        
        }else if(((Job)simulation[0].peek()).getFinish() != -1){
        
          int tiny = getArrayIndex(simulation);
        
          time = ((Job)simulation[tiny].peek()).getFinish();
        
          simulation[0].enqueue(simulation[tiny].dequeue());
        
          printTracker(tracker,simulation,n,time);
        
        }else{
        
          printTracker(tracker,simulation,n,time);
        
          int tiny = getArrayIndex(simulation);
          
          if( simulation[tiny].length() == 0){
        
           time = ((Job)simulation[0].peek()).getArrival();
        
           simulation[tiny].enqueue(simulation[0].dequeue());
        
           Job temporary = (Job)simulation[tiny].peek();
        
           temporary.computeFinishTime(time);
        
           printTracker(tracker,simulation,n,time);
           
           tiny = getArrayIndex(simulation);
           
           time = ((Job)simulation[tiny].peek()).getFinish();
           
           simulation[0].enqueue(simulation[tiny].dequeue());
           
           printTracker(tracker,simulation,n,time);
           
           tiny = getArrayIndex(simulation);
           
           time = ((Job)simulation[0].peek()).getArrival();
           
           simulation[tiny].enqueue(simulation[0].dequeue());
           
           temporary = (Job)simulation[tiny].peek();
           
           temporary.computeFinishTime(time);
           
           printTracker(tracker,simulation,n,time);
           
           time = ((Job)simulation[tiny+1].peek()).getFinish();
           
           simulation[0].enqueue(simulation[tiny+1].dequeue());
           
           printTracker(tracker,simulation,n,time);
       
           tiny = getArrayIndex(simulation);
       
           time = ((Job)simulation[tiny-1].peek()).getFinish();
       
           simulation[0].enqueue(simulation[tiny-1].dequeue());
       
           printTracker(tracker,simulation,n,time);
         
           }else if( ((Job)simulation[0].peek()).getArrival() <= ((Job)simulation[tiny].peek()).getFinish() ){
        
            time = ((Job)simulation[0].peek()).getArrival();
        
            simulation[tiny].enqueue(simulation[0].dequeue());   
           
            printTracker(tracker,simulation,n,time);
            
            time = ((Job)simulation[tiny].peek()).getFinish();
            
            simulation[0].enqueue(simulation[tiny].dequeue());
            
            Job temporary = (Job)simulation[tiny].peek();
            
            temporary.computeFinishTime(time);

            printTracker(tracker,simulation,n,time);  
          
          }else{
          
            Job temporary = (Job)simulation[tiny].peek();
          
            temporary.computeFinishTime(time);

            printTracker(tracker,simulation,n,time);
            
            time = ((Job)simulation[tiny].peek()).getFinish();
  
            simulation[0].enqueue(simulation[tiny].dequeue());
            
            printTracker(tracker,simulation,n,time);
            
          }
       
        }
      
      }
      
      if(n==1){
  
        reporter.println("Report file: "+ args[1] + ".rpt");
  
        reporter.println( totalnumjobs + " Jobs:");
  
        reporter.println(simulation[0] +"\n");
  
        reporter.println("************************************************");
      }
    
      int tWait=0; // totalwait
    
      int mWait=0; // maximumwait
    
      int maximum=0;
    
      double aWait=0; // averagewait

      Queue queueHolder = new Queue();
      
      while( simulation[0].length() != 0 ){
  
        maximum = ((Job)simulation[0].peek()).getWaitTime();
  
        if( mWait < maximum){
  
          mWait = maximum;
        }
  
        tWait += ((Job)simulation[0].peek()).getWaitTime();
  
        queueHolder.enqueue((Job)simulation[0].dequeue());
        }

      //find the maximum wait, average wait, and total wait for all the Jobs, then after done reset the finish times
      aWait =(double)tWait/totalnumjobs;
      
      aWait = (double)Math.round(aWait*100)/100;

      reporter.println( n + " processor: totalWait="+ tWait+ " maxWait="+mWait+" averageWait="+aWait);
      
      while(queueHolder.length() != 0){
      
        ((Job)queueHolder.peek()).resetFinishTime();
      
        storageQueue.enqueue((Job)queueHolder.dequeue());
      }
      
      tracker.println();
      
    }
    // end main body loops

    // close input and output files
    in.close();
  
    reporter.close();
  
    tracker.close();
    
  }
}
