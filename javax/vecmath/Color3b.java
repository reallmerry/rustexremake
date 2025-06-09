package javax.vecmath;

import java.awt.Color;
import java.io.Serializable;

public class Color3b extends Tuple3b implements Serializable {
   public static final long null = 6632576088353444794L;

   public Color3b(byte arg0, byte arg1, byte arg2) {
      super(arg0, arg1, arg2);
   }

   public Color3b(byte[] arg0) {
      super(arg0);
   }

   public Color3b(Color3b arg0) {
      super((Tuple3b)arg0);
   }

   public Color3b(Tuple3b arg0) {
      super(arg0);
   }

   public Color3b(Color arg0) {
      super((byte)arg0.getRed(), (byte)arg0.getGreen(), (byte)arg0.getBlue());
   }

   public Color3b() {
   }

   public final void null(Color arg0) {
      this.int = (byte)arg0.getRed();
      this.final = (byte)arg0.getGreen();
      this.this = (byte)arg0.getBlue();
   }

   public final Color null() {
      int var1 = this.int & 255;
      int var2 = this.final & 255;
      int var3 = this.this & 255;
      return new Color(var1, var2, var3);
   }
}
