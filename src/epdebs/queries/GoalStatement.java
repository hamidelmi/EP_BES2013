package epdebs.queries;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class GoalStatement 
{
	  private EPStatement epsStatement;
	  public void addListener(UpdateListener listener)
	  {
	    this.epsStatement.addListener(listener);
	  }
	  
	  public GoalStatement(EPAdministrator epAdministrator)
	  {
		  System.out.println("Goal stmt");
	    String query = "insert into GoalEvent "
	    		    + "select * from pattern[GoalEvent= BallContactEvent(goal = 1) "
	    		    + "->ballPositionEvent=RelevantPositionEvent(entType = 2) "
	    		    + "until (BallContactEvent or OutOfPlayEvent)] ";
	    
	    this.epsStatement = epAdministrator.createEPL(query);
	  }
	}
