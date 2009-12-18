/*
 * Copyright (c) 2009 tarchan. All rights reserved.
 */
package com.mac.tarchan.desktop;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.RootPaneContainer;
import javax.swing.table.JTableHeader;

/**
 * SexyControl
 * 
 * <p>
 * The following properties are available as of J2SE 5.0 on Mac OS X 10.5.
 * All properties are safely ignored by earlier versions of Java and earlier versions of Mac OS X.
 * </p>
 * <p>
 * <b>IMPORTANT:</b> The typical behavior of the corresponding JComponent should be expected for entries where a default value is not specified.
 * </p>
 * 
 * @author tarchan
 * @see <a href="http://developer.apple.com/mac/library/technotes/tn2007/tn2196.html">Technical Note TN2196: New Control Styles available within J2SE 5.0 on Mac OS X 10.5</a>
 */
public class SexyControl
{
	/**
	 * JComponent.sizeVariant
	 * 
	 * @see JComponentSizeVariant
	 */
	public static final String JComponentSizeVariantKey = "JComponent.sizeVariant";

	/**
	 * JComponent.sizeVariant
	 * 
	 * <pre>
	 * Type: {@link String}
	 * Allowed Values: "regular", "small", "mini"
	 * Default Value: "regular"
	 * Applies to: JLabel, JButton, JToggleButton, JCheckBox, JRadioButton, JComboBox, JProgressBar, JSlider, JTextField, JSpinner
	 * This property alters the components font size and appearance to provide a small or miniature variant of the control.
	 * </pre>
	 */
	public enum JComponentSizeVariant
	{
		/**
		 * <img src="http://developer.apple.com/mac/library/technotes/tn2007/images/tn2196_buttonR.png">
		 * <img src="http://developer.apple.com/mac/library/technotes/tn2007/images/tn2196_checkR.png">
		 * <img src="http://developer.apple.com/mac/library/technotes/tn2007/images/tn2196_radioR.png">
		 */
		regular,
		/**
		 * <img src="http://developer.apple.com/mac/library/technotes/tn2007/images/tn2196_buttonS.png">
		 * <img src="http://developer.apple.com/mac/library/technotes/tn2007/images/tn2196_checkS.png">
		 * <img src="http://developer.apple.com/mac/library/technotes/tn2007/images/tn2196_radioS.png">
		 */
		small,
		/**
		 * <img src="http://developer.apple.com/mac/library/technotes/tn2007/images/tn2196_buttonM.png">
		 * <img src="http://developer.apple.com/mac/library/technotes/tn2007/images/tn2196_checkM.png">
		 * <img src="http://developer.apple.com/mac/library/technotes/tn2007/images/tn2196_radioM.png">
		 */
		mini,
	}

	/**
	 * JButton.buttonType
	 * 
	 * @see JButtonButtonType
	 */
	public static final String JButtonButtonTypeKey = "JButton.buttonType";

