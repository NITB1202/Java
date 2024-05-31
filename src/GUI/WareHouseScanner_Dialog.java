package GUI;

import javax.swing.*;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
public class WareHouseScanner_Dialog {
    private JFrame frame;
    private boolean visible;
    private WareHouseImport_Dialog whid;
    public  JTextField txtField;
    public String barcode;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WareHouseScanner_Dialog scanner = new WareHouseScanner_Dialog();
            scanner.initAndShowGUI();
        });
    }


    private void initAndShowGUI() {
        frame = new JFrame("Barcode Scanner");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initWebcam();
        initTxtField();
        frame.setVisible(true);
    }
    
    public WareHouseScanner_Dialog() {
        frame = new JFrame("Barcode Scanner");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initAndShowGUI();
    }
    
    private void initWebcam() {
        final Webcam webcam = Webcam.getDefault();

        if (webcam == null) {
            System.out.println("No webcam found");
            return;
        }

        webcam.setViewSize(WebcamResolution.VGA.getSize());
        WebcamPanel panel = new WebcamPanel(webcam);
        panel.setFPSDisplayed(true);
        panel.setFillArea(true);
        frame.add(panel);
        frame.pack();

        Thread thread = new Thread(() -> {
            do {
                try {
                    LuminanceSource source = new BufferedImageLuminanceSource(webcam.getImage());
                    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                    Reader reader = new MultiFormatReader();
                    Result result = reader.decode(bitmap);
                    if (result != null) {
                        SwingUtilities.invokeLater(() -> {
                            System.out.println("ISBN: " + result.getText());
                            txtField.setText(result.getText());
                        });
                    }
                    JOptionPane.showMessageDialog(frame, "ISBN: "+result.getText(),"Thông Báo",JOptionPane.INFORMATION_MESSAGE);
                } catch (NotFoundException | ChecksumException | FormatException e) {
                    // Barcode not found
                }
            } while (true);
        });

        thread.setDaemon(true);
        thread.start();
    }
    
    private void initTxtField() {
        txtField = new JTextField(20);
        // Thêm txtField vào frame hoặc panel tại đây
    }
    
    public void setVisible(boolean b) {
        frame.setVisible(b);
    }
}
