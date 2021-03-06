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
package com.cognifide.qa.bb.qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * PageObject is a central concept of Bobcat. It's a term derived from Selenium framework and it means a class
 * that encapsulates page HTML and exposes page features. Add this annotation to classes that represent pages
 * or components within pages.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface PageObject {

  /**
   * Use this property to identify the page object by css selector
   *
   * @return page object css class
   */
  String css() default "";

  /**
   * Use this property to identify the page object by xpath selector
   *
   * @return page object xpath
   */
  String xpath() default "";
}
