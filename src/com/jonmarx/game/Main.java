/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonmarx.game;

import com.jonmarx.mobs.BasicBehavior;
import com.jonmarx.mobs.Entity;
import com.jonmarx.tiles.BlackBehavior;
import com.jonmarx.tiles.CollisionBehavior;
import com.jonmarx.tiles.CollisionTile;
import com.jonmarx.tiles.ColorBehavior;
import com.jonmarx.tiles.FlashBehavior;
import com.jonmarx.tiles.Tile;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.HashMap;
/**
 *
 * @author Jon
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HashMap<Integer, Tile> map = new HashMap<>();
        map.put(-1, new Tile(new ColorBehavior(Color.GREEN), false));
        map.put(-16777216, new Tile(new ColorBehavior(Color.RED), true));
        map.put(-65536, new CollisionTile(new CollisionBehavior() {
            @Override
            public void collide(Entity e) {
                JOptionPane.showMessageDialog(null, "Congrats, you won!");
                System.exit(0);
            }

            @Override
            public void render(int frame, Graphics g, int x, int y) {
                g.setColor(Color.YELLOW);
                g.fillRect(x * 16, y * 16, 16, 16);
            }

            @Override
            public void update(int frame, int x, int y) {
                
            }
            
        }, false));
        Tile[][] level = LevelLoader.compileLevel(map, LevelLoader.loadLevel("/com/jonmarx/images/levels/Maze.png"));
        
        TileManager.setup(level);
        EntityManager.addEntity(new Entity(15, 15, new BasicBehavior()));
        
        JFrame frame = new JFrame();
        frame.setSize(16 * 33, 16 * 35 - 9);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(new GamePanel());
        Timer t = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.repaint();
            }
        });
        t.start();
        
        Timer ti = new Timer(5, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                for(Entity e : EntityManager.getEntities()) {
                    try {
                        ((CollisionTile) TileManager.getTile(e.getLocation()[0] + xpush, e.getLocation()[1] + ypush)).collide(e);
                    } catch (Exception ex) {
                        continue;
                    }
                }
                Entity en = EntityManager.getEntities()[0];
                if(KeyboardListener.w) {
                    if(TileManager.getTile(xpush + en.getLocation()[0], ypush + en.getLocation()[1] - 1).isSolid()) {
                        KeyboardListener.w = false;
                        return;
                    }
                    if(ypush < 1 || en.getLocation()[1] > 16) {
                        en.setLocation(en.getLocation()[0], en.getLocation()[1] - 1);
                    } else {
                        ypush--;
                    }
                    if(en.getLocation()[1] < 0) {
                        en.setLocation(en.getLocation()[0], 0);
                    }
                    KeyboardListener.w = false;
                }
                if(KeyboardListener.a) {
                    if(TileManager.getTile(xpush + en.getLocation()[0] - 1, ypush + en.getLocation()[1]).isSolid()) {
                        KeyboardListener.a = false;
                        return;
                    }
                    if(xpush < 1 || en.getLocation()[0] > 16) {
                        en.setLocation(en.getLocation()[0] - 1, en.getLocation()[1]);
                    } else {
                        xpush--;
                    }
                    if(en.getLocation()[0] < 0) {
                        en.setLocation(0, en.getLocation()[1]);
                    }
                    KeyboardListener.a = false;
                }
                if(KeyboardListener.s) {
                    if(TileManager.getTile(xpush + en.getLocation()[0], ypush + en.getLocation()[1] + 1).isSolid()) {
                        KeyboardListener.s = false;
                        return;
                    }
                    if(ypush > level[0].length -33 || en.getLocation()[1] < 14) {
                        en.setLocation(en.getLocation()[0], en.getLocation()[1] + 1);
                    } else {
                        ypush++;
                    }
                    if(en.getLocation()[1] > 31) {
                        en.setLocation(en.getLocation()[0], 31);
                    }
                    KeyboardListener.s = false;
                }
                if(KeyboardListener.d) {
                    if(TileManager.getTile(xpush + en.getLocation()[0] + 1, ypush + en.getLocation()[1]).isSolid()) {
                        KeyboardListener.d = false;
                        return;
                    }
                    if(xpush > level.length -33 || en.getLocation()[0] < 14) {
                        en.setLocation(en.getLocation()[0] + 1, en.getLocation()[1]);
                    } else {
                        xpush++;
                    }
                    if(en.getLocation()[0] > 31) {
                        en.setLocation(31, en.getLocation()[1]);
                    }
                    KeyboardListener.d = false;
                }
            }
        });
        ti.start();
        frame.addKeyListener(new KeyboardListener());
    }
    
    static int xpush = 64 - 15;
    static int ypush = 64 - 16;
}

class GamePanel extends JPanel {
    int frame = 0;
    @Override
    public void paintComponent(Graphics g) {
        frame++;
        super.paintComponent(g);
        com.jonmarx.gfx.Screen.drawScreen(g, TileManager.getTiles(Main.xpush, Main.ypush, 32, 32), EntityManager.getEntities(), frame);
    }
}

class KeyboardListener implements KeyListener {
    public static boolean w = false;
    public static boolean a = false;
    public static boolean s = false;
    public static boolean d = false;
            
    @Override
    public void keyTyped(KeyEvent e) {
        return;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                w = true;
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                a = true;
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                s = true;
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                d = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        return;
    }
    
}
