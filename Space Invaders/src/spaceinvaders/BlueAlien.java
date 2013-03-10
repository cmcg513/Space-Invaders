package spaceinvaders;

import processing.core.PApplet;
import processing.core.PImage;

public class BlueAlien extends Alien
{
	// blue aliens get their own graphic
	PImage graphic;
	
	// C: size change increment
	int sizeChange = 1;
	
	// C: size change counter
	int sizeCount = 0;
	
	public BlueAlien(int x, int y, PApplet canvas)
	{
		super(x,y,canvas);
		
		// laod in the alien1.png graphic from the data folder
		// store it in our graphic instance variable
		this.graphic = this.canvas.loadImage("alien1.png");
	}
	
	// override the superclass display method
	@Override
	public void display()
	{
		// only draw if we are alive
		if (this.alive)
		{
			// C: increase size count
			this.sizeCount++;
			
			// C: change size
			if (this.sizeCount % 3 == 0)
			{
				this.size += this.sizeChange;
			}
			
			// C: change increment
			if (this.size == 20)
			{
				this.sizeChange = 1;
			}
			else if (this.size == 30)
			{
				this.sizeChange = -1;
			}
			// image the graphic to the screen
			canvas.image(this.graphic, this.x ,this.y, this.size, this.size);
		}
	}
}
