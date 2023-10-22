package net.smazeee.prehistoric.entity.goals;

import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.smazeee.prehistoric.entity.Dinosaur;

public class DinoLookAroundGoal extends RandomLookAroundGoal {
    private final Dinosaur mob;

    public DinoLookAroundGoal(Dinosaur mob) {
        super(mob);
        this.mob = mob;
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
