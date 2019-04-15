/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonmarx.game;

import com.jonmarx.mobs.BasicBehavior;
import com.jonmarx.mobs.Entity;
import com.jonmarx.mobs.EntityBehavior;
import com.jonmarx.tiles.BlackBehavior;
import com.jonmarx.tiles.CollisionBehavior;
import com.jonmarx.tiles.CollisionTile;
import com.jonmarx.tiles.ColorBehavior;
import com.jonmarx.tiles.EndBehavior;
import com.jonmarx.tiles.FlashBehavior;
import com.jonmarx.tiles.Tile;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Jon
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    static final String levelName = "/com/jonmarx/images/levels/Maze/Maze";
    static final String[] data = LevelLoader.loadData(levelName + ".dat");
    public static final int pixelSize = 16;
    public static final int windowSize = 32;
    static int xpush = Integer.parseInt(data[0]) - windowSize / 2;
    static int ypush = Integer.parseInt(data[1]) - windowSize / 2;

    public static void main(String[] args) {
        HashMap<Integer, Tile> map = (HashMap<Integer, Tile>) loadMapFromFile(Main.class.getResourceAsStream(levelName + ".ti"));
        HashMap<Integer, Entity> entities = (HashMap<Integer, Entity>) loadMapFromFile(Main.class.getResourceAsStream(levelName + ".en"));
        
        Tile[][] level = loadLevel(levelName + ".png", map);
        EntityManager.setup(entities);
        
        JFrame frame = new JFrame();
        frame.setSize(pixelSize * (windowSize + 1), pixelSize * (windowSize + 3) - 9);
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
                for(Entity e : EntityManager.getEntities().values()) {
                    try {
                        ((CollisionTile) TileManager.getTile(e.getLocation()[0] + xpush, e.getLocation()[1] + ypush)).collide(e);
                    } catch (Exception ex) {
                        continue;
                    }
                }
                Entity en = entities.get(0);
                if(KeyboardListener.w) {
                    if(TileManager.getTile(xpush + en.getLocation()[0], ypush + en.getLocation()[1] - 1).isSolid()) {
                        KeyboardListener.w = false;
                        return;
                    }
                    if(ypush < 1 || en.getLocation()[1] > windowSize / 2) {
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
                    if(xpush < 1 || en.getLocation()[0] > windowSize / 2) {
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
                    if(ypush > level[0].length - windowSize - 1 || en.getLocation()[1] < windowSize / 2 - 2) {
                        en.setLocation(en.getLocation()[0], en.getLocation()[1] + 1);
                    } else {
                        ypush++;
                    }
                    if(en.getLocation()[1] > level.length - 1) {
                        en.setLocation(en.getLocation()[0], level[0].length / 2 - 1);
                    }
                    KeyboardListener.s = false;
                }
                if(KeyboardListener.d) {
                    if(TileManager.getTile(xpush + en.getLocation()[0] + 1, ypush + en.getLocation()[1]).isSolid()) {
                        KeyboardListener.d = false;
                        return;
                    }
                    if(xpush > level.length - windowSize - 1 || en.getLocation()[0] < windowSize / 2 - 2) {
                        en.setLocation(en.getLocation()[0] + 1, en.getLocation()[1]);
                    } else {
                        xpush++;
                    }
                    if(en.getLocation()[0] > level.length / 2 - 1) {
                        en.setLocation(level.length / 2 - 1, en.getLocation()[1]);
                    }
                    KeyboardListener.d = false;
                }
            }
        });
        ti.start();
        frame.addKeyListener(new KeyboardListener());
    }
    
    // sets up the level, including tiles and entites.
    public static Tile[][] loadLevel(String level, HashMap<Integer, Tile> tiles) {
        Tile[][] levels = LevelLoader.compileLevel(tiles, LevelLoader.loadLevel(level));
        TileManager.setup(levels);
        return levels;
    }
    
    // Loads the map
    public static Map<?, ?> loadMapFromFile(InputStream input) {
        ObjectInputStream objectInputStream = null;
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(input);
            objectInputStream = new ObjectInputStream(bufferedInputStream);
            Object object = objectInputStream.readObject();
            objectInputStream.close();
            if(object instanceof HashMap<?, ?>) return (HashMap<?, ?>) object;
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                objectInputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    // Exports the map
    public static void exportMapToFile(Map<?, ?> map, String input) {
        BufferedOutputStream stream = null;
        try {
            stream = new BufferedOutputStream(new FileOutputStream(input));
            ObjectOutputStream strea = new ObjectOutputStream(stream);
            strea.writeObject(map);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stream.close();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

// Panal that draws the game itself
class GamePanel extends JPanel {
    int frame = 0;
    @Override
    public void paintComponent(Graphics g) {
        frame++;
        super.paintComponent(g);
        com.jonmarx.gfx.Screen.drawScreen(g, TileManager.getTiles(Main.xpush, Main.ypush, Main.windowSize, Main.windowSize), EntityManager.getEntities(), frame);
    }
}

// Takes in Input
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