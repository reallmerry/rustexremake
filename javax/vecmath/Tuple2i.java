package javax.vecmath;

import java.io.Serializable;
import net.daporkchop.fp2.mode.api.IFarRenderMode;
import net.optifine.shaders.SMCLog;

public abstract class Tuple2i implements Serializable, Cloneable {
   public static final long int = -3555701650170169638L;
   public int final;
   public int this;

   public Tuple2i(int arg0, int arg1) {
      thisx.final = arg0;
      thisx.this = arg1;
   }

   public Tuple2i(int[] arg0) {
      thisx.final = arg0[0];
      thisx.this = arg0[1];
   }

   public Tuple2i(Tuple2i arg0) {
      thisx.final = arg0.final;
      thisx.this = arg0.this;
   }

   public Tuple2i() {
      thisx.final = 0;
      thisx.this = 0;
   }

   public final void class(int arg0, int arg1) {
      thisx.final = arg0;
      thisx.this = arg1;
   }

   public final void class(int[] arg0) {
      thisx.final = arg0[0];
      thisx.this = arg0[1];
   }

   public final void do(Tuple2i arg0) {
      thisx.final = arg0.final;
      thisx.this = arg0.this;
   }

   public final void null(int[] arg0) {
      arg0[0] = thisx.final;
      arg0[1] = thisx.this;
   }

   public final void true(Tuple2i arg0) {
      arg0.final = thisx.final;
      arg0.this = thisx.this;
   }

   public final void class(Tuple2i arg0, Tuple2i arg1) {
      thisx.final = arg0.final + arg1.final;
      thisx.this = arg0.this + arg1.this;
   }

   public final void const(Tuple2i arg0) {
      thisx.final += arg0.final;
      thisx.this += arg0.this;
   }

   public final void null(Tuple2i arg0, Tuple2i arg1) {
      thisx.final = arg0.final - arg1.final;
      thisx.this = arg0.this - arg1.this;
   }

   public final void long(Tuple2i arg0) {
      thisx.final -= arg0.final;
      thisx.this -= arg0.this;
   }

   public final void class(Tuple2i arg0) {
      thisx.final = -arg0.final;
      thisx.this = -arg0.this;
   }

   public final void class() {
      thisx.final = -thisx.final;
      thisx.this = -thisx.this;
   }

   public final void const(int arg0, Tuple2i arg1) {
      thisx.final = arg0 * arg1.final;
      thisx.this = arg0 * arg1.this;
   }

   public final void true(int arg0) {
      thisx.final *= arg0;
      thisx.this *= arg0;
   }

   public final void null(int arg0, Tuple2i arg1, Tuple2i arg2) {
      thisx.final = arg0 * arg1.final + arg2.final;
      thisx.this = arg0 * arg1.this + arg2.this;
   }

   public final void long(int arg0, Tuple2i arg1) {
      thisx.final = arg0 * thisx.final + arg1.final;
      thisx.this = arg0 * thisx.this + arg1.this;
   }

   public String toString() {
      return IFarRenderMode.null("\u0000") + thisx.final + SMCLog.null(":t") + thisx.this + IFarRenderMode.null("\u0001");
   }

   public boolean equals(Object arg0) {
      try {
         Tuple2i var2 = (Tuple2i)arg0;
         return thisx.final == var2.final && thisx.this == var2.this;
      } catch (NullPointerException var3) {
         return false;
      } catch (ClassCastException var4) {
         return false;
      }
   }

   public int hashCode() {
      long var1 = 1L;
      var1 = 31L * var1 + (long)thisx.final;
      var1 = 31L * var1 + (long)thisx.this;
      return (int)(var1 ^ var1 >> 32);
   }

   public final void null(int arg0, int arg1, Tuple2i arg2) {
      if (arg2.final > arg1) {
         thisx.final = arg1;
      } else if (arg2.final < arg0) {
         thisx.final = arg0;
      } else {
         thisx.final = arg2.final;
      }

      if (arg2.this > arg1) {
         thisx.this = arg1;
      } else if (arg2.this < arg0) {
         thisx.this = arg0;
      } else {
         thisx.this = arg2.this;
      }

   }

   public final void class(int arg0, Tuple2i arg1) {
      if (arg1.final < arg0) {
         thisx.final = arg0;
      } else {
         thisx.final = arg1.final;
      }

      if (arg1.this < arg0) {
         thisx.this = arg0;
      } else {
         thisx.this = arg1.this;
      }

   }

   public final void null(int arg0, Tuple2i arg1) {
      if (arg1.final > arg0) {
         thisx.final = arg0;
      } else {
         thisx.final = arg1.final;
      }

      if (arg1.this > arg0) {
         thisx.this = arg0;
      } else {
         thisx.this = arg1.this;
      }

   }

   public final void null(Tuple2i arg0) {
      thisx.final = Math.abs(arg0.final);
      thisx.this = Math.abs(arg0.this);
   }

   public final void null(int arg0, int arg1) {
      if (thisx.final > arg1) {
         thisx.final = arg1;
      } else if (thisx.final < arg0) {
         thisx.final = arg0;
      }

      if (thisx.this > arg1) {
         thisx.this = arg1;
      } else if (thisx.this < arg0) {
         thisx.this = arg0;
      }

   }

   public final void const(int arg0) {
      if (thisx.final < arg0) {
         thisx.final = arg0;
      }

      if (thisx.this < arg0) {
         thisx.this = arg0;
      }

   }

   public final void long(int arg0) {
      if (thisx.final > arg0) {
         thisx.final = arg0;
      }

      if (thisx.this > arg0) {
         thisx.this = arg0;
      }

   }

   public final void null() {
      thisx.final = Math.abs(thisx.final);
      thisx.this = Math.abs(thisx.this);
   }

   public Object clone() {
      try {
         return super.clone();
      } catch (CloneNotSupportedException var2) {
         throw new InternalError();
      }
   }

   public final int class() {
      return thisx.final;
   }

   public final void class(int arg0) {
      thisx.final = arg0;
   }

   public final int null() {
      return thisx.this;
   }

   public final void null(int arg0) {
      thisx.this = arg0;
   }

   public static String null(String arg0) {
      int var10000 = (2 ^ 5) << 4 ^ 3;
      int var10001 = 4 << 3;
      int var10002 = 3 << 3;
      int var10003 = arg0.length();
      char[] var10004 = new char[var10003];
      boolean var10006 = true;
      int var5 = var10003 - 1;
      var10003 = var10002;
      int var3;
      var10002 = var3 = var5;
      char[] var1 = var10004;
      int var4 = var10003;
      var10000 = var10002;

      for(int var2 = var10001; var10000 >= 0; var10000 = var3) {
         var10001 = var3;
         char var6 = arg0.charAt(var3);
         --var3;
         var1[var10001] = (char)(var6 ^ var2);
         if (var3 < 0) {
            break;
         }

         var10002 = var3--;
         var1[var10002] = (char)(arg0.charAt(var10002) ^ var4);
      }

      return new String(var1);
   }
}
