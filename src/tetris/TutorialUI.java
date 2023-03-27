package tetris;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TutorialUI extends JPanel{

	public TutorialUI(){
		
		JLabel felirat  = new JLabel("Tetris", JLabel.RIGHT);
		felirat.setVerticalAlignment(JLabel.CENTER);
		felirat.setFont(new Font("Verdana", Font.PLAIN, 50));
		this.add(felirat);
		
		JLabel leiras  = new JLabel();
		
		leiras.setText("<html><body> A j�t�k c�lja, hogy kit�lts sorokat a lees� alakzatokkal ezzel szerezve pontokat. <br> *Jobb ny�llal jobbra megy egyet az alakzat <br> * Bal ny�llal balra megy egyet az alakzat <br>*Fel ny�llal forgatsz 90 fokot az es� alakzaton <br> *Le ny�llal pedig gyorsabban esik <br>*Ha egy alakzat nem tud tov�bb esni akkor a p�lya tetej�n megjelenik az �j <br> *Ha nem f�r m�r el, sz�val nem tud �jat lerakni akkor v�ge a j�t�knak <br>*Ha egy sor betelt akkor az elt�nik �s felette l�v� n�gyzetek leesnek <br> *Minden egyszere elt�ntettet soroknak a n�gyzet�t kapod meg pontk�nt <br>*Ha megnyomod a spacet akkor meg�ll a j�t�k m�gegyszer megnyomod elindul</body></html>");
		
		leiras.setVerticalAlignment(JLabel.CENTER);
		leiras.setFont(new Font("Verdana", Font.PLAIN, 11));
		
		
		
		this.add(felirat);
		this.add(leiras);
		this.setVisible(true);
	}
	
}
