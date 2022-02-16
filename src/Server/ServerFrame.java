package Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.*;
import java.sql.SQLException;

public class ServerFrame extends JFrame implements ActionListener {
	JTextField jt;
	Server server;
	
	public ServerFrame() {
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		JPanel jp = new JPanel(new GridLayout(2,1));
		jp.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		JLabel jl = new JLabel("Client Prot");
		jl.setHorizontalAlignment(JLabel.CENTER);
		jt = new JTextField(10);
		
		jp.add(jl);
		jp.add(jt);
		
		JButton jb = new JButton("통신");
		jb.addActionListener(this);
		
		c.add(jp,BorderLayout.CENTER);
		c.add(jb,BorderLayout.EAST);
		
		
		setTitle("무한의계단 Server");
		setSize(300,100);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int port = Integer.parseInt(jt.getText());
		
			try {
				server = new Server(port);
				server.start();
			} catch (IOException | SQLException e1) {e1.printStackTrace();}
		
		
	}

}
