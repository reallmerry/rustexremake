package javax.vecmath;

import java.io.Serializable;
import net.daporkchop.fp2.client.ShaderGlStateHelper;
import org.rocksdb.FileOperationInfo;

public class GVector implements Serializable, Cloneable {
   private int int;
   public double[] final;
   public static final long this = 1398850036893875112L;

   public GVector(int arg0) {
      thisx.int = arg0;
      thisx.final = new double[arg0];

      for(int var2 = 0; var2 < arg0; ++var2) {
         thisx.final[var2] = 0.0D;
      }

   }

   public GVector(double[] arg0) {
      thisx.int = arg0.length;
      thisx.final = new double[arg0.length];

      for(int var2 = 0; var2 < thisx.int; ++var2) {
         thisx.final[var2] = arg0[var2];
      }

   }

   public GVector(GVector arg0) {
      thisx.final = new double[arg0.int];
      thisx.int = arg0.int;

      for(int var2 = 0; var2 < thisx.int; ++var2) {
         thisx.final[var2] = arg0.final[var2];
      }

   }

   public GVector(Tuple2f arg0) {
      thisx.final = new double[2];
      thisx.final[0] = (double)arg0.final;
      thisx.final[1] = (double)arg0.this;
      thisx.int = 2;
   }

   public GVector(Tuple3f arg0) {
      thisx.final = new double[3];
      thisx.final[0] = (double)arg0.int;
      thisx.final[1] = (double)arg0.final;
      thisx.final[2] = (double)arg0.this;
      thisx.int = 3;
   }

   public GVector(Tuple3d arg0) {
      thisx.final = new double[3];
      thisx.final[0] = arg0.int;
      thisx.final[1] = arg0.final;
      thisx.final[2] = arg0.this;
      thisx.int = 3;
   }

   public GVector(Tuple4f arg0) {
      thisx.final = new double[4];
      thisx.final[0] = (double)arg0.null;
      thisx.final[1] = (double)arg0.int;
      thisx.final[2] = (double)arg0.final;
      thisx.final[3] = (double)arg0.this;
      thisx.int = 4;
   }

   public GVector(Tuple4d arg0) {
      thisx.final = new double[4];
      thisx.final[0] = arg0.null;
      thisx.final[1] = arg0.int;
      thisx.final[2] = arg0.final;
      thisx.final[3] = arg0.this;
      thisx.int = 4;
   }

   public GVector(double[] arg0, int arg1) {
      thisx.int = arg1;
      thisx.final = new double[arg1];

      for(int var3 = 0; var3 < arg1; ++var3) {
         thisx.final[var3] = arg0[var3];
      }

   }

   public final double class() {
      double var1 = 0.0D;

      for(int var3 = 0; var3 < thisx.int; ++var3) {
         var1 += thisx.final[var3] * thisx.final[var3];
      }

      return Math.sqrt(var1);
   }

   public final double null() {
      double var1 = 0.0D;

      for(int var3 = 0; var3 < thisx.int; ++var3) {
         var1 += thisx.final[var3] * thisx.final[var3];
      }

      return var1;
   }

   public final void const(GVector arg0) {
      double var2 = 0.0D;
      if (thisx.int != arg0.int) {
         throw new MismatchedSizeException(VecMathI18N.null(FileOperationInfo.null("jHH}Yq_.")));
      } else {
         int var4;
         for(var4 = 0; var4 < thisx.int; ++var4) {
            var2 += arg0.final[var4] * arg0.final[var4];
         }

         double var5 = 1.0D / Math.sqrt(var2);

         for(var4 = 0; var4 < thisx.int; ++var4) {
            thisx.final[var4] = arg0.final[var4] * var5;
         }

      }
   }

   public final void long() {
      double var1 = 0.0D;

      int var3;
      for(var3 = 0; var3 < thisx.int; ++var3) {
         var1 += thisx.final[var3] * thisx.final[var3];
      }

      double var4 = 1.0D / Math.sqrt(var1);

      for(var3 = 0; var3 < thisx.int; ++var3) {
         thisx.final[var3] *= var4;
      }

   }

