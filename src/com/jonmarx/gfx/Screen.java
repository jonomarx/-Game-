/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonmarx.gfx;

import com.jonmarx.game.EntityManager;
import com.jonmarx.mobs.Entity;
import com.jonmarx.tiles.Tile;
import java.awt.*;
import java.util.HashMap;


/**
 *
 * @author Jon
 */
public class Screen {
    public static void drawScreen(Graphics g, Tile[][] tiles, HashMap<Integer, Entity> entities, int frame) {
        drawScreen(g, tiles, EntityManager.getEntities().values().toArray(new Entity[0]), frame);
    }
    
    public static void drawScreen(Graphics g, com.jonmarx.tiles.Tile[][] tiles, Entity[] entities,int frame) {
        int i = 0;
        int j = 0;
        for(Tile[] t : tiles) {
            for(Tile tile : t) {
                if(tile == null) continue;
                tile.render(g, i, j, frame);
                j++;
            }
            j = 0;
            i++;
        }
        for(Entity entity : entities) {
            entity.render(g);        
        }
    }
}
