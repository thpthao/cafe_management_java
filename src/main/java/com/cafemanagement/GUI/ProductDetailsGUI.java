package com.cafemanagement.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.function.BiConsumer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

import com.cafemanagement.BLL.ProductBLL;
import com.cafemanagement.DTO.Product;
import com.cafemanagement.custom.Button;
import com.cafemanagement.custom.RoundPanel;
import com.cafemanagement.utils.Resource;
import com.cafemanagement.utils.VNString;

public class ProductDetailsGUI extends JFrame {
    private Vector<String> sizeList;
    private Vector<String> costList;
    private RoundPanel frame;
    private RoundPanel frame_content;
    private Button minimize;
    private Button exit;
    private Button plus;
    private Button minus;
    private Button confirm;
    private JLabel text_product;
    private RoundPanel[] roundPanel;
    private RoundPanel frameImg;
    private ImageIcon originalIcon;
    private JLabel slProductImg;


    private RoundPanel rpNote;
    private RoundPanel rpTextNote;

    private JLabel labelNote;

    private  JTextArea jTextNote;

    private JLabel[] label;
    private SaleGUI saleGUI;
    private JComboBox<String> comboBoxProductSize;
    private int quantity;
    private double discountCost;

    private String saveNote;
    private Product newProduct;
    private Product getProduct;
    private double amount;

