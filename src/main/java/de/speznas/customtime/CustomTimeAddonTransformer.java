package de.speznas.customtime;

import net.labymod.addon.AddonTransformer;
import net.labymod.api.TransformerType;

public class CustomTimeAddonTransformer extends AddonTransformer {

  @Override
  public void registerTransformers() {
    this.registerTransformer(TransformerType.VANILLA, "customtime.mixin.json");
  }
}
