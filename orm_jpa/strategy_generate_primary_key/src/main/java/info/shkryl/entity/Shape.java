package info.shkryl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Data
@Table(name = "shape")
public class Shape {
    @Id
    @TableGenerator(
            name="shape_generator",
            table="shape_id_generator",
            pkColumnName="id",
            valueColumnName="value",
            pkColumnValue = "shape",
            allocationSize=1
    )
    @GeneratedValue(strategy= GenerationType.TABLE,generator="shape_generator")
    private Integer id;

    private String name;
}
