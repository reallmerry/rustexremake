package optifine.json;

import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleStatement;
import com.alibaba.druid.support.jakarta.AbstractWebStatImpl;
import com.sk89q.worldedit.command.ChunkCommands;
import com.sk89q.worldedit.util.formatting.text.BlockNbtComponent;
import net.daporkchop.lib.primitive.lambda.FloatBoolFunction;
import net.daporkchop.lib.primitive.map.CharShortMap;
import net.minecraftforge.client.model.MultiModel;
import net.minecraftforge.fml.common.event.FMLEvent;
import net.optifine.entity.model.ModelAdapterEndermite;

public class JSONAware extends ModelAdapterEndermite {
   private static final FloatBoolFunction this = new FloatBoolFunction(ChunkCommands.null("@\fL\u001dA\u001bQ\u001a\u001b\fZ\u001d]\u001dMFQ\u0007P6S\b@\fC\bM6V\fU\u0004\u001a\u0019Z\u000e"));

   public void null(AbstractWebStatImpl arg0, double arg1, double arg2, double arg3, float arg4, int arg5, float arg6) {
      FMLEvent.break();
      CharShortMap var11 = (CharShortMap)arg0;
      if (var11.long() || var11.null()) {
         FMLEvent.null(516, 0.1F);
         thisx.class(JSONAware.this);
         float var12 = var11.long() ? var11.class(arg4) : var11.null(arg4);
         double var13 = var11.long() ? 256.0D - arg2 : 50.0D;
         var12 = BlockNbtComponent.true(var12 * 3.1415927F);
         int var15 = BlockNbtComponent.long((double)var12 * var13);
         float[] var16 = var11.long() ? OracleStatement.super.null() : OracleStatement.float.null();
         MultiModel.null(arg1, arg2, arg3, (double)arg4, (double)var12, (double)var11.class().class(), 0, var15, var16, 0.15D, 0.175D);
         MultiModel.null(arg1, arg2, arg3, (double)arg4, (double)var12, (double)var11.class().class(), 0, -var15, var16, 0.15D, 0.175D);
      }

      super.null(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
      FMLEvent.catch();
   }

   public int null(double arg0) {
      return super.null(arg0) + 1;
   }

   public float null() {
      return 1.0F;
   }
}
