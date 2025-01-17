package org.popcraft.chunky.platform;

import org.popcraft.chunky.util.Coordinate;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.serializer.TextSerializers;

import java.util.Optional;

import static org.popcraft.chunky.util.Translator.translateKey;

public class SpongeSender implements Sender {
    private CommandSource commandSource;

    public SpongeSender(CommandSource commandSource) {
        this.commandSource = commandSource;
    }

    @Override
    public boolean isPlayer() {
        return getPlayer().isPresent();
    }

    @Override
    public String getName() {
        return getPlayer().map(Player::getName).orElse("Console");
    }

    @Override
    public Coordinate getCoordinate() {
        return getPlayer()
                .map(Player::getLocation)
                .map(loc -> new Coordinate(loc.getBlockX(), loc.getBlockZ()))
                .orElse(new Coordinate(0, 0));
    }

    private Optional<Player> getPlayer() {
        if (commandSource instanceof Player) {
            return Optional.of((Player) commandSource);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void sendMessage(String key, boolean prefixed, Object... args) {
        commandSource.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(translateKey(key, prefixed, args)));
    }
}
