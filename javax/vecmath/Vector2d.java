package javax.vecmath;

import java.io.Serializable;

public class Vector2d extends Tuple2d implements Serializable {
   public static final long int = 8572646365302599857L;

   public Vector2d(double arg0, double arg1) {
      super(arg0, arg1);
   }

   public Vector2d(double[] arg0) {
      super(arg0);
   }

   public Vector2d(Vector2d arg0) {
      super((Tuple2d)arg0);
   }

   public Vector2d(Vector2f arg0) {
      super((Tuple2f)arg0);
   }

   public Vector2d(Tuple2d arg0) {
      super(arg0);
   }

   public Vector2d(Tuple2f arg0) {
      super(arg0);
   }

   public Vector2d() {
   }

   public final double class(Vector2d arg0) {
      return this.final * arg0.final + this.this * arg0.this;
   }

   public final double const() {
      return Math.sqrt(this.final * this.final + this.this * this.this);
   }

   public final double long() {
      return this.final * this.final + this.this * this.this;
   }

   public final void null(Vector2d arg0) {
      double var2 = 1.0D / Math.sqrt(arg0.final * arg0.final + arg0.this * arg0.this);
      this.final = arg0.final * var2;
      this.this = arg0.this * var2;
   }

   public final void long() {
      double var1 = 1.0D / Math.sqrt(this.final * this.final + this.this * this.this);
      this.final *= var1;
      this.this *= var1;
   }

   public final double null(Vector2d arg0) {
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
