package com.p532.gamemaker.main;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;
import javax.swing.border.LineBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.p532.gamemaker.ui.DragPanel;
import com.p532.gamemaker.ui.LabelTransferHandler;

public class Test extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Test() {
		getContentPane().setForeground(Color.YELLOW);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(new GridLayout(1, 2, 0, 0));

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(3, 2, 0, 0));

		JLabel lblNewLabel_2 = new JLabel("Game name");
		panel_2.add(lblNewLabel_2);

		textField_1 = new JTextField();
		panel_2.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Sprite");
		panel_2.add(lblNewLabel_3);

		textField = new JTextField();
		panel_2.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Type");
		panel_2.add(lblNewLabel);

		textField_2 = new JTextField();
		panel_2.add(textField_2);
		textField_2.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		panel.add(scrollPane);

		LabelTransferHandler th = LabelTransferHandler.getInstance();
		MouseListener handler = new HandlerLeft();

		DragPanel panel_5 = new DragPanel();
		scrollPane.setViewportView(panel_5);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel_5.rowHeights = new int[] { 40, 0 };
		gbl_panel_5.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0 };
		gbl_panel_5.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel_5.setLayout(gbl_panel_5);
		JLabel label3 = new JLabel(new ImageIcon("C:\\Users\\Rohit\\Desktop\\downloadSmallIcon.png"));
		GridBagConstraints gbc_label3 = new GridBagConstraints();
		gbc_label3.anchor = GridBagConstraints.WEST;
		gbc_label3.insets = new Insets(0, 0, 0, 5);
		gbc_label3.gridx = 0;
		gbc_label3.gridy = 0;
		panel_5.add(label3, gbc_label3);
		JLabel label2 = new JLabel(new ImageIcon("C:\\Users\\Rohit\\Desktop\\downloadSmallIcon.png"));
		GridBagConstraints gbc_label2 = new GridBagConstraints();
		gbc_label2.anchor = GridBagConstraints.WEST;
		gbc_label2.insets = new Insets(0, 0, 0, 5);
		gbc_label2.gridx = 1;
		gbc_label2.gridy = 0;
		panel_5.add(label2, gbc_label2);
		JLabel label4 = new JLabel(new ImageIcon("C:\\Users\\Rohit\\Desktop\\downloadSmallIcon.png"));
		GridBagConstraints gbc_label4 = new GridBagConstraints();
		gbc_label4.anchor = GridBagConstraints.WEST;
		gbc_label4.insets = new Insets(0, 0, 0, 5);
		gbc_label4.gridx = 2;
		gbc_label4.gridy = 0;
		panel_5.add(label4, gbc_label4);
		JLabel label5 = new JLabel(new ImageIcon("C:\\Users\\Rohit\\Desktop\\downloadSmallIcon.png"));
		GridBagConstraints gbc_label5 = new GridBagConstraints();
		gbc_label5.anchor = GridBagConstraints.WEST;
		gbc_label5.gridx = 3;
		gbc_label5.gridy = 0;
		panel_5.add(label5, gbc_label5);
		panel_5.addMouseListener(handler);
		panel_5.setTransferHandler(th);
		panel_5.setPanelPos(1);

		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel_4.rowHeights = new int[] { 0, 0 };
		gbl_panel_4.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_4.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel_4.setLayout(gbl_panel_4);

		JPanel panel_6 = new JPanel();
		panel.add(panel_6);
		GridBagLayout gbl_panel_6 = new GridBagLayout();
		gbl_panel_6.columnWidths = new int[] { 49, 0, 0, 0 };
		gbl_panel_6.rowHeights = new int[] { 30, 0, 25 };
		gbl_panel_6.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0 };
		gbl_panel_6.rowWeights = new double[] { 0.0, 0.0, 0.0 };
		panel_6.setLayout(gbl_panel_6);

		JButton btnNewButton = new JButton("Save");
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 1;
		panel_6.add(btnNewButton, gbc_btnNewButton);

		JButton btnNewButton_1 = new JButton("Play");
		btnNewButton_1.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton_1.gridx = 3;
		gbc_btnNewButton_1.gridy = 1;
		panel_6.add(btnNewButton_1, gbc_btnNewButton_1);
		panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { panel_5 }));

		JPanel panel_7 = new JPanel();
		getContentPane().add(panel_7);
		panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.PAGE_AXIS));

		DragPanel panel_1 = new DragPanel();
		panel_7.add(panel_1);
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setForeground(Color.GRAY);
		panel_1.setPanelPos(2);
		panel_1.addMouseListener(handler);
		panel_1.setTransferHandler(th);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWeights = new double[] {};
		gbl_panel_1.rowWeights = new double[] {};
		panel_1.setLayout(gbl_panel_1);

		DragPanel panel_3 = new DragPanel();
		panel_7.add(panel_3);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panel_3.setBackground(Color.ORANGE);
		panel_3.setForeground(Color.ORANGE);
		panel_3.setPanelPos(3);
		panel_3.addMouseListener(handler);
		panel_3.setTransferHandler(th);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWeights = new double[] {};
		gbl_panel_3.rowWeights = new double[] {};
		panel_3.setLayout(gbl_panel_3);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
	}
}

class HandlerLeft extends MouseAdapter {
	@Override
	public void mousePressed(MouseEvent e) {

		if (e.getSource() instanceof DragPanel) {
			DragPanel p = (DragPanel) e.getSource();
			Component c = SwingUtilities.getDeepestComponentAt(p, e.getX(), e.getY());
			if (c != null && c instanceof JLabel) {
				p.draggingLabel = (JLabel) c;
				p.getTransferHandler().exportAsDrag(p, e, TransferHandler.MOVE);
			}
		}
	}
}
