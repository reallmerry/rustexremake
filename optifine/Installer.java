package optifine;

import android.os.Vibrator;
import android.renderscript.Double3;
import breeze.linalg.support.TensorPairs;
import com.alibaba.druid.filter.logging.CommonsLogFilter;
import com.alibaba.druid.sql.ast.statement.SQLAlterTableDropConstraint;
import net.daporkchop.fp2.client.ShaderGlStateHelper;
import net.daporkchop.fp2.client.gl.OpenGL;
import net.daporkchop.lib.primitive.lambda.CharBoolFloatConsumer;
import net.daporkchop.lib.primitive.lambda.IntCharShortFunction;
import net.minecraft.command.CommandDefaultGameMode;
import net.minecraft.entity.INpc;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.optifine.shaders.config.PropertyDefaultFastFancyOff;

public class Installer extends TensorPairs {
   public Installer() {
      super(CommandDefaultGameMode.class, FixTypes.null("5\u00034\b\"23\u00055\u001e$"), 0.0F);
   }

   public CommonsLogFilter null() {
      return new CharBoolFloatConsumer();
   }

   public PropertyDefaultFastFancyOff null(CommonsLogFilter arg0, String arg1) {
      if (!(arg0 instanceof CharBoolFloatConsumer)) {
         return null;
      } else {
         CharBoolFloatConsumer var3 = (CharBoolFloatConsumer)arg0;
         if (arg1.equals(ShaderGlStateHelper.null("$*,"))) {
            return var3.int;
         } else if (arg1.equals(FixTypes.null("\u000f1\u001e5"))) {
            return var3.final;
         } else {
            return arg1.equals(ShaderGlStateHelper.null("(&,*")) ? var3.this : null;
         }
      }
   }

   public String[] null() {
      return new String[]{FixTypes.null("<\u00044"), ShaderGlStateHelper.null("!)0-"), FixTypes.null("\u0006>\u00022")};
   }

   public Double3 null(CommonsLogFilter arg0, float arg1) {
      INpc var3 = INpc.char;
      Object var4 = var3.null(CommandDefaultGameMode.class);
      if (!(var4 instanceof Vibrator)) {
         return null;
      } else {
         if (((IntCharShortFunction)var4).null() == null) {
            var4 = new Vibrator();
            ((IntCharShortFunction)var4).null(var3);
         }

         if (!OpenGL.do.null()) {
            SQLAlterTableDropConstraint.true(ShaderGlStateHelper.null("\u0005!&$'h-'7h%'6&'rc\u001c*$&\r-<*<:\r-,&:\u0000 &;7\u001a&&'-1-1f.''-/\u000b+-0<"));
            return null;
         } else {
            OpenGL.null((Object)var4, (WorldGenShrub)OpenGL.do, (Object)arg0);
            return (Double3)var4;
         }
      }
   }
}
