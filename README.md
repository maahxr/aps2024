    Bibliotecas utilizadas:
    import javax.swing.JFrame;
    import javax.swing.ImageIcon;
    import javax.swing.JPanel;
    import javax.swing.Timer;
    import java.awt.*;
    import java.util.ArrayList;
    import java.awt.image.BufferedImage;
    import java.awt.event.*;
    import java.awt.image.*;
    import javax.imageio.ImageIO;
    import java.awt.event.KeyEvent;
    import java.awt.Color;
    import java.awt.Graphics;
    import java.awt.Font;
    import java.awt.event.ActionListener;
    import java.awt.event.ActionEvent;
    import java.awt.event.KeyListener;
    import java.util.Random;

-----------------------------------

    Menu do jogo
    O menu possui quatro opções e elas devem ser controladas através das "setinhas" do teclado, e escolhidas 
    através da tecla "Enter".

-----------------------------------
    Opções do menu
    Pombo Mensageiro
    Gato Ambientalista
    Esquilo do Jardim
    SAIR 

-----------------------------------

    Diferença das fases:
    Pombo:
    Pombo clássico, sem habilidades especiais. 
    Esse pombo não pode atravessar as bordas do jogo, o obstáculo da tela e nem a si mesmo

    Gato:
    Esse gato pode atravessar o obstáculo da tela, mas não pode atravessar as bordas do jogo e nem a si mesmo.

    Esquilo:
    Recebe o dobro de pontos ao comer as plantas e flores.
    Esse esquilo pode atravessar as bordas do jogo, mas não pode atravessar o obstáculo da tela e nem a si mesmo.

-----------------------------------

    Tipos de lixos
    Bituca de cigarro: dá um ponto e aumenta o tamanho do pombo.
    Garrafas plásticas:  Dá o dobro de pontos e aumenta o tamanho do pombo
    Armadilha:  Diminui o tamanho do pombo para o tamanho inicial, sem fornecer nem retirar pontos.

    Latinha: dá um ponto e aumenta o tamanho do gato .
    Casca de banana:  Dá o dobro de pontos e aumenta o tamanho do gato 
    Remédio:  Diminui o tamanho do gato para o tamanho inicial, sem fornecer nem retirar pontos.

    Árvore:  Dá o dobro de pontos e aumenta o tamanho do esquilo
    Flor:  Dá 4 pontos e aumenta o tamanho do esquilo
    Serra elétrica:  Diminui o tamanho do esquilo para o tamanho inicial, sem fornecer nem retirar pontos.

    Veneno:  Veneno que causa a morte dos animais.

-----------------------------------

    Obtáculo na tela , que causa a morte (com exeção do Gato).
    Pombo: carro com lixo toxico
    Esquilo: desmatamento
