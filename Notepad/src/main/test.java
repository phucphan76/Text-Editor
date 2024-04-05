package main;

import javax.swing.UIManager;

import view.pad;

public class test {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new pad();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
