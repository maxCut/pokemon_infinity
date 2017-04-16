import java.util.ArrayList;
import java.awt.*;
public class cityCollisionCalculator
{
    public static ArrayList<Rectangle> getCityCollisions(int tileX,int tileY,tileType tile)
    {
        switch(tile)
        {
            case viridianCity:
                return viridianCity(tileX,tileY);

        }
        return new ArrayList<Rectangle>();
        
    }

    public static ArrayList<Rectangle> viridianCity(int tileX, int tileY)
    {
        int relativeX = (tileX+Seed.xShift)%Seed.townSizeX;
        int relativeY = (tileY+Seed.yShift)%Seed.townSizeY;

        ArrayList<Rectangle> ret = new ArrayList<Rectangle>();

        if(relativeX==0 && relativeY==5)//hard code all colisions
        {
            ret.add(new Rectangle(tileX,tileY+(int)(14*World.SCALE),(int)(16*World.SCALE),(int)(14*World.SCALE)));
            return ret;
        }
        return ret;
       


    }
}
