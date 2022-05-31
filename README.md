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

## @EventListener

### ApplicationReadyEvent

사용법은 `@EventListener(ApplicationReadyEvent.class)` 형식으로 작성하면 된다. AOP를 포함한 스프링 컨테이너가 완전히 초기화 된 이후에 호출된다.
보통 `@PostConstruct` 를 사용해서 초기화 작업을 진행하기도 하는데, AOP 부분이 다 적용(처리)되지 않은 시점에 호출될 수 있기 때문에 간혹 문제가 발생할 수 있다.  
