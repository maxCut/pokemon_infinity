
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
/**
 * Write a description of class SpriteRotation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SpriteRotation
{

    //Guys
    //left
    static BufferedImage leftSprite;
    static BufferedImage runningleftrightSprite;
    static BufferedImage runningleftleftSprite;
    //up
    static BufferedImage upSprite;
    static BufferedImage runninguprightSprite;
    static BufferedImage runningupleftSprite;
    //down
    static BufferedImage downSprite;
    static BufferedImage runningdownrightSprite;
    static BufferedImage runningdownleftSprite;
    //right
    static BufferedImage rightSprite;
    static BufferedImage runningrightrightSprite;
    static BufferedImage runningrightleftSprite;

    //Galls
    //left
    static BufferedImage GleftSprite;
    static BufferedImage GrunningleftrightSprite;
    static BufferedImage GrunningleftleftSprite;
    //up
    static BufferedImage GupSprite;
    static BufferedImage GrunninguprightSprite;
    static BufferedImage GrunningupleftSprite;
    //down
    static BufferedImage GdownSprite;
    static BufferedImage GrunningdownrightSprite;
    static BufferedImage GrunningdownleftSprite;
    //right
    static BufferedImage GrightSprite;
    static BufferedImage GrunningrightrightSprite;
    static BufferedImage GrunningrightleftSprite;

    //Map
    //static BufferedImage Background;
    private Direction facing;

    int footKeeper = 0;
    /**
     * Constructor for objects of class SpriteRotation
     */
    public SpriteRotation()
    {

        //All Photo Imports are done first!!!

        // the pictures class saves photos to the jar with the load command

        //Guys
        //left
        try {leftSprite = ImageIO.read(Pictures.load("OrphanMainleft.png"));}
        catch(Exception e) {System.out.println("Error loading image OML");}

        try {runningleftrightSprite = ImageIO.read(Pictures.load("OrphanRunningleftright.png"));}
        catch(Exception e) {System.out.println("Error loading image ORLR");}

        try {runningleftleftSprite = ImageIO.read(Pictures.load("OrphanRunningleftleft.png"));}
        catch(Exception e) {System.out.println("Error loading image ORLL");}

        //_______________________________________________
        //up
        try {upSprite = ImageIO.read(Pictures.load("OrphanMainback.png"));}
        catch(Exception e) {System.out.println("Error loading image OMB");}

        try {runninguprightSprite = ImageIO.read(Pictures.load("OrphanRunningupright.png"));}
        catch(Exception e) {System.out.println("Error loading image ORUR");}

        try {runningupleftSprite = ImageIO.read(Pictures.load("OrphanRunningupleft.png"));}
        catch(Exception e) {System.out.println("Error loading image  ORUL");}

        //______________________________________________
        //down
        try {downSprite = ImageIO.read(Pictures.load("OrphanMain.png"));}
        catch(Exception e) {System.out.println("Error loading image  OM");}

        try {runningdownrightSprite = ImageIO.read(Pictures.load("OrphanRunningdownright.png"));}
        catch(Exception e) {System.out.println("Error loading image ORDR");}

        try {runningdownleftSprite = ImageIO.read(Pictures.load("OrphanRunningdownleft.png"));}
        catch(Exception e) {System.out.println("Error loading image ");}

        //_______________________________________________
        //right
        try {rightSprite = ImageIO.read(Pictures.load("OrphanMainright.png"));}
        catch(Exception e) {System.out.println("Error loading image OMR");}

        try {runningrightrightSprite = ImageIO.read(Pictures.load("OrphanRunningrightright.png"));}
        catch(Exception e) {System.out.println("Error loading image ORRR");}

        try {runningrightleftSprite = ImageIO.read(Pictures.load("OrphanRunningrightleft.png"));}
        catch(Exception e) {System.out.println("Error loading image ORRL");}

        //Girls
        try {GleftSprite = ImageIO.read(Pictures.load("GirlOrphanMainleft.png"));}
        catch(Exception e) {System.out.println("Error loading image GOML");}

        try {GrunningleftrightSprite = ImageIO.read(Pictures.load("GirlOrphanRunningleftright.png"));}
        catch(Exception e) {System.out.println("Error loading image GORLR");}

        try {GrunningleftleftSprite = ImageIO.read(Pictures.load("GirlOrphanRunningleftleft.png"));}
        catch(Exception e) {System.out.println("Error loading image GORLL");}

        //_______________________________________________
        //up
        try {GupSprite = ImageIO.read(Pictures.load("GirlOrphanMainback.png"));}
        catch(Exception e) {System.out.println("Error loading image GOMB");}

        try {GrunninguprightSprite = ImageIO.read(Pictures.load("GirlOrphanRunningupright.png"));}
        catch(Exception e) {System.out.println("Error loading image GORUR");}

        try {GrunningupleftSprite = ImageIO.read(Pictures.load("GirlOrphanRunningupleft.png"));}
        catch(Exception e) {System.out.println("Error loading image  GORUL");}

        //______________________________________________
        //down
        try {GdownSprite = ImageIO.read(Pictures.load("GirlOrphanMain.png"));}
        catch(Exception e) {System.out.println("Error loading image  GOM");}

        try {GrunningdownrightSprite = ImageIO.read(Pictures.load("GirlOrphanRunningdownright.png"));}
        catch(Exception e) {System.out.println("Error loading image GORDR");}

        try {GrunningdownleftSprite = ImageIO.read(Pictures.load("GirlOrphanRunningdownleft.png"));}
        catch(Exception e) {System.out.println("Error loading image GORDL");}

        //_______________________________________________
        //right
        try {GrightSprite = ImageIO.read(Pictures.load("GirlOrphanMainright.png"));}
        catch(Exception e) {System.out.println("Error loading image GOMR");}

        try {GrunningrightrightSprite = ImageIO.read(Pictures.load("GirlOrphanRunningrightright.png"));}
        catch(Exception e) {System.out.println("Error loading image GORRR");}

        try {GrunningrightleftSprite = ImageIO.read(Pictures.load("GirlOrphanRunningrightleft.png"));}
        catch(Exception e) {System.out.println("Error loading image GORRL");}

        //Map
        //try {Background = ImageIO.read(Pictures.load("OrphanageBackground.png"));}
        //catch(Exception e) {System.out.println("Error loading image The Map");}

    }

    public void Move()
    {
        footKeeper++;
        footKeeper%=4;//dweadwdawd
    }

    public BufferedImage getSprite(Direction d, Gender g)
    {
        if(g==Gender.Male)//Boy
        {
            switch(d)//looks at the direction the sprite faces
            {
                case Up:
                switch(footKeeper)//looks at the position the sprite is in
                {
                    case 0:
                    return upSprite;
                    case 1:
                    return runninguprightSprite;
                    case 2:
                    return upSprite;
                    case 3:
                    return runningupleftSprite;
                }
                break;
                case Down:
                switch(footKeeper)//looks at the position the sprite is in
                {
                    case 0:
                    return downSprite;
                    case 1:
                    return runningdownrightSprite;
                    case 2:
                    return downSprite;
                    case 3:
                    return runningdownleftSprite;
                }
                case Left:
                switch(footKeeper)//looks at the position the sprite is in
                {
                    case 0:
                    return leftSprite;
                    case 1:
                    return runningleftrightSprite;
                    case 2:
                    return leftSprite;
                    case 3:
                    return runningleftleftSprite;
                }
                case Right:
                switch(footKeeper)//looks at the position the sprite is in
                {
                    case 0:
                    return rightSprite;
                    case 1:
                    return runningrightrightSprite;
                    case 2:
                    return rightSprite;
                    case 3:
                    return runningrightleftSprite;
                }
            }
        }
        else//Girl
        {
            switch(d)//looks at the direction the sprite faces
            {
                case Up:
                switch(footKeeper)//looks at the position the sprite is in
                {
                    case 0:
                    return GupSprite;
                    case 1:
                    return GrunninguprightSprite;
                    case 2:
                    return GupSprite;
                    case 3:
                    return GrunningupleftSprite;
                }
                break;
                case Down:
                switch(footKeeper)//looks at the position the sprite is in
                {
                    case 0:
                    return GdownSprite;
                    case 1:
                    return GrunningdownrightSprite;
                    case 2:
                    return GdownSprite;
                    case 3:
                    return GrunningdownleftSprite;
                }
                case Left:
                switch(footKeeper)//looks at the position the sprite is in
                {
                    case 0:
                    return GleftSprite;
                    case 1:
                    return GrunningleftrightSprite;
                    case 2:
                    return GleftSprite;
                    case 3:
                    return GrunningleftleftSprite;
                }
                case Right:
                switch(footKeeper)//looks at the position the sprite is in
                {
                    case 0:
                    return GrightSprite;
                    case 1:
                    return GrunningrightrightSprite;
                    case 2:
                    return GrightSprite;
                    case 3:
                    return GrunningrightleftSprite;
                }
            }
        }
        System.out.println("something is wrong with the sprite rotation class method getSprite");
        return null;
    }

}
