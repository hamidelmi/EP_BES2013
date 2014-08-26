package epdebs.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.swing.text.html.HTMLDocument.Iterator;


public class MatchIntervals
{
  class MatchInterval
  {
    int iEventId;
    int iHalf;
    double dInrevalStratTime;
    double dInrevalEndTime;
    double dTotalTime;
    
    public MatchInterval(int id, int half, double s_time, double e_time, double d_time)
    {
      this.iEventId = id;
      this.iHalf = half;
      
      this.dInrevalStratTime = s_time;
      this.dInrevalEndTime = e_time;
      this.dTotalTime = d_time;
    }
    
    public void setIntervalEndTime(double e_time)
    {
      this.dInrevalEndTime = e_time;
    }
    
    public double getIntervalStartTime()
    {
      return this.dInrevalStratTime;
    }
    
    public double getIntervalEndTime()
    {
      return this.dInrevalEndTime;
    }
    
    public boolean isBetween(double picoTs)
    {
      if ((picoTs >= this.dInrevalStratTime) && (picoTs <= this.dInrevalEndTime)) {
        return true;
      }
      return false;
    }
  }
  
  Vector<MatchInterval> IntervalsVector = null;
  int intervalIndex;
  double currMatchTime;
  
  public MatchIntervals()
  {
    this.IntervalsVector = new Vector();
    this.intervalIndex = 0;
    this.currMatchTime = 0.0D;
  }
  
  public void addMatchInterval(int id, int half, double s_time, double e_time)
  {
    double timeTillNow = 0.0D;
    if (!this.IntervalsVector.isEmpty()) {
      timeTillNow = ((MatchInterval)this.IntervalsVector.lastElement()).dTotalTime + (((MatchInterval)this.IntervalsVector.lastElement()).dInrevalEndTime - ((MatchInterval)this.IntervalsVector.lastElement()).dInrevalStratTime);
    }
    MatchInterval giCell = new MatchInterval(id, half, s_time, e_time, timeTillNow);
    this.IntervalsVector.add(giCell);
  }
  
  protected static BufferedReader InitializeReader(String sFileName)
  {
    BufferedReader reader = null;
    try
    {
      reader = new BufferedReader(new FileReader(sFileName));
    }
    catch (IOException e)
    {
      System.out.println("Error in initializeReader:" + e.getMessage() + " Existing");
      System.exit(1);
    }
    return reader;
  }
  
  public static boolean isTimeStampValid(String inputString, String TimeFormat)
  {
    SimpleDateFormat format = new SimpleDateFormat(TimeFormat);
    try
    {
      format.parse(inputString);
      return true;
    }
    catch (Exception e) {}
    return false;
  }
  
  public static double convertMilliToSec(long tsOrig)
  {
    double tsPico = tsOrig * Math.pow(10.0D, -3.0D);
    
    return tsPico;
  }
  
  public static long convertStringtoMilliSec(String currTime)
  {
    String sTimeStampFormat = "hh:mm:ss.SSS";
    SimpleDateFormat intervalTimeFormat = new SimpleDateFormat(sTimeStampFormat);
    Calendar baseCal = new GregorianCalendar();
    if (isTimeStampValid(currTime, sTimeStampFormat)) {
      try
      {
        baseCal.setTime(intervalTimeFormat.parse("00:00:00.000"));
        Date parsedDate = intervalTimeFormat.parse(currTime);
        Calendar tmpCal = new GregorianCalendar();
        tmpCal.setTime(parsedDate);
        
        return tmpCal.getTimeInMillis() - baseCal.getTimeInMillis();
      }
      catch (Exception e)
      {
        System.out.println("error converting ts to pico seconds");
        return 1800000L;
      }
    }
    return 1800000L;
  }
  
