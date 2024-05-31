package MyDesign;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author QUANG DIEN
 */
public class Button_Login extends JButton{
    
    private Shape shape;
    private final RippleEffect_Login rippleEffect;
    
    
    public Button_Login() {
        rippleEffect = new RippleEffect_Login(this);
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(8, 5, 8, 5));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 =(Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(255, 255, 255, 80));
        g2.fill(shape);
        rippleEffect.reder(g2, shape);
        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height); 
        shape = new FancyBorderRadius_Login(getWidth(), getHeight(), "15% 85% 63% 37% / 38% 29% 71% 62%").getShape();
    }

    
    
    
    
}
