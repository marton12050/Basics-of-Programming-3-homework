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
		
		leiras.setText("<html><body> A játék célja, hogy kitölts sorokat a leesõ alakzatokkal ezzel szerezve pontokat. <br> *Jobb nyíllal jobbra megy egyet az alakzat <br> * Bal nyíllal balra megy egyet az alakzat <br>*Fel nyíllal forgatsz 90 fokot az esõ alakzaton <br> *Le nyíllal pedig gyorsabban esik <br>*Ha egy alakzat nem tud tovább esni akkor a pálya tetején megjelenik az új <br> *Ha nem fér már el, szóval nem tud újat lerakni akkor vége a játéknak <br>*Ha egy sor betelt akkor az eltûnik és felette lévõ négyzetek leesnek <br> *Minden egyszere eltüntettet soroknak a négyzetét kapod meg pontként <br>*Ha megnyomod a spacet akkor megáll a játék mégegyszer megnyomod elindul</body></html>");
		
		leiras.setVerticalAlignment(JLabel.CENTER);
		leiras.setFont(new Font("Verdana", Font.PLAIN, 11));
		
		
		
		this.add(felirat);
		this.add(leiras);
		this.setVisible(true);
	}
	
}
