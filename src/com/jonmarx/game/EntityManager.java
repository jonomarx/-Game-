/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonmarx.game;

import com.jonmarx.mobs.Entity;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author Jon
 */
public class EntityManager {
    private static HashMap<Integer, Entity> list = new HashMap<>();
    
    public static void addEntity(int index, Entity e) {
        list.put(index, e);
    }
    
    public static void removeEntity(int index, Entity e) {
        list.replace(index, e);
    }
    
    public static HashMap<Integer, Entity> getEntities() {
        return list;
    }
    
    public static void setup(HashMap<Integer, Entity> map) {
        list = map;
    }
}
