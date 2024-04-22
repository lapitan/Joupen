package org.joutak.loginpluginforjoutak.logic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto {

    String name;

    String uuid;

    Calendar lastProlongDate;

    Integer prolongTimeInMonths;

}