   public final void null(double arg0, GVector arg1) {
      if (thisx.int != arg1.int) {
         throw new MismatchedSizeException(VecMathI18N.null(ShaderGlStateHelper.null("\u0004\u001e&+7'1y")));
      } else {
         for(int var4 = 0; var4 < thisx.int; ++var4) {
            thisx.final[var4] = arg1.final[var4] * arg0;
         }

      }
   }

   public final void null(double arg0) {
      for(int var3 = 0; var3 < thisx.int; ++var3) {
         thisx.final[var3] *= arg0;
      }

   }

   public final void null(double arg0, GVector arg1, GVector arg2) {
      if (arg2.int != arg1.int) {
         throw new MismatchedSizeException(VecMathI18N.null(FileOperationInfo.null("jHH}Yq_,")));
      } else if (thisx.int != arg1.int) {
         throw new MismatchedSizeException(VecMathI18N.null(ShaderGlStateHelper.null("\u0004\u001e&+7'1{")));
      } else {
         for(int var5 = 0; var5 < thisx.int; ++var5) {
            thisx.final[var5] = arg1.final[var5] * arg0 + arg2.final[var5];
         }

      }
   }

   public final void long(GVector arg0) {
      if (thisx.int != arg0.int) {
         throw new MismatchedSizeException(VecMathI18N.null(FileOperationInfo.null("jHH}Yq_*")));
      } else {
         for(int var2 = 0; var2 < thisx.int; ++var2) {
            double[] var10000 = thisx.final;
            var10000[var2] += arg0.final[var2];
         }

      }
   }

   public final void class(GVector arg0, GVector arg1) {
      if (arg0.int != arg1.int) {
         throw new MismatchedSizeException(VecMathI18N.null(ShaderGlStateHelper.null("\u0004\u001e&+7'1}")));
      } else if (thisx.int != arg0.int) {
         throw new MismatchedSizeException(VecMathI18N.null(FileOperationInfo.null("jHH}Yq_(")));
      } else {
         for(int var3 = 0; var3 < thisx.int; ++var3) {
            thisx.final[var3] = arg0.final[var3] + arg1.final[var3];
         }

      }
   }

   public final void class(GVector arg0) {
      if (thisx.int != arg0.int) {
         throw new MismatchedSizeException(VecMathI18N.null(ShaderGlStateHelper.null("\u0004\u001e&+7'1\u007f")));
      } else {
         for(int var2 = 0; var2 < thisx.int; ++var2) {
            double[] var10000 = thisx.final;
            var10000[var2] -= arg0.final[var2];
         }

      }
   }

   public final void null(GVector arg0, GVector arg1) {
      if (arg0.int != arg1.int) {
         throw new MismatchedSizeException(VecMathI18N.null(FileOperationInfo.null("jHH}Yq_&")));
      } else if (thisx.int != arg0.int) {
         throw new MismatchedSizeException(VecMathI18N.null(ShaderGlStateHelper.null("\u0004\u001e&+7'1q")));
      } else {
         for(int var3 = 0; var3 < thisx.int; ++var3) {
            thisx.final[var3] = arg0.final[var3] - arg1.final[var3];
         }

      }
   }

   public final void null(GMatrix arg0, GVector arg1) {
      if (arg0.null() != arg1.int) {
         throw new MismatchedSizeException(VecMathI18N.null(FileOperationInfo.null("Y{{NjBl\u001c.")));
      } else if (thisx.int != arg0.class()) {
         throw new MismatchedSizeException(VecMathI18N.null(ShaderGlStateHelper.null("\u000f\u0015- <,:ry")));
      } else {
         double[] var3;
         if (arg1 != thisx) {
            var3 = arg1.final;
         } else {
            var3 = (double[])((double[])thisx.final.clone());
         }

         for(int var4 = thisx.int - 1; var4 >= 0; --var4) {
            thisx.final[var4] = 0.0D;

            for(int var5 = arg1.int - 1; var5 >= 0; --var5) {
               double[] var10000 = thisx.final;
               var10000[var4] += arg0.final[var4][var5] * var3[var5];
            }
         }

      }
   }

