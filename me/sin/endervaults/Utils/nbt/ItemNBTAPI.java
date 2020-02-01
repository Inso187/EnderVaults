/*     */ package me.sin.endervaults.Utils.nbt;
/*     */ 
/*     */

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.logging.Level;

/*     */
/*     */
/*     */
/*     */
/*     */

/*     */
/*     */ public class ItemNBTAPI extends org.bukkit.plugin.java.JavaPlugin
/*     */ {
/*  12 */   private static boolean compatible = true;
/*  13 */   private static boolean jsonCompatible = true;
/*     */   public static ItemNBTAPI instance;
/*     */   private static final String STRING_TEST_KEY = "stringTest";
/*     */   
/*     */   public void onEnable() {
/*  18 */     instance = this;
/*  19 */     new MetricsLite(this);
/*  20 */     getLogger().info("Running NBT reflection test...");
/*     */     try
/*     */     {
/*  23 */       NBTFile file = new NBTFile(new File(getDataFolder(), "test.nbt"));
/*  24 */       file.addCompound("testcomp");
/*  25 */       file.setLong("time", Long.valueOf(System.currentTimeMillis()));
/*  26 */       file.setString("test", "test");
/*  27 */       file.save();
/*  28 */       file.getFile().delete();
/*     */       
/*  30 */       ItemStack item = new ItemStack(Material.STONE, 1);
/*  31 */       NBTItem nbtItem = new NBTItem(item);
/*     */       
/*  33 */       nbtItem.setString("stringTest", "TestString");
/*  34 */       nbtItem.setInteger("intTest", Integer.valueOf(42));
/*  35 */       nbtItem.setDouble("doubleTest", Double.valueOf(1.5D));
/*  36 */       nbtItem.setBoolean("booleanTest", Boolean.valueOf(true));
/*  37 */       nbtItem.setByte("byteTest", Byte.valueOf((byte)7));
/*  38 */       nbtItem.setShort("shortTest", Short.valueOf((short)64));
/*  39 */       nbtItem.setLong("longTest", Long.valueOf(2147483689L));
/*  40 */       nbtItem.setFloat("floatTest", Float.valueOf(13.37F));
/*  41 */       nbtItem.setIntArray("intarrayTest", INTARRAY_TEST_VALUE);
/*  42 */       nbtItem.setByteArray("bytearrayTest", BYTEARRAY_TEST_VALUE);
/*  43 */       nbtItem.addCompound("componentTest");
/*  44 */       NBTCompound comp = nbtItem.getCompound("componentTest");
/*  45 */       comp.setString("stringTest", "TestString2");
/*  46 */       comp.setInteger("intTest", Integer.valueOf(84));
/*  47 */       comp.setDouble("doubleTest", Double.valueOf(3.0D));
/*  48 */       NBTList list = comp.getList("testlist", NBTType.NBTTagString);
/*  49 */       if (MinecraftVersion.getVersion() == MinecraftVersion.MC1_7_R4) {
/*  50 */         getLogger().warning("Skipped the NBTList check, because 1.7 doesn't fully support it! The Item-NBT-API may not work!");
/*     */       } else {
/*  52 */         list.addString("test1");
/*  53 */         list.addString("test2");
/*  54 */         list.addString("test3");
/*  55 */         list.addString("test4");
/*  56 */         list.setString(2, "test42");
/*  57 */         list.remove(1);
/*     */       }
/*  59 */       NBTList taglist = comp.getList("complist", NBTType.NBTTagCompound);
/*  60 */       NBTListCompound lcomp = taglist.addCompound();
/*  61 */       lcomp.setDouble("double1", 0.3333D);
/*  62 */       lcomp.setInteger("int1", 42);
/*  63 */       lcomp.setString("test1", "test1");
/*  64 */       lcomp.setString("test2", "test2");
/*  65 */       lcomp.remove("test1");
/*     */       
/*  67 */       item = nbtItem.getItem();
/*  68 */       nbtItem = null;
/*  69 */       comp = null;
/*  70 */       list = null;
/*  71 */       nbtItem = new NBTItem(item);
/*     */       
/*     */ 
/*  74 */       if (!nbtItem.hasKey("stringTest").booleanValue()) {
/*  75 */         getLogger().warning("Wasn't able to check a key! The Item-NBT-API may not work!");
/*  76 */         compatible = false;
/*     */       }
/*  78 */       if ((!"TestString".equals(nbtItem.getString("stringTest"))) || 
/*  79 */         (nbtItem.getInteger("intTest").intValue() != 42) || 
/*  80 */         (nbtItem.getDouble("doubleTest").doubleValue() != 1.5D) || 
/*  81 */         (nbtItem.getByte("byteTest").byteValue() != 7) || 
/*  82 */         (nbtItem.getShort("shortTest").shortValue() != 64) || 
/*  83 */         (nbtItem.getFloat("floatTest").floatValue() != 13.37F) || 
/*  84 */         (nbtItem.getLong("longTest").longValue() != 2147483689L) || 
/*  85 */         (nbtItem.getIntArray("intarrayTest").length != INTARRAY_TEST_VALUE.length) || 
/*  86 */         (nbtItem.getByteArray("bytearrayTest").length != BYTEARRAY_TEST_VALUE.length) || 
/*  87 */         (!nbtItem.getBoolean("booleanTest").equals(Boolean.valueOf(true))))
/*     */       {
/*  89 */         getLogger().warning("One key does not equal the original value! The Item-NBT-API may not work!");
/*  90 */         compatible = false;
/*     */       }
/*  92 */       nbtItem.setString("stringTest", null);
/*  93 */       if (nbtItem.getKeys().size() != 10) {
/*  94 */         getLogger().warning("Wasn't able to remove a key (Got " + nbtItem.getKeys().size() + 
/*  95 */           " when expecting 4)! The Item-NBT-API may not work!");
/*  96 */         compatible = false;
/*     */       }
/*  98 */       comp = nbtItem.getCompound("componentTest");
/*  99 */       if (comp == null) {
/* 100 */         getLogger().warning("Wasn't able to get the NBTCompound! The Item-NBT-API may not work!");
/* 101 */         compatible = false;
/*     */       }
/* 103 */       if (!comp.hasKey("stringTest").booleanValue()) {
/* 104 */         getLogger().warning("Wasn't able to check a compound key! The Item-NBT-API may not work!");
/* 105 */         compatible = false;
/*     */       }
/* 107 */       if ((!"TestString2".equals(comp.getString("stringTest"))) || 
/* 108 */         (comp.getInteger("intTest").intValue() != 84) || 
/* 109 */         (comp.getDouble("doubleTest").doubleValue() != 3.0D) || 
/* 110 */         (comp.getBoolean("booleanTest").booleanValue())) {
/* 111 */         getLogger().warning("One key does not equal the original compound value! The Item-NBT-API may not work!");
/* 112 */         compatible = false;
/*     */       }
/*     */       
/* 115 */       list = comp.getList("testlist", NBTType.NBTTagString);
/* 116 */       if (comp.getType("testlist") != NBTType.NBTTagList) {
/* 117 */         getLogger().warning("Wasn't able to get the correct Tag type! The Item-NBT-API may not work!");
/* 118 */         compatible = false;
/*     */       }
/* 120 */       if ((!list.getString(1).equals("test42")) || (list.size() != 3)) {
/* 121 */         getLogger().warning("The List support got an error, and may not work!");
/*     */       }
/* 123 */       taglist = comp.getList("complist", NBTType.NBTTagCompound);
/* 124 */       if (taglist.size() == 1) {
/* 125 */         lcomp = taglist.getCompound(0);
/* 126 */         if (lcomp.getKeys().size() != 3) {
/* 127 */           getLogger().warning("Wrong key amount in Taglist (" + lcomp.getKeys().size() + ")! The Item-NBT-API may not work!");
/* 128 */           compatible = false;
/*     */         }
/* 130 */         else if ((lcomp.getDouble("double1") != 0.3333D) || (lcomp.getInteger("int1") != 42) || (!lcomp.getString("test2").equals("test2")) || (lcomp.hasKey("test1")))
/*     */         {
/*     */ 
/* 133 */           getLogger().warning("One key in the Taglist changed! The Item-NBT-API may not work!");
/* 134 */           compatible = false;
/*     */         }
/*     */       }
/*     */       else {
/* 138 */         getLogger().warning("Taglist is empty! The Item-NBT-API may not work!");
/* 139 */         compatible = false;
/*     */       }
/*     */     } catch (Exception ex) {
/* 142 */       getLogger().log(Level.SEVERE, null, ex);
/* 143 */       compatible = false;
/*     */     }
/*     */     
/* 146 */     testJson();
/*     */     
/* 148 */     String checkMessage = "Plugins that don't check properly, may throw Exeptions, crash or have unexpected behaviors!";
/* 149 */     if (compatible) {
/* 150 */       if (jsonCompatible) {
/* 151 */         getLogger().info("Success! This version of Item-NBT-API is compatible with your server.");
/*     */       } else {
/* 153 */         getLogger().info("General Success! This version of Item-NBT-API is mostly compatible with your server. JSON serialization is not working properly. " + checkMessage);
/*     */       }
/*     */     } else {
/* 156 */       getLogger().warning("WARNING! This version of Item-NBT-API seems to be broken with your Spigot version! " + checkMessage);
/*     */     }
/*     */   }
/*     */   
/*     */   public void testJson()
/*     */   {
/* 162 */     if (!MinecraftVersion.hasGson()) {
/* 163 */       getLogger().warning("Wasn't able to find Gson! The Item-NBT-API may not work with Json serialization/deserialization!");
/* 164 */       return;
/*     */     }
/*     */     try {
/* 167 */       ItemStack item = new ItemStack(Material.STONE, 1);
/* 168 */       NBTItem nbtItem = new NBTItem(item);
/*     */       
/* 170 */       nbtItem.setObject("jsonTest", new SimpleJsonTestObject());
/*     */       
/* 172 */       item = nbtItem.getItem();
/*     */       
/* 174 */       if (!nbtItem.hasKey("jsonTest").booleanValue()) {
/* 175 */         getLogger().warning("Wasn't able to find JSON key! The Item-NBT-API may not work with Json serialization/deserialization!");
/* 176 */         jsonCompatible = false;
/*     */       } else {
/* 178 */         SimpleJsonTestObject simpleObject = (SimpleJsonTestObject)nbtItem.getObject("jsonTest", SimpleJsonTestObject.class);
/* 179 */         if (simpleObject == null) {
/* 180 */           getLogger().warning("Wasn't able to check JSON key! The Item-NBT-API may not work with Json serialization/deserialization!");
/* 181 */           jsonCompatible = false;
/* 182 */         } else if ((!"TestString".equals(simpleObject.getTestString())) || 
/* 183 */           (simpleObject.getTestInteger() != 42) || 
/* 184 */           (simpleObject.getTestDouble() != 1.5D) || 
/* 185 */           (!simpleObject.isTestBoolean())) {
/* 186 */           getLogger().warning("One key does not equal the original value in JSON! The Item-NBT-API may not work with Json serialization/deserialization!");
/* 187 */           jsonCompatible = false;
/*     */         }
/*     */       }
/*     */     } catch (Exception ex) {
/* 191 */       getLogger().log(Level.SEVERE, null, ex);
/* 192 */       getLogger().warning(ex.getMessage());
/* 193 */       jsonCompatible = false;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void onDisable() {}
/*     */   
/*     */   public boolean isCompatible()
/*     */   {
/* 202 */     return compatible;
/*     */   }
/*     */   
/*     */   public static NBTItem getNBTItem(ItemStack item) {
/* 206 */     return new NBTItem(item);
/*     */   }
/*     */   
/*     */ 
/*     */   private static final String INT_TEST_KEY = "intTest";
/*     */   
/*     */   private static final String DOUBLE_TEST_KEY = "doubleTest";
/*     */   
/*     */   private static final String BOOLEAN_TEST_KEY = "booleanTest";
/*     */   private static final String JSON_TEST_KEY = "jsonTest";
/*     */   private static final String COMP_TEST_KEY = "componentTest";
/*     */   private static final String SHORT_TEST_KEY = "shortTest";
/*     */   private static final String BYTE_TEST_KEY = "byteTest";
/*     */   private static final String FLOAT_TEST_KEY = "floatTest";
/*     */   private static final String LONG_TEST_KEY = "longTest";
/*     */   private static final String INTARRAY_TEST_KEY = "intarrayTest";
/*     */   private static final String BYTEARRAY_TEST_KEY = "bytearrayTest";
/*     */   private static final String STRING_TEST_VALUE = "TestString";
/*     */   private static final int INT_TEST_VALUE = 42;
/*     */   private static final double DOUBLE_TEST_VALUE = 1.5D;
/*     */   private static final boolean BOOLEAN_TEST_VALUE = true;
/*     */   private static final short SHORT_TEST_VALUE = 64;
/*     */   private static final byte BYTE_TEST_VALUE = 7;
/*     */   private static final float FLOAT_TEST_VALUE = 13.37F;
/*     */   private static final long LONG_TEST_VALUE = 2147483689L;
/* 231 */   private static final int[] INTARRAY_TEST_VALUE = { 1337, 42, 69 };
/* 232 */   private static final byte[] BYTEARRAY_TEST_VALUE = { 8, 7, 3, 2 };
/*     */   
/*     */   public static class SimpleJsonTestObject
/*     */   {
/* 236 */     private String testString = "TestString";
/* 237 */     private int testInteger = 42;
/* 238 */     private double testDouble = 1.5D;
/* 239 */     private boolean testBoolean = true;
/*     */     
/*     */ 
/*     */ 
/*     */     public String getTestString()
/*     */     {
/* 245 */       return this.testString;
/*     */     }
/*     */     
/*     */     public void setTestString(String testString) {
/* 249 */       this.testString = testString;
/*     */     }
/*     */     
/*     */     public int getTestInteger() {
/* 253 */       return this.testInteger;
/*     */     }
/*     */     
/*     */     public void setTestInteger(int testInteger) {
/* 257 */       this.testInteger = testInteger;
/*     */     }
/*     */     
/*     */     public double getTestDouble() {
/* 261 */       return this.testDouble;
/*     */     }
/*     */     
/*     */     public void setTestDouble(double testDouble) {
/* 265 */       this.testDouble = testDouble;
/*     */     }
/*     */     
/*     */     public boolean isTestBoolean() {
/* 269 */       return this.testBoolean;
/*     */     }
/*     */     
/*     */     public void setTestBoolean(boolean testBoolean) {
/* 273 */       this.testBoolean = testBoolean;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\JavaAPIS\Item-NBT-API.jar!\de\tr7zw\itemnbtapi\ItemNBTAPI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */