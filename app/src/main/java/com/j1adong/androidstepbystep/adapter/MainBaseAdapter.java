package com.j1adong.androidstepbystep.adapter;

import java.util.List;

import com.j1adong.androidstepbystep.R;
import com.j1adong.androidstepbystep.ShareElementActivity;
import com.j1adong.androidstepbystep.util.LolipopAnimationUtil;
import com.j1adong.androidstepbystep.util.SnackUtil;
import com.j1adong.recyclerviewhelper.RvBaseAdapter;
import com.j1adong.recyclerviewhelper.RvBaseAdapterHelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.TextView;

/**
 * Created by J1aDong on 2016/11/8.
 */

public class MainBaseAdapter extends RvBaseAdapter<String>
{

	public MainBaseAdapter(Context context, int layoutResId, List list)
	{
		super(context, layoutResId, list);
	}

	@Override
	protected void convert(RvBaseAdapterHelper help, String item, int position)
	{
		TextView textView = help.getTextView(R.id.tv_item);
		textView.setText(item);
		LolipopAnimationUtil.setRippleBackground(textView);

		textView.setOnClickListener(view -> {
			// SnackUtil
			// .shortWithoutAct((Activity) getContext(), item);
			switch( position )
			{
				case 0:
					if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP )
					{
						getContext()
								.startActivity(new Intent(getContext(), ShareElementActivity.class));
					}
					else
					{
						SnackUtil
								.shortNormal((Activity) getContext(), "只能运行在5.0以上版本的手机");
					}
					break;
				default:
					break;
			}
		});

	}
}
