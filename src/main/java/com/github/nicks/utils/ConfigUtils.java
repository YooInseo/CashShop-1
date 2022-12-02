package com.github.nicks.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
     * Float형 값을 불러올 때
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
     * String형 값을 불러올 때
     * @param path 경로
     * @return
     */
    public String getString(String path) {
        return getConfig().getString(path);
    }


    /**
     * Integer형 값을 불러올 때
     * @param path 경로
     * @return
     */
    public int getInteger(String path) {
        return getConfig().getInt(path);
    }


    /**
     * Boolean형 값을 불러올 때
     * @param path 경로
     * @return
     */
    public double getDouble(String path) {
        return getConfig().getDouble(path);
    }


    /**
     * Long형 값을 불러올 때
     * @param path 경로
     * @return
     */
    public boolean getBoolean(String path) {
        return getConfig().getBoolean(path);
    }


    /**
     * Long형 값을 불러올 때
     * @param path 경로
     * @return
     */
    public long getLong(String path) {
        return getConfig().getLong(path);
    }
}
