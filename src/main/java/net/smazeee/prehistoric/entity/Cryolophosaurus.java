package net.smazeee.prehistoric.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.smazeee.prehistoric.PrehistoricCraft;
import net.smazeee.prehistoric.entity.goals.DinoLookAroundGoal;
import net.smazeee.prehistoric.entity.goals.DinoLookAtPlayerGoal;
import net.smazeee.prehistoric.entity.goals.DinoSleepGoal;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;
import software.bernie.geckolib3.core.builder.ILoopType.EDefaultLoopTypes;

import javax.annotation.Nullable;

public class Cryolophosaurus extends Dinosaur implements IAnimatable {
    private static final EntityDataAccessor<Boolean> DATA_CALLING = SynchedEntityData.defineId(Cryolophosaurus.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_PACK = SynchedEntityData.defineId(Cryolophosaurus.class, EntityDataSerializers.BOOLEAN);

    private int packNumber = 1;

    private final AnimationFactory cache = GeckoLibUtil.createFactory(this);
    private int callingTimer = 0;

    public Cryolophosaurus(EntityType<? extends Dinosaur> entityType, Level level) {
        super(entityType, level);
        this.maxUpStep = 1.5F;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.10D, true));
        this.goalSelector.addGoal(2, new DinoSleepGoal(this));
        this.goalSelector.addGoal(3, new DinoLookAtPlayerGoal(this));
        this.goalSelector.addGoal(5, new DinoLookAroundGoal(this));

        this.targetSelector.addGoal(2, new HurtByTargetGoal(this).setAlertOthers());
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 90.0F)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5F)
                .add(Attributes.MOVEMENT_SPEED, 0.2F)
                .add(Attributes.ATTACK_DAMAGE, 5.0F);
    }

    public Boolean isCalling() {
        return this.entityData.get(DATA_CALLING);
    }
    public void setCalling(boolean calling) {
        this.entityData.set(DATA_CALLING, calling);
    }

    public Boolean hasPack() {
        return this.entityData.get(DATA_PACK);
    }
    public void setPack(boolean pack) {
        this.entityData.set(DATA_PACK, pack);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_CALLING, false);
        this.entityData.define(DATA_PACK, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putBoolean("calling", this.isCalling());
        nbt.putBoolean("pack", this.hasPack());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.setCalling(nbt.getBoolean("calling"));
        this.setPack(nbt.getBoolean("pack"));
    }

    @Override
    public void tick() {
        if (this.isCalling()) {
            if (!this.level.isClientSide) {
                callingTimer++;
                if (callingTimer > 20) {
                    this.setCalling(false);
                    this.setSprinting(true);
                }
            }
        }
        PrehistoricCraft.LOGGER.info("The Cryolophosaurus is in a pack of " + packNumber);
        super.tick();
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData groupData, @Nullable CompoundTag nbt) {
        boolean foundPack = false;
        for (Cryolophosaurus cryo : level.getEntitiesOfClass(Cryolophosaurus.class, this.getBoundingBox().inflate(2))) {
            if (!cryo.isBaby()) {
                if (hasPack()) {
                    foundPack = true;
                    setPack(false);
                    packNumber++;
                    break;
                } else {
                    packNumber = 1;
                    foundPack = false;
                    setPack(true);
                }
            }
        }

        setPack(!foundPack);
        if (hasPack()) {
            PrehistoricCraft.LOGGER.info("A Cryolophosaurus found a pack.");
        } else if (packNumber == 2) {
            PrehistoricCraft.LOGGER.info("The Cryolophosaurus is in a pack of " + packNumber);
        }
        return super.finalizeSpawn(level, difficulty, spawnType, groupData, nbt);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        this.updateSwingTime();
    }

    /*@Override
    protected SoundEvent getAmbientSound() {
        return SoundRegistry.CONCAVENATOR_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundRegistry.CONCAVENATOR_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundRegistry.CONCAVENATOR_DEATH;
    }

     */

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
        return null;
    }

    protected static final AnimationBuilder IDLE = new AnimationBuilder().addAnimation("idle", EDefaultLoopTypes.LOOP);
    protected static final AnimationBuilder WALK = new AnimationBuilder().addAnimation("walk", EDefaultLoopTypes.LOOP);
    protected static final AnimationBuilder SLEEP = new AnimationBuilder().addAnimation("rest", EDefaultLoopTypes.LOOP);
    protected static final AnimationBuilder BITE = new AnimationBuilder().addAnimation("bite", EDefaultLoopTypes.PLAY_ONCE);
    protected static final AnimationBuilder RUN = new AnimationBuilder().addAnimation("run", EDefaultLoopTypes.LOOP);
    protected static final AnimationBuilder CALL = new AnimationBuilder().addAnimation("call", EDefaultLoopTypes.LOOP);
    protected static final AnimationBuilder DRINK = new AnimationBuilder().addAnimation("drink", EDefaultLoopTypes.LOOP);
    protected static final AnimationBuilder CLEAN = new AnimationBuilder().addAnimation("clean", EDefaultLoopTypes.LOOP);

    private <E extends IAnimatable> PlayState basicPredicate(AnimationEvent<E> event) {
        if (this.isSleeping()) {
            event.getController().setAnimation(SLEEP);
            return PlayState.CONTINUE;
        } else if (this.isCalling()) {
            event.getController().setAnimation(CALL);
            return PlayState.CONTINUE;
        } else if (this.isDrinking()) {
            event.getController().setAnimation(DRINK);
            return PlayState.CONTINUE;
        }   else if (this.isEating()) {
            event.getController().setAnimation(BITE);
            return PlayState.CONTINUE;
        }   else if (this.isCleaning()) {
            event.getController().setAnimation(CLEAN);
            return PlayState.CONTINUE;
        } else {
            if (event.isMoving()) {
                event.getController().setAnimation(this.isSprinting() ? RUN : WALK);
                return PlayState.CONTINUE;
            } else {
                event.getController().setAnimation(IDLE);
                return PlayState.CONTINUE;
            }
        }
    }

    private PlayState attackPredicate(AnimationEvent event) {
        if (this.swinging && event.getController().getAnimationState().equals(AnimationState.Stopped)) {
            event.getController().markNeedsReload();
            event.getController().setAnimation(BITE);
            this.swinging = false;
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "basic", 2, this::basicPredicate));
        data.addAnimationController(new AnimationController<>(this, "attack", 2, this::attackPredicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return cache;
    }
}
