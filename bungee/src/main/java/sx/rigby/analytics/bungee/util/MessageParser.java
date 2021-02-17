package sx.rigby.analytics.bungee.util;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

@UtilityClass
public class MessageParser {

    public BaseComponent[] parse(@NonNull String value) {
        return TextComponent.fromLegacyText(
                ChatColor.translateAlternateColorCodes('&', value)
        );
    }
}
