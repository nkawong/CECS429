package milestone1v2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;

public class CorpusSelectionGUI extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CorpusSelectionGUI frame = new CorpusSelectionGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CorpusSelectionGUI() {
		initComponent();
	}

	private void initComponent() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JRadioButton rdbtnNationalParkCorpus = new JRadioButton("National Park Corpus");
		buttonGroup.add(rdbtnNationalParkCorpus);
		rdbtnNationalParkCorpus.setBounds(126, 76, 165, 23);
		contentPane.add(rdbtnNationalParkCorpus);
		
		JRadioButton rdbtnOther = new JRadioButton("Other");
		buttonGroup.add(rdbtnOther);
		rdbtnOther.setBounds(126, 111, 141, 23);
		contentPane.add(rdbtnOther);

		JLabel lblNewLabel = new JLabel("Choose a Corpus");
		lblNewLabel.setBounds(157, 22, 165, 16);
		contentPane.add(lblNewLabel);
		
		JLabel error = new JLabel("");
		error.setBounds(121, 154, 225, 16);
		contentPane.add(error);

		JButton btnOkay = new JButton("Okay");
		btnOkay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnNationalParkCorpus.isSelected() || rdbtnOther.isSelected() ) {
					dispose();
				}
				else{
					error.setText("You have not selected a corpus!");
					error.setForeground(Color.red);
				}
			}
		});
		btnOkay.setBounds(157, 182, 117, 29);
		contentPane.add(btnOkay);
	
	}
}
