package com.agm.comp;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class ClientInfoPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public ClientInfoPanel() {
		System.out.println("----------------------");
		JLabel lblTest = new JLabel("test");
		lblTest.setFont(new Font("Tahoma", Font.PLAIN, 61));
		add(lblTest);
		this.setVisible(true);
	}

}
