package com.agm.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.agm.utils.Authentication;

public class LoginDialog extends JDialog {

	private final JTextField txtUsername = new JTextField(15);
	private final JPasswordField txtPwd = new JPasswordField();
	private final JLabel jlblStatus = new JLabel(" ");
	
	private boolean succeeded;

	public LoginDialog() {
		this(null, true);
	}

	/**
	 * Create the frame.
	 * 
	 */
	public LoginDialog(final JFrame parent, boolean modal) {
		super(parent, "AGM Client Info Login", modal);

		final JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final String userName = String.valueOf(txtUsername.getText());
				final String password = String.valueOf(txtPwd.getPassword());
				if (Authentication.hasValidCredentials(userName, password)) {
					parent.setVisible(true);
					setVisible(false);
					succeeded = true;
				} else {
					jlblStatus.setText("Invalid username or password");
					succeeded = false;
				}
			}
		});

		final JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				System.exit(0);
			}
		});

		final JPanel labelPanel = new JPanel(new GridLayout(2, 1));
		labelPanel.add(new JLabel("Username"));
		labelPanel.add(new JLabel("Password"));

		final JPanel inputPanel = new JPanel(new GridLayout(2, 1));
		inputPanel.add(this.txtUsername);
		inputPanel.add(this.txtPwd);

		final JPanel lblInputPanel = new JPanel();
		lblInputPanel.add(labelPanel);
		lblInputPanel.add(inputPanel);

		final JPanel buttonPanel = new JPanel();
		buttonPanel.add(btnLogin);
		buttonPanel.add(btnCancel);
		
		this.txtUsername.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	btnLogin.doClick();
		    }
		});
		this.txtPwd.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	btnLogin.doClick();
		    }
		});

		final JPanel layoutPanel = new JPanel(new BorderLayout());
		layoutPanel.add(buttonPanel, BorderLayout.CENTER);
		layoutPanel.add(this.jlblStatus, BorderLayout.NORTH);
		this.jlblStatus.setForeground(Color.RED);
		this.jlblStatus.setHorizontalAlignment(SwingConstants.CENTER);

		this.setLayout(new BorderLayout());
		this.add(lblInputPanel, BorderLayout.CENTER);
		this.add(layoutPanel, BorderLayout.SOUTH);
		this.pack();
		this.setLocationRelativeTo(null);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
    public String getUsername() {
        return txtUsername.getText().trim();
    }

    public String getPassword() {
        return new String(txtPwd.getPassword());
    }

    public boolean isSucceeded() {
        return succeeded;
    }
}