	/**
	 * JButton.buttonType
	 * 
	 * <pre>
	 * Type: {@link String}
	 * Allowed Values: "square", "gradient", "bevel", "textured", "roundRect", "recessed", "help", "segmented", "segmentedRoundRect", "segmentedCapsule", "segmentedTextured", "segmentedGradient"
	 * Applies to: {@link JButton} and subclasses
	 * This property alters the visual style of the button.
	 * <b>WARNING:</b> The "help" style is not designed to accommodate text or icons. Specifying this style produces the standard Mac OS X help button.
	 * <b>IMPORTANT:</b> Segmented styles must be accompanied by the appropriate JButton.segmentPosition client property in order to get the correct visual appearance.
	 * </pre>
	 */
	public enum JButtonButtonType
	{
		/**
		 * <img src="http://developer.apple.com/mac/library/technotes/tn2007/images/tn2196_square.png">
		 */
		square,
		/**
		 * <img src="http://developer.apple.com/mac/library/technotes/tn2007/images/tn2196_gradient.png">
		 */
		gradient,
		/**
		 * <img src="http://developer.apple.com/mac/library/technotes/tn2007/images/tn2196_bevel.png">
		 */
		bevel,
		/**
		 * <img src="http://developer.apple.com/mac/library/technotes/tn2007/images/tn2196_textured.png">
		 */
		textured,
		/**
		 * <img src="http://developer.apple.com/mac/library/technotes/tn2007/images/tn2196_roundrect.png">
		 */
		roundRect,
		/**
		 * <img src="http://developer.apple.com/mac/library/technotes/tn2007/images/tn2196_recessed.png">
		 */
		recessed,
		/**
		 * <img src="http://developer.apple.com/mac/library/technotes/tn2007/images/tn2196_help.png">
		 */
		help,
		/**
		 * <img src="http://developer.apple.com/mac/library/technotes/tn2007/images/tn2196_segmentedO.png">
		 */
		segmented,
		/**
		 * <img src="http://developer.apple.com/mac/library/technotes/tn2007/images/tn2196_roundRectO.png">
		 */
		segmentedRoundRect,
		/**
		 * <img src="http://developer.apple.com/mac/library/technotes/tn2007/images/tn2196_capsuleO.png">
		 */
		segmentedCapsule,
		/**
		 * <img src="http://developer.apple.com/mac/library/technotes/tn2007/images/tn2196_texturedO.png">
		 */
		segmentedTextured,
		/**
		 * segmentedGradient
		 * 
		 * @see <a href="http://lists.apple.com/archives/Java-dev/2008/May/msg00400.html">Re: + / - Buttons</a>
		 */
		segmentedGradient,
	}

	/**
	 * JButton.segmentPosition
	 * 
	 * @see JButtonSegmentPosition
	 */
	public static final String JButtonSegmentPositionKey = "JButton.segmentPosition";

	/**
	 * JButton.segmentPosition
	 * 
	 * <pre>
	 * Type: {@link String}
	 * Allowed Values: "first", "middle", "last", "only"
	 * Applies to: {@link JButton} and subclasses where the JButton.buttonType property has been set to "segmented", "segmentedRoundRect", "segmentedCapsule", or "segmentedTextured".
	 * </pre>
	 */
	public enum JButtonSegmentPosition
	{
		/**
		 * <img src="http://developer.apple.com/mac/library/technotes/tn2007/images/tn2196_segmentedF.png">
		 * <img src="http://developer.apple.com/mac/library/technotes/tn2007/images/tn2196_roundRectF.png">
		 * <img src="http://developer.apple.com/mac/library/technotes/tn2007/images/tn2196_capsuleF.png">
		 * <img src="http://developer.apple.com/mac/library/technotes/tn2007/images/tn2196_texturedF.png">
		 */
		first,
		/**
		 * <img src="http://developer.apple.com/mac/library/technotes/tn2007/images/tn2196_segmentedM.png">
		 * <img src="http://developer.apple.com/mac/library/technotes/tn2007/images/tn2196_roundRectM.png">
		 * <img src="http://developer.apple.com/mac/library/technotes/tn2007/images/tn2196_capsuleM.png">
		 * <img src="http://developer.apple.com/mac/library/technotes/tn2007/images/tn2196_texturedM.png">
		 */
		middle,
		/**
		 * <img src="http://developer.apple.com/mac/library/technotes/tn2007/images/tn2196_segmentedL.png">
		 * <img src="http://developer.apple.com/mac/library/technotes/tn2007/images/tn2196_roundRectL.png">
		 * <img src="http://developer.apple.com/mac/library/technotes/tn2007/images/tn2196_capsuleL.png">
		 * <img src="http://developer.apple.com/mac/library/technotes/tn2007/images/tn2196_texturedL.png">
		 */
		last,
		/**
		 * <img src="http://developer.apple.com/mac/library/technotes/tn2007/images/tn2196_segmentedO.png">
		 * <img src="http://developer.apple.com/mac/library/technotes/tn2007/images/tn2196_roundRectO.png">
		 * <img src="http://developer.apple.com/mac/library/technotes/tn2007/images/tn2196_capsuleO.png">
		 * <img src="http://developer.apple.com/mac/library/technotes/tn2007/images/tn2196_texturedO.png">
		 */
		only,
	}

