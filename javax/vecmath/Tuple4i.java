package javax.vecmath;

import android.location.GpsSatellite;
import java.io.Serializable;
import net.minecraft.client.resources.IResource;

public abstract class Tuple4i implements Serializable, Cloneable {
   public static final long false = 8064614250942616720L;
   public int null;
   public int int;
   public int final;
   public int this;

   public Tuple4i(int arg0, int arg1, int arg2, int arg3) {
      thisx.null = arg0;
      thisx.int = arg1;
      thisx.final = arg2;
      thisx.this = arg3;
   }

   public Tuple4i(int[] arg0) {
      thisx.null = arg0[0];
      thisx.int = arg0[1];
      thisx.final = arg0[2];
      thisx.this = arg0[3];
   }

   public Tuple4i(Tuple4i arg0) {
      thisx.null = arg0.null;
      thisx.int = arg0.int;
      thisx.final = arg0.final;
      thisx.this = arg0.this;
   }

   public Tuple4i() {
      thisx.null = 0;
      thisx.int = 0;
      thisx.final = 0;
      thisx.this = 0;
   }

   public final void null(int arg0, int arg1, int arg2, int arg3) {
      thisx.null = arg0;
      thisx.int = arg1;
      thisx.final = arg2;
      thisx.this = arg3;
   }

   public final void class(int[] arg0) {
      thisx.null = arg0[0];
      thisx.int = arg0[1];
      thisx.final = arg0[2];
      thisx.this = arg0[3];
   }

   public final void do(Tuple4i arg0) {
      thisx.null = arg0.null;
      thisx.int = arg0.int;
      thisx.final = arg0.final;
      thisx.this = arg0.this;
   }

   public final void null(int[] arg0) {
      arg0[0] = thisx.null;
      arg0[1] = thisx.int;
      arg0[2] = thisx.final;
      arg0[3] = thisx.this;
   }

   public final void true(Tuple4i arg0) {
      arg0.null = thisx.null;
      arg0.int = thisx.int;
      arg0.final = thisx.final;
      arg0.this = thisx.this;
   }

   public final void class(Tuple4i arg0, Tuple4i arg1) {
      thisx.null = arg0.null + arg1.null;
      thisx.int = arg0.int + arg1.int;
      thisx.final = arg0.final + arg1.final;
      thisx.this = arg0.this + arg1.this;
   }

   public final void const(Tuple4i arg0) {
      thisx.null += arg0.null;
      thisx.int += arg0.int;
      thisx.final += arg0.final;
      thisx.this += arg0.this;
   }

   public final void null(Tuple4i arg0, Tuple4i arg1) {
      thisx.null = arg0.null - arg1.null;
      thisx.int = arg0.int - arg1.int;
      thisx.final = arg0.final - arg1.final;
      thisx.this = arg0.this - arg1.this;
   }

   public final void long(Tuple4i arg0) {
      thisx.null -= arg0.null;
      thisx.int -= arg0.int;
      thisx.final -= arg0.final;
      thisx.this -= arg0.this;
   }

   public final void class(Tuple4i arg0) {
      thisx.null = -arg0.null;
      thisx.int = -arg0.int;
      thisx.final = -arg0.final;
      thisx.this = -arg0.this;
   }

   public final void class() {
      thisx.null = -thisx.null;
      thisx.int = -thisx.int;
      thisx.final = -thisx.final;
      thisx.this = -thisx.this;
   }

   public final void const(int arg0, Tuple4i arg1) {
      thisx.null = arg0 * arg1.null;
      thisx.int = arg0 * arg1.int;
      thisx.final = arg0 * arg1.final;
      thisx.this = arg0 * arg1.this;
   }

   public final void short(int arg0) {
      thisx.null *= arg0;
      thisx.int *= arg0;
      thisx.final *= arg0;
      thisx.this *= arg0;
   }

   public final void null(int arg0, Tuple4i arg1, Tuple4i arg2) {
      thisx.null = arg0 * arg1.null + arg2.null;
      thisx.int = arg0 * arg1.int + arg2.int;
      thisx.final = arg0 * arg1.final + arg2.final;
      thisx.this = arg0 * arg1.this + arg2.this;
   }

   public final void long(int arg0, Tuple4i arg1) {
      thisx.null = arg0 * thisx.null + arg1.null;
      thisx.int = arg0 * thisx.int + arg1.int;
      thisx.final = arg0 * thisx.final + arg1.final;
      thisx.this = arg0 * thisx.this + arg1.this;
   }

