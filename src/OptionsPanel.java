import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsPanel extends JPanel {

    private Screen myScreen;
    private JButton pause;
    private JButton resume;

    public OptionsPanel(Screen mainScreenRef){
        this.myScreen = mainScreenRef;
        this.pause = new JButton("PAUSE");
        this.pause.addActionListener(new pauseListener());
        this.resume = new JButton("RESUME");
        this.resume.addActionListener(new resumeListener());
        this.add(pause);
        this.add(resume);
        this.setVisible(true);
    }

    private class pauseListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            myScreen.pause();
        }
    }

    private class resumeListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            myScreen.resume();
        }
    }
}
