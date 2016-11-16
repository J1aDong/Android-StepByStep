package com.j1adong.androidstepbystep;

import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by J1aDong on 2016/11/14.
 */

public class TextureViewActivity extends BaseActivity
{

	@BindView(R.id.tv_simple)
	SimpleTextureView mTvSimple;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_textureview);
		ButterKnife.bind(this);


	}
}
