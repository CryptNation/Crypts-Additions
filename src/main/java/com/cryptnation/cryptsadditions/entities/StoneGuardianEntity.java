
package com.cryptnation.cryptsadditions.entities;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.World;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Hand;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ActionResultType;
import net.minecraft.network.IPacket;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import com.cryptnation.cryptsadditions.entities.renderer.StoneGuardianRenderer;
import com.cryptnation.cryptsadditions.CryptsAdditionsElements;

@CryptsAdditionsElements.ModElement.Tag
public class StoneGuardianEntity extends CryptsAdditionsElements.ModElement {
    public static EntityType entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER)
            .setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).immuneToFire()
            .size(1f, 3.4f)).build("stone_guardian").setRegistryName("stone_guardian");
    public StoneGuardianEntity(CryptsAdditionsElements instance) {
        super(instance, 1);
        FMLJavaModLoadingContext.get().getModEventBus().register(new ModelRegisterHandler());
        FMLJavaModLoadingContext.get().getModEventBus().register(new EntityAttributesRegisterHandler());
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void initElements() {
        elements.entities.add(() -> entity);
        elements.items.add(() -> new SpawnEggItem(entity, -10066330, -10040320, new Item.Properties().group(ItemGroup.MISC))
                .setRegistryName("stone_guardian_spawn_egg"));
    }

    @SubscribeEvent
    public void addFeatureToBiomes(BiomeLoadingEvent event) {
        event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(entity, 20, 4, 4));
    }

    @Override
    public void init(FMLCommonSetupEvent event) {
        EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                MonsterEntity::canMonsterSpawn);
    }
    private static class EntityAttributesRegisterHandler {
        @SubscribeEvent
        public void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
            AttributeModifierMap.MutableAttribute ammma = MobEntity.func_233666_p_();
            ammma = ammma.createMutableAttribute(Attributes.MOVEMENT_SPEED, 2);
            ammma = ammma.createMutableAttribute(Attributes.MAX_HEALTH, 700);
            ammma = ammma.createMutableAttribute(Attributes.ARMOR, 60);
            ammma = ammma.createMutableAttribute(Attributes.ATTACK_DAMAGE, 40);
            ammma = ammma.createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 40);
            ammma = ammma.createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 20);
            event.put(entity, ammma.create());
        }
    }

    public static class CustomEntity extends TameableEntity {
        public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
            this(entity, world);
        }

        public CustomEntity(EntityType<CustomEntity> type, World world) {
            super(type, world);
            experienceValue = 0;
            setNoAI(false);
        }

        @Override
        public IPacket<?> createSpawnPacket() {
            return NetworkHooks.getEntitySpawningPacket(this);
        }

        @Override
        protected void registerGoals() {
            super.registerGoals();
            this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, false));
            this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 1));
            this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
            this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
            this.goalSelector.addGoal(5, new SwimGoal(this));
        }

        @Override
        public CreatureAttribute getCreatureAttribute() {
            return CreatureAttribute.UNDEFINED;
        }

        @Override
        public double getMountedYOffset() {
            return super.getMountedYOffset() + 0.05;
        }

        @Override
        public void playStepSound(BlockPos pos, BlockState blockIn) {
            this.playSound((net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.iron_golem.step")),
                    0.15f, 1);
        }

        @Override
        public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
            return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.iron_golem.hurt"));
        }

        @Override
        public net.minecraft.util.SoundEvent getDeathSound() {
            return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.iron_golem.death"));
        }

        @Override
        public boolean attackEntityFrom(DamageSource source, float amount) {
            if (source.getImmediateSource() instanceof ArrowEntity)
                return false;
            if (source == DamageSource.FALL)
                return false;
            if (source == DamageSource.CACTUS)
                return false;
            if (source == DamageSource.LIGHTNING_BOLT)
                return false;
            return super.attackEntityFrom(source, amount);
        }

        @Override
        public ActionResultType func_230254_b_(PlayerEntity sourceentity, Hand hand) {
            ItemStack itemstack = sourceentity.getHeldItem(hand);
            ActionResultType retval = ActionResultType.func_233537_a_(this.world.isRemote());
            Item item = itemstack.getItem();
            if (itemstack.getItem() instanceof SpawnEggItem) {
                retval = super.func_230254_b_(sourceentity, hand);
            } else if (this.world.isRemote()) {
                retval = (this.isTamed() && this.isOwner(sourceentity) || this.isBreedingItem(itemstack))
                        ? ActionResultType.func_233537_a_(this.world.isRemote())
                        : ActionResultType.PASS;
            } else {
                if (this.isTamed()) {
                    if (this.isOwner(sourceentity)) {
                        if (item.isFood() && this.isBreedingItem(itemstack) && this.getHealth() < this.getMaxHealth()) {
                            this.consumeItemFromStack(sourceentity, itemstack);
                            this.heal((float) item.getFood().getHealing());
                            retval = ActionResultType.func_233537_a_(this.world.isRemote());
                        } else if (this.isBreedingItem(itemstack) && this.getHealth() < this.getMaxHealth()) {
                            this.consumeItemFromStack(sourceentity, itemstack);
                            this.heal(4);
                            retval = ActionResultType.func_233537_a_(this.world.isRemote());
                        } else {
                            retval = super.func_230254_b_(sourceentity, hand);
                        }
                    }
                } else if (this.isBreedingItem(itemstack)) {
                    this.consumeItemFromStack(sourceentity, itemstack);
                    if (this.rand.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, sourceentity)) {
                        this.setTamedBy(sourceentity);
                        this.world.setEntityState(this, (byte) 7);
                    } else {
                        this.world.setEntityState(this, (byte) 6);
                    }
                    this.enablePersistence();
                    retval = ActionResultType.func_233537_a_(this.world.isRemote());
                } else {
                    retval = super.func_230254_b_(sourceentity, hand);
                    if (retval == ActionResultType.SUCCESS || retval == ActionResultType.CONSUME)
                        this.enablePersistence();
                }
            }
            sourceentity.startRiding(this);
            double x = this.getPosX();
            double y = this.getPosY();
            double z = this.getPosZ();
            Entity entity = this;
            return retval;
        }

        @Override
        public AgeableEntity func_241840_a(ServerWorld serverWorld, AgeableEntity ageable) {
            CustomEntity retval = (CustomEntity) entity.create(serverWorld);
            retval.onInitialSpawn(serverWorld, serverWorld.getDifficultyForLocation(new BlockPos(retval.getPosition())), SpawnReason.BREEDING,
                    (ILivingEntityData) null, (CompoundNBT) null);
            return retval;
        }

        @Override
        public boolean isBreedingItem(ItemStack stack) {
            if (stack == null)
                return false;
            if (new ItemStack(Blocks.CRYING_OBSIDIAN, (int) (1)).getItem() == stack.getItem())
                return true;
            return false;
        }

        @Override
        public void travel(Vector3d dir) {
            Entity entity = this.getPassengers().isEmpty() ? null : (Entity) this.getPassengers().get(0);
            if (this.isBeingRidden()) {
                this.rotationYaw = entity.rotationYaw;
                this.prevRotationYaw = this.rotationYaw;
                this.rotationPitch = entity.rotationPitch * 0.5F;
                this.setRotation(this.rotationYaw, this.rotationPitch);
                this.jumpMovementFactor = this.getAIMoveSpeed() * 0.15F;
                this.renderYawOffset = entity.rotationYaw;
                this.rotationYawHead = entity.rotationYaw;
                this.stepHeight = 1.0F;
                if (entity instanceof LivingEntity) {
                    this.setAIMoveSpeed((float) this.getAttributeValue(Attributes.MOVEMENT_SPEED));
                    float forward = ((LivingEntity) entity).moveForward;
                    float strafe = ((LivingEntity) entity).moveStrafing;
                    super.travel(new Vector3d(strafe, 0, forward));
                }
                this.prevLimbSwingAmount = this.limbSwingAmount;
                double d1 = this.getPosX() - this.prevPosX;
                double d0 = this.getPosZ() - this.prevPosZ;
                float f1 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;
                if (f1 > 1.0F)
                    f1 = 1.0F;
                this.limbSwingAmount += (f1 - this.limbSwingAmount) * 0.4F;
                this.limbSwing += this.limbSwingAmount;
                return;
            }
            this.stepHeight = 0.5F;
            this.jumpMovementFactor = 0.02F;
            super.travel(dir);
        }
    }

    private class ModelRegisterHandler {
    }
}