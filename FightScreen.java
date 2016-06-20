import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class FightScreen extends JComponent
{
    static FightScreen start = new FightScreen();
    static Programmer defender = new Programmer(WildProgrammer.Henry, 1);
    static Programmer attacker = new Programmer(WildProgrammer.Henry, 1);
    static BufferedImage fightScreen;
    static JFrame frame = new JFrame();
    static int count = 0;
    static int x = 0;
    static int y = 0;
    static boolean topRight = false;
    static boolean downRight = false;
    static boolean topLeft = false;
    static boolean downLeft = false;
    public static void main(String[] args)
    {
        try
        {
            fightScreen = ImageIO.read(new File("battleScreen.png"));
        }
        catch (Exception e)
        {
            System.out.println("File not Found");
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(start);
        frame.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e)
                {
                    x = e.getX();
                    y = e.getY();
                    //System.out.println("X: " + x + " Y: " + y);
                    if((x >= 22 && x <= 200)&& (y >= 328 && y <= 365))
                    {
                        topRight = true;
                        downRight = false;
                        topLeft = false;
                        downLeft = false;
                        count +=1;
                        //System.out.println("RightTure");
                    }
                    if((x >= 22 && x <= 200) && (y > 365 && y <= 408))
                    {
                        topRight = false;
                        downRight = true;
                        topLeft = false;
                        downLeft = false;
                        count +=1;
                    }
                    if((x > 200 && x <= 390) && (y >= 328 && y <= 365))
                    {
                        topRight = false;
                        downRight = false;
                        topLeft = true;
                        downLeft = false;
                        count +=1;
                    }
                    if((x > 200 && x <= 390) && (y > 365 && y <= 408))
                    {
                        topRight = false;
                        downRight = false;
                        topLeft = false;
                        downLeft = true;
                        count += 1;
                    }
                    frame.repaint();
                }
            });
        frame.addKeyListener(new KeyAdapter(){
                public void keyPressed(KeyEvent e)
                {
                    Integer key = e.getKeyCode();
                    if(key == KeyEvent.VK_B)
                    {
                        if(count != 0)
                        {
                            count -= 1;
                        }
                    }
                    frame.repaint();
                }

                public void keyReleased(KeyEvent e)
                {
                    Integer key = e.getKeyCode();
                    if(key == KeyEvent.VK_B)
                    {

                    }
                }

                public void keyTyped(KeyEvent e)
                {

                }
            }); 

        frame.setVisible(true);
        frame.setSize(415, 438);
        frame.repaint();
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

    public void paintComponent(Graphics g)
    {
        g.drawImage(fightScreen, 0, 0, null);
        g.drawImage(attacker.getBack() , 54, 153, null);
        g.drawImage(defender.getFront() , 245, 36, null);
        g.setColor(Color.BLACK);
        g.setFont(g.getFont().deriveFont(20f));

        g.setColor(Color.BLACK);
        g.drawString("DefenderName", 45, 73);//displays the name
        g.drawRect(45, 73+10,64,6);//border of status bar
        g.drawString("FighterName", 254, 240);//displays the name
        g.drawRect(254, 240+10,64,6);

        if((int)(64*attacker.getHealthPercent()) >= 32)
        {
            g.setColor(Color.GREEN);
        }
        if((int)(64*attacker.getHealthPercent()) < 32)
        {
            g.setColor(Color.ORANGE);
        }
        if((int)(64*attacker.getHealthPercent()) < 16)
        {
            g.setColor(Color.RED);
        }

        //g.setColor(Color.ORANGE);
        g.fillRect(46,74 + 10, (int)(64*attacker.getHealthPercent()) ,4);

        if((int)(64*defender.getHealthPercent()) >= 32)
        {
            g.setColor(Color.GREEN);
        }
        if((int)(64*defender.getHealthPercent()) < 32)
        {
            g.setColor(Color.ORANGE);
        }
        if((int)(64*defender.getHealthPercent()) < 16)
        {
            g.setColor(Color.RED);
        }
        g.fillRect(255, 241 + 10, (int)(64*defender.getHealthPercent()), 4);
        //Control which statments to draw with if statements

        g.setColor(Color.BLACK);
        if(count == 0)
        {
            g.drawString("Move", 44, 326);
            g.drawString("Bag", 44, 365); 
            g.drawString("Switch" , 243, 326);
            g.drawString("Run", 243, 365);
        }
        if(count == 1 && topRight == true)
        {
            g.drawString(defender.getMoves().get(0).getName(), 44, 326);
            g.drawString(defender.getMoves().get(1).getName(), 44, 365);
            g.drawString(defender.getMoves().get(2).getName(), 243, 326);
            g.drawString(defender.getMoves().get(3).getName(), 243, 365);
        }
        if(count == 1 && downRight == true)
        {
            g.drawString("Caffeine", 44, 326);
            g.drawString("White Board", 44, 365);
            g.drawString("Idea", 243, 326);
            g.drawString("Power Cord", 243, 365);
        }
        if(count == 1 && topLeft == true)
        {
            g.drawString("Current Programmer", 44, 326);
            g.drawString("Programmer 2", 44, 365);
            g.drawString("Programmer 3", 243, 326);
            g.drawString("Programmer 4", 243, 365);
        }
        if(count == 2 && topRight == true)
        {
            if(!defender.getMoves().get(0).canUse())
            {
                if(count != 0)
                {
                    count -=1;
                }
            }
            else
            {
                defender.getMoves().get(0).use();
                defender.healSelf(defender.getMoves().get(0));
                attacker.takeHit(defender.getDamageDone(defender.getMoves().get(0)));
                //count = 3;

            }
        }
        if(count == 2 && downRight == true)
        {
            if(!defender.getMoves().get(1).canUse())
            {
                if(count != 0)
                {
                    count -=1;
                }
            }
            else
            {
                
            }
        }
        if(count == 2 && topLeft == true)
        {
            if(!defender.getMoves().get(2).canUse())
            {
                if(count != 0)
                {
                    count -=1;
                }
            }
            else
            {
                defender.getMoves().get(2).use();
                defender.healSelf(defender.getMoves().get(2));
                attacker.takeHit(defender.getDamageDone(defender.getMoves().get(2)));
                count = 3;

            }
        }
        if(count == 2 && downLeft == true)
        {
            if(!defender.getMoves().get(3).canUse())
            {
                if(count != 0)
                {
                    count -=1;
                }
            }
            else
            {
                defender.getMoves().get(3).use();
                defender.healSelf(defender.getMoves().get(3));
                attacker.takeHit(defender.getDamageDone(defender.getMoves().get(3)));

                //frame.repaint();
                //count = 3;

            }
        }
        if(count == 3)
        {
            g.drawString("Opponent is making their move", 100, 366);

            g.drawImage(fightScreen, 0, 0, null);
            g.drawImage(attacker.getBack() , 54, 153, null);
            g.drawImage(defender.getFront() , 245, 36, null);
            g.setColor(Color.BLACK);
            g.setFont(g.getFont().deriveFont(20f));

            g.setColor(Color.BLACK);
            g.drawString("DefenderName", 45, 73);//displays the name
            g.drawRect(45, 73+10,64,6);//border of status bar
            g.drawString("FighterName", 254, 240);//displays the name
            g.drawRect(254, 240+10,64,6);

            if((int)(64*attacker.getHealthPercent()) >= 32)
            {
                g.setColor(Color.GREEN);
            }
            if((int)(64*attacker.getHealthPercent()) < 32)
            {
                g.setColor(Color.ORANGE);
            }
            if((int)(64*attacker.getHealthPercent()) < 16)
            {
                g.setColor(Color.RED);
            }

            //g.setColor(Color.ORANGE);
            g.fillRect(46,74 + 10, (int)(64*attacker.getHealthPercent()) ,4);

            if((int)(64*defender.getHealthPercent()) >= 32)
            {
                g.setColor(Color.GREEN);
            }
            if((int)(64*defender.getHealthPercent()) < 32)
            {
                g.setColor(Color.ORANGE);
            }
            if((int)(64*defender.getHealthPercent()) < 16)
            {
                g.setColor(Color.RED);
            }
            g.fillRect(255, 241 + 10, (int)(64*defender.getHealthPercent()), 4);

            try
            {
                Thread.sleep(1000);
            }
            catch (Exception k)
            {

            }

            int tempAttack= (int)(Math.random()*4);
            if(!defender.getMoves().get(tempAttack).canUse())
            {
                //attackerTurn(g);
            }
            else
            {
                attacker.getMoves().get(0).use();
                attacker.healSelf(attacker.getMoves().get(0));
                defender.takeHit(attacker.getDamageDone(attacker.getMoves().get(0)));

            }

            count = 0;
        }
    }

}