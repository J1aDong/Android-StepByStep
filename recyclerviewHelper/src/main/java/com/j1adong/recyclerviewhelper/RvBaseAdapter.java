package com.j1adong.recyclerviewhelper;

import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Created by J1aDong on 16/6/2.
 */
public abstract class RvBaseAdapter<T>
		extends RecyclerView.Adapter<RvBaseAdapterHelper>
{

	public static final String TAG = RvBaseAdapter.class.getSimpleName();

	private Context mContext;
	private int mLayoutResId;
	private List<T> mList;

	/**
	 * 标记是否可以加载下一个
	 */
	private boolean mNextLoadEnable = false;

	private View mHeaderView;
	private View mFooterView;

	private static final int HEADER_VIEW = 0x00000111;
	private static final int LOADING_VIEW = 0x00000222;
	private static final int FOOTER_VIEW = 0x00000333;
	private boolean mLoadingMoreEnable = false;
	private OnLoadMoreListener mOnLoadMoreListener;
	private int lastPosition = -1;
	// 是否开启item动画
	private boolean mEnableAnimation = true;

	// 第一个position动画时的时间
	private long mStartTime = -1;

	// 设置多少秒后动画失效
	private float mOffsetSeconds = 0.3f;

	/***************** public method *********************/

	/**
	 * 是否开启item列表动画，默认开启
	 * 
	 * @param enableAnimation
	 */
	public void setEnableAnimation(boolean enableAnimation)
	{
		this.mEnableAnimation = enableAnimation;
	}

	/**
	 * 得到传入的List数组
	 *
	 * @return
	 */
	public List<T> getList()
	{
		return mList;
	}

	/**
	 * 获得context
	 *
	 * @return
	 */
	public Context getContext()
	{
		return mContext;
	}

	/**
	 * 设置加载更多的监听
	 * 
	 * @param mOnLoadMoreListener
	 */
	public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener)
	{
		mNextLoadEnable = true;
		this.mOnLoadMoreListener = mOnLoadMoreListener;
	}

	/**
	 * 添加FooterView
	 *
	 * @param view
	 */
	public void addFooterView(View view)
	{
		mNextLoadEnable = false;
		if( null == view )
		{
			throw new RuntimeException("footer is null");
		}
		this.mFooterView = view;
		this.notifyDataSetChanged();
	}

	/**
	 * 获取数据的项数
	 * 
	 * @return
	 */
	@Override
	public int getItemCount()
	{
		int i = mNextLoadEnable ? 1 : 0;
		return mList.size() + i + getHeaderVeiwsCount() + getFooterViewsCount();
	}

	/**
	 * 添加HeaderView
	 *
	 * @param view
	 */
	public void addHeaderView(View view)
	{
		if( null == view )
		{
			throw new RuntimeException("header is null");
		}
		this.mHeaderView = view;
		this.notifyDataSetChanged();
	}

	/**
	 * 设置加载状态
	 * 
	 * @param isNextLoad
	 */
	public void setNextLoad(boolean isNextLoad)
	{
		mNextLoadEnable = isNextLoad;
		mLoadingMoreEnable = false;
		notifyDataSetChanged();
	}

	/***************** public method *********************/

	public RvBaseAdapter(Context context, int layoutResId, List<T> list)
	{
		this.mContext = context;
		this.mLayoutResId = layoutResId;
		this.mList = list;
	}

	@Override
	public RvBaseAdapterHelper onCreateViewHolder(ViewGroup parent,
			int viewType)
	{

		// 加载页面
		if( viewType == LOADING_VIEW )
		{
			return new FooterViewHolder(getItemView(R.layout.def_loading, parent));
		}
		// header
		else if( viewType == HEADER_VIEW )
		{
			return new HeadViewHolder(mHeaderView);
		}
		// footer
		else if( viewType == FOOTER_VIEW )
		{
			return new FooterViewHolder(mFooterView);
		}
		else
		{
			return onCreateDefViewHolder(parent, viewType);
		}
	}

	private static class HeadViewHolder extends RvBaseAdapterHelper
	{

		HeadViewHolder(View itemView)
		{
			super(itemView);
		}
	}

	private static class FooterViewHolder extends RvBaseAdapterHelper
	{

		FooterViewHolder(View itemView)
		{
			super(itemView);
		}
	}

	private RvBaseAdapterHelper onCreateDefViewHolder(ViewGroup parent,
			int viewType)
	{
		return new ContentViewHolder(getItemView(mLayoutResId, parent));
	}

	private static class ContentViewHolder extends RvBaseAdapterHelper
	{

		ContentViewHolder(View itemView)
		{
			super(itemView);
		}
	}

	private View getItemView(int layoutResId, ViewGroup parent)
	{
		return LayoutInflater.from(mContext)
				.inflate(layoutResId, parent, false);
	}

	@Override
	public void onBindViewHolder(RvBaseAdapterHelper helper, int position)
	{
		if( mEnableAnimation )
		{
			setAnimation(helper.itemView, position);
		}

		if( helper instanceof ContentViewHolder )
		{
			int index = position - getHeaderVeiwsCount();

			convert(helper, mList.get(index), index);
		}
		else if( helper instanceof FooterViewHolder )
		{
			if( mNextLoadEnable && !mLoadingMoreEnable
					&& mOnLoadMoreListener != null )
			{
				mLoadingMoreEnable = true;
				mOnLoadMoreListener.onLoadMoreRequest();
				if( helper.itemView
						.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams )
				{
					StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) helper.itemView
							.getLayoutParams();
					params.setFullSpan(true);
				}
			}
		}
		else if( helper instanceof HeadViewHolder )
		{

		}
		else
		{
			int index = position - getHeaderVeiwsCount();
			onBindDefViewHolder(helper, mList.get(index), index);
		}
	}

	private void setAnimation(View view, int position)
	{
		if( 0 == position && mStartTime == -1 )
		{
			mStartTime = System.currentTimeMillis();
		}

		// 1s后失效
		if( System.currentTimeMillis() - mStartTime > mOffsetSeconds * 1000 )
		{
			return;
		}

		// If the bound view wasn't previously displayed on screen, it's
		// animated
		if( position > lastPosition )
		{
			Animation animation = AnimationUtils
					.loadAnimation(view.getContext(), R.anim.slide_in_bottom);
			animation.setStartOffset(position * 50);
			view.startAnimation(animation);
			lastPosition = position;
		}
	}

	/**
	 * 需要覆盖
	 *
	 * @param helper
	 * @param item
	 * @param position
	 */
	private void onBindDefViewHolder(RvBaseAdapterHelper helper, T item,
			int position)
	{

	}

	@Override
	public int getItemViewType(int position)
	{
		if( null != mHeaderView && position == 0 )
		{
			return HEADER_VIEW;
		}
		else if( position == mList.size() + getHeaderVeiwsCount() )
		{
			if( mNextLoadEnable )
				return LOADING_VIEW;
			else
				return FOOTER_VIEW;
		}
		return getDefItemViewType(position);
	}

	private int getDefItemViewType(int position)
	{
		return super.getItemViewType(position);
	}

	private int getHeaderVeiwsCount()
	{
		return mHeaderView == null ? 0 : 1;
	}

	private int getFooterViewsCount()
	{
		return mFooterView == null ? 0 : 1;
	}

	/**
	 * 实现该方法
	 *
	 * @param help
	 * @param item
	 * @param position
	 */
	protected abstract void convert(RvBaseAdapterHelper help, T item,
			int position);

	interface OnLoadMoreListener
	{
		void onLoadMoreRequest();
	}

}