   public final void null(GVector arg0, GMatrix arg1) {
      if (arg1.class() != arg0.int) {
         throw new MismatchedSizeException(VecMathI18N.null(FileOperationInfo.null("Y{{NjBl\u001c,")));
      } else if (thisx.int != arg1.null()) {
         throw new MismatchedSizeException(VecMathI18N.null(ShaderGlStateHelper.null("\u000f\u0015- <,:r{")));
      } else {
         double[] var3;
         if (arg0 != thisx) {
            var3 = arg0.final;
         } else {
            var3 = (double[])((double[])thisx.final.clone());
         }

         for(int var4 = thisx.int - 1; var4 >= 0; --var4) {
            thisx.final[var4] = 0.0D;

            for(int var5 = arg0.int - 1; var5 >= 0; --var5) {
               double[] var10000 = thisx.final;
               var10000[var4] += arg1.final[var5][var4] * var3[var5];
            }
         }

      }
   }

   public final void class() {
      for(int var1 = thisx.int - 1; var1 >= 0; --var1) {
         double[] var10000 = thisx.final;
         var10000[var1] *= -1.0D;
      }

   }

   public final void null() {
      for(int var1 = 0; var1 < thisx.int; ++var1) {
         thisx.final[var1] = 0.0D;
      }

   }

   public final void null(int arg0) {
      double[] var2 = new double[arg0];
      int var4;
      if (thisx.int < arg0) {
         var4 = thisx.int;
      } else {
         var4 = arg0;
      }

      for(int var3 = 0; var3 < var4; ++var3) {
         var2[var3] = thisx.final[var3];
      }

      thisx.int = arg0;
      thisx.final = var2;
   }

   public final void null(double[] arg0) {
      for(int var2 = thisx.int - 1; var2 >= 0; --var2) {
         thisx.final[var2] = arg0[var2];
      }

   }

   public final void null(GVector arg0) {
      int var2;
      if (thisx.int < arg0.int) {
         thisx.int = arg0.int;
         thisx.final = new double[thisx.int];

         for(var2 = 0; var2 < thisx.int; ++var2) {
            thisx.final[var2] = arg0.final[var2];
         }
      } else {
         for(var2 = 0; var2 < arg0.int; ++var2) {
            thisx.final[var2] = arg0.final[var2];
         }

         for(var2 = arg0.int; var2 < thisx.int; ++var2) {
            thisx.final[var2] = 0.0D;
         }
      }

   }

   public final void null(Tuple2f arg0) {
      if (thisx.int < 2) {
         thisx.int = 2;
         thisx.final = new double[2];
      }

      thisx.final[0] = (double)arg0.final;
      thisx.final[1] = (double)arg0.this;

      for(int var2 = 2; var2 < thisx.int; ++var2) {
         thisx.final[var2] = 0.0D;
      }

   }

   public final void null(Tuple3f arg0) {
      if (thisx.int < 3) {
         thisx.int = 3;
         thisx.final = new double[3];
      }

      thisx.final[0] = (double)arg0.int;
      thisx.final[1] = (double)arg0.final;
      thisx.final[2] = (double)arg0.this;

      for(int var2 = 3; var2 < thisx.int; ++var2) {
         thisx.final[var2] = 0.0D;
      }

   }

   public final void null(Tuple3d arg0) {
      if (thisx.int < 3) {
         thisx.int = 3;
         thisx.final = new double[3];
      }

      thisx.final[0] = arg0.int;
      thisx.final[1] = arg0.final;
      thisx.final[2] = arg0.this;

      for(int var2 = 3; var2 < thisx.int; ++var2) {
         thisx.final[var2] = 0.0D;
      }

   }

