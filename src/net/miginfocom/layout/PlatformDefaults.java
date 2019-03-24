package net.miginfocom.layout;

import javax.swing.*;
import java.util.HashMap;
/*
 * License (BSD):
 * ==============
 *
 * Copyright (c) 2004, Mikael Grev, MiG InfoCom AB. (miglayout (at) miginfocom (dot) com)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or other
 * materials provided with the distribution.
 * Neither the name of the MiG InfoCom AB nor the names of its contributors may be
 * used to endorse or promote products derived from this software without specific
 * prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE.
 *
 * @version 1.0
 * @author Mikael Grev, MiG InfoCom AB
 *         Date: 2006-sep-08
 */

/** Currently handles Windows and Mac OS X spacing.
 */
public final class PlatformDefaults
{
	private static volatile int MOD_COUNT = 0;

	private static final UnitValue LPX4 = new UnitValue(4, UnitValue.LPX, null);
	private static final UnitValue LPX7 = new UnitValue(7, UnitValue.LPX, null);
	private static final UnitValue LPX8 = new UnitValue(8, UnitValue.LPX, null);
	private static final UnitValue LPX9 = new UnitValue(9, UnitValue.LPX, null);
	private static final UnitValue LPX10 = new UnitValue(10, UnitValue.LPX, null);
	private static final UnitValue LPX11 = new UnitValue(11, UnitValue.LPX, null);
	private static final UnitValue LPX12 = new UnitValue(12, UnitValue.LPX, null);
	private static final UnitValue LPX14 = new UnitValue(14, UnitValue.LPX, null);
	private static final UnitValue LPX16 = new UnitValue(16, UnitValue.LPX, null);
	private static final UnitValue LPX20 = new UnitValue(20, UnitValue.LPX, null);

	private static final UnitValue LPY4 = new UnitValue(4, UnitValue.LPY, null);
	private static final UnitValue LPY7 = new UnitValue(7, UnitValue.LPY, null);
	private static final UnitValue LPY8 = new UnitValue(8, UnitValue.LPY, null);
	private static final UnitValue LPY9 = new UnitValue(9, UnitValue.LPY, null);
	private static final UnitValue LPY10 = new UnitValue(10, UnitValue.LPY, null);
	private static final UnitValue LPY11 = new UnitValue(11, UnitValue.LPY, null);
	private static final UnitValue LPY12 = new UnitValue(12, UnitValue.LPY, null);
	private static final UnitValue LPY14 = new UnitValue(14, UnitValue.LPY, null);
	private static final UnitValue LPY16 = new UnitValue(16, UnitValue.LPY, null);
	private static final UnitValue LPY20 = new UnitValue(20, UnitValue.LPY, null);

	public static final int WINDOWS_XP = 0;
	public static final int MAC_OSX = 1;
//	private static final int GNOME = 2;
//	private static final int KDE = 3;

	private static int CUR_PLAF = WINDOWS_XP;

	// Used for holding values.
	private final static UnitValue[] PANEL_INS = new UnitValue[4];
	private final static UnitValue[] DIALOG_INS = new UnitValue[4];

	private static String BUTTON_FORMAT = null;

	private static final HashMap<String, UnitValue> HOR_DEFS = new HashMap<String, UnitValue>(32);
	private static final HashMap<String, UnitValue> VER_DEFS = new HashMap<String, UnitValue>(32);
	private static BoundSize DEF_VGAP = null, DEF_HGAP = null;
	static BoundSize RELATED_X = null, RELATED_Y = null, UNRELATED_X = null, UNRELATED_Y = null;
	private static UnitValue BUTT_WIDTH = null;

	static {
		setPlatform(getCurrentPlatform());
		MOD_COUNT = 0;
	}

	/** Returns the platform that the JRE is running on currently.
	 * @return The platform that the JRE is running on currently. E.g. {@link #MAC_OSX} or {@link #WINDOWS_XP}.
	 */
	public static int getCurrentPlatform()
	{
		return System.getProperty("os.name").startsWith("Mac OS") ? MAC_OSX : WINDOWS_XP;
	}

