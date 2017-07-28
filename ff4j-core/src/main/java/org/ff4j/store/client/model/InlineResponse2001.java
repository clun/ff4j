/*
 * FF4J (ff4j.org) WebAPI
 * Administrate and operate all tasks on your features through this api.
 *
 * OpenAPI spec version: 1.5
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package org.ff4j.store.client.model;

/*
 * #%L
 * ff4j-core
 * %%
 * Copyright (C) 2013 - 2017 FF4J
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

import java.util.Objects;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.ff4j.store.client.model.InlineResponse200FeaturesStoreCache;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * InlineResponse2001
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2017-07-27T20:07:45.848Z")
public class InlineResponse2001 {
  @SerializedName("cache")
  private InlineResponse200FeaturesStoreCache cache = null;

  @SerializedName("numberOfProperties")
  private Integer numberOfProperties = null;

  @SerializedName("properties")
  private List<String> properties = null;

  @SerializedName("type")
  private String type = null;

  public InlineResponse2001 cache(InlineResponse200FeaturesStoreCache cache) {
    this.cache = cache;
    return this;
  }

   /**
   * Get cache
   * @return cache
  **/
  @ApiModelProperty(value = "")
  public InlineResponse200FeaturesStoreCache getCache() {
    return cache;
  }

  public void setCache(InlineResponse200FeaturesStoreCache cache) {
    this.cache = cache;
  }

  public InlineResponse2001 numberOfProperties(Integer numberOfProperties) {
    this.numberOfProperties = numberOfProperties;
    return this;
  }

   /**
   * Get numberOfProperties
   * @return numberOfProperties
  **/
  @ApiModelProperty(value = "")
  public Integer getNumberOfProperties() {
    return numberOfProperties;
  }

  public void setNumberOfProperties(Integer numberOfProperties) {
    this.numberOfProperties = numberOfProperties;
  }

  public InlineResponse2001 properties(List<String> properties) {
    this.properties = properties;
    return this;
  }

  public InlineResponse2001 addPropertiesItem(String propertiesItem) {
    if (this.properties == null) {
      this.properties = new ArrayList<String>();
    }
    this.properties.add(propertiesItem);
    return this;
  }

   /**
   * Get properties
   * @return properties
  **/
  @ApiModelProperty(value = "")
  public List<String> getProperties() {
    return properties;
  }

  public void setProperties(List<String> properties) {
    this.properties = properties;
  }

  public InlineResponse2001 type(String type) {
    this.type = type;
    return this;
  }

   /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(value = "")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineResponse2001 inlineResponse2001 = (InlineResponse2001) o;
    return Objects.equals(this.cache, inlineResponse2001.cache) &&
        Objects.equals(this.numberOfProperties, inlineResponse2001.numberOfProperties) &&
        Objects.equals(this.properties, inlineResponse2001.properties) &&
        Objects.equals(this.type, inlineResponse2001.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cache, numberOfProperties, properties, type);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse2001 {\n");
    
    sb.append("    cache: ").append(toIndentedString(cache)).append("\n");
    sb.append("    numberOfProperties: ").append(toIndentedString(numberOfProperties)).append("\n");
    sb.append("    properties: ").append(toIndentedString(properties)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
  
}

