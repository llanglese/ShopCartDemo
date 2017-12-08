package com.project.shopcart.uitls;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * ListViewForScrollView
 */
public class NonScrollListView extends ListView {
	public NonScrollListView(Context context) {
		super(context);
	}

	public NonScrollListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NonScrollListView(Context context, AttributeSet attrs,
							 int defStyle) {
		super(context, attrs, defStyle);
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}