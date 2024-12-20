package org.joutak.loginpluginforjoutak.event;

import lombok.extern.slf4j.Slf4j;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.joutak.loginpluginforjoutak.logic.dto.PlayerDto;
import org.joutak.loginpluginforjoutak.logic.dto.PlayerDtos;
import org.joutak.loginpluginforjoutak.logic.dto.converter.PlayerDtoCalendarConverter;
import org.joutak.loginpluginforjoutak.logic.dto.utils.PlayerDtosUtils;
import org.joutak.loginpluginforjoutak.logic.inputoutput.JsonReaderImpl;
import org.joutak.loginpluginforjoutak.logic.inputoutput.JsonWriterImpl;
import org.joutak.loginpluginforjoutak.logic.inputoutput.Reader;
import org.joutak.loginpluginforjoutak.logic.inputoutput.Writer;
import org.joutak.loginpluginforjoutak.utils.JoutakLoginProperties;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.EventListener;

@Slf4j
public class PlayerJoinEventHandler implements EventListener, Listener {

    @EventHandler
    public void playerJoinEvent(PlayerLoginEvent playerLoginEvent) {

        PlayerDto playerDto = PlayerDtosUtils.findPlayerByName(playerLoginEvent.getPlayer().getName());
        if (playerDto == null) {
            TextComponent textComponent = Component.text()
                    .append(Component.text("Тебя нет в вайтлисте. Напиши по этому поводу ", NamedTextColor.BLUE))
                    .append(Component.text("EnderDiss'e", NamedTextColor.RED))
                    .build();
            playerLoginEvent.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, textComponent);
            return;
        }

        if (PlayerDtoCalendarConverter.getValidUntil(playerDto).isBefore(LocalDate.now())) {
            TextComponent textComponent = Component.text()
                    .append(Component.text("Проходка кончилась((( Надо оплатить и написать ", NamedTextColor.BLUE))
                    .append(Component.text("EnderDiss'e", NamedTextColor.RED))
                    .build();
            playerLoginEvent.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, textComponent);
            return;
        }
        String uuid = playerDto.getUuid();
        if (uuid.equals("-1")) {
            Writer writer = new JsonWriterImpl(JoutakLoginProperties.saveFilepath);
            Reader reader = new JsonReaderImpl(JoutakLoginProperties.saveFilepath);

            PlayerDtos playerDtos = reader.read();
            playerDtos.getPlayerDtoList().remove(playerDto);
            LocalDate now = LocalDate.now();
            LocalDate validUntil = PlayerDtoCalendarConverter.getValidUntil(playerDto);
            LocalDate lastProlongDate = PlayerDtoCalendarConverter.getLastProlongDate(playerDto);
            validUntil = validUntil.plusDays(ChronoUnit.DAYS.between(lastProlongDate, now));
            playerDto.setValidUntil(validUntil.format(JoutakLoginProperties.dateTimeFormatter));
            playerDto.setLastProlongDate(now.format(JoutakLoginProperties.dateTimeFormatter));
            playerDtos.getPlayerDtoList().add(playerDto);
            writer.write(playerDtos);
            log.warn("Player {} joined for the first time, adjusted prohodka", playerDto.getName());
        }
        if (!uuid.equals(playerLoginEvent.getPlayer().getUniqueId().toString())) {
            Writer writer = new JsonWriterImpl(JoutakLoginProperties.saveFilepath);
            Reader reader = new JsonReaderImpl(JoutakLoginProperties.saveFilepath);

            PlayerDtos playerDtos = reader.read();
            playerDtos.getPlayerDtoList().remove(playerDto);
            playerDto.setUuid(playerLoginEvent.getPlayer().getUniqueId().toString());
            playerDtos.getPlayerDtoList().add(playerDto);
            writer.write(playerDtos);
            log.warn("changed UUID of player {} to new one {}", playerDto.getName(), playerDto.getUuid());
        }

        playerLoginEvent.allow();
    }

}
