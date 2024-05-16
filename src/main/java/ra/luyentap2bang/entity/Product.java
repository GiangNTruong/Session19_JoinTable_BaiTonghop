package ra.luyentap2bang.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @Column(name = "pro_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer proId;
    @Column(name = "pro_name")
    private String proName;
    @Column(name = "producer")
    private String producer;
    @Column(name = "year_marking")
    private Integer yearMaking;
    @Column(name = "expire_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expireDate;
    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "cate_id", referencedColumnName = "cate_id")
    private Category cate;
}
