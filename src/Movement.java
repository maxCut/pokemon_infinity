import java.awt.*;
import java.util.ArrayList;
import java.awt.Graphics;
/**
 * Write a description of class Movement here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Movement
{
    // instance variables - replace the example below with your own
    private Rectangle player;
    private Rectangle border;
//    private ArrayList<Rectangle> collisionBoxes = new ArrayList<Rectangle>();
//    private ArrayList<Rectangle> shakeyPatches = new ArrayList<Rectangle>();
    private final int MOVEDISTANCE = (int)(World.SCALE*3);

    public Seed worldSeed;
    /**
     * Constructor for objects of class Movement
     */
    public Movement(int x, int y, int width, int height, Rectangle b,Seed s)
    {
        player = new Rectangle(x,y,width,height);
        border = b;
        worldSeed = s;
    }

    
    public boolean cassualEncounter()
    {
    
        ArrayList<Rectangle> shakyPatches = calcShaky();
        for (Rectangle b:shakyPatches)
        {
            if(player.intersects(b))
            {
                return Math.random()<.02;
            }
        }
        return false;
    }

    public boolean canMoveTo(Direction d)
    {
        ArrayList<Rectangle> collisionBoxes = calcCollisions();
        Rectangle temp = new Rectangle((int)player.getX(),(int)player.getY(),(int)player.getWidth(),(int)player.getHeight()); 
        switch (d)
        {
            case Up:
            temp.y-=MOVEDISTANCE;
            break;
            case Down:
            temp.y+=MOVEDISTANCE;
            break;
            case Right:
            temp.x+=MOVEDISTANCE;
            break;
            case Left:
            temp.x-=MOVEDISTANCE;
        }

        for (Rectangle b:collisionBoxes)
        {
            if(temp.intersects(b))
            {
                return false;
            }
        }

        return true;

    }

    public void move(Direction d)
    {
        if(canMoveTo(d))
        {
            switch (d)
            {
                case Up:
                player.y-=MOVEDISTANCE;
                break;
                case Down:
                player.y+=MOVEDISTANCE;
                break;
                case Right:
                player.x+=MOVEDISTANCE;
                break;
                case Left:
                player.x-=MOVEDISTANCE;
            }
        } 

    }
    
    public int getX()
    {
        return player.x;
    }
    
    public int getY()
    {
        return player.y;
    }
     
    public ArrayList<Rectangle> calcShaky()
    {
        ArrayList<Rectangle> answer = new ArrayList<Rectangle>();
        //The following code is based on the painComponent in World.java
        final int tileWidth = (int)(World.SCALE*40);
        final int tileHeight = (int)(World.SCALE*40);
        final int xShift = (int)(World.SCALE*200);
        final int yShift = (int)(World.SCALE*200);
            int curTileX = player.x - xShift -tileWidth;
            int curTileY = player.y - yShift -tileHeight;
            int xAcust = player.x % tileWidth; 
            int yAcust = player.y % tileHeight;
            while(curTileY<player.y+ yShift + tileHeight)
            {
                if(worldSeed.getTile(curTileX-xAcust,curTileY-yAcust)==tileType.tallGrass)
                    answer.add(new Rectangle(curTileX-xAcust,curTileY-yAcust+5,tileWidth,25));
                curTileX += tileWidth;
                if(curTileX>player.x+xShift)
                {
                    curTileX = player.x - xShift;
                    curTileY += tileHeight;
                }

            }
                return answer;
    }

    public ArrayList<Rectangle> calcCollisions()
    {
        ArrayList<Rectangle> answer = new ArrayList<Rectangle>();
        //The following code is based on the painComponent in World.java
        final int tileWidth = (int)(World.SCALE*40);
        final int tileHeight = (int)(World.SCALE*40);
        final int xShift = (int)(World.SCALE*200);
        final int yShift = (int)(World.SCALE*200);
            int curTileX = player.x - xShift -tileWidth;
            int curTileY = player.y - yShift -tileHeight;
            int xAcust = player.x % tileWidth; 
            int yAcust = player.y % tileHeight;
            while(curTileY<player.y+ yShift + tileHeight)
            {
                if(worldSeed.getTile(curTileX-xAcust,curTileY-yAcust)==tileType.tree)
                    answer.add(new Rectangle(curTileX-xAcust-(int)(5*World.SCALE),curTileY-yAcust+(int)(9*World.SCALE),tileWidth,25));
                else if(worldSeed.getTile(curTileX-xAcust,curTileY-yAcust)==tileType.viridianCity)
                {
                    
                }

                curTileX += tileWidth;
                if(curTileX>player.x+xShift)
                {
                    curTileX = player.x - xShift;
                    curTileY += tileHeight;
                }

            }
                return answer;
    }
}
