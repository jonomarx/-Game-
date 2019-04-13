/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonmarx.game;

import com.jonmarx.tiles.FlashBehavior;
import com.jonmarx.tiles.Tile;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Jon
 */
public class LevelLoader {
    public static int[][] loadLevel(String filename) {
        try {
            BufferedImage image = ImageIO.read(LevelLoader.class.getResource(filename));
            int[][] output = new int[image.getWidth()][image.getHeight()];
            int k = 0;
            for(int i = 0; i < image.getWidth(); i++) {
                for(int j = 0; j < image.getHeight(); j++) {
                    output[i][j] = image.getRGB(i, j);
                    k++;
                }
            }
            return output;
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public static Tile[][] compileLevel(HashMap<Integer, Tile> list, int[][] tiles) {
        Tile[][] output = new Tile[tiles.length][tiles[0].length];
        for(int i = 0; i < tiles.length; i++) {
            for(int j = 0; j < tiles[0].length; j++) {
                output[i][j] = (list.get(tiles[i][j]));
            }
        }
        return output;
    }
}
