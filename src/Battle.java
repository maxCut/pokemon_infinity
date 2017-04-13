import java.awt.Graphics;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

import java.awt.*;
public class Battle
{
    private int selectorBox;
    private int currentMenu;
    private boolean gameOn;
    private Pokemon computer;

    static BufferedImage fightScreen;

    public int getCurrentMenu()
    {
        return currentMenu;
    }
    public Battle()
    {
        gameOn = false;
        currentMenu = 0;
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
        WildPokemon madTemp = null;
        switch((int)(7*Math.random()))
        {
            case 0:
            madTemp = WildPokemon.Henry;
            break;
            case 1:
            madTemp = WildPokemon.Mark;
            break;
            case 2:
            madTemp = WildPokemon.Rakesh;
            break;
            case 3:
            madTemp = WildPokemon.Max;
            break;
            case 4:
            madTemp = WildPokemon.Ricky;
            break;
            case 5:
            madTemp = WildPokemon.Fisher;
            case 6:
            madTemp = WildPokemon.QuirkTwins;
        }

        gameOn = true;
        if(computer==null)
        {
            computer = new Pokemon(madTemp,2);
        }
    }

    public void updateCurentMenu(int i)
    {
        currentMenu = i;
    }

    public boolean getState()
    {
        return gameOn;
    }

    public void deSelected()
    {
        currentMenu = 0;
    }

    public void setSelectorBox(int x)
    {
        selectorBox = x;
    }

    public int getSelectorBox()
    {
        return selectorBox;
    }

    public void SelectorActivated(Pokemon playersFirst,Player player)
    {
        if(gameOn)
        {
            if(currentMenu==0)
            {
                if (selectorBox == 0)
                {
                    currentMenu = 1;
                }
                else if(selectorBox == 1)
                {
                    currentMenu = 2;
                }
                else if(selectorBox == 2)
                {
                    currentMenu = 3;
                }
                else if(selectorBox == 3)
                {
                    computer=null;
                    gameOn= false;
                    currentMenu = 0;
                    selectorBox = 0;
                }
            }
            else if(currentMenu == 1)
            {
                playersFirst.getMoves().get(selectorBox).use();
                playersFirst.healSelf(playersFirst.getMoves().get(selectorBox));
                computer.takeHit(playersFirst.getDamageDone(playersFirst.getMoves().get(selectorBox)));
                if(!computer.isAwake())
                {
                    computer = null;
                    gameOn=false;
                }
                currentMenu = 4;
            }
            else if(currentMenu == 2)
            {
                if(selectorBox==0)//catch them
                {
                    player.catchPokemon(computer);
                    computer=null;
                    gameOn= false;
                    currentMenu = 0;
                    selectorBox = 0;
                }
                else if (selectorBox==2)//heal
                {
                    playersFirst.fullHeal();
                    currentMenu=4;
                }
            }
            else if(currentMenu== 3)
            {
                if(player.getProGrammer(selectorBox).isAwake())
                {
                    player.swapGrammer(selectorBox);
                    currentMenu= 4;
                }
            }
            else if (currentMenu == 4)
            {
                CPUTurn(playersFirst);
                currentMenu= 0;
            }
        }
    }

    public void drawGame(Graphics g, Pokemon p,Player cha)
    {
        fighterLoaderSetUp(g,p);
        loadHealthBar(g,p);
        drawSelectorBox(g);
        if(currentMenu == 0)
        {
            startMenu(g);
        }
        else if(currentMenu == 1)
        {
            moveMenu(g,p);
        }
        else if(currentMenu == 2)
        {
            itemMenu(g);
        }
        else if(currentMenu == 3)
        {
            switchMenu(g, cha);
        }
        else
        {
            g.drawString("Opponent is making their move", (int)(100*World.SCALE),(int)(366*World.SCALE));
        }

    }

    public void switchMenu(Graphics g, Player p)
    {
        g.setColor(Color.BLACK);
        g.drawString(p.topRight(), (int)(44*World.SCALE), (int)(326*World.SCALE));//top right
        g.drawString(p.bottomRight(), (int)(44*World.SCALE), (int)(365*World.SCALE));//bottom right
        g.drawString(p.topLeft(), (int)(243*World.SCALE), (int)(326*World.SCALE));//top left
        g.drawString(p.bottomLeft(), (int)(243*World.SCALE), (int)(365*World.SCALE));//bottom left
    }

