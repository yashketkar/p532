package com.p532.gamemaker.ui;

import java.awt.Color;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceMotionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.activation.ActivationDataFlavor;
import javax.activation.DataHandler;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;

public class LabelTransferHandler extends TransferHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final DataFlavor localObjectFlavor;
	private static LabelTransferHandler instance;
	private boolean srcRemoval = false;

	@SuppressWarnings("serial")
	private final JLabel label = new JLabel() {
		@Override
		public boolean contains(int x, int y) {
			return false;
		}
	};
	private final JWindow window = new JWindow();

	private LabelTransferHandler() {
		System.out.println("LabelTransferHandler");

		localObjectFlavor = new ActivationDataFlavor(DragPanel.class, DataFlavor.javaJVMLocalObjectMimeType, "JLabel");

		window.add(label);
		window.setAlwaysOnTop(true);
		window.setBackground(new Color(0, true));

		DragSource.getDefaultDragSource().addDragSourceMotionListener(new DragSourceMotionListener() {
			@Override
			public void dragMouseMoved(DragSourceDragEvent dsde) {
				Point pt = dsde.getLocation();
				pt.translate(5, 5); // offset
				window.setLocation(pt);
			}
		});
	}

	public static LabelTransferHandler getInstance() {

		if (instance == null) {
			instance = new LabelTransferHandler();
		}
		return instance;
	}

	@Override
	protected Transferable createTransferable(JComponent c) {
		System.out.println("createTransferable");
		DragPanel p = (DragPanel) c;
		JLabel l = p.draggingLabel;
		String text = l.getText();

		final DataHandler dh = new DataHandler(c, localObjectFlavor.getMimeType());
		if (text == null)
			return dh;
		final StringSelection ss = new StringSelection(text + "\n");
		return new Transferable() {
			@Override
			public DataFlavor[] getTransferDataFlavors() {
				ArrayList<DataFlavor> list = new ArrayList<>();
				for (DataFlavor f : ss.getTransferDataFlavors()) {
					list.add(f);
				}
				for (DataFlavor f : dh.getTransferDataFlavors()) {
					list.add(f);
				}
				return list.toArray(dh.getTransferDataFlavors());
			}

			public boolean isDataFlavorSupported(DataFlavor flavor) {
				for (DataFlavor f : getTransferDataFlavors()) {
					if (flavor.equals(f)) {
						return true;
					}
				}
				return false;
			}

			public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
				if (flavor.equals(localObjectFlavor)) {
					return dh.getTransferData(flavor);
				} else {
					return ss.getTransferData(flavor);
				}
			}
		};
	}

	@Override
	public boolean canImport(TransferSupport support) {
		if (!support.isDrop()) {
			return false;
		}
		return true;
	}

	@Override
	public int getSourceActions(JComponent c) {
		System.out.println("getSourceActions");
		DragPanel p = (DragPanel) c;
		label.setIcon(p.draggingLabel.getIcon());
		label.setText(p.draggingLabel.getText());
		window.pack();
		Point pt = p.draggingLabel.getLocation();
		SwingUtilities.convertPointToScreen(pt, p);
		window.setLocation(pt);
		window.setVisible(true);
		return MOVE;
	}

	@Override
	public boolean importData(TransferSupport support) {
		System.out.println("importData");
		if (!canImport(support))
			return false;
		JPanel target = null;

		target = (DragPanel) support.getComponent();

		try {
			DragPanel src = (DragPanel) support.getTransferable().getTransferData(localObjectFlavor);
			JLabel l = new JLabel();
			l.setIcon(src.draggingLabel.getIcon());
			l.setText(src.draggingLabel.getText());

			if ((((DragPanel) target).getPanelPos() == 3)) {
				System.out.println("hello " + ((DragPanel) target).getPanelPos());
				target.remove(l);
				srcRemoval = true;
			}
			if (((DragPanel) target).getPanelPos() == 2) {
				System.out.println("added " + ((DragPanel) target).getPanelPos());
				target.add(l);

			}
			if (src == target && src.getPanelPos() != 2) {
				System.out.println("same with " + src.getPanelPos());
				System.out.println("same");
				target.remove(l);
			}
			target.revalidate();
			return true;
		} catch (UnsupportedFlavorException ufe) {
			ufe.printStackTrace();
		} catch (java.io.IOException ioe) {
			ioe.printStackTrace();
		}
		return false;
	}

	@Override
	protected void exportDone(JComponent c, Transferable data, int action) {
		System.out.println("exportDone");
		DragPanel src = (DragPanel) c;
		if (action == TransferHandler.MOVE) {

			if (srcRemoval && src.getPanelPos() != 1) {
				src.remove(src.draggingLabel);
			}
			srcRemoval = false;
			src.revalidate();
			src.repaint();
		}
		src.draggingLabel = null;
		window.setVisible(false);
	}
}