	/**
	 * JComboBox.isPopDown
	 * 
	 * <pre>
	 * Type: {@link Boolean}
	 * Allowed Values: Boolean.TRUE, Boolean.FALSE
	 * Applies to: {@link JComboBox}
	 * This property alters the JComboBox's style to specify if it is intended to be a pop-down or a pop-up control. 
	 * </pre>
	 */
	public static final String JComboBoxIsPopDownKey = "JComboBox.isPopDown";

	/**
	 * JComboBox.isSquare
	 * 
	 * <pre>
	 * Type: {@link Boolean}
	 * Allowed Values: Boolean.TRUE, Boolean.FALSE
	 * Applies to: {@link JComboBox}
	 * This property changes the appearance of a JComboBox to have squared off edges.
	 * </pre>
	 */
	public static final String JComboBoxIsSquareKey = "JComboBox.isSquare";

	/**
	 * JProgressBar.style
	 * 
	 * @see JProgressBarStyle
	 */
	public static final String JProgressBarStyleKey = "JProgressBar.style";

	/**
	 * JProgressBar.style
	 * 
	 * <pre>
	 * Type: {@link String}
	 * Allowed Values: "circular"
	 * Applies to: {@link JProgressBar}
	 * This proper changes any indeterminate JProgressBar into a small circular spinning indeterminate progress indicator.
	 * </pre>
	 */
	public enum JProgressBarStyle
	{
		/**
		 * <img src="http://developer.apple.com/mac/library/technotes/tn2007/images/tn2196_circular.png">
		 */
		circular,
	}

	/**
	 * JTableHeader.selectedColumn
	 * 
	 * <pre>
	 * Type: {@link Number}
	 * Applies to: {@link JTableHeader}
	 * This property allows you to highlight the currently selected column of a JTable.
	 * The value should be set to the index of the column in the data model that you wish to be selected.
	 * </pre>
	 */
	public static final String JTableHeaderSelectedColumnKey = "JTableHeader.selectedColumn";

	/**
	 * JTableHeader.sortDirection
	 * 
	 * @see JTableHeaderSortDirection
	 */
	public static final String JTableHeaderSortDirectionKey = "JTableHeader.sortDirection";

	/**
	 * JTableHeader.sortDirection
	 * 
	 * <pre>
	 * Type: {@link String}
	 * Allowed Values: "ascending", "decending", null
	 * Default Value: null
	 * This property will put a small triangular sort indicator point up (decending) or down (ascending) in the selected column.
	 * Setting this property to null will clear the sort indicator.
	 * This property has no effect if JTableHeader.selectedColumn has not been specified.
	 * <b>Note:</b> The incorrect spelling "decending" for this properties allowed values is correct.
	 * Using the correctly spelled "descending" will have no effect in J2SE 5.0 on Mac OS X 10.5.0
	 * </pre>
	 */
	public enum JTableHeaderSortDirection
	{
		/** ascending */
		ascending,
		/** decending */
		decending,
	}

	/**
	 * JTextField.variant
	 * 
	 * @see JTextFieldVariant
	 */
	public static final String JTextFieldVariantKey = "JTextField.variant";

	/**
	 * JTextField.variant
	 * 
	 * <pre>
	 * Type: {@link String}
	 * Allowed Values: "search"
	 * Applies to: {@link JTextField}
	 * This property changes the text field into a search field.
	 * For additional functionality see the optional JTextField.Search.FindPopup, JTextField.Search.FindAction and JTextField.Search.CancelAction properties.
	 * </pre>
	 */
	public enum JTextFieldVariant
	{
		/**
		 * <img src="http://developer.apple.com/mac/library/technotes/tn2007/images/tn2196_searchfield.png">
		 */
		search,
	}

