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
package com.cognifide.qa.bb.aem.api.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.cognifide.qa.bb.qualifier.PageObject;

@PageObject(css = ".page__hero--overlay")
public class PageHero {

  @FindBy(css = ".btn.btn--light-outline.btn--large")
  private WebElement button;

  //todo think about returning a wrapper here (QuestionTarget?) that will wrap WebElement
  // and allow easier assertions of attributes, condition validation and general state probing
  public WebElement button() {
    return button;
  }

  public String buttonText() {
    return button.getText();
  }
}
