package de.speznas.customtime.mixin;

import de.speznas.customtime.timings.TimeController;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientWorld.class)
public abstract class CustomTimeMixin {

  @Shadow @Final private ClientWorld.ClientWorldInfo field_239130_d_;

  @Inject(method = "setDayTime", at = @At("HEAD"), cancellable = true)
  private void injectSetDayTime(CallbackInfo ci) {
    if(TimeController.getInstance().isEnabled()){
      ci.cancel();
    }
  }

  @Inject(method = "tick", at = @At("TAIL"))
  private void injectTick(CallbackInfo ci){
    if(TimeController.getInstance().isEnabled()){

      TimeController.getInstance().callUpdate();
      field_239130_d_.setDayTime(TimeController.getInstance().getCustomTime());
    }
  }
}
