package ui;

import com.sparanzza.constants.Constants;

import javax.swing.*;
import java.awt.*;

import static com.sparanzza.constants.Constants.BOARD_HEIGHT;

public class GamePanel extends JPanel {
	public GamePanel() {
		initializeLayout();
	}
	
	private void initializeLayout() {
		setPreferredSize(new Dimension(Constants.BOARD_WIDTH, BOARD_HEIGHT));
	}
}
