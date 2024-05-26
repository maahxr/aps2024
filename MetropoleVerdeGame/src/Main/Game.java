package Main;

import Gameplay.GatoAmbientalista;
import Gameplay.PomboMensageiro;
import Gameplay.EsquiloDoJardim;

import javax.swing.JFrame;
import java.awt.*;

public class Game {
	
	public static void main(String[] args) {
		
		//nome da tela na parte superior esquerda da janela
		JFrame window = new JFrame("Missão Metrópole Verde");
		window.setContentPane(new GamePanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);

	}
	public static void simple() {
        JFrame gameplay = new JFrame("Pombo Mensageiro da Limpeza");
        PomboMensageiro PomboMensageiro = new PomboMensageiro();

        gameplay.setBounds(320, 240, 905, 700);
        gameplay.setBackground(new Color(56, 36, 83));
        gameplay.setResizable(false);
        gameplay.setVisible(true);
        gameplay.setLocationRelativeTo(null);
        gameplay.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        gameplay.add(PomboMensageiro);
    }

    public static void kitty() {
        JFrame gameplay = new JFrame("Gato da Rua Ambientalista");
        GatoAmbientalista gatoAmbientalista = new GatoAmbientalista();

        gameplay.setBounds(320, 240, 905, 700);
        gameplay.setBackground(new Color(56, 36, 83));
        gameplay.setResizable(false);
        gameplay.setVisible(true);
        gameplay.setLocationRelativeTo(null);
        gameplay.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        gameplay.add(gatoAmbientalista);
    }

    public static void star() {
        JFrame gameplay = new JFrame("Esquilo Acrobata dos Jardins");
        EsquiloDoJardim esquiloDoJardim = new EsquiloDoJardim();

        gameplay.setBounds(320, 240, 905, 700);
        gameplay.setBackground(new Color(56, 36, 83));
        gameplay.setResizable(false);
        gameplay.setVisible(true);
        gameplay.setLocationRelativeTo(null);
        gameplay.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        gameplay.add(esquiloDoJardim);
    }

	
}
