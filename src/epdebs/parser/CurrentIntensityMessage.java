package epdebs.parser;

public class CurrentIntensityMessage
{
  protected long ts_start;
  protected long ts_stop;
  protected String player_id;
  protected int intensity;
  protected long distance;
  protected long speed;
  
  public long getTs_start()
  {
    return this.ts_start;
  }
  
  public long getTs_stop()
  {
    return this.ts_stop;
  }
  
  public String getPlayer_id()
  {
    return this.player_id;
  }
  
  public int getIntensity()
  {
    return this.intensity;
  }
  
  public long getDistance()
  {
    return this.distance;
  }
  
  public long getSpeed()
  {
    return this.speed;
  }
  
  public CurrentIntensityMessage(long ts_start, long ts_stop, String player_id, int intensity, long distance, int speed)
  {
    this.ts_start = ts_start;
    this.ts_stop = ts_stop;
    this.player_id = player_id;
    this.intensity = intensity;
    this.distance = distance;
    this.speed = speed;
  }
  
  public CurrentIntensityMessage(long ts, long ts2, String name, int intensity2, long distance2, long speed2) {}
  
  public String toString()
  {
    String sEventString = this.ts_start + "," + this.ts_stop + "," + this.player_id + "," + this.intensity + "," + this.distance + "," + this.speed;
    
    return sEventString;
  }
}
