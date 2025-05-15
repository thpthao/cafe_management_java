package com.cafemanagement.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Vector;
import java.util.function.BiConsumer;
import java.util.function.Function;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.cafemanagement.BLL.BillBLL;
import com.cafemanagement.BLL.BillDetailsBLL;
import com.cafemanagement.BLL.CategoryBLL;
import com.cafemanagement.BLL.ProductBLL;
import com.cafemanagement.DTO.Bill;
import com.cafemanagement.DTO.BillDetails;
import com.cafemanagement.DTO.Category;
import com.cafemanagement.DTO.Product;
import com.cafemanagement.custom.BillDetailPanel;
import com.cafemanagement.custom.Button;
import com.cafemanagement.custom.PlaceholderTextField;
import com.cafemanagement.custom.ProductPanel;
import com.cafemanagement.custom.RoundPanel;
import com.cafemanagement.utils.Day;
import com.cafemanagement.utils.Resource;
import com.cafemanagement.utils.VNString;


public class SaleGUI extends JPanel {
    private List<Product> listDetailBill = new ArrayList<>();
    private List<Integer> listQuantityChoice = new ArrayList<>();
    private List<String> listDiscountNote = new ArrayList<>();
    private List<Integer> listCost = new ArrayList<>();
    private List<Product> productlist = new ArrayList<>();
    private ProductBLL productBLL = new ProductBLL();

    private BillBLL billBLL = new BillBLL();
    private BillDetailsBLL billDetailsBLL = new BillDetailsBLL();
    private CategoryBLL categoryBLL = new CategoryBLL();
    private List<Object> categoryNameList;
    private ProductDetailsGUI productDetailsGUI;
    private String staffID;
    private JLabel[] infoLabel;
    private JComboBox<String> jComboBox;
    private PlaceholderTextField txtSearch1;
    private JTextField[] jTextField;
    private JLabel[] jLabel;
    private RoundPanel sale;
    private RoundPanel roundPanel1;
    private RoundPanel roundPanel2;
    private RoundPanel roundPanel3;
    private RoundPanel roundPanel4;
    private RoundPanel roundPanel5;
    private RoundPanel roundPanel6;
    private RoundPanel roundPanel8;
    private RoundPanel roundPanel9;
    private RoundPanel roundPanel10;
    private RoundPanel roundPanel11;
    private RoundPanel roundPanel12;
    private RoundPanel roundPanel13;
    private RoundPanel roundPanel14;
    private RoundPanel roundPanel15;
    private JScrollPane productScrollPane2;
    private JScrollPane productScrollPane1;
    private Button btnSearch1;
    private Button btnSearch2;
    private Button btnPurchase;
    private Button btnCancel;

    private final ProductPanel[] productPanel = new ProductPanel[productBLL.findProductsBy(Map.of()).size()];
    private int sl = 0;

