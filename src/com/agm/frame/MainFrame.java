package com.agm.frame;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import com.agm.gui.LoginDialog;

public class MainFrame extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					final MainFrame mainFrame = new MainFrame();
					mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		final LoginDialog loginDialog = new LoginDialog(this, true);
		loginDialog.setVisible(true);
		if (loginDialog.isSucceeded()) {
			this.showMenuBar();
			this.setVisible(true);
		}
	}

	private void showMenuBar() {
		// create a menu bar
		final JMenuBar menuBar = new JMenuBar();

		// create menus
		final JMenu fileMenu = new JMenu("File");
		final JMenu editMenu = new JMenu("Edit");
		final JMenu aboutMenu = new JMenu("About");
		final JMenu linkMenu = new JMenu("Links");

		// create menu items
		final JMenuItem newMenuItem = new JMenuItem("New");
		newMenuItem.setMnemonic(KeyEvent.VK_N);
		newMenuItem.setActionCommand("New");

		final JMenuItem openMenuItem = new JMenuItem("Open");
		openMenuItem.setActionCommand("Open");

		final JMenuItem saveMenuItem = new JMenuItem("Save");
		saveMenuItem.setActionCommand("Save");

		final JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.setActionCommand("Exit");

		final JMenuItem cutMenuItem = new JMenuItem("Cut");
		cutMenuItem.setActionCommand("Cut");

		final JMenuItem copyMenuItem = new JMenuItem("Copy");
		copyMenuItem.setActionCommand("Copy");

		final JMenuItem pasteMenuItem = new JMenuItem("Paste");
		pasteMenuItem.setActionCommand("Paste");

		final MenuItemListener menuItemListener = new MenuItemListener();

		newMenuItem.addActionListener(menuItemListener);
		openMenuItem.addActionListener(menuItemListener);
		saveMenuItem.addActionListener(menuItemListener);
		exitMenuItem.addActionListener(menuItemListener);
		cutMenuItem.addActionListener(menuItemListener);
		copyMenuItem.addActionListener(menuItemListener);
		pasteMenuItem.addActionListener(menuItemListener);

		final JCheckBoxMenuItem showWindowMenu = new JCheckBoxMenuItem("Show About", true);
		showWindowMenu.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (showWindowMenu.getState()) {
					menuBar.add(aboutMenu);
				} else {
					menuBar.remove(aboutMenu);
				}
			}
		});

		final JRadioButtonMenuItem showLinksMenu = new JRadioButtonMenuItem("Show Links", true);
		showLinksMenu.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (menuBar.getMenu(3) != null) {
					menuBar.remove(linkMenu);
					repaint();
				} else {
					menuBar.add(linkMenu);
					repaint();
				}
			}
		});

		// add menu items to menus
		fileMenu.add(newMenuItem);
		fileMenu.add(openMenuItem);
		fileMenu.add(saveMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(showWindowMenu);
		fileMenu.addSeparator();
		fileMenu.add(showLinksMenu);
		fileMenu.addSeparator();
		fileMenu.add(exitMenuItem);
		editMenu.add(cutMenuItem);
		editMenu.add(copyMenuItem);
		editMenu.add(pasteMenuItem);

		// add menu to menubar
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(aboutMenu);
		menuBar.add(linkMenu);

		// add menubar to the frame
		this.setJMenuBar(menuBar);
	}

	class MenuItemListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// statusLabel.setText(e.getActionCommand()
			// + " JMenuItem clicked.");
		}
	}
}
