package com.liyulin.skills.awt.event;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 组合框联动效果
 * 
 * @author liyulin
 * @date 2013/03/08
 */
@SuppressWarnings("serial")
public class ItemDemo extends JFrame implements ItemListener {

    private JPanel p;
    @SuppressWarnings("rawtypes")
	private JComboBox cmbProv, cmbCity;

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public ItemDemo() {
        super("组合框联动");
        p = new JPanel();
        String str[] = {"山东", "安徽", "江苏"};
        cmbProv = new JComboBox(str);
        cmbCity = new JComboBox();
        cmbCity.addItem("济南");
        cmbCity.addItem("青岛");
        cmbCity.addItem("烟台");
        p.add(cmbProv);
        p.add(cmbCity);
        cmbProv.addItemListener(this);
        this.add(p);
        this.setSize(200, 150);
        this.setLocation(100, 100);
        this.setDefaultCloseOperation(3);
    }

    @SuppressWarnings("unchecked")
	public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            cmbCity.removeAllItems();
            String item = e.getItem().toString();
            if (item.equals("山东")) {
                cmbCity.addItem("济南");
                cmbCity.addItem("青岛");
                cmbCity.addItem("烟台");
            }
            if (item.equals("安徽")) {
                cmbCity.addItem("合肥");
                cmbCity.addItem("芜湖");
                cmbCity.addItem("蚌埠");
            }
            if (item.equals("江苏")) {
                cmbCity.addItem("南京");
                cmbCity.addItem("江苏");
                cmbCity.addItem("扬州");
            }
        }
    }

    public static void main(String[] args) {
        ItemDemo f = new ItemDemo();
        f.setVisible(true);
    }
}