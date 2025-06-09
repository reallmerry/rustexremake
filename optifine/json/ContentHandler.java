package optifine.json;

import android.text.style.MetricAffectingSpan;
import breeze.linalg.operators.CSCMatrixOps_Generic;
import breeze.math.MutableNormedModule;
import breeze.util.JavaArrayOps;
import com.alibaba.druid.mock.MockSQLXML;
import com.alibaba.druid.sql.dialect.h2.visitor.H2ASTVisitor;
import com.alibaba.druid.sql.dialect.hive.stmt.HiveLoadDataStatement;
import java.util.Random;
import net.daporkchop.lib.primitive.lambda.ByteCharShortFunction;
import net.daporkchop.lib.primitive.lambda.FloatBoolDoubleFunction;
import net.minecraft.client.renderer.chunk.IRenderChunkFactory;
import net.minecraft.client.renderer.entity.RenderSlime;
import net.minecraft.command.ICommand;
import net.minecraft.util.registry.RegistrySimple;
import net.minecraftforge.client.model.FancyMissingModel;
import net.minecraftforge.fml.common.asm.transformers.DeobfuscationTransformer;
import net.optifine.entity.model.ModelAdapterCow;
import net.optifine.reflect.FieldLocatorName;
import net.optifine.util.IntegratedServerUtils;

public abstract class ContentHandler extends ModelAdapterCow {
   public static final MutableNormedModule null = MutableNormedModule.null(DeobfuscationTransformer.null("\fA\u000bE\u0011E\nH\r"));
   public static final MutableNormedModule int = MutableNormedModule.null(FloatBoolDoubleFunction.null("\u0015o\u0013d\u001dX\u0012b\u0015f\u000f"));
   public boolean final;
   public int[] this;

   public ContentHandler() {
      super(RegistrySimple.return);
      thisx.null(true);
      thisx.null(IntegratedServerUtils.true);
      thisx.null(0.2F);
      thisx.null(1);
      thisx.null(MockSQLXML.char);
   }

   public void null(ByteCharShortFunction arg0, FancyMissingModel arg1, CSCMatrixOps_Generic arg2) {
      boolean var4 = true;
      boolean var5 = true;
      int var6 = arg1.long();
      int var7 = arg1.class();
      int var8 = arg1.null();
      if (arg0.null(new FancyMissingModel(var6 - 2, var7 - 2, var8 - 2), new FancyMissingModel(var6 + 2, var7 + 2, var8 + 2))) {
         for(int var9 = -1; var9 <= 1; ++var9) {
            for(int var10 = -1; var10 <= 1; ++var10) {
               for(int var11 = -1; var11 <= 1; ++var11) {
                  FancyMissingModel var12 = arg1.null(var9, var10, var11);
                  CSCMatrixOps_Generic var13 = arg0.null(var12);
                  if (var13.null() == RegistrySimple.return && !(Boolean)var13.null(int)) {
                     arg0.null((FancyMissingModel)var12, (CSCMatrixOps_Generic)var13.null(int, true), 4);
                  }
               }
            }
         }
      }

   }

