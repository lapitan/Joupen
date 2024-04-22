package org.joutak.loginpluginforjoutak.logic.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.joutak.loginpluginforjoutak.logic.dto.PlayerDto;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LoginPluginLogicImpl implements LoginPluginLogic {

    Map<UUID, PlayerDto> playerDtoMap = new HashMap<>();

    @Override
    public void addPlayer(Player player, Integer prolongTimeInMonths) {
        //todo logic
    }

    @Override
    public void removePlayer(Player player) {
        //todo logic
    }

    @Override
    public boolean checkPlayer(Player player) {
        //todo logic
        return false;
    }

}
