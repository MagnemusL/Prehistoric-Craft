package net.smazeee.prehistoric.entity.goals;

import net.minecraft.world.entity.Saddleable;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.levelgen.RandomSource;
import net.smazeee.prehistoric.entity.Dinosaur;

import java.util.Random;

public class DinoSleepGoal extends Goal {
    private final Dinosaur mob;
    private int sleepTimer = 0;

    public DinoSleepGoal(Dinosaur dinosaur) {
        super();
        this.mob = dinosaur;
    }

    @Override
    public boolean canUse() {
        if (this.mob.level.isNight() && !this.mob.isInWater() && !this.mob.isInLava() && this.mob.getTarget() == null) {
            if (this.mob instanceof Saddleable saddled && saddled.isSaddled()) {
                return new Random().nextInt(2000) == 1;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean canContinueToUse() {
        if (this.mob.level.isDay()) {
            stop();
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void tick() {
        super.tick();
        this.sleepTimer++;
        if (this.mob.level.isDay()) {
            stop();
        }
    }

    @Override
    public void start() {
        this.sleepTimer = 0;
        this.mob.setSleeping(true);
    }

    @Override
    public void stop() {
        this.sleepTimer = 0;
        this.mob.setSleeping(false);
    }
}
