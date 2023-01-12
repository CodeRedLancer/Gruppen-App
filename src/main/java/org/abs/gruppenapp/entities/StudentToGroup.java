package org.abs.gruppenapp.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentToGroup {
  private String firstName;
  private String lastName;
  private int evaluation;
}
