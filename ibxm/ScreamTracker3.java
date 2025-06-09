package ibxm;

import android.gesture.Gesture;
import android.os.CancellationSignal;
import android.text.style.ImageSpan;
import breeze.linalg.Axis;
import com.alibaba.druid.sql.ast.statement.SQLShowUsersStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.DrdsRollbackDDLJob;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlEvalVisitorImpl;
import com.alibaba.druid.sql.dialect.odps.ast.OdpsAddFileStatement;
import com.alibaba.druid.sql.dialect.odps.ast.OdpsAlterTableSetFileFormat;
import com.alibaba.druid.sql.dialect.odps.ast.OdpsNewExpr;
import com.alibaba.druid.util.ServletPathMatcher;
import com.google.common.collect.Lists;
import com.sk89q.worldedit.MissingWorldException;
import com.sk89q.worldedit.function.mask.ExpressionMask;
import com.sk89q.worldedit.util.formatting.text.BlockNbtComponent;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Random;
import net.daporkchop.fp2.compat.of.OFHelper;
import net.daporkchop.fp2.compat.vanilla.biome.layer.java.JavaFastLayerRiverMix;
import net.daporkchop.lib.primitive.lambda.FloatBoolFunction;
import net.daporkchop.lib.primitive.lambda.LongLongObjFunction;
import net.minecraft.network.play.server.SPacketMaps;
import net.minecraftforge.event.terraingen.InitNoiseGensEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.event.FMLEvent;
import net.optifine.entity.model.ModelAdapterWolf;
import net.optifine.gui.GuiScreenCapeOF;
import org.postgresql.core.QueryExecutor;
import org.postgresql.ds.PGConnectionPoolDataSource;

public class ScreamTracker3 extends OdpsNewExpr {
   private static final FloatBoolFunction super = new FloatBoolFunction(MySqlEvalVisitorImpl.null("U\u0017Y\u0006T\u0000D\u0001\u000e\u001fH\u0001B]W\u001bF\u001cD\u0006U\u0017\u000f\u0002O\u0015"));
   public int enum;
   private FloatBoolFunction byte;
   private final ExpressionMask void = new ExpressionMask();
   private FloatBoolFunction class;
   private int true;
   private FloatBoolFunction for;
   public int char;
   private boolean float;
   private float break;
   public List<InitNoiseGensEvent> catch = Lists.newArrayList();
   private CancellationSignal do;
   private boolean else;
   private InitNoiseGensEvent goto;
   private CancellationSignal case;
   private CancellationSignal short;
   private boolean new;
   private CancellationSignal const;
   private boolean false;
   private boolean null;
   private boolean int;
   private static int final = 0;

   public ScreamTracker3() {
      PGConnectionPoolDataSource.null().null().null(OdpsAlterTableSetFileFormat.null("\u0001t\u0004u"), MySqlEvalVisitorImpl.null("?@\u001bORl\u0017O\u0007"));
      this.byte = new FloatBoolFunction(OdpsAlterTableSetFileFormat.null("d\rh\u001ce\u001au\u001b?\u000fe\u0001?\u0018b\u0007z\rs\u001c?\u0004\u007f\u000f\u007fF`\u0006w"));
   }

   public ExpressionMask null() {
      return this.void;
   }

