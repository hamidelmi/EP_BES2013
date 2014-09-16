package eplab.bodenobjekte;
 
public class GridCell
{
	protected String resolution = null;
	protected int cellWidth;
	protected int cellHeight;
	protected int resWidth;
	protected int resHeight;
	protected final int cellWidthAdj = 0;
	protected final int cellHeighthAdj = 33960;
   
	public GridCell(String resolution, int res_w, int res_h, int cellWidth, int cellHeight)
	{
		this.resolution = resolution;
		this.resWidth = res_w;
		this.resHeight = res_h;
		this.cellWidth = cellWidth;
		this.cellHeight = cellHeight;
	}
   
	public String getResolution()
	{
		return this.resolution;
	}
   
	public int getCellWidth()
	{
		return this.cellWidth;
	}
   
	public int getCellHeight()
	{
		return this.cellHeight;
	}
   
	public int getResWidth()
	{
		return this.resWidth;
	}
	   
	public int getResHeight()
	{
		return this.resHeight;
	}
	   
	public int getCellWidthAdj()
	{
		getClass();return 0;
	}
	   
	public int getCellHeightAdj()
	{
		getClass();return 33960;
	}
}