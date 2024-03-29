package spaceinvaders;

import processing.core.PApplet;

public class Alien
{
	// store our position on the screen
	public float x;
	public float y;
	
	// store whether we are still active
	public boolean alive;
	
	// store our current direction
	public int direction = 2;
	
	// store a reference to the canvas
	PApplet canvas;
	
	// C: each alien gets a missile
	Missile theMissile;
	
	// store a standard size for all aliens
	public int size = 25;
	
	// C: size of some aliens may change so we set a new instance var to change positioning 
	// based on initial size
	final int positioningSize = 25;

	// constructor
	public Alien(int x, int y, PApplet canvas)
	{
		this.x = x;
		this.y = y;
		this.alive = true;
		this.canvas = canvas;
		this.size = 25;
		
		this.theMissile = new Missile(0,-50,this.canvas);
	}
	
	// move the alien
	public void move()
	{
		// only update our x position based on our direction
		this.x += this.direction;

		// if we hit the right edge we should turn around
		// and move down by one row
		if (this.x > this.canvas.width)
		{
			this.direction *= -1;
			this.x = this.canvas.width;
			this.y += this.positioningSize;
		}
		
		// if we hit the left edge we should turn around
		// and move down by one row
		if (this.x < 0 - this.positioningSize)
		{
			this.direction *= -1;
			this.x = 0 - this.positioningSize;
			this.y += this.positioningSize;
		}
	}
	
	// test to see if the missile hit us
	public boolean missileHitTest(int mx, int my)
	{
		// if we are alive and the missile is intersecting our current location
		// then we have a hit
		if (this.alive && mx > this.x && mx < this.x+this.size && my > this.y && my < this.y+this.size)
		{
			this.alive = false;
			return true;
		}
		
		return false;
	}
	
	// display this alien
	public void display()
	{
		// only draw if we are currently alive
		if (this.alive)
		{
			this.canvas.fill(100);
			this.canvas.ellipse(this.x, this.y, 20, 20);
		}
	}
	
	// C: fire the missile
	public void fire()
	{
		if (this.alive)
		{
	
			if (firingCondition())
			{
				this.theMissile.reload((int)this.x, (int)this.y);
			}
			
		}
	}
	
	// C: decide whether or not to fire
	public boolean firingCondition()
	{
		int num = (int)this.canvas.random(1,1000);
		return (num == 21);
	}
}
