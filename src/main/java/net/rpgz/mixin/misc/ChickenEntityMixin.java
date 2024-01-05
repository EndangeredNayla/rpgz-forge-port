package net.rpgz.mixin.misc;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Chicken.class)
public abstract class ChickenEntityMixin extends Animal {
    @Shadow
    public float flap;
    @Shadow
    public float flapSpeed;
    @Shadow
    public float oFlapSpeed;
    @Shadow
    public float oFlap;

    public ChickenEntityMixin(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "aiStep", at = @At(value = "HEAD"), cancellable = true)
    public void aiStepChickenBlaze(CallbackInfo info) {
        if (this.isDeadOrDying()) {
            super.tickDeath();
            this.flap = 0.0F;
            this.flapSpeed = 0.0F;
            this.oFlapSpeed = 0.0F;
            this.oFlap = 0.0F;
            info.cancel();
        }
    }

}