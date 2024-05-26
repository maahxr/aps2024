package Menu;

import Main.Game;
import Menu.Background;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuState extends GameState {
	
	private Background bg;
	
	private int currentChoice = 0;
	private String[] options = {
		"Pombo Mensageiro",
		"Gato Ambientalista",
		"Esquilo do Jardim",
		"SAIR"
	};
	
	private Color titleColor;
	private Font titleFont;
	
	private Font font;
	private Font fontOption;

	
	public MenuState(GameStateManager gsm) {
		
		this.gsm = gsm;
		
		try {
			
			bg = new Background("/Backgrounds/fundojogooficial.png", 1);
			bg.setVector(-0.1, 0);
			
			titleColor = new Color(238, 0, 0);
			titleFont = new Font(
					"Stencil",
					Font.PLAIN,
					18);
			
			font = new Font("Arial", Font.PLAIN, 12);
			fontOption = new Font("Arial", Font.PLAIN, 12);

			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public void update() {
		bg.update();
	}
	
	public void draw(Graphics2D g) {
		
		// Arte - Background
		bg.draw(g);
		
		// Arte - Título - Mudar de cor
		g.setColor(Color.BLACK);
		g.setFont(titleFont);
		g.drawString("Missão Metrópole Verde", 42, 65);
		
		// Arte - Opções do menu - MUDAR DE COR
		g.setFont(fontOption);
		for(int i = 0; i < options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.GREEN.darker());
			}
			else {
				g.setColor(Color.BLACK);
			}
			g.drawString(options[i], 105, 128 + i * 25);
		}
		
	}
	
	private void select() {
		
		if(currentChoice == 0) {
			// Pombo Mensageiro 
			Game.simple();
		}
		if(currentChoice == 1) {
			// Gato Ambientalista
            Game.kitty();
		}
		if(currentChoice == 2) {
			// Esquilo dos Jardins
            Game.star();
		}
		if(currentChoice == 3) {
			// Sair
			System.exit(0);
		}
	}
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER){
			select();
		}
		if(k == KeyEvent.VK_UP) {
			currentChoice--;
			if(currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		if(k == KeyEvent.VK_DOWN) {
			currentChoice++;
			if(currentChoice == options.length) {
				currentChoice = 0;
			}
		}
	}
	public void keyReleased(int k) {}
	
}
