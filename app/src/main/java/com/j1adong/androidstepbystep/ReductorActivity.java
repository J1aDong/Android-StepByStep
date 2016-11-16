package com.j1adong.androidstepbystep;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.TextView;

import com.j1adong.androidstepbystep.action.CounterActions;
import com.j1adong.androidstepbystep.reduce.CounterReducer;
import com.yheriatovych.reductor.Actions;
import com.yheriatovych.reductor.Store;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by J1aDong on 2016/11/16.
 */

public class ReductorActivity extends BaseActivity
{

	@BindView(R.id.tv_num)
	TextView mTvNum;
	@BindView(R.id.btn_add)
	Button mBtnAdd;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reductor);
		ButterKnife.bind(this);

		Store<Integer> counterStore = ((MyApplication) getApplication())
				.getCounterStore();

		mTvNum.setText(counterStore.getState() + "");

		CounterActions actions = Actions.from(CounterActions.class);
		counterStore.subscribe(state -> {
			mTvNum.setText(counterStore.getState() + "");
		});

		mBtnAdd.setOnClickListener(view -> {
			counterStore.dispatch(actions.increment());
		});
	}
}
