package angry_bird.utils;

public class Constants {
    public static class UI{
        public static class skin{
            private static final String METAL = "skins/metal/metal-ui.json";
            private static final String FREEZING = "skins/freezing/freezingui/freezing-ui.json";
            private static final String COMIC = "skins/comic/comicui/comic-ui.json";
            public static final String BUTTON_SKIN = FREEZING;
        }

        public static class Backgrounds{
            public static final String MENU_SCREEN_BACKGROUND = "screens/main_menu_screen/background.png";
            public static final String LEVEL_SCREEN_BACKGROUND = "screens/level_select/background.png";
            public static final String WINNING_BACKGROUND = "screens/playing_screen/winning/winning.png";
            public static final String LOOSING_BACKGROUND = "screens/playing_screen/loosing/loosing.png";
        }

        public static class Healths{
            public static class BlockHealth{
                public static final float GLASS_HEALTH = 1000;
                public static final float STONE_HEALTH = 3000;
                public static final float WOOD_HEALTH = 2000;
            }
            public static class PigHealth{
                public static final float MINION_PIG_HEALTH = 1000;
                public static final float FOREMAN_PIG_HEALTH = 2000;
                public static final float KING_PIG_HEALTH = 3000;
            }
        }
    }

}
