
public class mapLocCalculator
{
    public static int getMapX(int x)
    {
        if(x>200&&x<800)
        {
            return 200-x;
        }
        else if (x<=200)
        {
            return 0;
        }
        else 
        {
            return -600;
        }
    }
    
    
    public static int getMapY(int y)
    {
        if(y>200&&y<800)
        {
            return 200-y;
        }
        else if (y<=200)
        {
            return 0;
        }
        else 
        {
            return -600;
        }
    }
    
    public static int getPlayerX(int x)
    {
        if(x<800&&x>200)
        {
            return 200;
        }
        else if (x>=800)
        {
            return x-600;
        }
        else 
        {
            return x;
        }
        
    }
    
    
    public static int getPlayerY(int y)
    {
        if(y<800&&y>200)
        {
            return 200;
        }
        else if (y>=800)
        {
            return y-600;
        }
        else 
        {
            return y;
        }
        
    }
}
