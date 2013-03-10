package spaceinvaders;

import processing.core.PApplet;

public class Player
{
	// keep track of our location on the screen
	public int x;
	public int xSize = 25;
	public int y;
	public int ySize = 10;
	
	
	// also store a reference to the graphics canvas
	public PApplet canvas;
	
	//give the player a life indication
	public boolean alive = true;
	
	// constructor
	public Player(int x, int y, PApplet canvas)
	{
		this.x = x;
		this.y = y;
		this.canvas = canvas;
	}
	
	// move - accepts a direction (integer)
	// -1 = move left
	//  0 = don't move
	// +1 = move right
	public void move(int direction)
	{
		if (direction == -1)
		{
			moveLeft();
		}
		
		if (direction == 1)
		{
			moveRight();
		}
	}
	
	// moveLeft - moves the player the left and allows them to wrap around from side to side
	public void moveLeft()
	{
		// move to the left
		this.x -= 3;
		
		// if we go off the left edge we should reorient to the right edge
		if (this.x < 0)
		{
			this.x = this.canvas.width;
		}
	}
	
	// moveRight - moves the player to the right and allows them to wrap around from side to side
	public void moveRight()
	{
		// move to the right
		this.x += 3;
		
		// if we go off the right edge we should reorient to the left side
		if (this.x > this.canvas.width)
		{
			this.x = 0;
		}
	}
	
	// draw our player (basic rectangle)
	public void display()
	{
		this.canvas.fill(150);
		this.canvas.rect(this.x, this.y, this.xSize, this.ySize);
	}
	
	// C: implement a missile hit test like the one for aliens 
	public boolean missileHitTest(int mx, int my)
	{
		// if we are alive and the missile is intersecting our current location
		// then we have a hit
		if (this.alive && mx > (this.x - this.xSize/2) && mx < (this.x + this.xSize/2) && my > (this.y - this.ySize/2) && my < (this.y + this.ySize/2))
		{
			this.alive = false;
			return true;
		}
		
		return false;
	}
	
}
