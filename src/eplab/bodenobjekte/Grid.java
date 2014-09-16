package eplab.bodenobjekte;
 
public class Grid
{
	static GridCell[] myHeatmap = new GridCell[4];
	static int gridWidth = eplab.anfragen.Game.GetFiledWidth();
	static int gridHeight = eplab.anfragen.Game.GetFiledHeight();
   
	static
	{
		//myHeatmap[0] = new GridCell("8X13", 8, 13, gridWidth / 8, gridHeight / 13);
		//myHeatmap[1] = new GridCell("16X25", 16, 25, gridWidth / 16, gridHeight / 25);
		myHeatmap[2] = new GridCell("32X50", 32, 50, gridWidth / 32, gridHeight / 50);
		//myHeatmap[3] = new GridCell("64X100", 64, 100, gridWidth / 64, gridHeight / 100);
	}
   
	public static GridCell[] getGrid()
	{
		return myHeatmap;
	}
   
	public static int GetLeftX(long cellCoordinate, int resSize, int cellSize, int cellAdj)
	{
		int lowleft_coord = 0;
		float cell_percent = ((float)cellCoordinate + cellAdj) / gridWidth;
		float cell_num = (float)(cell_percent * resSize - 1.0D);     
		lowleft_coord = cellSize * (int)Math.ceil(cell_num) - cellAdj;
		return lowleft_coord;
	}
	public static int GetLeftY(long cellCoordinate, int resSize, int cellSize, int cellAdj)
	{
		int lowleft_coord = 0;
		float cell_percent = ((float)cellCoordinate + cellAdj) / gridHeight;
		float cell_num = (float)(cell_percent * resSize - 1.0D);
		lowleft_coord = cellSize * (int)Math.ceil(cell_num) - cellAdj;
		return lowleft_coord;
	}
   
	public static int GetRightX(long cellCoordinate, int resSize, int cellSize, int cellAdj)
	{
		int uprightcoord = 0;
		float cell_percent = ((float)cellCoordinate + cellAdj) / gridWidth;
		float cell_num = cell_percent * resSize;     
		uprightcoord = cellSize * (int)Math.ceil(cell_num) - cellAdj;     
		return uprightcoord;
	}   
	public static int GetRightY(long cellCoordinate, int resSize, int cellSize, int cellAdj)
	{
		int uprightcoord = 0;     
		float cell_percent = ((float)cellCoordinate + cellAdj) / gridHeight;     
		float cell_num = cell_percent * resSize;
		uprightcoord = cellSize * (int)Math.ceil(cell_num) - cellAdj;     
		return uprightcoord;
	}
}