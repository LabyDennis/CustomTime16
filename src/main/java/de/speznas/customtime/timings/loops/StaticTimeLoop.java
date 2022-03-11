package de.speznas.customtime.timings.loops;

public class StaticTimeLoop extends LoopMode{

    private final int staticTime;
    public StaticTimeLoop(int staticTime){
        this.staticTime = staticTime;
    }

    @Override
    public int next(int stepSize) {
        return staticTime;
    }
}
