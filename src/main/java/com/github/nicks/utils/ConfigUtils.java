package com.github.nicks.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings("all")
public class ConfigUtils {

    private FileConfiguration config;
    private File file;
    private Plugin plugin;
    private String name;



    /**
     * 메인에서 콘피그를 생성시켜주는 역할을 한다.
     *
     * @param name 콘피그 이름 입력
     */
    public ConfigUtils(String name, Plugin plugin) {
        this.name = name;
        this.plugin = plugin;
    }


    /**
     * 메인에서 콘피그 정보를 입력후, Load 시킨다.
     */
    public void loadDefaultConfig() {
        File file = new File(plugin.getDataFolder(), this.name + ".yml");
        if (!file.exists()) {
            plugin.saveResource(this.name + ".yml", false);
        }
    }


    /**
     * 콘피그 저장
     */
    public boolean saveConfig() {
        if (this.config == null || this.file == null) return false;
        try {
            getConfig().save(this.file);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 콘피그 리로드
     */
    public void reloadConfig() {
        this.file = new File(plugin.getDataFolder(), name + ".yml");
        this.config = YamlConfiguration.loadConfiguration(this.file);

        InputStream inputStream = plugin.getResource(name + ".yml");
        if (inputStream != null) {
            YamlConfiguration config = YamlConfiguration.loadConfiguration(new InputStreamReader(inputStream));
            this.config.setDefaults(config);
        }
    }


    /**
     * 콘피그를 가져올 때
     */
    public FileConfiguration getConfig() {
        if (this.config == null) reloadConfig();
        return config;
    }


    /**
     * 콘피그 파일이 있고 없음을 알려줌.
     */
    public boolean isFileExist() {
        this.file = new File(plugin.getDataFolder(), name + ".yml");
        return this.file.exists();
    }


    /**
     * 콘피그 파일 삭제
     */
    public void deleteFile() {
        this.file = new File(plugin.getDataFolder(), name + ".yml");
        this.file.delete();
    }


    /**
     * 콘피그 파일 목록을 불러올 수 있다.
     * @return 값 리턴
     */
    public List<String> fileListName() {
        this.file = new File(plugin.getDataFolder(), name);
        ArrayList<String> newArray = new ArrayList<>();
        File[] list = this.file.listFiles();
        for(File file : list) {
            if(file != null) {
                String name = file.getName();
                name = name.replaceAll(".yml", "");
                newArray.add(name);
            }
        }
        return newArray;
    }


    /**
     * String형으로 저장
     * @param path 경로
     * @param value 값
     */
    public void setString(String path, String value) {
        getConfig().set(path, value);
        saveConfig();
    }


    /**
     * Integer형으로 저장
     * @param path 경로
     * @param integer 값
     */
    public void setInteger(String path, int integer) {
        getConfig().set(path, integer);
        saveConfig();
    }


    /**
     * Boolean형으로 저장
     * @param path 경로
     * @param Boolean true & fase
     */
    public void setBoolean(String path, boolean Boolean) {
        getConfig().set(path, Boolean);
        this.saveConfig();
    }


    /**
     * Long형으로 저장
     * @param path 경로
     * @param value 값
     */
    public void setLong(String path, Long value) {
        getConfig().set(path, value);
        saveConfig();
    }


    /**
     * Float형으로 저장
     * @param path 경로
     * @param value 값
     */
    public void setFloat(String path, float value) {
        getConfig().set(path, value);
        saveConfig();
    }


    /**
     * Double형으로 저장
     * @param path 경로
     * @param value 값
     */
    public void setDouble(String path, double value) {

        getConfig().set(path, value);
        this.saveConfig();
    }


    /**
     * Float형 값을 불러옵니다.
     * @param path 경로
     * @return
     */
    public float getFloat(String path) {
        return (float) getConfig().get(path);
    }


    /**
     * object 지정
     * @param path 경로
     * @param value 값
     */
    public void setObject(String path, Object value) {
        getConfig().set(path, value);
        this.saveConfig();
    }


    /**
     * String형 값을 불러옵니다.
     * @param path 경로
     * @return
     */
    public String getString(String path) {
        return getConfig().getString(path);
    }


    /**
     * Integer형 값을 불러옵니다.
     * @param path 경로
     * @return
     */
    public int getInteger(String path) {
        return getConfig().getInt(path);
    }


    /**
     * Boolean형 값을 불러옵니다.
     * @param path 경로
     * @return
     */
    public double getDouble(String path) {
        return getConfig().getDouble(path);
    }


    /**
     * Long형 값을 불러옵니다.
     * @param path 경로
     * @return
     */
    public boolean getBoolean(String path) {
        return getConfig().getBoolean(path);
    }


    /**
     * Long형 값을 불러옵니다.
     * @param path 경로
     * @return
     */
    public long getLong(String path) {
        return getConfig().getLong(path);
    }


    /**
     * 해당 폴더에 존재하는 파일의 목록을 불러옵니다.
     * @return
     */
    public File[] getFileList() {
        this.file = new File(plugin.getDataFolder(), name);
        return this.file.listFiles();
    }


    /**
     * 인벤토리를 저장합니다.
     * @param path
     * @param inv
     */
    public void setInventory(String path, Inventory inv) {
        for (HumanEntity viewers : inv.getViewers()) {
            InventoryView OpenInv = viewers.getOpenInventory();
            if (OpenInv.getTopInventory().equals(inv) && !inv.getType().equals(InventoryType.CRAFTING)) {
                getConfig().set(path + ".inv.title", OpenInv.getTitle());
                getConfig().set(path + ".inv.size", inv.getSize());

                List<ItemStack> itemStacks = new ArrayList<>();

                for (int i = 0; i < inv.getSize(); i++) {
                    ItemStack item = inv.getItem(i);
                    if (item != null) {
                        if (getConfig().get(path + ".inv.items." + i + ".data") != null) {
                            ConfigurationSection data = getConfig().getConfigurationSection(path + ".inv.items." + i + ".data");
                            if (data != null) {
                                for (String key : data.getKeys(false)) {
                                    ItemMeta meta = item.getItemMeta();
                                    PersistentDataContainer pdc = meta.getPersistentDataContainer();
                                    NamespacedKey namespacedKey = new NamespacedKey(plugin, key);

                                    if (pdc.has(namespacedKey, PersistentDataType.LONG)) {
                                        pdc.set(namespacedKey, PersistentDataType.LONG, data.getLong(key));
                                        item.setItemMeta(meta);
                                        itemStacks.add(item);
                                    }
                                }
                            }
                        }
                    }
                }
                ConfigurationSection newSection = getConfig().createSection(path + ".inv.items");

                List<ItemStack> items = new ArrayList<>();

                for (int i = 0; i < inv.getSize(); i++) {
                    ItemStack item = inv.getItem(i);

                    if (item != null) {
                        items.add(item);
                        setItemStack(path, item, i, i);
                    }
                }
            }
        }
        this.saveConfig();
    }


    /**
     * 아이템 저장
     * @param path
     * @param item
     * @param i
     * @param index
     */
    public void setItemStack(String path, ItemStack item, int i, int index) {
        ConfigurationSection section = getConfig().createSection(path + ".inv.items." + index);
        section.set("slot", i);
        setItemStack(path, item, section);
    }


    /**
     * 아이템 저장
     * @param path
     * @param value
     * @param section
     */
    public void setItemStack(String path, ItemStack value, ConfigurationSection section) {
        Objects.requireNonNull(section, "section 변수가 없습니다! 확인해 주세요! 에러가 발생된 메소드 : #setItemStack, section=?");

        section.set("Material", value.getType().name());
        section.set("Amount", value.getAmount());
        section.set("Durability", value.getDurability());


        if (value != null) {
            ItemMeta meta = value.getItemMeta();
            PersistentDataContainer data = meta.getPersistentDataContainer();
            if (data != null) {
                for (NamespacedKey key : data.getKeys()) {
                    long test = data.get(key, PersistentDataType.LONG);
                    section.set("data." + key.getKey(), test);
                }
            }
        }


        if (!value.getType().equals(Material.ENCHANTED_BOOK)) {
            if (value.hasItemMeta()) {
                ItemMeta meta = value.getItemMeta();
                assert meta != null;
                if (meta.hasDisplayName())
                    section.set("Meta.Display", meta.getDisplayName());

                if (meta.hasCustomModelData())
                    section.set("Meta.CustomModelData", meta.getCustomModelData());

                if (meta.hasLore()) {
                    List<String> lore = meta.getLore();
                    section.set("Meta.Lore", lore);

                }
                Map<Enchantment, Integer> enchants = meta.getEnchants();

                for (Enchantment enchantment : value.getEnchantments().keySet()) {
                    int level = enchants.get(enchantment);
                    section.set("Enchant." + enchantment.getName(), level);
                }
            }
        } else {


            EnchantmentStorageMeta enchantMeta = (EnchantmentStorageMeta) value.getItemMeta();

            Map<Enchantment, Integer> enchants = enchantMeta.getStoredEnchants();

            if (enchants != null) {
                for (Enchantment enchantment : enchants.keySet()) {
                    int level = enchants.get(enchantment);
                    section.set("Enchant." + enchantment.getName(), level);
                }
            }
        }
        this.saveConfig();
    }

    public Inventory getInventory(String path) {

        if (getConfig().get(path + ".inv.size") != null && getConfig().get(path + ".inv.title") != null) {

            Inventory inv = Bukkit.createInventory(null, (Integer) getConfig().get(path + ".inv.size"), (String) getConfig().get(path + ".inv.title"));

            ConfigurationSection section = getConfig().getConfigurationSection(path + ".inv.items");
            ConfigurationSection metaData = section.getConfigurationSection("Meta");

            for (int i = 0; i < inv.getSize(); i++) {
                String materialName = section.getString(i + ".Material");

                String displayName = section.getString(i + ".Meta.Display");

                List<String> lore = section.getStringList(i + ".Meta.Lore");

                if (materialName != null) {

                    Material material = Material.valueOf(materialName);
                    if (material.equals(Material.ENCHANTED_BOOK)) {
                        int slot = section.getInt(i + ".slot");
                        int amount = section.getInt(i + ".Amount");

                        ItemStack item = new ItemStack(Material.ENCHANTED_BOOK, amount);
                        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();

                        if (displayName != null) {
                            meta.setDisplayName(displayName);
                        } else if (lore != null) {
                            meta.setLore(lore);
                        }

                        int durability = section.getInt(i + ".Durability");

                        section = section.getConfigurationSection(i + ".Enchant");

                        if (section != null) {
                            for (String key : section.getKeys(false)) {
                                Enchantment enchantment = Enchantment.getByName(key);
                                meta.addStoredEnchant(enchantment, section.getInt(key), false);
                            }
                        }

                        ConfigurationSection data = section.getConfigurationSection(i + ".data");
                        if (data != null) {
                            PersistentDataContainer pdc = meta.getPersistentDataContainer();
                            for (String key : data.getKeys(false)) {
                                long value = data.getLong(key);
                                NamespacedKey name = new NamespacedKey(plugin, key);
                                pdc.set(name, PersistentDataType.LONG, value);
                            }
                        }

                        item.setItemMeta(meta);
                        inv.setItem(slot, item);
                    } else {
                        int slot = section.getInt(i + ".slot");
                        int amount = section.getInt(i + ".Amount");

                        ItemStack item = new ItemStack(material, amount);
                        ItemMeta meta = item.getItemMeta();

                        if (displayName != null) {
                            meta.setDisplayName(displayName);
                        }
                        meta.setLore(lore);
                        int durability = section.getInt(i + ".Durability");

                        ConfigurationSection data = section.getConfigurationSection(i + ".data");
                        if (data != null) {
                            PersistentDataContainer pdc = meta.getPersistentDataContainer();
                            for (String key : data.getKeys(false)) {
                                long value = data.getLong(key);
                                NamespacedKey name = new NamespacedKey(plugin, key);
                                pdc.set(name, PersistentDataType.LONG, value);
                            }
                        }
                        item.setItemMeta(meta);

                        section = section.getConfigurationSection(i + ".Enchant");

                        if (section != null) {
                            for (String key : section.getKeys(false)) {
                                Enchantment enchantment = Enchantment.getByName(key);
                                item.addUnsafeEnchantment(enchantment, section.getInt(key));
                            }
                        }


                        inv.setItem(slot, item);
                    }
                    section = getConfig().getConfigurationSection(path + ".inv.items");
                }
            }
            return inv;
        }
        return null;
    }
}
