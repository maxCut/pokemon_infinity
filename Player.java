import java.awt.*;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{

    private Gender gen;
    private SpriteRotation sprites;
    private Movement location;
    private Direction facing;
    private ArrayList<Programmer> party;
    /**
     * Constructor for objects of class Player
     */
    public Player(Gender g, Seed s)
    {
        gen = g;
        sprites = new SpriteRotation();
        location = new Movement(160,160,24,36,new Rectangle(0,0, 1000,970),s);
        facing = Direction.Up;
        party = new ArrayList<Programmer>();
        party.add(new Programmer(WildProgrammer.Max,3));
        party.add(new Programmer(WildProgrammer.Fisher,1));
    }
    
    public Programmer getProGrammer(int i)
    {
        return party.get(i);
    }

    public String topRight()
    {
        try
        {
            if (party.get(0).isAwake())
            {
                return party.get(0).getName();
            }
        }
        catch (Exception e)
        {
        }
        return "";
    }
    
    public String bottomRight()
    {
        try
        {
            if (party.get(1).isAwake())
            {
                return party.get(1).getName();
            }
        }
        catch (Exception e)
        {
        }
        return "";
    }
    
    public String topLeft()
    {
        
        try
        {
            if (party.get(2).isAwake())
            {
                return party.get(2).getName();
            }
        }
        catch (Exception e)
        {
        }
        return "";
    }
    
    public String bottomLeft()
    {
        try
        {
            if (party.get(3).isAwake())
            {
                return party.get(3).getName();
            }
        }
        catch (Exception e)
        {
        }
        return "";
    }
    
    public void swapGrammer(int i)
    {
        Programmer temp = party.get(i);
        party.set(i,party.get(0));
        party.set(0,temp);
    }
    
    public void catchProgrammer(Programmer p)
    {
        party.add(p);
    }

    public boolean playersRemaining()
    {
        for (Programmer p: party)
        {
            if(p.isAwake())
            {
                return true;
            }
        }
        return false;
    }

    public Programmer getActiveProgrammer()
    {
        for (Programmer p: party)
        {
            if(p.isAwake())
            {
                return p;
            }
        }
        return null;
    }

    public void move(Direction d)
    {
        sprites.Move();
        location.move(d);
        facing = d;
    }

    public BufferedImage getPic()
    {
        return sprites.getSprite(facing,gen);
    }

    public int getX()
    {
        return location.getX();
    }

    public int getY()
    {
        return location.getY();
    }

    public boolean isEncounter()
    {
        return location.cassualEncounter();
    }
}
