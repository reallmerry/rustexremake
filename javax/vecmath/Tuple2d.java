package javax.vecmath;

import com.sk89q.worldedit.regions.RegionIntersection;
import java.io.Serializable;
import net.minecraftforge.server.command.ForgeCommand;

public abstract class Tuple2d implements Serializable, Cloneable {
   public static final long int = 6205762482756093838L;
   public double final;
   public double this;

   public Tuple2d(double arg0, double arg1) {
      thisx.final = arg0;
      thisx.this = arg1;
   }

   public Tuple2d(double[] arg0) {
      thisx.final = arg0[0];
      thisx.this = arg0[1];
   }

   public Tuple2d(Tuple2d arg0) {
      thisx.final = arg0.final;
      thisx.this = arg0.this;
   }

   public Tuple2d(Tuple2f arg0) {
      thisx.final = (double)arg0.final;
      thisx.this = (double)arg0.this;
   }

   public Tuple2d() {
      thisx.final = 0.0D;
      thisx.this = 0.0D;
   }

   public final void class(double arg0, double arg1) {
      thisx.final = arg0;
      thisx.this = arg1;
   }

   public final void class(double[] arg0) {
      thisx.final = arg0[0];
      thisx.this = arg0[1];
   }

   public final void true(Tuple2d arg0) {
      thisx.final = arg0.final;
      thisx.this = arg0.this;
   }

   public final void null(Tuple2f arg0) {
      thisx.final = (double)arg0.final;
      thisx.this = (double)arg0.this;
   }

   public final void null(double[] arg0) {
      arg0[0] = thisx.final;
      arg0[1] = thisx.this;
   }

   public final void class(Tuple2d arg0, Tuple2d arg1) {
      thisx.final = arg0.final + arg1.final;
      thisx.this = arg0.this + arg1.this;
   }

   public final void const(Tuple2d arg0) {
      thisx.final += arg0.final;
      thisx.this += arg0.this;
   }

   public final void null(Tuple2d arg0, Tuple2d arg1) {
      thisx.final = arg0.final - arg1.final;
      thisx.this = arg0.this - arg1.this;
   }

   public final void long(Tuple2d arg0) {
      thisx.final -= arg0.final;
      thisx.this -= arg0.this;
   }

   public final void class(Tuple2d arg0) {
      thisx.final = -arg0.final;
      thisx.this = -arg0.this;
   }

   public final void class() {
      thisx.final = -thisx.final;
      thisx.this = -thisx.this;
   }

   public final void const(double arg0, Tuple2d arg1) {
      thisx.final = arg0 * arg1.final;
      thisx.this = arg0 * arg1.this;
   }

   public final void true(double arg0) {
      thisx.final *= arg0;
      thisx.this *= arg0;
   }

   public final void null(double arg0, Tuple2d arg1, Tuple2d arg2) {
      thisx.final = arg0 * arg1.final + arg2.final;
      thisx.this = arg0 * arg1.this + arg2.this;
   }

   public final void long(double arg0, Tuple2d arg1) {
      thisx.final = arg0 * thisx.final + arg1.final;
      thisx.this = arg0 * thisx.this + arg1.this;
   }

   public int hashCode() {
      long var1 = 1L;
      var1 = VecMathUtil.null(var1, thisx.final);
      var1 = VecMathUtil.null(var1, thisx.this);
      return VecMathUtil.null(var1);
   }

   public boolean null(Tuple2d arg0) {
      try {
         return thisx.final == arg0.final && thisx.this == arg0.this;
      } catch (NullPointerException var3) {
         return false;
      }
   }

   public boolean equals(Object arg0) {
      try {
         Tuple2d var2 = (Tuple2d)arg0;
         return thisx.final == var2.final && thisx.this == var2.this;
      } catch (NullPointerException var3) {
         return false;
      } catch (ClassCastException var4) {
         return false;
      }
   }

   public boolean null(Tuple2d arg0, double arg1) {
      double var4 = thisx.final - arg0.final;
      if (Double.isNaN(var4)) {
         return false;
      } else if ((var4 < 0.0D ? -var4 : var4) > arg1) {
         return false;
      } else {
         var4 = thisx.this - arg0.this;
         if (Double.isNaN(var4)) {
            return false;
         } else {
            return !((var4 < 0.0D ? -var4 : var4) > arg1);
         }
      }
   }

   public String toString() {
      return ForgeCommand.null("Q") + thisx.final + RegionIntersection.null("B4") + thisx.this + ForgeCommand.null("P");
   }

   public final void null(double arg0, double arg1, Tuple2d arg2) {
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

   public final void class(double arg0, Tuple2d arg1) {
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

   public final void null(double arg0, Tuple2d arg1) {
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

   public final void null(Tuple2d arg0) {
      thisx.final = Math.abs(arg0.final);
      thisx.this = Math.abs(arg0.this);
   }

   public final void null(double arg0, double arg1) {
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

   public final void const(double arg0) {
      if (thisx.final < arg0) {
         thisx.final = arg0;
      }

      if (thisx.this < arg0) {
         thisx.this = arg0;
      }

   }

   public final void long(double arg0) {
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

   public final void null(Tuple2d arg0, Tuple2d arg1, double arg2) {
      thisx.final = (1.0D - arg2) * arg0.final + arg2 * arg1.final;
      thisx.this = (1.0D - arg2) * arg0.this + arg2 * arg1.this;
   }

   public final void null(Tuple2d arg0, double arg1) {
      thisx.final = (1.0D - arg1) * thisx.final + arg1 * arg0.final;
      thisx.this = (1.0D - arg1) * thisx.this + arg1 * arg0.this;
   }

   public Object clone() {
      try {
         return super.clone();
      } catch (CloneNotSupportedException var2) {
         throw new InternalError();
      }
   }

   public final double class() {
      return thisx.final;
   }

   public final void class(double arg0) {
      thisx.final = arg0;
   }

   public final double null() {
      return thisx.this;
   }

   public final void null(double arg0) {
      thisx.this = arg0;
   }
}
