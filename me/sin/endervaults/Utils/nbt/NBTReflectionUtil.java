/*     */ package me.sin.endervaults.Utils.nbt;
/*     */ 
/*     */

import org.bukkit.block.BlockState;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.Stack;

/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */

/*     */
/*     */ public class NBTReflectionUtil
/*     */ {
/*  17 */   private static final String version = org.bukkit.Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
/*     */   
/*     */   private static Class getCraftItemStack()
/*     */   {
/*     */     try
/*     */     {
/*  23 */       return Class.forName("org.bukkit.craftbukkit." + version + ".inventory.CraftItemStack");
/*     */     }
/*     */     catch (Exception ex) {
/*  26 */       System.out.println("Error in ItemNBTAPI! (Outdated plugin?)");
/*  27 */       ex.printStackTrace(); }
/*  28 */     return null;
/*     */   }
/*     */   
/*     */   private static Class getCraftEntity()
/*     */   {
/*     */     try
/*     */     {
/*  35 */       return Class.forName("org.bukkit.craftbukkit." + version + ".entity.CraftEntity");
/*     */     }
/*     */     catch (Exception ex) {
/*  38 */       System.out.println("Error in ItemNBTAPI! (Outdated plugin?)");
/*  39 */       ex.printStackTrace(); }
/*  40 */     return null;
/*     */   }
/*     */   
/*     */   protected static Class getNBTBase()
/*     */   {
/*     */     try
/*     */     {
/*  47 */       return Class.forName("net.minecraft.server." + version + ".NBTBase");
/*     */     }
/*     */     catch (Exception ex) {
/*  50 */       System.out.println("Error in ItemNBTAPI! (Outdated plugin?)");
/*  51 */       ex.printStackTrace(); }
/*  52 */     return null;
/*     */   }
/*     */   
/*     */   protected static Class getNBTTagString()
/*     */   {
/*     */     try
/*     */     {
/*  59 */       return Class.forName("net.minecraft.server." + version + ".NBTTagString");
/*     */     }
/*     */     catch (Exception ex) {
/*  62 */       System.out.println("Error in ItemNBTAPI! (Outdated plugin?)");
/*  63 */       ex.printStackTrace(); }
/*  64 */     return null;
/*     */   }
/*     */   
/*     */   protected static Class getNBTTagCompound()
/*     */   {
/*     */     try
/*     */     {
/*  71 */       return Class.forName("net.minecraft.server." + version + ".NBTTagCompound");
/*     */     }
/*     */     catch (Exception ex) {
/*  74 */       System.out.println("Error in ItemNBTAPI! (Outdated plugin?)");
/*  75 */       ex.printStackTrace(); }
/*  76 */     return null;
/*     */   }
/*     */   
/*     */   protected static Class getNBTCompressedStreamTools()
/*     */   {
/*     */     try
/*     */     {
/*  83 */       return Class.forName("net.minecraft.server." + version + ".NBTCompressedStreamTools");
/*     */     }
/*     */     catch (Exception ex) {
/*  86 */       System.out.println("Error in ItemNBTAPI! (Outdated plugin?)");
/*  87 */       ex.printStackTrace(); }
/*  88 */     return null;
/*     */   }
/*     */   
/*     */   protected static Class getTileEntity()
/*     */   {
/*     */     try
/*     */     {
/*  95 */       return Class.forName("net.minecraft.server." + version + ".TileEntity");
/*     */     }
/*     */     catch (Exception ex) {
/*  98 */       System.out.println("Error in ItemNBTAPI! (Outdated plugin?)");
/*  99 */       ex.printStackTrace(); }
/* 100 */     return null;
/*     */   }
/*     */   
/*     */   protected static Class getCraftWorld()
/*     */   {
/*     */     try
/*     */     {
/* 107 */       return Class.forName("org.bukkit.craftbukkit." + version + ".CraftWorld");
/*     */     }
/*     */     catch (Exception ex) {
/* 110 */       System.out.println("Error in ItemNBTAPI! (Outdated plugin?)");
/* 111 */       ex.printStackTrace(); }
/* 112 */     return null;
/*     */   }
/*     */   
/*     */   public static Object getNewNBTTag()
/*     */   {
/* 117 */     String version = org.bukkit.Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
/*     */     try
/*     */     {
/* 120 */       Class c = Class.forName("net.minecraft.server." + version + ".NBTTagCompound");
/* 121 */       return c.newInstance();
/*     */     } catch (Exception ex) {
/* 123 */       System.out.println("Error in ItemNBTAPI! (Outdated plugin?)");
/* 124 */       ex.printStackTrace(); }
/* 125 */     return null;
/*     */   }
/*     */   
/*     */   private static Object getnewBlockPosition(int x, int y, int z)
/*     */   {
/* 130 */     String version = org.bukkit.Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
/*     */     try
/*     */     {
/* 133 */       Class c = Class.forName("net.minecraft.server." + version + ".BlockPosition");
/* 134 */       return c.getConstructor(new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE }).newInstance(new Object[] { Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(z) });
/*     */     } catch (Exception ex) {
/* 136 */       System.out.println("Error in ItemNBTAPI! (Outdated plugin?)");
/* 137 */       ex.printStackTrace(); }
/* 138 */     return null;
/*     */   }
/*     */   
/*     */   public static Object setNBTTag(Object NBTTag, Object NMSItem)
/*     */   {
/*     */     try
/*     */     {
/* 145 */       Method method = NMSItem.getClass().getMethod("setTag", new Class[] { NBTTag.getClass() });
/* 146 */       method.invoke(NMSItem, new Object[] { NBTTag });
/* 147 */       return NMSItem;
/*     */     } catch (Exception ex) {
/* 149 */       ex.printStackTrace();
/*     */     }
/* 151 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public static Object getNMSItemStack(ItemStack item)
/*     */   {
/* 157 */     Class cis = getCraftItemStack();
/*     */     try
/*     */     {
/* 160 */       Method method = cis.getMethod("asNMSCopy", new Class[] { ItemStack.class });
/* 161 */       return method.invoke(cis, new Object[] { item });
/*     */     }
/*     */     catch (Exception e) {
/* 164 */       e.printStackTrace();
/*     */     }
/* 166 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public static Object getNMSEntity(Entity entity)
/*     */   {
/* 172 */     Class cis = getCraftEntity();
/*     */     try
/*     */     {
/* 175 */       Method method = cis.getMethod("getHandle", new Class[0]);
/* 176 */       return method.invoke(getCraftEntity().cast(entity), new Object[0]);
/*     */     } catch (Exception e) {
/* 178 */       e.printStackTrace();
/*     */     }
/* 180 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public static Object readNBTFile(FileInputStream stream)
/*     */   {
/* 186 */     Class cis = getNBTCompressedStreamTools();
/*     */     try
/*     */     {
/* 189 */       Method method = cis.getMethod("a", new Class[] { InputStream.class });
/* 190 */       return method.invoke(cis, new Object[] { stream });
/*     */     } catch (Exception e) {
/* 192 */       e.printStackTrace();
/*     */     }
/* 194 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public static Object saveNBTFile(Object nbt, FileOutputStream stream)
/*     */   {
/* 200 */     Class cis = getNBTCompressedStreamTools();
/*     */     try
/*     */     {
/* 203 */       Method method = cis.getMethod("a", new Class[] { getNBTTagCompound(), java.io.OutputStream.class });
/* 204 */       return method.invoke(cis, new Object[] { nbt, stream });
/*     */     } catch (Exception e) {
/* 206 */       e.printStackTrace();
/*     */     }
/* 208 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public static ItemStack getBukkitItemStack(Object item)
/*     */   {
/* 214 */     Class cis = getCraftItemStack();
/*     */     try
/*     */     {
/* 217 */       Method method = cis.getMethod("asCraftMirror", new Class[] { item.getClass() });
/* 218 */       Object answer = method.invoke(cis, new Object[] { item });
/* 219 */       return (ItemStack)answer;
/*     */     } catch (Exception e) {
/* 221 */       e.printStackTrace();
/*     */     }
/* 223 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public static Object getItemRootNBTTagCompound(Object nmsitem)
/*     */   {
/* 229 */     Class c = nmsitem.getClass();
/*     */     try
/*     */     {
/* 232 */       Method method = c.getMethod("getTag", new Class[0]);
/* 233 */       return method.invoke(nmsitem, new Object[0]);
/*     */     }
/*     */     catch (Exception e) {
/* 236 */       e.printStackTrace();
/*     */     }
/* 238 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public static Object getEntityNBTTagCompound(Object nmsitem)
/*     */   {
/* 244 */     Class c = nmsitem.getClass();
/*     */     try
/*     */     {
/* 247 */       Method method = c.getMethod(MethodNames.getEntitynbtgetterMethodName(), new Class[] { getNBTTagCompound() });
/* 248 */       Object nbt = getNBTTagCompound().newInstance();
/* 249 */       Object answer = method.invoke(nmsitem, new Object[] { nbt });
/* 250 */       if (answer == null) {}
/* 251 */       return nbt;
/*     */     }
/*     */     catch (Exception e) {
/* 254 */       e.printStackTrace();
/*     */     }
/* 256 */     return null;
/*     */   }
/*     */   
/*     */   public static Object setEntityNBTTag(Object NBTTag, Object NMSItem)
/*     */   {
/*     */     try {
/* 262 */       Method method = NMSItem.getClass().getMethod(MethodNames.getEntitynbtsetterMethodName(), new Class[] { getNBTTagCompound() });
/* 263 */       method.invoke(NMSItem, new Object[] { NBTTag });
/* 264 */       return NMSItem;
/*     */     } catch (Exception ex) {
/* 266 */       ex.printStackTrace();
/*     */     }
/* 268 */     return null;
/*     */   }
/*     */   
/*     */   public static Object getTileEntityNBTTagCompound(BlockState tile)
/*     */   {
/*     */     try {
/* 274 */       Object pos = getnewBlockPosition(tile.getX(), tile.getY(), tile.getZ());
/* 275 */       Object cworld = getCraftWorld().cast(tile.getWorld());
/* 276 */       Object nmsworld = cworld.getClass().getMethod("getHandle", new Class[0]).invoke(cworld, new Object[0]);
/* 277 */       Object o = nmsworld.getClass().getMethod("getTileEntity", new Class[] { pos.getClass() }).invoke(nmsworld, new Object[] { pos });
/* 278 */       Method method = getTileEntity().getMethod(MethodNames.getTiledataMethodName(), new Class[] { getNBTTagCompound() });
/* 279 */       Object tag = getNBTTagCompound().newInstance();
/* 280 */       Object answer = method.invoke(o, new Object[] { tag });
/* 281 */       if (answer == null) {}
/* 282 */       return tag;
/*     */     }
/*     */     catch (Exception e) {
/* 285 */       e.printStackTrace();
/*     */     }
/* 287 */     return null;
/*     */   }
/*     */   
/*     */   public static void setTileEntityNBTTagCompound(BlockState tile, Object comp)
/*     */   {
/*     */     try {
/* 293 */       Object pos = getnewBlockPosition(tile.getX(), tile.getY(), tile.getZ());
/* 294 */       Object cworld = getCraftWorld().cast(tile.getWorld());
/* 295 */       Object nmsworld = cworld.getClass().getMethod("getHandle", new Class[0]).invoke(cworld, new Object[0]);
/* 296 */       Object o = nmsworld.getClass().getMethod("getTileEntity", new Class[] { pos.getClass() }).invoke(nmsworld, new Object[] { pos });
/* 297 */       Method method = getTileEntity().getMethod("a", new Class[] { getNBTTagCompound() });
/* 298 */       method.invoke(o, new Object[] { comp });
/*     */     } catch (Exception e) {
/* 300 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static Object getSubNBTTagCompound(Object compound, String name)
/*     */   {
/* 308 */     Class c = compound.getClass();
/*     */     try
/*     */     {
/* 311 */       Method method = c.getMethod("getCompound", new Class[] { String.class });
/* 312 */       return method.invoke(compound, new Object[] { name });
/*     */     }
/*     */     catch (Exception e) {
/* 315 */       e.printStackTrace();
/*     */     }
/* 317 */     return null;
/*     */   }
/*     */   
/*     */   public static void addNBTTagCompound(NBTCompound comp, String name) {
/* 321 */     if (name == null) {
/* 322 */       remove(comp, name);
/* 323 */       return;
/*     */     }
/* 325 */     Object nbttag = comp.getCompound();
/* 326 */     if (nbttag == null) {
/* 327 */       nbttag = getNewNBTTag();
/*     */     }
/* 329 */     if (!valideCompound(comp).booleanValue()) return;
/* 330 */     Object workingtag = gettoCompount(nbttag, comp);
/*     */     try
/*     */     {
/* 333 */       Method method = workingtag.getClass().getMethod("set", new Class[] { String.class, getNBTBase() });
/* 334 */       method.invoke(workingtag, new Object[] { name, getNBTTagCompound().newInstance() });
/* 335 */       comp.setCompound(nbttag);
/* 336 */       return;
/*     */     } catch (Exception ex) {
/* 338 */       ex.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public static Boolean valideCompound(NBTCompound comp)
/*     */   {
/* 344 */     Object root = comp.getCompound();
/* 345 */     if (root == null) {
/* 346 */       root = getNewNBTTag();
/*     */     }
/* 348 */     if (gettoCompount(root, comp) != null) return Boolean.valueOf(true); return Boolean.valueOf(false);
/*     */   }
/*     */   
/*     */   private static Object gettoCompount(Object nbttag, NBTCompound comp) {
/* 352 */     Stack<String> structure = new Stack();
/* 353 */     while (comp.getParent() != null) {
/* 354 */       structure.add(comp.getName());
/* 355 */       comp = comp.getParent();
/*     */     }
/* 357 */     while (!structure.isEmpty()) {
/* 358 */       nbttag = getSubNBTTagCompound(nbttag, (String)structure.pop());
/* 359 */       if (nbttag == null) {
/* 360 */         return null;
/*     */       }
/*     */     }
/* 363 */     return nbttag;
/*     */   }
/*     */   
/*     */   public static void setString(NBTCompound comp, String key, String text) {
/* 367 */     if (text == null) {
/* 368 */       remove(comp, key);
/* 369 */       return;
/*     */     }
/* 371 */     Object rootnbttag = comp.getCompound();
/* 372 */     if (rootnbttag == null) {
/* 373 */       rootnbttag = getNewNBTTag();
/*     */     }
/* 375 */     if (!valideCompound(comp).booleanValue()) return;
/* 376 */     Object workingtag = gettoCompount(rootnbttag, comp);
/*     */     try
/*     */     {
/* 379 */       Method method = workingtag.getClass().getMethod("setString", new Class[] { String.class, String.class });
/* 380 */       method.invoke(workingtag, new Object[] { key, text });
/* 381 */       comp.setCompound(rootnbttag);
/*     */     } catch (Exception ex) {
/* 383 */       ex.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public static String getString(NBTCompound comp, String key) {
/* 388 */     Object rootnbttag = comp.getCompound();
/* 389 */     if (rootnbttag == null) {
/* 390 */       rootnbttag = getNewNBTTag();
/*     */     }
/* 392 */     if (!valideCompound(comp).booleanValue()) return null;
/* 393 */     Object workingtag = gettoCompount(rootnbttag, comp);
/*     */     try
/*     */     {
/* 396 */       Method method = workingtag.getClass().getMethod("getString", new Class[] { String.class });
/* 397 */       return (String)method.invoke(workingtag, new Object[] { key });
/*     */     } catch (Exception ex) {
/* 399 */       ex.printStackTrace();
/*     */     }
/* 401 */     return null;
/*     */   }
/*     */   
/*     */   public static String getContent(NBTCompound comp, String key) {
/* 405 */     Object rootnbttag = comp.getCompound();
/* 406 */     if (rootnbttag == null) {
/* 407 */       rootnbttag = getNewNBTTag();
/*     */     }
/* 409 */     if (!valideCompound(comp).booleanValue()) return null;
/* 410 */     Object workingtag = gettoCompount(rootnbttag, comp);
/*     */     try
/*     */     {
/* 413 */       Method method = workingtag.getClass().getMethod("get", new Class[] { String.class });
/* 414 */       return method.invoke(workingtag, new Object[] { key }).toString();
/*     */     } catch (Exception ex) {
/* 416 */       ex.printStackTrace();
/*     */     }
/* 418 */     return null;
/*     */   }
/*     */   
/*     */   public static void setInt(NBTCompound comp, String key, Integer i) {
/* 422 */     if (i == null) {
/* 423 */       remove(comp, key);
/* 424 */       return;
/*     */     }
/* 426 */     Object rootnbttag = comp.getCompound();
/* 427 */     if (rootnbttag == null) {
/* 428 */       rootnbttag = getNewNBTTag();
/*     */     }
/* 430 */     if (!valideCompound(comp).booleanValue()) return;
/* 431 */     Object workingtag = gettoCompount(rootnbttag, comp);
/*     */     try
/*     */     {
/* 434 */       Method method = workingtag.getClass().getMethod("setInt", new Class[] { String.class, Integer.TYPE });
/* 435 */       method.invoke(workingtag, new Object[] { key, i });
/* 436 */       comp.setCompound(rootnbttag);
/*     */     } catch (Exception ex) {
/* 438 */       ex.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public static Integer getInt(NBTCompound comp, String key) {
/* 443 */     Object rootnbttag = comp.getCompound();
/* 444 */     if (rootnbttag == null) {
/* 445 */       rootnbttag = getNewNBTTag();
/*     */     }
/* 447 */     if (!valideCompound(comp).booleanValue()) return null;
/* 448 */     Object workingtag = gettoCompount(rootnbttag, comp);
/*     */     try
/*     */     {
/* 451 */       Method method = workingtag.getClass().getMethod("getInt", new Class[] { String.class });
/* 452 */       return (Integer)method.invoke(workingtag, new Object[] { key });
/*     */     } catch (Exception ex) {
/* 454 */       ex.printStackTrace();
/*     */     }
/* 456 */     return null;
/*     */   }
/*     */   
/*     */   public static void setByteArray(NBTCompound comp, String key, byte[] b) {
/* 460 */     if (b == null) {
/* 461 */       remove(comp, key);
/* 462 */       return;
/*     */     }
/* 464 */     Object rootnbttag = comp.getCompound();
/* 465 */     if (rootnbttag == null) {
/* 466 */       rootnbttag = getNewNBTTag();
/*     */     }
/* 468 */     if (!valideCompound(comp).booleanValue()) return;
/* 469 */     Object workingtag = gettoCompount(rootnbttag, comp);
/*     */     try
/*     */     {
/* 472 */       Method method = workingtag.getClass().getMethod("setByteArray", new Class[] { String.class, byte[].class });
/* 473 */       method.invoke(workingtag, new Object[] { key, b });
/* 474 */       comp.setCompound(rootnbttag);
/*     */     } catch (Exception ex) {
/* 476 */       ex.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public static byte[] getByteArray(NBTCompound comp, String key)
/*     */   {
/* 482 */     Object rootnbttag = comp.getCompound();
/* 483 */     if (rootnbttag == null) {
/* 484 */       rootnbttag = getNewNBTTag();
/*     */     }
/* 486 */     if (!valideCompound(comp).booleanValue()) return null;
/* 487 */     Object workingtag = gettoCompount(rootnbttag, comp);
/*     */     try
/*     */     {
/* 490 */       Method method = workingtag.getClass().getMethod("getByteArray", new Class[] { String.class });
/* 491 */       return (byte[])method.invoke(workingtag, new Object[] { key });
/*     */     } catch (Exception ex) {
/* 493 */       ex.printStackTrace();
/*     */     }
/* 495 */     return null;
/*     */   }
/*     */   
/*     */   public static void setIntArray(NBTCompound comp, String key, int[] i) {
/* 499 */     if (i == null) {
/* 500 */       remove(comp, key);
/* 501 */       return;
/*     */     }
/* 503 */     Object rootnbttag = comp.getCompound();
/* 504 */     if (rootnbttag == null) {
/* 505 */       rootnbttag = getNewNBTTag();
/*     */     }
/* 507 */     if (!valideCompound(comp).booleanValue()) return;
/* 508 */     Object workingtag = gettoCompount(rootnbttag, comp);
/*     */     try
/*     */     {
/* 511 */       Method method = workingtag.getClass().getMethod("setIntArray", new Class[] { String.class, int[].class });
/* 512 */       method.invoke(workingtag, new Object[] { key, i });
/* 513 */       comp.setCompound(rootnbttag);
/*     */     } catch (Exception ex) {
/* 515 */       ex.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public static int[] getIntArray(NBTCompound comp, String key) {
/* 520 */     Object rootnbttag = comp.getCompound();
/* 521 */     if (rootnbttag == null) {
/* 522 */       rootnbttag = getNewNBTTag();
/*     */     }
/* 524 */     if (!valideCompound(comp).booleanValue()) return null;
/* 525 */     Object workingtag = gettoCompount(rootnbttag, comp);
/*     */     try
/*     */     {
/* 528 */       Method method = workingtag.getClass().getMethod("getIntArray", new Class[] { String.class });
/* 529 */       return (int[])method.invoke(workingtag, new Object[] { key });
/*     */     } catch (Exception ex) {
/* 531 */       ex.printStackTrace();
/*     */     }
/* 533 */     return null;
/*     */   }
/*     */   
/*     */   public static void setFloat(NBTCompound comp, String key, Float f) {
/* 537 */     if (f == null) {
/* 538 */       remove(comp, key);
/* 539 */       return;
/*     */     }
/* 541 */     Object rootnbttag = comp.getCompound();
/* 542 */     if (rootnbttag == null) {
/* 543 */       rootnbttag = getNewNBTTag();
/*     */     }
/* 545 */     if (!valideCompound(comp).booleanValue()) return;
/* 546 */     Object workingtag = gettoCompount(rootnbttag, comp);
/*     */     try
/*     */     {
/* 549 */       Method method = workingtag.getClass().getMethod("setFloat", new Class[] { String.class, Float.TYPE });
/* 550 */       method.invoke(workingtag, new Object[] { key, Float.valueOf(f.floatValue()) });
/* 551 */       comp.setCompound(rootnbttag);
/*     */     } catch (Exception ex) {
/* 553 */       ex.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public static Float getFloat(NBTCompound comp, String key) {
/* 558 */     Object rootnbttag = comp.getCompound();
/* 559 */     if (rootnbttag == null) {
/* 560 */       rootnbttag = getNewNBTTag();
/*     */     }
/* 562 */     if (!valideCompound(comp).booleanValue()) return null;
/* 563 */     Object workingtag = gettoCompount(rootnbttag, comp);
/*     */     try
/*     */     {
/* 566 */       Method method = workingtag.getClass().getMethod("getFloat", new Class[] { String.class });
/* 567 */       return (Float)method.invoke(workingtag, new Object[] { key });
/*     */     } catch (Exception ex) {
/* 569 */       ex.printStackTrace();
/*     */     }
/* 571 */     return null;
/*     */   }
/*     */   
/*     */   public static void setLong(NBTCompound comp, String key, Long f) {
/* 575 */     if (f == null) {
/* 576 */       remove(comp, key);
/* 577 */       return;
/*     */     }
/* 579 */     Object rootnbttag = comp.getCompound();
/* 580 */     if (rootnbttag == null) {
/* 581 */       rootnbttag = getNewNBTTag();
/*     */     }
/* 583 */     if (!valideCompound(comp).booleanValue()) return;
/* 584 */     Object workingtag = gettoCompount(rootnbttag, comp);
/*     */     try
/*     */     {
/* 587 */       Method method = workingtag.getClass().getMethod("setLong", new Class[] { String.class, Long.TYPE });
/* 588 */       method.invoke(workingtag, new Object[] { key, Long.valueOf(f.longValue()) });
/* 589 */       comp.setCompound(rootnbttag);
/*     */     } catch (Exception ex) {
/* 591 */       ex.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public static Long getLong(NBTCompound comp, String key) {
/* 596 */     Object rootnbttag = comp.getCompound();
/* 597 */     if (rootnbttag == null) {
/* 598 */       rootnbttag = getNewNBTTag();
/*     */     }
/* 600 */     if (!valideCompound(comp).booleanValue()) return null;
/* 601 */     Object workingtag = gettoCompount(rootnbttag, comp);
/*     */     try
/*     */     {
/* 604 */       Method method = workingtag.getClass().getMethod("getLong", new Class[] { String.class });
/* 605 */       return (Long)method.invoke(workingtag, new Object[] { key });
/*     */     } catch (Exception ex) {
/* 607 */       ex.printStackTrace();
/*     */     }
/* 609 */     return null;
/*     */   }
/*     */   
/*     */   public static void setShort(NBTCompound comp, String key, Short f) {
/* 613 */     if (f == null) {
/* 614 */       remove(comp, key);
/* 615 */       return;
/*     */     }
/* 617 */     Object rootnbttag = comp.getCompound();
/* 618 */     if (rootnbttag == null) {
/* 619 */       rootnbttag = getNewNBTTag();
/*     */     }
/* 621 */     if (!valideCompound(comp).booleanValue()) return;
/* 622 */     Object workingtag = gettoCompount(rootnbttag, comp);
/*     */     try
/*     */     {
/* 625 */       Method method = workingtag.getClass().getMethod("setShort", new Class[] { String.class, Short.TYPE });
/* 626 */       method.invoke(workingtag, new Object[] { key, Short.valueOf(f.shortValue()) });
/* 627 */       comp.setCompound(rootnbttag);
/*     */     } catch (Exception ex) {
/* 629 */       ex.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public static Short getShort(NBTCompound comp, String key) {
/* 634 */     Object rootnbttag = comp.getCompound();
/* 635 */     if (rootnbttag == null) {
/* 636 */       rootnbttag = getNewNBTTag();
/*     */     }
/* 638 */     if (!valideCompound(comp).booleanValue()) return null;
/* 639 */     Object workingtag = gettoCompount(rootnbttag, comp);
/*     */     try
/*     */     {
/* 642 */       Method method = workingtag.getClass().getMethod("getShort", new Class[] { String.class });
/* 643 */       return (Short)method.invoke(workingtag, new Object[] { key });
/*     */     } catch (Exception ex) {
/* 645 */       ex.printStackTrace();
/*     */     }
/* 647 */     return null;
/*     */   }
/*     */   
/*     */   public static void setByte(NBTCompound comp, String key, Byte f) {
/* 651 */     if (f == null) {
/* 652 */       remove(comp, key);
/* 653 */       return;
/*     */     }
/* 655 */     Object rootnbttag = comp.getCompound();
/* 656 */     if (rootnbttag == null) {
/* 657 */       rootnbttag = getNewNBTTag();
/*     */     }
/* 659 */     if (!valideCompound(comp).booleanValue()) return;
/* 660 */     Object workingtag = gettoCompount(rootnbttag, comp);
/*     */     try
/*     */     {
/* 663 */       Method method = workingtag.getClass().getMethod("setByte", new Class[] { String.class, Byte.TYPE });
/* 664 */       method.invoke(workingtag, new Object[] { key, Byte.valueOf(f.byteValue()) });
/* 665 */       comp.setCompound(rootnbttag);
/*     */     } catch (Exception ex) {
/* 667 */       ex.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public static Byte getByte(NBTCompound comp, String key) {
/* 672 */     Object rootnbttag = comp.getCompound();
/* 673 */     if (rootnbttag == null) {
/* 674 */       rootnbttag = getNewNBTTag();
/*     */     }
/* 676 */     if (!valideCompound(comp).booleanValue()) return null;
/* 677 */     Object workingtag = gettoCompount(rootnbttag, comp);
/*     */     try
/*     */     {
/* 680 */       Method method = workingtag.getClass().getMethod("getByte", new Class[] { String.class });
/* 681 */       return (Byte)method.invoke(workingtag, new Object[] { key });
/*     */     } catch (Exception ex) {
/* 683 */       ex.printStackTrace();
/*     */     }
/* 685 */     return null;
/*     */   }
/*     */   
/*     */   public static void setDouble(NBTCompound comp, String key, Double d) {
/* 689 */     if (d == null) {
/* 690 */       remove(comp, key);
/* 691 */       return;
/*     */     }
/* 693 */     Object rootnbttag = comp.getCompound();
/* 694 */     if (rootnbttag == null) {
/* 695 */       rootnbttag = getNewNBTTag();
/*     */     }
/* 697 */     if (!valideCompound(comp).booleanValue()) return;
/* 698 */     Object workingtag = gettoCompount(rootnbttag, comp);
/*     */     try
/*     */     {
/* 701 */       Method method = workingtag.getClass().getMethod("setDouble", new Class[] { String.class, Double.TYPE });
/* 702 */       method.invoke(workingtag, new Object[] { key, d });
/* 703 */       comp.setCompound(rootnbttag);
/*     */     } catch (Exception ex) {
/* 705 */       ex.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public static Double getDouble(NBTCompound comp, String key) {
/* 710 */     Object rootnbttag = comp.getCompound();
/* 711 */     if (rootnbttag == null) {
/* 712 */       rootnbttag = getNewNBTTag();
/*     */     }
/* 714 */     if (!valideCompound(comp).booleanValue()) return null;
/* 715 */     Object workingtag = gettoCompount(rootnbttag, comp);
/*     */     try
/*     */     {
/* 718 */       Method method = workingtag.getClass().getMethod("getDouble", new Class[] { String.class });
/* 719 */       return (Double)method.invoke(workingtag, new Object[] { key });
/*     */     } catch (Exception ex) {
/* 721 */       ex.printStackTrace();
/*     */     }
/* 723 */     return null;
/*     */   }
/*     */   
/*     */   public static byte getType(NBTCompound comp, String key) {
/* 727 */     Object rootnbttag = comp.getCompound();
/* 728 */     if (rootnbttag == null) {
/* 729 */       rootnbttag = getNewNBTTag();
/*     */     }
/* 731 */     if (!valideCompound(comp).booleanValue()) return 0;
/* 732 */     Object workingtag = gettoCompount(rootnbttag, comp);
/*     */     try
/*     */     {
/* 735 */       Method method = workingtag.getClass().getMethod(MethodNames.getTypeMethodName(), new Class[] { String.class });
/* 736 */       return ((Byte)method.invoke(workingtag, new Object[] { key })).byteValue();
/*     */     } catch (Exception ex) {
/* 738 */       ex.printStackTrace();
/*     */     }
/* 740 */     return 0;
/*     */   }
/*     */   
/*     */   public static void setBoolean(NBTCompound comp, String key, Boolean d) {
/* 744 */     if (d == null) {
/* 745 */       remove(comp, key);
/* 746 */       return;
/*     */     }
/* 748 */     Object rootnbttag = comp.getCompound();
/* 749 */     if (rootnbttag == null) {
/* 750 */       rootnbttag = getNewNBTTag();
/*     */     }
/* 752 */     if (!valideCompound(comp).booleanValue()) return;
/* 753 */     Object workingtag = gettoCompount(rootnbttag, comp);
/*     */     try
/*     */     {
/* 756 */       Method method = workingtag.getClass().getMethod("setBoolean", new Class[] { String.class, Boolean.TYPE });
/* 757 */       method.invoke(workingtag, new Object[] { key, d });
/* 758 */       comp.setCompound(rootnbttag);
/*     */     } catch (Exception ex) {
/* 760 */       ex.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public static Boolean getBoolean(NBTCompound comp, String key) {
/* 765 */     Object rootnbttag = comp.getCompound();
/* 766 */     if (rootnbttag == null) {
/* 767 */       rootnbttag = getNewNBTTag();
/*     */     }
/* 769 */     if (!valideCompound(comp).booleanValue()) return null;
/* 770 */     Object workingtag = gettoCompount(rootnbttag, comp);
/*     */     try
/*     */     {
/* 773 */       Method method = workingtag.getClass().getMethod("getBoolean", new Class[] { String.class });
/* 774 */       return (Boolean)method.invoke(workingtag, new Object[] { key });
/*     */     } catch (Exception ex) {
/* 776 */       ex.printStackTrace();
/*     */     }
/* 778 */     return null;
/*     */   }
/*     */   
/*     */   public static void set(NBTCompound comp, String key, Object val) {
/* 782 */     if (val == null) {
/* 783 */       remove(comp, key);
/* 784 */       return;
/*     */     }
/* 786 */     Object rootnbttag = comp.getCompound();
/* 787 */     if (rootnbttag == null) {
/* 788 */       rootnbttag = getNewNBTTag();
/*     */     }
/* 790 */     if (!valideCompound(comp).booleanValue()) {
/* 791 */       new Throwable("InvalideCompound").printStackTrace();
/* 792 */       return;
/*     */     }
/* 794 */     Object workingtag = gettoCompount(rootnbttag, comp);
/*     */     try
/*     */     {
/* 797 */       Method method = workingtag.getClass().getMethod("set", new Class[] { String.class, getNBTBase() });
/* 798 */       method.invoke(workingtag, new Object[] { key, val });
/* 799 */       comp.setCompound(rootnbttag);
/*     */     } catch (Exception ex) {
/* 801 */       ex.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public static NBTList getList(NBTCompound comp, String key, NBTType type) {
/* 806 */     Object rootnbttag = comp.getCompound();
/* 807 */     if (rootnbttag == null) {
/* 808 */       rootnbttag = getNewNBTTag();
/*     */     }
/* 810 */     if (!valideCompound(comp).booleanValue()) return null;
/* 811 */     Object workingtag = gettoCompount(rootnbttag, comp);
/*     */     try
/*     */     {
/* 814 */       Method method = workingtag.getClass().getMethod("getList", new Class[] { String.class, Integer.TYPE });
/* 815 */       return new NBTList(comp, key, type, method.invoke(workingtag, new Object[] { key, Integer.valueOf(type.getId()) }));
/*     */     } catch (Exception ex) {
/* 817 */       ex.printStackTrace();
/*     */     }
/* 819 */     return null;
/*     */   }
/*     */   
/*     */   public static void setObject(NBTCompound comp, String key, Object value) {
/* 823 */     if (!MinecraftVersion.hasGson()) return;
/*     */     try {
/* 825 */       String json = GsonWrapper.getString(value);
/* 826 */       setString(comp, key, json);
/*     */     } catch (Exception ex) {
/* 828 */       ex.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public static <T> T getObject(NBTCompound comp, String key, Class<T> type) {
/* 833 */     if (!MinecraftVersion.hasGson()) return null;
/* 834 */     String json = getString(comp, key);
/* 835 */     if (json == null) {
/* 836 */       return null;
/*     */     }
/* 838 */     return (T)GsonWrapper.deserializeJson(json, type);
/*     */   }
/*     */   
/*     */   public static void remove(NBTCompound comp, String key) {
/* 842 */     Object rootnbttag = comp.getCompound();
/* 843 */     if (rootnbttag == null) {
/* 844 */       rootnbttag = getNewNBTTag();
/*     */     }
/* 846 */     if (!valideCompound(comp).booleanValue()) return;
/* 847 */     Object workingtag = gettoCompount(rootnbttag, comp);
/*     */     try
/*     */     {
/* 850 */       Method method = workingtag.getClass().getMethod("remove", new Class[] { String.class });
/* 851 */       method.invoke(workingtag, new Object[] { key });
/* 852 */       comp.setCompound(rootnbttag);
/*     */     } catch (Exception ex) {
/* 854 */       ex.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public static Boolean hasKey(NBTCompound comp, String key) {
/* 859 */     Object rootnbttag = comp.getCompound();
/* 860 */     if (rootnbttag == null) {
/* 861 */       rootnbttag = getNewNBTTag();
/*     */     }
/* 863 */     if (!valideCompound(comp).booleanValue()) return null;
/* 864 */     Object workingtag = gettoCompount(rootnbttag, comp);
/*     */     try
/*     */     {
/* 867 */       Method method = workingtag.getClass().getMethod("hasKey", new Class[] { String.class });
/* 868 */       return (Boolean)method.invoke(workingtag, new Object[] { key });
/*     */     } catch (Exception ex) {
/* 870 */       ex.printStackTrace();
/*     */     }
/* 872 */     return null;
/*     */   }
/*     */   
/*     */   public static Set<String> getKeys(NBTCompound comp)
/*     */   {
/* 877 */     Object rootnbttag = comp.getCompound();
/* 878 */     if (rootnbttag == null) {
/* 879 */       rootnbttag = getNewNBTTag();
/*     */     }
/* 881 */     if (!valideCompound(comp).booleanValue()) return null;
/* 882 */     Object workingtag = gettoCompount(rootnbttag, comp);
/*     */     try
/*     */     {
/* 885 */       Method method = workingtag.getClass().getMethod("c", new Class[0]);
/* 886 */       return (Set)method.invoke(workingtag, new Object[0]);
/*     */     } catch (Exception ex) {
/* 888 */       ex.printStackTrace();
/*     */     }
/* 890 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\JavaAPIS\Item-NBT-API.jar!\de\tr7zw\itemnbtapi\NBTReflectionUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */