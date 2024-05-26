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

import Menu.Background;

public class PomboMensageiro extends JPanel implements KeyListener, ActionListener{
	
    // Variável para tratar o desapareceimento da lixo
    public int time;

    private int[] pomboxlength = new int[750];
    private int[] pomboylength = new int[750];

    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    private static boolean gameOver = false;

    private ImageIcon rightmouth;
    private ImageIcon upmouth;
    private ImageIcon downmouth;
    private ImageIcon leftmouth;
    private ImageIcon pomboimage;

    // Tamanho da snake
    private int lengthpombo = 3;

    private Timer timer;
    private int delay = 100;

    // Posição x das "lixos"
    private int[] Cigarrosxpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,
            350,500,525,550,575,600,625,650,675,700};
    private int[] Garrafasxpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,
            350,500,525,550,575,600,625,650,675,700};
    private int[] Armadilhaxpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,
            350,500,525,550,575,600,625,650,675,700};
    private int[] Venenoxpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,
            350,500,525,550,575,600,625,650,675,700};

    // Posição y das "lixos"
    private int[] lixoypos = {75,100,125,150,175,200,225,250,400,425,450,
            475,500,525,550,575};

    // Posição xy do "obstáculo"
    private int[] Obstaculoxpos = {375, 400, 425, 450, 475};
    private int[] Obstaculoypos = {275, 300, 325, 350, 375};

    // Criando as lixos
    private ImageIcon Cigarros;
    private ImageIcon Garrafas;
    private ImageIcon Armadilha;
    private ImageIcon Veneno;

    // Criando o obstáculo
    private ImageIcon CarroToxicoObstaculo;

    // Randomiza a posição que a lixo irá aparecer
    private Random random = new Random();
    private int xpos = random.nextInt(23);
    private int ypos = random.nextInt(16);

    // Randomiza qual lixo irá aparecer
    private Random lixoAleatorio = new Random();
    private int PopsUp = lixoAleatorio.nextInt(15);

    // Pontuação
    private int score = 0;

    private int moves = 0;

    private ImageIcon titleImage;
    
    // Imagem de fundo
    private Image backgroundImage;
    
    public PomboMensageiro() {
    
        time = 0;

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
        
        // Carregando a imagem de fundo
        backgroundImage = new ImageIcon("Resources/Backgrounds/backgroundPombo.png").getImage();
        
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
        	pomboxlength[2] = 50;
        	pomboxlength[1] = 75;
        	pomboxlength[0] = 100;

        	pomboylength[2] = 100;
        	pomboylength[1] = 100;
        	pomboylength[0] = 100;
            
        }
    
        // Arte - Borda
        g.setColor(Color.black);
        g.drawRect(24, 10, 851, 55);

        // Arte - Título superior
        titleImage = new ImageIcon("Resources/Backgrounds/missaos.png");
        titleImage.paintIcon(this, g, 25, 11 );
        
        // Arte - Borda gameplay
        g.setColor(Color.white);
        g.drawRect(24, 74, 851, 555);

        // Arte - Pontuação
        g.setColor(Color.GREEN.darker());
        g.setFont(new Font("arial", Font.PLAIN, 18));
        g.drawString("PONTUAÇÃO: " + score, 710, 40);

        // Arte - Tamanho da "pombo"
        g.setColor(Color.GREEN.darker());
        g.setFont(new Font("arial", Font.PLAIN, 18));
        g.drawString("TAMANHO: " + lengthpombo, 40, 40);
   
        rightmouth = new ImageIcon("Resources/Characters/pombos.png");
        rightmouth.paintIcon(this, g, pomboxlength[0], pomboylength[0]);

        // Movimento da "pombo"
        for (int a = 0; a < lengthpombo; a++) {
            if (a==0 && right) {
                rightmouth = new ImageIcon("Resources/Characters/pombos.png");
                rightmouth.paintIcon(this, g, pomboxlength[a], pomboylength[a]);
            }
            if (a==0 && left) {
                leftmouth = new ImageIcon("Resources/Characters/pombos.png");
                leftmouth.paintIcon(this, g, pomboxlength[a], pomboylength[a]);
            }
            if (a==0 && down) {
                downmouth = new ImageIcon("Resources/Characters/pombos.png");
                downmouth.paintIcon(this, g, pomboxlength[a], pomboylength[a]);
            }
            if (a==0 && up) {
                upmouth = new ImageIcon("Resources/Characters/pombos.png");
                upmouth.paintIcon(this, g, pomboxlength[a], pomboylength[a]);
            }
            if (a!=0) {
                pomboimage = new ImageIcon("Resources/Characters/pombos.png");
                pomboimage.paintIcon(this, g, pomboxlength[a], pomboylength[a]);
            }
        }


        if (PopsUp <= 6) {
            // Cigarros
            Cigarros = new ImageIcon("Resources/Objetos/cinzeiro.png");
            if ((Cigarrosxpos[xpos] == pomboxlength[0] && lixoypos[ypos] == pomboylength[0])) {
                score ++;
                lengthpombo++;
                time = 0;
                xpos = random.nextInt(23);
                ypos = random.nextInt(16);

                PopsUp = lixoAleatorio.nextInt(15);
            }else if (time >= 90) {
                // Tempo de desaparecimento do lixo
                xpos = random.nextInt(23);
                ypos = random.nextInt(16);
                PopsUp = lixoAleatorio.nextInt(15);
                time = 0;
            }
            Cigarros.paintIcon(this, g, Cigarrosxpos[xpos], lixoypos[ypos]);
        }
       
        else if (PopsUp > 6 && PopsUp <= 9 ) {
            // Garrafas
            Garrafas = new ImageIcon("Resources/Objetos/plastico.png");
            if ((Garrafasxpos[xpos] == pomboxlength[0] && lixoypos[ypos] == pomboylength[0])) {
                score += 2;
                lengthpombo++;
                time = 0;
                xpos = random.nextInt(23);
                ypos = random.nextInt(16);

                PopsUp = lixoAleatorio.nextInt(15);
            }else if (time >= 90) {
                // Tempo de desaparecimento da lixo
                xpos = random.nextInt(23);
                ypos = random.nextInt(16);
                PopsUp = lixoAleatorio.nextInt(15);
                time = 0;
            }
            Garrafas.paintIcon(this, g, Garrafasxpos[xpos], lixoypos[ypos]);
        }
        else if (PopsUp > 9 && PopsUp <= 13 ) {
            // Veneno
            Veneno = new ImageIcon("Resources/Objetos/veneno.png");
            if ((Venenoxpos[xpos] == pomboxlength[0] && lixoypos[ypos] == pomboylength[0])) {
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
                // Tempo de desaparecimento da lixo
                xpos = random.nextInt(23);
                ypos = random.nextInt(16);
                PopsUp = lixoAleatorio.nextInt(15);
                time = 0;
            }
            Veneno.paintIcon(this, g, Venenoxpos[xpos], lixoypos[ypos]);
        }
        else {
            // Armadilha
            Armadilha = new ImageIcon("Resources/Objetos/armadilha.png");
            if ((Armadilhaxpos[xpos] == pomboxlength[0] && lixoypos[ypos] == pomboylength[0])) {
                lengthpombo = 3;
                time = 0;
                xpos = random.nextInt(23);
                ypos = random.nextInt(16);

                PopsUp = lixoAleatorio.nextInt(15);
            }else if (time >= 90) {
                // Tempo de desaparecimento da lixo
                xpos = random.nextInt(23);
                ypos = random.nextInt(16);
                PopsUp = lixoAleatorio.nextInt(15);
                time = 0;
            }
            Armadilha.paintIcon(this, g, Armadilhaxpos[xpos], lixoypos[ypos]);
        }


        CarroToxicoObstaculo = new ImageIcon("Resources/Objetos/carroToxico.png");
        CarroToxicoObstaculo.paintIcon(this, g, 350, 270);

        // Condições para o 'Game Over'
        for (int b = 1; b < lengthpombo; b++) {
            if (((pomboxlength[b] == pomboxlength[0]) && (pomboylength[b] == pomboylength[0])) ||
                    ((pomboxlength[b] >= Obstaculoxpos[0] && pomboxlength[b] <= Obstaculoxpos[4] ) &&
                            (pomboylength[b] >= Obstaculoypos[0] && pomboylength[b] <= Obstaculoypos[4])) ){
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
            for (int r = lengthpombo - 1; r >= 0; r--) {
            	pomboylength[r+1] = pomboylength[r];
            }
            for (int r = lengthpombo; r >= 0; r--) {
                if (r == 0) {
                	pomboxlength[r] = pomboxlength[r] + 25;
                }
                else {
                	pomboxlength[r] = pomboxlength[r-1];
                }
                if (pomboxlength[r] > 820) {
                	pomboxlength[r] = 820;
                }
            }
            repaint();
        }
        if (left) {
            for (int r = lengthpombo - 1; r >= 0; r--) {
            	pomboylength[r + 1] = pomboylength[r];
            }
            for (int r = lengthpombo; r >= 0; r--) {
                if (r == 0) {
                	pomboxlength[r] = pomboxlength[r] - 25;
                } else {
                	pomboxlength[r] = pomboxlength[r - 1];
                }
                if (pomboxlength[r] < 25) {
                	pomboxlength[r] = 25;
                }
            }
            repaint();
        }
        if(up) {
            for (int r = lengthpombo - 1; r >= 0; r--) {
            	pomboxlength[r+1] =pomboxlength[r];
            }
            for (int r = lengthpombo; r >= 0; r--) {
                if (r == 0) {
                	pomboylength[r] = pomboylength[r] - 25;
                }
                else {
                	pomboylength[r] = pomboylength[r-1];
                }
                if (pomboylength[r] < 75) {
                	pomboylength[r] = 75;
                }
            }
            repaint();
        }
        if(down) {
            for (int r = lengthpombo - 1; r >= 0; r--) {
            	pomboxlength[r+1] = pomboxlength[r];
            }
            for (int r = lengthpombo; r >= 0; r--) {
                if (r == 0) {
                	pomboylength[r] = pomboylength[r] + 25;
                }
                else {
                	pomboylength[r] = pomboylength[r-1];
                }
                if (pomboylength[r] > 590) {
                	pomboylength[r] = 590;
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
            lengthpombo = 3;
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