	private PlatformDefaults()
	{
	}

	/** Set the defaults to the default for the platform
	 * @param plaf The platform. <code>PlatformDefaults.WINDOWS</code> or <code>PlatformDefaults.MAC_OSX</code>
	 */
	public static void setPlatform(int plaf)
	{
		if (plaf < WINDOWS_XP || plaf > MAC_OSX)
			throw new IllegalArgumentException("Unknown platform: " + plaf);

		CUR_PLAF = plaf;
		if (plaf == WINDOWS_XP) {
			setRelatedGap(LPX4, LPY4);
			setUnrelatedGap(LPX7, LPY9);
			setParagraphGap(LPX14, LPY7);
			setIndentGap(LPX9, LPY9);
			setGridCellGap(LPX4, LPY4);

			setMinimumButtonWidth(new UnitValue(75, UnitValue.PIXEL, null));
			setButtonOrder("L_E+U+YNBXOCAH_R");
			setDialogInsets(LPY11, LPX11, LPY11, LPX11);
			setPanelInsets(LPY7, LPX7, LPY7, LPX7);
		} else {
			setRelatedGap(LPX8, LPY8);
			setUnrelatedGap(LPX12, LPY12);
			setParagraphGap(LPX16, LPY12);
			setIndentGap(LPX10, LPY10);
			setGridCellGap(LPX8, LPY8);

			setMinimumButtonWidth(new UnitValue(68, UnitValue.PIXEL, null));
			setButtonOrder("L_HE+U+NYBXCOA_R");
			setDialogInsets(LPY14, LPX20, LPY20, LPX20);
			setPanelInsets(LPY16, LPX16, LPY16, LPX16);
		}
	}

	/** Returns the current platform
	 * @return <code>PlatformDefaults.WINDOWS</code> or <code>PlatformDefaults.MAC_OSX</code>
	 */
	public static int getPlatform()
	{
		return CUR_PLAF;
	}

	/** Sets gap value for components that are "related".
	 * @param x The value that will be transformed to pixels. If <code>null</code> the current value will not change.
	 * @param y The value that will be transformed to pixels. If <code>null</code> the current value will not change.
	 */
	public static void setRelatedGap(UnitValue x, UnitValue y)
	{
		setUnitValue(new String[] {"r", "rel", "related"}, x, y);

		RELATED_X = new BoundSize(x, "related!");
		RELATED_Y = new BoundSize(y, "related!");
	}

	/** Sets gap value for components that are "unrelated".
	 * @param x The value that will be transformed to pixels. If <code>null</code> the current value will not change.
	 * @param y The value that will be transformed to pixels. If <code>null</code> the current value will not change.
	 */
	public static void setUnrelatedGap(UnitValue x, UnitValue y)
	{
		setUnitValue(new String[] {"u", "unrel", "unrelated"}, x, y);

		UNRELATED_X = new BoundSize(x, "unrelated!");
		UNRELATED_Y = new BoundSize(y, "unrelated!");
	}

	/** Sets paragraph gap value for components.
	 * @param x The value that will be transformed to pixels. If <code>null</code> the current value will not change.
	 * @param y The value that will be transformed to pixels. If <code>null</code> the current value will not change.
	 */
	public static void setParagraphGap(UnitValue x, UnitValue y)
	{
		setUnitValue(new String[] {"p", "para", "paragraph"}, x, y);
	}

	/** Sets gap value for components that are "intended".
	 * @param x The value that will be transformed to pixels. If <code>null</code> the current value will not change.
	 * @param y The value that will be transformed to pixels. If <code>null</code> the current value will not change.
	 */
	public static void setIndentGap(UnitValue x, UnitValue y)
	{
		setUnitValue(new String[] {"i", "ind", "indent"}, x, y);
	}

