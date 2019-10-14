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

import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.generator.project.contributor.ProjectContributor;
import org.apache.commons.codec.Charsets;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * add properties for mybatis integration
 *
 * @author bruce ge
 */
public class MybatisProjectContributor implements ProjectContributor {

	private ProjectDescription description;

	public MybatisProjectContributor(ProjectDescription description) {
		this.description = description;
	}

	// add folder too?
	@Override
	public void contribute(Path projectRoot) throws IOException {
		Path changelogDirectory = projectRoot.resolve("src/main/resources/application.properties");
		File file = new File(changelogDirectory.toUri());
		if (file.exists()) {
			String content = "spring.datasource.url=jdbc:mysql://localhost:3306/helloman?serverTimezone=GMT%2b8\n"
					+ "spring.datasource.username=root\n" + "spring.datasource.password=world\n"
					+ "spring.datasource.driver-class-name=com.mysql.jdbc.Driver\n"
					+ "mybatis.mapper-locations= classpath*:mapper/*.xml";
			Files.write(changelogDirectory, content.getBytes(Charsets.UTF_8), StandardOpenOption.WRITE,
					StandardOpenOption.APPEND);
			Path resolve = projectRoot.resolve("src/main/resources/mapper");
			Files.createDirectories(resolve);

			Path sqlResolve = projectRoot.resolve("src/main/resources/mapper");
			Files.createDirectories(sqlResolve);

			String packageName = this.description.getPackageName();

			String replace = packageName.replace(".", "/");
			String baseFolder = "src/main/java/" + replace;
			Files.createDirectories(projectRoot.resolve(baseFolder + "/entity"));
			Files.createDirectories(projectRoot.resolve(baseFolder + "/mapper"));
			Files.createDirectories(projectRoot.resolve(baseFolder + "/service"));
		}
	}

	@Override
	public int getOrder() {
		return 1;
	}

}
