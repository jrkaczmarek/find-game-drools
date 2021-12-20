package com.sample;

import java.util.List;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import javax.swing.JFrame;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DroolsTest {

    public static final void main(String[] args) {
        try {
	         KieServices ks = KieServices.Factory.get();
    	     KieContainer kContainer = ks.getKieClasspathContainer();
        	 KieSession kSession = kContainer.newKieSession("ksession-rules");
        	 kSession.fireAllRules();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
    

    
    public static class GUI extends JFrame {
		private static final long serialVersionUID = 1L;
		List<JRadioButton> jRadioButtons;
        public JButton jButton;
        ButtonGroup G1;
        JLabel L1;
        public String odpowiedzFinalna = null;
        public boolean padlaOdpowiedz = false;

        public GUI(List<String> odpowiedzi, String pytanie)
        {
            this.setTitle("Choose your game!");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            jButton = new JButton("Next");
            G1 = new ButtonGroup();
            L1 = new JLabel(pytanie);

            jRadioButtons = new ArrayList<>();
            for (Object s : odpowiedzi) {
                JRadioButton x = new JRadioButton((String) s);
                G1.add(x);
                jRadioButtons.add(x);
            }


            JPanel container = new JPanel();
            BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
            container.setLayout(layout);
            L1.setAlignmentX(CENTER_ALIGNMENT);
            container.add(L1);
            for (JRadioButton x: jRadioButtons) {
                x.setAlignmentX(CENTER_ALIGNMENT);
                container.add(x);
            }
            jButton.setAlignmentX(CENTER_ALIGNMENT);
            container.add(jButton);

            this.setLayout(new FlowLayout());
            this.add(container);
            this.pack();
            this.setLocationRelativeTo(null);
            this.setVisible(true);

            jButton.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    String odpowiedz = " ";
                    for (JRadioButton x: jRadioButtons) {
                        if(x.isSelected()) {
                            odpowiedz = x.getText();
                        }
                    }
                    if (!odpowiedz.equals(" ")) {
                        padlaOdpowiedz = true;
                        odpowiedzFinalna = odpowiedz;
                        synchronized (jButton) {
                            jButton.notify();
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(GUI.this, "You didn't choose anything!");
                    }

                }
            });
        }
    }
    
    
}
