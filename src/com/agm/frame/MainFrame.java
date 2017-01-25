package com.agm.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.agm.comp.ClientInfoPanel;
import com.agm.comp.LoginDialog;
import com.agm.utils.Constants;

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
					mainFrame.setLayout(new BorderLayout());
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
		final JMenu clientMenu = new JMenu("Clients");
		final JMenu servicesMenu = new JMenu("Services");
		final JMenu fileMenu = new JMenu("Files");
		final JMenu logoutMenu = new JMenu("Logout");

		// create menu items
		// client menu
		final JMenuItem newMenuItem = new JMenuItem("Register New Client");
		newMenuItem.setMnemonic(KeyEvent.VK_N);
		newMenuItem.setActionCommand(Constants.NEW_CLIENT);
		
		final JMenuItem manageExistingMenuItem = new JMenuItem("Manage Existing Clients");
		manageExistingMenuItem.setActionCommand(Constants.MANAGE_CLIENT);

		final JMenuItem uploadMenuItem = new JMenuItem("Upload");
		uploadMenuItem.setActionCommand(Constants.UPLOAD_FILE);
		
		final JMenuItem downloadMenuItem = new JMenuItem("Download");
		downloadMenuItem.setActionCommand(Constants.DOWNLOAD_FILE);

		final MenuItemListener menuItemListener = new MenuItemListener();

		newMenuItem.addActionListener(menuItemListener);
		manageExistingMenuItem.addActionListener(menuItemListener);
		uploadMenuItem.addActionListener(menuItemListener);
		downloadMenuItem.addActionListener(menuItemListener);

//		final JCheckBoxMenuItem showWindowMenu = new JCheckBoxMenuItem("Show About", true);
//		showWindowMenu.addItemListener(new ItemListener() {
//			public void itemStateChanged(ItemEvent e) {
//				if (showWindowMenu.getState()) {
//					menuBar.add(fileMenu);
//				} else {
//					menuBar.remove(fileMenu);
//				}
//			}
//		});
//
//		final JRadioButtonMenuItem showLinksMenu = new JRadioButtonMenuItem("Show Links", true);
//		showLinksMenu.addItemListener(new ItemListener() {
//			public void itemStateChanged(ItemEvent e) {
//				if (menuBar.getMenu(3) != null) {
//					menuBar.remove(logoutMenu);
//					repaint();
//				} else {
//					menuBar.add(logoutMenu);
//					repaint();
//				}
//			}
//		});

		// add menu items to menus
		clientMenu.add(newMenuItem);
		clientMenu.add(manageExistingMenuItem);
		clientMenu.addSeparator();
//		clientMenu.add(showWindowMenu);
//		clientMenu.addSeparator();
//		clientMenu.add(showLinksMenu);
//		clientMenu.addSeparator();
		fileMenu.add(uploadMenuItem);
		fileMenu.add(downloadMenuItem);

		// add menu to menubar
		menuBar.add(clientMenu);
		menuBar.add(servicesMenu);
		menuBar.add(fileMenu);
		menuBar.add(logoutMenu);

		// add menubar to the frame
		this.setJMenuBar(menuBar);
		this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                e.getWindow().dispose();
                System.exit(0);
            }
        });
	}

	class MenuItemListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals(Constants.NEW_CLIENT)) {
				add(new ClientInfoPanel());
				repaint();
			} else if (e.getActionCommand().equals(Constants.MANAGE_CLIENT)) {
				repaint();
			}
			
		}
	}
}