  public boolean ParseMatchInterruptionsFile(String sMatchInterFileName)
  {
    File dir = new File(sMatchInterFileName);
    for (File child : dir.listFiles()) {
      try
      {
        BufferedReader brInteruptReader = InitializeReader(child.toString());
        
        String[] splitInputLine = null;
        String sIntrruptionStr = null;
        
        int iIntervalId = 0;
        int iIntervalHalf = 0;
        String sRecordType = null;
        String sRealTime = null;
        double dSec = 0.0D;
        while ((sIntrruptionStr = brInteruptReader.readLine()) != null)
        {
          if ((sIntrruptionStr.equals("")) || (sIntrruptionStr.contains(";;;;")) || (!sIntrruptionStr.contains(";"))) {
            break;
          }
          splitInputLine = sIntrruptionStr.toString().split(";");
          if (!splitInputLine[0].equals("event_id"))
          {
            if (splitInputLine[0].startsWith("20")) {
              iIntervalHalf = 1;
            }
            if (splitInputLine[0].startsWith("60")) {
              iIntervalHalf = 2;
            }
            iIntervalId = Integer.parseInt(splitInputLine[3]);
            sRecordType = splitInputLine[1].split(" ")[2];
            sRealTime = splitInputLine[2];
            
            dSec = convertMilliToSec(convertStringtoMilliSec(sRealTime));
            if (sRecordType.equals("End")) {
              addMatchInterval(iIntervalId, iIntervalHalf, dSec, convertStringtoMilliSec("00:30:00.000"));
            }
            if ((sRecordType.equals("Begin")) && 
              (!this.IntervalsVector.isEmpty())) {
              ((MatchInterval)this.IntervalsVector.lastElement()).setIntervalEndTime(dSec);
            }
          }
        }
      }
      catch (IOException e)
      {
        System.out.println("error reading interaption file");
        return false;
      }
    }
    System.out.println("ParseMatchInterruptionFiles");
    return true;
  }
  
  public void printMatchInteraption()
  {
    System.out.println("Print Match interaptions :");
    java.util.Iterator<MatchInterval> it = this.IntervalsVector.iterator();
    while (it.hasNext())
    {
      MatchInterval activeInterval = (MatchInterval)it.next();
      System.out.println(activeInterval.iEventId + " " + activeInterval.iHalf + " " + activeInterval.dInrevalStratTime + " " + activeInterval.dInrevalEndTime + " " + activeInterval.dTotalTime);
    }
  }
  
  public boolean moveToActiveInterval(double currTs)
  {
    while (currTs > ((MatchInterval)this.IntervalsVector.get(this.intervalIndex)).dInrevalEndTime) {
      if (this.IntervalsVector.size() - 1 > this.intervalIndex)
      {
        this.currMatchTime = ((MatchInterval)this.IntervalsVector.get(this.intervalIndex)).dTotalTime;
        this.intervalIndex += 1;
      }
      else
      {
        return false;
      }
    }
    if (((MatchInterval)this.IntervalsVector.get(this.intervalIndex)).isBetween(currTs)) {
      if (this.intervalIndex > 1) {
        this.currMatchTime = (((MatchInterval)this.IntervalsVector.get(this.intervalIndex)).dTotalTime + (currTs - ((MatchInterval)this.IntervalsVector.get(this.intervalIndex)).dInrevalStratTime));
      } else {
        this.currMatchTime = (currTs - ((MatchInterval)this.IntervalsVector.get(this.intervalIndex)).dInrevalStratTime);
      }
    }
    return true;
  }
  
  public boolean isActiveInterval(double currTs)
  {
    if (!moveToActiveInterval(currTs)) {
      return false;
    }
    MatchInterval currMatchInterval = (MatchInterval)this.IntervalsVector.get(this.intervalIndex);
    if (currMatchInterval.isBetween(currTs)) {
      return true;
    }
    return false;
  }
  
  public double getCurrMatchTime(double currTs)
  {
    moveToActiveInterval(currTs);
    return this.currMatchTime;
  }
  
  public static void main(String[] args)
  {
    MatchIntervals matchIntervals = new MatchIntervals();
    
    matchIntervals.ParseMatchInterruptionsFile(Settings.matchInfoFilePath);
    
    matchIntervals.printMatchInteraption();
    
    System.out.println("time in Match :" + matchIntervals.getCurrMatchTime(179.08000000000001D));
  }
}
