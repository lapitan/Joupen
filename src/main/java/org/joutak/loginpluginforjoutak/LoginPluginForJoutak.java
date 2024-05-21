package org.joutak.loginpluginforjoutak;

import lombok.Getter;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.joutak.loginpluginforjoutak.commands.LoginAddAndRemovePlayerCommand;
import org.joutak.loginpluginforjoutak.event.PlayerJoinEventHandler;
import org.joutak.loginpluginforjoutak.logic.dto.PlayerDto;
import org.joutak.loginpluginforjoutak.logic.dto.PlayerDtos;
import org.joutak.loginpluginforjoutak.logic.dto.converter.PlayerDtoCalendarConverter;
import org.joutak.loginpluginforjoutak.logic.inputoutput.JsonWriterImpl;
import org.joutak.loginpluginforjoutak.utils.JoutakLoginProperties;
import org.joutak.loginpluginforjoutak.utils.ResourcesJsonReader;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Slf4j
public final class LoginPluginForJoutak extends JavaPlugin {

    @Getter
    private static LoginPluginForJoutak instance;

    @Override
    public void onEnable() {

        instance=this;
        ResourcesJsonReader.getResources();
        if (!JoutakLoginProperties.enabled){
            log.error("Plugin was disabled in config. Enable it in config file");
            return;
        }

        new LoginAddAndRemovePlayerCommand();
        Bukkit.getPluginManager().registerEvents(new PlayerJoinEventHandler(), this);
//        List<PlayerDto> playerDtos = List.of(
//                PlayerDto.builder()
//                        .name("1")
//                        .uuid("1")
//                        .lastProlongDate("2024-05-20")
//                        .validUntil("2024-06-20").build(),
//                PlayerDto.builder()
//                        .name("2")
//                        .uuid("2")
//                        .lastProlongDate("2023-05-20")
//                        .validUntil("2024-05-20").build()
//        );
//        System.out.println("1");
//        JsonWriterImpl jsonWriter = new JsonWriterImpl(JoutakLoginProperties.saveFilepath);
//        jsonWriter.write(new PlayerDtos(playerDtos));

//        LocalDateTime player1= PlayerDtoCalendarConverter.getLastProlongDate(playerDtos.get(0));
//        LocalDateTime player2= PlayerDtoCalendarConverter.getCurrentProlongDate(playerDtos.get(1));
//
//        log.info(String.valueOf(player1.isAfter(player2)));
//
//        log.info("player1: {}",JoutakLoginProperties.dateTimeFormatter.format(player1));
//        log.info("player2: {}",JoutakLoginProperties.dateTimeFormatter.format(player2));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
