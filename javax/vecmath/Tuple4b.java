package javax.vecmath;

import android.gesture.GestureStore;
import java.io.Serializable;
import net.minecraft.client.gui.inventory.GuiDispenser;

public abstract class Tuple4b implements Serializable, Cloneable {
   public static final long false = -8226727741811898211L;
   public byte null;
   public byte int;
   public byte final;
   public byte this;

   public Tuple4b(byte arg0, byte arg1, byte arg2, byte arg3) {
      thisx.null = arg0;
      thisx.int = arg1;
      thisx.final = arg2;
      thisx.this = arg3;
   }

   public Tuple4b(byte[] arg0) {
      thisx.null = arg0[0];
      thisx.int = arg0[1];
      thisx.final = arg0[2];
      thisx.this = arg0[3];
   }

   public Tuple4b(Tuple4b arg0) {
      thisx.null = arg0.null;
      thisx.int = arg0.int;
      thisx.final = arg0.final;
      thisx.this = arg0.this;
   }

   public Tuple4b() {
      thisx.null = 0;
      thisx.int = 0;
      thisx.final = 0;
      thisx.this = 0;
   }

   public String toString() {
      return GestureStore.null("X") + (thisx.null & 255) + GuiDispenser.null("7\u0015") + (thisx.int & 255) + GestureStore.null("dP") + (thisx.final & 255) + GuiDispenser.null("7\u0015") + (thisx.this & 255) + GestureStore.null("Y");
   }

   public final void class(byte[] arg0) {
      arg0[0] = thisx.null;
      arg0[1] = thisx.int;
      arg0[2] = thisx.final;
      arg0[3] = thisx.this;
   }

   public final void class(Tuple4b arg0) {
      arg0.null = thisx.null;
      arg0.int = thisx.int;
      arg0.final = thisx.final;
      arg0.this = thisx.this;
   }

   public final void null(Tuple4b arg0) {
      thisx.null = arg0.null;
      thisx.int = arg0.int;
      thisx.final = arg0.final;
      thisx.this = arg0.this;
   }

   public final void null(byte[] arg0) {
      thisx.null = arg0[0];
      thisx.int = arg0[1];
      thisx.final = arg0[2];
      thisx.this = arg0[3];
   }

   public boolean null(Tuple4b arg0) {
      try {
         return thisx.null == arg0.null && thisx.int == arg0.int && thisx.final == arg0.final && thisx.this == arg0.this;
      } catch (NullPointerException var3) {
         return false;
      }
   }

   public boolean equals(Object arg0) {
      try {
         Tuple4b var2 = (Tuple4b)arg0;
         return thisx.null == var2.null && thisx.int == var2.int && thisx.final == var2.final && thisx.this == var2.this;
      } catch (NullPointerException var3) {
         return false;
      } catch (ClassCastException var4) {
         return false;
      }
   }

   public int hashCode() {
      return (thisx.null & 255) << 0 | (thisx.int & 255) << 8 | (thisx.final & 255) << 16 | (thisx.this & 255) << 24;
   }

   public Object clone() {
      try {
         return super.clone();
      } catch (CloneNotSupportedException var2) {
         throw new InternalError();
      }
   }

   public final byte const() {
      return thisx.null;
   }

   public final void const(byte arg0) {
      thisx.null = arg0;
   }

   public final byte long() {
      return thisx.int;
   }

   public final void long(byte arg0) {
      thisx.int = arg0;
   }

   public final byte class() {
      return thisx.final;
   }

   public final void class(byte arg0) {
      thisx.final = arg0;
   }

   public final byte null() {
      return thisx.this;
   }

   public final void null(byte arg0) {
      thisx.this = arg0;
   }
}
