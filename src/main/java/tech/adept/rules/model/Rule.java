package tech.adept.rules.model;

import jakarta.persistence.*;

@Entity
@Table(name = "rules")
public class Rule {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "published")
  private boolean published;

  @Column(name = "Condition")
  private String Condition;

  @Column(name = "Countries")
  private String Countries;

  @Column(name = "Action")
  private String Action;

  public Rule() {

  }

  public Rule(long id, String title, String description, boolean published, String condition, String countries,
              String action) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.published = published;
    Condition = condition;
    Countries = countries;
    Action = action;
  }

  public long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isPublished() {
    return published;
  }

  public void setPublished(boolean isPublished) {
    this.published = isPublished;
  }

  @Override
  public String toString() {
    return "Tutorial [id=" + id + ", title=" + title + ", desc=" + description + ", published=" + published + "]";
  }

  public String getCondition() {
    return Condition;
  }

  public String getCountries() {
    return Countries;
  }

  public String getAction() {
    return Action;
  }

  public void setAction(String action) {
    Action = action;
  }
}
