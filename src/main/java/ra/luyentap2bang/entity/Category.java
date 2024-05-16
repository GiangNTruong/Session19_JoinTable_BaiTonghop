package ra.luyentap2bang.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @Column(name = "cate_id")
    private String cateId;
    @Column(name = "cate_name")
    private String catenName;
    @Column(name = "status")
    private Boolean status;

    @OneToMany(mappedBy = "cate",fetch = FetchType.EAGER)
    private Set<Product> products;

}
