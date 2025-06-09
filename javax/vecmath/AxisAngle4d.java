package javax.vecmath;

import java.io.Serializable;
import net.minecraft.command.CommandCompare;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;

public class AxisAngle4d implements Serializable, Cloneable {
   public static final long const = 3644296204459140589L;
   public double false;
   public double null;
   public double int;
   public double final;
   public static final double this = 1.0E-12D;

   public AxisAngle4d(double arg0, double arg1, double arg2, double arg3) {
      thisx.false = arg0;
      thisx.null = arg1;
      thisx.int = arg2;
      thisx.final = arg3;
   }

   public AxisAngle4d(double[] arg0) {
      thisx.false = arg0[0];
      thisx.null = arg0[1];
      thisx.int = arg0[2];
      thisx.final = arg0[3];
   }

   public AxisAngle4d(AxisAngle4d arg0) {
      thisx.false = arg0.false;
      thisx.null = arg0.null;
      thisx.int = arg0.int;
      thisx.final = arg0.final;
   }

   public AxisAngle4d(AxisAngle4f arg0) {
      thisx.false = (double)arg0.false;
      thisx.null = (double)arg0.null;
      thisx.int = (double)arg0.int;
      thisx.final = (double)arg0.final;
   }

   public AxisAngle4d(Vector3d arg0, double arg1) {
      thisx.false = arg0.int;
      thisx.null = arg0.final;
      thisx.int = arg0.this;
      thisx.final = arg1;
   }

   public AxisAngle4d() {
      thisx.false = 0.0D;
      thisx.null = 0.0D;
      thisx.int = 1.0D;
      thisx.final = 0.0D;
   }

   public final void null(double arg0, double arg1, double arg2, double arg3) {
      thisx.false = arg0;
      thisx.null = arg1;
      thisx.int = arg2;
      thisx.final = arg3;
   }

   public final void class(double[] arg0) {
      thisx.false = arg0[0];
      thisx.null = arg0[1];
      thisx.int = arg0[2];
      thisx.final = arg0[3];
   }

   public final void null(AxisAngle4d arg0) {
      thisx.false = arg0.false;
      thisx.null = arg0.null;
      thisx.int = arg0.int;
      thisx.final = arg0.final;
   }

   public final void null(AxisAngle4f arg0) {
      thisx.false = (double)arg0.false;
      thisx.null = (double)arg0.null;
      thisx.int = (double)arg0.int;
      thisx.final = (double)arg0.final;
   }

   public final void null(Vector3d arg0, double arg1) {
      thisx.false = arg0.int;
      thisx.null = arg0.final;
      thisx.int = arg0.this;
      thisx.final = arg1;
   }

   public final void null(double[] arg0) {
      arg0[0] = thisx.false;
      arg0[1] = thisx.null;
      arg0[2] = thisx.int;
      arg0[3] = thisx.final;
   }

   public final void null(Matrix4f arg0) {
      Matrix3d var2 = new Matrix3d();
      arg0.long(var2);
      thisx.false = (double)((float)(var2.int - var2.false));
      thisx.null = (double)((float)(var2.long - var2.null));
      thisx.int = (double)((float)(var2.new - var2.short));
      double var3 = thisx.false * thisx.false + thisx.null * thisx.null + thisx.int * thisx.int;
      if (var3 > 1.0E-12D) {
         var3 = Math.sqrt(var3);
         double var5 = 0.5D * var3;
         double var7 = 0.5D * (var2.case + var2.const + var2.final - 1.0D);
         thisx.final = (double)((float)Math.atan2(var5, var7));
         double var9 = 1.0D / var3;
         thisx.false *= var9;
         thisx.null *= var9;
         thisx.int *= var9;
      } else {
         thisx.false = 0.0D;
         thisx.null = 1.0D;
         thisx.int = 0.0D;
         thisx.final = 0.0D;
      }

   }

   public final void null(Matrix4d arg0) {
      Matrix3d var2 = new Matrix3d();
      arg0.true(var2);
      thisx.false = (double)((float)(var2.int - var2.false));
      thisx.null = (double)((float)(var2.long - var2.null));
      thisx.int = (double)((float)(var2.new - var2.short));
      double var3 = thisx.false * thisx.false + thisx.null * thisx.null + thisx.int * thisx.int;
      if (var3 > 1.0E-12D) {
         var3 = Math.sqrt(var3);
         double var5 = 0.5D * var3;
         double var7 = 0.5D * (var2.case + var2.const + var2.final - 1.0D);
         thisx.final = (double)((float)Math.atan2(var5, var7));
         double var9 = 1.0D / var3;
         thisx.false *= var9;
         thisx.null *= var9;
         thisx.int *= var9;
      } else {
         thisx.false = 0.0D;
         thisx.null = 1.0D;
         thisx.int = 0.0D;
         thisx.final = 0.0D;
      }

   }

   public final void null(Matrix3f arg0) {
      thisx.false = (double)(arg0.int - arg0.false);
      thisx.null = (double)(arg0.long - arg0.null);
      thisx.int = (double)(arg0.new - arg0.short);
      double var2 = thisx.false * thisx.false + thisx.null * thisx.null + thisx.int * thisx.int;
      if (var2 > 1.0E-12D) {
         var2 = Math.sqrt(var2);
         double var4 = 0.5D * var2;
         double var6 = 0.5D * ((double)(arg0.case + arg0.const + arg0.final) - 1.0D);
         thisx.final = (double)((float)Math.atan2(var4, var6));
         double var8 = 1.0D / var2;
         thisx.false *= var8;
         thisx.null *= var8;
         thisx.int *= var8;
      } else {
         thisx.false = 0.0D;
         thisx.null = 1.0D;
         thisx.int = 0.0D;
         thisx.final = 0.0D;
      }

   }

