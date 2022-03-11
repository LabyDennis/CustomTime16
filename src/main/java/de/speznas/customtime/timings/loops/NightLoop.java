package de.speznas.customtime.timings.loops;

public class NightLoop extends LoopMode{

    public NightLoop(){
        timeFrom = 14000;
        timeTo = 21500;
        currentTime = 14000;
        forward = true;
    }
}
