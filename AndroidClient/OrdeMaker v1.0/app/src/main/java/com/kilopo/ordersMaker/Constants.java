package com.kilopo.ordersMaker;


import com.kilopo.ordersMaker.dto.MenuDTO;

import java.util.ArrayList;

public final class Constants {
    public static final int TAB_ONE = 0;
    public static final int TAB_TWO = 1;
    public static final int TAB_THREE = 2;
    public static final int TAB_FOUR = 3;

    public static ArrayList<MenuDTO> menuDTOs = new ArrayList<MenuDTO>();
    public static ArrayList<MenuDTO> standartList = new ArrayList<MenuDTO>();

    public static ArrayList<MenuDTO> getMenuDTOs() {
        return menuDTOs;
    }

    public static void setMenuDTOs(ArrayList<MenuDTO> menuDTOs) {
        Constants.menuDTOs = menuDTOs;
    }

    public static ArrayList<MenuDTO> getStandartList() {
        return standartList;
    }

    public static void setStandartList(ArrayList<MenuDTO> standartList) {
        Constants.standartList = standartList;
    }

    public static final class URL {
        private static final String HOST =  "http://10.60.1.242:8080/"; //"http://192.168.101.30:8080/";
        public static final String GET_REMIND_ITEM = HOST + "menus/";
    }

}