    public void drawSelectorBox(Graphics g)
    {
        g.setColor(Color.YELLOW);
        if(selectorBox==0)
        {
            g.fillRect((int)(44*World.SCALE),(int)(308*World.SCALE),(int)(50*World.SCALE),(int)(30*World.SCALE));
        }
        else if(selectorBox==1)
        {
            g.fillRect((int)(44*World.SCALE),(int)(347*World.SCALE),(int)(50*World.SCALE),(int)(30*World.SCALE));
        }
        else if(selectorBox==2)
        {
            g.fillRect((int)(243*World.SCALE),(int)(308*World.SCALE),(int)(50*World.SCALE),(int)(30*World.SCALE));
        }
        else
        {
            g.fillRect((int)(243*World.SCALE),(int)(347*World.SCALE),(int)(50*World.SCALE),(int)(30*World.SCALE));
        }
    }

    public void itemMenu(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.drawString("Capture_Ball", (int)(44*World.SCALE), (int)(326*World.SCALE));
        g.drawString("FullHealPotion", (int)(243*World.SCALE), (int)(326*World.SCALE));
    }

    public void moveMenu(Graphics g, Pokemon defender)
    {
        g.setColor(Color.BLACK);
        g.drawString(defender.getMoves().get(0).getName(), (int)(44*World.SCALE), (int)(326*World.SCALE));//top right
        g.drawString(defender.getMoves().get(1).getName(), (int)(44*World.SCALE), (int)(365*World.SCALE));//bottom right
        g.drawString(defender.getMoves().get(2).getName(), (int)(243*World.SCALE), (int)(326*World.SCALE));//top left
        g.drawString(defender.getMoves().get(3).getName(), (int)(243*World.SCALE), (int)(365*World.SCALE));//bottom left
    }

    public void startMenu(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.drawString("Move", (int)(44*World.SCALE), (int)(326*World.SCALE));
        g.drawString("Bag", (int)(44*World.SCALE), (int)(365*World.SCALE));
        g.drawString("Switch", (int)(243*World.SCALE), (int)(326*World.SCALE));
        g.drawString("Run", (int)(243*World.SCALE), (int)(365*World.SCALE));
    }

    public void fighterLoaderSetUp(Graphics g, Pokemon defender)
    {

        g.drawImage(fightScreen, 0, 0,(int)(400*World.SCALE),(int)(400*World.SCALE),null);
        g.drawImage(defender.getBack() , (int)(54*World.SCALE), (int)(153*World.SCALE),(int)(100*World.SCALE),(int)(133*World.SCALE), null);
        g.drawImage(computer.getFront() , (int)(245*World.SCALE), (int)(36*World.SCALE),(int)(100*World.SCALE),(int)(133*World.SCALE), null);
        g.setColor(Color.BLACK);
        g.setFont(g.getFont().deriveFont(20f));

        g.setColor(Color.BLACK);
        g.drawString(computer.getName(),(int)(45*World.SCALE), (int)(73*World.SCALE));//displays the name
        g.drawRect((int)(45*World.SCALE),(int)(83*World.SCALE),(int)(64*World.SCALE),(int)(6*World.SCALE));//border of status bar
        g.drawString(defender.getName(), (int)(254*World.SCALE), (int)(240*World.SCALE));//displays the name
        g.drawRect((int)(254*World.SCALE),(int)(250*World.SCALE),(int)(64*World.SCALE),(int)(6*World.SCALE));//border of status bar
    }

    public void fighterLoaderLite(Graphics g,Pokemon defender)//draws everything but the fighters
    {
        g.drawImage(fightScreen, 0, 0,null);
        g.setColor(Color.BLACK);
        g.setFont(g.getFont().deriveFont(20f));

        g.setColor(Color.BLACK);
        g.drawString(computer.getName(),(int)(45*World.SCALE), (int)(73*World.SCALE));//displays the name
        g.drawRect((int)(45*World.SCALE),(int)(83*World.SCALE),(int)(64*World.SCALE),(int)(6*World.SCALE));//border of status bar
        g.drawString(defender.getName(), (int)(254*World.SCALE), (int)(240*World.SCALE));//displays the name
        g.drawRect((int)(254*World.SCALE),(int)(250*World.SCALE),(int)(64*World.SCALE),(int)(6*World.SCALE));//border of status bar


    }

    public void loadHealthBar(Graphics g, Pokemon defender)
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
        g.fillRect((int)(46*World.SCALE),(int)(84*World.SCALE),(int)(64*computer.getHealthPercent()*World.SCALE) ,(int)(4*World.SCALE));

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
        g.fillRect((int)(255*World.SCALE),(int)(251*World.SCALE), (int)(64*defender.getHealthPercent()*World.SCALE), (int)(4*World.SCALE));
        //Control which statments to draw with if statements

    }

    public void CPUTurn(Pokemon defender)
    {
        computer.getMoves().get((int)(Math.random()*4)).use();
        computer.healSelf(computer.getMoves().get((int)(Math.random()*4)));
        defender.takeHit(computer.getDamageDone(computer.getMoves().get((int)(Math.random()*4))));
    }

    public Pokemon getComputer()
    {
        return computer;
    }

}
