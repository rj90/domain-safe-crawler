package com.rafalj.crawler.model;

import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
@Setter
public class PageInfo implements Serializable {

  private String title;
  private String url;
  private List<PageInfo> elements;
}