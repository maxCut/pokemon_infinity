import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.*;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
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
    static World start = new World();
    static Map<tileType, BufferedImage> world = new HashMap<tileType, BufferedImage>();
    static BufferedImage tree;
    static BufferedImage grass;
    static BufferedImage tallGrass;
    static int height =0;
    static int width = 0;
    static int count = 0;
    static JFrame frame = new JFrame();
    static Player Character;
    static Battle battleEmulator;
    static Seed worldSeed;
    public static void main(String[] args) throws IOException
    {
        worldSeed = new Seed();
        runGameTextIntro();
        battleEmulator = new Battle();
        loadPictures();       
                
        
        //TODO remove hash map and replace with procedural generation

        createMap();
        String the = "";


        //ArrayList<String> temp = new ArrayList<String>();
        //the = the.replace("\n", "");

        //         System.out.println(temp);
        //         System.out.println(world.get(temp.get(0)));
        //         for(String n: temp)
        //         {
        //             happy.add(world.get(n));
        //         }
        //System.out.println("Happy: " + happy);

        //frame.setLayout(null);
        //frame. setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //start.setBounds(0, 0, 400, 400);
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
                            battleEmulator.SelectorActivated(Character.getActiveProgrammer(),Character);   
                        }

                    }
                    if(key == KeyEvent.VK_B)
                    {

                        if(battleEmulator.getState())
                        {
                            battleEmulator.deSelected();   
                        }

                    }

                }

                public void keyReleased(KeyEvent e)
                {
                    Integer key = e.getKeyCode();
                    if(key == KeyEvent.VK_RIGHT)
                    {

                    }
                    if(key == KeyEvent.VK_LEFT)
                    {

                    }
                    if(key == KeyEvent.VK_UP)
                    {

                    }
                    if(key == KeyEvent.VK_DOWN)
                    {

                    }
                }

                public void keyTyped(KeyEvent e)
                {

                }
            }); 

        while(true)
        {
            try
            {
                Thread.sleep(10);
            } 
            catch(Exception e)
            {

            }
            frame.repaint();
        }
    }

    public static void runGameTextIntro()
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
        final int xShift = 200;//these shift the tiles onto the screen, because java is weird and 0,0 is not the top left origin
        final int yShift = 200;

        if(!battleEmulator.getState())
        {
            //System.out.println("Happy 2 " + happy);
            //System.out.println(happy.size());
            int curTileX = Character.getX() - xShift -tileWidth;
            int curTileY = Character.getY() - yShift - tileHeight;

            int xAcust = Character.getX() % tileWidth; //This adjusts the place the tiles are drawn for when the character does not walk a full tile
            int yAcust = Character.getY() % tileHeight;
    
            //runs through the coordinates of every on screen Tile
            while(curTileY<Character.getY()+yShift + tileHeight)
            {
                g.drawImage(world.get(worldSeed.getTile(curTileX-xAcust,curTileY-yAcust)), curTileX-Character.getX()-xAcust + xShift, curTileY-Character.getY()-yAcust+yShift, null);
/*
                if(happy.get(i).equals("T"))
                {
                    Character.collisionBox(new Rectangle(width,height+5,40,25));
                }

                if(happy.get(i).equals("G"))
                {
                    Character.shakeyBox(new Rectangle(width,height+5,40,25));
                }
                    TODO replace this comented code with new collision and event boxes*/

                curTileX += tileWidth;
                if(curTileX>Character.getX()+xShift)
                {
                    curTileX = Character.getX() - xShift;
                    curTileY += tileHeight;
                }

                //System.out.println("Working? " + i);
            }
            g.drawImage(Character.getPic(),200,200,null);
            //g.drawImage(world.get("G"), 0, 0, null);
            //g.drawImage(world.get("g"), 0, 0, null);
        }

        if(battleEmulator.getState())
        {
            if(Character.playersRemaining())
            {
                frame.setSize(415, 438);
                battleEmulator.drawGame(g,Character.getActiveProgrammer(),Character);
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
