import javax.swing.JFrame;
import javax.swing.JComponent;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
public class World extends JComponent
{
        

    static World start = new World();   //this is the recursively defined object of the game itself. 
    static Map<tileType, BufferedImage> world = new HashMap<tileType, BufferedImage>();//way of storing image mem adresses
    static BufferedImage tree;//image
    static BufferedImage grass;//image
    static BufferedImage tallGrass;//image
    static JFrame frame = new JFrame();
    static Player Character;//the users player class
    static Battle battleEmulator;//mode that allows user to battle other players
    static Seed worldSeed;
    public static void main(String[] args) throws IOException
    {
        worldSeed = new Seed();
        runGameTextIntro(); // asks user if they are a boy or a girl and takes in the input
        battleEmulator = new Battle();
        loadPictures();// gets the pictures and stores them locally, almost at the point where pictures will be loaded into a jar       

        createMap(); //applies values of world seed to generate a map

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //jframe settings
        frame.add(start);
        frame.setVisible(true);
        frame.setSize(400, 400);

        frame.addKeyListener(new KeyAdapter(){
                public void keyPressed(KeyEvent e)
                {
                    Integer key = e.getKeyCode();
                    if(key == KeyEvent.VK_RIGHT)
                    {
                        if(battleEmulator.getState())
                        {
                            if(battleEmulator.getSelectorBox()<2)
                            {
                                battleEmulator.setSelectorBox(battleEmulator.getSelectorBox()+2);
                            }
                        }
                        else
                        {
                            Character.move(Direction.Right);
                        }

                        if(Character.isEncounter())
                        {
                            battleEmulator.casualEncounter();
                        }
                    }
                    if(key == KeyEvent.VK_LEFT)
                    {
                        if(battleEmulator.getState())
                        {
                            if(battleEmulator.getSelectorBox()>1)
                            {
                                battleEmulator.setSelectorBox(battleEmulator.getSelectorBox()-2);
                            }
                        }
                        else
                        {
                            Character.move(Direction.Left);
                        }

                        if(Character.isEncounter())
                        {
                            battleEmulator.casualEncounter();
                        }
                    }
                    if(key == KeyEvent.VK_UP)
                    {
                        if(battleEmulator.getState())
                        {
                            if(battleEmulator.getSelectorBox()%2!=0)
                            {
                                battleEmulator.setSelectorBox(battleEmulator.getSelectorBox()-1);
                            }
                        }
                        else
                        {
                            Character.move(Direction.Up);
                        }

                        if(Character.isEncounter())
                        {
                            battleEmulator.casualEncounter();
                        }
                    }
                    if(key == KeyEvent.VK_DOWN)
                    {
                        if(battleEmulator.getState())
                        {
                            if(battleEmulator.getSelectorBox()%2==0)
                            {
                                battleEmulator.setSelectorBox(battleEmulator.getSelectorBox()+1);
                            }
                        }
                        else
                        {
                            Character.move(Direction.Down);
                        }

                        if(Character.isEncounter())
                        {
                            battleEmulator.casualEncounter();
                        }
                    }
                    if(key == KeyEvent.VK_A)
                    {

                        if(battleEmulator.getState())
                        {
                            battleEmulator.SelectorActivated(Character.getActivePokemon(),Character);   
                        }

                    }
                    if(key == KeyEvent.VK_B)
                    {

                        if(battleEmulator.getState()&&battleEmulator.getCurrentMenu()!=4) //if its not the opponents turn and you are in a battle it will derefrence the state
                        {
                            battleEmulator.deSelected();   
                        }

                    }

                }

                public void keyReleased(KeyEvent e)
                {
                    Integer key = e.getKeyCode();
                    if(key == KeyEvent.VK_RIGHT){}//right now these do nothing but have potential for key released events in future
                    if(key == KeyEvent.VK_LEFT){}
                    if(key == KeyEvent.VK_UP){}
                    if(key == KeyEvent.VK_DOWN){}
                }

            }); 

        while(true)
        {
            try
            {
                Thread.sleep(10);//gives time aspect so that it doesnt repaint every second
            } 
            catch(Exception e)
            {

            }
            frame.repaint();
        }
    }

    public static void runGameTextIntro()//run in the beggining of the game
    {

        System.out.println("Are you a boy or a girl?");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        if(input.equalsIgnoreCase("boy"))
        {
            Character = new Player(Gender.Male, worldSeed);
        }
        else if(input.equalsIgnoreCase("girl"))
        {
            Character = new Player(Gender.Female, worldSeed);
        }
        else
        {
            System.out.println("invalid statement");
            System.exit(1);
        } 
    }
    
    public static void loadPictures()
    {
        try
        {
            tree = ImageIO.read(Pictures.load("tree.png"));
            grass = ImageIO.read(Pictures.load("grass.png"));
            tallGrass = ImageIO.read(Pictures.load("tallgrass.png"));
        }
        catch (Exception e)
        {
            System.out.println("File not Found");
        }

    }
    public static void createMap()
    {
        world.put(tileType.tree, tree);
        world.put(tileType.tallGrass, tallGrass);
        world.put(tileType.shortGrass, grass);
    }

    public void paintComponent(Graphics g)
    {
        final int tileWidth = 40;
        final int tileHeight = 40;
        final int xShift = 200;//these shift the tiles relative to the player.
        final int yShift = 200;

        final int startBorderX = tileWidth*(-40/tileWidth); //the start border for the x axis of where to start drawing tiles
        final int startBorderY = tileHeight*(0/tileHeight); //the start border for the y axis of where to start drawing tiles     Keep these divisible by tile width and tile height.

        if(!battleEmulator.getState())
        {
            int curTileX = Character.getX() - xShift -tileWidth;
            int curTileY = Character.getY() - yShift - tileHeight;

            int xAcust = Character.getX() % tileWidth; //This adjusts the place the tiles are drawn for when the character does not walk a full tile
            int yAcust = Character.getY() % tileHeight;
    
            //runs through the coordinates of every on screen Tile
            while(curTileY+startBorderY<Character.getY()+yShift + tileHeight)
            {
                g.drawImage(world.get(worldSeed.getTile(curTileX-xAcust+startBorderX,curTileY-yAcust+startBorderY)), curTileX-Character.getX()-xAcust + xShift+startBorderX, curTileY-Character.getY()-yAcust+yShift+startBorderY, null);

                curTileX += tileWidth;
                if(curTileX+startBorderX>Character.getX()+xShift)
                {
                    curTileX = Character.getX() - xShift;
                    curTileY += tileHeight;
                }

            }
            g.drawImage(Character.getPic(),200,200,null);
        }

        if(battleEmulator.getState())
        {
            if(Character.playersRemaining())
            {
                frame.setSize(415, 438);
                battleEmulator.drawGame(g,Character.getActivePokemon(),Character);
            }
            else
            {
                System.exit(1);
            }
        }
        else
        {
            frame.setSize(400, 400);
        }
    }
}
