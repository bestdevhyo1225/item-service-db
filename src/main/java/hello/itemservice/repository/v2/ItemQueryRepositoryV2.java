package hello.itemservice.repository.v2;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemSearchCond;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static hello.itemservice.domain.QItem.item;

@Repository
public class ItemQueryRepositoryV2 {

    private final JPAQueryFactory queryFactory;

    public ItemQueryRepositoryV2(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public List<Item> findAll(ItemSearchCond cond) {
        return queryFactory
            .select(item)
            .from(item)
            .where(
                itemNameLike(cond.getItemName()),
                itemPriceLoe(cond.getMaxPrice())
            )
            .fetch();
    }

    private BooleanExpression itemNameLike(String itemName) {
        if (StringUtils.hasText(itemName)) {
            return item.itemName.like("%" + itemName + "%");
        }
        return null;
    }

    private BooleanExpression itemPriceLoe(Integer maxPrice) {
        if (maxPrice != null) {
            return item.price.loe(maxPrice);
        }
        return null;
    }
}
