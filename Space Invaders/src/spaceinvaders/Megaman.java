package spaceinvaders;

import processing.core.PApplet;
import processing.core.PImage;

public class Megaman extends Alien
{
	//instance vars
	PImage left;
	
	PImage right;
	
	int bounceCount = 0;
	
	float heightChange = 1;
	
	public Megaman(int x, int y, PApplet canvas)
	{
		super(x,y,canvas);
		
		// laod in the alien1.png graphic from the data folder
		// store it in our graphic instance variable
		this.left = this.canvas.loadImage("Megaman8bitleft.png");
		this.right = this.canvas.loadImage("Megaman8bit.png");
	}
	
	// override the superclass display method
	@Override
	public void display()
	{
		// only draw if we are alive
		if (this.alive)
		{
			//give megaman the bounce movement
			this.bounceCount++;
			this.y += this.heightChange;
			if (this.bounceCount % 5 == 0)
			{
				if (this.heightChange == -1)
				{
					this.heightChange = 1;
				}
				else if (this.heightChange == 1)
				{
					this.heightChange = -1;
				}
			}
			
			// C: test direction
			if (this.direction > 0)
			{
				canvas.image(this.right, this.x ,this.y, this.size, this.size);
			}
			else
			{
				// image the graphic to the screen
				canvas.image(this.left, this.x ,this.y, this.size, this.size);
			}
		}
	}
}