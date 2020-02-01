/*    */ package me.sin.endervaults.Utils.nbt;
/*    */ 
/*    */

/*    */
/*    */ public enum MinecraftVersion {
/*  6 */   Unknown(0),  MC1_7_R4(174),  MC1_8_R3(183),  MC1_9_R1(191),  MC1_9_R2(192),  MC1_10_R1(1101),  MC1_11_R1(1111),  MC1_12_R1(1121);
/*    */   
/*    */   private MinecraftVersion(int id) {
/*  9 */     this.id = id;
/*    */   }
/*    */   
/*    */   private static MinecraftVersion version;
/*    */   private final int id;
/*    */   public int getId()
/*    */   {
/* 16 */     return this.id;
/*    */   }
/*    */   
/*    */   public static MinecraftVersion getVersion() {
/* 20 */     if (version != null) {
/* 21 */       return version;
/*    */     }
/* 23 */     String ver = org.bukkit.Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
/* 24 */     System.out.println("[NBTAPI] Found Spigot: " + ver + "! Trying to find NMS support");
/*    */     try {
/* 26 */       version = valueOf(ver.replace("v", "MC"));
/*    */     } catch (IllegalArgumentException ex) {
/* 28 */       version = Unknown;
/*    */     }
/* 30 */     if (version != Unknown) {
/* 31 */       System.out.println("[NBTAPI] NMS support '" + version.name() + "' loaded!");
/*    */     } else {
/* 33 */       System.out.println("[NBTAPI] Wasn't able to find NMS Support! Some functions will not work!");
/*    */     }
/* 35 */     return version;
/*    */   }
/*    */   
/* 38 */   private static Boolean cache = null;
/*    */   
/*    */   public static boolean hasGson() {
/* 41 */     if (cache != null) return cache.booleanValue();
/* 42 */     cache = Boolean.valueOf(false);
/*    */     try {
/* 44 */       System.out.println("Found Gson: " + Class.forName("com.google.gson.Gson"));
/* 45 */       cache = Boolean.valueOf(true);
/* 46 */       return cache.booleanValue();
/*    */     } catch (Exception ex) {}
/* 48 */     return cache.booleanValue();
/*    */   }
/*    */ }


/* Location:              D:\JavaAPIS\Item-NBT-API.jar!\de\tr7zw\itemnbtapi\MinecraftVersion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */