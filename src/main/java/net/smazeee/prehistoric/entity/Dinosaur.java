package net.smazeee.prehistoric.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

import javax.annotation.Nullable;

public class Dinosaur extends TamableAnimal {
    private static final EntityDataAccessor<Boolean> DATA_SLEEPING = SynchedEntityData.defineId(Dinosaur.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> DATA_FOOD = SynchedEntityData.defineId(Dinosaur.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_WATER = SynchedEntityData.defineId(Dinosaur.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_CLEANLINESS = SynchedEntityData.defineId(Dinosaur.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_NUTRITION = SynchedEntityData.defineId(Dinosaur.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Boolean> DATA_EATING = SynchedEntityData.defineId(Dinosaur.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_DRINKING = SynchedEntityData.defineId(Dinosaur.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_CLEANING = SynchedEntityData.defineId(Dinosaur.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Boolean> DATA_IN_PACK = SynchedEntityData.defineId(Dinosaur.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> DATA_PACK_NUMBER = SynchedEntityData.defineId(Dinosaur.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> DATA_PACK_LEADER = SynchedEntityData.defineId(Dinosaur.class, EntityDataSerializers.BOOLEAN);

    public Dinosaur(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
    }

    public int getPackNumber() {
        return this.entityData.get(DATA_PACK_NUMBER);
    }
    public int getFood() {
        return this.entityData.get(DATA_FOOD);
    }
    public int getWater() {
        return this.entityData.get(DATA_WATER);
    }
    public int getCleanliness() {
        return this.entityData.get(DATA_CLEANLINESS);
    }
    public int getNutrition() {
        return this.entityData.get(DATA_NUTRITION);
    }

    public void setPackNumber(int packNumber) {
        this.entityData.set(DATA_PACK_NUMBER,packNumber);
    }
    public void setFood(int food) {
        this.entityData.set(DATA_FOOD, food);
    }
    public void setWater(int water) {
        this.entityData.set(DATA_WATER, water);
    }
    public void setCleanliness(int cleanliness) {
        this.entityData.set(DATA_CLEANLINESS, cleanliness);
    }
    public void setNutrition(int nutrition) {
        this.entityData.set(DATA_NUTRITION, nutrition);
    }

    public boolean isEating() {
        return this.entityData.get(DATA_EATING);
    }
    public boolean isDrinking() {
        return this.entityData.get(DATA_DRINKING);
    }
    public boolean isInPack() {
        return this.entityData.get(DATA_IN_PACK);
    }
    public boolean isPackLeader() {
        return this.entityData.get(DATA_PACK_LEADER);
    }
    public boolean isCleaning() {
        return this.entityData.get(DATA_CLEANING);
    }
    public boolean isSleeping() {
        return this.entityData.get(DATA_SLEEPING);
    }

    public void setInPack(boolean inPack) {
        this.entityData.set(DATA_IN_PACK, inPack);
    }
    public void setPackLeader(boolean packLeader) {
        this.entityData.set(DATA_PACK_LEADER, packLeader);
    }
    public void setEating(boolean eating) {
        this.entityData.set(DATA_EATING, eating);
    }
    public void setDrinking(boolean drinking) {
        this.entityData.set(DATA_DRINKING, drinking);
    }
    public void setCleaning(boolean cleaning) {
        this.entityData.set(DATA_CLEANING, cleaning);
    }
    public void setSleeping(boolean sleeping) {
        this.entityData.set(DATA_SLEEPING, sleeping);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_SLEEPING, false);
        this.entityData.define(DATA_EATING, false);
        this.entityData.define(DATA_DRINKING, false);
        this.entityData.define(DATA_CLEANING, false);
        this.entityData.define(DATA_IN_PACK, false);
        this.entityData.define(DATA_PACK_LEADER, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putBoolean("sleeping", this.isSleeping());
        nbt.putBoolean("eating", this.isEating());
        nbt.putBoolean("drinking", this.isDrinking());
        nbt.putBoolean("cleaning", this.isCleaning());
        nbt.putBoolean("in_pack", this.isInPack());
        nbt.putBoolean("is_leader", this.isPackLeader());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.setSleeping(nbt.getBoolean("sleeping"));
        this.setEating(nbt.getBoolean("eating"));
        this.setDrinking(nbt.getBoolean("drinking"));
        this.setCleaning(nbt.getBoolean("cleaning"));
        this.setInPack(nbt.getBoolean("in_pack"));
        this.setPackLeader(nbt.getBoolean("is_leader"));
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (this.isSleeping()) {
            this.navigation.stop();
            this.setDeltaMovement(Vec3.ZERO);
        } else {
            super.travel(travelVector);
        }
    }

    @Override
    public boolean hurt(DamageSource damageSource, float amount) {
        this.setSleeping(false);
        return super.hurt(damageSource, amount);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
        return null;
    }
}
