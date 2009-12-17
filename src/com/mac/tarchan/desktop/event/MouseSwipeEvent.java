/*
 * MouseSwipeEvent.java
 * OwlPreview
 * 
 * Copyright(c)2009 NTT COMWARE BILLING SOLUTIONS CORPORATION All Rights Reserved.
 * 
 * 2009/08/26
 */
package com.mac.tarchan.desktop.event;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

/**
 * MouseSwipeEvent
 * 
 * @author v-togura
 */
public class MouseSwipeEvent extends MouseEvent
{
	private static final long serialVersionUID = 6816278805169429043L;

	/** スワイプの方向 */
	public enum SwipeDirection
	{
		/** スワイプなし */
		NONE,

		/** 左スワイプ */
		LEFT,

		/** 右スワイプ */
		RIGHT,

		/** 上スワイプ */
		UP,

		/** 下スワイプ */
		DOWN,
	};

	/** スワイプ開始イベント */
	MouseEvent start;

	/** スワイプ終端イベント */
	MouseEvent end;

	/** スワイプの速度 */
	double speed;

	/** スワイプの方向 */
	SwipeDirection dir;

	/**
	 * スワイプイベントを作成します。
	 * 
	 * @param start スワイプ開始イベント
	 * @param end スワイプ終端イベント
	 * @throws IllegalArgumentException start と end のタイムスタンプが同じ場合
	 */
	public MouseSwipeEvent(MouseEvent start, MouseEvent end)
	{
		super(end.getComponent(), end.getID(), end.getWhen(), end.getModifiers(), end.getX(), end.getY(), end.getClickCount(), end.isPopupTrigger(), end.getButton());
		this.start = start;
		this.end = end;

		// スワイプの速度を検出
		long dt = end.getWhen() - start.getWhen();
		int dx = Math.abs(start.getX() - end.getX());
//		if (dt == 0) throw new IllegalArgumentException("dt");
		speed = (double)dx / dt;

		// スワイプの方向を検出
		dir = start.getX() < end.getX() ? SwipeDirection.LEFT : SwipeDirection.RIGHT;
	}

	/**
	 * スワイプの幅を取得します。
	 * 右にスワイプした場合は正の値、左にスワイプした場合は負の値を返します。
	 * 
	 * @return スワイプの幅
	 */
	public int getSwipeWidth()
	{
		return end.getX() - start.getX();
	}

	/**
	 * スワイプの高さを取得します。
	 * 下にスワイプした場合は正の値、上にスワイプした場合は負の値を返します。
	 * 
	 * @return  スワイプの高さ
	 */
	public int getSwipeHeight()
	{
		return end.getY() - start.getY();
	}

	/**
	 * スワイプした領域を取得します。
	 * 
	 * @return スワイプした領域
	 */
	public Rectangle getSwipeBounds()
	{
		Rectangle bounds = new Rectangle(start.getPoint());
		bounds.add(end.getPoint());
		return bounds;
	}

	/**
	 * スワイプの速度を判定します。
	 * 
	 * @return speed スワイプの速度
	 */
	public double getSwipeSpeed()
	{
		return speed;
	}

	/**
	 * スワイプの方向を判定します。
	 * 
	 * @return dir スワイプの方向
	 */
	public SwipeDirection getSwipeDirection()
	{
		return dir;
	}

	/**
	 * スワイプの方向を記述するテキスト文字列を返します。
	 * 
	 * @return スワイプの方向を記述するテキスト文字列
	 * @see #getSwipeDirection()
	 */
	public String getSwipeDirectionText()
	{
		switch (dir)
		{
		case LEFT:
			return "LEFT";
		case RIGHT:
			return "RIGHT";
		case UP:
			return "UP";
		case DOWN:
			return "DOWN";
		default:
			return "NONE";
		}
	}

	/**
	 * 左スワイプかどうか判定します。
	 * 
	 * @return 左スワイプの場合は true
	 */
	public boolean isSwipeLeft()
	{
		return dir == SwipeDirection.LEFT;
	}

	/**
	 * 右スワイプかどうか判定します。
	 * 
	 * @return 右スワイプの場合は true
	 */
	public boolean isSwipeRight()
	{
		return dir == SwipeDirection.RIGHT;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(3);

		return new StringBuffer()
			.append(getClass().getName())
			.append("[")
			.append(getSwipeDirectionText())
			.append(",")
			.append(df.format(getSwipeSpeed()))
			.append(",")
			.append("(")
			.append(end.getX())
			.append(",")
			.append(end.getY())
			.append(")")
			.append("] on ")
			.append(getComponent().getName())
			.toString();
	}
}
