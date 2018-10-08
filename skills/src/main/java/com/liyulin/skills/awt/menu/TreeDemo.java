package com.liyulin.skills.awt.menu;

import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

/**
 * 树形菜单
 * 
 * @author liyulin
 * @date 2013/03/08
 */
@SuppressWarnings("serial")
public class TreeDemo extends JFrame implements TreeSelectionListener {

	private DefaultMutableTreeNode root;
	private DefaultTreeModel model;
	private JTree tree;
	private JTextArea textArea;
	private JPanel p;

	public TreeDemo() {
		super("TreeBrowserTest");
		root = makeSampleTree();
		model = new DefaultTreeModel(root);
		tree = new JTree(model);
		tree.addTreeSelectionListener(this);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		p = new JPanel(new GridLayout(1, 2));
		p.add(new JScrollPane(tree));
		textArea = new JTextArea();
		p.add(new JScrollPane(textArea));
		this.add(p);
		this.setSize(400, 300);
		this.setLocation(100, 100);
		this.setDefaultCloseOperation(3);
	}

	public DefaultMutableTreeNode makeSampleTree() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("世界");
		DefaultMutableTreeNode country = new DefaultMutableTreeNode("中国");
		root.add(country);
		DefaultMutableTreeNode state = new DefaultMutableTreeNode("山东");
		country.add(state);
		DefaultMutableTreeNode city = new DefaultMutableTreeNode("青岛");
		state.add(city);
		city = new DefaultMutableTreeNode("济南");
		state.add(city);
		state = new DefaultMutableTreeNode("江苏");
		country.add(state);
		city = new DefaultMutableTreeNode("南京");
		state.add(city);
		return root;
	}

	@Override
	public void valueChanged(TreeSelectionEvent event) {
		TreePath path = tree.getSelectionPath();
		if (path == null) {
			return;
		}
		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
		textArea.setText(selectedNode.getUserObject().toString());
	}

	public static void main(String[] args) {
		TreeDemo f = new TreeDemo();
		f.setVisible(true);
	}
}
