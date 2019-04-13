/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonmarx.game;

import com.jonmarx.mobs.Entity;
import java.util.LinkedList;

/**
 *
 * @author Jon
 */
public class EntityManager {
    private static LinkedList<Entity> list = new LinkedList<>();
    
    public static void addEntity(Entity e) {
        list.add(e);
    }
    
    public static void removeEntity(Entity e) {
        list.remove(e);
    }
    
    public static Entity[] getEntities() {
        return list.toArray(new Entity[list.size()]);
    }
}
