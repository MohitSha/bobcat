/*-
 * #%L
 * Bobcat
 * %%
 * Copyright (C) 2016 Cognifide Ltd.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.cognifide.qa.bb.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cognifide.qa.bb.constants.ConfigKeys;

public class LegacyConfig implements ConfigStrategy {
  private static final Logger LOG = LoggerFactory.getLogger(LegacyConfig.class);
  private Properties properties = null;
  private static final String[] PREFIXES = new String[] {"webdriver.", "phantomjs."};

  @Override
  public Properties gatherProperties() {
    if (properties == null) {
      try {
        String parents = System.getProperty(ConfigKeys.CONFIGURATION_PATHS, "src/main/config");
        String[] split = StringUtils.split(parents, ";");
        properties = loadDefaultProperties();
        for (String name : split) {
          File configParent = new File(StringUtils.trim(name));
          loadProperties(configParent, properties);
        }
        overrideFromSystemProperties(properties);
        setSystemProperties(properties);
      } catch (IOException e) {
        LOG.error("Can't bind properties", e);
      }
    }
    return properties;
  }

  private Properties loadDefaultProperties() throws IOException {
    Properties defaultProperties = new Properties();
    try (InputStream is = PropertyBinder.class.getClassLoader()
        .getResourceAsStream(ConfigKeys.DEFAULT_PROPERTIES_NAME)) {
      defaultProperties.load(is);
    } catch (IOException e) {
      LOG.error("Can't bind default properties", e);
    }
    return defaultProperties;
  }

  private void loadProperties(File file, Properties properties) throws IOException {
    if (!file.exists()) {
      LOG.warn("{} file doesn't exists.", file.getPath());
    } else {
      if (file.isDirectory()) {
        for (File child : file.listFiles()) {
          loadProperties(child, properties);
        }
      } else {
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));

        LOG.debug("loading properties from: {} (ie. {})", file, file.getAbsolutePath());
        try {
          properties.load(reader);
        } finally {
          reader.close();
        }
      }
    }
  }

  private void overrideFromSystemProperties(Properties properties) {
    System.getProperties().stringPropertyNames().stream().forEach(key -> {
      String systemProperty = System.getProperty(key);
      if (StringUtils.isNotBlank(systemProperty)) {
        properties.setProperty(key, systemProperty);
      }
    });
  }

  private void setSystemProperties(Properties properties) {
    for (String key : properties.stringPropertyNames()) {
      String systemProperty = System.getProperty(key);
      if (StringUtils.isNotBlank(systemProperty)) {
        continue;
      }
      for (String prefix : PREFIXES) {
        if (key.startsWith(prefix)) {
          System.setProperty(key, properties.getProperty(key));
          break;
        }
      }
    }
  }
}