	/**
	 * JTextField.Search.FindPopup
	 * 
	 * <pre>
	 * Type: {@link JPopupMenu}
	 * Allowed Values: Any JPopupMenu
	 * Applies to: JTextField with the JTextField.variant property set to "search"
	 * Setting this property attaches the provided JPopupMenu to the search control, and alters the icon to represent that there is a popup menu.
	 * Requires the JTextField.variant to be set to "search".
	 * </pre>
	 */
	public static final String JTextFieldSearchFindPopupKey = "JTextField.Search.FindPopup";

	/**
	 * JTextField.Search.FindAction
	 * 
	 * <pre>
	 * Type: {@link ActionListener}
	 * Allowed Values: Any ActionListener
	 * Applies to: JTextField with the JTextField.variant property set to "search"
	 * Setting this property will trigger the provided ActionListener when the "find icon" in the search field is clicked.
	 * Requires the JTextField.variant to be set to "search".
	 * </pre>
	 */
	public static final String JTextFieldSearchFindActionKey = "JTextField.Search.FindAction";

	/**
	 * JTextField.Search.CancelAction
	 * 
	 * <pre>
	 * Type: {@link ActionListener}
	 * Allowed Values: Any ActionListener
	 * Applies to: JTextField with the JTextField.variant property set to "search"
	 * Setting this property will trigger the provided ActionListener when the "cancel icon" in the search field is clicked.
	 * Requires the JTextField.variant to be set to "search".
	 * <b>Note:</b> The cancel icon is only present when there is text in the search field.
	 * </pre>
	 */
	public static final String JTextFieldSearchCancelActionKey = "JTextField.Search.CancelAction";

	/**
	 * Window.documentModified
	 * 
	 * <pre>
	 * Type: {@link Boolean}
	 * Allowed Values: Boolean.TRUE, Boolean.FALSE
	 * Default Value: Boolean.FALSE
	 * Applies to: JRootPane, JInternalFrame
	 * This property adds the document dirty mark in the close button of the window.
	 * This is used to indicate that the document window has unsaved content, and attempts to close the window will request that the user save the document or discard changes.
	 * </pre>
	 */
	public static final String WindowDocumentModifiedKey = "Window.documentModified";

	/**
	 * Window.documentFile
	 * 
	 * <pre>
	 * Type: {@link java.io.File}
	 * Allowed Values: Any
	 * Applies to: JRootPane
	 * This property adds a document proxy icon to the title bar of the window.
	 * <b>WARNING:</b> This property only applies to JRootPanes that have a heavyweight peer.
	 * </pre>
	 */
	public static final String WindowDocumentFileKey = "Window.documentFile";

	/**
	 * Window.style
	 * 
	 * @see WindowStyle
	 */
	public static final String WindowStyleKey = "Window.style";

	/**
	 * Window.style
	 * 
	 * <pre>
	 * Type: {@link String}
	 * Allowed Values: "small"
	 * Applies to: JRootPane
	 * This property determines if the window has a Utility-style title bar.
	 * In order to make this window style also float above all others you must additionally call setAlwaysOnTop(true).
	 * Windows that have both the "small" style and are set to always be on top will automatically hide themselves when your application is no longer frontmost.
	 * This is similar to how native applications behave.
	 * <b>WARNING:</b> This property must be set before the heavyweight peer for the Window is created. Once addNotify() has been called on the component, causing creation of the heavyweight peer, changing this property has no effect.
	 * </pre>
	 */
	public enum WindowStyle
	{
		/** small */
		small,
	}

