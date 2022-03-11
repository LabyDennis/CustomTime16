package de.speznas.customtime.timings;

import de.speznas.customtime.timings.loops.*;
import net.labymod.api.LabyModAddon;

public class TimeController {

    private static TimeController instance;
    private final LabyModAddon labyModAddon;

    private boolean enabled;
    private int staticTime;

    private int customTime;
    private int loopSpeed;
    private LoopMode loopMode;

    public TimeController(LabyModAddon labyModAddon){
        instance = this;
        this.labyModAddon = labyModAddon;
    }

    public void callUpdate(){
        customTime = loopMode.next(loopSpeed);
    }

    public static TimeController getInstance() {
        return instance;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public int getCustomTime() {
        return customTime;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;

        labyModAddon.getConfig().remove("enabled");
        labyModAddon.getConfig().addProperty("enabled", enabled);
        labyModAddon.saveConfig();
    }

    public void setStaticTime(int staticTime){
        this.staticTime = staticTime;

        labyModAddon.getConfig().remove("staticTime");
        labyModAddon.getConfig().addProperty("staticTime", staticTime);
        labyModAddon.saveConfig();
    }

    public void setLoopSpeed(int speed){
        this.loopSpeed = speed;

        labyModAddon.getConfig().remove("loopSpeed");
        labyModAddon.getConfig().addProperty("loopSpeed", speed);
        labyModAddon.saveConfig();
    }

    public void setLoopMode(String loopModeString){
        if(loopModeString.equals("CustomTime")){
            loopMode = new StaticTimeLoop(staticTime);
        }else if(loopModeString.equals("DayNightloop")){
            loopMode = new DayNightLoop();
        }else if(loopModeString.equals("Dayloop")){
            loopMode = new DayLoop();
        }else if(loopModeString.equals("Nightloop")){
            loopMode = new NightLoop();
        }else if(loopModeString.equals("Sunriseloop")){
            loopMode = new SunriseLoop();
        }

        labyModAddon.getConfig().remove("loopMode");
        labyModAddon.getConfig().addProperty("loopMode", loopModeString);
        labyModAddon.saveConfig();
    }
}
