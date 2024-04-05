package view;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.myController;
import model.myModel;

import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class pad extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private myModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pad frame = new pad();
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
	public pad() {
		this.model = new myModel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(0, 21, 434, 240);
		contentPane.add(textArea);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 434, 22);
		contentPane.add(menuBar);

		JButton btnNewButton = new JButton("Open");
		btnNewButton.addActionListener(		
				(e)->{
					JFileChooser fc = new JFileChooser();

					int returnVal = fc.showOpenDialog(btnNewButton);

					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = fc.getSelectedFile();
						model.setFileName(file.getAbsolutePath());
						textArea.setText(readFile(model.getFileName()));
					} else {
						System.out.println("Open command cancelled by user. \n");
					}
				}	
		);
		menuBar.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Save");
		btnNewButton_1.addActionListener(
				(e)->{
					model.setContent(textArea.getText());
					JFileChooser fc = new JFileChooser();

					int returnVal = fc.showSaveDialog(btnNewButton_1);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = fc.getSelectedFile();

						writeFile(file.getAbsolutePath(), model.getContent());
					} else {
						System.out.println("Save command cancelled by user. \n");
					}
				}
		);
		menuBar.add(btnNewButton_1);
		this.setVisible(true);
	}

	public static String readFile(String name) {
		String content = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(name));
			String line;
			while ((line = reader.readLine()) != null) {
				content += line + "\n";
			}
			reader.close();
			return content;
		} catch (IOException e) {
			System.out.println("Error reading file");
			return "";
		}
	}

	public static void writeFile(String name, String content) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(name));
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			System.out.println("Error writing to file");
		}
	}
}