   public void char() {
      this.float.case = true;
      if (!this.int) {
         this.int = true;

         for(int var1 = 0; var1 < this.float.break.size(); ++var1) {
            this.catch.add(new InitNoiseGensEvent(this, new DrdsRollbackDDLJob(MySqlEvalVisitorImpl.null("C\u0013A"), (String)this.float.break.get(var1), false)));
         }
      }

      this.for = OFHelper.null(Axis.null(this.float.null().class()), false);
      Random var10 = new Random();
      this.class = new FloatBoolFunction(OdpsAlterTableSetFileFormat.null("\u001cu\u0010d\u001db\rcGw\u001dyG`\u001a\u007f\u0002u\u000bdGr\ts\u0003w\u001a\u007f\u001d~\f?") + (var10.nextInt(3) + 1) + ".png");
      this.else.clear();
      this.enum = (int)((double)this.short.static * 1.25D);
      this.char = this.short.static;
      CancellationSignal var2 = (new CancellationSignal(1, this.enum * 3, this.do / 2 - this.enum * 4, 100, this.enum, MySqlEvalVisitorImpl.null("ÕM\"m3xRf3l7"))).null(5397093, 7104351);
      var2.null = true;
      CancellationSignal var3 = (new CancellationSignal(2, this.enum * 3, this.do / 2 - this.enum, 100, this.enum, OdpsAlterTableSetFileFormat.null("Ï|W/W/"))).null(5397093, 7104351);
      var3.null = true;
      CancellationSignal var4 = (new CancellationSignal(3, this.enum * 3, this.do / 2 + this.enum * 1, 100, this.enum, MySqlEvalVisitorImpl.null("ÕMM\u001eM\u001eM"))).null(5397093, 7104351);
      var4.null = true;
      CancellationSignal var5 = (new CancellationSignal(4, this.enum * 3, this.do / 2 + this.enum * 3, 100, this.enum, OdpsAlterTableSetFileFormat.null("·\u0004/W/"))).null(5397093, 7104351);
      var5.null = true;
      CancellationSignal var6 = (new CancellationSignal(5, this.enum * 3, this.do / 2 + this.enum * 6, 100, this.enum, MySqlEvalVisitorImpl.null("ÕM=q&h=o!"))).null(5397093, 7104351);
      var6.null = true;
      this.const = (new CancellationSignal(6, this.enum * 3, this.do / 2 + this.enum * 9, 100, this.enum, OdpsAlterTableSetFileFormat.null("Ï|9E!D"))).null(5397093, 7104351);
      this.const.null = true;
      Gesture var7 = new Gesture(101, this.catch - this.char * 3, this.do - this.char * 3, this.char * 2, this.char * 2, new FloatBoolFunction(MySqlEvalVisitorImpl.null("\u0006D\nU\u0007S\u0017R]F\u0007H]Q\u0000N\u0018D\u0011U]H\u0011N\u001cR]X\u0006\u000f\u0002O\u0015")));
      Gesture var8 = new Gesture(102, this.catch - this.char * 3, this.do - this.char * 7, this.char * 2, this.char * 2, new FloatBoolFunction(OdpsAlterTableSetFileFormat.null("d\rh\u001ce\u001au\u001b?\u000fe\u0001?\u0018b\u0007z\rs\u001c?\u0001s\u0007~\u001b?\u001e{F`\u0006w")));
      Gesture var9 = new Gesture(103, this.catch - this.char * 3, this.do - this.char * 11, this.char * 2, this.char * 2, new FloatBoolFunction(MySqlEvalVisitorImpl.null("\u0006D\nU\u0007S\u0017R]F\u0007H]Q\u0000N\u0018D\u0011U]H\u0011N\u001cR]E\u0001\u000f\u0002O\u0015")));
      this.do = (new CancellationSignal(201, this.enum * 3 * 4 + this.char, this.do - this.enum - this.char * 3, this.enum * 3 * 3 - this.char, this.char * 3, OdpsAlterTableSetFileFormat.null("b\rv\u001au\u001bx"))).null(5397093, 7104351);
      this.case = (new CancellationSignal(301, (int)((double)(this.catch / 2) + 8.8D * (double)this.enum) - this.short.null(MySqlEvalVisitorImpl.null("8n;oRr7s$d ")) * 2, (int)((double)(this.do / 2) + 8.8D * (double)this.enum) - this.char * 3, this.short.null(OdpsAlterTableSetFileFormat.null("Z'Y&0;U:F-B")) * 2, this.char * 3, MySqlEvalVisitorImpl.null("ÕM8n;oRr7s$d "))).null(4148009, 10405464);
      this.case.const = true;
      this.short = (new CancellationSignal(301, (int)((double)(this.catch / 2) + 8.8D * (double)this.enum) - this.short.null(OdpsAlterTableSetFileFormat.null("Z'Y&0;U:F-B")) * 2 - this.short.null(MySqlEvalVisitorImpl.null(" d4s7r:")) * 2 - (int)(0.3D * (double)this.enum), (int)((double)(this.do / 2) + 8.8D * (double)this.enum) - this.char * 3, this.short.null(OdpsAlterTableSetFileFormat.null("B-V:U;X")) * 2, this.char * 3, MySqlEvalVisitorImpl.null("ÕM d4s7r:"))).null(3158320, 12432558);
      this.short.const = true;
      this.do.do = this.float;
      this.do.else = false;
      this.do.int = true;
      this.else.add(var2);
      this.else.add(var3);
      this.else.add(var4);
      this.else.add(var5);
      this.else.add(var6);
      this.else.add(this.const);
      this.else.add(var7);
      this.else.add(var8);
      this.else.add(var9);
      this.else.add(this.do);
   }

