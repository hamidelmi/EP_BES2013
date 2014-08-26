package epdebs.queries;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class GoalStatement 
{
	  private EPStatement epStatement;
	  
	  public GoalStatement(EPAdministrator epAdministrator, UpdateListener listener)
	  {
	    String query = "insert into ShotOnGoalEvent "
	    		    + "select * from pattern[shotOnGoalEvent= BallContactEvent(shotongoal = 1) "
	    		    + "->ballPositionEvent=RelevantPositionEvent(entType = 2) "
	    		    + "until (BallContactEvent or PauseIntervalEvent)] ";
	    
	    String stmt = "insert into ShotOnGoalEvent "
	    		+ "select * from pattern[shotOnGoalEvent= BallContactEvent(shotongoal = 1) "
	    		+ "->ballPositionEvent=RelevantPositionEvent(entType = 2) "
	    		+ "until (BallContactEvent or OutOfPlayEvent)] ";
	    
	    this.epStatement = epAdministrator.createEPL(query);
		this.epStatement.addListener(listener);
	  }
	}
