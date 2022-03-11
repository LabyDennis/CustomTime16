package de.speznas.customtime.timings.loops;

public class DayNightLoop extends LoopMode {

    public DayNightLoop(){
        timeFrom = 0;
        timeTo = 23999;
    }

    @Override
    public int next(int stepSize) {
        if(currentTime > timeTo){
            currentTime = timeFrom;
        }
        if(currentTime < timeFrom){
            currentTime = timeTo;
        }
        currentTime = currentTime + stepSize;

        return currentTime;
    }
}
