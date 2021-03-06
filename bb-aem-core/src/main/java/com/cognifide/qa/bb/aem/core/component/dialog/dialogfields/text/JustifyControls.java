/*-
 * #%L
 * Bobcat
 * %%
 * Copyright (C) 2018 Cognifide Ltd.
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
package com.cognifide.qa.bb.aem.core.component.dialog.dialogfields.text;

import org.openqa.selenium.WebElement;

import com.cognifide.qa.bb.qualifier.PageObjectInterface;

/**
 * Interface that represents text justification options of Rich Text editor.
 */
@PageObjectInterface
public interface JustifyControls {

  /**
   * @return {@link WebElement} representing the Justify Left button
   */
  WebElement getJustifyLeftBtn();

  /**
   * @return {@link WebElement} representing the Justify Center button
   */
  WebElement getJustifyCenterBtn();

  /**
   * @return {@link WebElement} representing the Justify Right button
   */
  WebElement getJustifyRightBtn();
}