   public final void null(Matrix3d arg0) {
      thisx.false = (double)((float)(arg0.int - arg0.false));
      thisx.null = (double)((float)(arg0.long - arg0.null));
      thisx.int = (double)((float)(arg0.new - arg0.short));
      double var2 = thisx.false * thisx.false + thisx.null * thisx.null + thisx.int * thisx.int;
      if (var2 > 1.0E-12D) {
         var2 = Math.sqrt(var2);
         double var4 = 0.5D * var2;
         double var6 = 0.5D * (arg0.case + arg0.const + arg0.final - 1.0D);
         thisx.final = (double)((float)Math.atan2(var4, var6));
         double var8 = 1.0D / var2;
         thisx.false *= var8;
         thisx.null *= var8;
         thisx.int *= var8;
      } else {
         thisx.false = 0.0D;
         thisx.null = 1.0D;
         thisx.int = 0.0D;
         thisx.final = 0.0D;
      }

   }

   public final void null(Quat4f arg0) {
      double var2 = (double)(arg0.null * arg0.null + arg0.int * arg0.int + arg0.final * arg0.final);
      if (var2 > 1.0E-12D) {
         var2 = Math.sqrt(var2);
         double var4 = 1.0D / var2;
         thisx.false = (double)arg0.null * var4;
         thisx.null = (double)arg0.int * var4;
         thisx.int = (double)arg0.final * var4;
         thisx.final = 2.0D * Math.atan2(var2, (double)arg0.this);
      } else {
         thisx.false = 0.0D;
         thisx.null = 1.0D;
         thisx.int = 0.0D;
         thisx.final = 0.0D;
      }

   }

   public final void null(Quat4d arg0) {
      double var2 = arg0.null * arg0.null + arg0.int * arg0.int + arg0.final * arg0.final;
      if (var2 > 1.0E-12D) {
         var2 = Math.sqrt(var2);
         double var4 = 1.0D / var2;
         thisx.false = arg0.null * var4;
         thisx.null = arg0.int * var4;
         thisx.int = arg0.final * var4;
         thisx.final = 2.0D * Math.atan2(var2, arg0.this);
      } else {
         thisx.false = 0.0D;
         thisx.null = 1.0D;
         thisx.int = 0.0D;
         thisx.final = 0.0D;
      }

   }

   public String toString() {
      return CommandCompare.null("\u0012") + thisx.false + CPacketPlayerTryUseItemOnBlock.null(" \\") + thisx.null + CommandCompare.null("\r\u001a") + thisx.int + CPacketPlayerTryUseItemOnBlock.null(" \\") + thisx.final + CommandCompare.null("\u0013");
   }

   public boolean null(AxisAngle4d arg0) {
      try {
         return thisx.false == arg0.false && thisx.null == arg0.null && thisx.int == arg0.int && thisx.final == arg0.final;
      } catch (NullPointerException var3) {
         return false;
      }
   }

   public boolean equals(Object arg0) {
      try {
         AxisAngle4d var2 = (AxisAngle4d)arg0;
         return thisx.false == var2.false && thisx.null == var2.null && thisx.int == var2.int && thisx.final == var2.final;
      } catch (NullPointerException var3) {
         return false;
      } catch (ClassCastException var4) {
         return false;
      }
   }

   public boolean null(AxisAngle4d arg0, double arg1) {
      double var4 = thisx.false - arg0.false;
      if ((var4 < 0.0D ? -var4 : var4) > arg1) {
         return false;
      } else {
         var4 = thisx.null - arg0.null;
         if ((var4 < 0.0D ? -var4 : var4) > arg1) {
            return false;
         } else {
            var4 = thisx.int - arg0.int;
            if ((var4 < 0.0D ? -var4 : var4) > arg1) {
               return false;
            } else {
               var4 = thisx.final - arg0.final;
               return !((var4 < 0.0D ? -var4 : var4) > arg1);
            }
         }
      }
   }

   public int hashCode() {
      long var1 = 1L;
      var1 = VecMathUtil.null(var1, thisx.false);
      var1 = VecMathUtil.null(var1, thisx.null);
      var1 = VecMathUtil.null(var1, thisx.int);
      var1 = VecMathUtil.null(var1, thisx.final);
      return VecMathUtil.null(var1);
   }

   public Object clone() {
      try {
         return super.clone();
      } catch (CloneNotSupportedException var2) {
         throw new InternalError();
      }
   }

   public final double const() {
      return thisx.final;
   }

   public final void const(double arg0) {
      thisx.final = arg0;
   }

   public double long() {
      return thisx.false;
   }

   public final void long(double arg0) {
      thisx.false = arg0;
   }

   public final double class() {
      return thisx.null;
   }

   public final void class(double arg0) {
      thisx.null = arg0;
   }

   public double null() {
      return thisx.int;
   }

   public final void null(double arg0) {
      thisx.int = arg0;
   }

   public static String null(String arg0) {
      int var10000 = 4 << 4 ^ 1 << 1;
      int var10001 = (3 ^ 5) << 4 ^ 2 << 1;
      int var10002 = 4 << 3 ^ 2 ^ 5;
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
