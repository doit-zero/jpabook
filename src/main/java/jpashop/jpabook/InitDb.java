package jpashop.jpabook;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jpashop.jpabook.domain.*;
import jpashop.jpabook.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;
    @PostConstruct
    public void init(){
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{
        private final EntityManager em;

        public void dbInit1(){
            Member member = createMember("userA","인천","2","22");
            em.persist(member);

            Book book = new Book();
            book.setName("JPA1 BOOK");
            book.setPrice(10000);
            book.setStockQuantity(100);
            em.persist(book);

            Book book2 = new Book();
            book2.setName("JPA2 BOOK");
            book2.setPrice(20000);
            book2.setStockQuantity(100);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createItem(book, 10000, 1);
            OrderItem orderItem2 = OrderItem.createItem(book2, 20000, 2);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        public void dbInit2(){
            Member member = createMember("userB","서울","1","11");
            em.persist(member);

            Book book = new Book();
            book.setName("SPRING1 BOOK");
            book.setPrice(20000);
            book.setStockQuantity(200);
            em.persist(book);

            Book book2 = new Book();
            book2.setName("SPRING2 BOOK");
            book2.setPrice(40000);
            book2.setStockQuantity(300);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createItem(book, 20000, 3);
            OrderItem orderItem2 = OrderItem.createItem(book2, 40000, 4);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        private static Member createMember(String name,String city,String street,String zipcode) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city,street,zipcode));
            return member;
        }
    }
}


