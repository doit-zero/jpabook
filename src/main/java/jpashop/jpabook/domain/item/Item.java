package jpashop.jpabook.domain.item;

import jakarta.persistence.*;
import jpashop.jpabook.domain.Category;
import jpashop.jpabook.domain.OrderItem;
import jpashop.jpabook.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();


    //==비즈니스 로직==//
    // 데이터를 가지고 있는 곳에서 데이터를 조작하는 것이 객체지향적임 그게 응집도가 있음

    /*
    * stock 증가
    * */
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    /*
    * stock 감소
    * */
    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if(restStock < 0){
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
