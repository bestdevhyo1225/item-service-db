# Item Service DB

## DTO의 위치?

`DTO` 를 마지막으로 제공하는 곳이 어디냐에 따라 `DTO` 를 해당 패키지에 위치하면 된다. 예를 들면, `Repository` 레이어에서 제공하는 경우에는 `repository` 패키지에 위치해야 한다.

```java
public interface ItemRepository {

    Item save(Item item);

    void update(Long itemId, ItemUpdateDto updateParam);

    Optional<Item> findById(Long id);

    List<Item> findAll(ItemSearchCond cond);

}
```

`ItemUpdateDto`, `ItemSearchCond` 는 `DTO` 인데 `Repository` 레이어에서 마지막으로 제공하기 때문에 `repository` 패키지 내부에 위치해야 한다.

만약에 `Service` 레이어에서 `DTO` 를 마지막으로 제공한다면, `service` 패키지 내부에 `DTO` 들을 위치 시켜두면 된다.