   public final void null(Tuple4f arg0) {
      if (thisx.int < 4) {
         thisx.int = 4;
         thisx.final = new double[4];
      }

      thisx.final[0] = (double)arg0.null;
      thisx.final[1] = (double)arg0.int;
      thisx.final[2] = (double)arg0.final;
      thisx.final[3] = (double)arg0.this;

      for(int var2 = 4; var2 < thisx.int; ++var2) {
         thisx.final[var2] = 0.0D;
      }

   }

   public final void null(Tuple4d arg0) {
      if (thisx.int < 4) {
         thisx.int = 4;
         thisx.final = new double[4];
      }

      thisx.final[0] = arg0.null;
      thisx.final[1] = arg0.int;
      thisx.final[2] = arg0.final;
      thisx.final[3] = arg0.this;

      for(int var2 = 4; var2 < thisx.int; ++var2) {
         thisx.final[var2] = 0.0D;
      }

   }

   public final int null() {
      return thisx.final.length;
   }

   public final double null(int arg0) {
      return thisx.final[arg0];
   }

   public final void null(int arg0, double arg1) {
      thisx.final[arg0] = arg1;
   }

   public String toString() {
      StringBuffer var1 = new StringBuffer(thisx.int * 8);

      for(int var2 = 0; var2 < thisx.int; ++var2) {
         var1.append(thisx.final[var2]).append(" ");
      }

      return var1.toString();
   }

   public int hashCode() {
      long var1 = 1L;

      for(int var3 = 0; var3 < thisx.int; ++var3) {
         var1 = VecMathUtil.null(var1, thisx.final[var3]);
      }

      return VecMathUtil.null(var1);
   }

   public boolean null(GVector arg0) {
      try {
         if (thisx.int != arg0.int) {
            return false;
         } else {
            for(int var2 = 0; var2 < thisx.int; ++var2) {
               if (thisx.final[var2] != arg0.final[var2]) {
                  return false;
               }
            }

            return true;
         }
      } catch (NullPointerException var3) {
         return false;
      }
   }

   public boolean equals(Object arg0) {
      try {
         GVector var2 = (GVector)arg0;
         if (thisx.int != var2.int) {
            return false;
         } else {
            for(int var3 = 0; var3 < thisx.int; ++var3) {
               if (thisx.final[var3] != var2.final[var3]) {
                  return false;
               }
            }

            return true;
         }
      } catch (ClassCastException var4) {
         return false;
      } catch (NullPointerException var5) {
         return false;
      }
   }

   public boolean null(GVector arg0, double arg1) {
      if (thisx.int != arg0.int) {
         return false;
      } else {
         for(int var6 = 0; var6 < thisx.int; ++var6) {
            double var4 = thisx.final[var6] - arg0.final[var6];
            if ((var4 < 0.0D ? -var4 : var4) > arg1) {
               return false;
            }
         }

         return true;
      }
   }

   public final double class(GVector arg0) {
      if (thisx.int != arg0.int) {
         throw new MismatchedSizeException(VecMathI18N.null(FileOperationInfo.null("Y{{NjBl\u001c*")));
      } else {
         double var2 = 0.0D;

         for(int var4 = 0; var4 < thisx.int; ++var4) {
            var2 += thisx.final[var4] * arg0.final[var4];
         }

         return var2;
      }
   }

   public final void null(GMatrix arg0, GMatrix arg1, GMatrix arg2, GVector arg3) {
      if (arg0.null == arg3.null() && arg0.null == arg0.int && arg0.null == arg1.null) {
         if (arg1.int == thisx.final.length && arg1.int == arg2.int && arg1.int == arg2.null) {
            GMatrix var5 = new GMatrix(arg0.null, arg1.int);
            var5.do(arg0, arg2);
            var5.class(arg0, arg1);
            var5.class();
            thisx.null(var5, arg3);
         } else {
            throw new MismatchedSizeException(VecMathI18N.null(FileOperationInfo.null("Y{{NjBl\u001f-")));
         }
      } else {
         throw new MismatchedSizeException(VecMathI18N.null(ShaderGlStateHelper.null("\u000f\u0015- <,:r}")));
      }
   }

