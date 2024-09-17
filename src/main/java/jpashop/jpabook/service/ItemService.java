package jpashop.jpabook.service;

import jpashop.jpabook.domain.item.Book;
import jpashop.jpabook.domain.item.Item;
import jpashop.jpabook.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    /**
     * 변경 감지로 데이터 변경되는 경우
     * 1. itemRepository에서 데이터를 가져온다. 이 때 가져온 데이터는 영속성 컨텍스트에 등록됨
     * 2. DB에서 가져온 데이터를 변경
     * 3. 업데이트나 뭐를 할 필요가 없다. 왜냐하면 @Transactional로 커밋이 되기 때문에
     * 4. 커밋이 될 때 JPA는 flush를 실행하면 이 때, 변경된 값들도 감지하여 변경된 값을 DB에 커밋한다.
     *  가급적이면 병합보다는 변경 감지를 쓰는게 안전하다. 변경감지로 명확하게 해주는게 훨씬 낫다.
     * */
    @Transactional
    public void updateItem(Long itemId, String name,int price,int stockQuantity){
        Item findItem = itemRepository.findOne(itemId);
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
}