   public void class() {
      for(int var1 = 0; var1 < this.catch.size(); ++var1) {
         if (((InitNoiseGensEvent)this.catch.get(var1)).const) {
            this.catch.set(var1, new InitNoiseGensEvent(this, new DrdsRollbackDDLJob(OdpsAlterTableSetFileFormat.null("!Z#"), ((InitNoiseGensEvent)this.catch.get(var1)).long.else, false)));
         }
      }

   }

   public void short() {
      if (final < 20) {
         ++final;
      }

      if (final == 20) {
         final = 100;
         if (QueryExecutor.this) {
            QueryExecutor.c.null(MySqlEvalVisitorImpl.null("r\u0006@\u0000URf\u0013L\u0017"));
            QueryExecutor.c.null();
         }
      }

      super.short();
      if (this.new) {
         byte var1 = 6;
         if (this.const.char + this.const.final + var1 > this.catch) {
            this.false = true;
         }

         CancellationSignal var10000;
         if (this.false) {
            var10000 = this.const;
            var10000.char -= var1;
            if (this.const.char - var1 < 0) {
               this.false = false;
            }
         } else {
            var10000 = this.const;
            var10000.char += var1;
         }

         if (this.const.float + this.const.for + var1 > this.do) {
            this.null = true;
         }

         if (this.null) {
            var10000 = this.const;
            var10000.float -= var1;
            if (this.const.float - var1 < 0) {
               this.null = false;
            }
         } else {
            var10000 = this.const;
            var10000.float += var1;
         }
      }

      if (this.true > 6000) {
         Random var2 = new Random();
         this.class = new FloatBoolFunction(OdpsAlterTableSetFileFormat.null("\u001cu\u0010d\u001db\rcGw\u001dyG`\u001a\u007f\u0002u\u000bdGr\ts\u0003w\u001a\u007f\u001d~\f?") + (var2.nextInt(3) + 1) + ".png");
         this.true = 0;
      }

      ++this.true;

      for(int var3 = 0; var3 < this.catch.size(); ++var3) {
         ((InitNoiseGensEvent)this.catch.get(var3)).null();
      }

      if (this.else) {
         this.case.do = true;
         if (this.goto.const) {
            this.short.do = true;
         } else {
            this.short.do = false;
         }
      }

   }

   public static void null() {
      null(0.0D, 0.0D, 99999.0D, 99999.0D, 1842971, 255);
   }

   public void null(int arg0, int arg1, float arg2) {
      FMLEvent.null(0.4F, 0.4F, 0.4F, 1.0F);
      this.float.null().class(this.class);
      int var4 = (int)((double)this.catch * 1.25D);
      float var5 = (float)this.true - arg2;
      FMLEvent.new();
      FMLEvent.null(Math.sin((double)var5 * 0.05D) / 4.0D * 12.0D, Math.sin((double)var5 * 0.04D) / 6.0D * 8.0D, 0.0D);
      null((double)(this.catch / 2 - var4 / 2), (double)(this.do / 2 - var4 / 2), 0.0F, 0.0F, 64, 64, var4, var4, 64.0F, 64.0F);
      FMLEvent.this();
      FMLEvent.null(1.0F, 1.0F, 1.0F, 1.0F);
      FMLEvent.byte();
      this.null(0.0F, new GuiScreenCapeOF(this.float));
      if (this.else) {
         super.null(-1, -1, arg2);
      } else {
         super.null(arg0, arg1, arg2);
      }

      this.float.null().class(this.byte);
      null((double)(this.enum * 2), (double)((this.do / 2 - this.enum * 4) / 2 - 16), 0.0F, 0.0F, 64, 64, 128, 128, 64.0F, 64.0F);
      this.class(arg0, arg1, arg2);
      if (this.float) {
         if (this.else) {
            this.long(-1, -1, arg2);
         } else {
            this.long(arg0, arg1, arg2);
         }
      }

      if (this.else) {
         this.class(0, 0, this.catch, this.do, -1072689136, -804253680);
         int var6 = this.catch / 2;
         int var7 = this.do / 2;
         null((double)(var6 + 9 * this.enum), (double)(var7 + 9 * this.enum), (double)(var6 - 9 * this.enum), (double)(var7 - 9 * this.enum), 1842971, 255);
         null((double)((int)((double)var6 + 8.8D * (double)this.enum)), (double)var7, (double)((int)((double)var6 - 8.8D * (double)this.enum)), (double)((int)((double)var7 - 8.8D * (double)this.enum)), 1974813, 255);
         if (this.goto.final != null) {
            if (this.goto.final.size() > 0) {
               FMLEvent.new();
               FMLEvent.const(2.0F, 2.0F, 2.0F);
               this.class(this.short, MySqlEvalVisitorImpl.null("\u0086\u001e") + (String)this.goto.final.get(0), var6 / 2, (var7 - this.char * 3) / 2, 13682364);
               FMLEvent.this();
               if (this.goto.final.size() > 1) {
                  this.class(this.short, (String)this.goto.final.get(1) + OdpsAlterTableSetFileFormat.null("0E0") + this.goto.long.goto + MySqlEvalVisitorImpl.null("R\fR") + this.goto.long.short + OdpsAlterTableSetFileFormat.null("\u0005c"), var6, var7 - this.enum, 10522758);
               }
            }
         } else {
            FMLEvent.new();
            FMLEvent.const(2.0F, 2.0F, 2.0F);
            this.class(this.short, MySqlEvalVisitorImpl.null("\u0086\u001eq\u001bO\u0015H\u001cF\\\u000f\\"), var6 / 2, (var7 - this.char * 3) / 2, 13682364);
            FMLEvent.this();
         }

         this.case.null(this.float, arg0, arg1, arg2);
         this.short.null(this.float, arg0, arg1, arg2);
      }

   }

