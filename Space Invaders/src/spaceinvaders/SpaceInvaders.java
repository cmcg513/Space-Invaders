/*
 * Author (of this version): Casey McGinley (NOTE: my comments are denoted by C:
 * Framework and basic structure: Craig Kapp
 * Due: 12/3/12 11:59PM
 * 
 * Controls: 'a' to move left; 'd' for right; 's' to shoot; 'w' to reload, when applicable
 * 
 * Description: Save the planet! You are being invaded by size changing blue aliens, boring red aliens, 
 * koopas, and megamen (who can't seem to stop jumping up and down).  You have 10 missiles at most to 
 * fire at any given time. Head back to the refresh icon on the left to reload.  Kill all the aliens in 
 * order to win. There are 32 aliens total and the score counter in the upper right keeps track of these 
 * kills.  Be wary of the aliens missiles though. They hurt! One shot and you're a goner! No extra lives
 * in this game.
 * 
 */


package spaceinvaders;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class SpaceInvaders extends PApplet 
{
	// the user controlled player
	Player thePlayer;
	int playerMovementDirection = 0;

	// the user controlled missiles
	Missile[] missiles = new Missile[10];
	
	// C: create a reload icon
	Reload reloader;
	
	// number of missiles
	int ammo = this.missiles.length;
	
	// C: missile index
	int missileIndex = 0;

	// our aliens
	// C: change to multidimensional array
	Alien[][] theAliens;
	
	// C: instance var for "splash state"
	int state = 0;
	
	// C: instance var for image
	PImage menu;
	
	// C: instance var for "you won" splash screen
	PImage winner;
	
	// C: instance var for loss
	PImage loser;
	
	// C: instance var to keep track of point
	int points = 0;
	
	// C: counters for each alien's initial x position
	int blue = 0;
	int red = 25;
	int koopa = 50;
	int megaman = 75;


	
	public void setup() 
	{
		
		this.reloader = new Reload(5, 400, 100, this);
		
		// set the size of our graphics canvas 1000,300
		// C: for my splash, we set to 960, 540
		size(1000, 500);
		
		// C: load opening splash screen
		this.menu = loadImage("SpaceSplash.png");
		
		// C: load winning splash screen
		this.winner = loadImage("youwon.png");
		
		// C: gameover screen
		this.loser = loadImage("gameover.png");
		
		// C: draw our image to the screen at position 0,0
	    image(this.menu, 0, 0, this.width, this.height);
		
		// smooth all drawing
		smooth();
		
		// draw all rectangles from their center points
		rectMode(CENTER);

		// create a new instance of our Player class
		this.thePlayer = new Player(this.width/2, this.height-25, this);
		
		// create a new instance of our Missile class
		// (the player only gets one missile - if they shoot more than one then we
		// simply take the one in flight and bring it back down so that it can fire again)
		
		for (int j = 0; j < this.missiles.length; j++)
		{
			this.missiles[j] = new Missile(0,-50,this);
		}
		// create two new aliens
		// C: array of 4 rows of 8 aliens
		this.theAliens = new Alien[4][8];
		
		// C: two rows of red and blue aliens
		for (int j = 0; j < this.theAliens[0].length; j++)
		{
			this.theAliens[0][j] = new BlueAlien(this.blue, 25, this);
			this.blue += 50;
		}
		for (int j = 0; j < this.theAliens[1].length; j++)
		{
			this.theAliens[1][j] = new RedAlien(this.red, 55, this);
			this.red += 50;
		}
		
		// C: row of Koopas
		for (int j = 0; j < this.theAliens[2].length; j++)
		{
			this.theAliens[2][j] = new Koopa(this.koopa, 85, this);
			this.koopa += 50;
		}
		
		// C: row of megamen
		for (int j = 0; j < this.theAliens[2].length; j++)
		{
			this.theAliens[3][j] = new Megaman(this.megaman, 115, this);
			this.megaman += 50;
		}
		

		// load in our font so that we can draw text to the screen
		PFont genericFont = loadFont("sansSerifFont24.vlw");
		
		// set the default font as the one we just loaded
		this.textFont( genericFont );
	}

	public void draw() 
	{
		
		// C: change the splash state
		// C: test if user has pressed a key while in the opening screen
		if (keyPressed == true && this.state == 0)
		{
			this.state = 1;
		}
		// C: test if player has won
		else if (this.points == this.theAliens.length * this.theAliens[0].length)
		{
			this.state = 2;
		}
		// C: resetting the game??
		/*else if (keyPressed == true && (this.state == 2 || this.state == 3))
		{
			reset();
		}*/
		
		// C: test if player has lost
		else if (this.thePlayer.alive == false)
		{
			this.state = 3;
		}
		
		
		
		//C: test the splash state
		if (this.state == 1)
		{
			// erase the background
			background(0);
			
			// title text
			fill(255);
			text("Space Invaders!", 0, 25);
			
			// C: display points
			text("SCORE: " + this.points, this.width-200, 25);
			
			// C: display number of missiles
			text("AMMO: " + this.ammo, this.width-500, 25);
					
			// C: display the reload icon
			this.reloader.display();
			
			// iterate over all aliens
			//iterate through rows
			for (int r = 0; r < this.theAliens.length; r++)
			{
				//iterate through columns
				for (int c = 0; c < this.theAliens[0].length; c++)
				{
					// tell each one to move
					this.theAliens[r][c].move();
					for (int j = 0; j < this.missiles.length; j ++)
					{
						// check each alien to see if the missile hit them
						// if so the alien will flip its own "alive" variable to false
						boolean hit = this.theAliens[r][c].missileHitTest(this.missiles[j].x,  this.missiles[j].y);
						
						// C: if we have a hit, increase the point count
						if (hit)
						{
							this.points++;
						}
					}
					// C: test to see if the player has been hit
					this.thePlayer.missileHitTest(this.theAliens[r][c].theMissile.x, this.theAliens[r][c].theMissile.y);
					// you could do something with "hit" here if you needed to react to an alien being hit
		
					// display each alien
					this.theAliens[r][c].display();
					this.theAliens[r][c].theMissile.move("Enemy");
					this.theAliens[r][c].theMissile.display();
					this.theAliens[r][c].fire();
				}
			}
				
			// move and display the missiles
			for (int j = 0; j < this.missiles.length; j ++)
			{
				this.missiles[j].move("Player");
				this.missiles[j].display();
			}
					
			// move and display the player
			this.thePlayer.move(this.playerMovementDirection);
			this.thePlayer.display();
		}
		
		// C: display you won splash screen
		
		//C: implement a button for trying again; i.e. reset state and point tracker and aliens
		else if ( this.state == 2)
		{
			image(this.winner, 0, 0, this.width, this.height);
		}
		
		// C: losing state
		else if ( this.state == 3)
		{
			image(this.loser, 0, 0, this.width, this.height);
		}
	}

	// every time a key is pressed this method will execute
	public void keyPressed()
	{
		// user hit the 'a' key - indicate that we want to move left
		if (key == 'a')
		{
			this.playerMovementDirection = -1;
		}
		
		// user hit the 'd' key - indicate that we want to move right
		if (key == 'd')
		{
			this.playerMovementDirection = 1;
		}
		
		// user hit the 's' key - reload and fire the missile
		if (key == 's' && this.missileIndex < this.missiles.length)
		{
			System.out.println(missileIndex);
			this.missiles[missileIndex].reload(this.thePlayer.x, this.thePlayer.y);
			this.missileIndex++;
			this.ammo--;
		}
		
		// C: if you're in the reload icon, press w to reload
		if (key == 'w' && (this.reloader.playerInside(this.thePlayer)))
		{
			this.missileIndex = 0;
			this.ammo = this.missiles.length;
		}
	}
	
	// every time a key is released this method will execute
	public void keyReleased()
	{
		// indicate that the player should stop moving
		this.playerMovementDirection = 0;
	}
	
	
	
	//How to implement a reset method?
	/*public void reset()
	{
		this.playerMovementDirection = 0;
		// number of missiles
		this.ammo = this.missiles.length;
		
		// C: missile index
		this.missileIndex = 0;
		
		this.state = 0;
		
		this.points = 0;
		
		this.blue = 0;
		this.red = 25;
		this.koopa = 50;
		this.megaman = 75;
		
		for (int j = 0; j < this.theAliens[0].length; j++)
		{
			this.theAliens[0][j] = new BlueAlien(this.blue, 25, this);
			this.blue += 50;
		}
		for (int j = 0; j < this.theAliens[1].length; j++)
		{
			this.theAliens[1][j] = new RedAlien(this.red, 55, this);
			this.red += 50;
		}
		
		// C: row of Koopas
		for (int j = 0; j < this.theAliens[2].length; j++)
		{
			this.theAliens[2][j] = new Koopa(this.koopa, 85, this);
			this.koopa += 50;
		}
		
		// C: row of megamen
		for (int j = 0; j < this.theAliens[2].length; j++)
		{
			this.theAliens[3][j] = new Megaman(this.megaman, 115, this);
			this.megaman += 50;
		}
	}*/	
}
