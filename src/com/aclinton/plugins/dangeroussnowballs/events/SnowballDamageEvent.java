package com.aclinton.plugins.dangeroussnowballs.events;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;

import java.util.ArrayList;

public class SnowballDamageEvent implements Listener {
    private ArrayList<EntityType> damage_receivers = new ArrayList<EntityType>() {{
        add(EntityType.PLAYER);
        add(EntityType.ZOMBIE);
        add(EntityType.ZOMBIE_VILLAGER);
        add(EntityType.SKELETON);
        add(EntityType.SKELETON_HORSE);
        add(EntityType.ENDERMAN);
        add(EntityType.CREEPER);
        add(EntityType.SPIDER);
        add(EntityType.CAVE_SPIDER);
    }};
    private double lightning_chance = 0.25;

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        Entity dealer = e.getDamager();
        Entity receiver = e.getEntity();

        // Damage must be from a snowball and only received by entities in the damage receivers list
        if(dealer.getType() != EntityType.SNOWBALL
                || !(this.damage_receivers.contains(receiver.getType()))) {
            return;
        }

        Snowball snowball = (Snowball) dealer;
        ProjectileSource thrower = snowball.getShooter();

        // Only players have this super powah
        if(!(thrower instanceof Player)) {
            return;
        }

        Location location = receiver.getLocation();
        World w = dealer.getWorld();
        w.createExplosion(location, 2);

        double chance = Math.random();

        if(chance < this.lightning_chance) { // you might get struck by lightning
            w.strikeLightning(((Player) thrower).getLocation());
        }

        if(receiver instanceof Player) {
            receiver.sendMessage("BOOM. You just got esploded with some shnow >:)");
        }
    }
}
