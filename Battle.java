
import java.awt.Graphics;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

import java.awt.*;
public class Battle
{
    private int selectorBox;
    private int curentMenu;
    private boolean gameOn;
    private Programmer computer;

    static BufferedImage fightScreen;

    public Battle()
    {
        gameOn = false;
        curentMenu = 0;
        selectorBox = 0;
        try
        {
            fightScreen = ImageIO.read(Pictures.load("battleScreen.png"));
        }
        catch (Exception e)
        {
            System.out.println("File not Found");
        }
    }

    public void endBattle()
    {
        gameOn = false;
    }

    public void casualEncounter()
    {
        WildProgrammer madTemp = null;
        switch((int)(6*Math.random()))
        {
            case 0:
            madTemp = WildProgrammer.Henry;
            break;
            case 1:
            madTemp = WildProgrammer.Mark;
            break;
            case 2:
            madTemp = WildProgrammer.Rakesh;
            break;
            case 3:
            madTemp = WildProgrammer.Max;
            break;
            case 4:
            madTemp = WildProgrammer.Ricky;
            break;
            case 5:
            madTemp = WildProgrammer.Fisher;
        }

        gameOn = true;
        if(computer==null)
        {
            computer = new Programmer(madTemp,2);
        }
    }

    public void updateCurentMenu(int i)
    {
        curentMenu = i;
    }

    public boolean getState()
    {
        return gameOn;
    }

    public void deSelected()
    {
        curentMenu = 0;
    }

    public void setSelectorBox(int x)
    {
        selectorBox = x;
    }

    public int getSelectorBox()
    {
        return selectorBox;
    }

    public void SelectorActivated(Programmer playersFirst,Player player)
    {
        if(gameOn)
        {
            if(curentMenu==0)
            {
                if (selectorBox == 0)
                {
                    curentMenu = 1;
                }
                else if(selectorBox == 1)
                {
                    curentMenu = 2;
                }
                else if(selectorBox == 2)
                {
                    curentMenu = 3;
                }
                else if(selectorBox == 3)
                {
                    computer=null;
                    gameOn= false;
                    curentMenu = 0;
                    selectorBox = 0;
                }
            }
            else if(curentMenu == 1)
            {
                playersFirst.getMoves().get(selectorBox).use();
                playersFirst.healSelf(playersFirst.getMoves().get(selectorBox));
                computer.takeHit(playersFirst.getDamageDone(playersFirst.getMoves().get(selectorBox)));
                if(!computer.isAwake())
                {
                    computer = null;
                    gameOn=false;
                }
                curentMenu = 4;
            }
            else if(curentMenu == 2)
            {
                if(selectorBox==0)
                {
                    player.catchProgrammer(computer);
                    computer=null;
                    gameOn= false;
                    curentMenu = 0;
                    selectorBox = 0;
                }
            }
            else if(curentMenu== 3)
            {
                if(player.getProGrammer(selectorBox).isAwake())
                {
                    player.swapGrammer(selectorBox);

                    curentMenu= 4;
                }
            }
            else if (curentMenu == 4)
            {
                CPUTurn(playersFirst);
                curentMenu= 0;
            }
        }
    }

    public void drawGame(Graphics g, Programmer p,Player cha)
    {
        fighterLoaderSetUp(g,p);
        loadHealthBar(g,p);
        drawSelectorBox(g);
        if(curentMenu == 0)
        {
            startMenu(g);
        }
        else if(curentMenu == 1)
        {
            moveMenu(g,p);
        }
        else if(curentMenu == 2)
        {
            itemMenu(g);
        }
        else if(curentMenu == 3)
        {
            switchMenu(g, cha);
        }
        else
        {
            g.drawString("Opponent is making their move", 100, 366);
        }

    }

    public void switchMenu(Graphics g, Player p)
    {
        g.setColor(Color.BLACK);
        g.drawString(p.topRight(), 44, 326);
        g.drawString(p.bottomRight(), 44, 365);
        g.drawString(p.topLeft(), 243, 326);
        g.drawString(p.bottomLeft(), 243, 365);
    }

    public void drawSelectorBox(Graphics g)
    {
        g.setColor(Color.YELLOW);
        if(selectorBox==0)
        {
            g.fillRect(44, 308, 50,30);
        }
        else if(selectorBox==1)
        {
            g.fillRect(44, 347, 50,30);
        }
        else if(selectorBox==2)
        {
            g.fillRect(243, 308, 50,30);
        }
        else
        {
            g.fillRect(243, 347, 50,30);
        }
    }

    public void itemMenu(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.drawString("Idea", 44, 326);
        g.drawString("White Board", 44, 365);
        g.drawString("Caffeine", 243, 326);
        g.drawString("Power Cord", 243, 365);
    }

    public void moveMenu(Graphics g, Programmer defender)
    {
        g.setColor(Color.BLACK);
        g.drawString(defender.getMoves().get(0).getName(), 44, 326);//top right
        g.drawString(defender.getMoves().get(1).getName(), 44, 365); //bottom right
        g.drawString(defender.getMoves().get(2).getName(), 243, 326);//top left
        g.drawString(defender.getMoves().get(3).getName(), 243, 365);//bottom left
    }

    public void startMenu(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.drawString("Move", 44, 326);
        g.drawString("Bag", 44, 365); 
        g.drawString("Switch" , 243, 326);
        g.drawString("Run", 243, 365);
    }

    public void fighterLoaderSetUp(Graphics g, Programmer defender)
    {

        g.drawImage(fightScreen, 0, 0, null);
        g.drawImage(defender.getBack() , 54, 153, null);
        g.drawImage(computer.getFront() , 245, 36, null);
        g.setColor(Color.BLACK);
        g.setFont(g.getFont().deriveFont(20f));

        g.setColor(Color.BLACK);
        g.drawString(computer.getName(), 45, 73);//displays the name
        g.drawRect(45, 73+10,64,6);//border of status bar
        g.drawString(defender.getName(), 254, 240);//displays the name
        g.drawRect(254, 240+10,64,6);

    }

    public void loadHealthBar(Graphics g, Programmer defender)
    {
        if((int)(64*computer.getHealthPercent()) >= 32)
        {
            g.setColor(Color.GREEN);
        }
        if((int)(64*computer.getHealthPercent()) < 32)
        {
            g.setColor(Color.ORANGE);
        }
        if((int)(64*computer.getHealthPercent()) < 16)
        {
            g.setColor(Color.RED);
        }

        //g.setColor(Color.ORANGE);
        g.fillRect(46,74 + 10, (int)(64*computer.getHealthPercent()) ,4);

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

    }

    public void CPUTurn(Programmer defender)
    {
        computer.getMoves().get((int)(Math.random()*4)).use();
        computer.healSelf(computer.getMoves().get((int)(Math.random()*4)));
        defender.takeHit(computer.getDamageDone(computer.getMoves().get((int)(Math.random()*4))));
    }

}
