
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
/**
 * Enumeration class Difrent - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum WildPokemon
{
    Henry("Henry",100, 300, "HenryFront.png", "HenryBack.png" , Type.Python, 
    Move.NodeJs, Move.Nap, Move.Procrasitinate, Move.SingAlong ),
    Mark("Mark", 50, 400, "MarcFront.png", "MarcBack.png", Type.Construct2, 
    Move.MakeWaffles, Move.JavaDocs, Move.Bite, Move.SingAlong ),
    Rakesh("Rakesh", 80, 330, "RakeshFront.png", "RakeshBack.png", Type.AndroidDevelopment,
    Move.Bite, Move.Procrasitinate, Move.StealChair, Move.CompileTimeError),
    Max("Max", 70, 345, "MaxFront.png", "MaxBack.png", Type.Java,
    Move.WKP, Move.nullPointerException, Move.SingAlong, Move.CompileTimeError),
    Ricky("Ricky", 66, 301, "RickyFront.png", "RickyBack.png", Type.Python,
    Move.StealChair, Move.Procrasitinate, Move.Bite, Move.SingAlong),
    Fisher("Fisher", 90, 290, "FisherFront.png", "FisherBack.png", Type.C,
    Move.Nap, Move.Bite, Move.SingAlong, Move.nullPointerException);
    
    final String NAME;
    final int BRAINPOWER;
    final int ALERTNESS;
    final String FRONTFILENAME;
    final String BACKFILENAME;
    final Type PROGRAMMERTYPE;
    final Move M1;
    final Move M2;
    final Move M3;
    final Move M4;
    
    WildPokemon(String n, int bP, int a, String frontFN, String backFN, Type t,
    Move m1, Move m2, Move m3, Move m4)
    {
        M1 = m1;
        M2 = m2;
        M3 = m3;
        M4 = m4;
        NAME = n;
        BRAINPOWER = bP;
        ALERTNESS = a;
        FRONTFILENAME = frontFN;
        BACKFILENAME = backFN;
        PROGRAMMERTYPE = t;
    }
    
    String getName()
    {
        return NAME;
    }
    
    BufferedImage getFront()
    {
        BufferedImage temp = null;
        try {temp = ImageIO.read(Pictures.load(FRONTFILENAME));}
        catch(Exception e) {System.out.println("Error loading image " + FRONTFILENAME);}
        return temp;
    }
    
    BufferedImage getBack()
    {
        BufferedImage temp = null;
        try {temp = ImageIO.read(Pictures.load(BACKFILENAME));}
        catch(Exception e) {System.out.println("Error loading image " + BACKFILENAME);}
        return temp;
    }
    
    int getBrainPower()
    {
        return BRAINPOWER;
    }
    
    int getAlertness()
    {
        return ALERTNESS;
    }
    
    Type getType()
    {
        return PROGRAMMERTYPE;
    }
    
    Move m1()
    {
        return M1;
    }
    
    Move m2()
    {
        return M2;
    }
    
    Move m3()
    {
        return M3;
    }
    
    Move m4()
    {
        return M4;
    }
    
    
    
}
