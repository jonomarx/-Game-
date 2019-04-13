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
public abstract class CollisionBehavior extends TileBehavior {
    public abstract void collide(Entity e);
}
