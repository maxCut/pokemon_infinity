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
        tileX/=(int)(40*World.SCALE);
        tileY/=(int)(40*World.SCALE);
        int relativeX = ((tileX+Seed.xShift-1)%Seed.townSizeX+Seed.townSizeX)%Seed.townSizeX;
        int relativeY = ((tileY+Seed.yShift-1)%Seed.townSizeY+Seed.townSizeY)%Seed.townSizeY;

        ArrayList<Rectangle> ret = new ArrayList<Rectangle>();

        if(relativeX==0 && relativeY==5)//hard code all colisions //the sign
        {
            ret.add(new Rectangle(tileX*(int)(40*World.SCALE),tileY*(int)(40*World.SCALE)-(int)(22*World.SCALE),(int)(10*World.SCALE),(int)(1*World.SCALE)));
            return ret;
        }
        else if(relativeX==1 && relativeY==1)//the top left house
        {
            ret.add(new Rectangle(tileX*(int)(40*World.SCALE) + (int)(20*World.SCALE),tileY*(int)(40*World.SCALE)+(int)(22*World.SCALE),(int)(79*World.SCALE),(int)(39*World.SCALE)));
            return ret;
        }
        return ret;
       


    }
}
