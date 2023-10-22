package net.smazeee.prehistoric.entity.goals;

import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.player.Player;
import net.smazeee.prehistoric.entity.Dinosaur;

public class DinoLookAtPlayerGoal extends LookAtPlayerGoal {
    private final Dinosaur mob;

    public DinoLookAtPlayerGoal(Dinosaur mob) {
        super(mob, Player.class, 5.0F);
        this.mob =  mob;
    }

    @Override
    public boolean canUse() {
        return super.canUse() && !mob.isSleeping();
    }

    @Override
    public boolean canContinueToUse() {
        if (mob.isSleeping()) {
            return false;
        } else {
            return super.canContinueToUse();
        }
    }
}