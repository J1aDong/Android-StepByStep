package com.j1adong.androidstepbystep.reduce;

import com.j1adong.androidstepbystep.action.CounterActions;
import com.yheriatovych.reductor.Reducer;
import com.yheriatovych.reductor.annotations.AutoReducer;

/**
 * Created by J1aDong on 2016/11/16.
 */

@AutoReducer
public abstract class CounterReducer implements Reducer<Integer>
{

	@AutoReducer.InitialState
	int initialState()
	{
		return 0;
	}

	@AutoReducer.Action(value = CounterActions.INCREMENT, from = CounterActions.class)
	int increment(int state)
	{
		return state + 1;
	}

	@AutoReducer.Action(value = CounterActions.ADD, from = CounterActions.class)
	int add(int state, int value)
	{
		return state + value;
	}

	public static CounterReducer create()
	{
		return new CounterReducerImpl(); //编译后生成
	}
}
