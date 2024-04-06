package view;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import controller.myController;
import model.myModel;

import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
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
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;

public class pad extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private myModel model;
	private JTextComponent textArea;

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
		setBounds(100, 100, 544, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 528, 30);
		contentPane.add(menuBar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 28, 528, 293);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);

		JButton btnNewButton = new JButton("Open");
		btnNewButton.addActionListener(		
				(e)->{
					JFileChooser fc = new JFileChooser();

					int returnVal = fc.showOpenDialog(btnNewButton);

					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = fc.getSelectedFile();
						model.setFileName(file.getAbsolutePath());
						model.setContent(readFile(model.getFileName()));
						textArea.setText(model.getContent());
					} else {
						System.out.println("Open command cancelled by user. \n");
					}
				}	
		);
		
		JButton btnNewButton_2 = new JButton("New");
		btnNewButton_2.addActionListener(
				(e)->{
					if(model.getContent()!=textArea.getText()) {
						int a = JOptionPane.showConfirmDialog(textArea, "Do you want to save the file?", "Select", JOptionPane.YES_NO_OPTION);
						if(a==0) {
							if(!model.getFileName().isEmpty()) {
								model.setContent(textArea.getText());
								writeFile(model.getFileName(), model.getContent());
							} else {
								JFileChooser fc = new JFileChooser();
								
								int returnVal = fc.showSaveDialog(btnNewButton_2);
								if (returnVal == JFileChooser.APPROVE_OPTION) {
									File file = fc.getSelectedFile();
									model.setFileName(file.getAbsolutePath());
									model.setContent(textArea.getText());
									writeFile(model.getFileName(), model.getContent());
								} else {
									System.out.println("Save command cancelled by user. \n");
								}
							}
						}
					}
					textArea.setText("");
					model.setContent("");
					model.setFileName("");
				}
		);
		menuBar.add(btnNewButton_2);
		menuBar.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Save");
		btnNewButton_1.addActionListener(
				(e)->{
					int a = JOptionPane.showConfirmDialog(textArea, "Do you want to save the file?", "Select", JOptionPane.YES_NO_OPTION);
					if(a == 0) {
						if(!model.getFileName().isEmpty()) {
							model.setContent(textArea.getText());
							writeFile(model.getFileName(), model.getContent());
						} else {
							JFileChooser fc = new JFileChooser();
						
							int returnVal = fc.showSaveDialog(btnNewButton_1);
							if (returnVal == JFileChooser.APPROVE_OPTION) {
								File file = fc.getSelectedFile();
								model.setFileName(file.getAbsolutePath());
								model.setContent(textArea.getText());
								writeFile(model.getFileName(), model.getContent());
							} else {
								System.out.println("Save command cancelled by user. \n");
							}
						}	
					}
				}
		);
		menuBar.add(btnNewButton_1);
		
		JButton btnNewButton_3 = new JButton("Save As");
		btnNewButton_3.addActionListener(
				(e)->{
					JFileChooser fc = new JFileChooser();
					
					int returnVal = fc.showSaveDialog(btnNewButton_1);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = fc.getSelectedFile();
						model.setFileName(file.getAbsolutePath());
						model.setContent(textArea.getText());
						writeFile(model.getFileName(), model.getContent());
					} else {
						System.out.println("Save command cancelled by user. \n");
					}
				}
		);
		menuBar.add(btnNewButton_3);
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
