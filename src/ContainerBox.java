import java.awt.Color;
import java.awt.Graphics;

public class ContainerBox {
   int minX, maxX, minY, maxY;  // Box's bounds (package access)
   private Color colorFilled;   // Box's filled color (background)
   private Color colorBorder;   // Box's border color
   private static final Color DEFAULT_COLOR_FILLED = Color.white;
   private static final Color DEFAULT_COLOR_BORDER = Color.black;
   

   public ContainerBox(int x, int y, int width, int height, Color colorFilled, Color colorBorder) {
      minX = x;
      minY = y;
      maxX = x + width - 1;
      maxY = y + height - 1;
      this.colorFilled = colorFilled;
      this.colorBorder = colorBorder;
   }
   

   public ContainerBox(int x, int y, int width, int height) {
      this(x, y, width, height, DEFAULT_COLOR_FILLED, DEFAULT_COLOR_BORDER);
   }
   

   public void set(int x, int y, int width, int height) {
      minX = x;
      minY = y;
      maxX = x + width - 1;
      maxY = y + height - 1;
   }


   public void draw(Graphics g) {
      g.setColor(colorFilled);
      g.fillRect(minX, minY, maxX - minX - 1, maxY - minY - 1);
      g.setColor(colorBorder);
      g.drawRect(minX, minY, maxX - minX - 1, maxY - minY - 1);
   }
}