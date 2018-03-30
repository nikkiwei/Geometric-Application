package Application;
import Database.*;
import GeometryPack.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.*;
import javax.swing.*;

public final class Zoom {

	int scaleFactor;
	GeometryPack geometry;
	
	public Zoom()
	{
		scaleFactor = 100; //set default
		geometry = new GeometryPack();
	}
	
	public void changeScale(int newScale)
	{
		scaleFactor=newScale;
	}

	public void paint(Graphics2D g2, DB database, int pointSize) {
			//for all the point arrays (that will be drawn into shapes) saved in data base
			for (int i=0; i<database.Size(); i++){
				Point[] shapeArray = database.Get(i); 

				// POINT: length of the array is 1
				if (shapeArray.length == 1) {

					//Saves the point from the array
					Point p =  shapeArray[0];
					//Draws ovals at the point's location
					g2.fillOval(p.x - (int)(pointSize), p.y - (int)(pointSize), pointSize*2, pointSize*2);
				}

				// LINE: length of the array is 2
				else if (shapeArray.length == 2 ) {

					//Saves the two line end points from the array
					Point p1 = shapeArray[0];
					Point p2 = shapeArray[1];

					//Draws the line with two ovals on the end points
					g2.fillOval(p1.x - (int)(pointSize/2), p1.y - (int)(pointSize/2), pointSize, pointSize);
					g2.fillOval(p2.x - (int)(pointSize/2), p2.y - (int)(pointSize/2), pointSize, pointSize);
					g2.drawLine(p1.x, p1.y, p2.x, p2.y);
				}
		}
	}
}
