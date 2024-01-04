package net.rpgz.mixin.misc;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;
import net.minecraft.world.entity.monster.Blaze;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Blaze)
public abstract class BlazeEntityMixin extends HostileEntity {
    public BlazeEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tickMovement", at = @At(value = "HEAD"), cancellable = true)
    public void tickMovementMixinBlaze(CallbackInfo info) {
        if (this.isDead()) {
            super.tickMovement();
            info.cancel();
        }
    }

}