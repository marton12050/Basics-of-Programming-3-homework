package tetris;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;

public class ScoreboardUI extends JPanel{

	
	
	JTable scoreboardJT;
	ScoreboardData data;
	public ScoreboardUI() {
		this.setLayout(new FlowLayout());
		
		data = new ScoreboardData();
		
		//fájlból olvas
		try {
            data = new ScoreboardData();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("SBplayers.dat"));
            data.players = (List<PlayerData>)ois.readObject();
            ois.close();
        } catch(Exception ex) {}
		
		scoreboardJT = new JTable(data);
		scoreboardJT.setEnabled(false);
		scoreboardJT.setFillsViewportHeight(true);
		this.setLayout(new BorderLayout());
		this.add(scoreboardJT, BorderLayout.CENTER);
		this.add(scoreboardJT.getTableHeader(), BorderLayout.NORTH);
	}
	
	public void addPlayer(String nev,int pont) {
		if(data.addStudent(nev, pont)) { // fájlba ír ha volt változás
			try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("SBplayers.dat"));
                oos.writeObject(data.players);
                oos.close();
            } catch(Exception ex) {}
			
		}
	}
}