	/**
	 * Window.alpha
	 * 
	 * <pre>
	 * Type: {@link Float}
	 * Allowed Values: Any value from 0.0 to 1.0 inclusive
	 * Default Value: 1.0
	 * Applies to: JRootPane
	 * This property sets the opacity for the whole window and can be changed throughout the lifetime of the window.
	 * <b>WARNING:</b> This property only applies to JRootPanes that have a heavyweight peer.
	 * </pre>
	 */
	public static final String WindowAlphaKey = "Window.alpha";

	/**
	 * Window.shadow
	 * 
	 * <pre>
	 * Type: {@link Boolean}
	 * Allowed Values: Boolean.TRUE, Boolean.FALSE
	 * Default Value: Boolean.TRUE
	 * Applies to: JRootPane
	 * This property determines if the window has a shadow.
	 * <b>WARNING:</b> This property must be set before the heavyweight peer for the Window is created.
	 * Once addNotify() has been called on the component, causing creation of the heavyweight peer, changing this property has no effect.
	 * </pre>
	 */
	public static final String WindowShadowKey = "Window.shadow";

	/**
	 * apple.awt.windowShadow.revalidateNow
	 * 
	 * <pre>
	 * Type: {@link Object}
	 * Allowed Values: Any
	 * Applies to JRootPane
	 * Changing the value of this property causes the window's shadow to be recomputed based on the current window shape.
	 * The values used are inconsequential, as long as the new value is different from the current value.
	 * This property uses equals() to compare the equality of the two objects involved.
	 * <b>WARNING:</b> This property only applies to JRootPanes that have a heavyweight peer.
	 * <b>IMPORTANT:</b> This property requires the Window.shadow property to be explicitly set to Boolean.TRUE
	 * </pre>
	 */
	public static final String AppleAwtWindowShadowRrevalidateNowKey = "apple.awt.windowShadow.revalidateNow";

	/**
	 * apple.awt.brushMetalLook
	 * 
	 * <pre>
	 * Type: {@link Boolean}
	 * Allowed Values: Boolean.TRUE, Boolean.FALSE
	 * Default Value: Undefined
	 * Applies to: JRootPane
	 * This property determines if the window should use the Brushed Metal texture.
	 * This client property overrides the corresponding system property, which determines its default value.
	 * <b>WARNING:</b> This property must be set before the heavyweight peer for the Window is created. Once addNotify() has been called on the component, causing creation of the heavyweight peer, changing this property has no effect.
	 * </pre>
	 */
	public static final String AppleAwtBrushMetalLookKey = "apple.awt.brushMetalLook";

	/**
	 * apple.awt.draggableWindowBackground
	 * 
	 * <pre>
	 * Type: {@link Boolean}
	 * Allowed Values: Boolean.TRUE, Boolean.FALSE
	 * Default Value: Boolean.FALSE
	 * Applies to: JRootPane
	 * Setting this property to Boolean.TRUE allows dragging of the Window via mouse-down on any part of the window that is not a heavyweight control.
	 * This client property overrides the system property.
	 * <b>WARNING:</b> This property must be set before the heavyweight peer for the Window is created.
	 * Once addNotify() has been called on the component, causing creation of the heavyweight peer, changing this property has no effect.
	 * </pre>
	 */
	public static final String AppleAwtDraggableWindowBackgroundKey = "apple.awt.draggableWindowBackground";

	/**
	 * apple.awt.delayWindowOrdering
	 * 
	 * <pre>
	 * Type: {@link Boolean}
	 * Allowed Values: Boolean.TRUE, Boolean.FALSE
	 * Default Value: Boolean.FALSE
	 * Applies to: JRootPane
	 * This property determines if the window comes to front immediately during a drag and drop operation.
	 * If set to true, drag operations do not bring the window forward.
	 * The default value causes the window to be brought forward immediately.
	 * <b>WARNING:</b> This property must be set before the heavyweight peer for the Window is created.
	 * Once addNotify() has been called on the component, causing creation of the heavyweight peer, changing this property has no effect.
	 * </pre>
	 */
	public static final String AppleAwtDelayWindowOrderingKey = "apple.awt.delayWindowOrdering";

