package org.joutak.loginpluginforjoutak.logic.inputoutput;

import org.joutak.loginpluginforjoutak.logic.dto.PlayerDto;

import java.util.List;

public interface Writer {

    void write(List<PlayerDto> playerDtos);

}
