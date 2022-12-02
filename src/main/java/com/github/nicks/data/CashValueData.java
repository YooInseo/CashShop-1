package com.github.nicks.data;

import com.github.nicks.utils.ConfigUtils;
import org.bukkit.entity.Player;

import static com.github.nicks.CashShop.plugin;


public class CashValueData {

    private ConfigUtils config;
    private Player player;



    /**
     * 플레이어에게 캐시를 지급합니다.
     *
     * @param player
     * @return amount 지급할 캐시
     */
    public void depositCash(Player player, Double amount) {
        config = new ConfigUtils("data/" + player.getUniqueId(), plugin);
        config.setDouble("playerdata.cash", config.getDouble("playerdata.cash") + amount);
        player.sendMessage(amount + "지급 완료");
    }


    /**
     * 플레이어의 캐시를 차감합니다.
     *
     * @param player
     * @return amount 차감할 캐시
     */

    public void withdrawCash(Player player, Double amount) {
        config = new ConfigUtils("data/" + player.getUniqueId(), plugin);
        config.setDouble("playerdata.cash", config.getDouble("playerdata.cash") - amount);
        player.sendMessage(amount + "차감 완료");
    }


    /**
     * [ 유틸 ] 플레이어의 캐시를 설정합니다.
     *
     * @param player
     * @param amount 설정할 캐시
     */
    public void setCash(Player player, Double amount) {
        config = new ConfigUtils("data/" + player.getUniqueId(), plugin);
        config.setDouble("playerdata.cash", amount);
    }


    /**
     * [ 유틸 ] 플레이어의 캐시를 가져옵니다.
     *
     * @param player
     * @return 값을 리턴합니다.
     */
    public Double getCash(Player player) {
        this.player = player;
        config = new ConfigUtils("data/" + this.player.getUniqueId(), plugin);
        return config.getConfig().getDouble("playerdata.cash");
    }

}