	/**
	 * apple.laf.useScreenMenuBar
	 */
	public static final String AppleLafUseScreenMenuBarKey = "apple.laf.useScreenMenuBar";

	/**
	 * com.apple.mrj.application.apple.menu.about.name
	 * 
	 * @see <a href="http://developer.apple.com/jp/technotes/tn2031.html">Java Runtime Properties for Mac OS X</a>
	 */
	public static final String AppleMenuAboutNameKey = "com.apple.mrj.application.apple.menu.about.name";

	/**
	 * setComponentSize
	 * 
	 * @param component コンポーネント
	 * @param size サイズ
	 */
	public static void setComponentSize(JComponent component, JComponentSizeVariant size)
	{
		component.putClientProperty(JComponentSizeVariantKey, size.name());
	}

	/**
	 * setButtonStyle
	 * 
	 * @param button ボタン
	 * @param style スタイル
	 */
	public static void setButtonStyle(AbstractButton button, JButtonButtonType style)
	{
		button.putClientProperty(JButtonButtonTypeKey, style.name());
	}

	/**
	 * setSegmentButtonStyle
	 * 
	 * @param button ボタン
	 * @param style スタイル
	 * @param position ポジション
	 */
	public static void setSegmentButtonStyle(AbstractButton button, JButtonButtonType style, JButtonSegmentPosition position)
	{
		setButtonStyle(button, style);
		button.putClientProperty(JButtonSegmentPositionKey, position.name());
	}

	/**
	 * setSegmentButtonGroup
	 * 
	 * @param group ボタングループ
	 * @param style スタイル
	 * @see #setSegmentButtonStyle(AbstractButton, JButtonButtonType, JButtonSegmentPosition)
	 */
	public static void setSegmentButtonStyle(ButtonGroup group, JButtonButtonType style)
	{
		int count = group.getButtonCount();
		Enumeration<AbstractButton> elements = group.getElements();
		if (count == 1)
		{
			AbstractButton only = elements.nextElement();
			setSegmentButtonStyle(only, style, JButtonSegmentPosition.only);
		}
		else if (count >= 2)
		{
			AbstractButton first = elements.nextElement();
			setSegmentButtonStyle(first, style, JButtonSegmentPosition.first);
			for (int i = 1; i < count - 1; i++)
			{
				AbstractButton middle = elements.nextElement();
				setSegmentButtonStyle(middle, style, JButtonSegmentPosition.middle);
			}
			AbstractButton last = elements.nextElement();
			setSegmentButtonStyle(last, style, JButtonSegmentPosition.last);
		}
		else
		{
			// 何もしない
		}
	}

	/**
	 * setJTextFieldStyle
	 * 
	 * @param textField テキストフィールド
	 * @param style スタイル
	 */
	public static void setTextFieldStyle(JTextField textField, JTextFieldVariant style)
	{
		textField.putClientProperty(JTextFieldVariantKey, style.name());
	}

	/**
	 * setSearchTextField
	 * 
	 * @param textField テキストフィールド
	 * @param popupMenu ポップアップメニュー
	 * @param find 検索アクション
	 * @param cancel キャンセルアクション
	 * @see #JTextFieldVariantKey
	 * @see #JTextFieldSearchFindPopupKey
	 * @see #JTextFieldSearchFindActionKey
	 * @see #JTextFieldSearchCancelActionKey
	 */
	public static void setSearchTextField(JTextField textField, JPopupMenu popupMenu, ActionListener find, ActionListener cancel)
	{
		setTextFieldStyle(textField, JTextFieldVariant.search);
		textField.putClientProperty(JTextFieldSearchFindPopupKey, popupMenu);
		textField.putClientProperty(JTextFieldSearchFindActionKey, find);
		textField.putClientProperty(JTextFieldSearchCancelActionKey, cancel);
	}