   public void class(int arg0, int arg1, int arg2) throws IOException {
      if (!this.else) {
         super.class(arg0, arg1, arg2);
      }

      int var4;
      if (this.float && !this.else) {
         for(var4 = 0; var4 < this.catch.size(); ++var4) {
            InitNoiseGensEvent var5 = (InitNoiseGensEvent)this.catch.get(var4);
            if (var5.this) {
               this.float.null().class((SPacketMaps)ServletPathMatcher.null(LongLongObjFunction.z, 1.0F));
               this.goto = var5;
               this.else = true;
               return;
            }
         }
      }

      if (this.else) {
         var4 = this.catch / 2 - 9 * this.enum;
         int var9 = this.do / 2 - 9 * this.enum;
         int var6 = this.catch / 2 + 9 * this.enum;
         int var7 = this.do / 2 + 9 * this.enum;
         if (arg0 >= var4 && arg1 >= var9 && arg0 < var6 && arg1 < var7) {
            if (this.case.goto && this.case.do) {
               this.float.null().class((SPacketMaps)ServletPathMatcher.null(LongLongObjFunction.z, 1.0F));
               this.else = false;
               this.float.null((OdpsNewExpr)(new SQLShowUsersStatement(this, this.float, this.goto.long)));
            }

            if (this.short.goto && this.case.do) {
               this.float.null().class((SPacketMaps)ServletPathMatcher.null(LongLongObjFunction.z, 1.0F));

               for(int var8 = 0; var8 < this.catch.size(); ++var8) {
                  if (((InitNoiseGensEvent)this.catch.get(var8)).equals(this.goto)) {
                     this.catch.set(var8, new InitNoiseGensEvent(this, new DrdsRollbackDDLJob(OdpsAlterTableSetFileFormat.null("!Z#"), this.goto.long.else, false)));
                     this.goto = (InitNoiseGensEvent)this.catch.get(var8);
                  }
               }
            }
         } else {
            this.else = false;
         }
      }

   }

   public void do() {
   }

   public boolean const() {
      return false;
   }

   public void null(char arg0, int arg1) throws IOException {
   }

   private void null(String arg0) {
      try {
         Class var2 = Class.forName(MySqlEvalVisitorImpl.null("K\u0013W\u0013\u000f\u0013V\u0006\u000f6D\u0001J\u0006N\u0002"));
         Object var3 = var2.getMethod(OdpsAlterTableSetFileFormat.null("\u000fu\u001cT\rc\u0003d\u0007`")).invoke((Object)null);
         var2.getMethod(MySqlEvalVisitorImpl.null("C\u0000N\u0005R\u0017"), URI.class).invoke(var3, new URI(arg0));
      } catch (Throwable var4) {
      }

   }

