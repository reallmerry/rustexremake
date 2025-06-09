package javax.vecmath;

import java.io.Serializable;
import net.daporkchop.lib.math.vector.i.Vec3i;
import net.minecraft.client.audio.MovingSoundMinecart;

public abstract class Tuple3b implements Serializable, Cloneable {
   public static final long null = -483782685323607044L;
   public byte int;
   public byte final;
   public byte this;

   public Tuple3b(byte arg0, byte arg1, byte arg2) {
      thisx.int = arg0;
      thisx.final = arg1;
      thisx.this = arg2;
   }

   public Tuple3b(byte[] arg0) {
      thisx.int = arg0[0];
      thisx.final = arg0[1];
      thisx.this = arg0[2];
   }

   public Tuple3b(Tuple3b arg0) {
      thisx.int = arg0.int;
      thisx.final = arg0.final;
      thisx.this = arg0.this;
   }

   public Tuple3b() {
      thisx.int = 0;
      thisx.final = 0;
      thisx.this = 0;
   }

   public String toString() {
      return Vec3i.null("O") + (thisx.int & 255) + MovingSoundMinecart.null(";~") + (thisx.final & 255) + Vec3i.null("!G") + (thisx.this & 255) + MovingSoundMinecart.null("w");
   }

   public final void class(byte[] arg0) {
      arg0[0] = thisx.int;
      arg0[1] = thisx.final;
      arg0[2] = thisx.this;
   }

   public final void class(Tuple3b arg0) {
      arg0.int = thisx.int;
      arg0.final = thisx.final;
      arg0.this = thisx.this;
   }

   public final void null(Tuple3b arg0) {
      thisx.int = arg0.int;
      thisx.final = arg0.final;
      thisx.this = arg0.this;
   }

   public final void null(byte[] arg0) {
      thisx.int = arg0[0];
      thisx.final = arg0[1];
      thisx.this = arg0[2];
   }

   public boolean null(Tuple3b arg0) {
      try {
         return thisx.int == arg0.int && thisx.final == arg0.final && thisx.this == arg0.this;
      } catch (NullPointerException var3) {
         return false;
      }
   }

   public boolean equals(Object arg0) {
      try {
         Tuple3b var2 = (Tuple3b)arg0;
         return thisx.int == var2.int && thisx.final == var2.final && thisx.this == var2.this;
      } catch (NullPointerException var3) {
         return false;
      } catch (ClassCastException var4) {
         return false;
      }
   }

   public int hashCode() {
      return (thisx.int & 255) << 0 | (thisx.final & 255) << 8 | (thisx.this & 255) << 16;
   }

   public Object clone() {
      try {
         return super.clone();
      } catch (CloneNotSupportedException var2) {
         throw new InternalError();
      }
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
