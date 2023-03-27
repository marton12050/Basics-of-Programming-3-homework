package tetris;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class Window {

	private JFrame ablak;
	static final int Width = 480;
	static final int Height = 720;
	private Board palya;
	private TutorialUI tutUI;
	private ScoreboardUI scorebUI;
	private CardLayout cl;
	JPanel mainJP;
	
	public Window() {
		//f� ablak kezd� be�ll�t�sai
        ablak = new JFrame("Tetris");
        ablak.setSize(Width,Height);
        ablak.setLocationRelativeTo(null);
        ablak.setResizable(false);
        cl = new CardLayout();
        ablak.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //panelek amiket l�tunk
        palya = new Board(this);
        tutUI = new TutorialUI();
        scorebUI = new ScoreboardUI();
        
        //f� panel
        mainJP = new JPanel();
        mainJP.setLayout(cl);
        ablak.add(mainJP);
        
        //f� panelhez adjuk a paneleket
        mainJP.add(tutUI, "kezdo");
        mainJP.add(palya, "palya");
        mainJP.add(scorebUI, "score");
        cl.show(mainJP, "kezdo");
        
        //menu
        JMenuBar menubar = new JMenuBar();
        ablak.setJMenuBar(menubar);
        JMenu menu = new  JMenu("Menu");
        menubar.add(menu);

        //menu elemek
        JMenuItem tutorialJMI = new JMenuItem("Tutorial");
        menu.add(tutorialJMI);
        JMenuItem tetrisJMI = new JMenuItem("Tetris");
        menu.add(tetrisJMI);
        JMenuItem scoreboardJMI = new JMenuItem("Dics�s�gt�bla");
        menu.add(scoreboardJMI);
        
        //menu elemek megnyom�s�ra mit csin�ljon
        tutorialJMI.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				palya.stopGame();
				cl.show(mainJP, "kezdo");
				
				ablak.repaint();
				ablak.revalidate();
			}
        });
        tetrisJMI.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				cl.show(mainJP, "palya");
				ablak.repaint();
				ablak.revalidate();
				palya.startGame();
				
			}
        });
        scoreboardJMI.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				palya.stopGame();
				cl.show(mainJP, "score");
				
				ablak.repaint();
				ablak.revalidate();
			}
        });
        
        ablak.setFocusable(true);
        ablak.addKeyListener(palya);
        ablak.setJMenuBar(menubar);
        ablak.setVisible(true);
    }
	public void addToScoreboard(String nev,int pont) { // j�t�k v�g�n scoreboardhoz adja a j�t�kost a megadott felt�telek alapj�n
		palya.stopGame();
		scorebUI.addPlayer(nev,pont);
		cl.show(mainJP, "kezdo");
		ablak.repaint();
		ablak.revalidate();
	}
	
	public static void main(String[] args) {
			new Window();
		
	}

}