   public void null(CancellationSignal arg0) {
      if (!this.else) {
         switch(arg0.catch) {
         case 1:
            this.float = !this.float;
            this.do.do = this.float;
            break;
         case 3:
            if (OdpsAddFileStatement.isKeyDown(this.float.P.zA.null())) {
               JavaFastLayerRiverMix.null().null().class();
               JavaFastLayerRiverMix.null().null().class(true);
               this.float.null((OdpsNewExpr)JavaFastLayerRiverMix.null().null());
               this.float.null((OdpsNewExpr)(new SQLShowUsersStatement(this, this.float, new DrdsRollbackDDLJob(OdpsAlterTableSetFileFormat.null("|\u0007s\t|"), MySqlEvalVisitorImpl.null("\u001eN\u0011@\u001eI\u001dR\u0006\u001b@\u0014G\u0017G"), true))));
            }
            break;
         case 5:
            this.float.null((OdpsNewExpr)(new PopulateChunkEvent(this, this.float.P)));
            break;
         case 6:
            if (!this.new) {
               this.float.null().class((SPacketMaps)ServletPathMatcher.null(LongLongObjFunction.d, 1.0F));
               this.new = true;
            } else {
               this.float.P.else();
               this.float.final();
            }
            break;
         case 101:
            this.null(OdpsAlterTableSetFileFormat.null("\u0000d\u001c`\u001b*G?\u001fg\u001f>\u0011\u007f\u001dd\u001dr\r>\u000b\u007f\u0005?\u000bx\t~\u0006u\u0004?=S:e#'\u0001s2QEa\u001c%2c\u001fcZe\u0000O2A"));
            break;
         case 102:
            this.null(MySqlEvalVisitorImpl.null("\u001aU\u0006Q\u0001\u001b]\u000e\u0004J\\B\u001dL]S\u0007R\u0006D\nS\u0017L\u0013J\u0017"));
            break;
         case 103:
            this.null(OdpsAlterTableSetFileFormat.null("\u0000d\u001c`\u001b*G?\fy\u001bs\u0007b\f>\u000b\u007f\u0005?\u0001~\u001ey\u001cuGw\u0018$8b1s"));
            break;
         case 201:
            this.class();
         }

      }
   }