    public SaleGUI(String staffID) {
        System.gc();
        this.staffID = staffID;
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(248,247,248));
        initComponents();
    }

    public List<Product> getListDetailBill() {
        return listDetailBill;
    }

    public void setListDetailBill(List<Product> listDetailBill) {
        this.listDetailBill = listDetailBill;
    }

    public List<Integer> getListQuantityChoice() {
        return listQuantityChoice;
    }

    public void setListQuantityChoice(List<Integer> listQuantityChoice) {
        this.listQuantityChoice = listQuantityChoice;
    }

    public List<Integer> getListCost() {
        return listCost;
    }

    public void setListCost (List<Integer> listCost) {
        this.listCost = listCost;
    }
    public List<String> getListDiscountNote() {
        return listDiscountNote;
    }

    public void setListDiscountNote (List<String> listDiscountNote) {
        this.listDiscountNote = listDiscountNote;
    }

    public RoundPanel getRoundPanel9() {
        return roundPanel9;
    }

    public void setRoundPanel9(RoundPanel roundPanel9) {
        this.roundPanel9 = roundPanel9;
    }

    public void initComponents() {
        Font fontTimesNewRoman = new Font("Times New Roman", Font.PLAIN, 14);
        roundPanel1 = new RoundPanel();
        roundPanel2 = new RoundPanel();
        roundPanel3 = new RoundPanel();
        roundPanel4 = new RoundPanel();
        roundPanel5 = new RoundPanel();
        roundPanel6 = new RoundPanel();
        roundPanel8 = new RoundPanel();
        roundPanel9 = new RoundPanel();
        roundPanel10 = new RoundPanel();
        roundPanel11 = new RoundPanel();
        roundPanel12 = new RoundPanel();
        roundPanel13 = new RoundPanel();
        roundPanel14 = new RoundPanel();
        roundPanel15 = new RoundPanel();

        productScrollPane2 = new JScrollPane(roundPanel9);
        productScrollPane1 = new JScrollPane(roundPanel4);

        btnSearch1 = new Button();
        btnSearch2 = new Button();
        btnPurchase = new Button();
        btnCancel = new Button();

        txtSearch1 = new PlaceholderTextField("Nhập tên sản phẩm ...");
        sale = new RoundPanel();
        jComboBox = new JComboBox<>();

        sale.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        sale.setBackground(new Color(248, 247, 248));
        this.add(sale, BorderLayout.CENTER);

        roundPanel1.setPreferredSize(new Dimension(615, 670));
        roundPanel1.setAutoscrolls(true);
        sale.add(roundPanel1);

        roundPanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
        roundPanel2.setPreferredSize(new Dimension(370, 670));
        roundPanel2.setAutoscrolls(true);
        sale.add(roundPanel2);

        roundPanel3.setLayout(new BorderLayout(90, 0));
        roundPanel3.setPreferredSize(new Dimension(615, 50));
        roundPanel3.setAutoscrolls(true);
        roundPanel1.add(roundPanel3);

        productScrollPane1.setPreferredSize(new Dimension(600, 600));
        roundPanel6.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        roundPanel6.setPreferredSize(new Dimension(135, 35));
        roundPanel6.setAutoscrolls(true);
        roundPanel3.add(roundPanel6, BorderLayout.WEST);

        jComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
        jComboBox.setMaximumRowCount(10); 
        jComboBox.setPreferredSize(new Dimension(120, 35));
        jComboBox.setBorder(null);
        jComboBox.setFocusable(false);
        jComboBox.addActionListener(this::pressJComboBox);
        roundPanel6.add(jComboBox);

        roundPanel5.setLayout(new FlowLayout(FlowLayout.LEFT, 23, 5));
        roundPanel5.setPreferredSize(new Dimension(300, 35));
        roundPanel5.setAutoscrolls(true);
        roundPanel3.add(roundPanel5, BorderLayout.CENTER);

        txtSearch1.setFont(fontTimesNewRoman);
        txtSearch1.setPreferredSize(new Dimension(300, 35));
        txtSearch1.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        txtSearch1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changeText();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changeText();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                changeText();
            }
        });
        roundPanel5.add(txtSearch1);


        btnSearch1.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnSearch1.setPreferredSize(new Dimension(35, 35));
        btnSearch1.setIcon(Resource.loadImageIcon("img/icons/search.png"));
        btnSearch1.setFocusPainted(false);
        btnSearch1.setRadius(50);
        btnSearch1.setBorderColor(new Color(0x7EC8C4));
        roundPanel5.add(btnSearch1);

        categoryNameList = categoryBLL.getObjectsProperty("NAME", categoryBLL.getCategoryList());
        Vector<String> comboBoxItems = new Vector<>();
        comboBoxItems.add("TẤT CẢ");
        for (Object name : categoryNameList) {
            comboBoxItems.add(name.toString());
        }
        jComboBox.setModel(new DefaultComboBoxModel<>(comboBoxItems));

        roundPanel8.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        roundPanel8.setPreferredSize(new Dimension(350, 35));
        roundPanel2.add(roundPanel8);

        roundPanel15.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        roundPanel15.setPreferredSize(new Dimension(350, 35));
        roundPanel2.add(roundPanel15);

        productScrollPane2.setPreferredSize(new Dimension(350, 355));
        productScrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        productScrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        roundPanel9.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        roundPanel9.setBorder(BorderFactory.createLineBorder(Color.black));
        roundPanel9.setPreferredSize(new Dimension(productScrollPane2.getWidth(), productScrollPane2.getHeight()));
        roundPanel2.add(productScrollPane2);


        roundPanel10.setLayout(new BoxLayout(roundPanel10, BoxLayout.Y_AXIS));
        roundPanel10.setPreferredSize(new Dimension(350, 100));
        roundPanel2.add(roundPanel10);

        roundPanel11.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 0));
        roundPanel11.setPreferredSize(new Dimension(350, 40));
        roundPanel2.add(roundPanel11);

        roundPanel12.setLayout(new FlowLayout(FlowLayout.RIGHT));
        roundPanel10.add(roundPanel12);
        roundPanel13.setLayout(new FlowLayout(FlowLayout.RIGHT));
        roundPanel10.add(roundPanel13);
        roundPanel14.setLayout(new FlowLayout(FlowLayout.RIGHT));
        roundPanel10.add(roundPanel14);

        jLabel = new JLabel[3];
        jTextField = new JTextField[3];
        for (int i = 0; i < 3; i++) {
            jLabel[i] = new JLabel();
            jLabel[i].setFont(fontTimesNewRoman);
            jTextField[i] = new JTextField();
            jTextField[i].setFont(fontTimesNewRoman);
            jTextField[i].setHorizontalAlignment(JTextField.RIGHT);
            if (i == 0) {
                jLabel[i].setText("Tổng tiền: ");
                roundPanel12.add(jLabel[i]);
                roundPanel12.add(jTextField[i]);
            } else if (i == 1) {
                jLabel[i].setText("Tiền nhận: ");
                roundPanel13.add(jLabel[i]);
                roundPanel13.add(jTextField[i]);
            } else {
                jLabel[i].setText("Tiền thừa: ");
                roundPanel14.add(jLabel[i]);
                roundPanel14.add(jTextField[i]);
            }

            if (i != 1) {
                jTextField[i].setPreferredSize(new Dimension(123, 25));
                jTextField[i].setBorder(BorderFactory.createEmptyBorder());
                jTextField[i].setEditable(false);
                jTextField[i].setFocusable(false);
                jTextField[i].setText(VNString.currency(0.0));
            } else {
                jTextField[i].setPreferredSize(new Dimension(120, 25));
                jTextField[i].getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        calculate();
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        calculate();
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        calculate();
                    }
                });
                jTextField[i].addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        char c = e.getKeyChar();
                        if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
                            e.consume();
                        }
                    }
                });
            }
        }

        BiConsumer<Button, List<Object>> configButton = (button, properties) -> {
            button.setPreferredSize(new Dimension(135, 40));
            button.setBorderPainted(false);
            button.setRadius(15);
            button.setFocusPainted(false);
            button.setFont(new Font("Times New Roman", Font.PLAIN, 14));
            button.setBorderColor(Color.BLACK);
            button.setForeground(Color.BLACK);
            button.setText((String) properties.get(0));
            button.setColor((Color) properties.get(1));
            button.setColorOver((Color) properties.get(2));
            button.setColorClick((Color) properties.get(3));
            button.setIcon(Resource.loadImageIcon((String) properties.get(4)));
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    ((Runnable) properties.get(5)).run();
                }
            });
        };
        configButton.accept(btnPurchase, List.of("Thanh toán", new Color(0x1F3933), new Color(0,98,65),new Color(0,98,65), "", (Runnable) this::purchase));
        btnPurchase.setBorderColor(new Color(0x1F3933));
        btnPurchase.setForeground(new Color(0xffffff));
        btnPurchase.setFont(new Font("Times New Roman", Font.BOLD, 15));
        roundPanel11.add(btnPurchase);

        configButton.accept(btnCancel, List.of("Hủy", new Color(0xFFBD3737), new Color(0xFF0000), new Color(0xB65858),
                "", (Runnable) this::cancel));
        btnCancel.setBorderColor(new Color(0xFFBD3737));
        btnCancel.setForeground(new Color(0xffffff));
        btnCancel.setFont(new Font("Times New Roman", Font.BOLD, 15));
        roundPanel11.add(btnCancel);

        productScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        productScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        roundPanel4.setLayout(new FlowLayout(FlowLayout.LEFT, 7, 5));
        roundPanel4.setBorder(BorderFactory.createLineBorder(new Color(0xF8F7F8)));
        roundPanel4.setAutoscrolls(true);
        roundPanel1.add(productScrollPane1);

        roundPanel9.setAutoscrolls(true);
        Set<String> setProductNames = new HashSet<>();
        for (Product product : productBLL.getProductList()) {
            if (!setProductNames.contains(product.getName())) {
                setProductNames.add(product.getName());
                productlist.add(product);
            }
        }
        loadProducts(product -> true);
    }

    public void addProductPanel(Product product, int index) {
        RoundPanel frameProduct = new RoundPanel();
        RoundPanel frameImg = new RoundPanel();
        JTextField categoryName = new JTextField();
        JLabel productImage = new JLabel();
        JTextField[] productName = new JTextField[2];
        Color color = new Color(0XFFFFFF);

        productPanel[sl] = new ProductPanel();
        productPanel[sl].setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
        productPanel[sl].setPreferredSize(new Dimension(185, 250));
        productPanel[sl].setBackground(color);
        productPanel[sl].setColor(color);
        productPanel[sl].setColorOver(new Color(126,200,196));
        productPanel[sl].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                if (productDetailsGUI != null) {
                    productDetailsGUI.dispose();
                }
                productDetailsGUI = new ProductDetailsGUI(SaleGUI.this, product, index, "");
                productDetailsGUI.setVisible(true);
            }
        });
        roundPanel4.add(productPanel[sl]);

        frameProduct.setPreferredSize(new Dimension(181, 246));
        frameProduct.setBackground(color);
        productPanel[sl].add(frameProduct);
        sl++;

        Font fontDialog = new Font("Dialog", Font.BOLD, 15);
        Category category = categoryBLL.findCategoriesBy(Map.of("CATEGORY_ID", product.getCategoryID())).get(0);
        categoryName.setFont(fontDialog);
        categoryName.setPreferredSize(new Dimension(120, 20));
        categoryName.setHorizontalAlignment(JTextField.CENTER);
        categoryName.setEditable(false);
        categoryName.setFocusable(false);
        categoryName.setBorder(BorderFactory.createEmptyBorder());
        categoryName.setBackground(color);
        categoryName.setText(category.getName());
        frameProduct.add(categoryName);

        frameImg.setPreferredSize(new Dimension(181, 165));
        frameImg.setBackground(color);
        frameProduct.add(frameImg);

        ImageIcon originalImage = Resource.loadImageIcon(product.getImage());
        productImage.setIcon(new ImageIcon(originalImage.getImage().getScaledInstance(181, 165, Image.SCALE_SMOOTH)));
        frameImg.add(productImage);

        String[] name = {"", ""};
        if (product.getName().length() <= 15) {
            name[0] = product.getName();
        } else {
            String[] splitName = product.getName().split(" ");
            for (String word : splitName) {
                if ((name[0] + " " + word).length() <= 15)
                    name[0] += " " + word;
                else
                    name[1] += " " + word;
            }
        }

        Dimension textFieldSize = new Dimension(150, 20);
        for (int i = 0; i < 2; i++) {
            productName[i] = new JTextField(name[i].trim());
            productName[i].setFont(fontDialog);
            productName[i].setPreferredSize(textFieldSize);
            productName[i].setEditable(false);
            productName[i].setFocusable(false);
            productName[i].setBorder(BorderFactory.createEmptyBorder());
            productName[i].setBackground(new Color(0XFFFFFF));
            productName[i].setForeground(new Color(0X1F3933));
            productName[i].setHorizontalAlignment(JTextField.CENTER);
            frameProduct.add(productName[i]);
        }
    }

    public void addProductToBill(List<Product> listDetailBill, List<Integer> listQuantityChoice) {
        roundPanel9.removeAll();
        this.listDetailBill = listDetailBill;
        if (this.listDetailBill.size() >= 5) {
            int height = 75 * this.listDetailBill.size();
            roundPanel9.setPreferredSize(new Dimension(productScrollPane2.getWidth(), height));
        }
        double totalPrice = 0.0;
        for (int i = 0; i < listDetailBill.size(); i++) {
            BillDetailPanel billDetailPanel = new BillDetailPanel();
            Product product = listDetailBill.get(i);
            billDetailPanel.setData(listDetailBill.get(i), listQuantityChoice.get(i), listCost.get(i));

            int index = i;
            billDetailPanel.getPaymentFrame().addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    new ProductDetailsGUI(SaleGUI.this, product, listQuantityChoice.get(index), listDiscountNote.get(index)).setVisible(true);
                }
            });
            billDetailPanel.getPayment_img().addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (JOptionPane.showOptionDialog(null,
                        "Bạn có chắc chắn muốn xoá sản phẩm khỏi hoá đơn này?",
                        "Xác nhận",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        new String[]{"Xoá", "Huỷ"},
                        "Xoá") == JOptionPane.YES_OPTION) {
                        listDetailBill.remove(index);
                        listQuantityChoice.remove(index);
                        roundPanel9.repaint();
                        roundPanel9.revalidate();
                        addProductToBill(listDetailBill, listQuantityChoice);
                    }
                }
            });
            totalPrice += listCost.get(i) * listQuantityChoice.get(i);
            roundPanel9.add(billDetailPanel);
            roundPanel9.repaint();
            roundPanel9.revalidate();
        }
        jTextField[0].setText(VNString.currency(totalPrice));
        calculate();
    }

    public void purchase() {
        if (roundPanel4.getComponentCount() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để mua", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else if (jTextField[1].getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nhập số tiền nhận từ khách hàng để thanh toán", "Lỗi", JOptionPane.ERROR_MESSAGE);
            jTextField[1].requestFocusInWindow();
            jTextField[1].selectAll();
        } else if (Double.parseDouble(jTextField[2].getText().replaceAll("[^\\d-]+", "")) > 0.0) {
            JOptionPane.showMessageDialog(this, "Không đủ tiền", "Lỗi", JOptionPane.ERROR_MESSAGE);
            jTextField[1].requestFocusInWindow();
            jTextField[1].selectAll();
        } else {
            Bill bill;
            BillDetails addbillDetails;
            String billID = billBLL.getAutoID();
            String customerID = "CUS000";
            SimpleDateFormat formatter = new SimpleDateFormat();
            formatter.applyPattern("yyyy-MM-dd");
           String staffID = this.staffID;
            Day date = new Day();
            double received = Double.parseDouble(jTextField[1].getText());
            double excess = Double.parseDouble(jTextField[2].getText().replaceAll("\\D+", "")) * -1;
          bill = new Bill(billID,  staffID, date, 0.0, received, excess, false);

            if (billBLL.exists(bill))
                JOptionPane.showMessageDialog(this, "Hoá đơn đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            else if (billBLL.exists(Map.of("BILL_ID", bill.getBillID())))
                JOptionPane.showMessageDialog(this, "Hoá đơn đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            else if (billBLL.addBill(bill))
                JOptionPane.showMessageDialog(this, "Mua hàng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(this, "Mua hàng thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);

            double billDetail_total;
            double billDetail_percent;
            for (int i = 0; i < listDetailBill.size(); i++) {
                Product product = listDetailBill.get(i);
                billDetail_total = listCost.get(i) ;
                billDetail_percent = 0.0;
                addbillDetails = new BillDetails(billID, product.getProductID(), listQuantityChoice.get(i), listDiscountNote.get(i), billDetail_total, billDetail_percent);
                billDetailsBLL.addBillDetails(addbillDetails);
            }
            cancel();
        }      cancel();
    }

    public void cancel() {
        int size = listDetailBill.size();
        for (int i = 0; i < size; i++) {
            listDetailBill.remove(0);
            listQuantityChoice.remove(0);
        }
        roundPanel9.removeAll();
        roundPanel9.repaint();
        roundPanel9.revalidate();
        roundPanel9.setPreferredSize(new Dimension(productScrollPane2.getWidth(), 352));
        jTextField[0].setText(VNString.currency(0.0));
        jTextField[1].setText("");
        jTextField[2].setText(VNString.currency(0.0));
    }

    public void pressJComboBox(ActionEvent evt) {
        String categoryName = Objects.requireNonNull(jComboBox.getSelectedItem()).toString();
        String categoryID;
        if (categoryName.equals("TẤT CẢ"))
            categoryID = "";
        else
            categoryID = categoryBLL.findCategoriesBy(Map.of("NAME", categoryName)).get(0).getCategoryID();
        refreshProducts(product -> product.getCategoryID().contains(categoryID));
    }

    public void changeText() {
        String name = VNString.removeUpperCaseAccent(txtSearch1.getText().toUpperCase());
        refreshProducts(product -> VNString.removeUpperCaseAccent(product.getName()).contains(name));
    }

    public void refreshProducts(Function<Product, Boolean> condition) {
        roundPanel4.removeAll();
        roundPanel4.repaint();
        roundPanel4.revalidate();
        int size = 0;
        for (int i = 0; i < productlist.size(); i++) {
            if (condition.apply(productlist.get(i))) {
                roundPanel4.add(productPanel[i]);
                size++;
            }
        }
        double height = 256 * Math.ceil((double) size / 3);
        roundPanel4.setPreferredSize(new Dimension(productScrollPane1.getWidth(), (int) height));
    }

    public void loadProducts(Function<Product, Boolean> condition) {
        roundPanel4.removeAll();
        roundPanel4.repaint();
        roundPanel4.revalidate();
        int size = 0;
        for (Product product : productlist) {
            if (condition.apply(product)) {
                addProductPanel(product, 1);
                size++;
            }
        }
        double height = 256 * Math.ceil((double) size / 3);
        roundPanel4.setPreferredSize(new Dimension(productScrollPane1.getWidth(), (int) height));
    }

    public void calculate() {
        String text = jTextField[1].getText();
        double change = 0.0;
        if (!text.isEmpty())
            change = Double.parseDouble(jTextField[0].getText().replaceAll("\\D+", "")) - Double.parseDouble(text);
        jTextField[2].setText(VNString.currency(change));
    }

}