	/** Sets gap between two cells in the grid. NOt that this is not a gap between component IN a cell, that has to be set
	 * on the component constraints.
	 * @param x The value that will be transformed to pixels. If <code>null</code> the current value will not change.
	 * @param y The value that will be transformed to pixels. If <code>null</code> the current value will not change.
	 */
	public static void setGridCellGap(UnitValue x, UnitValue y)
	{
		if (x != null)
			DEF_HGAP = new BoundSize(x, null);
		if (y != null)
			DEF_VGAP = new BoundSize(y, null);
		MOD_COUNT++;
	}

	/** Sets the recommended minimum button width.
	 * @param width The recommended minimum button width.
	 */
	public static void setMinimumButtonWidth(UnitValue width)
	{
		BUTT_WIDTH = width;
		MOD_COUNT++;
	}
	/** Returns the recommended minimum button width depending on the current set platform.
	 * @return The recommended minimum button width depending on the current set platform.
	 */
	public static UnitValue getMinimumButtonWidth()
	{
		return BUTT_WIDTH;
	}

	/** Returns the unit value associated with the unit. (E.i. "related" or "indent"). Must be lower case.
	 * @param unit The unit string.
	 * @return Tthe unit value associated with the unit. <code>null</code> for unrecognized units.
	 */
	public static UnitValue getUnitValueX(String unit)
	{
		return HOR_DEFS.get(unit);
	}

	/** Returns the unit value associated with the unit. (E.i. "related" or "indent"). Must be lower case.
	 * @param unit The unit string.
	 * @return Tthe unit value associated with the unit. <code>null</code> for unrecognized units.
	 */
	public static UnitValue getUnitValueY(String unit)
	{
		return VER_DEFS.get(unit);
	}

	/** Sets the unit value assiciated with a unit string. This may be used to store values for new unit strings
	 * or modify old. Note that if a built in unit (such as "related") is modified all versions of it must be
	 * set (I.e. "r", "rel" and "related"). The build in values will be reset to the default ones if the platform
	 * is re-set.
	 * @param unitStrings The unit strings. E.g. "mu", "myunit". Will be converted to lower case and trimmed. Not <code>null</code>.
	 * @param x The value for the horizontal dimension. If <code>null</code> the value is not changed.
	 * @param y The value for the vertical dimension. Might be same object as for <code>x</code>. If <code>null</code> the value is not changed.
	 */
	public static final void setUnitValue(String[] unitStrings, UnitValue x, UnitValue y)
	{
		for (int i = 0; i < unitStrings.length; i++) {
			String s = unitStrings[i].toLowerCase().trim();
			if (x != null)
				HOR_DEFS.put(s, x);
			if (y != null)
				VER_DEFS.put(s, y);
		}
		MOD_COUNT++;
	}

	/** Understands ("r", "rel", "related") OR ("u", "unrel", "unrelated") OR ("i", "ind", "indent") OR ("p", "para", "paragraph").
	 */
	static final int convertToPixels(float value, String unit, boolean isHor, float ref, ContainerWrapper parent, ComponentWrapper comp)
	{
		UnitValue uv = (isHor ? HOR_DEFS : VER_DEFS).get(unit);
		return uv != null ? Math.round(value * uv.getPixels(ref, parent, comp)) : UnitConverter.UNABLE;
	}

	/** Returns the order for the typical buttons in a standard button bar. It is one letter per button type.
	 * @return The button order.
	 * @see #setButtonOrder(String)
	 */
	public static final String getButtonOrder()
	{
		return BUTTON_FORMAT;
	}

