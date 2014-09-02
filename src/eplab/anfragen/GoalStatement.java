package eplab.anfragen;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class GoalStatement 
{
	  private EPStatement epsStatement;
	  
	  public GoalStatement(EPAdministrator epAdministrator, UpdateListener listener)
	  {
		  System.out.println("Goal stmt");
	    String query = "insert into GoalEvent "
	    		    + "select * from pattern[GoalEvent= BallContactEvent(shotongoal = 1) "
	    		  //  + "->ballPositionEvent=RelevantPositionEvent(entType = 2) "
	    		    + "until (BallContactEvent"+/* or OutOfPlayEvent*/")] ";
	    
	    this.epsStatement = epAdministrator.createEPL(query);
	    this.epsStatement.addListener(listener);
	  }
	}
