package com.example.lry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class WordView extends TextView {
	private List mWordsList = new ArrayList();
	private Paint mLoseFocusPaint;
	private Paint mOnFocusePaint;
	private float mX = 0;
	private float mMiddleY = 0;
	private float mY = 0;
	private static final int DY = 50;
	private int mIndex = 0;

	public WordView(Context context) throws IOException {
		super(context);
		init();
	}

	public WordView(Context context, AttributeSet attrs) throws IOException {
		super(context, attrs);
		init();
	}

	public WordView(Context context, AttributeSet attrs, int defStyle)
			throws IOException {
		super(context, attrs, defStyle);
		init();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
        if(mWordsList.size()!=0&&mWordsList!=null)
        {
		canvas.drawColor(Color.alpha(75));
		Paint p = mLoseFocusPaint;
		p.setTextAlign(Paint.Align.CENTER);
		Paint p2 = mOnFocusePaint;
		p2.setTextAlign(Paint.Align.CENTER);

		canvas.drawText((String) mWordsList.get(mIndex), mX, mMiddleY, p2);
        System.out.println("¸è´ÊË÷Òý£º"+(String) mWordsList.get(mIndex));
		int alphaValue = 25;
		float tempY = mMiddleY;
//		for (int i = mIndex - 1; i >= 0; i--) {
//			tempY -= DY;
//			if (tempY < 0) {
//				break;
//			}
//			p.setColor(Color.argb(255 - alphaValue, 245, 245, 245));
//			canvas.drawText((String) mWordsList.get(i), mX, tempY, p);
//			alphaValue += 25;
//		}
		alphaValue = 25;
		tempY = mMiddleY;
		for (int i = mIndex + 1, len = mWordsList.size(); i < len; i++) {
			tempY += DY;
			if (tempY > mY) {
				break;
			}
			p.setColor(Color.argb(255 - alphaValue, 245, 245, 245));
			canvas.drawText((String) mWordsList.get(i), mX, tempY, p);
			alphaValue += 25;
		}
		mIndex++;
        }
        else
        {
        	Paint p = mLoseFocusPaint;
        	p.setTextAlign(Paint.Align.CENTER);
        	canvas.drawText("ÔÝÎÞ¸è´Ê", mX, mMiddleY, p);
        }
	}

	
	public void setIndex(int mIndex)
	{
		this.mIndex=mIndex;
	}
	
	public int getIndex()
	{
		return mIndex;
	}
	public void setList(List<LryStructure> list)
	{
		if(list==null)
			return ;
		
		if(list.size()<=0)
		{
			mWordsList=list;
		}
		else
		{
			mWordsList=new ArrayList();
			for(int i=0;i<list.size();i++)
			{
				mWordsList.add(list.get(i).getS_lry());
			}
		}
		//mIndex=mWordsList.size();
		mIndex=0;
	}
	
	public List getList()
	{
		return mWordsList;
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int ow, int oh) {
		super.onSizeChanged(w, h, ow, oh);

		mX = w * 0.5f;
		mY = h;
		mMiddleY = h * 0.3f;
	}

	@SuppressLint("SdCardPath")
	private void init() throws IOException {
		setFocusable(true);

		
		//ReadLry rl=new ReadLry();
		//lrcHandler.readLRC("/sdcard/ÅãÎÒÈ¥Á÷ÀË.lrc");
		
        
		mLoseFocusPaint = new Paint();
		mLoseFocusPaint.setAntiAlias(true);
		mLoseFocusPaint.setTextSize(22);
		mLoseFocusPaint.setColor(Color.WHITE);
		mLoseFocusPaint.setTypeface(Typeface.SERIF);

		mOnFocusePaint = new Paint();
		mOnFocusePaint.setAntiAlias(true);
		mOnFocusePaint.setColor(Color.YELLOW);
		mOnFocusePaint.setTextSize(30);
		mOnFocusePaint.setTypeface(Typeface.SANS_SERIF);
	}
}