	/** Sets the order for the typical buttons in a standard button bar. It is one letter per button type.
	 * <p>
	 * Letter in upper case will get the minimum button width that the {@link #getMinimumButtonWidth()} specifies
	 * and letters in lower case will get the width the corrent look&feel specifies.
	 * <p>
	 * Gaps will never be added to before the first component or after the last component. However, '+' (push) will be
	 * applied before and after as well, but with a minimum size of 0 if first/last so there will not be a gap
	 * before or after.
	 * <p>
	 * If gaps are explicitly set on buttons they will never be reduced, but they may be increased.
	 * <p>
	 * These are the characters that can be used:
	 * <ul>
	 * <li><code>'L'</code> - Buttons with this style tag will staticall end up on the left end of the bar.
	 * <li><code>'R'</code> - Buttons with this style tag will staticall end up on the right end of the bar.
	 * <li><code>'H'</code> - A tag for the "help" button that normally is supposed to be on the right.
	 * <li><code>'E'</code> - A tag for the "help2" button that normally is supposed to be on the left.
	 * <li><code>'Y'</code> - A tag for the "yes" button.
	 * <li><code>'N'</code> - A tag for the "no" button.
	 * <li><code>'X'</code> - A tag for the "next >" or "forward >" button.
	 * <li><code>'B'</code> - A tag for the "< back>" or "< previous" button.
	 * <li><code>'I'</code> - A tag for the "finish".
	 * <li><code>'A'</code> - A tag for the "apply" button.
	 * <li><code>'C'</code> - A tag for the "cancel" or "close" button.
	 * <li><code>'O'</code> - A tag for the "ok" button.
	 * <li><code>'U'</code> - All Uncategorized, Other, or "Unknown" buttons. Tag will be "other".
	 * <li><code>'+'</code> - A glue push gap that will take as much space as it can and at least an "unrelated" gap. (Platform dependant)
	 * <li><code>'_'</code> - (underscore) An "unrelated" gap. (Platform dependant)
	 * </ul>
	 * <p>
	 * Even though the style tags are normally applied to buttons this works with all components.
	 * <p>
	 * The normal style for MAC OS X is <code>"L_HE+U+FBI_NYCOA_R"</code> and for Windows is <code>"L_E+U+FBI_YNOCAH_R"</code>
	 * @param order The new button order for the current platform.
	 */
	public static final void setButtonOrder(String order)
	{
		BUTTON_FORMAT = order;
		MOD_COUNT++;
	}

	/** Returns the tag (used in the {@link CC}) for a char. The char is same as used in {@link #getButtonOrder()}.
	 * @param c The char. Must be lower case!
	 * @return The tag that corresponds to the char or <code>null</code> if the char is unrecognized.
	 */
	static final String getTagForChar(char c)
	{
		switch (c) {
			case 'o':
				return "ok";
			case 'c':
				return "cancel";
			case 'h':
				return "help";
			case 'e':
				return "help2";
			case 'y':
				return "yes";
			case 'n':
				return "no";
			case 'a':
				return "apply";
			case 'x':
				return "next";  // a.k.a forward
			case 'b':
				return "back";  // a.k.a. previous
			case 'i':
				return "finish";
			case 'l':
				return "left";
			case 'r':
				return "right";
			case 'u':
				return "other";
			default:
				return null;
		}
	}

	/** Returns the platform recommended inter-cell gap in the horizontal (x) dimension..
	 * @return The platform recommended inter-cell gap in the horizontal (x) dimension..
	 */
	public static BoundSize getGridGapX()
	{
		return DEF_HGAP;
	}

	/** Returns the platform recommended inter-cell gap in the vertical (x) dimension..
	 * @return The platform recommended inter-cell gap in the vertical (x) dimension..
	 */
	public static BoundSize getGridGapY()
	{
		return DEF_VGAP;
	}

	/** Returns the default dialog inset depending of the current platform.
	 * @param side top == 0, left == 1, bottom = 2, right = 3.
	 * @return The inset. Never <code>null</code>.
	 */
	public static UnitValue getDialogInsets(int side)
	{
		return DIALOG_INS[side];
	}

