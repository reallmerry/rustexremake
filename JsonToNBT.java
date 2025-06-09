package net.minecraft.nbt;

import breeze.linalg.operators.CSCMatrixOps_Generic;
import breeze.math.EnumeratedCoordinateField;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MysqlShowStcStatement;
import javax.annotation.Nullable;
import net.daporkchop.lib.primitive.lambda.DoubleLongObjConsumer;
import net.optifine.entity.model.ModelAdapterCow;

public class JsonToNBT implements DoubleLongObjConsumer {
   private final CSCMatrixOps_Generic[] null;
   private final EnumeratedCoordinateField int;
   private final int final;
   private int this;

   public JsonToNBT(int arg0, EnumeratedCoordinateField arg1) {
      thisx.null = new CSCMatrixOps_Generic[1 << arg0];
      thisx.final = arg0;
      thisx.int = arg1;
   }

   public int null(CSCMatrixOps_Generic arg0) {
      int var2;
      for(var2 = 0; var2 < thisx.this; ++var2) {
         if (thisx.null[var2] == arg0) {
            return var2;
         }
      }

      var2 = thisx.this;
      if (var2 < thisx.null.length) {
         thisx.null[var2] = arg0;
         ++thisx.this;
         return var2;
      } else {
         return thisx.int.null(thisx.final + 1, arg0);
      }
   }

   @Nullable
   public CSCMatrixOps_Generic null(int arg0) {
      return arg0 >= 0 && arg0 < thisx.this ? thisx.null[arg0] : null;
   }

   public void class(MysqlShowStcStatement arg0) {
      thisx.this = arg0.null();

      for(int var2 = 0; var2 < thisx.this; ++var2) {
         thisx.null[var2] = (CSCMatrixOps_Generic)ModelAdapterCow.byte.null(arg0.null());
      }

   }

   public void null(MysqlShowStcStatement arg0) {
      arg0.null(thisx.this);

      for(int var2 = 0; var2 < thisx.this; ++var2) {
         arg0.null(ModelAdapterCow.byte.null(thisx.null[var2]));
      }

   }

   public int null() {
      int var1 = MysqlShowStcStatement.null(thisx.this);

      for(int var2 = 0; var2 < thisx.this; ++var2) {
         var1 += MysqlShowStcStatement.null(ModelAdapterCow.byte.null(thisx.null[var2]));
      }

      return var1;
   }
}
