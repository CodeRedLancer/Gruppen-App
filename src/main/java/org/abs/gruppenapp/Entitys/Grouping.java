package org.abs.gruppenapp.Entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Grouping")
@Table(name = "grouping_table")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Grouping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idGrouping;

    @Column(name = "idGroup")
    private int idGroup;
    @Column(name = "idStudent")
    private int idStudent;
}
