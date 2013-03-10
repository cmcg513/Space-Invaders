package spaceinvaders;

import processing.core.PApplet;
import processing.core.PImage;

public class Koopa extends Alien
{
	// koopa aliens get their own graphics
	PImage left;
	
	//give him to images for when he changes directions
	PImage right;
	
	public Koopa(int x, int y, PApplet canvas)
	{
		super(x,y,canvas);
		
		// laod in the alien1.png graphic from the data folder
		// store it in our graphic instance variable
		this.left = this.canvas.loadImage("koopa_left2.png");
		this.right = this.canvas.loadImage("koopa_right2.png");
	}
	
	// override the superclass display method
	@Override
	public void display()
	{
		// only draw if we are alive
		if (this.alive)
		{
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