	/**
	 * setCircularProgressBar
	 * 
	 * @param progressBar プログレスバー
	 * @see JProgressBarStyle#circular
	 */
	public static void setCircularProgressBar(JProgressBar progressBar)
	{
		progressBar.setIndeterminate(true);
		progressBar.putClientProperty(JProgressBarStyleKey, JProgressBarStyle.circular.name());
	}

	/**
	 * setPopDownComboBox
	 * 
	 * @param comboBox コンボボックス
	 * @see #JComboBoxIsPopDownKey
	 */
	public static void setPopDownComboBox(JComboBox comboBox)
	{
		comboBox.putClientProperty(JComboBoxIsPopDownKey, true);
	}

	/**
	 * setSquareComboBox
	 * 
	 * @param comboBox コンボボックス
	 * @see #JComboBoxIsSquareKey
	 */
	public static void setSquareComboBox(JComboBox comboBox)
	{
		comboBox.putClientProperty(JComboBoxIsSquareKey, true);
	}

	/**
	 * setWindowDocumentModified
	 * 
	 * @param window ウインドウ
	 * @param modified ドキュメント変更
	 * @see #WindowDocumentModifiedKey
	 */
	public static void setWindowDocumentModified(RootPaneContainer window, boolean modified)
	{
		JRootPane rootPane = window.getRootPane();
		rootPane.putClientProperty(WindowDocumentModifiedKey, modified);
	}

	/**
	 * setWindowDocumentFile
	 * Requires heavyweight peer.
	 * 
	 * @param window ウインドウ
	 * @param file ファイル
	 * @see #WindowDocumentFileKey
	 */
	public static void setWindowDocumentFile(RootPaneContainer window, File file)
	{
		JRootPane rootPane = window.getRootPane();
		rootPane.putClientProperty(WindowDocumentFileKey, file);
	}

	/**
	 * setWindowStyle
	 * Must be set before the heavyweight peer is created.
	 * 
	 * @param window ウインドウ
	 * @param style スタイル
	 * @see #WindowStyleKey
	 */
	public static void setWindowStyle(RootPaneContainer window, WindowStyle style)
	{
		JRootPane rootPane = window.getRootPane();
		rootPane.putClientProperty(WindowStyleKey, style.name());
	}

	/**
	 * setWindowAlpha
	 * Requires heavyweight peer.
	 * 
	 * @param window ウインドウ
	 * @param alpha Any value from 0.0 to 1.0 inclusive
	 * @see #WindowAlphaKey
	 */
	public static void setWindowAlpha(RootPaneContainer window, double alpha)
	{
		JRootPane rootPane = window.getRootPane();
		rootPane.putClientProperty(WindowAlphaKey, alpha);
	}

	/**
	 * setWindowShadow
	 * 
	 * @param window ウインドウ
	 * @param shadow シャドウ
	 * @see #WindowShadowKey
	 */
	public static void setWindowShadow(RootPaneContainer window, boolean shadow)
	{
		JRootPane rootPane = window.getRootPane();
		rootPane.putClientProperty(WindowShadowKey, shadow);
	}

	/**
	 * useBrushMetalLook
	 * 
	 * @see #AppleAwtBrushMetalLookKey
	 */
	public static void useBrushMetalLook()
	{
		System.setProperty(AppleAwtBrushMetalLookKey, String.valueOf(true));
	}

	/**
	 * メニューバーをスクリーンに設定します。
	 * 
	 * @see #AppleLafUseScreenMenuBarKey
	 */
	public static void useScreenMenuBar()
	{
		System.setProperty(AppleLafUseScreenMenuBarKey, String.valueOf(true));
	}

	/**
	 * アプリケーション名を設定します。
	 * 
	 * @param aboutName アプリケーション名
	 * @see #AppleMenuAboutNameKey
	 */
	public static void setAppleMenuAboutName(String aboutName)
	{
		System.setProperty(AppleMenuAboutNameKey, aboutName);
	}
}
