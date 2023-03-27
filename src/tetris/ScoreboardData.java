package tetris;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ScoreboardData extends AbstractTableModel{
	
	List<PlayerData> players = new ArrayList<PlayerData>();

	
    @Override
    public Object getValueAt(int i, int i1) {
    	PlayerData player = players.get(i);
		switch(i1) {
			case 0: return i+1;
			case 1: return player.getName();
			default: return player.getScore();
		}
	}

    @Override
    public int getRowCount() {
		return players.size();
	}

    @Override
	public int getColumnCount() {
		return 3;
	}
    @Override
	public Class<?> getColumnClass(int i)
	{
		switch(i)
		{
			case 0: return Integer.class;
			case 1: return String.class;
			default: return Integer.class;
		}
	}

    @Override
	public String getColumnName(int i)
	{
		switch(i)
		{
			case 0: return "Helyezés";
			case 1: return "Név";
			default: return "Pontok";
		}
	}
    
    
    public boolean addStudent(String nev,int pont) {
    	
    	if(players.size() < 10) {
    		players.add(new PlayerData(nev, pont));
    	}
    	else if(players.get(players.size()-1).getScore() < pont ) {// ha az utolsó 10. elem kisebb mint a megadott pontszám akkor kicseréljük mivel csak a top 10 van benne
    			players.remove(players.size()-1);
    			players.add(new PlayerData(nev, pont));
    		}
    	else
    		return false;
    	
    	this.fireTableDataChanged();
    	
    	Collections.sort(players, new Comparator<PlayerData>() { // ha hozzáadunk akkor rendezünk

            public int compare(PlayerData o1, PlayerData o2) {
                return Integer.valueOf(o2.getScore()).compareTo(o1.getScore());
            }
        });
    	return true;
    }
    
    
}
