package com.j1adong.androidstepbystep.action;

import com.yheriatovych.reductor.Action;
import com.yheriatovych.reductor.annotations.ActionCreator;

import static com.j1adong.androidstepbystep.action.CounterActions.ADD;
import static com.j1adong.androidstepbystep.action.CounterActions.INCREMENT;

/**
 * Created by J1aDong on 2016/11/16.
 */

@ActionCreator
public interface CounterActions {
    String INCREMENT = "INCREMENT";
    String ADD = "ADD";

    @ActionCreator.Action(INCREMENT)
    Action increment();

    @ActionCreator.Action(ADD)
    Action add(int value);
}
