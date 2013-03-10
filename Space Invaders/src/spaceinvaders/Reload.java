package spaceinvaders;

import processing.core.PApplet;
import processing.core.PImage;

public class Reload
{
	//instance vars
	PImage icon;
	int x;
	int y;
	int size;
	
	PApplet canvas;
	
	//constructor
	public Reload(int x, int y, int size, PApplet canvas)
	{	
		this.x = x;
		this.y = y;
		this.size = size;
		this.canvas = canvas;
		this.icon = this.canvas.loadImage("gtk-refresh.png");
	}
	
	//display
	public void display()
	{
		// image the graphic to the screen
		this.canvas.image(this.icon, this.x ,this.y, this.size, this.size);
	}
	
	//test if player is inside the icon
	public boolean playerInside(Player player)
	{
		int leftX = player.x - (player.xSize)/2;
		int rightX = player.x + (player.xSize)/2;
		int upY = player.y - (player.ySize)/2;
		int downY = player.y + (player.ySize)/2;
		
		if (leftX > this.x && rightX < (this.x + this.size) && upY > this.y && downY < (this.y + this.size))
		{
			return true;
		}
		return false;
	}
}