   public String toString() {
      return GpsSatellite.null(",") + thisx.null + IResource.null("'\u0019") + thisx.int + GpsSatellite.null("_$") + thisx.final + IResource.null("'\u0019") + thisx.this + GpsSatellite.null("-");
   }

   public boolean equals(Object arg0) {
      try {
         Tuple4i var2 = (Tuple4i)arg0;
         return thisx.null == var2.null && thisx.int == var2.int && thisx.final == var2.final && thisx.this == var2.this;
      } catch (NullPointerException var3) {
         return false;
      } catch (ClassCastException var4) {
         return false;
      }
   }

   public int hashCode() {
      long var1 = 1L;
      var1 = 31L * var1 + (long)thisx.null;
      var1 = 31L * var1 + (long)thisx.int;
      var1 = 31L * var1 + (long)thisx.final;
      var1 = 31L * var1 + (long)thisx.this;
      return (int)(var1 ^ var1 >> 32);
   }

   public final void null(int arg0, int arg1, Tuple4i arg2) {
      if (arg2.null > arg1) {
         thisx.null = arg1;
      } else if (arg2.null < arg0) {
         thisx.null = arg0;
      } else {
         thisx.null = arg2.null;
      }

      if (arg2.int > arg1) {
         thisx.int = arg1;
      } else if (arg2.int < arg0) {
         thisx.int = arg0;
      } else {
         thisx.int = arg2.int;
      }

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

   public final void class(int arg0, Tuple4i arg1) {
      if (arg1.null < arg0) {
         thisx.null = arg0;
      } else {
         thisx.null = arg1.null;
      }

      if (arg1.int < arg0) {
         thisx.int = arg0;
      } else {
         thisx.int = arg1.int;
      }

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

   public final void null(int arg0, Tuple4i arg1) {
      if (arg1.null > arg0) {
         thisx.null = arg0;
      } else {
         thisx.null = arg1.null;
      }

      if (arg1.int > arg0) {
         thisx.int = arg0;
      } else {
         thisx.int = arg1.int;
      }

      if (arg1.final > arg0) {
         thisx.final = arg0;
      } else {
         thisx.final = arg1.final;
      }

      if (arg1.this > arg0) {
         thisx.this = arg0;
      } else {
         thisx.this = arg1.final;
      }

   }

   public final void null(Tuple4i arg0) {
      thisx.null = Math.abs(arg0.null);
      thisx.int = Math.abs(arg0.int);
      thisx.final = Math.abs(arg0.final);
      thisx.this = Math.abs(arg0.this);
   }

   public final void null(int arg0, int arg1) {
      if (thisx.null > arg1) {
         thisx.null = arg1;
      } else if (thisx.null < arg0) {
         thisx.null = arg0;
      }

      if (thisx.int > arg1) {
         thisx.int = arg1;
      } else if (thisx.int < arg0) {
         thisx.int = arg0;
      }

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

   public final void do(int arg0) {
      if (thisx.null < arg0) {
         thisx.null = arg0;
      }

      if (thisx.int < arg0) {
         thisx.int = arg0;
      }

      if (thisx.final < arg0) {
         thisx.final = arg0;
      }

      if (thisx.this < arg0) {
         thisx.this = arg0;
      }

   }

   public final void true(int arg0) {
      if (thisx.null > arg0) {
         thisx.null = arg0;
      }

      if (thisx.int > arg0) {
         thisx.int = arg0;
      }

      if (thisx.final > arg0) {
         thisx.final = arg0;
      }

      if (thisx.this > arg0) {
         thisx.this = arg0;
      }

   }

   public final void null() {
      thisx.null = Math.abs(thisx.null);
      thisx.int = Math.abs(thisx.int);
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

   public final int const() {
      return thisx.null;
   }

   public final void const(int arg0) {
      thisx.null = arg0;
   }

   public final int long() {
      return thisx.int;
   }

   public final void long(int arg0) {
      thisx.int = arg0;
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
      int var10000 = (3 ^ 5) << 4 ^ 3 << 2 ^ 1;
      int var10001 = (3 ^ 5) << 4 ^ (3 ^ 5) << 1;
      int var10002 = 1 << 3 ^ 1;
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
