package com.j1adong.androidstepbystep;

import android.app.Application;

import com.j1adong.androidstepbystep.reduce.CounterReducer;
import com.yheriatovych.reductor.Store;

/**
 * Created by J1aDong on 2016/11/17.
 */

public class MyApplication extends Application
{

	private Store<Integer> counterStore;

	@Override
	public void onCreate()
	{
		super.onCreate();

		counterStore = Store.create(CounterReducer.create());
	}

	public Store<Integer> getCounterStore()
	{
		return counterStore;
	}
}
