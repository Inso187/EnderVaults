/*    */ package me.sin.endervaults.Utils.nbt;
/*    */ 
/*    */ public class MethodNames
/*    */ {
/*    */   protected static String getTiledataMethodName() {
/*  6 */     MinecraftVersion v = MinecraftVersion.getVersion();
/*  7 */     if (v == MinecraftVersion.MC1_8_R3) {
/*  8 */       return "b";
/*    */     }
/* 10 */     return "save";
/*    */   }
/*    */   
/*    */   protected static String getTypeMethodName() {
/* 14 */     MinecraftVersion v = MinecraftVersion.getVersion();
/* 15 */     if (v == MinecraftVersion.MC1_8_R3) {
/* 16 */       return "b";
/*    */     }
/* 18 */     return "d";
/*    */   }
/*    */   
/*    */   protected static String getEntitynbtgetterMethodName() {
/* 22 */     MinecraftVersion v = MinecraftVersion.getVersion();
/* 23 */     return "b";
/*    */   }
/*    */   
/*    */   protected static String getEntitynbtsetterMethodName() {
/* 27 */     MinecraftVersion v = MinecraftVersion.getVersion();
/* 28 */     return "a";
/*    */   }
/*    */   
/*    */   protected static String getremoveMethodName() {
/* 32 */     MinecraftVersion v = MinecraftVersion.getVersion();
/* 33 */     if (v == MinecraftVersion.MC1_8_R3) {
/* 34 */       return "a";
/*    */     }
/* 36 */     return "remove";
/*    */   }
/*    */ }


/* Location:              D:\JavaAPIS\Item-NBT-API.jar!\de\tr7zw\itemnbtapi\MethodNames.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */