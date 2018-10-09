//------------------------------------------------------------------------------
// Sudeep Baniya
// CMPS 12B
// sucbaniy
// 5-15-2016
// The programs displays error when Queue is empty
// QueueEmptyException.java
//------------------------------------------------------------------------------
public class QueueEmptyException extends RuntimeException{
	
	public QueueEmptyException(String s){
	super(s);
	}
}