package org.popcraft.chunky.platform;

import org.popcraft.chunky.GenerationTask;

import java.util.List;
import java.util.Optional;

public interface Config {
    Optional<GenerationTask> loadTask(World world);

    List<GenerationTask> loadTasks();

    void saveTask(GenerationTask generationTask);

    void saveTasks();

    void cancelTask(World world);

    void cancelTasks();

    int getVersion();

    String getLanguage();

    boolean getContinueOnRestart();

    void reload();
}
