/*
 *
 *  * Copyright 2012-2019 the original author or authors.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      https://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 *
 *
 */

package io.spring.start.site.extension.dependency.mybatis;

import io.spring.initializr.generator.project.contributor.ProjectContributor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * add properties for mybatis integration
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
