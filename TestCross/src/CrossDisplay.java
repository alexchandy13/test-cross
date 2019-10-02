import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

import javax.swing.*;

public class CrossDisplay extends JFrame
implements ActionListener
{
	  private JTextField dihy, rec, Ab, aB, dist;
	  private JLabel ldihy, lrec, lAb, laB, ldist, blank;


public CrossDisplay()
{
	super("Two Point Test Cross");
	ldihy = new JLabel("Dominant-Dominant:", SwingConstants.RIGHT);
	dihy = new JTextField();
	dihy.setPreferredSize(new Dimension(80, 30));
	dihy.addActionListener(this);

	lrec = new JLabel("Recessive-Recessive:", SwingConstants.RIGHT);
	rec = new JTextField();
	rec.setPreferredSize(new Dimension(80, 30));
	rec.addActionListener(this);

	lAb = new JLabel("Dominant-Recessive:", SwingConstants.RIGHT);
	Ab = new JTextField();
	Ab.setPreferredSize(new Dimension(80, 30));
 	Ab.addActionListener(this);
 	
	laB = new JLabel("Recessive-Dominant:", SwingConstants.RIGHT);
	aB = new JTextField();
	aB.setPreferredSize(new Dimension(80, 30));
 	aB.addActionListener(this);

 	ldist = new JLabel("Loci Distance (cM):");
 	dist = new JTextField();
 	dist.setPreferredSize(new Dimension(80, 30));
 	dist.addActionListener(this);
 	dist.setEditable(false);
 	
 	blank = new JLabel("Find Distance:");
 
 JButton go = new JButton("Compute");
 go.addActionListener(this);

 Container c = getContentPane();
 c.setBackground(Color.WHITE);
 JPanel p = new JPanel();
 p.setLayout(new GridLayout(6, 2, 5, 5));
 p.add(ldihy);
 p.add(dihy);
 p.add(lrec);
 p.add(rec);
 p.add(lAb);
 p.add(Ab);
 p.add(laB);
 p.add(aB);
 p.add(ldist);
 p.add(dist);
 p.add(blank);
 p.add(go);
 
 c.add(p, BorderLayout.EAST);

}

public void actionPerformed(ActionEvent e)
{
	  double rc1 = Double.parseDouble(dihy.getText());
	  double rc2 = Double.parseDouble(rec.getText());
	  double rc3 = Double.parseDouble(Ab.getText());
	  double rc4 = Double.parseDouble(aB.getText());
	  //DisplayPanel chart = new DisplayPanel(degrees, undercutt, pitchspeed);
	  paintComponent(getGraphics());
	  String distance = calcDist(rc1, rc2, rc3, rc4);
	  dist.setText(distance);
}

public void paintComponent(Graphics g)
{
  super.paintComponents(g);

  int wi = getWidth();
  int h = getHeight();
  int x = (wi/2)-150;
  int y = h/2;
  int r = Math.min(wi, h) / 4;
  drawAlleles(g, x, y, r);
}

public void drawAlleles(Graphics g, int x, int y, int r)
{
	double rc1 = Double.parseDouble(dihy.getText());	
	double rc2 = Double.parseDouble(rec.getText());
	double rc3 = Double.parseDouble(Ab.getText());
	double rc4 = Double.parseDouble(aB.getText());
	double total = rc1+rc2+rc3+rc4;
	double dist = 1.0000*(rc3+rc4)/total;
	double cM = dist*100;
	DecimalFormat df = new DecimalFormat("000.00");
	double dM = cM*10;
	int mid = (int) (dM/2);
	int length = 500;
	int mp = length/2;
	int width = 80;
	g.setColor(Color.GREEN);
	g.fillRect(x-mp, y, length, width);
	g.setColor(Color.RED);
	g.drawLine(x-mid, y, x-mid, y+width);
	g.drawLine(x-mid+1, y, x-mid+1, y+width);
	g.drawLine(x-mid-1, y, x-mid-1, y+width);
	g.setColor(Color.BLUE);
	g.drawLine(x+mid, y, x+mid, y+width);
	g.drawLine(x+mid+1, y, x+mid+1, y+width);
	g.drawLine(x+mid-1, y, x+mid-1, y+width);
	g.setColor(Color.BLACK);
	g.drawLine(x-mid, y-5, x-mid, y-31);
	g.drawLine(x-mid+1, y-5, x-mid+1, y-31);
	g.drawLine(x-mid-1, y-5, x-mid-1, y-31);
	g.drawLine(x+mid, y-5, x+mid, y-31);
	g.drawLine(x+mid+1, y-5, x+mid+1, y-31);
	g.drawLine(x+mid-1, y-5, x+mid-1, y-31);
	g.drawLine(x-mid, y-17, x+mid, y-17);
	g.drawLine(x-mid, y-18, x+mid, y-18);
	g.drawLine(x-mid, y-19, x+mid, y-19);
	g.setFont(new Font("Calibri", Font.PLAIN, 20));
	g.drawString(df.format(cM)+" "+"centimorgans", x-100, y-50);
	
			
		
}

// Returns BMI equal to weight in kilograms divided
// over squared height in meters.
private String calcDist(double rc1,  double rc2, double rc3, double rc4)
{
	double total = rc1+rc2+rc3+rc4;
	double dist = 1.0000*(rc3+rc4)/total;
	double cM = dist*100;	
	DecimalFormat df = new DecimalFormat("000.00");	
	return df.format(cM);
}




public static void main(String[] args)
{
	CrossDisplay w = new CrossDisplay();
	w.setBounds(300, 300, 1000, 700);
	w.setDefaultCloseOperation(EXIT_ON_CLOSE);
	w.setVisible(true);
}

}