	/** Sets the default insets for a dialog. Values that are null will not be changed.
	 * @param top The top inset. May be <code>null</code>.
	 * @param left The left inset. May be <code>null</code>.
	 * @param bottom The bottom inset. May be <code>null</code>.
	 * @param right The right inset. May be <code>null</code>.
	 */
	public static void setDialogInsets(UnitValue top, UnitValue left, UnitValue bottom, UnitValue right)
	{
		if (top != null)
			DIALOG_INS[0] = top;
		if (left != null)
			DIALOG_INS[1] = left;
		if (bottom != null)
			DIALOG_INS[2] = bottom;
		if (right != null)
			DIALOG_INS[3] = right;

		MOD_COUNT++;
	}

	/** Returns the default panel inset depending of the current platform.
	 * @param side top == 0, left == 1, bottom = 2, right = 3.
	 * @return The inset. Never <code>null</code>.
	 */
	public static UnitValue getPanelInsets(int side)
	{
		return PANEL_INS[side];
	}

	/** Sets the default insets for a dialog. Values that are null will not be changed.
	 * @param top The top inset. May be <code>null</code>.
	 * @param left The left inset. May be <code>null</code>.
	 * @param bottom The bottom inset. May be <code>null</code>.
	 * @param right The right inset. May be <code>null</code>.
	 */
	public static void setPanelInsets(UnitValue top, UnitValue left, UnitValue bottom, UnitValue right)
	{
		if (top != null)
			PANEL_INS[0] = top;
		if (left != null)
			PANEL_INS[1] = left;
		if (bottom != null)
			PANEL_INS[2] = bottom;
		if (right != null)
			PANEL_INS[3] = right;

		MOD_COUNT++;
	}

	/** Returns the percentage used for alignment for labels (0 is left, 50 is center and 100 is right).
	 * @return The percentage used for alignment for labels
	 */
	public static float getLabelAlignPercentage()
	{
		return CUR_PLAF == MAC_OSX ? 1f : 0f;
	}

	/** Returns the default gap between two components that <b>are in the same cell</b>.
	 * @param comp The component that the gap is for. Never <code>null</code>.
	 * @param adjacentComp The adjacent component if any. May be <code>null</code>.
	 * @param adjacentSide Whan side the <code>adjacentComp</code> is on. {@link javax.swing.SwingUtilities#TOP} or
	 * {@link javax.swing.SwingUtilities#LEFT} or {@link javax.swing.SwingUtilities#BOTTOM} or {@link javax.swing.SwingUtilities#RIGHT}.
	 * @param tag The tag string that the component might be tagged with in the component constraints. May be <code>null</code>.
	 * @param isLTR If it is left-to-right.
	 * @return The default gap between two components or <code>null</code> if there should be no gap.
	 */
	static BoundSize getDefaultComponentGap(ComponentWrapper comp, ComponentWrapper adjacentComp, int adjacentSide, String tag, boolean isLTR)
	{
		if (adjacentComp == null)
			return null;

		// Compensated for right to left.
//		if (isLTR == false) {
//			adjacentSide += 2;
//			if (adjacentSide > SwingConstants.RIGHT)
//				adjacentSide -= 4;
//		}

		if (adjacentSide == SwingConstants.LEFT || adjacentSide == SwingConstants.TOP)
			return null;

		boolean isHor = adjacentSide == SwingConstants.LEFT || adjacentSide == SwingConstants.RIGHT;

		if (adjacentComp.getComponetType(false) == ComponentWrapper.TYPE_LABEL && comp.getComponetType(false) == ComponentWrapper.TYPE_TEXT_FIELD)
			return isHor ? UNRELATED_X : UNRELATED_Y;

		return isHor ? RELATED_X : RELATED_Y;
	}

	/** Returns how many times the defaults has been changed. This can be used as a light weight check to
	 * see if layout caches needs to be refreshed.
	 * @return How many times the defaults has been changed.
	 */
	public static int getModCount()
	{
		return MOD_COUNT;
	}

	/** Tells all layout manager instances to revalidate and recalculated everything.
	 */
	public void invalidate()
	{
		MOD_COUNT++;
	}
}