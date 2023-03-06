package com.jesusfc.springboot3java17.openApi.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * PagedResponsePageableSort
 */
@Validated
public class PagedResponsePageableSort   {
  @JsonProperty("sorted")
  private Boolean sorted = null;

  @JsonProperty("unsorted")
  private Boolean unsorted = null;

  public PagedResponsePageableSort sorted(Boolean sorted) {
    this.sorted = sorted;
    return this;
  }

  /**
   * Get sorted
   * @return sorted
   **/
  @Schema(description = "")
  
    public Boolean isSorted() {
    return sorted;
  }

  public void setSorted(Boolean sorted) {
    this.sorted = sorted;
  }

  public PagedResponsePageableSort unsorted(Boolean unsorted) {
    this.unsorted = unsorted;
    return this;
  }

  /**
   * Get unsorted
   * @return unsorted
   **/
  @Schema(description = "")
  
    public Boolean isUnsorted() {
    return unsorted;
  }

  public void setUnsorted(Boolean unsorted) {
    this.unsorted = unsorted;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PagedResponsePageableSort pagedResponsePageableSort = (PagedResponsePageableSort) o;
    return Objects.equals(this.sorted, pagedResponsePageableSort.sorted) &&
        Objects.equals(this.unsorted, pagedResponsePageableSort.unsorted);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sorted, unsorted);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PagedResponsePageableSort {\n");
    
    sb.append("    sorted: ").append(toIndentedString(sorted)).append("\n");
    sb.append("    unsorted: ").append(toIndentedString(unsorted)).append("\n");
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
