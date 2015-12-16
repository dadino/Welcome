package it.dadino.welcome;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter<T extends View> extends PagerAdapter {

	private List<T>      mViews  = new ArrayList<>();
	private List<String> mTitles = new ArrayList<>();

	public void addView(T view, String title) {
		mViews.add(view);
		mTitles.add(title);
	}

	public T getView(int position) {
		return mViews.get(position);
	}

	public T getView(int position, ViewPager pager) {
		return getView(position);
	}

	@Override
	public int getCount() {
		return mViews.size();
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		ViewPager pager = (ViewPager) container;
		View view = getView(position, pager);

		pager.addView(view);

		return view;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object view) {
		container.removeView((View) view);
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return mTitles.get(position);
	}

	public void clear() {
		mViews.clear();
		mTitles.clear();
		notifyDataSetChanged();
	}
}
