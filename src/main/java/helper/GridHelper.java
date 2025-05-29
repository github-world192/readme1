package helper;
public class GridHelper { 
	public static String getGridPositionStyle(int index) { 
		// Bottom row (Start to left)
		if (index >= 0 && index <= 10) { 
			return "grid-row: 11; grid-column: " + (11 - index) + ";"; 
			} 
		// Left column (bottom to top) 
		else if (index >= 11 && index <= 19) { 
			return "grid-row: " + (11 - (index - 10)) + "; grid-column: 1;"; 
			} 
		// Top row (left to right) 
		else if (index >= 20 && index <= 30) { 
			return "grid-row: 1; grid-column: " + (index - 19) + ";"; 
			} 
		// Right column (top to bottom) 
		else if (index >= 31 && index <= 39) { 
			return "grid-row: " + (index - 29) + "; grid-column: 11;"; 
			} 
		return ""; 
		}
}