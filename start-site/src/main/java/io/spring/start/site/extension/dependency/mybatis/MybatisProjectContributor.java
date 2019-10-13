package io.spring.start.site.extension.dependency.mybatis;

import io.spring.initializr.generator.buildsystem.Build;
import io.spring.initializr.generator.project.contributor.ProjectContributor;
import io.spring.initializr.generator.spring.build.BuildCustomizer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * pacakgeName io.spring.start.site.extension.dependency.mybatis
 *
 * @author bruce ge
 */
public class MybatisProjectContributor implements ProjectContributor {

    @Override
    public void contribute(Path projectRoot) throws IOException {
        Path changelogDirectory = projectRoot.resolve("src/main/resources/application.properties");
        File file = new File(changelogDirectory.toUri());
        if (file.exists()) {
            Files.write(changelogDirectory, "hello : world".getBytes(), StandardOpenOption.WRITE, StandardOpenOption.APPEND);
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
