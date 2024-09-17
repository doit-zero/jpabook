package jpashop.jpabook.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jpashop.jpabook.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {
    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; // 주문 가격
    private int count; // 주문 수량

    //protected OrderItem() {
        // 외부에서 기본 생성자로 객체를 생성하여 set으로 필드값을 변경함을 방지하기 위함 + 값 주입은 오직 createItem으로 가능하도록 만드는 것임
    //} -> @NoArgesConstructor로 대체 가능

    //==생성 메서드==//
    public static OrderItem createItem(Item item,int orderPrice,int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;
    }


    //==비즈니스 로직==//
    public void cancel() {
        getItem().addStock(count);
    }

    //==조회 로직//
    /**
     * 주문 상품 전체 가겨 조회
     * */
    public int getTotalPrice() {
        return item.getPrice() * getCount();
    }
}
