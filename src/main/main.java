package main;
//import java.util.Observer;


import javax.swing.JFrame;

import model.Espacio;
import viewControl.FinishFrame;
import viewControl.MainFrame;
import viewControl.StartFrame;

public class main {	//extends JFrame implements Observer{
	public static void main(String[] args) {
		
		//MODELO//
		//Espacio.getInstance().addObserver(this); 	//AÑADO EL OBSERVADOR
		Espacio.getInstance(); //.addObserver(this);
		
		//VISTA//
		@SuppressWarnings("unused")
		StartFrame startFrame = new StartFrame();
		startFrame.setVisible(true);
	 	
//		@SuppressWarnings("unused")
//		MainFrame mainFrame = new MainFrame();
//		
//		@SuppressWarnings("unused")
//		FinishFrame finishFrame = new FinishFrame(null);
		
		
	}

}
