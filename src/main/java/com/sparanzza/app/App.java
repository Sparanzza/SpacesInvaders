package com.sparanzza.app;

import com.sparanzza.ui.GameMainFrame;

import java.awt.*;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			new GameMainFrame();
		});
	}
}
