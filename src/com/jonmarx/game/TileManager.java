/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonmarx.game;

import com.jonmarx.tiles.BlackBehavior;
import com.jonmarx.tiles.Tile;
import com.jonmarx.tiles.TileBehavior;
/**
 *
 * @author Jon
 */
public class TileManager {
    private static Tile[][] tiles;
    
    public static void changeTile(int x, int y, Tile tile) {
        tiles[x][y] = tile;
    }
    
    public static Tile getTile(int x, int y) {
        return tiles[x][y];
    }
    
    public static Tile[][] getTiles(int x, int y, int xlength, int ylength) {
        Tile[][] output = new Tile[xlength][ylength];
        for(int i = 0; i < xlength; i++) {
            for(int j = 0; j < ylength; j++) {
                try {
                    output[i][j] = tiles[i + x][j + y];
                } catch (ArrayIndexOutOfBoundsException e) {
                    output[i][j] = new Tile(new BlackBehavior(), false);
                }
            }
        }
        return output;
    }
    
    public static void setup(Tile[][] tile) {
        tiles = tile;
    }
}