    public ProductDetailsGUI(SaleGUI saleGUI, Product product, int quantity, String saveNote) {
        System.gc();
        this.newProduct = product;
        this.saleGUI = saleGUI;
        this.quantity = quantity;
        initComponents();
       sizeList = new Vector<>();
        costList = new Vector<>();
        amount = 1;
        List<Product> products = new ProductBLL().findProductsBy(Map.of("NAME", product.getName()));
        for (Product product1 : products) {
            costList.add(VNString.currency(product1.getCost()));
            sizeList.add(product1.getSized());
        }
        comboBoxProductSize.setModel(new DefaultComboBoxModel<>(sizeList));
        label[4].setText(costList.get(0));
        label[5].setText(String.valueOf(quantity));
        System.out.println(saveNote);
        jTextNote.setText(saveNote);
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));
    }

    public void initComponents() {
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setUndecorated(true);


        frame_content = new RoundPanel();
        frame = new RoundPanel();
        exit = new Button();
        minimize = new Button();
        text_product = new JLabel();
        roundPanel = new RoundPanel[12];
        label = new JLabel[12];
        frameImg = new RoundPanel();
        originalIcon = new ImageIcon();
        confirm = new Button();
        slProductImg = new JLabel();
        minus = new Button();
        plus = new Button();
        comboBoxProductSize = new JComboBox<>();
        rpNote = new RoundPanel();
        labelNote = new JLabel();
        jTextNote = new JTextArea();
        rpTextNote = new RoundPanel();

        for (int i = 0; i < roundPanel.length; i++) {
            roundPanel[i] = new RoundPanel();
            label[i] = new JLabel();
        }

        frame.setLayout(new FlowLayout());
        frame.setBackground(new Color(255, 255, 255));
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.add(frame);

        //nORTH 
        roundPanel[0].setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 10));
        roundPanel[0].setBackground(new Color(31,57,51));
        roundPanel[0].setPreferredSize(new Dimension(1000, 50));
        frame.add(roundPanel[0]);

        //center
        frame_content.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));
        frame_content.setBackground(new Color(255,255,255));
        frame_content.setPreferredSize(new Dimension(1000, 700));
        frame.add(frame_content,BorderLayout.CENTER);


        roundPanel[1].setLayout(new FlowLayout(FlowLayout.CENTER,10,0));
        roundPanel[1].setBackground(new Color(255,255,255));
        roundPanel[1].setPreferredSize(new Dimension(1000, 50));
        //roundPanel[1].setBackground(new Color(145, 0, 0));
        frame_content.add(roundPanel[1],BorderLayout.NORTH);

        roundPanel[2].setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        roundPanel[2].setPreferredSize(new Dimension(310, 250));
        roundPanel[2].setBackground(new Color(31, 57, 51));
        frame_content.add(roundPanel[2], BorderLayout.WEST);

        roundPanel[3].setLayout(new FlowLayout(FlowLayout.CENTER));
        roundPanel[3].setBackground(new Color(255,255,255));
        roundPanel[3].setPreferredSize(new Dimension(650, 250));
        frame_content.add(roundPanel[3], BorderLayout.EAST);

        roundPanel[4].setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        roundPanel[4].setBackground(new Color(255,255,255));
        roundPanel[4].setPreferredSize(new Dimension(1000, 50));
        frame_content.add(roundPanel[4],BorderLayout.SOUTH);

        BiConsumer<Button, List<Object>> configButton = (button, properties) -> {
            button.setBorder(null);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setFont(new Font("Times New Roman", Font.PLAIN, 16));
            button.setBorderColor(Color.BLACK);
            button.setForeground(Color.BLACK);
            button.setText((String) properties.get(0));
            button.setPreferredSize(new Dimension((Integer) properties.get(1), (Integer) properties.get(2)));
            button.setRadius((Integer) properties.get(3));
            button.setColor((Color) properties.get(4));
            button.setColorOver((Color) properties.get(5));
            button.setColorClick((Color) properties.get(6));
            button.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent evt) {
                    ((Runnable) properties.get(7)).run();
                }
            });
        };
        configButton.accept(minimize, List.of("-", 45, 25, 15, new Color(0xF3F0F0), new Color(0xC4BDBD), new Color(0x676161), (Runnable) this::minimize));
        roundPanel[0].add(minimize);
        configButton.accept(exit, List.of("X", 45, 25, 15, new Color(0xFD1111), new Color(0xB04848), new Color(0xE79292), (Runnable) this::exit));
        roundPanel[0].add(exit);

        text_product.setText("CHI TIẾT MUA HÀNG");
        text_product.setForeground(new Color(31, 57, 51));
        text_product.setFont(new Font("Times New Roman", Font.BOLD, 25));
        text_product.setPreferredSize(new Dimension(260, 40));
        roundPanel[1].add(text_product);

        frameImg.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        frameImg.setPreferredSize(new Dimension(300, 240));
        frameImg.setBackground(new Color(31, 57, 51));
        roundPanel[2].add(frameImg);

        originalIcon = Resource.loadImageIcon(newProduct.getImage());
        slProductImg.setIcon(new ImageIcon(originalIcon.getImage().getScaledInstance(300, 240, Image.SCALE_SMOOTH)));
        frameImg.add(slProductImg); 

        roundPanel[5].setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
        roundPanel[5].setBackground(new Color(255,255,255));
        roundPanel[5].setPreferredSize(new Dimension(650, 50));
        roundPanel[3].add(roundPanel[5]);

        roundPanel[6].setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
        roundPanel[6].setBackground(new Color(255,255,255));
        roundPanel[6].setPreferredSize(new Dimension(650, 50));
        roundPanel[3].add(roundPanel[6]);

        rpNote.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
        rpNote.setBackground(new Color(255,255,255));
        rpNote.setPreferredSize(new Dimension(650, 50));
        roundPanel[3].add(rpNote);

        roundPanel[7].setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
        roundPanel[7].setBackground(new Color(255,255,255));
        roundPanel[7].setPreferredSize(new Dimension(650, 50));
        roundPanel[3].add(roundPanel[7]);

        label[0].setText("Tên sản phẩm\t:");
        label[0].setPreferredSize(new Dimension(130, 35));
        label[0].setFont(new Font("Times New Roman", Font.BOLD, 17));
        label[0].setForeground(Color.BLACK);
        roundPanel[5].add(label[0]);

        label[1].setText("Giá\t\t\t:");
        label[1].setPreferredSize(new Dimension(130, 35));
        label[1].setFont(new Font("Times New Roman", Font.BOLD, 17));
        label[1].setForeground(Color.BLACK);
        roundPanel[6].add(label[1]);


        label[2].setText("Số lượng\t\t:");
        label[2].setPreferredSize(new Dimension(130, 35));
        label[2].setFont(new Font("Times New Roman", Font.BOLD, 17));
        label[2].setForeground(Color.BLACK);
        roundPanel[7].add(label[2]);

        labelNote.setText("Ghi chú\t\t:");
        labelNote.setPreferredSize(new Dimension(130, 35));
        labelNote.setFont(new Font("Times New Roman", Font.BOLD, 17));
        labelNote.setForeground(Color.BLACK);
        rpNote.add(labelNote);

        rpTextNote.setLayout(new FlowLayout(FlowLayout.CENTER,0,5));
        rpTextNote.setPreferredSize(new Dimension(450, 30));
        rpTextNote.setBackground(new Color(240, 240, 240));
        rpNote.add(rpTextNote);

        jTextNote.setPreferredSize(new Dimension(420, 30));
        jTextNote.setFont(new Font("Times New Roman", Font.BOLD, 14));
        jTextNote.setForeground(Color.BLACK);
        jTextNote.setBackground(new Color(240, 240, 240));
        PlainDocument doc = (PlainDocument) jTextNote.getDocument();

        doc.setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
                // Check if the resulting text length would exceed 20 characters
                if (fb.getDocument().getLength() + text.length() <= 60) {
                    super.insertString(fb, offset, text, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                // Check if the resulting text length would exceed 20 characters
                if (fb.getDocument().getLength() - length + text.length() <= 60) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
        rpTextNote.add(jTextNote);

        roundPanel[8].setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        roundPanel[8].setBackground(new Color(240, 240, 240));
        roundPanel[8].setPreferredSize(new Dimension(400, 35));
        roundPanel[5].add(roundPanel[8]);

        label[3].setText(newProduct.getName());
        label[3].setPreferredSize(new Dimension(400, 35));
        label[3].setHorizontalAlignment(JLabel.CENTER);
        label[3].setFont(new Font("Times New Roman", Font.BOLD, 15));
        label[3].setForeground(Color.BLACK);
        roundPanel[8].add(label[3]);

        roundPanel[9].setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        roundPanel[9].setBackground(new Color(240, 240, 240));
        roundPanel[9].setPreferredSize(new Dimension(150, 35));
        roundPanel[6].add(roundPanel[9]);

        label[4].setPreferredSize(new Dimension(150, 35));
        label[4].setHorizontalAlignment(JLabel.CENTER);
        label[4].setFont(new Font("Times New Roman", Font.BOLD, 15));
        label[4].setForeground(Color.BLACK);
        roundPanel[9].add(label[4]);

        configButton.accept(minus, List.of("-", 35, 35, 50, new Color(31,57,51), new Color(0,98,65), new Color(0,98,65), (Runnable) this::minusPress));
        minus.setFont(new Font("Tahoma", Font.PLAIN, 16));
        minus.setBorderColor(new Color(0x1F3933));
        minus.setForeground(new Color(0xffffff));
        roundPanel[7].add(minus);

        roundPanel[10].setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        roundPanel[10].setBackground(new Color(240, 240, 240));
        roundPanel[10].setPreferredSize(new Dimension(50, 35));
        roundPanel[7].add(roundPanel[10]);

        label[5].setPreferredSize(new Dimension(50, 35));
        label[5].setHorizontalAlignment(JLabel.CENTER);
        label[5].setFont(new Font("Times New Roman", Font.BOLD, 15));
        label[5].setForeground(Color.BLACK);
        roundPanel[10].add(label[5]);

        configButton.accept(plus, List.of("+", 35, 35, 50, new Color(31,57,51), new Color(0,98,65), new Color(0,98,65), (Runnable) this::plusPress));
        plus.setFont(new Font("Tahoma", Font.PLAIN, 16));
        plus.setBorderColor(new Color(0x1F3933));
        plus.setForeground(new Color(0xffffff));
        roundPanel[7].add(plus);

        label[6].setText("Size:");
        label[6].setPreferredSize(new Dimension(50, 35));
        label[6].setFont(new Font("Times New Roman", Font.BOLD, 17));
        label[6].setForeground(Color.BLACK);
        roundPanel[7].add(label[6]);

        comboBoxProductSize.setFont(new Font("Dialog", Font.PLAIN, 12));
        comboBoxProductSize.setMaximumRowCount(10);//so luong
        comboBoxProductSize.setPreferredSize(new Dimension(100, 35));
        comboBoxProductSize.setBorder(null);
        comboBoxProductSize.setFocusable(false);
        comboBoxProductSize.setBackground(new Color(240, 240, 240));
        comboBoxProductSize.setForeground(Color.BLACK);
        comboBoxProductSize.addActionListener(e -> selectSize());
        roundPanel[7].add(comboBoxProductSize);

        configButton.accept(confirm, List.of("Xác nhận", 150, 50, 45, new Color(31,57,51), new Color(0,98,65), new Color(0,98,65), (Runnable) this::pressConfirm));
        confirm.setIcon(Resource.loadImageIcon(""));
        confirm.setFont(new Font("Times New Roman", Font.BOLD, 16));
        confirm.setBorderColor(new Color(0x1F3933));
        confirm.setForeground(new Color(0xffffff));
        roundPanel[4].add(confirm);
    }

    public void minusPress() {
        int quantity = Integer.parseInt(label[5].getText());
        if (quantity > 1) {
            quantity--;
            label[5].setText(Integer.toString(quantity));
        }
    }

    public void plusPress() {
        int quantity = Integer.parseInt(label[5].getText());
        if (quantity < 100) {
            quantity++;
            label[5].setText(Integer.toString(quantity));
        }
    }

    private void minimize() {
        setState(HomeGUI.ICONIFIED);
    }

    private void exit() {
        this.dispose();
    }

    public void selectSize() {
        for (int i = 0; i < sizeList.size(); i++) {
            if (comboBoxProductSize.getSelectedItem().toString().equals(sizeList.get(i))) {
                label[4].setText(costList.get(i));
                break;
            }
        }
    }

    private Product checkOrderExits(Product product) {
        for (int i = 0; i < saleGUI.getListDetailBill().size(); i++) {
            if (product.getName().equals(saleGUI.getListDetailBill().get(i).getName()) &&
                product.getSized().equals(saleGUI.getListDetailBill().get(i).getSized())) {
                return saleGUI.getListDetailBill().get(i);
            }
        }
        return null;
    }

    public void pressConfirm() {
        getProduct = new ProductBLL()
            .searchProducts("NAME = '" + newProduct.getName() + "'", "SIZED = '" + comboBoxProductSize.getSelectedItem().toString() + "'")
            .get(0);
        int quantity = Integer.parseInt(label[5].getText());
        discountCost = getProduct.getCost() * amount;
        saveNote = jTextNote.getText();
        if (checkOrderExits(getProduct) != null) {
            int location = saleGUI.getListDetailBill().indexOf(checkOrderExits(getProduct));
        }

            if (checkOrderExits(getProduct) != null) {
                System.out.println("updating");
                int location = saleGUI.getListDetailBill().indexOf(checkOrderExits(getProduct));
                saleGUI.getListQuantityChoice().set(location, quantity);
                saleGUI.getListCost().set(location,(int) discountCost);
                saleGUI.getListDiscountNote().set(location, saveNote);
            } else {
                System.out.println("add new");
                saleGUI.getListDetailBill().add(getProduct);
                saleGUI.getListQuantityChoice().add(quantity);
                saleGUI.getListCost().add((int) discountCost);
                saleGUI.getListDiscountNote().add(saveNote);
            }

            saleGUI.getRoundPanel9().removeAll();
            saleGUI.addProductToBill(saleGUI.getListDetailBill(), saleGUI.getListQuantityChoice());
            this.dispose();

    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