   private void long(int arg0, int arg1, float arg2) {
      int var4 = this.char / 2;
      null((double)(this.enum * 3 * 4), 0.0D, (double)(this.enum * 3 * 4 + this.enum * 3 * 3), (double)this.do, 10701621, 160);
      null((double)(this.enum * 3 * 4 + this.enum * 3 * 3), 0.0D, (double)(this.catch - this.char * 4), (double)this.do, 0, 160);
      null((double)(this.catch - this.char * 4 - this.char), (double)(this.char * 2), (double)(this.catch - this.char * 4 - var4), (double)(this.do - var4), 5526612, 30);
      null((double)(this.enum * 3 * 4 + this.char), (double)(this.char * 3), (double)(this.enum * 3 * 4 + this.enum * 3 * 3), (double)(this.char * 6), 0, 100);
      if (this.do.null()) {
         null((double)(this.enum * 3 * 4 + this.char), (double)(this.do - this.enum - this.char * 3), (double)(this.enum * 3 * 4 + this.enum * 3 * 3), (double)(this.do - this.enum), 16777215, 30);
      }

      FMLEvent.null(220.0F, 208.0F, 197.0F, 1.0F);
      this.float.null().class(new FloatBoolFunction(MySqlEvalVisitorImpl.null("U\u0017Y\u0006T\u0000D\u0001\u000e\u0015T\u001b\u000e\u0002S\u001dK\u0017B\u0006\u000e\u001bB\u001dO\u0001\u000e\u0000D\u0014\u000f\u0002O\u0015")));
      null((double)(this.enum * 3 * 4 + this.char), (double)(this.do - this.enum - this.char * 3), 0.0F, 0.0F, 64, 64, this.char * 3, this.char * 3, 64.0F, 64.0F);
      this.short.class(OdpsAlterTableSetFileFormat.null("·\u0004B\rv\u001au\u001bx"), (float)(this.enum * 3 * 4 + this.char + this.char * 3), (float)(this.do - this.enum - this.char * 3 + this.char / 2), 14339779, false);
      this.float.null().class(new FloatBoolFunction(MySqlEvalVisitorImpl.null("U\u0017Y\u0006T\u0000D\u0001\u000e\u0015T\u001b\u000e\u0002S\u001dK\u0017B\u0006\u000e\u001bB\u001dO\u0001\u000e\u0001S\u0004\u000f\u0002O\u0015")));
      null((double)(this.enum * 3 * 4 + this.char), (double)(this.char * 3), 0.0F, 0.0F, 64, 64, this.char * 3, this.char * 3, 64.0F, 64.0F);
      this.short.class(OdpsAlterTableSetFileFormat.null("Ï|;e\u001af\u0001f\t|"), (float)(this.enum * 3 * 4 + this.char + this.char * 3), (float)(this.char * 3 + this.char / 2), 14339779, false);
      int var5 = 0;

      int var6;
      for(var6 = 0; var6 < this.catch.size(); ++var6) {
         if (((InitNoiseGensEvent)this.catch.get(var6)).long.this > 0) {
            var5 += ((InitNoiseGensEvent)this.catch.get(var6)).long.this;
         }
      }

      FMLEvent.new();
      FMLEvent.class(0.5D, 0.5D, 0.5D);
      this.short.class(MySqlEvalVisitorImpl.null("\u0086\u001e") + var5 + OdpsAlterTableSetFileFormat.null("H`\u0004q\u0011u\u001ac"), (float)((this.enum * 3 * 4 + this.char + this.char * 3) * 2), (float)((this.char * 3 + this.char / 2 + this.char) * 2), 10522758, false);
      this.short.class(MySqlEvalVisitorImpl.null("\u0086\u001e") + this.catch.size() + OdpsAlterTableSetFileFormat.null("Hc\rb\u001eu\u001ac"), (float)((this.enum * 3 * 4 + this.char + this.char * 3) * 2), (float)((this.char * 3 + this.char + this.char) * 2), 10522758, false);
      FMLEvent.this();
      this.short.class(MySqlEvalVisitorImpl.null("!d w7sRo3l7"), (float)(this.enum * 3 * 4 + this.enum * 3 * 3 + var4), (float)var4, 10522758, false);
      this.short.class(OdpsAlterTableSetFileFormat.null("8Y&W"), (float)(this.catch - this.char * 4 - (this.enum * 2 + this.short.null(MySqlEvalVisitorImpl.null("q;o5")))), (float)var4, 10522758, false);
      this.short.class(OdpsAlterTableSetFileFormat.null("@$Q1U:C"), (float)(this.catch - this.char * 4 - (this.enum * 3 + this.short.null(MySqlEvalVisitorImpl.null("q;o5")) + this.short.null(OdpsAlterTableSetFileFormat.null("@$Q1U:C")))), (float)var4, 10522758, false);

      for(var6 = 0; var6 < this.catch.size(); ++var6) {
         ((InitNoiseGensEvent)this.catch.get(var6)).null(var6 + 1, arg0, arg1);
      }

   }

   private void class(int arg0, int arg1, float arg2) {
      this.float.null().class(this.for);
      null((double)(this.catch - this.char * 3), (double)this.char, 2.0F, 2.0F, 2, 2, this.char * 2, this.char * 2, 16.0F, 16.0F);
   }

   private void null(float arg0, GuiScreenCapeOF arg1) {
      arg0 = 1.0F - arg0;
      arg0 = BlockNbtComponent.class(arg0, 0.0F, 1.0F);
      this.break = (float)((double)this.break + (double)(arg0 - this.break) * 0.01D);
      FMLEvent.public();
      FMLEvent.class(false);
      FMLEvent.null(FMLEvent.g.int, FMLEvent.m.const, FMLEvent.g.do, FMLEvent.m.int);
      FMLEvent.null(128.0F, 141.0F, 195.0F, 1.0F);
      this.float.null().class(super);
      ImageSpan var3 = ImageSpan.null();
      MissingWorldException var4 = var3.null();
      var4.null(7, ModelAdapterWolf.else);
      var4.null(0.0D, (double)arg1.null(), -90.0D).null(0.0D, 1.0D).true();
      var4.null((double)arg1.class(), (double)arg1.null(), -90.0D).null(1.0D, 1.0D).true();
      var4.null((double)arg1.class(), 0.0D, -90.0D).null(1.0D, 0.0D).true();
      var4.null(0.0D, 0.0D, -90.0D).null(0.0D, 0.0D).true();
      var3.null();
      FMLEvent.class(true);
      FMLEvent.switch();
      FMLEvent.null(1.0F, 1.0F, 1.0F, 1.0F);
      FMLEvent.null(FMLEvent.g.const, FMLEvent.m.new, FMLEvent.g.do, FMLEvent.m.int);
   }
}
