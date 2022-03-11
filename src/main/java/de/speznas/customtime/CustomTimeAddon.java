package de.speznas.customtime;

import java.util.List;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import de.speznas.customtime.timings.TimeController;
import net.labymod.api.LabyModAddon;
import net.labymod.gui.elements.DropDownMenu;
import net.labymod.settings.elements.*;
import net.labymod.utils.Consumer;
import net.labymod.utils.Material;

public class CustomTimeAddon extends LabyModAddon {


  private TimeController timeController;

  @Override
  public void onEnable() {
    timeController = new TimeController(this);
  }

  @Override
  public void loadConfig() {

    getDefaultConfig().entrySet().forEach(new java.util.function.Consumer<Map.Entry<String, JsonElement>>() {
      @Override
      public void accept(Map.Entry<String, JsonElement> stringJsonElementEntry) {

        if(!getConfig().has(stringJsonElementEntry.getKey())){
          getConfig().add(stringJsonElementEntry.getKey(), stringJsonElementEntry.getValue());
        }
      }
    });

    timeController.setEnabled(getConfig().get("enabled").getAsBoolean());
    timeController.setStaticTime(getConfig().get("staticTime").getAsInt());
    timeController.setLoopSpeed(getConfig().get("loopSpeed").getAsInt());
    timeController.setLoopMode(getConfig().get("loopMode").getAsString());
  }

  @Override
  protected void fillSettings(List<SettingsElement> list) {

    list.add(new BooleanElement( "Enabled", new ControlElement.IconData( Material.LEVER ), new Consumer<Boolean>() {
      @Override
      public void accept( Boolean accepted ) {
        timeController.setEnabled(accepted);
      }
    }, timeController.isEnabled()));

    NumberElement staticTimeNumElement = new NumberElement("CustomTime \n(0 - 24000)",
            new ControlElement.IconData(Material.WATCH), this.getConfig().get("staticTime").getAsInt());

    staticTimeNumElement.setRange(0, 24000);
    staticTimeNumElement.addCallback( new Consumer<Integer>() {
      @Override
      public void accept( Integer accepted ) {
        timeController.setStaticTime(accepted);
      }
    });
    list.add(staticTimeNumElement);

    NumberElement speedNumElement = new NumberElement("Loop Speed \n(0 - 200)",
            new ControlElement.IconData(Material.REDSTONE_TORCH), this.getConfig().get("loopSpeed").getAsInt());

    speedNumElement.setRange(0, 200);
    speedNumElement.addCallback(new Consumer<Integer>() {
      @Override
      public void accept( Integer accepted ) {
        timeController.setLoopSpeed(accepted);
      }
    });
    list.add(speedNumElement);

    DropDownMenu dropDownMenu = new DropDownMenu("Presets", 0, 0, 0, 0);
    dropDownMenu.addOption("CustomTime");
    dropDownMenu.addOption("DayNightloop");
    dropDownMenu.addOption("Dayloop");
    dropDownMenu.addOption("Nightloop");
    dropDownMenu.addOption("Sunriseloop");
    dropDownMenu.setSelected(this.getConfig().get("loopMode").getAsString());

    DropDownElement dropDownElement = new DropDownElement("", dropDownMenu);

    dropDownElement.setChangeListener(new Consumer() {
      @Override
      public void accept(Object accepted) {
        timeController.setLoopMode(accepted.toString());
      }
    });
    list.add(dropDownElement);
  }

  private JsonObject getDefaultConfig(){
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("enabled", false);
    jsonObject.addProperty("staticTime", 0);
    jsonObject.addProperty("loopSpeed", 5);
    jsonObject.addProperty("loopMode", "DayNightloop");

    return jsonObject;
  }

}
