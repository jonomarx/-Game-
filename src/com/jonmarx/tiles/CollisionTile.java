/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonmarx.tiles;

import com.jonmarx.mobs.Entity;

/**
 *
 * @author Jon
 */
public class CollisionTile extends Tile {
    public CollisionTile(CollisionBehavior t, boolean isSolid) {
        super(t, isSolid);
    }
    
    public void collide(Entity e) {
        if(b == null) return;
        ((CollisionBehavior) b).collide(e);
    }
}
