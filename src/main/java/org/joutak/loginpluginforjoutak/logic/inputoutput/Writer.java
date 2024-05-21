package org.joutak.loginpluginforjoutak.logic.inputoutput;

import org.joutak.loginpluginforjoutak.logic.dto.PlayerDto;
import org.joutak.loginpluginforjoutak.logic.dto.PlayerDtos;

import java.util.List;

public interface Writer {

    void write(PlayerDtos playerDtos);

    void addNew(PlayerDto playerDto);

}
