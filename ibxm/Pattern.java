package ibxm;

import breeze.linalg.operators.CSCMatrixOps_Generic;
import com.alibaba.druid.sql.dialect.hive.stmt.HiveLoadDataStatement;
import net.daporkchop.lib.primitive.lambda.IntBoolDoubleConsumer;
import net.minecraft.util.registry.RegistrySimple;
import net.minecraftforge.client.model.FancyMissingModel;
import net.optifine.entity.model.ModelAdapterCow;
import net.optifine.reflect.FieldLocatorName;

public class Pattern extends ModelAdapterCow {
   public Pattern(RegistrySimple arg0, IntBoolDoubleConsumer arg1) {
      super(arg0, arg1);
   }

   /** @deprecated */
   public boolean class(CSCMatrixOps_Generic arg0) {
      return true;
   }

   /** @deprecated */
   public int class(CSCMatrixOps_Generic arg0, FieldLocatorName arg1, FancyMissingModel arg2, HiveLoadDataStatement arg3) {
      return 15;
   }
}