   public final void null(GMatrix arg0, GVector arg1, GVector arg2) {
      int var4 = arg0.null * arg0.int;
      double[] var5 = new double[var4];
      double[] var6 = new double[var4];
      int[] var7 = new int[arg1.null()];
      if (arg0.null != arg1.null()) {
         throw new MismatchedSizeException(VecMathI18N.null(ShaderGlStateHelper.null("\u000f\u0015- <,:r~")));
      } else if (arg0.null != arg2.null()) {
         throw new MismatchedSizeException(VecMathI18N.null(FileOperationInfo.null("Y{{NjBl\u001f*")));
      } else if (arg0.null != arg0.int) {
         throw new MismatchedSizeException(VecMathI18N.null(ShaderGlStateHelper.null("\u000f\u0015- <,:q}")));
      } else {
         int var8;
         for(var8 = 0; var8 < arg0.null; ++var8) {
            for(int var9 = 0; var9 < arg0.int; ++var9) {
               var5[var8 * arg0.int + var9] = arg0.final[var8][var9];
            }
         }

         for(var8 = 0; var8 < var4; ++var8) {
            var6[var8] = 0.0D;
         }

         for(var8 = 0; var8 < arg0.null; ++var8) {
            var6[var8 * arg0.int] = arg1.final[var8];
         }

         for(var8 = 0; var8 < arg0.int; ++var8) {
            var7[var8] = (int)arg2.final[var8];
         }

         GMatrix.null(arg0.null, var5, var7, var6);

         for(var8 = 0; var8 < arg0.null; ++var8) {
            thisx.final[var8] = var6[var8 * arg0.int];
         }

      }
   }

   public final double null(GVector arg0) {
      return Math.acos(thisx.class(arg0) / (thisx.class() * arg0.class()));
   }

   /** @deprecated */
   public final void null(GVector arg0, GVector arg1, float arg2) {
      thisx.null(arg0, arg1, (double)arg2);
   }

   /** @deprecated */
   public final void null(GVector arg0, float arg1) {
      thisx.null(arg0, (double)arg1);
   }

   public final void null(GVector arg0, GVector arg1, double arg2) {
      if (arg1.int != arg0.int) {
         throw new MismatchedSizeException(VecMathI18N.null(FileOperationInfo.null("Y{{NjBl\u001f.")));
      } else if (thisx.int != arg0.int) {
         throw new MismatchedSizeException(VecMathI18N.null(ShaderGlStateHelper.null("\u000f\u0015- <,:qy")));
      } else {
         for(int var5 = 0; var5 < thisx.int; ++var5) {
            thisx.final[var5] = (1.0D - arg2) * arg0.final[var5] + arg2 * arg1.final[var5];
         }

      }
   }

   public final void null(GVector arg0, double arg1) {
      if (arg0.int != thisx.int) {
         throw new MismatchedSizeException(VecMathI18N.null(FileOperationInfo.null("Y{{NjBl\u001f,")));
      } else {
         for(int var4 = 0; var4 < thisx.int; ++var4) {
            thisx.final[var4] = (1.0D - arg1) * thisx.final[var4] + arg1 * arg0.final[var4];
         }

      }
   }

   public Object clone() {
      GVector var1 = null;

      try {
         var1 = (GVector)super.clone();
      } catch (CloneNotSupportedException var3) {
         throw new InternalError();
      }

      var1.final = new double[thisx.int];

      for(int var2 = 0; var2 < thisx.int; ++var2) {
         var1.final[var2] = thisx.final[var2];
      }

      return var1;
   }
}