   public void null(ByteCharShortFunction arg0, FancyMissingModel arg1, CSCMatrixOps_Generic arg2, Random arg3) {
      if (!arg0.new && (Boolean)arg2.null(int) && (Boolean)arg2.null(null)) {
         boolean var5 = true;
         boolean var6 = true;
         int var7 = arg1.long();
         int var8 = arg1.class();
         int var9 = arg1.null();
         boolean var10 = true;
         boolean var11 = true;
         boolean var12 = true;
         if (thisx.this == null) {
            thisx.this = new int['耀'];
         }

         if (arg0.null(new FancyMissingModel(var7 - 5, var8 - 5, var9 - 5), new FancyMissingModel(var7 + 5, var8 + 5, var9 + 5))) {
            FancyMissingModel.b var13 = new FancyMissingModel.b();
            int var14 = -4;

            label114:
            while(true) {
               int var15;
               int var16;
               if (var14 > 4) {
                  var14 = 1;

                  while(true) {
                     if (var14 > 4) {
                        break label114;
                     }

                     for(var15 = -4; var15 <= 4; ++var15) {
                        for(var16 = -4; var16 <= 4; ++var16) {
                           for(int var20 = -4; var20 <= 4; ++var20) {
                              if (thisx.this[(var15 + 16) * 1024 + (var16 + 16) * 32 + var20 + 16] == var14 - 1) {
                                 if (thisx.this[(var15 + 16 - 1) * 1024 + (var16 + 16) * 32 + var20 + 16] == -2) {
                                    thisx.this[(var15 + 16 - 1) * 1024 + (var16 + 16) * 32 + var20 + 16] = var14;
                                 }

                                 if (thisx.this[(var15 + 16 + 1) * 1024 + (var16 + 16) * 32 + var20 + 16] == -2) {
                                    thisx.this[(var15 + 16 + 1) * 1024 + (var16 + 16) * 32 + var20 + 16] = var14;
                                 }

                                 if (thisx.this[(var15 + 16) * 1024 + (var16 + 16 - 1) * 32 + var20 + 16] == -2) {
                                    thisx.this[(var15 + 16) * 1024 + (var16 + 16 - 1) * 32 + var20 + 16] = var14;
                                 }

                                 if (thisx.this[(var15 + 16) * 1024 + (var16 + 16 + 1) * 32 + var20 + 16] == -2) {
                                    thisx.this[(var15 + 16) * 1024 + (var16 + 16 + 1) * 32 + var20 + 16] = var14;
                                 }

                                 if (thisx.this[(var15 + 16) * 1024 + (var16 + 16) * 32 + (var20 + 16 - 1)] == -2) {
                                    thisx.this[(var15 + 16) * 1024 + (var16 + 16) * 32 + (var20 + 16 - 1)] = var14;
                                 }

                                 if (thisx.this[(var15 + 16) * 1024 + (var16 + 16) * 32 + var20 + 16 + 1] == -2) {
                                    thisx.this[(var15 + 16) * 1024 + (var16 + 16) * 32 + var20 + 16 + 1] = var14;
                                 }
                              }
                           }
                        }
                     }

                     ++var14;
                  }
               }

               for(var15 = -4; var15 <= 4; ++var15) {
                  for(var16 = -4; var16 <= 4; ++var16) {
                     CSCMatrixOps_Generic var17 = arg0.null((FancyMissingModel)var13.null(var7 + var14, var8 + var15, var9 + var16));
                     ModelAdapterCow var18 = var17.null();
                     if (var18 != RenderSlime.qB && var18 != RenderSlime.Ra) {
                        if (var17.null() == RegistrySimple.return) {
                           thisx.this[(var14 + 16) * 1024 + (var15 + 16) * 32 + var16 + 16] = -2;
                        } else {
                           thisx.this[(var14 + 16) * 1024 + (var15 + 16) * 32 + var16 + 16] = -1;
                        }
                     } else {
                        thisx.this[(var14 + 16) * 1024 + (var15 + 16) * 32 + var16 + 16] = 0;
                     }
                  }
               }

               ++var14;
            }
         }

         int var19 = thisx.this[16912];
         if (var19 >= 0) {
            arg0.null((FancyMissingModel)arg1, (CSCMatrixOps_Generic)arg2.null(int, false), 4);
         } else {
            thisx.class(arg0, arg1);
         }
      }

   }

   public void null(CSCMatrixOps_Generic arg0, ByteCharShortFunction arg1, FancyMissingModel arg2, Random arg3) {
      if (arg1.long(arg2.short()) && !arg1.null(arg2.do()).class() && arg3.nextInt(15) == 1) {
         double var5 = (double)((float)arg2.long() + arg3.nextFloat());
         double var7 = (double)arg2.class() - 0.05D;
         double var9 = (double)((float)arg2.null() + arg3.nextFloat());
         arg1.null(MetricAffectingSpan.abstract, var5, var7, var9, 0.0D, 0.0D, 0.0D);
      }

   }

   private void class(ByteCharShortFunction arg0, FancyMissingModel arg1) {
      thisx.null(arg0, arg1, arg0.null(arg1), 0);
      arg0.float(arg1);
   }

   public int null(Random arg0) {
      return arg0.nextInt(20) == 0 ? 1 : 0;
   }

   public IRenderChunkFactory null(CSCMatrixOps_Generic arg0, Random arg1, int arg2) {
      return IRenderChunkFactory.null(RenderSlime.Ca);
   }

   public void null(ByteCharShortFunction arg0, FancyMissingModel arg1, CSCMatrixOps_Generic arg2, float arg3, int arg4) {
      if (!arg0.new) {
         int var6 = thisx.do(arg2);
         if (arg4 > 0) {
            var6 -= 2 << arg4;
            if (var6 < 10) {
               var6 = 10;
            }
         }

         if (arg0.enum.nextInt(var6) == 0) {
            IRenderChunkFactory var7 = thisx.null(arg2, arg0.enum, arg4);
            null(arg0, arg1, new ICommand(var7, 1, thisx.null(arg2)));
         }

         var6 = 200;
         if (arg4 > 0) {
            var6 -= 10 << arg4;
            if (var6 < 40) {
               var6 = 40;
            }
         }

         thisx.class(arg0, arg1, arg2, var6);
      }

   }

   public void class(ByteCharShortFunction arg0, FancyMissingModel arg1, CSCMatrixOps_Generic arg2, int arg3) {
   }

   public int do(CSCMatrixOps_Generic arg0) {
      return 20;
   }

   /** @deprecated */
   public boolean long(CSCMatrixOps_Generic arg0) {
      return false;
   }

   public void null(boolean arg0) {
      thisx.final = arg0;
   }

   public H2ASTVisitor null() {
      return thisx.final ? H2ASTVisitor.false : H2ASTVisitor.const;
   }

   /** @deprecated */
   public boolean do(CSCMatrixOps_Generic arg0) {
      return false;
   }

   public abstract JavaArrayOps.a null(int var1);

   /** @deprecated */
   public boolean null(CSCMatrixOps_Generic arg0, FieldLocatorName arg1, FancyMissingModel arg2, HiveLoadDataStatement arg3) {
      return !thisx.final && arg1.null(arg2.null(arg3)).null() == thisx ? false : super.null(arg0, arg1, arg2, arg3);
   }
}
