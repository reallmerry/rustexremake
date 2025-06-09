package javax.vecmath;

import java.io.Serializable;

public class Vector3d extends Tuple3d implements Serializable {
   public static final long null = 3761969948420550442L;

   public Vector3d(double arg0, double arg1, double arg2) {
      super(arg0, arg1, arg2);
   }

   public Vector3d(double[] arg0) {
      super(arg0);
   }

   public Vector3d(Vector3d arg0) {
      super((Tuple3d)arg0);
   }

   public Vector3d(Vector3f arg0) {
      super((Tuple3f)arg0);
   }

   public Vector3d(Tuple3f arg0) {
      super(arg0);
   }

   public Vector3d(Tuple3d arg0) {
      super(arg0);
   }

   public Vector3d() {
   }

   public final void null(Vector3d arg0, Vector3d arg1) {
      double var3 = arg0.final * arg1.this - arg0.this * arg1.final;
      double var5 = arg1.int * arg0.this - arg1.this * arg0.int;
      this.this = arg0.int * arg1.final - arg0.final * arg1.int;
      this.int = var3;
      this.final = var5;
   }

   public final void null(Vector3d arg0) {
      double var2 = 1.0D / Math.sqrt(arg0.int * arg0.int + arg0.final * arg0.final + arg0.this * arg0.this);
      this.int = arg0.int * var2;
      this.final = arg0.final * var2;
      this.this = arg0.this * var2;
   }

   public final void long() {
      double var1 = 1.0D / Math.sqrt(this.int * this.int + this.final * this.final + this.this * this.this);
      this.int *= var1;
      this.final *= var1;
      this.this *= var1;
   }

   public final double class(Vector3d arg0) {
      return this.int * arg0.int + this.final * arg0.final + this.this * arg0.this;
   }

   public final double true() {
      return this.int * this.int + this.final * this.final + this.this * this.this;
   }

   public final double const() {
      return Math.sqrt(this.int * this.int + this.final * this.final + this.this * this.this);
   }

   public final double null(Vector3d arg0) {
      double var2 = this.class(arg0) / (this.const() * arg0.const());
      if (var2 < -1.0D) {
         var2 = -1.0D;
      }

      if (var2 > 1.0D) {
         var2 = 1.0D;
      }

      return Math.acos(var2);
   }
}
