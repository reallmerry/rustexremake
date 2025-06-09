package javax.vecmath;

import java.awt.Color;
import java.io.Serializable;

public class Color4b extends Tuple4b implements Serializable {
   public static final long false = -105080578052502155L;

   public Color4b(byte arg0, byte arg1, byte arg2, byte arg3) {
      super(arg0, arg1, arg2, arg3);
   }

   public Color4b(byte[] arg0) {
      super(arg0);
   }

   public Color4b(Color4b arg0) {
      super((Tuple4b)arg0);
   }

   public Color4b(Tuple4b arg0) {
      super(arg0);
   }

   public Color4b(Color arg0) {
      super((byte)arg0.getRed(), (byte)arg0.getGreen(), (byte)arg0.getBlue(), (byte)arg0.getAlpha());
   }

   public Color4b() {
   }

   public final void null(Color arg0) {
      this.null = (byte)arg0.getRed();
      this.int = (byte)arg0.getGreen();
      this.final = (byte)arg0.getBlue();
      this.this = (byte)arg0.getAlpha();
   }

   public final Color null() {
      int var1 = this.null & 255;
      int var2 = this.int & 255;
      int var3 = this.final & 255;
      int var4 = this.this & 255;
      return new Color(var1, var2, var3, var4);
   }
}
