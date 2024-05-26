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

public class GatoAmbientalista extends JPanel implements KeyListener, ActionListener{

    public int time;

    private int[] gatoxlength = new int[750];
    private int[] gatoylength = new int[750];

    private static boolean gameOver = false;
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    private ImageIcon rightmouth;
    private ImageIcon upmouth;
    private ImageIcon downmouth;
    private ImageIcon leftmouth;
    private ImageIcon gatoimage;


    private int lengthgato = 3;

    private Timer timer;
    private int delay = 100;


    private int[] Latinhaxpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,
            350,500,525,550,575,600,625,650,675,700};
    private int[] Cascaxpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,
            350,500,525,550,575,600,625,650,675,700};
    private int[] Remedioxpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,
            350,500,525,550,575,600,625,650,675,700};
    private int[] Venenoxpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,
            350,500,525,550,575,600,625,650,675,700};

    private int[] lixoypos = {75,100,125,150,175,200,225,250,400,425,450,
            475,500,525,550,575};


    private ImageIcon Latinha;
    private ImageIcon Casca;
    private ImageIcon Remedio;
    private ImageIcon Veneno;

    private ImageIcon Latadelixo;

    private Random random = new Random();
    private int xpos = random.nextInt(23);
    private int ypos = random.nextInt(16);


    private Random lixoAleatorio = new Random();
    private int PopsUp = lixoAleatorio.nextInt(15);


    private int score = 0;

    private int moves = 0;

    private ImageIcon titleImage;
    
    // Imagem de fundo
    private Image backgroundImage;

    public GatoAmbientalista() {
        time = 0;

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
        
        // Carregando a imagem de fundo
        backgroundImage = new ImageIcon("Resources/Backgrounds/backgroundGato.png").getImage();
       
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
            gatoxlength[2] = 50;
            gatoxlength[1] = 75;
            gatoxlength[0] = 100;

            gatoylength[2] = 100;
            gatoylength[1] = 100;
            gatoylength[0] = 100;
        }


     // Arte - Borda
        g.setColor(Color.black);
        g.drawRect(24, 10, 851, 55);

        // Arte - Título superior
        titleImage = new ImageIcon("Resources/Backgrounds/missaos.png");
        titleImage.paintIcon(this, g, 25, 11 );


        g.setColor(Color.white);
        g.drawRect(24, 74, 851, 577);


        g.setColor(Color.GREEN.darker());
        g.setFont(new Font("arial", Font.PLAIN, 18));
        g.drawString("PONTUAÇÃO: " + score, 710, 40);


        g.setColor(Color.GREEN.darker());
        g.setFont(new Font("arial", Font.PLAIN, 18));
        g.drawString("TAMANHO: " + lengthgato, 40, 40);


        rightmouth = new ImageIcon("Resources/Characters/gato.png");
        rightmouth.paintIcon(this, g, gatoxlength[0], gatoylength[0]);


        for (int a = 0; a < lengthgato; a++) {
            if (a==0 && right) {
                rightmouth = new ImageIcon("Resources/Characters/gato.png");
                rightmouth.paintIcon(this, g, gatoxlength[a], gatoylength[a]);
            }
            if (a==0 && left) {
                leftmouth = new ImageIcon("Resources/Characters/gato.png");
                leftmouth.paintIcon(this, g, gatoxlength[a], gatoylength[a]);
            }
            if (a==0 && down) {
                downmouth = new ImageIcon("Resources/Characters/gato.png");
                downmouth.paintIcon(this, g, gatoxlength[a], gatoylength[a]);
            }
            if (a==0 && up) {
                upmouth = new ImageIcon("Resources/Characters/gato.png");
                upmouth.paintIcon(this, g, gatoxlength[a], gatoylength[a]);
            }
            if (a!=0) {
                gatoimage = new ImageIcon("Resources/Characters/gato.png");
                gatoimage.paintIcon(this, g, gatoxlength[a], gatoylength[a]);
            }
        }

        Latadelixo = new ImageIcon("Resources/Objetos/lixo.png");
        Latadelixo.paintIcon(this, g, 350, 270);

        if (PopsUp <= 6) {
            // Simple Fruit
            Latinha = new ImageIcon("Resources/Objetos/latinha.png");
            if ((Latinhaxpos[xpos] == gatoxlength[0] && lixoypos[ypos] == gatoylength[0])) {
                score ++;
                lengthgato++;
                time = 0;
                xpos = random.nextInt(23);
                ypos = random.nextInt(16);

                PopsUp = lixoAleatorio.nextInt(10);
            }else if (time >= 90) {
                xpos = random.nextInt(23);
                ypos = random.nextInt(16);
                PopsUp = lixoAleatorio.nextInt(10);
                time = 0;
            }
            Latinha.paintIcon(this, g, Latinhaxpos[xpos], lixoypos[ypos]);
        }
        else if (PopsUp > 6 && PopsUp <= 9 ) {
            // Big Fruit
            Casca = new ImageIcon("Resources/Objetos/casca.png");
            if ((Cascaxpos[xpos] == gatoxlength[0] && lixoypos[ypos] == gatoylength[0])) {
                score += 2;
                lengthgato++;
                time = 0;
                xpos = random.nextInt(23);
                ypos = random.nextInt(16);

                PopsUp = lixoAleatorio.nextInt(15);
            }else if (time >= 90) {
                xpos = random.nextInt(23);
                ypos = random.nextInt(16);
                PopsUp = lixoAleatorio.nextInt(15);
                time = 0;
            }

            Casca.paintIcon(this, g, Cascaxpos[xpos], lixoypos[ypos]);
        }
        else if (PopsUp > 9 && PopsUp <= 13 ) {
            // Bomb Fruit
            Veneno = new ImageIcon("Resources/Objetos/veneno.png");
            if ((Venenoxpos[xpos] == gatoxlength[0] && lixoypos[ypos] == gatoylength[0])) {
                right = false;
                left = false;
                up = false;
                down = false;
                gameOver = true;

                //MUDANÇA DE COR, TEXTO E POSIÇÃO 
                g.setColor(Color.RED);
                g.setFont(new Font("arial", Font.BOLD, 50));
                g.drawString("GAME OVER", 298, 240);

                g.setFont(new Font("arial", Font.BOLD, 20));
                g.drawString("Aperte 'SPACE' para REINICIAR", 300, 273);

            }else if (time >= 90) {
                // Tempo de desaparecimento da fruta
                xpos = random.nextInt(23);
                ypos = random.nextInt(16);
                PopsUp = lixoAleatorio.nextInt(15);
                time = 0;
            }
            Veneno.paintIcon(this, g, Venenoxpos[xpos], lixoypos[ypos]);
        }
        else {
            // Decrease Fruit
            Remedio = new ImageIcon("Resources/Objetos/remedio.png");
            if ((Remedioxpos[xpos] == gatoxlength[0] && lixoypos[ypos] == gatoylength[0])) {
                lengthgato = 3;
                time = 0;
                xpos = random.nextInt(23);
                ypos = random.nextInt(16);

                PopsUp = lixoAleatorio.nextInt(15);
            }else if (time >= 90) {
                xpos = random.nextInt(23);
                ypos = random.nextInt(16);
                PopsUp = lixoAleatorio.nextInt(15);
                time = 0;
            }
            Remedio.paintIcon(this, g, Remedioxpos[xpos], lixoypos[ypos]);
        }


        for (int b = 1; b < lengthgato; b++) {
            if (gatoxlength[b] == gatoxlength[0] && gatoylength[b] == gatoylength[0]) {
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
            for (int r = lengthgato - 1; r >= 0; r--) {
                gatoylength[r+1] = gatoylength[r];
            }
            for (int r = lengthgato; r >= 0; r--) {
                if (r == 0) {
                    gatoxlength[r] = gatoxlength[r] + 25;
                }
                else {
                    gatoxlength[r] = gatoxlength[r-1];
                }
                if (gatoxlength[r] > 820) {
                    gatoxlength[r] = 820;
                }
            }
            repaint();
        }
        if (left) {
            for (int r = lengthgato - 1; r >= 0; r--) {
                gatoylength[r + 1] = gatoylength[r];
            }
            for (int r = lengthgato; r >= 0; r--) {
                if (r == 0) {
                    gatoxlength[r] = gatoxlength[r] - 25;
                } else {
                    gatoxlength[r] = gatoxlength[r - 1];
                }
                if (gatoxlength[r] < 25) {
                    gatoxlength[r] = 25;
                }
            }
            repaint();
        }
        if(up) {
            for (int r = lengthgato - 1; r >= 0; r--) {
                gatoxlength[r+1] = gatoxlength[r];
            }
            for (int r = lengthgato; r >= 0; r--) {
                if (r == 0) {
                    gatoylength[r] = gatoylength[r] - 25;
                }
                else {
                    gatoylength[r] = gatoylength[r-1];
                }
                if (gatoylength[r] < 75) {
                    gatoylength[r] = 75;
                }
            }
            repaint();
        }
        if(down) {
            for (int r = lengthgato - 1; r >= 0; r--) {
                gatoxlength[r+1] = gatoxlength[r];
            }
            for (int r = lengthgato; r >= 0; r--) {
                if (r == 0) {
                    gatoylength[r] = gatoylength[r] + 25;
                }
                else {
                    gatoylength[r] = gatoylength[r-1];
                }
                if (gatoylength[r] > 590) {
                    gatoylength[r] = 590;
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
            lengthgato = 3;
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
