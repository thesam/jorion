package se.timberline.jorion;

import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;

public class JOrion {

	private JFrame frmLbxBrowser;
	private JList fileList;
	private JList entryList;
	private List<LbxEntry> entries;
	private JTextPane textPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JOrion window = new JOrion();
					window.frmLbxBrowser.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws FileNotFoundException
	 */
	public JOrion() throws IOException {
		initialize();
		fillList();
	}

	private void fillList() throws IOException {
		LbxArchiveReader reader = new LbxArchiveReader(
				BinaryFileReader.createFromFile(new File("test/VORTEX.LBX")));
		LbxArchive archive = reader.getArchive();
		entries = archive.getEntries();
		List<String> entryNames = new LinkedList<String>();
		for (LbxEntry lbxEntry : entries) {
			entryNames.add(lbxEntry.getName());
		}
		entryList.setListData(entryNames.toArray());

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLbxBrowser = new JFrame();
		frmLbxBrowser.setTitle("LBX Browser");
		frmLbxBrowser.setBounds(100, 100, 450, 300);
		frmLbxBrowser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLbxBrowser.getContentPane().setLayout(new GridLayout(2, 0, 10, 10));

		JPanel panel_2 = new JPanel();
		frmLbxBrowser.getContentPane().add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel = new JPanel();
		panel_2.add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		fileList = new JList();
		panel.add(fileList);

		JPanel panel_1 = new JPanel();
		panel_2.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);

		entryList = new JList();
		entryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		entryList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (arg0.getSource() == entryList) {
					int index = entryList.getSelectedIndex();
					System.err.println("Selection changed: " + index);
					LbxEntry entry = entries.get(index);
					textPane.setText("You selected " + entry.getName());
				}
			}
		});
		scrollPane.setViewportView(entryList);

		JPanel panel_3 = new JPanel();
		frmLbxBrowser.getContentPane().add(panel_3);
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));

		textPane = new JTextPane();
		textPane.setEditable(false);
		panel_3.add(textPane);
	}

}
