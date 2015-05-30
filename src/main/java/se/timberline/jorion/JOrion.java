package se.timberline.jorion;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JOrion {

	private JFrame frmLbxBrowser;
	private JList<String> fileList;
	private JList<String> entryList;
	private List<LbxEntry> entries;
	private JTable table;
	private SuperTableModel tableModel;
	private LbxPicturePanel picturePanel;

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
		fillFileList();
		// fillList();
	}

	private void fillFileList() {
		String[] files = new File("test/").list(new FilenameFilter() {

			@Override
			public boolean accept(File arg0, String arg1) {
				return arg1.endsWith(".LBX") || arg1.endsWith(".lbx");
			}

		});
		fileList.setListData(files);
	}

	private void fillList(File file) throws IOException {
		LbxArchiveReader reader = new LbxArchiveReader(
				BinaryBlob.createFromFile(file));
		LbxArchive archive = reader.getArchive();
		entries = archive.getEntries();
		List<String> entryNames = new ArrayList<String>();
		for (LbxEntry lbxEntry : entries) {
			entryNames.add(lbxEntry.toString());
		}
		entryList.setListData( entryNames.toArray(new String[0]));

	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 */
	private void initialize() throws IOException {
		frmLbxBrowser = new JFrame();
		frmLbxBrowser.setTitle("LBX Browser");
		frmLbxBrowser.setBounds(100, 100, 450, 300);
		frmLbxBrowser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLbxBrowser.getContentPane().setLayout(new GridLayout(0, 2, 10, 10));

		tableModel = new SuperTableModel();

		JPanel selectionAndDisplayPanel = new JPanel();
		frmLbxBrowser.getContentPane().add(selectionAndDisplayPanel);
		selectionAndDisplayPanel.setLayout(new GridLayout(2, 1, 0, 0));

		JPanel selectionPanel = new JPanel();
		selectionAndDisplayPanel.add(selectionPanel);
		selectionPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel = new JPanel();
		selectionPanel.add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		JScrollPane scrollPane_2 = new JScrollPane();
		panel.add(scrollPane_2);

		fileList = new JList<String>();
		scrollPane_2.setViewportView(fileList);
		fileList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (arg0.getSource() == fileList) {
					String fileName = (String) fileList.getSelectedValue();
					try {
						fillList(new File("test/" + fileName));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});

		JPanel panel_1 = new JPanel();
		selectionPanel.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);

		entryList = new JList<String>();
		entryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		entryList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (arg0.getSource() == entryList) {
					int index = entryList.getSelectedIndex();
					LbxEntry entry = entries.get(index);
					List<Integer> content = entry.getContent().toList();
					// System.err.println(System.currentTimeMillis());
					tableModel.setContent(content);
					table.revalidate();
					try {
						picturePanel.setPicture(LbxPicture.createFrom(entry
								.getContent()));
					} catch (IOException e) {
						e.printStackTrace();
					}
					picturePanel.repaint();
					// System.err.println(System.currentTimeMillis());
				}
			}
		});
		scrollPane.setViewportView(entryList);

		LbxArchiveReader reader = new LbxArchiveReader(
				BinaryBlob.createFromFile(new File("test/FONTS.LBX")));
		LbxArchive archive = reader.getArchive();
		LbxEntry paletteEntry = archive.getEntries().get(2);

		picturePanel = new LbxPicturePanel(LbxPalette.createFrom(paletteEntry
				.getContent()));
		selectionAndDisplayPanel.add(picturePanel);

		JPanel hexPanel = new JPanel();
		frmLbxBrowser.getContentPane().add(hexPanel);
		hexPanel.setLayout(new GridLayout(1, 0, 0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		hexPanel.add(scrollPane_1);
		table = new JTable(tableModel);
		scrollPane_1.setViewportView(table);
	}

}
