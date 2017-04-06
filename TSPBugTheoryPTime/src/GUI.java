import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.SystemColor;

public class GUI extends JFrame {
	private static final long serialVersionUID = -7466782193912627641L;
	private JPanel contentPane;
	private static  ArrayList<Circulo> Lista= new ArrayList<Circulo>();
	private static  ArrayList<Circulo> auxLista= new ArrayList<Circulo>();
	static JLabel Info = new JLabel("Info:");
	static int contLine;
	static int contCircle;
		    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					try {
						//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
						UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					} catch (Exception e) {
						try {
							UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
						} catch (ClassNotFoundException | InstantiationException
								| IllegalAccessException | UnsupportedLookAndFeelException e1) {
							
							e1.printStackTrace();
						}
						e.printStackTrace();

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				GUI frame = new GUI();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				
			}
		});
	}

	public GUI() {
		setBackground(Color.CYAN);
		setTitle("PTS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 983, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JButton Solutionbtn = new JButton("Make Solution");
		Solutionbtn.setBounds(739, 46, 165, 23);
		contentPane.add(Solutionbtn);
		JButton Ramdonbtn = new JButton("Ramdon Points");
		Ramdonbtn.setBounds(739, 11, 165, 23);
		contentPane.add(Ramdonbtn);
		
		
		Info.setBackground(SystemColor.activeCaptionBorder);
		Info.setVerticalAlignment(SwingConstants.TOP);
		Info.setBounds(741, 93, 163, 141);
		contentPane.add(Info);
		
		
	//Actions
	Ramdonbtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				generateRamdonPoints();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		
		}
	});
	Solutionbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			try {
				expand((Circulo)Lista.get(0));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			}
		});
	}
//_Metodss
	
	 public void paintPoint(Circulo p, Color c) {
	    Graphics g=  getGraphics();
	    Graphics2D ga= (Graphics2D) g;
	    ga.setColor(c);
	    ga.fillOval((int)p.getX(),(int) p.getY(),(int)p.getR(),(int)p.getR());
	  }	 

	public void expand(Circulo circle) throws InterruptedException {
		    Graphics g=  getGraphics();
		    Graphics2D ga= (Graphics2D) g;
		    //ga.setColor(new Color((int)(Math.random() * 0x1000000)));
		    double x1=0;
		    double x2=0;
		    double y1=0;
		    double y2=0;
	    	
		    for (Circulo circleBig : Lista) {
			int cont=0;
			while(cont<auxLista.size() && circleBig.getHasFlag()<1){//lines<=Lista.size()*2
				
				int var=checkPoint(circleBig);
				if(var>0){
					x1=auxLista.get(var).getX();
					y1=auxLista.get(var).getY();
					x2=auxLista.get(cont).getX();
					y2=auxLista.get(cont).getY();
					drawline(x1, y1, x2, y2);
					cont++;
				}
				circleBig.setR(circleBig.getR()+10);
				circleBig.setX(circleBig.getX()-5);
				circleBig.setY(circleBig.getY()-5);
				Thread.sleep(500);
				ga.drawOval((int)circleBig.getX(),(int) circleBig.getY(), (int)circleBig.getR(),(int) circleBig.getR());
				checkPoint(circleBig);
				
			}
		   }
	 }	
	 
	 
	private int checkPoint(Circulo c) {
		int i=0;//index intercep other circle
		for (Circulo point : auxLista) {
			if ( c.getR()!= point.getR() && intercep(c, point)){
				point.setHasFlag(point.getHasFlag()+1);
				auxLista.get(i).setHasFlag(auxLista.get(i).getHasFlag()+1);
				return i;	
			}
			i++;
		}
		
		return -1;
	}

	private void drawline(double x1, double y1, double x2,double y2) {
		 Graphics g=  getGraphics();
		 Graphics2D ga= (Graphics2D) g;
		 ga.setColor(Color.BLACK);
		 ga.clearRect(600, 200, 100, 10);
		 ga.drawLine((int)x1+5, (int)y1+5, (int)x2+5,(int) y2+5);

		Info.setText("Line:"+ contLine);
		
		contLine++;
		
	}
	public boolean intercep(Circulo small, Circulo big) 
	{ 
		double x1=big.getX();
		double x2=small.getX();
		double y1=big.getY();
		double y2=small.getY();
		double r1=big.getR();
		double r2=small.getR();
		return (Math.abs(r1 - r2) <= Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2)))
	              && (Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2)) <= (r1 + r2));

	} 

	protected void generateRamdonPoints() throws Exception, Exception {
			File fileDir = new File("posxy.txt");
			@SuppressWarnings("resource")
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));
	        String str;
	        String[] ar = null;
	        //Random rand = new Random();
	       
	    while ((str = in.readLine()) != null) {
	        ar=str.split(",");
		    Circulo c= new Circulo(0,0,0,0);   
		    c.setX(Double.parseDouble(ar[0])+50);
		    c.setY(Double.parseDouble(ar[1])+50);
		    c.setR(10);
		    c.setHasFlag(0);
		    Lista.add(c);
		    Circulo c1= new Circulo(Double.parseDouble(ar[0])+50,Double.parseDouble(ar[1])+50,15,0);
		    auxLista.add(c1);
		    paintPoint(c,Color.black);
		    
		 
		}

	    //ordenarPosicionX(Lista);
	    //ordenarPosicionY(Lista);
	}
	
	@SuppressWarnings("unused")
	private void ordenarPosicionX(ArrayList<Circulo> listParam) {
		Collections.sort(listParam, new Comparator<Circulo>() {
			public int compare(Circulo a, Circulo b) {
				if (a.getX() == b.getX())
					return ((Comparable<Double>) a.getX()).compareTo(b.getX());
				return a.getX() > b.getX() ? 1 : a.getX() < b.getX() ? -1
						: 0;
			}
		});
	}
	@SuppressWarnings("unused")
	private void ordenarPosicionY(ArrayList<Circulo> listParam) {
		Collections.sort(listParam, new Comparator<Circulo>() {
			public int compare(Circulo a, Circulo b) {
				if (a.getY() == b.getY())
					return ((Comparable<Double>) a.getY()).compareTo(b.getY());
				return a.getY() < b.getY() ? 1 : a.getY() > b.getY() ? -1
						: 0;
			}
		});
	}
}  
    	
 

