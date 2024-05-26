package Gameplay;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class EsquiloDoJardim extends JPanel implements KeyListener, ActionListener{

    public int time;

    private int[] esquiloxlength = new int[750];
    private int[] esquiloylength = new int[750];

    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    private static boolean gameOver = false;

    private ImageIcon rightmouth;
    private ImageIcon upmouth;
    private ImageIcon downmouth;
    private ImageIcon leftmouth;
    private ImageIcon esquiloimage;


    private int lengthesquilo = 3;

    private Timer timer;
    private int delay = 100;


    private int[] Arvorexpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,
            350,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
    private int[] Florxpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,
            350,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
    private int[] Serraxpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,
            350,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
    private int[] Venenoxpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,
            350,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};

    private int[] objetosypos = {75,100,125,150,175,200,225,250,400,425,450,
            475,500,525,550,575};

    private int[] Desmatamentoxpos = {375, 400, 425, 450, 475};
    private int[] Desmatamentoypos = {275, 300, 325, 350, 375};

    private ImageIcon Arvore;
    private ImageIcon Flor;
    private ImageIcon Serra;
    private ImageIcon Veneno;

    private ImageIcon DesmatamentoObstaculo;

    private Random random = new Random();
    private int xpos = random.nextInt(29);
    private int ypos = random.nextInt(18);


    private Random objetoAleatorio = new Random();
    private int PopsUp = objetoAleatorio.nextInt(15);


    private int score = 0;

    private int moves = 0;

    private ImageIcon titleImage;
    
    private Image backgroundImage;


    public EsquiloDoJardim() {
        time = 0;

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
        
     // Carregando a imagem de fundo
        backgroundImage = new ImageIcon("Resources/Backgrounds/backgroundEsquilo.png").getImage();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Desenhando a imagem de fundo
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    public void paint(Graphics g) {
    	
        super.paint(g); // Chama paintComponent


        if (moves == 0) {
            esquiloxlength[2] = 50;
            esquiloxlength[1] = 75;
            esquiloxlength[0] = 100;

            esquiloylength[2] = 100;
            esquiloylength[1] = 100;
            esquiloylength[0] = 100;
        }

        // Arte - Borda
        g.setColor(Color.black);
        g.drawRect(24, 10, 851, 55);

        // Arte - Título superior
        titleImage = new ImageIcon("Resources/Backgrounds/missaos.png");
        titleImage.paintIcon(this, g, 25, 11 );

        // Arte - Pontuação
        g.setColor(Color.GREEN.darker());
        g.setFont(new Font("arial", Font.PLAIN, 18));
        g.drawString("PONTUAÇÃO: " + score, 710, 40);

        // Arte - Tamanho da "pombo"
        g.setColor(Color.GREEN.darker());
        g.setFont(new Font("arial", Font.PLAIN, 18));
        g.drawString("TAMANHO: " + lengthesquilo, 40, 40);


        rightmouth = new ImageIcon("Resources/Characters/esquilo.png");
        rightmouth.paintIcon(this, g, esquiloxlength[0], esquiloylength[0]);


        for (int a = 0; a < lengthesquilo; a++) {
            if (a==0 && right) {
                rightmouth = new ImageIcon("Resources/Characters/esquilo.png");
                rightmouth.paintIcon(this, g, esquiloxlength[a], esquiloylength[a]);
            }
            if (a==0 && left) {
                leftmouth = new ImageIcon("Resources/Characters/esquilo.png");
                leftmouth.paintIcon(this, g, esquiloxlength[a], esquiloylength[a]);
            }
            if (a==0 && down) {
                downmouth = new ImageIcon("Resources/Characters/esquilo.png");
                downmouth.paintIcon(this, g, esquiloxlength[a], esquiloylength[a]);
            }
            if (a==0 && up) {
                upmouth = new ImageIcon("Resources/Characters/esquilo.png");
                upmouth.paintIcon(this, g, esquiloxlength[a], esquiloylength[a]);
            }
            if (a!=0) {
                esquiloimage = new ImageIcon("Resources/Characters/esquilo.png");
                esquiloimage.paintIcon(this, g, esquiloxlength[a], esquiloylength[a]);
            }
        }


        if (PopsUp <= 6) {
            // Simple Fruit
            Arvore = new ImageIcon("Resources/Objetos/arvore.png");
            if ((Arvorexpos[xpos] == esquiloxlength[0] && objetosypos[ypos] == esquiloylength[0])) {
                score += 2;
                lengthesquilo++;
                time = 0;
                xpos = random.nextInt(29);
                ypos = random.nextInt(16);

                PopsUp = objetoAleatorio.nextInt(15);
            }else if (time >= 90) {
                xpos = random.nextInt(29);
                ypos = random.nextInt(16);
                PopsUp = objetoAleatorio.nextInt(15);
                time = 0;
            }
            Arvore.paintIcon(this, g, Arvorexpos[xpos], objetosypos[ypos]);
        }
        else if (PopsUp > 6 && PopsUp <= 9 ) {
            // Big Fruit
            Flor = new ImageIcon("Resources/Objetos/flor.png");
            if ((Florxpos[xpos] == esquiloxlength[0] && objetosypos[ypos] == esquiloylength[0])) {
                score += 4;
                time = 0;
                lengthesquilo++;
                xpos = random.nextInt(29);
                ypos = random.nextInt(16);

                PopsUp = objetoAleatorio.nextInt(15);
            }else if (time >= 90) {
                xpos = random.nextInt(29);
                ypos = random.nextInt(16);
                PopsUp = objetoAleatorio.nextInt(15);
                time = 0;
            }

            Flor.paintIcon(this, g, Florxpos[xpos], objetosypos[ypos]);
        }
        else if (PopsUp > 9 && PopsUp <= 13 ) {
            // Bomb Fruit
            Veneno = new ImageIcon("Resources/Objetos/veneno.png");
            if ((Venenoxpos[xpos] == esquiloxlength[0] && objetosypos[ypos] == esquiloylength[0])) {
                right = false;
                left = false;
                up = false;
                down = false;
                gameOver = true;

                g.setColor(Color.RED);
                g.setFont(new Font("arial", Font.BOLD, 50));
                g.drawString("GAME OVER", 298, 240);

                g.setFont(new Font("arial", Font.BOLD, 20));
                g.drawString("Aperte 'SPACE' para REINICIAR", 300, 273);
                
            }else if (time >= 90) {
                // Tempo de desaparecimento da fruta
                xpos = random.nextInt(29);
                ypos = random.nextInt(16);
                PopsUp = objetoAleatorio.nextInt(15);
                time = 0;
            }
            Veneno.paintIcon(this, g, Venenoxpos[xpos], objetosypos[ypos]);
        }
        else {
            // Decrease Fruit
            Serra = new ImageIcon("Resources/Objetos/serra.png");
            if ((Serraxpos[xpos] == esquiloxlength[0] && objetosypos[ypos] == esquiloylength[0])) {
                lengthesquilo = 3;
                time = 0;
                xpos = random.nextInt(29);
                ypos = random.nextInt(16);

                PopsUp = objetoAleatorio.nextInt(15);
            }else if (time >= 90) {
                xpos = random.nextInt(29);
                ypos = random.nextInt(16);
                PopsUp = objetoAleatorio.nextInt(15);
                time = 0;
            }
            Serra.paintIcon(this, g, Serraxpos[xpos], objetosypos[ypos]);
        }


        DesmatamentoObstaculo = new ImageIcon("Resources/Objetos/desmatamento.png");
        DesmatamentoObstaculo.paintIcon(this, g, 380, 290);


        for (int b = 1; b < lengthesquilo; b++) {
            if (((esquiloxlength[b] == esquiloxlength[0]) && (esquiloylength[b] == esquiloylength[0])) ||
                    ((esquiloxlength[b] >= Desmatamentoxpos[0] && esquiloxlength[b] <= Desmatamentoxpos[4] ) &&
                            (esquiloylength[b] >= Desmatamentoypos[0] && esquiloylength[b] <= Desmatamentoypos[4])) ){
                right = false;
                left = false;
                up = false;
                down = false;
                gameOver = true;

                g.setColor(Color.RED);
                g.setFont(new Font("arial", Font.BOLD, 50));
                g.drawString("GAME OVER", 298, 240);

                g.setFont(new Font("arial", Font.BOLD, 20));
                g.drawString("Aperte 'SPACE' para REINICIAR", 300, 273);
            }
        }

        g.dispose();
    }

    public void actionPerformed(ActionEvent e) {
        timer.start();
        time++;

        if(right) {
            for (int r = lengthesquilo - 1; r >= 0; r--) {
                esquiloylength[r+1] = esquiloylength[r];
            }
            for (int r = lengthesquilo; r >= 0; r--) {
                if (r == 0) {
                    esquiloxlength[r] = esquiloxlength[r] + 25;
                }
                else {
                    esquiloxlength[r] = esquiloxlength[r-1];
                }
                if (esquiloxlength[r] > 850) {
                    esquiloxlength[r] = 25;
                }
            }
            repaint();
        }

        if (left) {
            for (int r = lengthesquilo - 1; r >= 0; r--) {
                esquiloylength[r + 1] = esquiloylength[r];
            }
            for (int r = lengthesquilo; r >= 0; r--) {
                if (r == 0) {
                    esquiloxlength[r] = esquiloxlength[r] - 25;
                } else {
                    esquiloxlength[r] = esquiloxlength[r - 1];
                }
                if (esquiloxlength[r] < 25) {
                    esquiloxlength[r] = 850;
                }
            }
            repaint();
        }
        if(up) {
            for (int r = lengthesquilo - 1; r >= 0; r--) {
                esquiloxlength[r+1] = esquiloxlength[r];
            }
            for (int r = lengthesquilo; r >= 0; r--) {
                if (r == 0) {
                    esquiloylength[r] = esquiloylength[r] - 25;
                }
                else {
                    esquiloylength[r] = esquiloylength[r-1];
                }
                if (esquiloylength[r] < 75) {
                    esquiloylength[r] = 625;
                }
            }
            repaint();
        }
        if(down) {
            for (int r = lengthesquilo - 1; r >= 0; r--) {
                esquiloxlength[r+1] = esquiloxlength[r];
            }
            for (int r = lengthesquilo; r >= 0; r--) {
                if (r == 0) {
                    esquiloylength[r] = esquiloylength[r] + 25;
                }
                else {
                    esquiloylength[r] = esquiloylength[r-1];
                }
                if (esquiloylength[r] > 625) {
                    esquiloylength[r] = 75;
                }
            }
            repaint();
        }

    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            moves = 0;
            score = 0;
            lengthesquilo = 3;
            time = 0;
            gameOver = false;
            repaint();
        }

        if (!gameOver) {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                moves++;
                right = true;
                if (!left) {
                    right = true;
                } else {
                    right = false;
                    left = true;
                }
                up = false;
                down = false;
            }

            if (moves != 0) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    moves++;
                    left = true;
                    if (!right) {
                        left = true;
                    } else {
                        left = false;
                        right = true;
                    }
                    up = false;
                    down = false;
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_UP) {
                moves++;
                up = true;
                if (!down) {
                    up = true;
                } else {
                    up = false;
                    down = true;
                }
                left = false;
                right = false;
            }

            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                moves++;
                down = true;
                if (!up) {
                    down = true;
                } else {
                    down = false;
                    up = true;
                }
                left = false;
                right = false;
            }
        }

    }

    public void keyReleased(KeyEvent e) {

    }
}
