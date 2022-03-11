package de.speznas.customtime.timings.loops;

public class LoopMode {

    int timeFrom;
    int timeTo;
    boolean forward;

    int currentTime;

    public int next(int stepSize) {
        if(currentTime > timeTo){
            forward = false;
        }else if( currentTime < timeFrom){
            forward = true;
        }

        currentTime = currentTime + (forward ? stepSize : -stepSize);
        return currentTime;
    }
}
